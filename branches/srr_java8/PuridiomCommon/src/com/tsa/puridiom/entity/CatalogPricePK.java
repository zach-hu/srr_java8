package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class CatalogPricePK implements Serializable {

    /** identifier field */
    private String itemNumber;

    /** identifier field */
    private String vendorId;

    /** full constructor */
    public CatalogPricePK(java.lang.String itemNumber, java.lang.String vendorId) {
        this.itemNumber = itemNumber;
        this.vendorId = vendorId;
    }

    /** default constructor */
    public CatalogPricePK() {
    }

    public java.lang.String getItemNumber() {
		return this.itemNumber;
    }

    public void setItemNumber(java.lang.String itemNumber) {
        this.itemNumber = itemNumber;
    }

    public java.lang.String getVendorId() {
		return this.vendorId;
    }

    public void setVendorId(java.lang.String vendorId) {
        this.vendorId = vendorId;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("itemNumber", getItemNumber())
            .append("vendorId", getVendorId())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof CatalogPricePK) ) return false;
        CatalogPricePK castOther = (CatalogPricePK) other;
        return new EqualsBuilder()
            .append(this.getItemNumber(), castOther.getItemNumber())
            .append(this.getVendorId(), castOther.getVendorId())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getItemNumber())
            .append(getVendorId())
            .toHashCode();
    }

}
