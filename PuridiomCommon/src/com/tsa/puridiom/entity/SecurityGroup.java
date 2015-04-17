package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class SecurityGroup implements Serializable {

    /** identifier field */
    private String groupId;

    /** nullable persistent field */
    private String groupDescription;

    /** nullable persistent field */
    private String owner;

    /** nullable persistent field */
    private String status;

    /** nullable persistent field */
    private java.util.Date dateEntered;

    /** nullable persistent field */
    private java.util.Date dateExpires;

    /** nullable persistent field */
    private String idlemin;

    /** full constructor */
    public SecurityGroup(java.lang.String groupId, java.lang.String groupDescription, java.lang.String owner, java.lang.String status, java.util.Date dateEntered, java.util.Date dateExpires, java.lang.String idlemin) {
        this.groupId = groupId;
        this.groupDescription = groupDescription;
        this.owner = owner;
        this.status = status;
        this.dateEntered = dateEntered;
        this.dateExpires = dateExpires;
        this.idlemin = idlemin;
    }

    /** default constructor */
    public SecurityGroup() {
    }

    /** minimal constructor */
    public SecurityGroup(java.lang.String groupId) {
        this.groupId = groupId;
    }

    public java.lang.String getGroupId() {
		return (java.lang.String)HiltonUtility.ckNull(this.groupId).trim();
    }

    public void setGroupId(java.lang.String groupId) {
		if (!HiltonUtility.isEmpty(groupId) && groupId.length() > 15) {
			groupId = groupId.substring(0, 15);
		}
		this.groupId = groupId;
    }

    public java.lang.String getGroupDescription() {
		return (java.lang.String)HiltonUtility.ckNull(this.groupDescription).trim();
    }

    public void setGroupDescription(java.lang.String groupDescription) {
		if (!HiltonUtility.isEmpty(groupDescription) && groupDescription.length() > 40) {
			groupDescription = groupDescription.substring(0, 40);
		}
		this.groupDescription = groupDescription;
    }

    public java.lang.String getOwner() {
		return (java.lang.String)HiltonUtility.ckNull(this.owner).trim();
    }

    public void setOwner(java.lang.String owner) {
		if (!HiltonUtility.isEmpty(owner) && owner.length() > 15) {
			owner = owner.substring(0, 15);
		}
		this.owner = owner;
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

    public java.util.Date getDateEntered() {
		return this.dateEntered;
//		return HiltonUtility.ckNull(this.dateEntered);
//		return (java.sql.Date)HiltonUtility.ckNull(this.dateEntered);
    }

    public void setDateEntered(java.util.Date dateEntered) {
        this.dateEntered = dateEntered;
    }

    public java.util.Date getDateExpires() {
		return this.dateExpires;
//		return HiltonUtility.ckNull(this.dateExpires);
//		return (java.sql.Date)HiltonUtility.ckNull(this.dateExpires);
    }

    public void setDateExpires(java.util.Date dateExpires) {
        this.dateExpires = dateExpires;
    }

    public java.lang.String getIdlemin() {
		return (java.lang.String)HiltonUtility.ckNull(this.idlemin).trim();
    }

    public void setIdlemin(java.lang.String idlemin) {
		if (!HiltonUtility.isEmpty(idlemin) && idlemin.length() > 4) {
			idlemin = idlemin.substring(0, 4);
		}
		this.idlemin = idlemin;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("groupId", getGroupId())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof SecurityGroup) ) return false;
        SecurityGroup castOther = (SecurityGroup) other;
        return new EqualsBuilder()
            .append(this.getGroupId(), castOther.getGroupId())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getGroupId())
            .toHashCode();
    }

}
