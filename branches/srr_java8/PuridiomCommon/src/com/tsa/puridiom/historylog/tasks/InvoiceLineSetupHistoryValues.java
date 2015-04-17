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
import com.tsa.puridiom.entity.InvoiceHeader;
import com.tsa.puridiom.entity.InvoiceLine;

public class InvoiceLineSetupHistoryValues extends HistoryLogSetupValues
{
	private boolean autoReleased = false;
    private String nextUser = "";
    private String reason = "";

    public HistoryLog invoiceLineSetup(HistoryLog history, InvoiceLine line, InvoiceHeader header)
    {
        if(history == null){	history = this.setUpHistory(history);	}
        history.setIcHeaderHistory(header.getIcHeaderHistory());
        history.setIcLineHistory(line.getIcLineHistory());
        history.setIcHeader(header.getIcIvcHeader());
        history.setIcLine(line.getIcIvcLine());
 	    history.setDoctype("IVL");
 	    history.setStatus(line.getStatus());
 	    history.setUserid(this.getUserId());
 	    return history;

    }
    /**
     * @param oldHeader
     * @param newHeader
     * @return HistoryLog
     */
    public HistoryLog getLineHistoryLog(InvoiceLine newLine, InvoiceHeader header)
    {
        HistoryLog history = null;
        history = this.getHistoryRecord(newLine, header);

        return history;
    }

    public HistoryLog setText(InvoiceLine line, HistoryLog history, InvoiceHeader header)
    {
        HistoryLogInvoiceText text = new HistoryLogInvoiceText(header, line);
        //text.setReason(this.getReason());
        String status = line.getStatus();
        String historyStatus = (String)this.getHistoryStatus();

        text.setOrganizationId(this.getOrganizationId());

        if(historyStatus.equalsIgnoreCase("RESEND")){
        	history.setDescription(text.resendLineText(this.getForwardedTo()));
        } 
        else if (status.equals(DocumentStatus.IVC_INPROGRESS))
        {
            history.setDescription(text.createLineText());
        }
        else if (status.equals(DocumentStatus.IVC_RECALLED))
        {
        	history.setDescription(text.recallLineText());
        }
        else if (status.equals(DocumentStatus.IVC_APPROVING))
        {
        	history.setDescription(text.forwardLineText(this.getForwardedTo()));
        }
        else if(status.equals(DocumentStatus.IVC_APPROVED))
        {
            //history.setDescription(text.approveText());
        }
        else if(status.equals(DocumentStatus.IVC_REJECTED))
        {
        	history.setDescription(text.rejectLineText());
        }
        else if(status.equals(DocumentStatus.CANCELLED))
        {
            history.setDescription(text.cancelLineText());
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
    private HistoryLog getHistoryRecord(InvoiceLine line, InvoiceHeader header)
    {
        HistoryLog history = this.invoiceLineSetup(null, line, header);
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