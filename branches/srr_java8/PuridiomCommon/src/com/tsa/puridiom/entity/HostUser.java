package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsagate.foundation.utility.Utility;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class HostUser implements Serializable {

    /** identifier field */
    private String mailId;
    
    /** nullable persistent field */
	private String userId;
	
	/** nullable persistent field */
	private String organizationId;
	
    /** full constructor */
    public HostUser( java.lang.String mailId, java.lang.String userId, java.lang.String organizationId) {
    	this.mailId = mailId;
    	this.userId = userId;
    	this.organizationId = organizationId;
    }

    /** default constructor */
    public HostUser() {
    }

    /** minimal constructor */
    public HostUser(java.lang.String mailId) {
        this.mailId = mailId;
    }

    public java.lang.String getMailId() {
		return (java.lang.String)Utility.ckNull(this.mailId);
    }

    public void setMailId(java.lang.String mailId) {
        if (!HiltonUtility.isEmpty(mailId)) {
            mailId = mailId.toLowerCase();
        }
    	this.mailId = mailId;
    }
    
    public java.lang.String getUserId() {
		return (java.lang.String)HiltonUtility.ckNull(this.userId);
    }

    public void setUserId(java.lang.String userId) {
        if (!HiltonUtility.isEmpty(userId)) {
            userId = userId.toUpperCase();
        }
    	this.userId = userId;
    }
    
    public java.lang.String getOrganizationId() {
		return (java.lang.String)HiltonUtility.ckNull(this.organizationId);
    }

    public void setOrganizationId(java.lang.String organizationId) {
    	this.organizationId = organizationId;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("mailId", getMailId())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof HostUser) ) return false;
        HostUser castOther = (HostUser) other;
        return new EqualsBuilder()
            .append(this.getMailId(), castOther.getMailId())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getMailId())
            .toHashCode();
    }

}
