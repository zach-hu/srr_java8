package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class SecurityProfile implements Serializable {

    /** identifier field */
    private com.tsa.puridiom.entity.SecurityProfilePK comp_id;

    /** nullable persistent field */
    private String flags;

    /** nullable persistent field */
    private java.math.BigDecimal accessValue;

    /** full constructor */
    public SecurityProfile(com.tsa.puridiom.entity.SecurityProfilePK comp_id, java.lang.String flags, java.math.BigDecimal accessValue) {
        this.comp_id = comp_id;
        this.flags = flags;
        this.accessValue = accessValue;
    }

    /** default constructor */
    public SecurityProfile() {
    }

    /** minimal constructor */
    public SecurityProfile(com.tsa.puridiom.entity.SecurityProfilePK comp_id) {
        this.comp_id = comp_id;
    }

    public com.tsa.puridiom.entity.SecurityProfilePK getComp_id() {
        return this.comp_id;
    }

    public void setComp_id(com.tsa.puridiom.entity.SecurityProfilePK comp_id) {
        this.comp_id = comp_id;
    }

    public java.lang.String getFlags() {
		return (java.lang.String)HiltonUtility.ckNull(this.flags).trim();
    }

    public void setFlags(java.lang.String flags) {
		if (!HiltonUtility.isEmpty(flags) && flags.length() > 15) {
			flags = flags.substring(0, 15);
		}
		this.flags = flags;
    }

    public java.math.BigDecimal getAccessValue() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.accessValue);
    }

    public void setAccessValue(java.math.BigDecimal accessValue) {
        this.accessValue = accessValue;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("comp_id", getComp_id())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof SecurityProfile) ) return false;
        SecurityProfile castOther = (SecurityProfile) other;
        return new EqualsBuilder()
            .append(this.getComp_id(), castOther.getComp_id())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getComp_id())
            .toHashCode();
    }

}
