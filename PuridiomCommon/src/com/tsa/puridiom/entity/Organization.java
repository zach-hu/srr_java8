package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class Organization implements Serializable {

    /** identifier field */
	private String organizationId;

    /** nullable persistent field */
    private java.util.Date dateExpires;

    /** nullable persistent field */
	private String organizationName;

	/** nullable persistent field */
	private String status;

    /** full constructor */
    public Organization( java.lang.String organizationId, java.util.Date dateExpires, java.lang.String organizationName, java.lang.String status) {
    	this.organizationId = organizationId;
        this.dateExpires = dateExpires;
        this.organizationName = organizationName;
        this.status = status;
    }

    /** default constructor */
    public Organization() {
    }

    /** minimal constructor */
    public Organization(java.lang.String organizationId) {
		if (!HiltonUtility.isEmpty(organizationId) && organizationId.length() > 15) {
		    organizationId = organizationId.substring(0, 15);
		}
        this.organizationId = organizationId;
    }

    public java.lang.String getOrganizationId() {
		return (java.lang.String)HiltonUtility.ckNull(this.organizationId);
    }

    public void setOrganizationId(java.lang.String organizationId) {
    	this.organizationId = organizationId;
    }

    public java.util.Date getDateExpires() {
		return this.dateExpires;
    }

    public void setDateExpires(java.util.Date dateExpires) {
        this.dateExpires = dateExpires;
    }

    public java.lang.String getOrganizationName() {
		return (java.lang.String)HiltonUtility.ckNull(this.organizationName);
    }

    public void setOrganizationName(java.lang.String organizationName) {
		if (!HiltonUtility.isEmpty(organizationName) && organizationName.length() > 60) {
		    organizationName = organizationName.substring(0, 60);
		}
    	this.organizationName = organizationName;
    }

    public java.lang.String getStatus() {
		return (java.lang.String)HiltonUtility.ckNull(this.status).trim();
    }

    public void setStatus(java.lang.String status) {
		if (!HiltonUtility.isEmpty(status) && status.length() > 4) {
			status = status.substring(0, 4);
		}
		this.status = status;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("organizationId", getOrganizationId())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof Organization) ) return false;
        Organization castOther = (Organization) other;
        return new EqualsBuilder()
            .append(this.getOrganizationId(), castOther.getOrganizationId())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getOrganizationId())
            .toHashCode();
    }

}
