package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class VendorAffiliatePK implements Serializable {

    /** identifier field */
    private String vendorId;

    /** identifier field */
    private String affiliateId;

    /** full constructor */
    public VendorAffiliatePK(java.lang.String vendorId, java.lang.String affiliateId) {
        this.vendorId = vendorId;
        this.affiliateId = affiliateId;
    }

    /** default constructor */
    public VendorAffiliatePK() {
    }

    public java.lang.String getVendorId() {
		return (java.lang.String)HiltonUtility.ckNull(this.vendorId);
    }

    public void setVendorId(java.lang.String vendorId) {
        this.vendorId = vendorId;
    }

    public java.lang.String getAffiliateId() {
		return (java.lang.String)HiltonUtility.ckNull(this.affiliateId);
    }

    public void setAffiliateId(java.lang.String affiliateId) {
        this.affiliateId = affiliateId;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("vendorId", getVendorId())
            .append("affiliateId", getAffiliateId())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof VendorAffiliatePK) ) return false;
        VendorAffiliatePK castOther = (VendorAffiliatePK) other;
        return new EqualsBuilder()
            .append(this.getVendorId(), castOther.getVendorId())
            .append(this.getAffiliateId(), castOther.getAffiliateId())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getVendorId())
            .append(getAffiliateId())
            .toHashCode();
    }

}
