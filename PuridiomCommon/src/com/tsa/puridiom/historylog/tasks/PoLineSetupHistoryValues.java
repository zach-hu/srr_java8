/*
 * Created on Jul 22, 2004
 *
 * @author  * renzo
 * package com.tsa.puridiom.historylog.tasks.PoLineSetupHistoryValues.java
 *
 */
package com.tsa.puridiom.historylog.tasks;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.entity.HistoryLog;
import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.entity.PoLine;
import com.tsagate.foundation.utility.Utility;

import java.math.BigDecimal;

public class PoLineSetupHistoryValues extends HistoryLogSetupValues
{
    private boolean autoReleased = false;
    private String nextUser = "";
    private String reason = "";
    private String writehistory = "";
    private BigDecimal originalQty = new BigDecimal(0);
    private BigDecimal originalPrice = new BigDecimal(0);
    private String requisitionNumber = "";

    public HistoryLog poLineSetup(HistoryLog history, PoLine line, PoHeader header)
    {
        if(history == null){	history = this.setUpHistory(history);	}
        history.setIcHeaderHistory(header.getIcHeaderHistory());
        history.setIcLineHistory(line.getIcLineHistory());
        history.setIcHeader(header.getIcPoHeader());
        history.setIcLine(line.getIcPoLine());
         history.setDoctype("POL");
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
    public HistoryLog getLineHistoryLog(PoLine newLine, PoHeader header)
    {
        HistoryLog history = null;
        history = this.getHistoryRecord(newLine, header);

        return history;
    }

    public HistoryLog setText(PoLine line, HistoryLog history, PoHeader header)
    {
        HistoryLogPoText text = new HistoryLogPoText(header, line);
        text.setReason(this.getReason());
        String status = line.getStatus();

        text.setOrganizationId(this.getOrganizationId());

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
        else if( !Utility.isEmpty(this.getRequisitionNumber()) )
        {
        	history.setDescription( text.linkedLineToReq(this.getRequisitionNumber()) );
        }
        else if(status.equals(DocumentStatus.PO_AWARDED))
        {
            history.setDescription(text.forwardLineText(this.isAutoReleased()));
        }
        else if(status.equals(DocumentStatus.PO_INPROGRESS))
        {
            history.setDescription(text.createLineText());
        }
        else if(status.equals(DocumentStatus.PO_APPROVING))
        {
            history.setDescription(text.approvingLineText(this.getNextUser()));
        }
        else if(status.equals(DocumentStatus.CANCELLED))
        {
            history.setDescription(text.cancelLineText());
        }
        else if(status.equals(DocumentStatus.CLOSED))
        {
            history.setDescription(text.closeLineText());
        }
        else if(status.equals(DocumentStatus.DELETE_INPROGRESS))
        {
            history.setDescription(text.deleteLineText());
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
    private HistoryLog getHistoryRecord(PoLine line, PoHeader header)
    {
        HistoryLog history = this.poLineSetup(null, line, header);
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
	public void setRequisitionNumber(String requisitionNumber) {
		this.requisitionNumber = requisitionNumber;
	}
	public String getRequisitionNumber() {
		return requisitionNumber;
	}
}