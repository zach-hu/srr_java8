/*
 * Created on Jul 22, 2004
 *
 * @author  * renzo
 * project: HiltonCommon
 * package com.tsa.puridiom.historylog.tasks;.RequisitionLineSetupHistoryValues.java
 *
 */
package com.tsa.puridiom.receiptline.history.tasks;

import java.lang.reflect.Method;
import java.math.BigDecimal;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.HistoryLog;
import com.tsa.puridiom.entity.PoLine;
import com.tsa.puridiom.entity.ReceiptHeader;
import com.tsa.puridiom.entity.ReceiptLine;
import com.tsa.puridiom.historylog.tasks.HistoryLogSetupValues;
import com.tsa.puridiom.historylog.tasks.HistoryStatus;
import com.tsa.puridiom.receiptheader.history.HistoryLogReceiptText;
import com.tsagate.foundation.rule.operator.NewLogicOperator;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.Utility;

public class ReceiptLineSetupHistoryValues extends HistoryLogSetupValues
{
	PoLine poLine = null;
	private String writehistory = "";
    private BigDecimal originalQty = new BigDecimal(0);
    private BigDecimal originalPrice = new BigDecimal(0);

    public HistoryLog recLineSetup(HistoryLog history, ReceiptLine line, ReceiptHeader header)
    {
        if(history == null){
        	history = this.setUpHistory(history);
        	}
        history.setIcHeaderHistory(header.getIcHeaderHistory());
        history.setIcLineHistory(line.getIcLineHistory());
        history.setIcHeader(line.getIcRecHeader());
        history.setIcLine(line.getIcRecLine());
        history.setDoctype("RCL");
        history.setStatus(line.getStatus());
        if(this.poLine != null)
        {
        	history.setUserid(line.getInspectorAssigned());
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
    public HistoryLog getLineHistoryLog(ReceiptLine newLine, ReceiptHeader header)
    {
        HistoryLog history = null;

        history = this.getHistoryRecord(newLine, header);

        return history;
    }

    public HistoryLog setText(ReceiptLine line, HistoryLog history, ReceiptHeader header)
    {
        HistoryLogReceiptText text = new HistoryLogReceiptText(header, line);
        String status = line.getStatus();
        String historyStatus = (String)this.getHistoryStatus();

        text.setOrganizationId(this.getOrganizationId());

        	if(status.equals(DocumentStatus.RCV_INPROGRESS) && Utility.isEmpty(historyStatus))
	        {
	            history.setDescription(text.createLineText());
	        }
        	else if(historyStatus.equals("MANUALASSIGNMENT"))
	        {
        		String description="ReceiptNumber: " + line.getReceiptNumber() +" assigned to: "+ line.getInspectorAssigned()+".";
	            history.setDescription(description);
	        }
        	else if((status.equals(DocumentStatus.RCV_STEP_1) || status.equals(DocumentStatus.RCV_STEP_2) || status.equals(DocumentStatus.RCV_STEP_3)) && Utility.isEmpty(historyStatus))
        	{
        		String rejected = "";
        		String accepted = "";
        		String statusString = "";
        		String extraText = "";
        		if(status.equals(DocumentStatus.RCV_STEP_1)){
        			rejected = line.getQtyStep0Rejected().toString();
        			accepted = line.getQtyStep0Accepted().toString();
        			extraText = "\n Received into QA :"+accepted+".";
        		} else if(status.equals(DocumentStatus.RCV_STEP_2) && line.getInspectionRequired().equalsIgnoreCase("Y")){
        			rejected = line.getQtyStep1Rejected().toString();
        			accepted = line.getQtyStep1Accepted().toString();
        		} else if(status.equals(DocumentStatus.RCV_STEP_2)){
        			rejected = line.getQtyStep0Rejected().toString();
        			accepted = line.getQtyStep0Accepted().toString();
        		} else if(status.equals(DocumentStatus.RCV_STEP_3)){
        			rejected = "0" ;    // line.getQtyStep2Rejected().toString();  // No need for rejected here
        			accepted = line.getQtyAccepted().toString();
        		}
        		statusString = DocumentStatus.toString(status).substring(DocumentStatus.toString(status).lastIndexOf(" ")+1);
        		String description=	"Line " + line.getReceiptLine() +
        											", Item #"+ line.getPoLine().getDescription() +
        											", Quantity: "+line.getPoLine().getQuantity() +
        											"; was rejected the Qty:"+ rejected +
        											", passed to "+ statusString+" the Qty: "+accepted+
        											" ; now Line in: "+DocumentStatus.toString(status)+"."+extraText;
        		history.setDescription(description);
        	}
        	else if((status.equals(DocumentStatus.RCV_RECEIVED) || status.equals(DocumentStatus.RCV_PARTIALLYRECEIVED)) && line.getPoLine() != null && line.getPoLine().getItemSource().equalsIgnoreCase("INV") && Utility.isEmpty(historyStatus))
        	{
        			String description = "Inventory Item Qty: "+ line.getQtyAccepted() +" received";
        			history.setDescription(description);
        	}
	        else if(status.equals(DocumentStatus.CANCELLED) && Utility.isEmpty(historyStatus))
	        {
	            history.setDescription(text.cancelLineText());
	        }
	        else if(this.testStatus(status, historyStatus, "forwarded"))
	        {
	            history.setDescription(text.forwardText(this.getForwardedTo()));
	        }
	        else if(!historyStatus.equals("MSRLINE")){
	            history = null;
	        }
        return history;
    }

    /**
     * @param status
     */
    private HistoryLog getHistoryRecord(ReceiptLine line, ReceiptHeader header)
    {
        HistoryLog history = this.recLineSetup(null, line, header);
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
