/*
 * Created on August 27, 2004
 * package com.tsa.puridiom.historylog.tasks.RfqSetupValues.java
 *
 */
package com.tsa.puridiom.historylog.tasks;

import java.math.BigDecimal;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.HistoryLog;
import com.tsa.puridiom.entity.RfqHeader;

public class RfqSetupValues extends HistoryLogSetupValues
{
	private boolean autoReleased = false;
    private String nextUser = "";
    private String reason = "";
	/**
     * @param oldHeader
     * @param newHeader
     * @return HistoryLog
     */
    public HistoryLog getHeaderHistoryLog(RfqHeader newHeader)
    {
        HistoryLog history = null;

        history = this.getHistoryRecord(newHeader);


        return history;
    }

    public HistoryLog setText(RfqHeader header, HistoryLog history)
    {
        HistoryLogRfqText text = new HistoryLogRfqText(header);
        String status = header.getStatus();

        text.setOrganizationId(this.getOrganizationId());

        if(status.equals(DocumentStatus.RFQ_PURCHASING))
        {
            history.setDescription(text.forwardText());
        }
        else if(status.equals(DocumentStatus.RFQ_INPROGRESS))
        {
            history.setDescription(text.createText());
        }
        else if(status.equals(DocumentStatus.CANCELLED))
        {
            history.setDescription(text.cancelText());
        }
        else if(status.equals(DocumentStatus.RFQ_APPROVING))
        {
            history.setDescription(text.approvingText(this.getNextUser()));
        }
        else if(status.equals(DocumentStatus.RFQ_OPENSOLICITATION))
        {
            history.setDescription(text.openText());
        }
        else if(status.equals(DocumentStatus.PO_AWARDED))
        {
            history.setDescription(text.awardedText());
        }else if(status.equals(DocumentStatus.RFQ_REJECTED))
        {
            history.setDescription(text.rejectText(this.getRejectedBy()));
        }
        else
        {
            history = null;
        }
        
        if (!HiltonUtility.isEmpty(this.getApproverNotes())) {
            history.setDescription(history.getDescription() + " Approval Notes: " + this.getApproverNotes());
        }
        
        return history;
    }

    /**
     * @param status
     */
    private HistoryLog getHistoryRecord(RfqHeader header)
    {
        HistoryLog history = this.rfqHeaderSetup(null, header);
        history = this.setText(header, history);

        return history;
    }


    public HistoryLog rfqHeaderSetup(HistoryLog history, RfqHeader header)
    {
        if(history == null){	history = this.setUpHistory(history);	}
        history.setIcHeaderHistory(header.getIcHeaderHistory());
        history.setIcLineHistory(new BigDecimal("0"));
        history.setIcHeader(header.getIcRfqHeader());
        history.setIcLine(new BigDecimal("0"));
 	    history.setDoctype("RFH");
 	    history.setStatus(header.getStatus());
 	   if(!this.isAutoReleased())
       {
           history.setUserid(header.getOwner());
       }
       else
       {
           history.setUserid("AUTORELEASE");
       }

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
