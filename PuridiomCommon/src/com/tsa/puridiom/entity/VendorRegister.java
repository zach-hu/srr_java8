package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsagate.foundation.utility.Utility;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class VendorRegister implements Serializable {

    /** identifier field */
    private com.tsa.puridiom.entity.VendorRegisterPK comp_id;

    /** nullable persistent field */
    private String vendorName;

    /** nullable persistent field */
    private String vendorFaxNumber;

    /** nullable persistent field */
    private String vendorSic;

    /** nullable persistent field */
    private String vendorDuns;

    /** nullable persistent field */
    private String contactLastName;

    /** nullable persistent field */
    private String contactFirstName;

    /** nullable persistent field */
    private String contactMidInit;

    /** nullable persistent field */
    private String contactSalutation;

    /** nullable persistent field */
    private String contactTitle;

    /** nullable persistent field */
    private String contactPhoneNo;

    /** nullable persistent field */
    private String contactFaxNumber;
    
    /** nullable persistent field */
    private String contactMobileNumber;

    /** nullable persistent field */
    private String contactPassword;

    /** nullable persistent field */
    private String addressLine2;

    /** nullable persistent field */
    private String addressLine3;

    /** nullable persistent field */
    private String addressLine4;

    /** nullable persistent field */
    private String addressCity;

    /** nullable persistent field */
    private String addressState;

    /** nullable persistent field */
    private String addressZipCode;

    /** nullable persistent field */
    private String addressCountry;

    /** nullable persistent field */
    private String vendorUdf1;

    /** nullable persistent field */
    private String vendorUdf2;

    /** nullable persistent field */
    private String vendorUdf3;

    /** nullable persistent field */
    private String vendorUdf4;

    /** nullable persistent field */
    private String vendorUdf5;

    /** nullable persistent field */
    private String vendorUdf6;

    /** nullable persistent field */
    private String vendorUdf7;

    /** nullable persistent field */
    private String vendorUdf8;

    /** nullable persistent field */
    private String vendorUdf9;

    /** nullable persistent field */
    private String vendorUdf10;

    /** nullable persistent field */
    private String vendorClass;

    /** nullable persistent field */
    private String vendorWebAddress;

    /** nullable persistent field */
    private String vendorVendTerms;

    /** nullable persistent field */
    private String vendPaymentType;

    /** nullable persistent field */
    private java.math.BigDecimal vendorYears;

    /** nullable persistent field */
    private java.math.BigDecimal vendorLeadDays;

    /** nullable persistent field */
    private String vendorEin;

    /** nullable persistent field */
    private String vendorEdiVendor;

    /** nullable persistent field */
    private String vendorEdiAddress;

    /** nullable persistent field */
    private String vendorNotes;

    /** nullable persistent field */
    private String altLastName;

    /** nullable persistent field */
    private String altFirstName;

    /** nullable persistent field */
    private String altMidInit;

    /** nullable persistent field */
    private String altSalutation;

    /** nullable persistent field */
    private String altTitle;

    /** nullable persistent field */
    private String altEmailAddr;

    /** nullable persistent field */
    private String altPhoneNo;

    /** nullable persistent field */
    private String altFaxNumber;

    /** nullable persistent field */
    private String contactType;

    /** nullable persistent field */
    private String refCompanyName;

    /** nullable persistent field */
    private String refPhoneNumber;

    /** nullable persistent field */
    private String refContactName;

    /** nullable persistent field */
    private String vendorNaics;

    /** nullable persistent field */
    private String diversityProgram;

    /** nullable persistent field */
    private String subcontract;

    /** nullable persistent field */
    private String ownershipType;

    /** nullable persistent field */
    private String diverseCertOrgs;

    /** nullable persistent field */
    private String businessType;

    /** nullable persistent field */
    private String digitalSig;

    /** nullable persistent field */
    private java.util.Date dateEntered;

    /** nullable persistent field */
    private String termsAccepted;

    /** nullable persistent field */
    private String rtAddressLine1;

    /** nullable persistent field */
    private String rtAddressLine2;

    /** nullable persistent field */
    private String rtAddressLine3;

    /** nullable persistent field */
    private String rtAddressLine4;

    /** nullable persistent field */
    private String rtAddressCity;

    /** nullable persistent field */
    private String rtAddressState;

    /** nullable persistent field */
    private String rtAddressZip;

    /** nullable persistent field */
    private String rtAddressCountry;

    /** nullable persistent field */
    private String authorizedByName;

    /** nullable persistent field */
    private String authorizedByTitle;

    /** nullable persistent field */
    private java.util.Date authorizedDate;

    /** nullable persistent field */
    private String status;

    /** nullable persistent field */
    private String rejectionNotes;

    /** nullable persistent field */
    private String vendorShipVia;

    /** nullable persistent field */
    private String vendorFobId;

    /** nullable persistent field */
    private String vendorPrintFaxCode;

    /** nullable persistent field */
    private String contactInfo1;

    /** nullable persistent field */
    private String contactInfo2;

    /** nullable persistent field */
    private String contactInfo3;

    /** nullable persistent field */
    private String contactInfo4;

    /** nullable persistent field */
    private String altInfo1;

    /** nullable persistent field */
    private String altInfo2;

    /** nullable persistent field */
    private String altInfo3;

    /** nullable persistent field */
    private String altInfo4;

    /** nullable persistent field */
    private String arLastName;

    /** nullable persistent field */
    private String arFirstName;

    /** nullable persistent field */
    private String arMidInit;

    /** nullable persistent field */
    private String arSalutation;

    /** nullable persistent field */
    private String arTitle;

    /** nullable persistent field */
    private String arEmailAddr;

    /** nullable persistent field */
    private String arPhoneNo;

    /** nullable persistent field */
    private String arFaxNumber;

    /** nullable persistent field */
    private String arInfo1;

    /** nullable persistent field */
    private String arInfo2;

    /** nullable persistent field */
    private String arInfo3;

    /** nullable persistent field */
    private String arInfo4;

    /** nullable persistent field */
    private String vendorEmailAddr;

    /** nullable persistent field */
    private String eftBankName;

    /** nullable persistent field */
    private String eftAccountNumber;

    /** nullable persistent field */
    private String eftRoutingNumber;

    /** nullable persistent field */
    private String eftWireAccount;

    /** nullable persistent field */
    private String eftPersonName;

    /** nullable persistent field */
    private String eftAccountType;

    /** nullable persistent field */
    private String vendor1099;

    /** nullable persistent field */
    private String priorPassword;

    /** nullable persistent field */
    private String passChanged;

    /** nullable persistent field */
    private String lockLogin;

    /** full constructor */
    public VendorRegister(com.tsa.puridiom.entity.VendorRegisterPK comp_id, java.lang.String vendorName, java.lang.String vendorFaxNumber, java.lang.String vendorSic, java.lang.String vendorDuns, java.lang.String contactLastName, java.lang.String contactFirstName, java.lang.String contactMidInit, java.lang.String contactSalutation, java.lang.String contactTitle, java.lang.String contactPhoneNo, java.lang.String contactFaxNumber, java.lang.String contactMobileNumber, java.lang.String contactPassword, java.lang.String addressLine2, java.lang.String addressLine3, java.lang.String addressLine4, java.lang.String addressCity, java.lang.String addressState, java.lang.String addressZipCode, java.lang.String addressCountry, java.lang.String vendorUdf1, java.lang.String vendorUdf2, java.lang.String vendorUdf3, java.lang.String vendorUdf4, java.lang.String vendorUdf5, java.lang.String vendorUdf6, java.lang.String vendorUdf7, java.lang.String vendorUdf8, java.lang.String vendorUdf9, java.lang.String vendorUdf10, java.lang.String vendorClass, java.lang.String vendorWebAddress, java.lang.String vendorVendTerms, java.math.BigDecimal vendorYears, java.math.BigDecimal vendorLeadDays, java.lang.String vendorEin, java.lang.String vendorEdiVendor, java.lang.String vendorEdiAddress, java.lang.String vendorNotes, java.lang.String altLastName, java.lang.String altFirstName, java.lang.String altMidInit, java.lang.String altSalutation, java.lang.String altTitle, java.lang.String altEmailAddr, java.lang.String altPhoneNo, java.lang.String altFaxNumber, java.lang.String contactType, java.lang.String refCompanyName, java.lang.String refPhoneNumber, java.lang.String refContactName, java.lang.String vendorNaics, java.lang.String diversityProgram, java.lang.String subcontract, java.lang.String ownershipType, java.lang.String diverseCertOrgs, java.lang.String businessType, java.lang.String digitalSig, java.util.Date dateEntered, String termsAccepted, java.lang.String rtAddressLine1, java.lang.String rtAddressLine2, java.lang.String rtAddressLine3, java.lang.String rtAddressLine4, java.lang.String rtAddressCity, java.lang.String rtAddressState, java.lang.String rtAddressZip, java.lang.String rtAddressCountry, java.lang.String authorizedByName, java.lang.String authorizedByTitle, java.util.Date authorizedDate, java.lang.String status, java.lang.String rejectionNotes, java.lang.String vendorShipVia, java.lang.String vendorFobId, java.lang.String vendorPrintFaxCode, java.lang.String contactInfo1, java.lang.String contactInfo2, java.lang.String contactInfo3, java.lang.String contactInfo4, java.lang.String altInfo1, java.lang.String altInfo2, java.lang.String altInfo3, java.lang.String altInfo4, java.lang.String arLastName, java.lang.String arFirstName, java.lang.String arMidInit, java.lang.String arSalutation, java.lang.String arTitle, java.lang.String arEmailAddr, java.lang.String arPhoneNo, java.lang.String arFaxNumber, java.lang.String arInfo1, java.lang.String arInfo2, java.lang.String arInfo3, java.lang.String arInfo4, java.lang.String vendorEmailAddr, java.lang.String vendor1099, java.lang.String priorPassword, java.lang.String passChanged, java.lang.String lockLogin) {
        this.comp_id = comp_id;
        this.vendorName = vendorName;
        this.vendorFaxNumber = vendorFaxNumber;
        this.vendorSic = vendorSic;
        this.vendorDuns = vendorDuns;
        this.contactLastName = contactLastName;
        this.contactFirstName = contactFirstName;
        this.contactMidInit = contactMidInit;
        this.contactSalutation = contactSalutation;
        this.contactTitle = contactTitle;
        this.contactPhoneNo = contactPhoneNo;
        this.contactFaxNumber = contactFaxNumber;
        this.contactMobileNumber = contactMobileNumber;
        this.contactPassword = contactPassword;
        this.addressLine2 = addressLine2;
        this.addressLine3 = addressLine3;
        this.addressLine4 = addressLine4;
        this.addressCity = addressCity;
        this.addressState = addressState;
        this.addressZipCode = addressZipCode;
        this.addressCountry = addressCountry;
        this.vendorUdf1 = vendorUdf1;
        this.vendorUdf2 = vendorUdf2;
        this.vendorUdf3 = vendorUdf3;
        this.vendorUdf4 = vendorUdf4;
        this.vendorUdf5 = vendorUdf5;
        this.vendorUdf6 = vendorUdf6;
        this.vendorUdf7 = vendorUdf7;
        this.vendorUdf8 = vendorUdf8;
        this.vendorUdf9 = vendorUdf9;
        this.vendorUdf10 = vendorUdf10;
        this.vendorClass = vendorClass;
        this.vendorWebAddress = vendorWebAddress;
        this.vendorVendTerms = vendorVendTerms;
        this.vendorYears = vendorYears;
        this.vendorLeadDays = vendorLeadDays;
        this.vendorEin = vendorEin;
        this.vendorEdiVendor = vendorEdiVendor;
        this.vendorEdiAddress = vendorEdiAddress;
        this.vendorNotes = vendorNotes;
        this.altLastName = altLastName;
        this.altFirstName = altFirstName;
        this.altMidInit = altMidInit;
        this.altSalutation = altSalutation;
        this.altTitle = altTitle;
        this.altEmailAddr = altEmailAddr;
        this.altPhoneNo = altPhoneNo;
        this.altFaxNumber = altFaxNumber;
        this.contactType = contactType;
        this.refCompanyName = refCompanyName;
        this.refPhoneNumber = refPhoneNumber;
        this.refContactName = refContactName;
        this.vendorNaics = vendorNaics;
        this.diversityProgram = diversityProgram;
        this.subcontract = subcontract;
        this.ownershipType = ownershipType;
        this.diverseCertOrgs = diverseCertOrgs;
        this.businessType = businessType;
        this.digitalSig = digitalSig;
        this.dateEntered = dateEntered;
        this.termsAccepted = termsAccepted;
        this.rtAddressLine1 = rtAddressLine1;
        this.rtAddressLine2 = rtAddressLine2;
        this.rtAddressLine3 = rtAddressLine3;
        this.rtAddressLine4 = rtAddressLine4;
        this.rtAddressCity = rtAddressCity;
        this.rtAddressState = rtAddressState;
        this.rtAddressZip = rtAddressZip;
        this.rtAddressCountry = rtAddressCountry;
        this.authorizedByName = authorizedByName;
        this.authorizedByTitle = authorizedByTitle;
        this.authorizedDate = authorizedDate;
        this.status = status;
        this.rejectionNotes = rejectionNotes;
        this.vendorShipVia = vendorShipVia;
        this.vendorFobId = vendorFobId;
        this.vendorPrintFaxCode = vendorPrintFaxCode;
        this.contactInfo1 = contactInfo1;
        this.contactInfo2 = contactInfo2;
        this.contactInfo3 = contactInfo3;
        this.contactInfo4 = contactInfo4;
        this.altInfo1 = altInfo1;
        this.altInfo2 = altInfo2;
        this.altInfo3 = altInfo3;
        this.altInfo4 = altInfo4;
        this.arLastName = arLastName;
        this.arFirstName = arFirstName;
        this.arMidInit = arMidInit;
        this.arSalutation = arSalutation;
        this.arTitle = arTitle;
        this.arEmailAddr = arEmailAddr;
        this.arPhoneNo = arPhoneNo;
        this.arFaxNumber = arFaxNumber;
        this.arInfo1 = arInfo1;
        this.arInfo2 = arInfo2;
        this.arInfo3 = arInfo3;
        this.arInfo4 = arInfo4;
        this.vendorEmailAddr = vendorEmailAddr;
        this.vendor1099 = vendor1099;
        this.priorPassword = priorPassword;
        this.passChanged = passChanged;
        this.lockLogin = lockLogin;
    }

    /** default constructor */
    public VendorRegister() {
    }

    /** minimal constructor */
    public VendorRegister(com.tsa.puridiom.entity.VendorRegisterPK comp_id) {
        this.comp_id = comp_id;
    }

    public com.tsa.puridiom.entity.VendorRegisterPK getComp_id() {
        return this.comp_id;
    }

    public void setComp_id(com.tsa.puridiom.entity.VendorRegisterPK comp_id) {
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

    public java.lang.String getVendorFaxNumber() {
		return (java.lang.String)HiltonUtility.ckNull(this.vendorFaxNumber).trim();
    }

    public void setVendorFaxNumber(java.lang.String vendorFaxNumber) {
		if (!HiltonUtility.isEmpty(vendorFaxNumber) && vendorFaxNumber.length() > 30) {
			vendorFaxNumber = vendorFaxNumber.substring(0, 30);
		}
		this.vendorFaxNumber = vendorFaxNumber;
    }

    public java.lang.String getVendorSic() {
		return (java.lang.String)HiltonUtility.ckNull(this.vendorSic).trim();
    }

    public void setVendorSic(java.lang.String vendorSic) {
		if (!HiltonUtility.isEmpty(vendorSic) && vendorSic.length() > 4) {
			vendorSic = vendorSic.substring(0, 4);
		}
		this.vendorSic = vendorSic;
    }

    public java.lang.String getVendorDuns() {
		return (java.lang.String)HiltonUtility.ckNull(this.vendorDuns).trim();
    }

    public void setVendorDuns(java.lang.String vendorDuns) {
		if (!HiltonUtility.isEmpty(vendorDuns) && vendorDuns.length() > 11) {
			vendorDuns = vendorDuns.substring(0, 11);
		}
		this.vendorDuns = vendorDuns;
    }

    public java.lang.String getContactLastName() {
		return (java.lang.String)HiltonUtility.ckNull(this.contactLastName).trim();
    }

    public void setContactLastName(java.lang.String contactLastName) {
		if (!HiltonUtility.isEmpty(contactLastName) && contactLastName.length() > 20) {
			contactLastName = contactLastName.substring(0, 20);
		}
		this.contactLastName = contactLastName;
    }

    public java.lang.String getContactFirstName() {
		return (java.lang.String)HiltonUtility.ckNull(this.contactFirstName).trim();
    }

    public void setContactFirstName(java.lang.String contactFirstName) {
		if (!HiltonUtility.isEmpty(contactFirstName) && contactFirstName.length() > 20) {
			contactFirstName = contactFirstName.substring(0, 20);
		}
		this.contactFirstName = contactFirstName;
    }

    public java.lang.String getContactMidInit() {
		return (java.lang.String)HiltonUtility.ckNull(this.contactMidInit).trim();
    }

    public void setContactMidInit(java.lang.String contactMidInit) {
		if (!HiltonUtility.isEmpty(contactMidInit) && contactMidInit.length() > 2) {
			contactMidInit = contactMidInit.substring(0, 2);
		}
		this.contactMidInit = contactMidInit;
    }

    public java.lang.String getContactSalutation() {
		return (java.lang.String)HiltonUtility.ckNull(this.contactSalutation).trim();
    }

    public void setContactSalutation(java.lang.String contactSalutation) {
		if (!HiltonUtility.isEmpty(contactSalutation) && contactSalutation.length() > 5) {
			contactSalutation = contactSalutation.substring(0, 5);
		}
		this.contactSalutation = contactSalutation;
    }

    public java.lang.String getContactTitle() {
		return (java.lang.String)HiltonUtility.ckNull(this.contactTitle).trim();
    }

    public void setContactTitle(java.lang.String contactTitle) {
		if (!HiltonUtility.isEmpty(contactTitle) && contactTitle.length() > 30) {
			contactTitle = contactTitle.substring(0, 30);
		}
		this.contactTitle = contactTitle;
    }

    public java.lang.String getContactPhoneNo() {
		return (java.lang.String)HiltonUtility.ckNull(this.contactPhoneNo).trim();
    }

    public void setContactPhoneNo(java.lang.String contactPhoneNo) {
		if (!HiltonUtility.isEmpty(contactPhoneNo) && contactPhoneNo.length() > 30) {
			contactPhoneNo = contactPhoneNo.substring(0, 30);
		}
		this.contactPhoneNo = contactPhoneNo;
    }

    public java.lang.String getContactFaxNumber() {
		return (java.lang.String)HiltonUtility.ckNull(this.contactFaxNumber).trim();
    }

    public void setContactFaxNumber(java.lang.String contactFaxNumber) {
		if (!HiltonUtility.isEmpty(contactFaxNumber) && contactFaxNumber.length() > 30) {
			contactFaxNumber = contactFaxNumber.substring(0, 30);
		}
		this.contactFaxNumber = contactFaxNumber;
    }
    
    public java.lang.String getContactMobileNumber() {
    	return (java.lang.String)HiltonUtility.ckNull(this.contactMobileNumber).trim();
    }
    
    public void setContactMobileNumber(java.lang.String contactMobileNumber) {
    	if (!HiltonUtility.isEmpty(contactMobileNumber) && contactMobileNumber.length() > 30) {
    		contactMobileNumber = contactMobileNumber.substring(0, 30);
    	}
    	this.contactMobileNumber = contactMobileNumber;
    }

    public java.lang.String getContactPassword() {
		return (java.lang.String)Utility.ckNull(this.contactPassword).trim();
    }

    public void setContactPassword(java.lang.String contactPassword) {
		if (!HiltonUtility.isEmpty(contactPassword) && contactPassword.length() > 12) {
			contactPassword = contactPassword.substring(0, 12);
		}
		this.contactPassword = contactPassword;
    }

    public java.lang.String getAddressLine2() {
		return (java.lang.String)HiltonUtility.ckNull(this.addressLine2).trim();
    }

    public void setAddressLine2(java.lang.String addressLine2) {
		if (!HiltonUtility.isEmpty(addressLine2) && addressLine2.length() > 40) {
		    addressLine2 = addressLine2.substring(0, 40);
		}
        this.addressLine2 = addressLine2;
    }

    public java.lang.String getAddressLine3() {
		return (java.lang.String)HiltonUtility.ckNull(this.addressLine3).trim();
    }

    public void setAddressLine3(java.lang.String addressLine3) {
		if (!HiltonUtility.isEmpty(addressLine3) && addressLine3.length() > 40) {
		    addressLine3 = addressLine3.substring(0, 40);
		}
        this.addressLine3 = addressLine3;
    }

    public java.lang.String getAddressLine4() {
		return (java.lang.String)HiltonUtility.ckNull(this.addressLine4).trim();
    }

    public void setAddressLine4(java.lang.String addressLine4) {
		if (!HiltonUtility.isEmpty(addressLine4) && addressLine4.length() > 40) {
		    addressLine4 = addressLine4.substring(0, 40);
		}
        this.addressLine4 = addressLine4;
    }

    public java.lang.String getAddressCity() {
		return (java.lang.String)HiltonUtility.ckNull(this.addressCity).trim();
    }

    public void setAddressCity(java.lang.String addressCity) {
		if (!HiltonUtility.isEmpty(addressCity) && addressCity.length() > 30) {
			addressCity = addressCity.substring(0, 30);
		}
		this.addressCity = addressCity;
    }

    public java.lang.String getAddressState() {
		return (java.lang.String)HiltonUtility.ckNull(this.addressState).trim();
    }

    public void setAddressState(java.lang.String addressState) {
		if (!HiltonUtility.isEmpty(addressState) && addressState.length() > 15) {
			addressState = addressState.substring(0, 15);
		}
		this.addressState = addressState;
    }

    public java.lang.String getAddressZipCode() {
		return (java.lang.String)HiltonUtility.ckNull(this.addressZipCode).trim();
    }

    public void setAddressZipCode(java.lang.String addressZipCode) {
		if (!HiltonUtility.isEmpty(addressZipCode) && addressZipCode.length() > 15) {
			addressZipCode = addressZipCode.substring(0, 15);
		}
		this.addressZipCode = addressZipCode;
    }

    public java.lang.String getAddressCountry() {
		return (java.lang.String)HiltonUtility.ckNull(this.addressCountry).trim();
    }

    public void setAddressCountry(java.lang.String addressCountry) {
		if (!HiltonUtility.isEmpty(addressCountry) && addressCountry.length() > 25) {
			addressCountry = addressCountry.substring(0, 25);
		}
		this.addressCountry = addressCountry;
    }

    public java.lang.String getVendorUdf1() {
		return (java.lang.String)HiltonUtility.ckNull(this.vendorUdf1).trim();
    }

    public void setVendorUdf1(java.lang.String vendorUdf1) {
		if (!HiltonUtility.isEmpty(vendorUdf1) && vendorUdf1.length() > 15) {
		    vendorUdf1 = vendorUdf1.substring(0, 15);
		}
        this.vendorUdf1 = vendorUdf1;
    }

    public java.lang.String getVendorUdf2() {
		return (java.lang.String)HiltonUtility.ckNull(this.vendorUdf2).trim();
    }

    public void setVendorUdf2(java.lang.String vendorUdf2) {
		if (!HiltonUtility.isEmpty(vendorUdf2) && vendorUdf2.length() > 15) {
		    vendorUdf2 = vendorUdf2.substring(0, 15);
		}
        this.vendorUdf2 = vendorUdf2;
    }

    public java.lang.String getVendorUdf3() {
		return (java.lang.String)HiltonUtility.ckNull(this.vendorUdf3).trim();
    }

    public void setVendorUdf3(java.lang.String vendorUdf3) {
		if (!HiltonUtility.isEmpty(vendorUdf3) && vendorUdf3.length() > 15) {
		    vendorUdf3 = vendorUdf3.substring(0, 15);
		}
        this.vendorUdf3 = vendorUdf3;
    }

    public java.lang.String getVendorUdf4() {
		return (java.lang.String)HiltonUtility.ckNull(this.vendorUdf4).trim();
    }

    public void setVendorUdf4(java.lang.String vendorUdf4) {
		if (!HiltonUtility.isEmpty(vendorUdf4) && vendorUdf4.length() > 15) {
		    vendorUdf4 = vendorUdf4.substring(0, 15);
		}
        this.vendorUdf4 = vendorUdf4;
    }

    public java.lang.String getVendorUdf5() {
		return (java.lang.String)HiltonUtility.ckNull(this.vendorUdf5).trim();
    }

    public void setVendorUdf5(java.lang.String vendorUdf5) {
		if (!HiltonUtility.isEmpty(vendorUdf5) && vendorUdf5.length() > 15) {
		    vendorUdf5 = vendorUdf5.substring(0, 15);
		}
        this.vendorUdf5 = vendorUdf5;
    }

    public java.lang.String getVendorUdf6() {
		return (java.lang.String)HiltonUtility.ckNull(this.vendorUdf6).trim();
    }

    public void setVendorUdf6(java.lang.String vendorUdf6) {
		if (!HiltonUtility.isEmpty(vendorUdf6) && vendorUdf6.length() > 15) {
		    vendorUdf6 = vendorUdf6.substring(0, 15);
		}
        this.vendorUdf6 = vendorUdf6;
    }

    public java.lang.String getVendorUdf7() {
		return (java.lang.String)HiltonUtility.ckNull(this.vendorUdf7).trim();
    }

    public void setVendorUdf7(java.lang.String vendorUdf7) {
		if (!HiltonUtility.isEmpty(vendorUdf7) && vendorUdf7.length() > 15) {
		    vendorUdf7 = vendorUdf7.substring(0, 15);
		}
        this.vendorUdf7 = vendorUdf7;
    }

    public java.lang.String getVendorUdf8() {
		return (java.lang.String)HiltonUtility.ckNull(this.vendorUdf8).trim();
    }

    public void setVendorUdf8(java.lang.String vendorUdf8) {
		if (!HiltonUtility.isEmpty(vendorUdf8) && vendorUdf8.length() > 15) {
		    vendorUdf8 = vendorUdf8.substring(0, 15);
		}
        this.vendorUdf8 = vendorUdf8;
    }

    public java.lang.String getVendorUdf9() {
		return (java.lang.String)HiltonUtility.ckNull(this.vendorUdf9).trim();
    }

    public void setVendorUdf9(java.lang.String vendorUdf9) {
		if (!HiltonUtility.isEmpty(vendorUdf9) && vendorUdf9.length() > 15) {
		    vendorUdf9 = vendorUdf9.substring(0, 15);
		}
        this.vendorUdf9 = vendorUdf9;
    }

    public java.lang.String getVendorUdf10() {
		return (java.lang.String)HiltonUtility.ckNull(this.vendorUdf10).trim();
    }

    public void setVendorUdf10(java.lang.String vendorUdf10) {
		if (!HiltonUtility.isEmpty(vendorUdf10) && vendorUdf10.length() > 15) {
		    vendorUdf10 = vendorUdf10.substring(0, 15);
		}
        this.vendorUdf10 = vendorUdf10;
    }

    public java.lang.String getVendorClass() {
		return (java.lang.String)HiltonUtility.ckNull(this.vendorClass).trim();
    }

    public void setVendorClass(java.lang.String vendorClass) {
		if (!HiltonUtility.isEmpty(vendorClass) && vendorClass.length() > 15) {
			vendorClass = vendorClass.substring(0, 15);
		}
		this.vendorClass = vendorClass;
    }

    public java.lang.String getVendorWebAddress() {
		return (java.lang.String)HiltonUtility.ckNull(this.vendorWebAddress).trim();
    }

    public void setVendorWebAddress(java.lang.String vendorWebAddress) {
		if (!HiltonUtility.isEmpty(vendorWebAddress) && vendorWebAddress.length() > 60) {
			vendorWebAddress = vendorWebAddress.substring(0, 60);
		}
		this.vendorWebAddress = vendorWebAddress;
    }

    public java.lang.String getVendorVendTerms() {
		return (java.lang.String)HiltonUtility.ckNull(this.vendorVendTerms).trim();
    }

    public void setVendorVendTerms(java.lang.String vendorVendTerms) {
		if (!HiltonUtility.isEmpty(vendorVendTerms) && vendorVendTerms.length() > 15) {
			vendorVendTerms = vendorVendTerms.substring(0, 15);
		}
		this.vendorVendTerms = vendorVendTerms;
    }

    public java.lang.String getVendPaymentType() {
		return (java.lang.String)HiltonUtility.ckNull(this.vendPaymentType).trim();
    }

    public void setVendPaymentType(java.lang.String vendPaymentType) {
		if (!HiltonUtility.isEmpty(vendPaymentType) && vendPaymentType.length() > 15) {
			vendPaymentType = vendPaymentType.substring(0, 15);
		}
		this.vendPaymentType = vendPaymentType;
    }

    public java.math.BigDecimal getVendorYears() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.vendorYears);
    }

    public void setVendorYears(java.math.BigDecimal vendorYears) {
        this.vendorYears = vendorYears;
    }

    public java.math.BigDecimal getVendorLeadDays() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.vendorLeadDays);
    }

    public void setVendorLeadDays(java.math.BigDecimal vendorLeadDays) {
        this.vendorLeadDays = vendorLeadDays;
    }

    public java.lang.String getVendorEin() {
		return (java.lang.String)HiltonUtility.ckNull(this.vendorEin).trim();
    }

    public void setVendorEin(java.lang.String vendorEin) {
		if (!HiltonUtility.isEmpty(vendorEin) && vendorEin.length() > 10) {
			vendorEin = vendorEin.substring(0, 10);
		}
		this.vendorEin = vendorEin;
    }

    public java.lang.String getVendorEdiVendor() {
		return (java.lang.String)HiltonUtility.ckNull(this.vendorEdiVendor).trim();
    }

    public void setVendorEdiVendor(java.lang.String vendorEdiVendor) {
		if (!HiltonUtility.isEmpty(vendorEdiVendor) && vendorEdiVendor.length() > 15) {
			vendorEdiVendor = vendorEdiVendor.substring(0, 15);
		}
		this.vendorEdiVendor = vendorEdiVendor;
    }

    public java.lang.String getVendorEdiAddress() {
		return (java.lang.String)HiltonUtility.ckNull(this.vendorEdiAddress).trim();
    }

    public void setVendorEdiAddress(java.lang.String vendorEdiAddress) {
		if (!HiltonUtility.isEmpty(vendorEdiAddress) && vendorEdiAddress.length() > 20) {
			vendorEdiAddress = vendorEdiAddress.substring(0, 20);
		}
		this.vendorEdiAddress = vendorEdiAddress;
    }

    public java.lang.String getVendorNotes() {
		return (java.lang.String)HiltonUtility.ckNull(this.vendorNotes).trim();
    }

    public void setVendorNotes(java.lang.String vendorNotes) {
		if (!HiltonUtility.isEmpty(vendorNotes) && vendorNotes.length() > 255) {
			vendorNotes = vendorNotes.substring(0, 255);
		}
		this.vendorNotes = vendorNotes;
    }

    public java.lang.String getAltLastName() {
		return (java.lang.String)HiltonUtility.ckNull(this.altLastName).trim();
    }

    public void setAltLastName(java.lang.String altLastName) {
		if (!HiltonUtility.isEmpty(altLastName) && altLastName.length() > 20) {
			altLastName = altLastName.substring(0, 20);
		}
		this.altLastName = altLastName;
    }

    public java.lang.String getAltFirstName() {
		return (java.lang.String)HiltonUtility.ckNull(this.altFirstName).trim();
    }

    public void setAltFirstName(java.lang.String altFirstName) {
		if (!HiltonUtility.isEmpty(altFirstName) && altFirstName.length() > 20) {
			altFirstName = altFirstName.substring(0, 20);
		}
		this.altFirstName = altFirstName;
    }

    public java.lang.String getAltMidInit() {
		return (java.lang.String)HiltonUtility.ckNull(this.altMidInit).trim();
    }

    public void setAltMidInit(java.lang.String altMidInit) {
		if (!HiltonUtility.isEmpty(altMidInit) && altMidInit.length() > 2) {
			altMidInit = altMidInit.substring(0, 2);
		}
		this.altMidInit = altMidInit;
    }

    public java.lang.String getAltSalutation() {
		return (java.lang.String)HiltonUtility.ckNull(this.altSalutation).trim();
    }

    public void setAltSalutation(java.lang.String altSalutation) {
		if (!HiltonUtility.isEmpty(altSalutation) && altSalutation.length() > 5) {
			altSalutation = altSalutation.substring(0, 5);
		}
		this.altSalutation = altSalutation;
    }

    public java.lang.String getAltTitle() {
		return (java.lang.String)HiltonUtility.ckNull(this.altTitle).trim();
    }

    public void setAltTitle(java.lang.String altTitle) {
		if (!HiltonUtility.isEmpty(altTitle) && altTitle.length() > 30) {
			altTitle = altTitle.substring(0, 30);
		}
		this.altTitle = altTitle;
    }

    public java.lang.String getAltEmailAddr() {
		return (java.lang.String)Utility.ckNull(this.altEmailAddr).trim();
    }

    public void setAltEmailAddr(java.lang.String altEmailAddr) {
		if (!HiltonUtility.isEmpty(altEmailAddr) && altEmailAddr.length() > 50) {
			altEmailAddr = altEmailAddr.substring(0, 50);
		}
		this.altEmailAddr = altEmailAddr;
    }

    public java.lang.String getAltPhoneNo() {
		return (java.lang.String)HiltonUtility.ckNull(this.altPhoneNo).trim();
    }

    public void setAltPhoneNo(java.lang.String altPhoneNo) {
		if (!HiltonUtility.isEmpty(altPhoneNo) && altPhoneNo.length() > 30) {
			altPhoneNo = altPhoneNo.substring(0, 30);
		}
		this.altPhoneNo = altPhoneNo;
    }

    public java.lang.String getAltFaxNumber() {
		return (java.lang.String)HiltonUtility.ckNull(this.altFaxNumber).trim();
    }

    public void setAltFaxNumber(java.lang.String altFaxNumber) {
		if (!HiltonUtility.isEmpty(altFaxNumber) && altFaxNumber.length() > 30) {
			altFaxNumber = altFaxNumber.substring(0, 30);
		}
		this.altFaxNumber = altFaxNumber;
    }

    public java.lang.String getContactType() {
		return (java.lang.String)HiltonUtility.ckNull(this.contactType).trim();
    }

    public void setContactType(java.lang.String contactType) {
		if (!HiltonUtility.isEmpty(contactType) && contactType.length() > 15) {
			contactType = contactType.substring(0, 15);
		}
		this.contactType = contactType;
    }

    public java.lang.String getRefCompanyName() {
		return (java.lang.String)HiltonUtility.ckNull(this.refCompanyName).trim();
    }

    public void setRefCompanyName(java.lang.String refCompanyName) {
		if (!HiltonUtility.isEmpty(refCompanyName) && refCompanyName.length() > 255) {
		    refCompanyName = refCompanyName.substring(0, 255);
		}
		this.refCompanyName = refCompanyName;
    }

    public java.lang.String getRefPhoneNumber() {
		return (java.lang.String)HiltonUtility.ckNull(this.refPhoneNumber).trim();
    }

    public void setRefPhoneNumber(java.lang.String refPhoneNumber) {
		if (!HiltonUtility.isEmpty(refCompanyName) && refCompanyName.length() > 255) {
		    refPhoneNumber = refPhoneNumber.substring(0, 2556);
		}
		this.refPhoneNumber = refPhoneNumber;
    }

    public java.lang.String getRefContactName() {
		return (java.lang.String)HiltonUtility.ckNull(this.refContactName).trim();
    }

    public void setRefContactName(java.lang.String refContactName) {
		if (!HiltonUtility.isEmpty(refContactName) && refContactName.length() > 255) {
		    refContactName = refContactName.substring(0, 255);
		}
		this.refContactName = refContactName;
    }

    public java.lang.String getVendorNaics() {
		return (java.lang.String)HiltonUtility.ckNull(this.vendorNaics).trim();
    }

    public void setVendorNaics(java.lang.String  vendorNaics) {
		if (!HiltonUtility.isEmpty( vendorNaics) &&  vendorNaics.length() > 255) {
		    vendorNaics =  vendorNaics.substring(0, 255);
		}
		this. vendorNaics =  vendorNaics;
    }

    public java.lang.String getDiversityProgram() {
		return (java.lang.String)HiltonUtility.ckNull(this.diversityProgram).trim();
    }

    public void setDiversityProgram(java.lang.String  diversityProgram) {
		if (!HiltonUtility.isEmpty( diversityProgram) &&  diversityProgram.length() > 1) {
		    diversityProgram =  diversityProgram.substring(0, 1);
		}
		this. diversityProgram =  diversityProgram;
    }

    public java.lang.String getSubcontract() {
		return (java.lang.String)HiltonUtility.ckNull(this.subcontract).trim();
    }

    public void setSubcontract(java.lang.String  subcontract) {
		if (!HiltonUtility.isEmpty( subcontract) &&  subcontract.length() > 1) {
		    subcontract =  subcontract.substring(0, 1);
		}
		this. subcontract =  subcontract;
    }

    public java.lang.String getOwnershipType() {
		return (java.lang.String)HiltonUtility.ckNull(this.ownershipType).trim();
    }

    public void setOwnershipType(java.lang.String  ownershipType) {
		if (!HiltonUtility.isEmpty( ownershipType) &&  ownershipType.length() > 40) {
		    ownershipType =  ownershipType.substring(0, 40);
		}
		this. ownershipType =  ownershipType;
    }

    public java.lang.String getDiverseCertOrgs() {
		return (java.lang.String)HiltonUtility.ckNull(this.diverseCertOrgs).trim();
    }

    public void setDiverseCertOrgs(java.lang.String  diverseCertOrgs) {
		if (!HiltonUtility.isEmpty( diverseCertOrgs) &&  diverseCertOrgs.length() > 40) {
		    diverseCertOrgs =  diverseCertOrgs.substring(0, 40);
		}
		this. diverseCertOrgs =  diverseCertOrgs;
    }

    public java.lang.String getBusinessType() {
		return (java.lang.String)HiltonUtility.ckNull(this.businessType).trim();
    }

    public void setBusinessType(java.lang.String  businessType) {
		if (!HiltonUtility.isEmpty( businessType) &&  businessType.length() > 40) {
		    businessType =  businessType.substring(0, 40);
		}
		this. businessType =  businessType;
    }

    public java.lang.String getDigitalSig() {
		return (java.lang.String)HiltonUtility.ckNull(this.digitalSig).trim();
    }

    public void setDigitalSig(java.lang.String  digitalSig) {
		if (!HiltonUtility.isEmpty( digitalSig) &&  digitalSig.length() > 15) {
		    digitalSig =  digitalSig.substring(0, 15);
		}
		this.digitalSig =  digitalSig;
    }

    public java.util.Date getDateEntered() {
		return this.dateEntered;
    }

    public void setDateEntered(java.util.Date dateEntered) {
        this.dateEntered = dateEntered;
    }

    public java.lang.String getTermsAccepted() {
		return (java.lang.String)HiltonUtility.ckNull(this.termsAccepted).trim();
    }

    public void setTermsAccepted(java.lang.String  termsAccepted) {
		if (!HiltonUtility.isEmpty( termsAccepted) &&  termsAccepted.length() > 10) {
		    termsAccepted =  termsAccepted.substring(0, 10);
		}
		this.termsAccepted =  termsAccepted;
    }

    public java.lang.String getRtAddressLine1() {
		return (java.lang.String)HiltonUtility.ckNull(this.rtAddressLine1).trim();
    }

    public void setRtAddressLine1(java.lang.String rtAddressLine1) {
		if (!HiltonUtility.isEmpty(rtAddressLine1) && rtAddressLine1.length() > 40) {
		    rtAddressLine1 = rtAddressLine1.substring(0, 40);
		}
        this.rtAddressLine1 = rtAddressLine1;
    }

    public java.lang.String getRtAddressLine2() {
		return (java.lang.String)HiltonUtility.ckNull(this.rtAddressLine2).trim();
    }

    public void setRtAddressLine2(java.lang.String rtAddressLine2) {
		if (!HiltonUtility.isEmpty(rtAddressLine2) && rtAddressLine2.length() > 40) {
		    rtAddressLine2 = rtAddressLine2.substring(0, 40);
		}
        this.rtAddressLine2 = rtAddressLine2;
    }

    public java.lang.String getRtAddressLine3() {
		return (java.lang.String)HiltonUtility.ckNull(this.rtAddressLine3).trim();
    }

    public void setRtAddressLine3(java.lang.String rtAddressLine3) {
		if (!HiltonUtility.isEmpty(rtAddressLine3) && rtAddressLine3.length() > 40) {
		    rtAddressLine3 = rtAddressLine3.substring(0, 40);
		}
        this.rtAddressLine3 = rtAddressLine3;
    }

    public java.lang.String getRtAddressLine4() {
		return (java.lang.String)HiltonUtility.ckNull(this.rtAddressLine4).trim();
    }

    public void setRtAddressLine4(java.lang.String rtAddressLine4) {
		if (!HiltonUtility.isEmpty(rtAddressLine4) && rtAddressLine4.length() > 40) {
		    rtAddressLine4 = rtAddressLine4.substring(0, 40);
		}
        this.rtAddressLine4 = rtAddressLine4;
    }

    public java.lang.String getRtAddressCity() {
		return (java.lang.String)HiltonUtility.ckNull(this.rtAddressCity).trim();
    }

    public void setRtAddressCity(java.lang.String rtAddressCity) {
		if (!HiltonUtility.isEmpty(rtAddressCity) && rtAddressCity.length() > 30) {
			rtAddressCity = rtAddressCity.substring(0, 30);
		}
		this.rtAddressCity = rtAddressCity;
    }

    public java.lang.String getRtAddressState() {
		return (java.lang.String)HiltonUtility.ckNull(this.rtAddressState).trim();
    }

    public void setRtAddressState(java.lang.String rtAddressState) {
		if (!HiltonUtility.isEmpty(rtAddressState) && rtAddressState.length() > 15) {
			rtAddressState = rtAddressState.substring(0, 15);
		}
		this.rtAddressState = rtAddressState;
    }

    public java.lang.String getRtAddressZip() {
		return (java.lang.String)HiltonUtility.ckNull(this.rtAddressZip).trim();
    }

    public void setRtAddressZip(java.lang.String rtAddressZip) {
		if (!HiltonUtility.isEmpty(rtAddressZip) && rtAddressZip.length() > 15) {
			rtAddressZip = rtAddressZip.substring(0, 15);
		}
		this.rtAddressZip = rtAddressZip;
    }

    public java.lang.String getRtAddressCountry() {
		return (java.lang.String)HiltonUtility.ckNull(this.rtAddressCountry).trim();
    }

    public void setRtAddressCountry(java.lang.String rtAddressCountry) {
		if (!HiltonUtility.isEmpty(rtAddressCountry) && rtAddressCountry.length() > 25) {
			rtAddressCountry = rtAddressCountry.substring(0, 25);
		}
		this.rtAddressCountry = rtAddressCountry;
    }

    public java.lang.String getAuthorizedByName() {
		return (java.lang.String)HiltonUtility.ckNull(this.authorizedByName).trim();
    }

    public void setAuthorizedByName(java.lang.String  authorizedByName) {
		if (!HiltonUtility.isEmpty(authorizedByName) &&  authorizedByName.length() > 60) {
			authorizedByName =  authorizedByName.substring(0, 60);
		}
		this.authorizedByName =  authorizedByName;
    }

    public java.lang.String getAuthorizedByTitle() {
		return (java.lang.String)HiltonUtility.ckNull(this.authorizedByTitle).trim();
    }

    public void setAuthorizedByTitle(java.lang.String  authorizedByTitle) {
		if (!HiltonUtility.isEmpty(authorizedByTitle) &&  authorizedByTitle.length() > 60) {
			authorizedByTitle =  authorizedByTitle.substring(0, 60);
		}
		this.authorizedByTitle =  authorizedByTitle;
    }

    public java.util.Date getAuthorizedDate() {
		return this.authorizedDate;
    }

    public void setAuthorizedDate(java.util.Date authorizedDate) {
        this.authorizedDate = authorizedDate;
    }

    public java.lang.String getStatus() {
		return (java.lang.String)HiltonUtility.ckNull(this.status).trim();
    }

    public void setStatus(java.lang.String  status) {
		if (!HiltonUtility.isEmpty(status) &&  status.length() > 4) {
			status =  status.substring(0, 4);
		}
		this.status =  status;
    }

    public java.lang.String getRejectionNotes() {
		return (java.lang.String)HiltonUtility.ckNull(this.rejectionNotes).trim();
    }

    public void setRejectionNotes(java.lang.String  rejectionNotes) {
		if (!HiltonUtility.isEmpty(rejectionNotes) &&  rejectionNotes.length() > 255) {
			rejectionNotes =  rejectionNotes.substring(0, 255);
		}
		this.rejectionNotes =  rejectionNotes;
    }

    public String getAltDisplayName() {
    	StringBuffer name = new StringBuffer();
    	if (!HiltonUtility.isEmpty(this.altFirstName)) {
    		name.append(this.altFirstName.trim());
    	}
    	if (!HiltonUtility.isEmpty(this.altMidInit)) {
    		if (name.length() > 0) {
    			name.append(" " + this.altMidInit.trim());
    		} else {
    			name.append(this.altMidInit.trim());
    		}
    	}
    	if (!HiltonUtility.isEmpty(this.altLastName)) {
    		if (name.length() > 0) {
    			name.append(" " + this.altLastName.trim());
    		} else {
    			name.append(this.altLastName.trim());
    		}
    	}
    	if (HiltonUtility.isEmpty(name.toString())) {
    		return "";
    	}
    	return name.toString();
   	}

    public String getContactDisplayName() {
    	StringBuffer name = new StringBuffer();
    	if (!HiltonUtility.isEmpty(this.contactFirstName)) {
    		name.append(this.contactFirstName.trim());
    	}
    	if (!HiltonUtility.isEmpty(this.contactMidInit)) {
    		if (name.length() > 0) {
    			name.append(" " + this.contactMidInit.trim());
    		} else {
    			name.append(this.contactMidInit.trim());
    		}
    	}
    	if (!HiltonUtility.isEmpty(this.contactLastName)) {
    		if (name.length() > 0) {
    			name.append(" " + this.contactLastName.trim());
    		} else {
    			name.append(this.contactLastName.trim());
    		}
    	}
    	if (HiltonUtility.isEmpty(name.toString())) {
    		return "";
    	}
    	return name.toString();
   	}

    public String getAddressCityStateZip()
    {
        StringBuffer sb = new StringBuffer();
        if(!HiltonUtility.isEmpty(this.getAddressCity()) )
        {
            sb.append(this.getAddressCity());
        }
        if(!HiltonUtility.isEmpty(this.getAddressState()) )
        {
            if(sb.length() > 0)
            {
                sb.append(", ");
            }
            sb.append(this.getAddressState());
        }
        if(!HiltonUtility.isEmpty(this.getAddressZipCode()) )
        {
            if(sb.length() > 0)
            {
                sb.append(" ");
            }
            sb.append(this.getAddressZipCode());
        }
        return sb.toString();
    }

    public String getRtAddressCityStateZip()
    {
        StringBuffer sb = new StringBuffer();
        if(!HiltonUtility.isEmpty(this.getRtAddressCity()) )
        {
            sb.append(this.getRtAddressCity());
        }
        if(!HiltonUtility.isEmpty(this.getRtAddressState()) )
        {
            if(sb.length() > 0)
            {
                sb.append(", ");
            }
            sb.append(this.getRtAddressState());
        }
        if(!HiltonUtility.isEmpty(this.getRtAddressZip()) )
        {
            if(sb.length() > 0)
            {
                sb.append(" ");
            }
            sb.append(this.getRtAddressZip());
        }
        return sb.toString();
    }

    public java.lang.String getVendorShipVia() {
		return (java.lang.String)HiltonUtility.ckNull(this.vendorShipVia).trim();
    }

    public void setVendorShipVia(java.lang.String vendorShipVia) {
		if (!HiltonUtility.isEmpty(vendorShipVia) && vendorShipVia.length() > 15) {
			vendorShipVia = vendorShipVia.substring(0, 15);
		}
		this.vendorShipVia = vendorShipVia;
    }

    public java.lang.String getVendorFobId() {
		return (java.lang.String)HiltonUtility.ckNull(this.vendorFobId).trim();
    }

    public void setVendorFobId(java.lang.String vendorFobId) {
		if (!HiltonUtility.isEmpty(vendorFobId) && vendorFobId.length() > 15) {
			vendorFobId = vendorFobId.substring(0, 15);
		}
		this.vendorFobId = vendorFobId;
    }

    public java.lang.String getVendorPrintFaxCode() {
		return (java.lang.String)HiltonUtility.ckNull(this.vendorPrintFaxCode).trim();
    }

    public void setVendorPrintFaxCode(java.lang.String vendorPrintFaxCode) {
		if (!HiltonUtility.isEmpty(vendorPrintFaxCode) && vendorPrintFaxCode.length() > 1) {
			vendorPrintFaxCode = vendorPrintFaxCode.substring(0, 1);
		}
		this.vendorPrintFaxCode = vendorPrintFaxCode;
    }

    public java.lang.String getContactInfo1() {
		return (java.lang.String)HiltonUtility.ckNull(this.contactInfo1).trim();
    }

    public void setContactInfo1(java.lang.String contactInfo1) {
		if (!HiltonUtility.isEmpty(contactInfo1) && contactInfo1.length() > 50) {
			contactInfo1 = contactInfo1.substring(0, 50);
		}
		this.contactInfo1 = contactInfo1;
    }


    public java.lang.String getContactInfo2() {
		return (java.lang.String)HiltonUtility.ckNull(this.contactInfo2).trim();
    }

    public void setContactInfo2(java.lang.String contactInfo2) {
		if (!HiltonUtility.isEmpty(contactInfo2) && contactInfo2.length() > 50) {
			contactInfo2 = contactInfo2.substring(0, 50);
		}
		this.contactInfo2 = contactInfo2;
    }


    public java.lang.String getContactInfo3() {
		return (java.lang.String)HiltonUtility.ckNull(this.contactInfo3).trim();
    }

    public void setContactInfo3(java.lang.String contactInfo3) {
		if (!HiltonUtility.isEmpty(contactInfo3) && contactInfo3.length() > 50) {
			contactInfo3 = contactInfo3.substring(0, 50);
		}
		this.contactInfo3 = contactInfo3;
    }


    public java.lang.String getContactInfo4() {
		return (java.lang.String)HiltonUtility.ckNull(this.contactInfo4).trim();
    }

    public void setContactInfo4(java.lang.String contactInfo4) {
		if (!HiltonUtility.isEmpty(contactInfo4) && contactInfo4.length() > 50) {
			contactInfo4 = contactInfo4.substring(0, 50);
		}
		this.contactInfo4 = contactInfo4;
    }


    public java.lang.String getAltInfo1() {
		return (java.lang.String)HiltonUtility.ckNull(this.altInfo1).trim();
    }

    public void setAltInfo1(java.lang.String altInfo1) {
		if (!HiltonUtility.isEmpty(altInfo1) && altInfo1.length() > 50) {
			altInfo1 = altInfo1.substring(0, 50);
		}
		this.altInfo1 = altInfo1;
    }


    public java.lang.String getAltInfo2() {
		return (java.lang.String)HiltonUtility.ckNull(this.altInfo2).trim();
    }

    public void setAltInfo2(java.lang.String altInfo2) {
		if (!HiltonUtility.isEmpty(altInfo2) && altInfo2.length() > 50) {
			altInfo2 = altInfo2.substring(0, 50);
		}
		this.altInfo2 = altInfo2;
    }


    public java.lang.String getAltInfo3() {
		return (java.lang.String)HiltonUtility.ckNull(this.altInfo3).trim();
    }

    public void setAltInfo3(java.lang.String altInfo3) {
		if (!HiltonUtility.isEmpty(altInfo3) && altInfo3.length() > 50) {
			altInfo3 = altInfo3.substring(0, 50);
		}
		this.altInfo3 = altInfo3;
    }


    public java.lang.String getAltInfo4() {
		return (java.lang.String)HiltonUtility.ckNull(this.altInfo4).trim();
    }

    public void setAltInfo4(java.lang.String altInfo4) {
		if (!HiltonUtility.isEmpty(altInfo4) && altInfo4.length() > 50) {
			altInfo4 = altInfo4.substring(0, 50);
		}
		this.altInfo4 = altInfo4;
    }

    public java.lang.String getArLastName() {
		return (java.lang.String)HiltonUtility.ckNull(this.arLastName).trim();
    }

    public void setArLastName(java.lang.String arLastName) {
		if (!HiltonUtility.isEmpty(arLastName) && arLastName.length() > 20) {
			arLastName = arLastName.substring(0, 20);
		}
		this.arLastName = arLastName;
    }

    public java.lang.String getArFirstName() {
		return (java.lang.String)HiltonUtility.ckNull(this.arFirstName).trim();
    }

    public void setArFirstName(java.lang.String arFirstName) {
		if (!HiltonUtility.isEmpty(arFirstName) && arFirstName.length() > 20) {
			arFirstName = arFirstName.substring(0, 20);
		}
		this.arFirstName = arFirstName;
    }

    public java.lang.String getArMidInit() {
		return (java.lang.String)HiltonUtility.ckNull(this.arMidInit).trim();
    }

    public void setArMidInit(java.lang.String arMidInit) {
		if (!HiltonUtility.isEmpty(arMidInit) && arMidInit.length() > 2) {
			arMidInit = arMidInit.substring(0, 2);
		}
		this.arMidInit = arMidInit;
    }

    public java.lang.String getArSalutation() {
		return (java.lang.String)HiltonUtility.ckNull(this.arSalutation).trim();
    }

    public void setArSalutation(java.lang.String arSalutation) {
		if (!HiltonUtility.isEmpty(arSalutation) && arSalutation.length() > 5) {
			arSalutation = arSalutation.substring(0, 5);
		}
		this.arSalutation = arSalutation;
    }

    public java.lang.String getArTitle() {
		return (java.lang.String)HiltonUtility.ckNull(this.arTitle).trim();
    }

    public void setArTitle(java.lang.String arTitle) {
		if (!HiltonUtility.isEmpty(arTitle) && arTitle.length() > 30) {
			arTitle = arTitle.substring(0, 30);
		}
		this.arTitle = arTitle;
    }

    public java.lang.String getArEmailAddr() {
		return (java.lang.String)Utility.ckNull(this.arEmailAddr).trim();
    }

    public void setArEmailAddr(java.lang.String arEmailAddr) {
		if (!HiltonUtility.isEmpty(arEmailAddr) && arEmailAddr.length() > 50) {
			arEmailAddr = arEmailAddr.substring(0, 50);
		}
		this.arEmailAddr = arEmailAddr;
    }

    public java.lang.String getArPhoneNo() {
		return (java.lang.String)HiltonUtility.ckNull(this.arPhoneNo).trim();
    }

    public void setArPhoneNo(java.lang.String arPhoneNo) {
		if (!HiltonUtility.isEmpty(arPhoneNo) && arPhoneNo.length() > 30) {
			arPhoneNo = arPhoneNo.substring(0, 30);
		}
		this.arPhoneNo = arPhoneNo;
    }

    public java.lang.String getArFaxNumber() {
		return (java.lang.String)HiltonUtility.ckNull(this.arFaxNumber).trim();
    }

    public void setArFaxNumber(java.lang.String arFaxNumber) {
		if (!HiltonUtility.isEmpty(arFaxNumber) && arFaxNumber.length() > 30) {
			arFaxNumber = arFaxNumber.substring(0, 30);
		}
		this.arFaxNumber = arFaxNumber;
    }

    public java.lang.String getArInfo1() {
		return (java.lang.String)HiltonUtility.ckNull(this.arInfo1).trim();
    }

    public void setArInfo1(java.lang.String arInfo1) {
		if (!HiltonUtility.isEmpty(arInfo1) && arInfo1.length() > 50) {
			arInfo1 = arInfo1.substring(0, 50);
		}
		this.arInfo1 = arInfo1;
    }


    public java.lang.String getArInfo2() {
		return (java.lang.String)HiltonUtility.ckNull(this.arInfo2).trim();
    }

    public void setArInfo2(java.lang.String arInfo2) {
		if (!HiltonUtility.isEmpty(arInfo2) && arInfo2.length() > 50) {
			arInfo2 = arInfo2.substring(0, 50);
		}
		this.arInfo2 = arInfo2;
    }


    public java.lang.String getArInfo3() {
		return (java.lang.String)HiltonUtility.ckNull(this.arInfo3).trim();
    }

    public void setArInfo3(java.lang.String arInfo3) {
		if (!HiltonUtility.isEmpty(arInfo3) && arInfo3.length() > 50) {
			arInfo3 = arInfo3.substring(0, 50);
		}
		this.arInfo3 = arInfo3;
    }

    public java.lang.String getArInfo4() {
		return (java.lang.String)HiltonUtility.ckNull(this.arInfo4).trim();
    }

    public void setArInfo4(java.lang.String arInfo4) {
		if (!HiltonUtility.isEmpty(arInfo4) && arInfo4.length() > 50) {
			arInfo4 = arInfo4.substring(0, 50);
		}
		this.arInfo4 = arInfo4;
    }

    public java.lang.String getVendorEmailAddr() {
		return (java.lang.String)Utility.ckNull(this.vendorEmailAddr).trim();
    }

    public void setVendorEmailAddr(java.lang.String vendorEmailAddr) {
		if (!HiltonUtility.isEmpty(vendorEmailAddr) && vendorEmailAddr.length() > 40) {
			vendorEmailAddr = vendorEmailAddr.substring(0, 40);
		}
		this.vendorEmailAddr = vendorEmailAddr;
    }

    public void setEftBankName(String eftBankName) {
    	if (!HiltonUtility.isEmpty(eftBankName) && eftBankName.length() > 40) {
    		eftBankName = eftBankName.substring(0, 40);
		}
		this.eftBankName = eftBankName;
	}

	public String getEftBankName() {
		return (java.lang.String)HiltonUtility.ckNull(this.eftBankName).trim();
	}

	public void setEftAccountNumber(String eftAccountNumber) {
		if (!HiltonUtility.isEmpty(eftAccountNumber) && eftAccountNumber.length() > 20) {
			eftAccountNumber = eftAccountNumber.substring(0, 20);
		}
		this.eftAccountNumber = eftAccountNumber;
	}

	public String getEftAccountNumber() {
		return (java.lang.String)HiltonUtility.ckNull(this.eftAccountNumber).trim();
	}

	public void setEftRoutingNumber(String eftRoutingNumber) {
		if (!HiltonUtility.isEmpty(eftRoutingNumber) && eftRoutingNumber.length() > 20) {
			eftRoutingNumber = eftRoutingNumber.substring(0, 20);
		}
		this.eftRoutingNumber = eftRoutingNumber;
	}

	public String getEftRoutingNumber() {
		return (java.lang.String)HiltonUtility.ckNull(this.eftRoutingNumber).trim();
	}

	public void setEftWireAccount(String eftWireAccount) {
		if (!HiltonUtility.isEmpty(eftWireAccount) && eftWireAccount.length() > 20) {
			eftWireAccount = eftWireAccount.substring(0, 20);
		}
		this.eftWireAccount = eftWireAccount;
	}

	public String getEftWireAccount() {
		return (java.lang.String)HiltonUtility.ckNull(this.eftWireAccount).trim();
	}

	public void setEftPersonName(String eftPersonName) {
		if (!HiltonUtility.isEmpty(eftPersonName) && eftPersonName.length() > 20) {
			eftPersonName = eftPersonName.substring(0, 20);
		}
		this.eftPersonName = eftPersonName;
	}

	public String getEftPersonName() {
		return (java.lang.String)HiltonUtility.ckNull(this.eftPersonName).trim();
	}

	public void setEftAccountType(String eftAccountType) {
		if (!HiltonUtility.isEmpty(eftAccountType) && eftAccountType.length() > 1) {
			eftAccountType = eftAccountType.substring(0, 1);
		}
		this.eftAccountType = eftAccountType;
	}

	public String getEftAccountType() {
		return (java.lang.String)HiltonUtility.ckNull(this.eftAccountType).trim();
	}

	public java.lang.String getVendor1099() {
		return (java.lang.String)HiltonUtility.ckNull(this.vendor1099).trim();
    }

    public void setVendor1099(java.lang.String vendor1099) {
		if (!HiltonUtility.isEmpty(vendor1099) && vendor1099.length() > 1) {
			vendor1099 = vendor1099.substring(0, 1);
		}
		this.vendor1099 = vendor1099;
    }

    public java.lang.String getPriorPassword() {
		return (java.lang.String)Utility.ckNull(this.priorPassword).trim();
    }

    public void setPriorPassword(java.lang.String priorPassword) {
		if (!HiltonUtility.isEmpty(priorPassword) && priorPassword.length() > 255) {
			priorPassword = priorPassword.substring(0, 255);
		}
		this.priorPassword = priorPassword;
    }
    public java.lang.String getPassChanged() {
		return (java.lang.String)Utility.ckNull(this.passChanged).trim();
    }

    public void setPassChanged(java.lang.String passChanged) {
		if (!HiltonUtility.isEmpty(passChanged) && passChanged.length() > 10) {
			passChanged = passChanged.substring(0, 10);
		}
		this.passChanged = passChanged;
    }

    public java.lang.String getLockLogin() {
		return (java.lang.String)HiltonUtility.ckNull(this.lockLogin).trim();
    }

    public void setLockLogin(java.lang.String lockLogin) {
		if (!HiltonUtility.isEmpty(lockLogin) && lockLogin.length() > 1) {
			lockLogin = lockLogin.substring(0, 1);
		}
		this.lockLogin = lockLogin;
    }

	public String toString() {
        return new ToStringBuilder(this)
            .append("comp_id", getComp_id())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof VendorRegister) ) return false;
        VendorRegister castOther = (VendorRegister) other;
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
