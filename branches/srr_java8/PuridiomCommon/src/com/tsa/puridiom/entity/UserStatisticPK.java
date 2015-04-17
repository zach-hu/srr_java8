package com.tsa.puridiom.entity;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class UserStatisticPK implements Serializable {

    /** identifier field */
    private String userId;

    /** identifier field */
    private String statType;

    /** identifier field */
    private String statKey;

    /** identifier field */
    private String statYear;

    /** identifier field */
    private String statMonth;

    /** full constructor */
    public UserStatisticPK(java.lang.String userId, java.lang.String statType, java.lang.String statKey, java.lang.String statYear, java.lang.String statMonth) {
        this.userId = userId;
        this.statType = statType;
        this.statKey = statKey;
        this.statYear = statYear;
        this.statMonth = statMonth;
    }

    /** default constructor */
    public UserStatisticPK() {
    }

    public java.lang.String getUserId() {
        return this.userId;
    }

    public void setUserId(java.lang.String userId) {
        this.userId = userId;
    }

    public java.lang.String getStatType() {
        return this.statType;
    }

    public void setStatType(java.lang.String statType) {
        this.statType = statType;
    }

    public java.lang.String getStatKey() {
        return this.statKey;
    }

    public void setStatKey(java.lang.String statKey) {
        this.statKey = statKey;
    }

    public java.lang.String getStatYear() {
        return this.statYear;
    }

    public void setStatYear(java.lang.String statYear) {
        this.statYear = statYear;
    }

    public java.lang.String getStatMonth() {
        return this.statMonth;
    }

    public void setStatMonth(java.lang.String statMonth) {
        this.statMonth = statMonth;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("userId", getUserId())
            .append("statType", getStatType())
            .append("statKey", getStatKey())
            .append("statYear", getStatYear())
            .append("statMonth", getStatMonth())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof UserStatisticPK) ) return false;
        UserStatisticPK castOther = (UserStatisticPK) other;
        return new EqualsBuilder()
            .append(this.getUserId(), castOther.getUserId())
            .append(this.getStatType(), castOther.getStatType())
            .append(this.getStatKey(), castOther.getStatKey())
            .append(this.getStatYear(), castOther.getStatYear())
            .append(this.getStatMonth(), castOther.getStatMonth())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getUserId())
            .append(getStatType())
            .append(getStatKey())
            .append(getStatYear())
            .append(getStatMonth())
            .toHashCode();
    }

}
