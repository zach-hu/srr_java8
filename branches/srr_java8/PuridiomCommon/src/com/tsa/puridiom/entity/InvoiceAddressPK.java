package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class InvoiceAddressPK implements Serializable {

    /** identifier field */
    private String vendorId;

    /** identifier field */
    private String addressCode;

    /** full constructor */
    public InvoiceAddressPK(java.lang.String vendorId, java.lang.String addressCode) {
        this.vendorId = vendorId;
        this.addressCode = addressCode;
    }

    /** default constructor */
    public InvoiceAddressPK() {
    }

    public java.lang.String getVendorId() {
		return (java.lang.String)HiltonUtility.ckNull(this.vendorId);
    }

    public void setVendorId(java.lang.String vendorId) {
        this.vendorId = vendorId;
    }

    public java.lang.String getAddressCode() {
		return (java.lang.String)HiltonUtility.ckNull(this.addressCode);
    }

    public void setAddressCode(java.lang.String addressCode) {
        this.addressCode = addressCode;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("vendorId", getVendorId())
            .append("addressCode", getAddressCode())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof InvoiceAddressPK) ) return false;
        InvoiceAddressPK castOther = (InvoiceAddressPK) other;
        return new EqualsBuilder()
            .append(this.getVendorId(), castOther.getVendorId())
            .append(this.getAddressCode(), castOther.getAddressCode())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getVendorId())
            .append(getAddressCode())
            .toHashCode();
    }

}
