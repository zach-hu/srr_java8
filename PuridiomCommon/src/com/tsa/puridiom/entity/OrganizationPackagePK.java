package com.tsa.puridiom.entity;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class OrganizationPackagePK implements Serializable {
	
    /** identifier field */
    private java.lang.String organizationId;

	/** identifier field */
    private java.math.BigDecimal icPackage;
    
    /** full constructor */
    public OrganizationPackagePK(java.lang.String organizationId, java.math.BigDecimal icPackage) {
        this.organizationId = organizationId;
        this.icPackage = icPackage;
    }

    /** default constructor */
    public OrganizationPackagePK() {
    }

    public java.lang.String getOrganizationId() {
        return this.organizationId;
    }

    public void setOrganizationId(java.lang.String organizationId) {
        this.organizationId = organizationId;
    }

    public java.math.BigDecimal getIcPackage() {
		return this.icPackage;
    }

    public void setIcPackage(java.math.BigDecimal icPackage) {
        this.icPackage = icPackage;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("icPackage: ", getIcPackage())
            .append("organizationId: ", getOrganizationId())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof OrganizationPackagePK) ) return false;
        OrganizationPackagePK castOther = (OrganizationPackagePK) other;
        return new EqualsBuilder()
            .append(this.getIcPackage(), castOther.getIcPackage())
            .append(this.getOrganizationId(), castOther.getOrganizationId())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getIcPackage())
            .append(getOrganizationId())
            .toHashCode();
    }

}