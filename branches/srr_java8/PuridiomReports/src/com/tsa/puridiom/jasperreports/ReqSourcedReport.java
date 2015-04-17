package com.tsa.puridiom.jasperreports;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.entity.PoHeader;
import com.tsagate.foundation.utility.Utility;

public class ReqSourcedReport
{
	private RequisitionHeader requisitionHeader;
	private PoHeader poHeader;
	private String requisitionNumber;
	private String reqStatus;
	private String reqItemLocation;
	private String poNumber;
	private String poStatus;
	private List receiptHeaderList;
	private List invoiceHeaderList;
	private List invBinLocationList;
	
	public ReqSourcedReport(RequisitionHeader requisitionHeader)
	{
		this.requisitionHeader = requisitionHeader;
	}
	
	
	public RequisitionHeader getRequisitionHeader() {
		return requisitionHeader;
	}


	public void setRequisitionHeader(RequisitionHeader requisitionHeader) {
		this.requisitionHeader = requisitionHeader;
	}


	public PoHeader getPoHeader() {
		return poHeader;
	}


	public void setPoHeader(PoHeader poHeader) {
		this.poHeader = poHeader;
	}


	public String getRequisitionNumber() {
		if(this.getRequisitionHeader() == null) {		return "";		}
		return this.getRequisitionHeader().getRequisitionNumber();
	}


	public void setRequisitionNumber(String requisitionNumber) {
		this.requisitionNumber = requisitionNumber;
	}
	


	public String getReqItemLocation() {
		if(this.getRequisitionHeader() == null) {		return "";		}
		return this.getRequisitionHeader().getItemLocation();
	}


	public void setReqItemLocation(String reqItemLocation) {
		this.reqItemLocation = reqItemLocation;
	}


	public String getReqStatus() {
		if(this.getRequisitionHeader() == null) {		return "";		}
		return this.getRequisitionHeader().getStatus();
	}


	public void setReqStatus(String reqStatus) {
		this.reqStatus = reqStatus;
	}


	public String getPoNumber() {
		if(this.getPoHeader() == null) {		return "";		}
		return this.getPoHeader().getPoNumber();
	}


	public void setPoNumber(String poNumber) {
		this.poNumber = poNumber;
	}


	public String getPoStatus() {
		if(this.getPoHeader() == null) {		return "";		}
		return this.getPoHeader().getStatus();
	}


	public void setPoStatus(String poStatus) {
		this.poStatus = poStatus;
	}


	public List getReceiptHeaderList() {
		return receiptHeaderList;
	}


	public void setReceiptHeaderList(List receiptHeaderList) {
		this.receiptHeaderList = receiptHeaderList;
	}


	public List getInvoiceHeaderList() {
		return invoiceHeaderList;
	}


	public void setInvoiceHeaderList(List invoiceHeaderList) {
		this.invoiceHeaderList = invoiceHeaderList;
	}


	public List getInvBinLocationList() {
		return invBinLocationList;
	}


	public void setInvBinLocationList(List invBinLocationList) {
		this.invBinLocationList = invBinLocationList;
	}
	
	

}
