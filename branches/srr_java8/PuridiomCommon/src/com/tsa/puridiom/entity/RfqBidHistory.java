package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class RfqBidHistory implements Serializable {

     /** identifier field */
    private com.tsa.puridiom.entity.RfqBidHistoryPK comp_id;

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
    private java.util.Date lastMdfDate;

    /** nullable persistent field */
    private String bidCode;

    /** nullable persistent field */
    private java.math.BigDecimal umFactor;

    /** nullable persistent field */
  //  private java.math.BigDecimal bidSequence;

    /** nullable persistent field */
    private String timeZone;

    /** nullable persistent field */
    private String userId;

    /** full constructor */
    public RfqBidHistory(com.tsa.puridiom.entity.RfqBidHistoryPK comp_id, java.math.BigDecimal quantity, java.lang.String umCode, java.math.BigDecimal unitPrice, java.lang.String discountSource, java.math.BigDecimal discountAmount, java.math.BigDecimal discountPercent, java.math.BigDecimal shippingCharges, java.math.BigDecimal otherCharges, java.lang.String otherDescript, java.lang.String taxShipping, java.lang.String taxOther, java.lang.String commentFlag, java.math.BigDecimal taxPercent, java.math.BigDecimal taxAmount, java.math.BigDecimal shippingTaxAmt, java.math.BigDecimal otherTaxAmount, java.lang.String bidCurrency, java.util.Date lastChgDate, java.util.Date lastMdfDate, java.lang.String bidCode, java.math.BigDecimal umFactor, java.lang.String userId, java. lang.String timeZone) {
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
        this.lastMdfDate = lastMdfDate;
        this.bidCode = bidCode;
        this.umFactor = umFactor;
       // this.bidSequence = bidSequence;
        this.timeZone = timeZone;

        this.userId = userId;
    }

    /** default constructor */
    public RfqBidHistory() {
    }

    /** minimal constructor */
    public RfqBidHistory(com.tsa.puridiom.entity.RfqBidHistoryPK comp_id) {
       this.comp_id = comp_id;
    }

     public com.tsa.puridiom.entity.RfqBidHistoryPK getComp_id() {
        return this.comp_id;
    }

    public void setComp_id(RfqBidHistoryPK comp_id) {
        this.comp_id = comp_id;
    }

    public java.math.BigDecimal getQuantity() {
        return this.quantity;
    }

    public void setQuantity(java.math.BigDecimal quantity) {
        this.quantity = quantity;
    }

    public java.lang.String getUmCode() {
        return this.umCode;
    }

    public void setUmCode(java.lang.String umCode) {
        this.umCode = umCode;
    }

    public java.math.BigDecimal getUnitPrice() {
        return this.unitPrice;
    }

    public void setUnitPrice(java.math.BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public java.lang.String getDiscountSource() {
        return this.discountSource;
    }

    public void setDiscountSource(java.lang.String discountSource) {
        this.discountSource = discountSource;
    }

    public java.math.BigDecimal getDiscountAmount() {
        return this.discountAmount;
    }

    public void setDiscountAmount(java.math.BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }

    public java.math.BigDecimal getDiscountPercent() {
        return this.discountPercent;
    }

    public void setDiscountPercent(java.math.BigDecimal discountPercent) {
        this.discountPercent = discountPercent;
    }

    public java.math.BigDecimal getShippingCharges() {
        return this.shippingCharges;
    }

    public void setShippingCharges(java.math.BigDecimal shippingCharges) {
        this.shippingCharges = shippingCharges;
    }

    public java.math.BigDecimal getOtherCharges() {
        return this.otherCharges;
    }

    public void setOtherCharges(java.math.BigDecimal otherCharges) {
        this.otherCharges = otherCharges;
    }

    public java.lang.String getOtherDescript() {
        return this.otherDescript;
    }

    public void setOtherDescript(java.lang.String otherDescript) {
        this.otherDescript = otherDescript;
    }

    public java.lang.String getTaxShipping() {
        return this.taxShipping;
    }

    public void setTaxShipping(java.lang.String taxShipping) {
        this.taxShipping = taxShipping;
    }

    public java.lang.String getTaxOther() {
        return this.taxOther;
    }

    public void setTaxOther(java.lang.String taxOther) {
        this.taxOther = taxOther;
    }

    public java.lang.String getCommentFlag() {
        return this.commentFlag;
    }

    public void setCommentFlag(java.lang.String commentFlag) {
        this.commentFlag = commentFlag;
    }

    public java.math.BigDecimal getTaxPercent() {
        return this.taxPercent;
    }

    public void setTaxPercent(java.math.BigDecimal taxPercent) {
        this.taxPercent = taxPercent;
    }

    public java.math.BigDecimal getTaxAmount() {
        return this.taxAmount;
    }

    public void setTaxAmount(java.math.BigDecimal taxAmount) {
        this.taxAmount = taxAmount;
    }

    public java.math.BigDecimal getShippingTaxAmt() {
        return this.shippingTaxAmt;
    }

    public void setShippingTaxAmt(java.math.BigDecimal shippingTaxAmt) {
        this.shippingTaxAmt = shippingTaxAmt;
    }

    public java.math.BigDecimal getOtherTaxAmount() {
        return this.otherTaxAmount;
    }

    public void setOtherTaxAmount(java.math.BigDecimal otherTaxAmount) {
        this.otherTaxAmount = otherTaxAmount;
    }

    public java.lang.String getBidCurrency() {
        return this.bidCurrency;
    }

    public void setBidCurrency(java.lang.String bidCurrency) {
        this.bidCurrency = bidCurrency;
    }

    public java.util.Date getLastChgDate() {
        return this.lastChgDate;
    }

    public void setLastChgDate(java.util.Date lastChgDate) {
        this.lastChgDate = lastChgDate;
    }

    public java.util.Date getLastMdfDate() {
        return this.lastMdfDate;
    }

    public void setLastMdfDate(java.util.Date lastMdfDate) {
        this.lastMdfDate = lastMdfDate;
    }

    public java.lang.String getBidCode() {
        return this.bidCode;
    }

    public void setBidCode(java.lang.String bidCode) {
        this.bidCode = bidCode;
    }

    public java.math.BigDecimal getUmFactor() {
        return this.umFactor;
    }

    public void setUmFactor(java.math.BigDecimal umFactor) {
        this.umFactor = umFactor;
    }

    /*public java.math.BigDecimal getBidSequence() {
        return this.bidSequence;
    }

    public void setBidSequence(java.math.BigDecimal bidSequence) {
        this.bidSequence = bidSequence;
    }*/


    public java.lang.String getUserId() {
        return this.userId;
    }

    public void setUserId(java.lang.String userId) {
        this.userId = userId;
    }

    public String getTimeZone()
    {
        return (java.lang.String) HiltonUtility.ckNull(this.timeZone).trim();
    }

    public void setTimeZone(String timeZone)
    {
        if (!HiltonUtility.isEmpty(timeZone) && timeZone.length() > 30) {
            timeZone = timeZone.substring(0, 30);
        }
        this.timeZone = timeZone;
    }

    public String toString() {
        return new ToStringBuilder(this)
        .append("comp_id", getComp_id())
        .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof RfqBidHistory) ) return false;
        RfqBidHistory castOther = (RfqBidHistory) other;
        return new EqualsBuilder()
            .append(this.getComp_id(), castOther.getComp_id())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getComp_id())
            .toHashCode();
    }

}
