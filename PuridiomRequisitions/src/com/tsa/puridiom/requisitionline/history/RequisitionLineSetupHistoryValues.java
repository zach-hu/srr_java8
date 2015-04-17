/*
 * Created on Jul 22, 2004
 *
 * @author  * renzo
 * project: HiltonCommon
 * package com.tsa.puridiom.historylog.tasks;.RequisitionLineSetupHistoryValues.java
 *
 */
package com.tsa.puridiom.requisitionline.history;

import java.math.BigDecimal;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.entity.HistoryLog;
import com.tsa.puridiom.entity.PoLine;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsa.puridiom.historylog.tasks.HistoryLogSetupValues;
import com.tsa.puridiom.historylog.tasks.HistoryStatus;
import com.tsa.puridiom.requisitionheader.history.HistoryLogRequisitionText;
import com.tsagate.foundation.utility.Utility;

public class RequisitionLineSetupHistoryValues extends HistoryLogSetupValues
{
	PoLine poLine = null;
	private String writehistory = "";
    private BigDecimal originalQty = new BigDecimal(0);
    private BigDecimal originalPrice = new BigDecimal(0);

    public HistoryLog reqLineSetup(HistoryLog history, RequisitionLine line, RequisitionHeader header)
    {
        if(history == null){	history = this.setUpHistory(history);	}
        history.setIcHeaderHistory(header.getIcHeaderHistory());
        history.setIcLineHistory(line.getIcLineHistory());
        history.setIcHeader(line.getIcReqHeader());
        history.setIcLine(line.getIcReqLine());
        history.setDoctype("RQL");
        history.setStatus(line.getStatus());
        if(this.poLine != null)
        {
        	history.setUserid(line.getAssignedBuyer());
        }
        else
        {
        	history.setUserid(this.getUserId());
        }

         return history;

    }
    /**
     * @param oldHeader
     * @param newHeader
     * @return HistoryLog
     */
    public HistoryLog getLineHistoryLog(RequisitionLine newLine, RequisitionHeader header)
    {
        HistoryLog history = null;

        history = this.getHistoryRecord(newLine, header);

        return history;
    }

