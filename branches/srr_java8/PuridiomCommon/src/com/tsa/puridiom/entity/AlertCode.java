package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class AlertCode implements Serializable {

    /** identifier field */
    private String refType;

    /** nullable persistent field */
    private String refDescription;

    /** full constructor */
    public AlertCode(java.lang.String refType, java.lang.String refDescription) {
        this.refType = refType;
        this.refDescription = refDescription;
    }

    /** default constructor */
    public AlertCode() {
    }

    /** minimal constructor */
    public AlertCode(java.lang.String refType) {
        this.refType = refType;
    }

    public java.lang.String getRefType() {
		return (java.lang.String)HiltonUtility.ckNull(this.refType).trim();
    }

    public void setRefType(java.lang.String refType) {
		if (!HiltonUtility.isEmpty(refType) && refType.length() > 3) {
			refType = refType.substring(0, 3);
		}
		this.refType = refType;
    }

    public java.lang.String getRefDescription() {
		return (java.lang.String)HiltonUtility.ckNull(this.refDescription).trim();
    }

    public void setRefDescription(java.lang.String refDescription) {
		if (!HiltonUtility.isEmpty(refDescription) && refDescription.length() > 60) {
			refDescription = refDescription.substring(0, 60);
		}
		this.refDescription = refDescription;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("refType", getRefType())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof AlertCode) ) return false;
        AlertCode castOther = (AlertCode) other;
        return new EqualsBuilder()
            .append(this.getRefType(), castOther.getRefType())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getRefType())
            .toHashCode();
    }

}
