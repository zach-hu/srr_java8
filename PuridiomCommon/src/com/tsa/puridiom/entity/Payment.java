package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class Payment implements Serializable {

    /** identifier field */
    private com.tsa.puridiom.entity.PaymentPK comp_id;

    /** nullable persistent field */
    private java.math.BigDecimal icPoLine;

    /** nullable persistent field */
    private String poNumber;

    /** nullable persistent field */
    private java.math.BigDecimal  revisionNumber;

    /** nullable persistent field */
    private java.math.BigDecimal releaseNumber;

    /** nullable persistent field */
    private java.util.Date paymentDate;

    /** nullable persistent field */
    private String invoiceNumber;

    /** nullable persistent field */
    private java.math.BigDecimal paymentAmount;

    /** nullable persistent field */
    private String checkNumber;

    /** nullable persistent field */
    private String voucherNumber;

    /** full constructor */
    public Payment(com.tsa.puridiom.entity.PaymentPK comp_id, java.math.BigDecimal icPoLine, java.lang.String poNumber, java.math.BigDecimal revisionNumber, java.math.BigDecimal releaseNumber, java.util.Date paymentDate, java.lang.String invoiceNumber, java.math.BigDecimal paymentAmount, java.lang.String checkNumber, java.lang.String voucherNumber) {
        this.comp_id = comp_id;
        this.icPoLine = icPoLine;
        this.poNumber = poNumber;
        this.revisionNumber = revisionNumber;
        this.releaseNumber = releaseNumber;
        this.paymentDate = paymentDate;
        this.invoiceNumber = invoiceNumber;
        this.paymentAmount = paymentAmount;
        this.checkNumber = checkNumber;
        this.voucherNumber = voucherNumber;
    }

    /** default constructor */
    public Payment() {
    }

    /** minimal constructor */
    public Payment(com.tsa.puridiom.entity.PaymentPK comp_id) {
        this.comp_id = comp_id;
    }

    public com.tsa.puridiom.entity.PaymentPK getComp_id() {
        return this.comp_id;
    }

    public void setComp_id(com.tsa.puridiom.entity.PaymentPK comp_id) {
        this.comp_id = comp_id;
    }

    public java.math.BigDecimal getIcPoLine() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.icPoLine);
    }

    public void setIcPoLine(java.math.BigDecimal icPoLine) {
        this.icPoLine = icPoLine;
    }

    public java.lang.String getPoNumber() {
		return (java.lang.String)HiltonUtility.ckNull(this.poNumber).trim();
    }

    public void setPoNumber(java.lang.String poNumber) {
		if (!HiltonUtility.isEmpty(poNumber) && poNumber.length() > 20) {
			poNumber = poNumber.substring(0, 20);
		}
		this.poNumber = poNumber;
    }

    public java.math.BigDecimal getRevisionNumber() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.revisionNumber);
    }

    public void setRevisionNumber(java.math.BigDecimal revisionNumber) {
        this.revisionNumber = revisionNumber;
    }

    public java.math.BigDecimal getReleaseNumber() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.releaseNumber);
    }

    public void setReleaseNumber(java.math.BigDecimal releaseNumber) {
        this.releaseNumber = releaseNumber;
    }

    public java.util.Date getPaymentDate() {
		return this.paymentDate;
//		return HiltonUtility.ckNull(this.paymentDate);
    }

    public void setPaymentDate(java.util.Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public java.lang.String getInvoiceNumber() {
		return (java.lang.String)HiltonUtility.ckNull(this.invoiceNumber).trim();
    }

    public void setInvoiceNumber(java.lang.String invoiceNumber) {
		if (!HiltonUtility.isEmpty(invoiceNumber) && invoiceNumber.length() > 20) {
			invoiceNumber = invoiceNumber.substring(0, 20);
		}
		this.invoiceNumber = invoiceNumber;
    }

    public java.math.BigDecimal getPaymentAmount() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.paymentAmount);
    }

    public void setPaymentAmount(java.math.BigDecimal paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public java.lang.String getCheckNumber() {
		return (java.lang.String)HiltonUtility.ckNull(this.checkNumber).trim();
    }

    public void setCheckNumber(java.lang.String checkNumber) {
		if (!HiltonUtility.isEmpty(checkNumber) && checkNumber.length() > 15) {
			checkNumber = checkNumber.substring(0, 15);
		}
		this.checkNumber = checkNumber;
    }

    public java.lang.String getVoucherNumber() {
		return (java.lang.String)HiltonUtility.ckNull(this.voucherNumber).trim();
    }

    public void setVoucherNumber(java.lang.String voucherNumber) {
		if (!HiltonUtility.isEmpty(voucherNumber) && voucherNumber.length() > 20) {
			voucherNumber = voucherNumber.substring(0, 20);
		}
		this.voucherNumber = voucherNumber;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("comp_id", getComp_id())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof Payment) ) return false;
        Payment castOther = (Payment) other;
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
