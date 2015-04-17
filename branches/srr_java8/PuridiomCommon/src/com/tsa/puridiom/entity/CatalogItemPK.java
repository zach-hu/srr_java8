package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class CatalogItemPK implements Serializable {

    /** identifier field */
    private String catalogId;

    /** identifier field */
    private String itemNumber;

    /** full constructor */
    public CatalogItemPK(java.lang.String catalogId, java.lang.String itemNumber) {
        this.catalogId = catalogId;
        this.itemNumber = itemNumber;
    }

    /** default constructor */
    public CatalogItemPK() {
    }

    public java.lang.String getCatalogId() {
		return (java.lang.String)HiltonUtility.ckNull(this.catalogId);
    }

    public void setCatalogId(java.lang.String catalogId) {
        this.catalogId = catalogId;
    }

    public java.lang.String getItemNumber() {
		return (java.lang.String)HiltonUtility.ckNull(this.itemNumber);
    }

    public void setItemNumber(java.lang.String itemNumber) {
        this.itemNumber = itemNumber;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("catalogId", getCatalogId())
            .append("itemNumber", getItemNumber())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof CatalogItemPK) ) return false;
        CatalogItemPK castOther = (CatalogItemPK) other;
        return new EqualsBuilder()
            .append(this.getCatalogId(), castOther.getCatalogId())
            .append(this.getItemNumber(), castOther.getItemNumber())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getCatalogId())
            .append(getItemNumber())
            .toHashCode();
    }

}