    public HistoryLog setText(RequisitionLine line, HistoryLog history, RequisitionHeader header)
    {
        HistoryLogRequisitionText text = new HistoryLogRequisitionText(header, line);
        String status = line.getStatus();
        String historyStatus = (String)this.getHistoryStatus();

        text.setOrganizationId(this.getOrganizationId());

        if(historyStatus.equalsIgnoreCase("MANUALASSIGNMENT"))
        {
        	history.setDescription(text.manualAssignment());
        }
        else
        {
        	if (this.writehistory.equalsIgnoreCase("Y"))
            {
            	if (this.originalQty.compareTo(new BigDecimal(0)) > -1  && this.originalPrice.compareTo(new BigDecimal(0)) > -1)
            	{
            		history.setDescription(text.changeLineInfoText(this.originalQty, this.originalPrice));
            	}
            	else if (this.originalQty.compareTo(new BigDecimal(0)) > -1)
            	{
            		history.setDescription(text.changeQuantityLineText(this.originalQty));
            	}
            	else if (this.originalPrice.compareTo(new BigDecimal(0)) > -1)
            	{
            		history.setDescription(text.changePriceLineText(this.originalPrice));
            	}
            }
        	else if(status.equals(DocumentStatus.REQ_INPROGRESS) && Utility.isEmpty(historyStatus))
	        {
	            history.setDescription(text.createLineText());
	        }
	        else if(status.equals(DocumentStatus.CANCELLED) && Utility.isEmpty(historyStatus))
	        {
	            history.setDescription(text.cancelLineText());
	        }
	        else if(status.equals(DocumentStatus.CLOSED) && Utility.isEmpty(historyStatus))
	        {
	            history.setDescription(text.closeLineText());
	        }
	        else if(status.equals(DocumentStatus.REQ_RECALLED) && Utility.isEmpty(historyStatus))
	        {
	            history.setDescription(text.recallLineText());
	        }
	        else if(this.testStatus(status, historyStatus, "forwarded"))
	        {
	            history.setDescription(text.forwardLineText(this.getForwardedTo()));
	        }
	        else if(status.equals(DocumentStatus.REQ_APPROVING) && historyStatus.equals(HistoryStatus.REQ_FORWARDING))
	        {
	            history.setDescription(text.approvingLineText(this.getForwardedTo()));
	        }
	        else if(status.equals(DocumentStatus.REQ_APPROVED) || historyStatus.equals(HistoryStatus.REQ_APPROVED))
	        {
	            history.setDescription(text.approveLineText());
	        }
	        else if(status.equals(DocumentStatus.REQ_REJECTED))
	        {
	            history.setDescription(text.rejectLineText());
	        }
	        else if(status.equals(DocumentStatus.PO_INPROGRESS))
	        {
	        	history.setDescription(text.onOrderText(this.poLine));
	        }
	        else if(status.equals(DocumentStatus.INV_PARTIAL))
	        {
	            if(line.getBackordered().compareTo(new BigDecimal(0)) > 0)
	            {
	                history.setDescription(text.backorderLineText());
	            }
	        }
        	else if(status.equals(DocumentStatus.REQ_PLANNING ) && Utility.isEmpty(historyStatus))
	        {
	            history.setDescription(msrText(text.createLineText()));
	        }
        	else if(status.equals(DocumentStatus.REQ_PLANNING_APPROVING))
	        {
	            history.setDescription(msrText(text.approvingLineText(this.getForwardedTo())));
	        }
        	else if(status.equals(DocumentStatus.REQ_PLANNING_APPROVED))
	        {
	            history.setDescription(msrText(text.approveLineText()));
	        }
        	else if(status.equals(DocumentStatus.REQ_PLANNING_REJECTED))
	        {
	            history.setDescription(msrText(text.rejectLineText()));
	        }
        	else if(status.equals(DocumentStatus.REQ_PLANNING_RECALLED)  && Utility.isEmpty(historyStatus))
	        {
	            history.setDescription(msrText(text.recallLineText()));
	        }
        	else if(status.equals(DocumentStatus.REQ_PLANNING_SOURCED) )
	        {
	            history.setDescription(msrText(text.sourcedText() + " " + line.getRequisitionNumber()));
	        }
        	else if(status.equals(DocumentStatus.REQ_ORDERED))
        	{
        		history.setDescription(msrText(text.orderLineText()));
        	}
        	else if(status.equals(DocumentStatus.REQ_PARTIALLYRECEIVED))
        	{
        		history.setDescription(msrText(text.partiallyReceiveLineText()));
        	}
        	else if(status.equals(DocumentStatus.REQ_RECEIVED))
        	{
        		history.setDescription(msrText(text.receiveLineText()));
        	}
        	else if(status.equals(DocumentStatus.REQ_PARTIALLYKITTED))
        	{
        		history.setDescription(msrText(text.partiallyKitLineText()));
        	}
        	else if(status.equals(DocumentStatus.REQ_KITTED))
        	{
        		history.setDescription(msrText(text.kitLineText()));
        	}
        	else if(status.equals(DocumentStatus.REQ_PARTIALLYISSUED))
        	{
        		history.setDescription(msrText(text.partiallyIssueLineText()));
        	}
        	else if(status.equals(DocumentStatus.REQ_ISSUED))
        	{
        		history.setDescription(msrText(text.issueLineText()));
        	}
        	else if(status.equals(DocumentStatus.REQ_CLOSED))
        	{
        		history.setDescription(msrText(text.closeLineText()));
        	}
	        else
	        {
	            history = null;
	        }
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
    private HistoryLog getHistoryRecord(RequisitionLine line, RequisitionHeader header)
    {
        HistoryLog history = this.reqLineSetup(null, line, header);
        history = this.setText(line, history, header);

        return history;
    }
	public PoLine getPoLine() {
		return poLine;
	}
	public void setPoLine(PoLine poLine) {
		this.poLine = poLine;
	}

	public String getWriteHistory() {
		return writehistory;
	}
	public void setWriteHistory(String writehistory) {
		this.writehistory = writehistory;
	}
	public BigDecimal getOriginalQty() {
		return originalQty;
	}
	public void setOriginalQty(BigDecimal originalQty) {
		this.originalQty = originalQty;
	}
	public BigDecimal getOriginalPrice() {
		return originalPrice;
	}
	public void setOriginalPrice(BigDecimal originalPrice) {
		this.originalPrice = originalPrice;
	}

}
