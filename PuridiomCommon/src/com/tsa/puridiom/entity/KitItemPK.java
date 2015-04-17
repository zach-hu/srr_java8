package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class KitItemPK implements Serializable {

    /** identifier field */
    private String parentCatalogId;

    /** identifier field */
    private String parentItemNumber;

    /** identifier field */
    private String childCatalogId;

    /** identifier field */
    private String childItemNumber;

    /** full constructor */
    public KitItemPK(java.lang.String parentCatalogId, java.lang.String parentItemNumber, java.lang.String childCatalogId, java.lang.String childItemNumber) {
        this.parentCatalogId = parentCatalogId;
        this.parentItemNumber = parentItemNumber;
        this.childCatalogId = childCatalogId;
        this.childItemNumber = childItemNumber;
    }

    /** default constructor */
    public KitItemPK() {
    }

    public java.lang.String getParentCatalogId() {
		return (java.lang.String)HiltonUtility.ckNull(this.parentCatalogId);
    }

    public void setParentCatalogId(java.lang.String parentCatalogId) {
        this.parentCatalogId = parentCatalogId;
    }

    public java.lang.String getParentItemNumber() {
		return (java.lang.String)HiltonUtility.ckNull(this.parentItemNumber);
    }

    public void setParentItemNumber(java.lang.String parentItemNumber) {
        this.parentItemNumber = parentItemNumber;
    }

    public java.lang.String getChildCatalogId() {
		return (java.lang.String)HiltonUtility.ckNull(this.childCatalogId);
    }

    public void setChildCatalogId(java.lang.String childCatalogId) {
        this.childCatalogId = childCatalogId;
    }

    public java.lang.String getChildItemNumber() {
		return (java.lang.String)HiltonUtility.ckNull(this.childItemNumber);
    }

    public void setChildItemNumber(java.lang.String childItemNumber) {
        this.childItemNumber = childItemNumber;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("parentCatalogId", getParentCatalogId())
            .append("parentItemNumber", getParentItemNumber())
            .append("childCatalogId", getChildCatalogId())
            .append("childItemNumber", getChildItemNumber())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof KitItemPK) ) return false;
        KitItemPK castOther = (KitItemPK) other;
        return new EqualsBuilder()
            .append(this.getParentCatalogId(), castOther.getParentCatalogId())
            .append(this.getParentItemNumber(), castOther.getParentItemNumber())
            .append(this.getChildCatalogId(), castOther.getChildCatalogId())
            .append(this.getChildItemNumber(), castOther.getChildItemNumber())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getParentCatalogId())
            .append(getParentItemNumber())
            .append(getChildCatalogId())
            .append(getChildItemNumber())
            .toHashCode();
    }

}
