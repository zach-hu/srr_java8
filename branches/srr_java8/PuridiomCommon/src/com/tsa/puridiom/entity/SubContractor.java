package com.tsa.puridiom.entity;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.tsa.puridiom.common.utility.HiltonUtility;

/** @author Hibernate CodeGenerator */
public class SubContractor implements Serializable {

    /** identifier field */
    private com.tsa.puridiom.entity.SubContractorPK comp_id;

    /** nullable persistent field */
    private String contractno;

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
    private String contactName;

    /** nullable persistent field */
    private String contactEmail;

    /** nullable persistent field */
    private String contactPhone;

    /** nullable persistent field */
    private String contactFax;

    /** nullable persistent field */
    private java.util.Date dateEntered;

    /** nullable persistent field */
    private java.util.Date dateExpires;

    /** nullable persistent field */
    private String status;

    /** nullable persistent field */
    private String continfo;

    /** nullable persistent field */
    private String owner;

    /** nullable persistent field */
    private String affiliateId;
    
    /** full constructor */
    public SubContractor(com.tsa.puridiom.entity.SubContractorPK comp_id, java.lang.String contractno, java.lang.String addressLine1, java.lang.String addressLine2, java.lang.String addressLine3, java.lang.String addressLine4, java.lang.String city, java.lang.String state, java.lang.String postalCode, java.lang.String country, java.lang.String contactName, java.lang.String contactEmail, java.lang.String contactPhone, java.lang.String contactFax, java.util.Date dateEntered, java.util.Date dateExpires, java.lang.String status, java.lang.String continfo, java.lang.String owner, java.lang.String affiliateId) {
        this.comp_id = comp_id;
        this.contractno = contractno;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.addressLine3 = addressLine3;
        this.addressLine4 = addressLine4;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
        this.country = country;
        this.contactName = contactName;
        this.contactEmail = contactEmail;
        this.contactPhone = contactPhone;
        this.contactFax = contactFax;
        this.dateEntered = dateEntered;
        this.dateExpires = dateExpires;
        this.status = status;
        this.continfo = continfo;
        this.owner = owner;
        this.affiliateId = affiliateId;
    }

    /** default constructor */
    public SubContractor() {
    }

    /** minimal constructor */
    public SubContractor(com.tsa.puridiom.entity.SubContractorPK comp_id) {
        this.comp_id = comp_id;
    }

    public com.tsa.puridiom.entity.SubContractorPK getComp_id() {
        return this.comp_id;
    }

    public void setComp_id(com.tsa.puridiom.entity.SubContractorPK comp_id) {
        this.comp_id = comp_id;
    }

    public java.lang.String getContractno() {
        return this.contractno;
    }

    public void setContractno(java.lang.String contractno) {
        this.contractno = contractno;
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

    public java.lang.String getContactName() {
    	return (java.lang.String)HiltonUtility.ckNull(this.contactName).trim();
    }

    public void setContactName(java.lang.String contactName) {
        this.contactName = contactName;
    }

    public java.lang.String getContactEmail() {
    	return (java.lang.String)HiltonUtility.ckNull(this.contactEmail).trim();
    }

    public void setContactEmail(java.lang.String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public java.lang.String getContactPhone() {
    	return (java.lang.String)HiltonUtility.ckNull(this.contactPhone).trim();
    }

    public void setContactPhone(java.lang.String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public java.lang.String getContactFax() {
    	return (java.lang.String)HiltonUtility.ckNull(this.contactFax).trim();
    }

    public void setContactFax(java.lang.String contactFax) {
        this.contactFax = contactFax;
    }

    public java.util.Date getDateEntered() {
        return this.dateEntered;
    }

    public void setDateEntered(java.util.Date dateEntered) {
        this.dateEntered = dateEntered;
    }

    public java.util.Date getDateExpires() {
        return this.dateExpires;
    }

    public void setDateExpires(java.util.Date dateExpires) {
        this.dateExpires = dateExpires;
    }

    public java.lang.String getStatus() {
    	return (java.lang.String)HiltonUtility.ckNull(this.status).trim();
    }

    public void setStatus(java.lang.String status) {
        this.status = status;
    }

    public java.lang.String getContinfo() {
    	return (java.lang.String)HiltonUtility.ckNull(this.continfo).trim();
    }

    public void setContinfo(java.lang.String continfo) {
        this.continfo = continfo;
    }

    public java.lang.String getOwner() {
    	return (java.lang.String)HiltonUtility.ckNull(this.owner).trim();
    }

    public void setOwner(java.lang.String owner) {
        this.owner = owner;
    }

    public java.lang.String getAffiliateId() {
    	return (java.lang.String)HiltonUtility.ckNull(this.affiliateId).trim();
    }

    public void setAffiliateId(java.lang.String affiliateId) {
        this.affiliateId = affiliateId;
    }    
    

    public String toString() {
        return new ToStringBuilder(this)
            .append("comp_id", getComp_id())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof SubContractor) ) return false;
        SubContractor castOther = (SubContractor) other;
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
