package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class RecentVendor implements Serializable {

    /** identifier field */
    private com.tsa.puridiom.entity.RecentVendorPK comp_id;

    /** nullable persistent field */
    private String vendorName;

    /** nullable persistent field */
    private String addressCode;

    /** nullable persistent field */
    private String contactCode;

    /** nullable persistent field */
    private java.util.Date dateEntered;

    /** full constructor */
    public RecentVendor(com.tsa.puridiom.entity.RecentVendorPK comp_id, java.lang.String vendorName, java.lang.String addressCode, java.lang.String contactCode, java.util.Date dateEntered) {
        this.comp_id = comp_id;
        this.vendorName = vendorName;
        this.addressCode = addressCode;
        this.contactCode = contactCode;
        this.dateEntered = dateEntered;
    }

    /** default constructor */
    public RecentVendor() {
    }

    /** minimal constructor */
    public RecentVendor(com.tsa.puridiom.entity.RecentVendorPK comp_id) {
        this.comp_id = comp_id;
    }

    public com.tsa.puridiom.entity.RecentVendorPK getComp_id() {
        return this.comp_id;
    }

    public void setComp_id(com.tsa.puridiom.entity.RecentVendorPK comp_id) {
        this.comp_id = comp_id;
    }

    public java.lang.String getVendorName() {
		return (java.lang.String)HiltonUtility.ckNull(this.vendorName).trim();
    }

    public void setVendorName(java.lang.String vendorName) {
		if (!HiltonUtility.isEmpty(vendorName) && vendorName.length() > 40) {
			vendorName = vendorName.substring(0, 40);
		}
		this.vendorName = vendorName;
    }

    public java.lang.String getAddressCode() {
		return (java.lang.String)HiltonUtility.ckNull(this.addressCode).trim();
    }

    public void setAddressCode(java.lang.String addressCode) {
		if (!HiltonUtility.isEmpty(addressCode) && addressCode.length() > 15) {
			addressCode = addressCode.substring(0, 15);
		}
		this.addressCode = addressCode;
    }

    public java.lang.String getContactCode() {
		return (java.lang.String)HiltonUtility.ckNull(this.contactCode).trim();
    }

    public void setContactCode(java.lang.String contactCode) {
		if (!HiltonUtility.isEmpty(contactCode) && contactCode.length() > 15) {
			contactCode = contactCode.substring(0, 15);
		}
		this.contactCode = contactCode;
    }

    public java.util.Date getDateEntered() {
        return this.dateEntered;
    }

    public void setDateEntered(java.util.Date dateEntered) {
        this.dateEntered = dateEntered;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("comp_id", getComp_id())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof RecentVendor) ) return false;
        RecentVendor castOther = (RecentVendor) other;
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
