package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class CatalogSitelistPK implements Serializable {

    /** identifier field */
    private String vendorId;

    /** identifier field */
    private String catalogType;

    /** full constructor */
    public CatalogSitelistPK(java.lang.String vendorId, java.lang.String catalogType) {
        this.vendorId = vendorId;
        this.catalogType = catalogType;
    }

    /** default constructor */
    public CatalogSitelistPK() {
    }

    public java.lang.String getVendorId() {
		return this.vendorId;
    }

    public void setVendorId(java.lang.String vendorId) {
        this.vendorId = vendorId;
    }

    public java.lang.String getCatalogType() {
		return this.catalogType;
    }

    public void setCatalogType(java.lang.String catalogType) {
        this.catalogType = catalogType;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("vendorId", getVendorId())
            .append("catalogType", getCatalogType())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof CatalogSitelistPK) ) return false;
        CatalogSitelistPK castOther = (CatalogSitelistPK) other;
        return new EqualsBuilder()
            .append(this.getVendorId(), castOther.getVendorId())
            .append(this.getCatalogType(), castOther.getCatalogType())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getVendorId())
            .append(getCatalogType())
            .toHashCode();
    }

}
