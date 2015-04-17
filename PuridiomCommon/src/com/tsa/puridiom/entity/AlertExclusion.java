package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class AlertExclusion implements Serializable {

    /** identifier field */
    private com.tsa.puridiom.entity.AlertExclusionPK comp_id;

    /** nullable persistent field */
    private String refType;

    /** nullable persistent field */
    private String owner;

    /** nullable persistent field */
    private java.util.Date excludedDate;

    /** nullable persistent field */
    private String refNumber;

    /** full constructor */
    public AlertExclusion(com.tsa.puridiom.entity.AlertExclusionPK comp_id, java.lang.String refType, java.lang.String owner, java.util.Date excludedDate, java.lang.String refNumber) {
        this.comp_id = comp_id;
        this.refType = refType;
        this.owner = owner;
        this.excludedDate = excludedDate;
        this.refNumber = refNumber;
    }

    /** default constructor */
    public AlertExclusion() {
    }

    /** minimal constructor */
    public AlertExclusion(com.tsa.puridiom.entity.AlertExclusionPK comp_id) {
        this.comp_id = comp_id;
    }

    public com.tsa.puridiom.entity.AlertExclusionPK getComp_id() {
        return this.comp_id;
    }

    public void setComp_id(com.tsa.puridiom.entity.AlertExclusionPK comp_id) {
        this.comp_id = comp_id;
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

    public java.lang.String getOwner() {
		return (java.lang.String)HiltonUtility.ckNull(this.owner).trim();
    }

    public void setOwner(java.lang.String owner) {
		if (!HiltonUtility.isEmpty(owner) && owner.length() > 15) {
			owner = owner.substring(0, 15);
		}
		this.owner = owner;
    }

    public java.util.Date getExcludedDate() {
		return this.excludedDate;
//		return HiltonUtility.ckNull(this.excludedDate);
//		return (java.sql.Date)HiltonUtility.ckNull(this.excludedDate);
    }

    public void setExcludedDate(java.util.Date excludedDate) {
        this.excludedDate = excludedDate;
    }

    public java.lang.String getRefNumber() {
		return (java.lang.String)HiltonUtility.ckNull(this.refNumber).trim();
    }

    public void setRefNumber(java.lang.String refNumber) {
		if (!HiltonUtility.isEmpty(refNumber) && refNumber.length() > 20) {
			refNumber = refNumber.substring(0, 20);
		}
		this.refNumber = refNumber;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("comp_id", getComp_id())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof AlertExclusion) ) return false;
        AlertExclusion castOther = (AlertExclusion) other;
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
