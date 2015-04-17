package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class VendorRegCommRelPK implements Serializable {

    /** identifier field */
    private String vendorId;

    /** identifier field */
    private String commodityCode;

    /** full constructor */
    public VendorRegCommRelPK(java.lang.String vendorId, java.lang.String commodityCode) {
        this.vendorId = vendorId;
        this.commodityCode = commodityCode;
    }

    /** default constructor */
    public VendorRegCommRelPK() {
    }

    public java.lang.String getVendorId() {
		return (java.lang.String)HiltonUtility.ckNull(this.vendorId);
    }

    public void setVendorId(java.lang.String vendorId) {
        this.vendorId = vendorId;
    }

    public java.lang.String getCommodityCode() {
		return (java.lang.String)HiltonUtility.ckNull(this.commodityCode);
    }

    public void setCommodityCode(java.lang.String commodityCode) {
        this.commodityCode = commodityCode;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("vendorId", getVendorId())
            .append("commodityCode", getCommodityCode())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof VendorRegCommRelPK) ) return false;
        VendorRegCommRelPK castOther = (VendorRegCommRelPK) other;
        return new EqualsBuilder()
            .append(this.getVendorId(), castOther.getVendorId())
            .append(this.getCommodityCode(), castOther.getCommodityCode())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getVendorId())
            .append(getCommodityCode())
            .toHashCode();
    }

}
