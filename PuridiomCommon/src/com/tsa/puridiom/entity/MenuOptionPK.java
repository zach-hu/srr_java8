package com.tsa.puridiom.entity;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class MenuOptionPK implements Serializable {

    /** identifier field */
    private String userType;

    /** identifier field */
    private String alias;

    /** full constructor */
    public MenuOptionPK(java.lang.String userType, java.lang.String alias) {
        this.userType = userType;
        this.alias = alias;
    }

    /** default constructor */
    public MenuOptionPK() {
    }

    public java.lang.String getUserType() {
        return this.userType;
    }

    public void setUserType(java.lang.String userType) {
        this.userType = userType;
    }

    public java.lang.String getAlias() {
        return this.alias;
    }

    public void setAlias(java.lang.String alias) {
        this.alias = alias;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("userType", getUserType())
            .append("alias", getAlias())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof MenuOptionPK) ) return false;
        MenuOptionPK castOther = (MenuOptionPK) other;
        return new EqualsBuilder()
            .append(this.getUserType(), castOther.getUserType())
            .append(this.getAlias(), castOther.getAlias())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getUserType())
            .append(getAlias())
            .toHashCode();
    }

}
