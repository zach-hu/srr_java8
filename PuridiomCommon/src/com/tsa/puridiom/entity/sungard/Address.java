package com.tsa.puridiom.entity.sungard;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class Address implements Serializable {

    /** nullable persistent field */
    private java.math.BigDecimal internalVendorId;

    /** nullable persistent field */
    private java.math.BigDecimal associatedAddrId;

    /** nullable persistent field */
    private String addressTypeInd;

    /** nullable persistent field */
    private String defaultInd;

    /** nullable persistent field */
    private String state;

    /** nullable persistent field */
    private String zipcode;

    /** nullable persistent field */
    private String countryCode;

    /** nullable persistent field */
    private String genericName2;

    /** nullable persistent field */
    private String genericName3;

    /** nullable persistent field */
    private String address1;

    /** nullable persistent field */
    private String address2;

    /** nullable persistent field */
    private String address3;

    /** nullable persistent field */
    private String city;

    /** full constructor */
    public Address(java.math.BigDecimal internalVendorId, java.math.BigDecimal associatedAddrId, java.lang.String addressTypeInd, java.lang.String defaultInd, java.lang.String state, java.lang.String zipcode, java.lang.String countryCode, java.lang.String genericName2, java.lang.String genericName3, java.lang.String address1, java.lang.String address2, java.lang.String address3, java.lang.String city) {
        this.internalVendorId = internalVendorId;
        this.associatedAddrId = associatedAddrId;
        this.addressTypeInd = addressTypeInd;
        this.defaultInd = defaultInd;
        this.state = state;
        this.zipcode = zipcode;
        this.countryCode = countryCode;
        this.genericName2 = genericName2;
        this.genericName3 = genericName3;
        this.address1 = address1;
        this.address2 = address2;
        this.address3 = address3;
        this.city = city;
    }

    /** default constructor */
    public Address() {
    }

    public java.math.BigDecimal getInternalVendorId() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.internalVendorId);
    }

    public void setInternalVendorId(java.math.BigDecimal internalVendorId) {
        this.internalVendorId = internalVendorId;
    }

    public java.math.BigDecimal getAssociatedAddrId() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.associatedAddrId);
    }

    public void setAssociatedAddrId(java.math.BigDecimal associatedAddrId) {
        this.associatedAddrId = associatedAddrId;
    }

    public java.lang.String getAddressTypeInd() {
		return (java.lang.String)HiltonUtility.ckNull(this.addressTypeInd).trim();
    }

    public void setAddressTypeInd(java.lang.String addressTypeInd) {
		if (!HiltonUtility.isEmpty(addressTypeInd) && addressTypeInd.length() > 1) {
			addressTypeInd = addressTypeInd.substring(0, 1);
		}
		this.addressTypeInd = addressTypeInd;
    }

    public java.lang.String getDefaultInd() {
		return (java.lang.String)HiltonUtility.ckNull(this.defaultInd).trim();
    }

    public void setDefaultInd(java.lang.String defaultInd) {
		if (!HiltonUtility.isEmpty(defaultInd) && defaultInd.length() > 1) {
			defaultInd = defaultInd.substring(0, 1);
		}
		this.defaultInd = defaultInd;
    }

    public java.lang.String getState() {
		return (java.lang.String)HiltonUtility.ckNull(this.state).trim();
    }

    public void setState(java.lang.String state) {
		if (!HiltonUtility.isEmpty(state) && state.length() > 2) {
			state = state.substring(0, 2);
		}
		this.state = state;
    }

    public java.lang.String getZipcode() {
		return (java.lang.String)HiltonUtility.ckNull(this.zipcode).trim();
    }

    public void setZipcode(java.lang.String zipcode) {
		if (!HiltonUtility.isEmpty(zipcode) && zipcode.length() > 10) {
			zipcode = zipcode.substring(0, 10);
		}
		this.zipcode = zipcode;
    }

    public java.lang.String getCountryCode() {
		return (java.lang.String)HiltonUtility.ckNull(this.countryCode).trim();
    }

    public void setCountryCode(java.lang.String countryCode) {
		if (!HiltonUtility.isEmpty(countryCode) && countryCode.length() > 2) {
			countryCode = countryCode.substring(0, 2);
		}
		this.countryCode = countryCode;
    }

    public java.lang.String getGenericName2() {
		return (java.lang.String)HiltonUtility.ckNull(this.genericName2).trim();
    }

    public void setGenericName2(java.lang.String genericName2) {
		if (!HiltonUtility.isEmpty(genericName2) && genericName2.length() > 60) {
			genericName2 = genericName2.substring(0, 60);
		}
		this.genericName2 = genericName2;
    }

    public java.lang.String getGenericName3() {
		return (java.lang.String)HiltonUtility.ckNull(this.genericName3).trim();
    }

    public void setGenericName3(java.lang.String genericName3) {
		if (!HiltonUtility.isEmpty(genericName3) && genericName3.length() > 60) {
			genericName3 = genericName3.substring(0, 60);
		}
		this.genericName3 = genericName3;
    }

    public java.lang.String getAddress1() {
		return (java.lang.String)HiltonUtility.ckNull(this.address1).trim();
    }

    public void setAddress1(java.lang.String address1) {
		if (!HiltonUtility.isEmpty(address1) && address1.length() > 60) {
			address1 = address1.substring(0, 60);
		}
		this.address1 = address1;
    }

    public java.lang.String getAddress2() {
		return (java.lang.String)HiltonUtility.ckNull(this.address2).trim();
    }

    public void setAddress2(java.lang.String address2) {
		if (!HiltonUtility.isEmpty(address2) && address2.length() > 60) {
			address2 = address2.substring(0, 60);
		}
		this.address2 = address2;
    }

    public java.lang.String getAddress3() {
		return (java.lang.String)HiltonUtility.ckNull(this.address3).trim();
    }

    public void setAddress3(java.lang.String address3) {
		if (!HiltonUtility.isEmpty(address3) && address3.length() > 60) {
			address3 = address3.substring(0, 60);
		}
		this.address3 = address3;
    }

    public java.lang.String getCity() {
		return (java.lang.String)HiltonUtility.ckNull(this.city).trim();
    }

    public void setCity(java.lang.String city) {
		if (!HiltonUtility.isEmpty(city) && city.length() > 20) {
			city = city.substring(0, 20);
		}
		this.city = city;
    }

    public String toString() {
        return new ToStringBuilder(this)
        .append("internalVendorId", getInternalVendorId())
        .append("associatedAddrId", getAssociatedAddrId())
        .toString();
    }

}
