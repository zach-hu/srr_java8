package com.tsa.puridiom.jasperreports;

import java.math.BigDecimal;

public class Totals
{
	private BigDecimal subtotal = new BigDecimal(0);
	private BigDecimal discount = new BigDecimal(0);
	private BigDecimal taxAmount = new BigDecimal(0);
	private BigDecimal useTaxAmount = new BigDecimal(0);
	private BigDecimal shippingCharges = new BigDecimal(0);
	private BigDecimal shippingTaxAmount = new BigDecimal(0);
	private BigDecimal otherCharges = new BigDecimal(0);
	private BigDecimal otherTaxAmount = new BigDecimal(0);
	private BigDecimal total = new BigDecimal(0);

	public Totals()
	{
		super();
	}
	public Totals(BigDecimal _subtotal, BigDecimal _discount, BigDecimal _taxAmount, BigDecimal _shippingCharges, BigDecimal _otherCharges, BigDecimal _total)
	{
		this.setSubtotal(_subtotal);
		this.setDiscount(_discount);
		this.setTaxAmount(_taxAmount);
		this.setUseTaxAmount(useTaxAmount);
		this.setShippingCharges(_shippingCharges);
		this.setOtherCharges(_otherCharges);
		this.setTotal(_total);
	}
	public BigDecimal getDiscountAmount() {
		return discount;
	}
	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}
	public BigDecimal getOtherCharges() {
		return otherCharges;
	}
	public void setOtherCharges(BigDecimal otherCharges) {
		this.otherCharges = otherCharges;
	}
	public BigDecimal getOtherTaxAmount() {
		return otherTaxAmount;
	}
	public void setOtherTaxAmount(BigDecimal otherTaxAmount) {
		this.otherTaxAmount = otherTaxAmount;
	}
	public BigDecimal getShippingCharges() {
		return shippingCharges;
	}
	public void setShippingCharges(BigDecimal shippingCharges) {
		this.shippingCharges = shippingCharges;
	}
	public BigDecimal getShippingTaxAmount() {
		return shippingTaxAmount;
	}
	public void setShippingTaxAmount(BigDecimal shippingTaxAmount) {
		this.shippingTaxAmount = shippingTaxAmount;
	}
	public BigDecimal getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(BigDecimal subtotal) {
		this.subtotal = subtotal;
	}
	public BigDecimal getTaxAmount() {
		return taxAmount;
	}
	public void setTaxAmount(BigDecimal taxamount) {
		this.taxAmount = taxamount;
	}
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	public BigDecimal getUseTaxAmount()
	{
		return useTaxAmount;
	}
	public void setUseTaxAmount(BigDecimal useTaxAmount)
	{
		this.useTaxAmount = useTaxAmount;
	}
	public BigDecimal getDiscount()
	{
		return discount;
	}


}
