/*
 * Created on Jul 20, 2004
 *
 * @author  * renzo
 * project: HiltonCommon
 * package com.tsa.puridiom.historylog.tasks.RequisitionSetupValues.java
 *
 */
package com.tsa.puridiom.requisitionheader.history;

import java.math.BigDecimal;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.entity.HistoryLog;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.historylog.tasks.HistoryLogSetupValues;
import com.tsa.puridiom.historylog.tasks.HistoryStatus;
import com.tsagate.foundation.utility.Utility;

public class RequisitionHistorySetupValues extends HistoryLogSetupValues
{

    /**
     * @param oldHeader
     * @param newHeader
     * @return HistoryLog
     */
    public HistoryLog getHeaderHistoryLog(RequisitionHeader newHeader)
    {
        HistoryLog history = null;
        history = this.getHistoryRecord(newHeader);

        return history;
    }

    public HistoryLog setText(RequisitionHeader header, HistoryLog history)
    {
        HistoryLogRequisitionText text = new HistoryLogRequisitionText(header);
        text.setOrganizationId(this.getOrganizationId());

        String historyStatus = (String)this.getHistoryStatus();
        String status = header.getStatus();

        if (historyStatus.equals(DocumentStatus.BUYER_REMARKS))
        {
        	history.setDescription(text.buyerRemarksText());
        	history.setIcLine(new BigDecimal((String)this.getExtraIc()));
        } else if (historyStatus.equals(HistoryStatus.BUYER_ASSIGNMENT))
        {
        	history.setDescription(text.buyerAssignment());
        }
        else if(status.equals(DocumentStatus.TEMPLATE) && Utility.isEmpty(historyStatus))
        {
            history.setDescription(text.createTextReqFromTemplate());
        }
        else if(status.equals(DocumentStatus.REQ_INPROGRESS) && Utility.isEmpty(historyStatus))
        {
            history.setDescription(text.createText());
        }
        else if(status.equals(DocumentStatus.CANCELLED) && Utility.isEmpty(historyStatus))
        {
            history.setDescription(text.cancelText());
        }
        else if(status.equals(DocumentStatus.CLOSED) && Utility.isEmpty(historyStatus))
        {
            history.setDescription(text.closeText());
        }
        else if(status.equals(DocumentStatus.REQ_RECALLED) && Utility.isEmpty(historyStatus))
        {
            history.setDescription(text.recallText());
        }
        else if(status.equals(DocumentStatus.REQ_REJECTED))
        {
            history.setDescription(text.rejectText(this.getRejectedBy()));
        }
        else if(status.equals(DocumentStatus.REQ_FORWARDED))
        {
            history.setDescription(text.forwardedText(this.getForwardedTo()));
        }
        else if(this.testStatus(status, historyStatus, "forwarded"))
        {
            history.setDescription(text.forwardText(this.getForwardedTo()));
        }
        else if(status.equals(DocumentStatus.REQ_APPROVING) && (historyStatus.equalsIgnoreCase("") || historyStatus.equals(HistoryStatus.REQ_FORWARDING)))
        {
            history.setDescription(text.approvingText(this.getForwardedTo()));
        }
        else if(status.equals(DocumentStatus.REQ_APPROVED) || historyStatus.equals(HistoryStatus.REQ_APPROVED))
        {
            history.setDescription(text.approveText());
        }
        /*else if(status.equals(DocumentStatus.PO_INPROGRESS))
        {
            history.setDescription(text.onOrderText());
        }*/
        else if(status.equals(DocumentStatus.RCV_PARTIALLYRECEIVED))
        {
            history.setDescription(text.partiallyRcvdText());
        }
        else if(status.equals(DocumentStatus.RCV_RECEIVED))
        {
            history.setDescription(text.fullyRcvdText());
        }
        else if(status.equals(DocumentStatus.PO_AWARDED))
        {
            history.setDescription(text.awardedText());
        }
    	else if(status.equals(DocumentStatus.REQ_PLANNING ) && Utility.isEmpty(historyStatus))
        {
            history.setDescription(msrText(text.createText()));
        }
    	else if(status.equals(DocumentStatus.REQ_PLANNING_APPROVING))
        {
            history.setDescription(msrText(text.approvingText(this.getForwardedTo())));
        }
    	else if(status.equals(DocumentStatus.REQ_PLANNING_APPROVED))
        {
            history.setDescription(msrText(text.approveText()));
        }
    	else if(status.equals(DocumentStatus.REQ_PLANNING_REJECTED))
        {
            history.setDescription(msrText(text.rejectText(this.getRejectedBy())));
        }
    	else if(status.equals(DocumentStatus.REQ_PLANNING_RECALLED)  && Utility.isEmpty(historyStatus))
        {
            history.setDescription(msrText(text.recallText()));
        }
    	else if(status.equals(DocumentStatus.REQ_PLANNING_SOURCING)  && historyStatus.compareTo(DocumentStatus.REQ_PLANNING_SOURCING) != 0)
        {
            history.setDescription("MSR Request sourcing started");
        }
    	else if(status.equals(DocumentStatus.REQ_PLANNING_SOURCED) )
        {
            history.setDescription(msrText(text.sourcedText()));
        }
    	else if(status.equals(DocumentStatus.REQ_ORDERED) )
    	{
    		history.setDescription(msrText(text.orderedText()));
    	}
    	else if(status.equals(DocumentStatus.REQ_PARTIALLYRECEIVED) )
    	{
    		history.setDescription(msrText(text.partiallyRcvdText()));
    	}
    	else if(status.equals(DocumentStatus.REQ_RECEIVED) )
    	{
    		history.setDescription(msrText(text.fullyRcvdText()));
    	}
    	else if(status.equals(DocumentStatus.REQ_PARTIALLYKITTED) )
    	{
    		history.setDescription(msrText(text.partiallyKittedText()));
    	}
    	else if(status.equals(DocumentStatus.REQ_KITTED) )
    	{
    		history.setDescription(msrText(text.kittedText()));
    	}
    	else if(status.equals(DocumentStatus.REQ_PARTIALLYISSUED) )
    	{
    		history.setDescription(msrText(text.partiallyIssuedText()));
    	}
    	else if(status.equals(DocumentStatus.REQ_ISSUED) )
    	{
    		history.setDescription(msrText(text.issuedText()));
    	}
    	else if(status.equals(DocumentStatus.REQ_RESERVED) )
    	{
    		history.setDescription(msrText(text.reservedText()));
    	}
    	else if(status.equals(DocumentStatus.REQ_CLOSED) )
    	{
    		history.setDescription(msrText(text.closeText()));
    	}
        else
        {
            history = null;
        }
        return history;
    }

    private String msrText(String text) {
    	String newText = "" ;
    	if (text != null) {
    		newText = text.replaceAll("Requisition", "MSR Request") ;
    	}
    	return newText ;
    }

    /**
     * @param status
     */
    public HistoryLog getHistoryRecord(RequisitionHeader header)
    {
        HistoryLog history = this.reqHeaderSetup(null, header);
        history = this.setText(header, history);

        return history;
    }


    public HistoryLog reqHeaderSetup(HistoryLog history, RequisitionHeader header)
    {
        if(history == null){	history = this.setUpHistory(history);	}
        history.setIcHeaderHistory(header.getIcHeaderHistory());
        history.setIcLineHistory(new BigDecimal("0"));
        history.setIcHeader(header.getIcReqHeader());
        history.setIcLine(new BigDecimal("0"));
         history.setDoctype("REQ");
         if (this.getHistoryStatus().equals(DocumentStatus.BUYER_REMARKS))
         {
        	 history.setStatus((String) this.getHistoryStatus());
         }
         else
         {
        	 history.setStatus(header.getStatus());
         }
         history.setUserid(this.getUserId());

         return history;

    }
}
