package com.tsa.puridiom.entity;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;

import com.tsagate.foundation.utility.Dates;

public class InvoiceCheckLine {

	private String location;
	private String invoiceNumber;
	private String description;
	private String termsCode;
	private BigDecimal invoiceAmount;
	private BigDecimal discountAmount;
	private BigDecimal adjustment;
	private BigDecimal netAmount;
	private java.util.Date invoiceDate;
	private java.util.Date paymentDueDate;

	private BigDecimal termsDiscDays;
	private BigDecimal termsDiscperc;

	private BigDecimal discountBasis;

	public InvoiceCheckLine(InvoiceHeader ivh)
	{
		this.location = ivh.getUdf1Code();
		this.invoiceNumber = ivh.getInvoiceNumber();
		this.description = ivh.getInvoiceDesc();
		this.termsCode = ivh.getTermsCode();
		this.invoiceAmount = ivh.getInvoiceTotal();
		this.adjustment = ivh.getInvoiceAdjustment();
		this.paymentDueDate = ivh.getPaymentDueDate();
		this.netAmount = ivh.getInvoiceTotal();
		this.termsDiscDays = ivh.getTermsDiscdays();
		this.termsDiscperc = ivh.getTermsDiscperc();
		this.invoiceDate = ivh.getInvoiceDate();

		if (ivh.getUdf9Code().equals("Y")) 				//	Apply Invoice Total Terms Discount
		{
			this.discountBasis = ivh.getInvoiceTotal();
		}
		else if (ivh.getUdf10Code().equals("Y"))		//	Apply Invoice Base Terms Discount
		{
			this.discountBasis = ivh.getInvoiceSubtotal();
		}
		else
		{
			this.discountBasis = ivh.getInvoiceTotal();
		}
	}

	public void calculateDiscount(char discountAnyway)
	{
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String paymentDueDateString = formatter.format(paymentDueDate);

		Integer	daysAfter = new Integer(0);
		try
		{
			daysAfter = new Integer(Dates.getDaysAfter("", paymentDueDateString, 0));
		}
		catch(Exception e)
		{

		}

		if ( discountAnyway=='Y'  || termsDiscDays.intValue() < daysAfter.intValue())
		{
			this.discountAmount = this.discountBasis.multiply(this.termsDiscperc).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP);
		}
		else
		{
			this.discountAmount = new BigDecimal(0);
		}
		this.discountAmount = this.discountAmount.add(this.adjustment);

		this.netAmount = this.invoiceAmount.subtract(this.discountAmount);

	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public BigDecimal getDiscountAmount() {
		return discountAmount;
	}
	public void setDiscountAmount(BigDecimal discountAmount) {
		this.discountAmount = discountAmount;
	}
	public BigDecimal getInvoiceAmount() {
		return invoiceAmount;
	}
	public void setInvoiceAmount(BigDecimal invoiceAmount) {
		this.invoiceAmount = invoiceAmount;
	}
	public String getInvoiceNumber() {
		return invoiceNumber;
	}
	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public BigDecimal getNetAmount() {
		return netAmount;
	}
	public void setNetAmount(BigDecimal netAmount) {
		this.netAmount = netAmount;
	}

	public String getTermsCode() {
		return termsCode;
	}

	public void setTermsCode(String termsCode) {
		this.termsCode = termsCode;
	}

	public java.util.Date getPaymentDueDate() {
		return paymentDueDate;
	}

	public void setPaymentDueDate(java.util.Date paymentDueDate) {
		this.paymentDueDate = paymentDueDate;
	}

	public BigDecimal getTermsDiscDays() {
		return termsDiscDays;
	}

	public void setTermsDiscDays(BigDecimal termsDiscDays) {
		this.termsDiscDays = termsDiscDays;
	}

	public BigDecimal getTermsDiscperc() {
		return termsDiscperc;
	}

	public void setTermsDiscperc(BigDecimal termsDiscperc) {
		this.termsDiscperc = termsDiscperc;
	}

	public java.util.Date getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(java.util.Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public BigDecimal getAdjustment() {
		return adjustment;
	}

	public void setAdjustment(BigDecimal adjustment) {
		this.adjustment = adjustment;
	}
}

