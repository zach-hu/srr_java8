package com.tsa.puridiom.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class InvoiceCheck {

	private String invoiceCheckNumber;
	private String vendorName;
	private String addressLine2;
	private String city;
	private String state;
	private String postalCode;
	private String country;
    private java.util.Date checkDate;
    private BigDecimal invoiceAmountTotal;
	private BigDecimal discountAmountTotal;
	private BigDecimal netAmountTotal;

    private List invoiceCheckLineList;

    public InvoiceCheck()
    {
    	this.invoiceCheckNumber = "autogenerado";
    	this.invoiceAmountTotal = new BigDecimal("0");
    	this.discountAmountTotal = new BigDecimal("0");
    	this.netAmountTotal = new BigDecimal("0");
    	this.checkDate = new Date();
    }

    public void calculateTotal()
    {
    	for (Iterator it = this.invoiceCheckLineList.iterator(); it.hasNext(); )
	    {
    		InvoiceCheckLine line = (InvoiceCheckLine) it.next();
    		this.invoiceAmountTotal = this.invoiceAmountTotal.add( line.getInvoiceAmount());
    		this.discountAmountTotal = this.discountAmountTotal.add( line.getDiscountAmount());
    		this.netAmountTotal = this.netAmountTotal.add(line.getNetAmount());
	    }
    }

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public List getInvoiceCheckLineList() {
		return invoiceCheckLineList;
	}

	public void setInvoiceCheckLineList(List invoiceCheckLineList) {
		this.invoiceCheckLineList = invoiceCheckLineList;
	}

	public String getInvoiceCheckNumber() {
		return invoiceCheckNumber;
	}

	public void setInvoiceCheckNumber(String invoiceCheckNumber) {
		this.invoiceCheckNumber = invoiceCheckNumber;
	}

	public java.util.Date getCheckDate() {
		return checkDate;
	}

	public void setCheckDate(java.util.Date checkDate) {
		this.checkDate = checkDate;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public BigDecimal getDiscountAmountTotal() {
		return discountAmountTotal;
	}

	public void setDiscountAmountTotal(BigDecimal discountAmountTotal) {
		this.discountAmountTotal = discountAmountTotal;
	}

	public BigDecimal getInvoiceAmountTotal() {
		return invoiceAmountTotal;
	}

	public void setInvoiceAmountTotal(BigDecimal invoiceAmountTotal) {
		this.invoiceAmountTotal = invoiceAmountTotal;
	}

	public BigDecimal getNetAmountTotal() {
		return netAmountTotal;
	}

	public void setNetAmountTotal(BigDecimal netAmountTotal) {
		this.netAmountTotal = netAmountTotal;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
}

