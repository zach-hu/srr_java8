package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class SecurityProfilePK implements Serializable {

    /** identifier field */
    private String profileType;

    /** identifier field */
    private String groupId;

    /** identifier field */
    private String profile;

    /** full constructor */
    public SecurityProfilePK(java.lang.String profileType, java.lang.String groupId, java.lang.String profile) {
        this.profileType = profileType;
        this.groupId = groupId;
        this.profile = profile;
    }

    /** default constructor */
    public SecurityProfilePK() {
    }

    public java.lang.String getProfileType() {
		return (java.lang.String)HiltonUtility.ckNull(this.profileType);
    }

    public void setProfileType(java.lang.String profileType) {
        this.profileType = profileType;
    }

    public java.lang.String getGroupId() {
		return (java.lang.String)HiltonUtility.ckNull(this.groupId);
    }

    public void setGroupId(java.lang.String groupId) {
        this.groupId = groupId;
    }

    public java.lang.String getProfile() {
		return (java.lang.String)HiltonUtility.ckNull(this.profile);
    }

    public void setProfile(java.lang.String profile) {
        this.profile = profile;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("profileType", getProfileType())
            .append("groupId", getGroupId())
            .append("profile", getProfile())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof SecurityProfilePK) ) return false;
        SecurityProfilePK castOther = (SecurityProfilePK) other;
        return new EqualsBuilder()
            .append(this.getProfileType(), castOther.getProfileType())
            .append(this.getGroupId(), castOther.getGroupId())
            .append(this.getProfile(), castOther.getProfile())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getProfileType())
            .append(getGroupId())
            .append(getProfile())
            .toHashCode();
    }

}
