package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsagate.foundation.utility.Utility;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author KC */
public class RecentAccountPK implements Serializable {

    /** identifier field */
    private java.lang.String userId;

    /** identifier field */
    private java.lang.String accountString;

    /** full constructor */
    public RecentAccountPK(java.lang.String userId, java.lang.String accountString) {
        this.userId = userId;
        this.accountString = accountString;
    }

    /** default constructor */
    public RecentAccountPK() {
    }

    public java.lang.String getUserId() {
		return (java.lang.String)Utility.ckNull(this.userId);
    }

    public void setUserId(java.lang.String userId) {
        this.userId = userId;
    }

    public java.lang.String getAccountString() {
		return (java.lang.String)Utility.ckNull(this.accountString);
    }

    public void setAccountString(java.lang.String accountString) {
        this.accountString = accountString;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("userId", getUserId())
            .append("accountString", getAccountString())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof RecentAccountPK) ) return false;
        RecentAccountPK castOther = (RecentAccountPK) other;
        return new EqualsBuilder()
            .append(this.getUserId(), castOther.getUserId())
            .append(this.getAccountString(), castOther.getAccountString())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getUserId())
            .append(getAccountString())
            .toHashCode();
    }

}
