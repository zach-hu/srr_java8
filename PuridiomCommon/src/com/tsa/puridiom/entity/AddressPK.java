package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class AddressPK implements Serializable {

    /** identifier field */
    private String addressType;

    /** identifier field */
    private String addressCode;

    /** full constructor */
    public AddressPK(java.lang.String addressType, java.lang.String addressCode) {
        this.addressType = addressType;
        this.addressCode = addressCode;
    }

    /** default constructor */
    public AddressPK() {
    }

    public java.lang.String getAddressType() {
		return (java.lang.String)HiltonUtility.ckNull(this.addressType);
    }

    public void setAddressType(java.lang.String addressType) {
        this.addressType = addressType;
    }

    public java.lang.String getAddressCode() {
		return (java.lang.String)HiltonUtility.ckNull(this.addressCode);
    }

    public void setAddressCode(java.lang.String addressCode) {
        this.addressCode = addressCode;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("addressType", getAddressType())
            .append("addressCode", getAddressCode())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof AddressPK) ) return false;
        AddressPK castOther = (AddressPK) other;
        return new EqualsBuilder()
            .append(this.getAddressType(), castOther.getAddressType())
            .append(this.getAddressCode(), castOther.getAddressCode())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getAddressType())
            .append(getAddressCode())
            .toHashCode();
    }

}
