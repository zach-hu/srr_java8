/*
 * Created on Jul 20, 2004
 *
 * @author  * renzo
 * package com.tsa.puridiom.historylog.tasks.PoSetupValues.java
 *
 */
package com.tsa.puridiom.historylog.tasks;

import java.math.BigDecimal;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.HistoryLog;
import com.tsa.puridiom.entity.PoHeader;

public class PoSetupValues extends HistoryLogSetupValues
{
    private boolean autoReleased = false;
    private String nextUser = "";
    private String reason = "";
    private String buyerRemarks;
    /**
     * @param oldHeader
     * @param newHeader
     * @return HistoryLog
     */
    public HistoryLog getHeaderHistoryLog(PoHeader newHeader)
    {
        HistoryLog history = null;

        history = this.getHistoryRecord(newHeader);


        return history;
    }

    public HistoryLog setText(PoHeader header, HistoryLog history)
    {
        HistoryLogPoText text = new HistoryLogPoText(header);
        text.setReason(this.getReason());
        String status = header.getStatus();

        text.setOrganizationId(this.getOrganizationId());
        String historyStatus = (String)this.getHistoryStatus();

        if (historyStatus.equals(DocumentStatus.BUYER_REMARKS))
        {
        	history.setDescription(this.getBuyerRemarks());
        	history.setIcLine(header.getIcPoHeader());
        }
        else if(this.isAutoReleased())
        {
        	history.setDescription(text.approveText(true));
        }
        else if(status.equals(DocumentStatus.RCV_PARTIALLYRECEIVED))
        {
            history.setDescription(text.partiallyReceivedText());
        }
        else if(status.equals(DocumentStatus.RCV_RECEIVED))
        {
            if(!HiltonUtility.isEmpty(historyStatus) && historyStatus.equalsIgnoreCase("QR")){
            	history.setDescription(text.quickReceivedText(this.getNextUser()));
            } else {
            	history.setDescription(text.receivedText());
            }
        }
        else if(status.equals(DocumentStatus.PO_AWARDED))
        {
            history.setDescription(text.awardedText());
        }
        else if(status.equals(DocumentStatus.PO_INPROGRESS))
        {
            history.setDescription(text.createText());
        }
        else if(status.equals(DocumentStatus.PO_APPROVING))
        {
            history.setDescription(text.approvingText(this.getNextUser()));
        }
        else if(status.equals(DocumentStatus.CANCELLED))
        {
            history.setDescription(text.cancelText());
        }
        else if(status.equals(DocumentStatus.CLOSED))
        {
            history.setDescription(text.closeText());
        }
        else if(status.equals(DocumentStatus.PO_REJECTED))
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
    private HistoryLog getHistoryRecord(PoHeader header)
    {
        HistoryLog history = this.poHeaderSetup(null, header);
        history = this.setText(header, history);

        return history;
    }


    public HistoryLog poHeaderSetup(HistoryLog history, PoHeader header)
    {
        if(history == null){	history = this.setUpHistory(history);	}
        history.setIcHeaderHistory(header.getIcHeaderHistory());
        history.setIcLineHistory(new BigDecimal("0"));
        history.setIcHeader(header.getIcPoHeader());
        history.setIcLine(new BigDecimal("0"));
         history.setDoctype("POH");
         if (this.getHistoryStatus().equals(DocumentStatus.BUYER_REMARKS))
         {
        	 history.setStatus((String) this.getHistoryStatus());
         }
         else
         {
        	 history.setStatus(header.getStatus());
         }

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

	public String getBuyerRemarks() {
		return buyerRemarks;
	}

	public void setBuyerRemarks(String buyerRemarks)
	{
		this.buyerRemarks = "Procurement Remarks: " + buyerRemarks;
	}
}
