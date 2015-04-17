package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class PaymentPK implements Serializable {

    /** identifier field */
    private java.math.BigDecimal icPoHeader;

    /** identifier field */
    private java.math.BigDecimal sequence;

    /** full constructor */
    public PaymentPK(java.math.BigDecimal icPoHeader, java.math.BigDecimal sequence) {
        this.icPoHeader = icPoHeader;
        this.sequence = sequence;
    }

    /** default constructor */
    public PaymentPK() {
    }

    public java.math.BigDecimal getIcPoHeader() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.icPoHeader);
    }

    public void setIcPoHeader(java.math.BigDecimal icPoHeader) {
        this.icPoHeader = icPoHeader;
    }

    public java.math.BigDecimal getSequence() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.sequence);
    }

    public void setSequence(java.math.BigDecimal sequence) {
        this.sequence = sequence;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("icPoHeader", getIcPoHeader())
            .append("sequence", getSequence())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof PaymentPK) ) return false;
        PaymentPK castOther = (PaymentPK) other;
        return new EqualsBuilder()
            .append(this.getIcPoHeader(), castOther.getIcPoHeader())
            .append(this.getSequence(), castOther.getSequence())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getIcPoHeader())
            .append(getSequence())
            .toHashCode();
    }

}
