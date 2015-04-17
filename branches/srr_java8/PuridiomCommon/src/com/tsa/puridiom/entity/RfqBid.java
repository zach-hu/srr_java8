package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class RfqBid implements Serializable {

    /** identifier field */
    private com.tsa.puridiom.entity.RfqBidPK comp_id;

    /** nullable persistent field */
    private java.math.BigDecimal quantity;

    /** nullable persistent field */
    private String umCode;

    /** nullable persistent field */
    private java.math.BigDecimal unitPrice;

    /** nullable persistent field */
    private String discountSource;

    /** nullable persistent field */
    private java.math.BigDecimal discountAmount;

    /** nullable persistent field */
    private java.math.BigDecimal discountPercent;

    /** nullable persistent field */
    private java.math.BigDecimal shippingCharges;

    /** nullable persistent field */
    private java.math.BigDecimal otherCharges;

    /** nullable persistent field */
    private String otherDescript;

    /** nullable persistent field */
    private String taxShipping;

    /** nullable persistent field */
    private String taxOther;

    /** nullable persistent field */
    private String commentFlag;

    /** nullable persistent field */
    private java.math.BigDecimal taxPercent;

    /** nullable persistent field */
    private java.math.BigDecimal taxAmount;

    /** nullable persistent field */
    private java.math.BigDecimal shippingTaxAmt;

    /** nullable persistent field */
    private java.math.BigDecimal otherTaxAmount;

    /** nullable persistent field */
    private String bidCurrency;

    /** nullable persistent field */
    private java.util.Date lastChgDate;

    /** nullable persistent field */
    private String bidCode;

    /** nullable persistent field */
    private java.math.BigDecimal umFactor;

    /** nullable persistent field */
    private java.math.BigDecimal currencyFactor;

    /** full constructor */
    public RfqBid(com.tsa.puridiom.entity.RfqBidPK comp_id, java.math.BigDecimal quantity, java.lang.String umCode, java.math.BigDecimal unitPrice, java.lang.String discountSource, java.math.BigDecimal discountAmount, java.math.BigDecimal discountPercent, java.math.BigDecimal shippingCharges, java.math.BigDecimal otherCharges, java.lang.String otherDescript, java.lang.String taxShipping, java.lang.String taxOther, java.lang.String commentFlag, java.math.BigDecimal taxPercent, java.math.BigDecimal taxAmount, java.math.BigDecimal shippingTaxAmt, java.math.BigDecimal otherTaxAmount, java.lang.String bidCurrency, java.util.Date lastChgDate, java.lang.String bidCode, java.math.BigDecimal umFactor, java.math.BigDecimal currencyFactor) {
        this.comp_id = comp_id;
        this.quantity = quantity;
        this.umCode = umCode;
        this.unitPrice = unitPrice;
        this.discountSource = discountSource;
        this.discountAmount = discountAmount;
        this.discountPercent = discountPercent;
        this.shippingCharges = shippingCharges;
        this.otherCharges = otherCharges;
        this.otherDescript = otherDescript;
        this.taxShipping = taxShipping;
        this.taxOther = taxOther;
        this.commentFlag = commentFlag;
        this.taxPercent = taxPercent;
        this.taxAmount = taxAmount;
        this.shippingTaxAmt = shippingTaxAmt;
        this.otherTaxAmount = otherTaxAmount;
        this.bidCurrency = bidCurrency;
        this.lastChgDate = lastChgDate;
        this.bidCode = bidCode;
        this.umFactor = umFactor;
        this.currencyFactor = currencyFactor;
    }

    /** default constructor */
    public RfqBid() {
    }

    /** minimal constructor */
    public RfqBid(com.tsa.puridiom.entity.RfqBidPK comp_id) {
        this.comp_id = comp_id;
    }

    public com.tsa.puridiom.entity.RfqBidPK getComp_id() {
        return this.comp_id;
    }

    public void setComp_id(com.tsa.puridiom.entity.RfqBidPK comp_id) {
        this.comp_id = comp_id;
    }

    public java.math.BigDecimal getQuantity() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.quantity);
    }

    public void setQuantity(java.math.BigDecimal quantity) {
        this.quantity = quantity;
    }

    public java.lang.String getUmCode() {
		return (java.lang.String)HiltonUtility.ckNull(this.umCode).trim();
    }

    public void setUmCode(java.lang.String umCode) {
		if (!HiltonUtility.isEmpty(umCode) && umCode.length() > 15) {
			umCode = umCode.substring(0, 15);
		}
		this.umCode = umCode;
    }

    public java.math.BigDecimal getUnitPrice() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.unitPrice);
    }

    public void setUnitPrice(java.math.BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public java.lang.String getDiscountSource() {
		return (java.lang.String)HiltonUtility.ckNull(this.discountSource).trim();
    }

    public void setDiscountSource(java.lang.String discountSource) {
		if (!HiltonUtility.isEmpty(discountSource) && discountSource.length() > 1) {
			discountSource = discountSource.substring(0, 1);
		}
		this.discountSource = discountSource;
    }

    public java.math.BigDecimal getDiscountAmount() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.discountAmount);
    }

    public void setDiscountAmount(java.math.BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }

    public java.math.BigDecimal getDiscountPercent() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.discountPercent);
    }

    public void setDiscountPercent(java.math.BigDecimal discountPercent) {
        this.discountPercent = discountPercent;
    }

    public java.math.BigDecimal getShippingCharges() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.shippingCharges);
    }

    public void setShippingCharges(java.math.BigDecimal shippingCharges) {
        this.shippingCharges = shippingCharges;
    }

    public java.math.BigDecimal getOtherCharges() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.otherCharges);
    }

    public void setOtherCharges(java.math.BigDecimal otherCharges) {
        this.otherCharges = otherCharges;
    }

    public java.lang.String getOtherDescript() {
		return (java.lang.String)HiltonUtility.ckNull(this.otherDescript).trim();
    }

    public void setOtherDescript(java.lang.String otherDescript) {
		if (!HiltonUtility.isEmpty(otherDescript) && otherDescript.length() > 30) {
			otherDescript = otherDescript.substring(0, 30);
		}
		this.otherDescript = otherDescript;
    }

    public java.lang.String getTaxShipping() {
		return (java.lang.String)HiltonUtility.ckNull(this.taxShipping).trim();
    }

    public void setTaxShipping(java.lang.String taxShipping) {
		if (!HiltonUtility.isEmpty(taxShipping) && taxShipping.length() > 1) {
			taxShipping = taxShipping.substring(0, 1);
		}
		this.taxShipping = taxShipping;
    }

    public java.lang.String getTaxOther() {
		return (java.lang.String)HiltonUtility.ckNull(this.taxOther).trim();
    }

    public void setTaxOther(java.lang.String taxOther) {
		if (!HiltonUtility.isEmpty(taxOther) && taxOther.length() > 1) {
			taxOther = taxOther.substring(0, 1);
		}
		this.taxOther = taxOther;
    }

    public java.lang.String getCommentFlag() {
		return (java.lang.String)HiltonUtility.ckNull(this.commentFlag).trim();
    }

    public void setCommentFlag(java.lang.String commentFlag) {
		if (!HiltonUtility.isEmpty(commentFlag) && commentFlag.length() > 1) {
			commentFlag = commentFlag.substring(0, 1);
		}
		this.commentFlag = commentFlag;
    }

    public java.math.BigDecimal getTaxPercent() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.taxPercent);
    }

    public void setTaxPercent(java.math.BigDecimal taxPercent) {
        this.taxPercent = taxPercent;
    }

    public java.math.BigDecimal getTaxAmount() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.taxAmount);
    }

    public void setTaxAmount(java.math.BigDecimal taxAmount) {
        this.taxAmount = taxAmount;
    }

    public java.math.BigDecimal getShippingTaxAmt() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.shippingTaxAmt);
    }

    public void setShippingTaxAmt(java.math.BigDecimal shippingTaxAmt) {
        this.shippingTaxAmt = shippingTaxAmt;
    }

    public java.math.BigDecimal getOtherTaxAmount() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.otherTaxAmount);
    }

    public void setOtherTaxAmount(java.math.BigDecimal otherTaxAmount) {
        this.otherTaxAmount = otherTaxAmount;
    }

    public java.lang.String getBidCurrency() {
		return (java.lang.String)HiltonUtility.ckNull(this.bidCurrency).trim();
    }

    public void setBidCurrency(java.lang.String bidCurrency) {
		if (!HiltonUtility.isEmpty(bidCurrency) && bidCurrency.length() > 15) {
			bidCurrency = bidCurrency.substring(0, 15);
		}
		this.bidCurrency = bidCurrency;
    }

    public java.util.Date getLastChgDate() {
		return this.lastChgDate;
//		return HiltonUtility.ckNull(this.lastChgDate);
//		return (java.sql.Date)HiltonUtility.ckNull(this.lastChgDate);
    }

    public void setLastChgDate(java.util.Date lastChgDate) {
        this.lastChgDate = lastChgDate;
    }

    public java.lang.String getBidCode() {
		return (java.lang.String)HiltonUtility.ckNull(this.bidCode).trim();
    }

    public void setBidCode(java.lang.String bidCode) {
		if (!HiltonUtility.isEmpty(bidCode) && bidCode.length() > 15) {
			bidCode = bidCode.substring(0, 15);
		}
		this.bidCode = bidCode;
    }

    public java.math.BigDecimal getCurrencyFactor() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.currencyFactor);
    }

    public void setCurrencyFactor(java.math.BigDecimal currencyFactor) {
        this.currencyFactor = currencyFactor;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("comp_id", getComp_id())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof RfqBid) ) return false;
        RfqBid castOther = (RfqBid) other;
        return new EqualsBuilder()
            .append(this.getComp_id(), castOther.getComp_id())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getComp_id())
            .toHashCode();
    }

    public java.math.BigDecimal getUmFactor() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.umFactor);
    }

    public void setUmFactor(java.math.BigDecimal umFactor) {
        this.umFactor = umFactor;
    }

}
