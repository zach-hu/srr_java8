package com.tsa.puridiom.entity;

import com.tsagate.foundation.database.Entity;
import com.tsagate.foundation.utility.Utility;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class AppRulePK extends Entity implements Serializable {

    /** identifier field */
    private String userId;

    /** identifier field */
    private String udf1Code;

    /** identifier field */
    private String udf2Code;

    /** identifier field */
    private String udf3Code;

    /** identifier field */
    private String udf4Code;

    /** identifier field */
    private String udf5Code;

    /** identifier field */
    private String udf6Code;

    /** identifier field */
    private String udf7Code;

    /** full constructor */
    public AppRulePK(java.lang.String userId, java.lang.String udf1Code, java.lang.String udf2Code, java.lang.String udf3Code, java.lang.String udf4Code, java.lang.String udf5Code, java.lang.String udf6Code, java.lang.String udf7Code) {
        this.userId = userId;
        this.udf1Code = udf1Code;
        this.udf2Code = udf2Code;
        this.udf3Code = udf3Code;
        this.udf4Code = udf4Code;
        this.udf5Code = udf5Code;
        this.udf6Code = udf6Code;
        this.udf7Code = udf7Code;
    }

    /** default constructor */
    public AppRulePK() {
    }

    public java.lang.String getUserId() {
		return (java.lang.String)Utility.ckNull(this.userId);
    }

    public void setUserId(java.lang.String userId) {
        this.userId = userId;
    }

    public java.lang.String getUdf1Code() {
		return (java.lang.String)Utility.ckNull(this.udf1Code);
    }

    public void setUdf1Code(java.lang.String udf1Code) {
        this.udf1Code = udf1Code;
    }

    public java.lang.String getUdf2Code() {
		return (java.lang.String)Utility.ckNull(this.udf2Code);
    }

    public void setUdf2Code(java.lang.String udf2Code) {
        this.udf2Code = udf2Code;
    }

    public java.lang.String getUdf3Code() {
		return (java.lang.String)Utility.ckNull(this.udf3Code);
    }

    public void setUdf3Code(java.lang.String udf3Code) {
        this.udf3Code = udf3Code;
    }

    public java.lang.String getUdf4Code() {
		return (java.lang.String)Utility.ckNull(this.udf4Code);
    }

    public void setUdf4Code(java.lang.String udf4Code) {
        this.udf4Code = udf4Code;
    }

    public java.lang.String getUdf5Code() {
		return (java.lang.String)Utility.ckNull(this.udf5Code);
    }

    public void setUdf5Code(java.lang.String udf5Code) {
        this.udf5Code = udf5Code;
    }

    public java.lang.String getUdf6Code() {
		return (java.lang.String)Utility.ckNull(this.udf6Code);
    }

    public void setUdf6Code(java.lang.String udf6Code) {
        this.udf6Code = udf6Code;
    }

    public java.lang.String getUdf7Code() {
		return (java.lang.String)Utility.ckNull(this.udf7Code);
    }

    public void setUdf7Code(java.lang.String udf7Code) {
        this.udf7Code = udf7Code;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("userId", getUserId())
            .append("udf1Code", getUdf1Code())
            .append("udf2Code", getUdf2Code())
            .append("udf3Code", getUdf3Code())
            .append("udf4Code", getUdf4Code())
            .append("udf5Code", getUdf5Code())
            .append("udf6Code", getUdf6Code())
            .append("udf7Code", getUdf7Code())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof AppRulePK) ) return false;
        AppRulePK castOther = (AppRulePK) other;
        return new EqualsBuilder()
            .append(this.getUserId(), castOther.getUserId())
            .append(this.getUdf1Code(), castOther.getUdf1Code())
            .append(this.getUdf2Code(), castOther.getUdf2Code())
            .append(this.getUdf3Code(), castOther.getUdf3Code())
            .append(this.getUdf4Code(), castOther.getUdf4Code())
            .append(this.getUdf5Code(), castOther.getUdf5Code())
            .append(this.getUdf6Code(), castOther.getUdf6Code())
            .append(this.getUdf7Code(), castOther.getUdf7Code())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getUserId())
            .append(getUdf1Code())
            .append(getUdf2Code())
            .append(getUdf3Code())
            .append(getUdf4Code())
            .append(getUdf5Code())
            .append(getUdf6Code())
            .append(getUdf7Code())
            .toHashCode();
    }

}
