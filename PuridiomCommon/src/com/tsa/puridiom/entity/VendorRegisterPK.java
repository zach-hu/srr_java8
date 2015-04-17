package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;
import com.tsagate.foundation.utility.Utility;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class VendorRegisterPK implements Serializable {

    /** identifier field */
    private String vendorId;

    /** identifier field */
    private String contactEmailAddr;

    /** full constructor */
    public VendorRegisterPK(java.lang.String vendorId, java.lang.String contactEmailAddr) {
        this.vendorId = vendorId;
        this.contactEmailAddr = contactEmailAddr;
    }

    /** default constructor */
    public VendorRegisterPK() {
    }

    public java.lang.String getVendorId() {
		return (java.lang.String)HiltonUtility.ckNull(this.vendorId);
    }

    public void setVendorId(java.lang.String vendorId) {
        this.vendorId = vendorId;
    }

    public java.lang.String getContactEmailAddr() {
		return (java.lang.String)Utility.ckNull(this.contactEmailAddr);
    }

    public void setContactEmailAddr(java.lang.String contactEmailAddr) {
        this.contactEmailAddr = contactEmailAddr;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("vendorId", getVendorId())
            .append("contactEmailAddr", getContactEmailAddr())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof VendorRegisterPK) ) return false;
        VendorRegisterPK castOther = (VendorRegisterPK) other;
        return new EqualsBuilder()
            .append(this.getVendorId(), castOther.getVendorId())
            .append(this.getContactEmailAddr(), castOther.getContactEmailAddr())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getVendorId())
            .append(getContactEmailAddr())
            .toHashCode();
    }

}
