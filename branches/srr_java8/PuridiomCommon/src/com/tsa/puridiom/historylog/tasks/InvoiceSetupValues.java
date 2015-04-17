/*
 * Created on August 27, 2004
 * package com.tsa.puridiom.historylog.tasks.InvoiceSetupValues.java
 *
 */
package com.tsa.puridiom.historylog.tasks;

import java.math.BigDecimal;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.HistoryLog;
import com.tsa.puridiom.entity.InvoiceHeader;

public class InvoiceSetupValues extends HistoryLogSetupValues
{
    private String nextUser = "";
    private String reason = "";
	/**
     * @param oldHeader
     * @param newHeader
     * @return HistoryLog
     */
    public HistoryLog getHeaderHistoryLog(InvoiceHeader newHeader)
    {
        HistoryLog history = null;

        history = this.getHistoryRecord(newHeader);


        return history;
    }

    public HistoryLog setText(InvoiceHeader header, HistoryLog history)
    {
        HistoryLogInvoiceText text = new HistoryLogInvoiceText(header);
        String status = header.getStatus();
        
        String historyStatus = (String)this.getHistoryStatus();

        text.setOrganizationId(this.getOrganizationId());

        if(historyStatus.equalsIgnoreCase("RESEND")){
        	history.setDescription(text.ResendText(this.getForwardedTo()));
        } 
        else if (status.equals(DocumentStatus.IVC_INPROGRESS))
        {
            history.setDescription(text.createText());
        }
        else if (status.equals(DocumentStatus.IVC_RECALLED))
        {
        	history.setDescription(text.recallText());
        }
        else if (status.equals(DocumentStatus.IVC_APPROVING))
        {
            history.setDescription(text.approvingText(this.getForwardedTo()));
        }
        else if(status.equals(DocumentStatus.IVC_APPROVED))
        {
            history.setDescription(text.approveText());
        }
        else if(status.equals(DocumentStatus.IVC_REJECTED))
        {
        	history.setDescription(text.rejectText(this.getRejectedBy()));
        }
        else if(status.equals(DocumentStatus.CANCELLED))
        {
            history.setDescription(text.cancelText());
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
    private HistoryLog getHistoryRecord(InvoiceHeader header)
    {
        HistoryLog history = this.invoiceHeaderSetup(null, header);
        history = this.setText(header, history);

        return history;
    }


    public HistoryLog invoiceHeaderSetup(HistoryLog history, InvoiceHeader header)
    {
        if(history == null){	history = this.setUpHistory(history);	}
        history.setIcHeaderHistory(header.getIcHeaderHistory());
        history.setIcLineHistory(new BigDecimal("0"));
        history.setIcHeader(header.getIcIvcHeader());
        history.setIcLine(new BigDecimal("0"));
 	    history.setDoctype("IVH");
 	    history.setStatus(header.getStatus());
 	    history.setUserid(this.getUserId());

 	   return history;
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
