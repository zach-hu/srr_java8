package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author kathleen */
public class VendorAccount implements Serializable {

    /** identifier field */
    private com.tsa.puridiom.entity.VendorAccountPK comp_id;

    /** nullable persistent field */
    private String description;

    /** full constructor */
    public VendorAccount(com.tsa.puridiom.entity.VendorAccountPK comp_id, java.lang.String description) {
        this.comp_id = comp_id;
        this.description = description;
    }

    /** default constructor */
    public VendorAccount() {
    }

    /** minimal constructor */
    public VendorAccount(com.tsa.puridiom.entity.VendorAccountPK comp_id) {
        this.comp_id = comp_id;
    }

    public com.tsa.puridiom.entity.VendorAccountPK getComp_id() {
        return this.comp_id;
    }

    public void setComp_id(com.tsa.puridiom.entity.VendorAccountPK comp_id) {
        this.comp_id = comp_id;
    }

    public java.lang.String getDescription() {
		return (java.lang.String)HiltonUtility.ckNull(this.description).trim();
    }

    public void setDescription(java.lang.String description) {
		if (!HiltonUtility.isEmpty(description) && description.length() > 60) {
			description = description.substring(0, 60);
		}
		this.description = description;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("comp_id", getComp_id())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof VendorAccount) ) return false;
        VendorAccount castOther = (VendorAccount) other;
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
