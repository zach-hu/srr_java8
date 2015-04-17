package com.tsa.puridiom.entity;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class RecentVendorPK implements Serializable {

    /** identifier field */
    private String userId;

    /** identifier field */
    private String vendorId;

    /** full constructor */
    public RecentVendorPK(java.lang.String userId, java.lang.String vendorId) {
        this.userId = userId;
        this.vendorId = vendorId;
    }

    /** default constructor */
    public RecentVendorPK() {
    }

    public java.lang.String getUserId() {
        return this.userId;
    }

    public void setUserId(java.lang.String userId) {
        this.userId = userId;
    }

    public java.lang.String getVendorId() {
        return this.vendorId;
    }

    public void setVendorId(java.lang.String vendorId) {
        this.vendorId = vendorId;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("userId", getUserId())
            .append("vendorId", getVendorId())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof RecentVendorPK) ) return false;
        RecentVendorPK castOther = (RecentVendorPK) other;
        return new EqualsBuilder()
            .append(this.getUserId(), castOther.getUserId())
            .append(this.getVendorId(), castOther.getVendorId())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getUserId())
            .append(getVendorId())
            .toHashCode();
    }

}
