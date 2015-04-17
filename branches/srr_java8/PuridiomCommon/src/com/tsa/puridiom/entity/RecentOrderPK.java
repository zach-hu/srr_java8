package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class RecentOrderPK implements Serializable {

    /** identifier field */
    private java.lang.String buyerCode;

    /** identifier field */
    private java.math.BigDecimal icPoHeader;

    /** full constructor */
    public RecentOrderPK(java.lang.String buyerCode, java.math.BigDecimal icPoHeader) {
        this.buyerCode = buyerCode;
        this.icPoHeader = icPoHeader;
    }

    /** default constructor */
    public RecentOrderPK() {
    }

    public java.lang.String getBuyerCode() {
		return (java.lang.String)HiltonUtility.ckNull(this.buyerCode);
    }

    public void setBuyerCode(java.lang.String buyerCode) {
        this.buyerCode = buyerCode;
    }
    
    public java.math.BigDecimal getIcPoHeader() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.icPoHeader);
    }

    public void setIcPoHeader(java.math.BigDecimal icPoHeader) {
        this.icPoHeader = icPoHeader;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("buyerCode", getBuyerCode())
            .append("icPoHeader", getIcPoHeader())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof RecentOrderPK) ) return false;
        RecentOrderPK castOther = (RecentOrderPK) other;
        return new EqualsBuilder()
            .append(this.getBuyerCode(), castOther.getBuyerCode())
            .append(this.getIcPoHeader(), castOther.getIcPoHeader())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getBuyerCode())
            .append(getIcPoHeader())
            .toHashCode();
    }

}
