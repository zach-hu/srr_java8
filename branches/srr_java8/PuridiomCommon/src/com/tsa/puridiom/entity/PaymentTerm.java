package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class PaymentTerm implements Serializable {

    /** identifier field */
    private String termsCode;

    /** nullable persistent field */
    private String termDescription;

    /** nullable persistent field */
    private java.math.BigDecimal termTypeFlag;

    /** nullable persistent field */
    private java.math.BigDecimal termDays;

    /** nullable persistent field */
    private java.math.BigDecimal discount;

    /** nullable persistent field */
    private java.math.BigDecimal discountDays;

    /** nullable persistent field */
    private java.util.Date dateEntered;

    /** nullable persistent field */
    private java.util.Date dateExpires;

    /** nullable persistent field */
    private String owner;

    /** nullable persistent field */
    private String status;

    /** nullable persistent field */
    private String calcMethod;

    /** full constructor */
    public PaymentTerm(java.lang.String termsCode, java.lang.String termDescription, java.math.BigDecimal termTypeFlag, java.math.BigDecimal termDays, java.math.BigDecimal discount, java.math.BigDecimal discountDays, java.util.Date dateEntered, java.util.Date dateExpires, java.lang.String owner, java.lang.String status, java.lang.String calcMethod) {
        this.termsCode = termsCode;
        this.termDescription = termDescription;
        this.termTypeFlag = termTypeFlag;
        this.termDays = termDays;
        this.discount = discount;
        this.discountDays = discountDays;
        this.dateEntered = dateEntered;
        this.dateExpires = dateExpires;
        this.owner = owner;
        this.status = status;
        this.calcMethod = calcMethod;
    }

    /** default constructor */
    public PaymentTerm() {
    }

    /** minimal constructor */
    public PaymentTerm(java.lang.String termsCode) {
        this.termsCode = termsCode;
    }

    public java.lang.String getTermsCode() {
		return (java.lang.String)HiltonUtility.ckNull(this.termsCode).trim();
    }

    public void setTermsCode(java.lang.String termsCode) {
		if (!HiltonUtility.isEmpty(termsCode) && termsCode.length() > 15) {
			termsCode = termsCode.substring(0, 15);
		}
		this.termsCode = termsCode;
    }

    public java.lang.String getTermDescription() {
		return (java.lang.String)HiltonUtility.ckNull(this.termDescription).trim();
    }

    public void setTermDescription(java.lang.String termDescription) {
		if (!HiltonUtility.isEmpty(termDescription) && termDescription.length() > 60) {
			termDescription = termDescription.substring(0, 60);
		}
		this.termDescription = termDescription;
    }

    public java.math.BigDecimal getTermTypeFlag() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.termTypeFlag);
    }

    public void setTermTypeFlag(java.math.BigDecimal termTypeFlag) {
        this.termTypeFlag = termTypeFlag;
    }

    public java.math.BigDecimal getTermDays() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.termDays);
    }

    public void setTermDays(java.math.BigDecimal termDays) {
        this.termDays = termDays;
    }

    public java.math.BigDecimal getDiscount() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.discount);
    }

    public void setDiscount(java.math.BigDecimal discount) {
        this.discount = discount;
    }

    public java.math.BigDecimal getDiscountDays() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.discountDays);
    }

    public void setDiscountDays(java.math.BigDecimal discountDays) {
        this.discountDays = discountDays;
    }

    public java.util.Date getDateEntered() {
		return this.dateEntered;
//		return HiltonUtility.ckNull(this.dateEntered);
//		return (java.sql.Date)HiltonUtility.ckNull(this.dateEntered);
    }

    public void setDateEntered(java.util.Date dateEntered) {
        this.dateEntered = dateEntered;
    }

    public java.util.Date getDateExpires() {
		return this.dateExpires;
//		return HiltonUtility.ckNull(this.dateExpires);
//		return (java.sql.Date)HiltonUtility.ckNull(this.dateExpires);
    }

    public void setDateExpires(java.util.Date dateExpires) {
        this.dateExpires = dateExpires;
    }

    public java.lang.String getOwner() {
		return (java.lang.String)HiltonUtility.ckNull(this.owner).trim();
    }

    public void setOwner(java.lang.String owner) {
		if (!HiltonUtility.isEmpty(owner) && owner.length() > 15) {
			owner = owner.substring(0, 15);
		}
		this.owner = owner;
    }

    public java.lang.String getStatus() {
		return (java.lang.String)HiltonUtility.ckNull(this.status).trim();
    }

    public void setStatus(java.lang.String status) {
		if (!HiltonUtility.isEmpty(status) && status.length() > 4) {
			status = status.substring(0, 4);
		}
		this.status = status;
    }

    public java.lang.String getCalcMethod() {
		return (java.lang.String)HiltonUtility.ckNull(this.calcMethod).trim();
    }

    public void setCalcMethod(java.lang.String calcMethod) {
		if (!HiltonUtility.isEmpty(calcMethod) && calcMethod.length() > 1) {
			calcMethod = calcMethod.substring(0, 1);
		}
		this.calcMethod = calcMethod;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("termsCode", getTermsCode())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof PaymentTerm) ) return false;
        PaymentTerm castOther = (PaymentTerm) other;
        return new EqualsBuilder()
            .append(this.getTermsCode(), castOther.getTermsCode())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getTermsCode())
            .toHashCode();
    }

}
