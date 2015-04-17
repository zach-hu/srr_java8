/*
 * Created on Jul 22, 2004
 *
 * @author  * renzo
 * package com.tsa.puridiom.historylog.tasks.RfqLineSetupHistoryValues.java
 *
 */
package com.tsa.puridiom.historylog.tasks;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.entity.HistoryLog;
import com.tsa.puridiom.entity.RfqHeader;
import com.tsa.puridiom.entity.RfqLine;

public class RfqLineSetupHistoryValues extends HistoryLogSetupValues
{
	private boolean autoReleased = false;
    private String nextUser = "";
    private String reason = "";

    public HistoryLog rfqLineSetup(HistoryLog history, RfqLine line, RfqHeader header)
    {
        if(history == null){	history = this.setUpHistory(history);	}
        history.setIcHeaderHistory(header.getIcHeaderHistory());
        history.setIcLineHistory(line.getIcLineHistory());
        history.setIcHeader(header.getIcRfqHeader());
        history.setIcLine(line.getIcRfqLine());
 	    history.setDoctype("RFL");
 	    history.setStatus(line.getStatus());
 	   if(this.isAutoReleased())
       {
           history.setUserid("AUTORELEASE");
       }
       else
       {
           history.setUserid(header.getOwner());
       }

 	    return history;

    }
    /**
     * @param oldHeader
     * @param newHeader
     * @return HistoryLog
     */
    public HistoryLog getLineHistoryLog(RfqLine newLine, RfqHeader header)
    {
        HistoryLog history = null;
        history = this.getHistoryRecord(newLine, header);

        return history;
    }

    public HistoryLog setText(RfqLine line, HistoryLog history, RfqHeader header)
    {
        HistoryLogRfqText text = new HistoryLogRfqText(header, line);
        String status = line.getStatus();

        text.setOrganizationId(this.getOrganizationId());

        if(status.equals(DocumentStatus.RFQ_OPENSOLICITATION))
        {
            history.setDescription(text.forwardLineText());
        }
        else if(status.equals(DocumentStatus.RFQ_INPROGRESS))
        {
            history.setDescription(text.createLineText());
        }
        else if(status.equals(DocumentStatus.RFQ_APPROVING))
        {
            history.setDescription(text.approvingLineText(this.getNextUser()));
        }
        else if(status.equals(DocumentStatus.CANCELLED))
        {
            history.setDescription(text.cancelLineText());
        }
        else if(status.equals(DocumentStatus.RFQ_PURCHASING))
        {
            history.setDescription(text.approveLineText());
        }
        else
        {
            history = null;
        }
        return history;
    }

    /**
     * @param status
     */
    private HistoryLog getHistoryRecord(RfqLine line, RfqHeader header)
    {
        HistoryLog history = this.rfqLineSetup(null, line, header);
        history = this.setText(line, history, header);

        return history;
    }
    public boolean isAutoReleased()
    {
        return autoReleased;
    }
    public void setAutoReleased(boolean autoReleased)
    {
        this.autoReleased = autoReleased;
    }
	public String getNextUser() {
		return nextUser;
	}
	public void setNextUser(String nextUser) {
		this.nextUser = nextUser;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
}