package com.tsa.puridiom.entity.sungard;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import java.math.BigDecimal;

/** @author Hibernate CodeGenerator */
public class VendorPK implements Serializable {

    /** identifier field */
    private String vendorId;

    /** identifier field */
    private BigDecimal internalVendorId;

    /** full constructor */
    public VendorPK(java.lang.String vendorId, java.math.BigDecimal internalVendorId) {
        this.vendorId = vendorId;
        this.internalVendorId = internalVendorId;
    }

    /** default constructor */
    public VendorPK() {
    }

    public java.lang.String getVendorId() {
		return this.vendorId;
    }

    public void setVendorId(java.lang.String vendorId) {
        this.vendorId = vendorId;
    }

    public java.math.BigDecimal getInternalVendorId() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.internalVendorId);
    }

    public void setInternalVendorId(java.math.BigDecimal internalVendorId) {
        this.internalVendorId = internalVendorId;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("vendorId", getVendorId())
            .append("internalVendorId", String.valueOf(getInternalVendorId()))
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof VendorPK) ) return false;
        VendorPK castOther = (VendorPK) other;
        return new EqualsBuilder()
            .append(this.getVendorId(), castOther.getVendorId())
            .append(this.getInternalVendorId(), castOther.getInternalVendorId())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getVendorId())
            .append(getInternalVendorId())
            .toHashCode();
    }

}
