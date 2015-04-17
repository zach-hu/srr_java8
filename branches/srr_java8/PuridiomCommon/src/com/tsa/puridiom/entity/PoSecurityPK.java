package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class PoSecurityPK implements Serializable {

    /** identifier field */
    private String poNumber;

    /** identifier field */
    private String accessType;

    /** identifier field */
    private String accessId;

    /** full constructor */
    public PoSecurityPK(java.lang.String poNumber, java.lang.String accessType, java.lang.String accessId) {
        this.poNumber = poNumber;
        this.accessType = accessType;
        this.accessId = accessId;
    }

    /** default constructor */
    public PoSecurityPK() {
    }

    public java.lang.String getPoNumber() {
		return (java.lang.String)HiltonUtility.ckNull(this.poNumber);
    }

    public void setPoNumber(java.lang.String poNumber) {
        this.poNumber = poNumber;
    }

    public java.lang.String getAccessType() {
		return (java.lang.String)HiltonUtility.ckNull(this.accessType);
    }

    public void setAccessType(java.lang.String accessType) {
        this.accessType = accessType;
    }

    public java.lang.String getAccessId() {
		return (java.lang.String)HiltonUtility.ckNull(this.accessId);
    }

    public void setAccessId(java.lang.String accessId) {
        this.accessId = accessId;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("poNumber", getPoNumber())
            .append("accessType", getAccessType())
            .append("accessId", getAccessId())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof PoSecurityPK) ) return false;
        PoSecurityPK castOther = (PoSecurityPK) other;
        return new EqualsBuilder()
            .append(this.getPoNumber(), castOther.getPoNumber())
            .append(this.getAccessType(), castOther.getAccessType())
            .append(this.getAccessId(), castOther.getAccessId())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getPoNumber())
            .append(getAccessType())
            .append(getAccessId())
            .toHashCode();
    }

}
