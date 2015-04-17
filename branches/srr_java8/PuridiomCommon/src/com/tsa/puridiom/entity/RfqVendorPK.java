package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class RfqVendorPK implements Serializable {

    /** identifier field */
    private java.math.BigDecimal icRfqHeader;

    /** identifier field */
    private String vendorId;

    /** full constructor */
    public RfqVendorPK(java.math.BigDecimal icRfqHeader, java.lang.String vendorId) {
        this.icRfqHeader = icRfqHeader;
        this.vendorId = vendorId;
    }

    /** default constructor */
    public RfqVendorPK() {
    }

    public java.math.BigDecimal getIcRfqHeader() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.icRfqHeader);
    }

    public void setIcRfqHeader(java.math.BigDecimal icRfqHeader) {
        this.icRfqHeader = icRfqHeader;
    }

    public java.lang.String getVendorId() {
		return (java.lang.String)HiltonUtility.ckNull(this.vendorId);
    }

    public void setVendorId(java.lang.String vendorId) {
        this.vendorId = vendorId;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("icRfqHeader", getIcRfqHeader())
            .append("vendorId", getVendorId())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof RfqVendorPK) ) return false;
        RfqVendorPK castOther = (RfqVendorPK) other;
        return new EqualsBuilder()
            .append(this.getIcRfqHeader(), castOther.getIcRfqHeader())
            .append(this.getVendorId(), castOther.getVendorId())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getIcRfqHeader())
            .append(getVendorId())
            .toHashCode();
    }

}
