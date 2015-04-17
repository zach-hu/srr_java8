package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class RecentRfqPK implements Serializable {

    /** identifier field */
    private java.lang.String buyerCode;

    /** identifier field */
    private java.math.BigDecimal icRfqHeader;

    /** full constructor */
    public RecentRfqPK(java.lang.String buyerCode, java.math.BigDecimal icRfqHeader) {
        this.buyerCode = buyerCode;
        this.icRfqHeader = icRfqHeader;
    }

    /** default constructor */
    public RecentRfqPK() {
    }

    public java.lang.String getBuyerCode() {
		return (java.lang.String)HiltonUtility.ckNull(this.buyerCode);
    }

    public void setBuyerCode(java.lang.String buyerCode) {
        this.buyerCode = buyerCode;
    }
    
    public java.math.BigDecimal getIcRfqHeader() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.icRfqHeader);
    }

    public void setIcRfqHeader(java.math.BigDecimal icRfqHeader) {
        this.icRfqHeader = icRfqHeader;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("buyerCode", getBuyerCode())
            .append("icRfqHeader", getIcRfqHeader())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof RecentRfqPK) ) return false;
        RecentRfqPK castOther = (RecentRfqPK) other;
        return new EqualsBuilder()
            .append(this.getBuyerCode(), castOther.getBuyerCode())
            .append(this.getIcRfqHeader(), castOther.getIcRfqHeader())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getBuyerCode())
            .append(getIcRfqHeader())
            .toHashCode();
    }

}
