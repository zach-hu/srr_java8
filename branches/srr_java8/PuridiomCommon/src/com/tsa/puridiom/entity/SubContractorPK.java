package com.tsa.puridiom.entity;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.tsa.puridiom.common.utility.HiltonUtility;

/** @author Hibernate CodeGenerator */
public class SubContractorPK implements Serializable {

    /** identifier field */
    private String poNumber;

    /** identifier field */
    private java.math.BigDecimal releaseNumber;

    /** identifier field */
    private String subName;

    /** full constructor */
    public SubContractorPK(java.lang.String poNumber, java.math.BigDecimal releaseNumber, java.lang.String subName) {
        this.poNumber = poNumber;
        this.releaseNumber = releaseNumber;
        this.subName = subName;
    }

    /** default constructor */
    public SubContractorPK() {
    }

    public java.lang.String getPoNumber() {
    	return (java.lang.String)HiltonUtility.ckNull(this.poNumber).trim();
    }

    public void setPoNumber(java.lang.String poNumber) {
        this.poNumber = poNumber;
    }

    public java.math.BigDecimal getReleaseNumber() {
    	return (java.math.BigDecimal)HiltonUtility.ckNull(this.releaseNumber);
    }

    public void setReleaseNumber(java.math.BigDecimal releaseNumber) {
        this.releaseNumber = releaseNumber;
    }

    public java.lang.String getSubName() {
    	return (java.lang.String)HiltonUtility.ckNull(this.subName).trim();
    }

    public void setSubName(java.lang.String subName) {
        this.subName = subName;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("poNumber", getPoNumber())
            .append("releaseNumber", getReleaseNumber())
            .append("subName", getSubName())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof SubContractorPK) ) return false;
        SubContractorPK castOther = (SubContractorPK) other;
        return new EqualsBuilder()
            .append(this.getPoNumber(), castOther.getPoNumber())
            .append(this.getReleaseNumber(), castOther.getReleaseNumber())
            .append(this.getSubName(), castOther.getSubName())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getPoNumber())
            .append(getReleaseNumber())
            .append(getSubName())
            .toHashCode();
    }

}
