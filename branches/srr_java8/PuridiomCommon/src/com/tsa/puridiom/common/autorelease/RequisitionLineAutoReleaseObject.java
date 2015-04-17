package com.tsa.puridiom.common.autorelease;

import java.math.BigDecimal;
import java.util.List;

import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.entity.PoLine;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsa.puridiom.entity.ShipTo;

public class RequisitionLineAutoReleaseObject
{
	private RequisitionHeader requisitionHeader;
	private RequisitionLine requistionLine;
	private PoHeader poHeader;
	private PoLine poLine;
	private ShipTo shipTo;
	private List accountsList;
	private BigDecimal quantity = new BigDecimal(0);

	/**
	 * This constructor is used in the autoRelease Process
	 * <p>It Receives an Object[3]</p>
	 * <p>data[0] RequisitionHeader</p>
	 * <p>data[1] RequisitionLine</p>
	 * <p>data[2] ShipTo</p>
	 * <p>This object has only one shipto.</p>
	 * @param data
	 */
	public RequisitionLineAutoReleaseObject(Object data[])
	{
		this.setRequisitionHeader((RequisitionHeader)data[0]);
		this.setRequistionLine((RequisitionLine)data[1]);
		this.setQuantity(this.getRequistionLine().getQuantity());
		this.setShipTo((ShipTo)data[2]);
	}

	public BigDecimal getIcReqHeader()
	{
		return this.requisitionHeader.getIcReqHeader();
	}

	public BigDecimal getIcReqLine()
	{
		return this.requistionLine.getIcReqLine();
	}

	public List getAccountsList() {
		return accountsList;
	}
	public void setAccountsList(List accountsList) {
		this.accountsList = accountsList;
	}
	public BigDecimal getQuantity() {
		return quantity;
	}
	private void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}
	public RequisitionHeader getRequisitionHeader() {
		return requisitionHeader;
	}
	public void setRequisitionHeader(RequisitionHeader requisitionHeader) {
		this.requisitionHeader = requisitionHeader;
	}
	public RequisitionLine getRequistionLine() {
		return requistionLine;
	}
	public void setRequistionLine(RequisitionLine requistionLine) {
		this.requistionLine = requistionLine;
	}

	public ShipTo getShipTo() {
		return shipTo;
	}

	public void setShipTo(ShipTo shipTo)
	{
		if(shipTo != null)
		{
			this.shipTo = shipTo;
			this.setQuantity(shipTo.getQuantity());
		}
	}

	public PoLine getPoLine() {
		return poLine;
	}

	public void setPoLine(PoLine poLine) {
		this.poLine = poLine;
	}

	public PoHeader getPoHeader() {
		return poHeader;
	}

	public void setPoHeader(PoHeader poHeader) {
		this.poHeader = poHeader;
	}
}
