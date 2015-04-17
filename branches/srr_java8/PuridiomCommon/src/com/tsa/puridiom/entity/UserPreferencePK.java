package com.tsa.puridiom.entity;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class UserPreferencePK implements Serializable {

    /** identifier field */
    private String userId;

    /** identifier field */
    private String property;

    /** full constructor */
    public UserPreferencePK(java.lang.String userId, java.lang.String property) {
        this.userId = userId;
        this.property = property;
    }

    /** default constructor */
    public UserPreferencePK() {
    }

    public java.lang.String getUserId() {
        return this.userId;
    }

    public void setUserId(java.lang.String userId) {
        this.userId = userId;
    }

    public java.lang.String getProperty() {
        return this.property;
    }

    public void setProperty(java.lang.String property) {
        this.property = property;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("userId", getUserId())
            .append("property", getProperty())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof UserPreferencePK) ) return false;
        UserPreferencePK castOther = (UserPreferencePK) other;
        return new EqualsBuilder()
            .append(this.getUserId(), castOther.getUserId())
            .append(this.getProperty(), castOther.getProperty())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getUserId())
            .append(getProperty())
            .toHashCode();
    }

}
