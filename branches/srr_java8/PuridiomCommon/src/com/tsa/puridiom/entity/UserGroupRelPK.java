package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class UserGroupRelPK implements Serializable {

    /** identifier field */
    private String groupId;

    /** identifier field */
    private String userId;

    /** full constructor */
    public UserGroupRelPK(java.lang.String groupId, java.lang.String userId) {
        this.groupId = groupId;
        this.userId = userId;
    }

    /** default constructor */
    public UserGroupRelPK() {
    }

    public java.lang.String getGroupId() {
		return (java.lang.String)HiltonUtility.ckNull(this.groupId);
    }

    public void setGroupId(java.lang.String groupId) {
        this.groupId = groupId;
    }

    public java.lang.String getUserId() {
		return (java.lang.String)HiltonUtility.ckNull(this.userId);
    }

    public void setUserId(java.lang.String userId) {
        this.userId = userId;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("groupId", getGroupId())
            .append("userId", getUserId())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof UserGroupRelPK) ) return false;
        UserGroupRelPK castOther = (UserGroupRelPK) other;
        return new EqualsBuilder()
            .append(this.getGroupId(), castOther.getGroupId())
            .append(this.getUserId(), castOther.getUserId())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getGroupId())
            .append(getUserId())
            .toHashCode();
    }

}
