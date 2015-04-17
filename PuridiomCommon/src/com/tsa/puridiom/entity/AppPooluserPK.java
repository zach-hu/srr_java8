package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class AppPooluserPK implements Serializable {

    /** identifier field */
    private String poolid;

    /** identifier field */
    private String userId;

    /** full constructor */
    public AppPooluserPK(java.lang.String poolid, java.lang.String userId) {
        this.poolid = poolid;
        this.userId = userId;
    }

    /** default constructor */
    public AppPooluserPK() {
    }

    public java.lang.String getPoolid() {
		return (java.lang.String)HiltonUtility.ckNull(this.poolid);
    }

    public void setPoolid(java.lang.String poolid) {
        this.poolid = poolid;
    }

    public java.lang.String getUserId() {
		return (java.lang.String)HiltonUtility.ckNull(this.userId);
    }

    public void setUserId(java.lang.String userId) {
        this.userId = userId;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("poolid", getPoolid())
            .append("userId", getUserId())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof AppPooluserPK) ) return false;
        AppPooluserPK castOther = (AppPooluserPK) other;
        return new EqualsBuilder()
            .append(this.getPoolid(), castOther.getPoolid())
            .append(this.getUserId(), castOther.getUserId())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getPoolid())
            .append(getUserId())
            .toHashCode();
    }

}
