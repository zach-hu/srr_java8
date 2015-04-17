package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class ConsolidatedVendor implements Serializable {

    /** identifier field */
    private String vendorId;

    /** nullable persistent field */
    private String vendorName;

    /** nullable persistent field */
    private String addressLine1;

    /** nullable persistent field */
    private String addressLine2;

    /** nullable persistent field */
    private String addressLine3;

    /** nullable persistent field */
    private String addressLine4;

    /** nullable persistent field */
    private String city;

    /** nullable persistent field */
    private String state;

    /** nullable persistent field */
    private String postalCode;

    /** nullable persistent field */
    private String country;

    /** nullable persistent field */
    private String apReference;

    /** nullable persistent field */
    private String addressCode;

    /** full constructor */
    public ConsolidatedVendor(java.lang.String vendorId, java.lang.String vendorName, java.lang.String addressLine1, java.lang.String addressLine2, java.lang.String addressLine3, java.lang.String addressLine4, java.lang.String city, java.lang.String state, java.lang.String postalCode, java.lang.String country, java.lang.String apReference, java.lang.String addressCode) {
        this.vendorId = vendorId;
        this.vendorName = vendorName;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.addressLine3 = addressLine3;
        this.addressLine4 = addressLine4;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
        this.country = country;
        this.apReference = apReference;
        this.addressCode = addressCode;
    }

    /** default constructor */
    public ConsolidatedVendor() {
    }

    /** minimal constructor */
    public ConsolidatedVendor(java.lang.String vendorId) {
        this.vendorId = vendorId;
    }

    public java.lang.String getVendorId() {
		return (java.lang.String)HiltonUtility.ckNull(this.vendorId).trim();
    }

    public void setVendorId(java.lang.String vendorId) {
        this.vendorId = vendorId;
    }

    public java.lang.String getVendorName() {
		return (java.lang.String)HiltonUtility.ckNull(this.vendorName).trim();
    }

    public void setVendorName(java.lang.String vendorName) {
        this.vendorName = vendorName;
    }

    public java.lang.String getAddressLine1() {
		return (java.lang.String)HiltonUtility.ckNull(this.addressLine1).trim();
    }

    public void setAddressLine1(java.lang.String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public java.lang.String getAddressLine2() {
		return (java.lang.String)HiltonUtility.ckNull(this.addressLine2).trim();
    }

    public void setAddressLine2(java.lang.String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public java.lang.String getAddressLine3() {
		return (java.lang.String)HiltonUtility.ckNull(this.addressLine3).trim();
    }

    public void setAddressLine3(java.lang.String addressLine3) {
        this.addressLine3 = addressLine3;
    }

    public java.lang.String getAddressLine4() {
		return (java.lang.String)HiltonUtility.ckNull(this.addressLine4).trim();
    }

    public void setAddressLine4(java.lang.String addressLine4) {
        this.addressLine4 = addressLine4;
    }

    public java.lang.String getCity() {
		return (java.lang.String)HiltonUtility.ckNull(this.city).trim();
    }

    public void setCity(java.lang.String city) {
        this.city = city;
    }

    public java.lang.String getState() {
		return (java.lang.String)HiltonUtility.ckNull(this.state).trim();
    }

    public void setState(java.lang.String state) {
        this.state = state;
    }

    public java.lang.String getPostalCode() {
		return (java.lang.String)HiltonUtility.ckNull(this.postalCode).trim();
    }

    public void setPostalCode(java.lang.String postalCode) {
        this.postalCode = postalCode;
    }

    public java.lang.String getCountry() {
		return (java.lang.String)HiltonUtility.ckNull(this.country).trim();
    }

    public void setCountry(java.lang.String country) {
        this.country = country;
    }

    public java.lang.String getApReference() {
    	return (java.lang.String)HiltonUtility.ckNull(this.apReference).trim();
    }

    public void setApReference(java.lang.String apReference) {
    	this.apReference = apReference;
    }

    public java.lang.String getAddressCode() {
    	return (java.lang.String)HiltonUtility.ckNull(this.addressCode).trim();
    }

    public void setAddressCode(java.lang.String addressCode) {
    	this.addressCode = addressCode;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("vendorId", getVendorId())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof InvoiceVendor) ) return false;
        InvoiceVendor castOther = (InvoiceVendor) other;
        return new EqualsBuilder()
            .append(this.getVendorId(), castOther.getVendorId())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getVendorId())
            .toHashCode();
    }

}
