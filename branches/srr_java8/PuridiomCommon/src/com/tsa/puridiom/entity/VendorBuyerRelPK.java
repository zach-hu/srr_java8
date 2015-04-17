package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class VendorBuyerRelPK implements Serializable {

    /** identifier field */
    private String vendorId;

    /** identifier field */
    private String userId;

    /** full constructor */
    public VendorBuyerRelPK(java.lang.String vendorId, java.lang.String userId) {
        this.vendorId = vendorId;
        this.userId = userId;
    }

    /** default constructor */
    public VendorBuyerRelPK() {
    }

    public java.lang.String getVendorId() {
		return (java.lang.String)HiltonUtility.ckNull(this.vendorId);
    }

    public void setVendorId(java.lang.String vendorId) {
        this.vendorId = vendorId;
    }

    public java.lang.String getUserId() {
		return (java.lang.String)HiltonUtility.ckNull(this.userId);
    }

    public void setUserId(java.lang.String userId) {
        this.userId = userId;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("vendorId", getVendorId())
            .append("userId", getUserId())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof VendorBuyerRelPK) ) return false;
        VendorBuyerRelPK castOther = (VendorBuyerRelPK) other;
        return new EqualsBuilder()
            .append(this.getVendorId(), castOther.getVendorId())
            .append(this.getUserId(), castOther.getUserId())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getVendorId())
            .append(getUserId())
            .toHashCode();
    }

}
