package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class AccountQxref implements Serializable {

    /** identifier field */
    private com.tsa.puridiom.entity.AccountQxrefPK comp_id;

    /** nullable persistent field */
    private String wellFacility;

    /** nullable persistent field */
    private String costCenter;

    /** full constructor */
    public AccountQxref(com.tsa.puridiom.entity.AccountQxrefPK comp_id, java.lang.String wellFacility, java.lang.String costCenter) {
        this.comp_id = comp_id;
        this.wellFacility = wellFacility;
        this.costCenter = costCenter;
    }

    /** default constructor */
    public AccountQxref() {
    }

    /** minimal constructor */
    public AccountQxref(com.tsa.puridiom.entity.AccountQxrefPK comp_id) {
        this.comp_id = comp_id;
    }

    public com.tsa.puridiom.entity.AccountQxrefPK getComp_id() {
        return this.comp_id;
    }

    public void setComp_id(com.tsa.puridiom.entity.AccountQxrefPK comp_id) {
        this.comp_id = comp_id;
    }

    public java.lang.String getWellFacility() {
		return (java.lang.String)HiltonUtility.ckNull(this.wellFacility).trim();
    }

    public void setWellFacility(java.lang.String wellFacility) {
		if (!HiltonUtility.isEmpty(wellFacility) && wellFacility.length() > 15) {
			wellFacility = wellFacility.substring(0, 15);
		}
		this.wellFacility = wellFacility;
    }

    public java.lang.String getCostCenter() {
		return (java.lang.String)HiltonUtility.ckNull(this.costCenter).trim();
    }

    public void setCostCenter(java.lang.String costCenter) {
		if (!HiltonUtility.isEmpty(costCenter) && costCenter.length() > 15) {
			costCenter = costCenter.substring(0, 15);
		}
		this.costCenter = costCenter;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("comp_id", getComp_id())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof AccountQxref) ) return false;
        AccountQxref castOther = (AccountQxref) other;
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
