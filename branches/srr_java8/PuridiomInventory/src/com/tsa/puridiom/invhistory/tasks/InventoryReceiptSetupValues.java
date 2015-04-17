/*
 * Created on Jul 20, 2004
 *
 * @author  * renzo
 * package com.tsa.puridiom.historylog.tasks.PoSetupValues.java
 *
 */
package com.tsa.puridiom.invhistory.tasks;

import java.math.BigDecimal;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.entity.HistoryLog;
import com.tsa.puridiom.entity.InvBinLocation;
import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.entity.ReceiptLine;
import com.tsa.puridiom.historylog.tasks.HistoryLogPoText;
import com.tsa.puridiom.historylog.tasks.HistoryLogSetupValues;
import com.tsa.puridiom.historylog.tasks.HistoryStatus;

public class InventoryReceiptSetupValues extends HistoryLogSetupValues
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
    public HistoryLog getHeaderHistoryLog(InvBinLocation newInvBinLocation, ReceiptLine newReceiptLine)
    {
        HistoryLog history = null;

        history = this.getHistoryRecord(newInvBinLocation, newReceiptLine);


        return history;
    }

    public HistoryLog setText(InvBinLocation newInvBinLocation, ReceiptLine receiptLine, HistoryLog history)
    {

    	String description =  "Item " + newInvBinLocation.getItemNumber() + "  Qty: " +  receiptLine.getQtyAccepted() + " stored at " +
    		newInvBinLocation.getItemLocation() + " - Bin: " + newInvBinLocation.getAisle() + "." +  newInvBinLocation.getLocrow() + "." +
    		newInvBinLocation.getTier() + "." + newInvBinLocation.getBin()  ;

    	history.setDescription(description) ;
        return history;
    }

    private HistoryLog getHistoryRecord(InvBinLocation newInvBinLocation,  ReceiptLine newReceiptLine)
    {
        HistoryLog history = this.receiptLineSetup(null, newInvBinLocation, newReceiptLine);
        history = this.setText(newInvBinLocation,newReceiptLine, history);


        return history;
    }


    public HistoryLog receiptLineSetup(HistoryLog history, InvBinLocation newInvBinLocation,  ReceiptLine newReceiptLine)
    {
        if(history == null){	history = this.setUpHistory(history);	}
        history.setIcHeaderHistory(newReceiptLine.getIcRecHeader());
        history.setIcLineHistory(newReceiptLine.getIcLineHistory());
        history.setIcHeader(newReceiptLine.getIcRecHeader());
        history.setIcLine(newReceiptLine.getIcRecLine());
        history.setDoctype("RCL");
        history.setStatus(newReceiptLine.getStatus());
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

	public String getBuyerRemarks() {
		return buyerRemarks;
	}

	public void setBuyerRemarks(String buyerRemarks)
	{
		this.buyerRemarks = "Procurement Remarks: " + buyerRemarks;
	}
}
