package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class ContactPK implements Serializable {

    /** identifier field */
    private String contactCode;

    /** identifier field */
    private String contactType;

    /** identifier field */
    private String vendorId;

    /** full constructor */
    public ContactPK(java.lang.String contactCode, java.lang.String contactType, java.lang.String vendorId) {
        this.contactCode = contactCode;
        this.contactType = contactType;
        this.vendorId = vendorId;
    }

    /** default constructor */
    public ContactPK() {
    }

    public java.lang.String getContactCode() {
		return this.contactCode;
    }

    public void setContactCode(java.lang.String contactCode) {
        this.contactCode = contactCode;
    }

    public java.lang.String getContactType() {
		return this.contactType;
    }

    public void setContactType(java.lang.String contactType) {
        this.contactType = contactType;
    }

    public java.lang.String getVendorId() {
		return this.vendorId;
    }

    public void setVendorId(java.lang.String vendorId) {
        this.vendorId = vendorId;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("contactCode", getContactCode())
            .append("contactType", getContactType())
            .append("vendorId", getVendorId())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof ContactPK) ) return false;
        ContactPK castOther = (ContactPK) other;
        return new EqualsBuilder()
            .append(this.getContactCode(), castOther.getContactCode())
            .append(this.getContactType(), castOther.getContactType())
            .append(this.getVendorId(), castOther.getVendorId())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getContactCode())
            .append(getContactType())
            .append(getVendorId())
            .toHashCode();
    }

}
