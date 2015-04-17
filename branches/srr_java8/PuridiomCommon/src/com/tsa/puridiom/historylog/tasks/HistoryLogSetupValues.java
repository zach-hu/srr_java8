/*
 * Created on Jul 20, 2004
 *
 * @author  * renzo
 * package com.tsa.puridiom.historylog.tasks;.HistoryLogSetupValues.java
 *
 */
package com.tsa.puridiom.historylog.tasks;

import java.math.BigDecimal;
import java.util.Map;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.entity.HistoryLog;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.UniqueKeyGenerator;
import com.tsagate.foundation.utility.Utility;

public class HistoryLogSetupValues
{
    private String organizationId = "PURIDIOM";
    private String userId = "";
    private String timeZone = "";
    private String ipAddress = "";
	private Object historyStatus = null;
    private String forwardedTo = "";
    private String rejectedBy = "";
    private Object extraIc;
    private String approverNotes = "";

    public String getForwardedTo()
    {
        return forwardedTo;
    }
    public void setForwardedTo(String forwardedTo)
    {
        this.forwardedTo = forwardedTo;
    }
    public String getRejectedBy()
    {
        return rejectedBy;
    }
    public void setRejectedBy(String rejectedBy)
    {
        this.rejectedBy = rejectedBy;
    }
    public String getUserId()
    {
        return userId;
    }
    public void setUserId(String userId)
    {
        this.userId = userId;
    }

    public String getTimeZone()
    {
        return this.timeZone;
    }

    public void setTimeZone(String timeZone)
    {
        this.timeZone = timeZone;
    }

    public String getIpAddress()
	{
		return ipAddress;
	}

	public void setIpAddress(String ipAddress)
	{
		this.ipAddress = ipAddress;
	}
	
    public String getApproverNotes() {
		return approverNotes;
	}
	public void setApproverNotes(String approverNotes) {
		this.approverNotes = approverNotes;
	}
	
	public Map setUpIncomingRequest(Map incomingRequest)
    {
        UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();
        String userTimeZone = (String) incomingRequest.get("userTimeZone");
        incomingRequest.put("HistoryLog_icHistory",	ukg.getUniqueKey().toString());
        incomingRequest.put("HistoryLog_logDate", Dates.today("", userTimeZone));
        incomingRequest.put("HistoryLog_logTime",  Dates.getTimeString(Dates.today("", userTimeZone)));
        incomingRequest.put("HistoryLog_userid", incomingRequest.get("userId"));
        incomingRequest.put("HistoryLog_timeZone", userTimeZone);

        return incomingRequest;
    }

    public HistoryLog setUpHistory(HistoryLog history)
    {
        if(history == null)
        {
            history = new HistoryLog();
        }

        UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();
        history.setIcHistory(new BigDecimal(ukg.getUniqueKey().toString()));
        history.setLogDate(Dates.today("yyyy/MM/dd", this.getTimeZone()));
        history.setLogTime(Dates.getNow(null, this.getTimeZone()));
        history.setIpAddress(this.ipAddress);
        history.setTimeZone(this.getTimeZone());

        return history;
    }
    public String getOrganizationId()
    {
        return organizationId;
    }
    public void setOrganizationId(String organizationId)
    {
        this.organizationId = organizationId;
    }
    public Object getHistoryStatus()
    {
        if(Utility.isObjectEmpty(this.historyStatus))
        {
            return "";
        }
        else
        {
            return historyStatus;
        }
    }
    public void setHistoryStatus(Object historyStatus)
    {
        this.historyStatus = historyStatus;
    }

    public boolean testStatus(String reqStatus, String historyStatus, String typeHistory)
    {
        if(typeHistory.equalsIgnoreCase("forwarded"))
        {
            if( reqStatus.equals(DocumentStatus.REQ_APPROVING) && historyStatus.equals(HistoryStatus.REQ_FORWARDED))
            {
                return true;
            }
            else if (reqStatus.equals(DocumentStatus.REQ_INPROGRESS) && historyStatus.equals(HistoryStatus.REQ_FORWARDED))
            {
                return true;
            }
        }
        return false;
    }

    public Object getExtraIc()
    {
        if(Utility.isObjectEmpty(this.extraIc))
        {
            return "0";
        }
        else
        {
            return this.extraIc;
        }
    }
    public void setExtraIc(Object _extraIc)
    {
        this.extraIc = _extraIc;
    }

}
