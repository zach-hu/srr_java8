package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class VendorAccountPK implements Serializable {

    /** identifier field */
    private String vendorId;

    /** identifier field */
    private String accountNumber;

    /** full constructor */
    public VendorAccountPK(java.lang.String vendorId, java.lang.String accountNumber) {
        this.vendorId = vendorId;
        this.accountNumber = accountNumber;
    }

    /** default constructor */
    public VendorAccountPK() {
    }

    public java.lang.String getVendorId() {
		return (java.lang.String)HiltonUtility.ckNull(this.vendorId);
    }

    public void setVendorId(java.lang.String vendorId) {
        this.vendorId = vendorId;
    }

    public java.lang.String getAccountNumber() {
		return (java.lang.String)HiltonUtility.ckNull(this.accountNumber);
    }

    public void setAccountNumber(java.lang.String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("vendorId", getVendorId())
            .append("accountNumber", getAccountNumber())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof VendorAccountPK) ) return false;
        VendorAccountPK castOther = (VendorAccountPK) other;
        return new EqualsBuilder()
            .append(this.getVendorId(), castOther.getVendorId())
            .append(this.getAccountNumber(), castOther.getAccountNumber())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getVendorId())
            .append(getAccountNumber())
            .toHashCode();
    }

}
