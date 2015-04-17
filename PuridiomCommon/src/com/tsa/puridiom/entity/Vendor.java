package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsagate.foundation.database.Entity;

import java.io.Serializable;
import java.math.BigDecimal;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class Vendor extends Entity implements Serializable {

    /** identifier field */
    private String vendorId;

    /** nullable persistent field */
    private String vendorName;

    /** nullable persistent field */
    private java.math.BigDecimal performance;

    /** nullable persistent field */
    private String vendorClass;

    /** nullable persistent field */
    private String fobId;

    /** nullable persistent field */
    private String vendTerms;

    /** nullable persistent field */
    private String vendPaymentType;

    /** nullable persistent field */
    private String shipVia;

    /** nullable persistent field */
    private String printFaxCode;

    /** nullable persistent field */
    private String faxNumber;

    /** nullable persistent field */
    private String vendorAccount;

    /** nullable persistent field */
    private String ediVendor;

    /** nullable persistent field */
    private String taxCode;

    /** nullable persistent field */
    private String currencyCode;

    /** nullable persistent field */
    private String vendorSic;

    /** nullable persistent field */
    private String vendorDuns;

    /** nullable persistent field */
    private String vendorEin;

    /** nullable persistent field */
    private String inspectionReqd;

    /** nullable persistent field */
    private java.math.BigDecimal poCopies;

    /** nullable persistent field */
    private String printPrices;

    /** nullable persistent field */
    private java.math.BigDecimal leadDays;

    /** nullable persistent field */
    private java.math.BigDecimal yearsInBusiness;

    /** nullable persistent field */
    private java.math.BigDecimal yearsAsVendor;

    /** nullable persistent field */
    private java.util.Date lastActive;

    /** nullable persistent field */
    private java.util.Date lastChange;

    /** nullable persistent field */
    private String vendUdf1;

    /** nullable persistent field */
    private String vendUdf2;

    /** nullable persistent field */
    private String vendUdf3;

    /** nullable persistent field */
    private String vendUdf4;

    /** nullable persistent field */
    private String vendUdf5;

    /** nullable persistent field */
    private String vendUdf6;

    /** nullable persistent field */
    private String vendUdf7;

    /** nullable persistent field */
    private String vendUdf8;

    /** nullable persistent field */
    private String vendUdf9;

    /** nullable persistent field */
    private String vendUdf10;

    /** nullable persistent field */
    private java.util.Date dateEntered;

    /** nullable persistent field */
    private java.util.Date dateExpires;

    /** nullable persistent field */
    private String status;

    /** nullable persistent field */
    private String owner;

    /** nullable persistent field */
    private String notes;

    /** nullable persistent field */
    private String vendor1099;

    /** nullable persistent field */
    private String apReference;

    /** nullable persistent field */
    private String ediAddress;

    /** nullable persistent field */
    private String emailAddress;

    /** nullable persistent field */
    private String webAddress;

    /** nullable persistent field */
    private String parentCode;

    /** nullable persistent field */
    private String pcardCode;

    /** nullable persistent field */
    private String lastChangedBy;

    /** nullable persistent field */
    private String apBatchid;

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
    private String termsAccepted;

    /** nullable persistent field */
    private String validated;

    /** nullable persistent field */
    private BigDecimal vendorRating;

    /** nullable persistent field */
    private String rated;

    /** nullable persistent field */
    private java.math.BigDecimal icHistory;

    /** nullable persistent field */
    private java.util.Date dateExported;

    /** identifier field */
	private java.math.BigDecimal iclLevel;
	
	/** nullable persistent field */
    private String vendorType;

    /** nullable not persistent field */
    private String buyer;
    
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
    
    /** full constructor */
    public Vendor(java.lang.String vendorId, java.lang.String vendorName, java.math.BigDecimal performance, java.lang.String vendorClass, java.lang.String fobId, java.lang.String vendTerms, java.lang.String shipVia, java.lang.String printFaxCode, java.lang.String faxNumber, java.lang.String vendorAccount, java.lang.String ediVendor, java.lang.String taxCode, java.lang.String currencyCode, java.lang.String vendorSic, java.lang.String vendorDuns, java.lang.String vendorEin, java.lang.String inspectionReqd, java.math.BigDecimal poCopies, java.lang.String printPrices, java.math.BigDecimal leadDays, java.math.BigDecimal yearsInBusiness, java.math.BigDecimal yearsAsVendor, java.util.Date lastActive, java.util.Date lastChange, java.lang.String vendUdf1, java.lang.String vendUdf2, java.lang.String vendUdf3, java.lang.String vendUdf4, java.lang.String vendUdf5, java.lang.String vendUdf6, java.lang.String vendUdf7, java.lang.String vendUdf8, java.lang.String vendUdf9, java.lang.String vendUdf10, java.util.Date dateEntered, java.util.Date dateExpires, java.lang.String status, java.lang.String owner, java.lang.String notes, java.lang.String vendor1099, java.lang.String apReference, java.lang.String ediAddress, java.lang.String emailAddress, java.lang.String webAddress, java.lang.String parentCode, java.lang.String pcardCode, java.lang.String lastChangedBy, java.lang.String apBatchid, java.lang.String refCompanyName, java.lang.String refPhoneNumber, java.lang.String refContactName, java.lang.String vendorNaics, java.lang.String diversityProgram, java.lang.String subcontract, java.lang.String ownershipType, java.lang.String diverseCertOrgs, String businessType, java.lang.String digitalSig, java.lang.String termsAccepted, java.lang.String validated, BigDecimal vendorRating, String rated, java.math.BigDecimal icHistory, java.util.Date dateExported, java.math.BigDecimal iclLevel, java.lang.String vendorType, java.lang.String buyer) {
        this.vendorId = vendorId;
        this.vendorName = vendorName;
        this.performance = performance;
        this.vendorClass = vendorClass;
        this.fobId = fobId;
        this.vendTerms = vendTerms;
        this.shipVia = shipVia;
        this.printFaxCode = printFaxCode;
        this.faxNumber = faxNumber;
        this.vendorAccount = vendorAccount;
        this.ediVendor = ediVendor;
        this.taxCode = taxCode;
        this.currencyCode = currencyCode;
        this.vendorSic = vendorSic;
        this.vendorDuns = vendorDuns;
        this.vendorEin = vendorEin;
        this.inspectionReqd = inspectionReqd;
        this.poCopies = poCopies;
        this.printPrices = printPrices;
        this.leadDays = leadDays;
        this.yearsInBusiness = yearsInBusiness;
        this.yearsAsVendor = yearsAsVendor;
        this.lastActive = lastActive;
        this.lastChange = lastChange;
        this.vendUdf1 = vendUdf1;
        this.vendUdf2 = vendUdf2;
        this.vendUdf3 = vendUdf3;
        this.vendUdf4 = vendUdf4;
        this.vendUdf5 = vendUdf5;
        this.vendUdf6 = vendUdf6;
        this.vendUdf7 = vendUdf7;
        this.vendUdf8 = vendUdf8;
        this.vendUdf9 = vendUdf9;
        this.vendUdf10 = vendUdf10;
        this.dateEntered = dateEntered;
        this.dateExpires = dateExpires;
        this.status = status;
        this.owner = owner;
        this.notes = notes;
        this.vendor1099 = vendor1099;
        this.apReference = apReference;
        this.ediAddress = ediAddress;
        this.emailAddress = emailAddress;
        this.webAddress = webAddress;
        this.parentCode = parentCode;
        this.pcardCode = pcardCode;
        this.lastChangedBy = lastChangedBy;
        this.apBatchid = apBatchid;
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
        this.termsAccepted = termsAccepted;
        this.validated = validated;
        this.vendorRating = vendorRating;
        this.rated = rated;
        this.icHistory = icHistory;
        this.dateExported = dateExported;
        this.iclLevel = iclLevel;
        this.vendorType = vendorType;
        this.buyer = buyer;
    }

    /** default constructor */
    public Vendor() {
    }

    /** minimal constructor */
    public Vendor(java.lang.String vendorId) {
        this.vendorId = vendorId;
    }

    public java.lang.String getVendorId() {
		return (java.lang.String)HiltonUtility.ckNull(this.vendorId).trim();
    }

    public void setVendorId(java.lang.String vendorId) {
		if (!HiltonUtility.isEmpty(vendorId) && vendorId.length() > 15) {
			vendorId = vendorId.substring(0, 15);
		}
		this.vendorId = vendorId;
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

    public java.math.BigDecimal getPerformance() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.performance);
    }

    public void setPerformance(java.math.BigDecimal performance) {
        this.performance = performance;
    }

    public java.lang.String getVendorClass() {
		return (java.lang.String)HiltonUtility.ckNull(this.vendorClass).trim();
    }

    public void setVendorClass(java.lang.String vendorClass) {
		if (!HiltonUtility.isEmpty(vendorClass) && vendorClass.length() > 40) {
			vendorClass = vendorClass.substring(0, 40);
		}
		this.vendorClass = vendorClass;
    }

    public java.lang.String getFobId() {
		return (java.lang.String)HiltonUtility.ckNull(this.fobId).trim();
    }

    public void setFobId(java.lang.String fobId) {
		if (!HiltonUtility.isEmpty(fobId) && fobId.length() > 15) {
			fobId = fobId.substring(0, 15);
		}
		this.fobId = fobId;
    }

    public java.lang.String getVendTerms() {
		return (java.lang.String)HiltonUtility.ckNull(this.vendTerms).trim();
    }

    public void setVendTerms(java.lang.String vendTerms) {
		if (!HiltonUtility.isEmpty(vendTerms) && vendTerms.length() > 15) {
			vendTerms = vendTerms.substring(0, 15);
		}
		this.vendTerms = vendTerms;
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

    public java.lang.String getShipVia() {
		return (java.lang.String)HiltonUtility.ckNull(this.shipVia).trim();
    }

    public void setShipVia(java.lang.String shipVia) {
		if (!HiltonUtility.isEmpty(shipVia) && shipVia.length() > 15) {
			shipVia = shipVia.substring(0, 15);
		}
		this.shipVia = shipVia;
    }

    public java.lang.String getPrintFaxCode() {
		return (java.lang.String)HiltonUtility.ckNull(this.printFaxCode).trim();
    }

    public void setPrintFaxCode(java.lang.String printFaxCode) {
		if (!HiltonUtility.isEmpty(printFaxCode) && printFaxCode.length() > 1) {
			printFaxCode = printFaxCode.substring(0, 1);
		}
		this.printFaxCode = printFaxCode;
    }

    public java.lang.String getFaxNumber() {
		return (java.lang.String)HiltonUtility.ckNull(this.faxNumber).trim();
    }

    public void setFaxNumber(java.lang.String faxNumber) {
		if (!HiltonUtility.isEmpty(faxNumber) && faxNumber.length() > 30) {
			faxNumber = faxNumber.substring(0, 30);
		}
		this.faxNumber = faxNumber;
    }

    public java.lang.String getVendorAccount() {
		return (java.lang.String)HiltonUtility.ckNull(this.vendorAccount).trim();
    }

    public void setVendorAccount(java.lang.String vendorAccount) {
		if (!HiltonUtility.isEmpty(vendorAccount) && vendorAccount.length() > 20) {
			vendorAccount = vendorAccount.substring(0, 20);
		}
		this.vendorAccount = vendorAccount;
    }

    public java.lang.String getEdiVendor() {
		return (java.lang.String)HiltonUtility.ckNull(this.ediVendor).trim();
    }

    public void setEdiVendor(java.lang.String ediVendor) {
		if (!HiltonUtility.isEmpty(ediVendor) && ediVendor.length() > 15) {
			ediVendor = ediVendor.substring(0, 15);
		}
		this.ediVendor = ediVendor;
    }

    public java.lang.String getTaxCode() {
		return (java.lang.String)HiltonUtility.ckNull(this.taxCode).trim();
    }

    public void setTaxCode(java.lang.String taxCode) {
		if (!HiltonUtility.isEmpty(taxCode) && taxCode.length() > 15) {
			taxCode = taxCode.substring(0, 15);
		}
		this.taxCode = taxCode;
    }

    public java.lang.String getCurrencyCode() {
		return (java.lang.String)HiltonUtility.ckNull(this.currencyCode).trim();
    }

    public void setCurrencyCode(java.lang.String currencyCode) {
		if (!HiltonUtility.isEmpty(currencyCode) && currencyCode.length() > 15) {
			currencyCode = currencyCode.substring(0, 15);
		}
		this.currencyCode = currencyCode;
    }

    public java.lang.String getVendorSic() {
		return (java.lang.String)HiltonUtility.ckNull(this.vendorSic).trim();
    }

    public void setVendorSic(java.lang.String vendorSic) {
		if (!HiltonUtility.isEmpty(vendorSic) && vendorSic.length() > 15) {
			vendorSic = vendorSic.substring(0, 15);
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

    public java.lang.String getVendorEin() {
		return (java.lang.String)HiltonUtility.ckNull(this.vendorEin).trim();
    }

    public void setVendorEin(java.lang.String vendorEin) {
		if (!HiltonUtility.isEmpty(vendorEin) && vendorEin.length() > 30) {
			vendorEin = vendorEin.substring(0, 30);
		}
		this.vendorEin = vendorEin;
    }

    public java.lang.String getInspectionReqd() {
		return (java.lang.String)HiltonUtility.ckNull(this.inspectionReqd).trim();
    }

    public void setInspectionReqd(java.lang.String inspectionReqd) {
		if (!HiltonUtility.isEmpty(inspectionReqd) && inspectionReqd.length() > 1) {
			inspectionReqd = inspectionReqd.substring(0, 1);
		}
		this.inspectionReqd = inspectionReqd;
    }

    public java.math.BigDecimal getPoCopies() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.poCopies);
    }

    public void setPoCopies(java.math.BigDecimal poCopies) {
        this.poCopies = poCopies;
    }

    public java.lang.String getPrintPrices() {
		return (java.lang.String)HiltonUtility.ckNull(this.printPrices).trim();
    }

    public void setPrintPrices(java.lang.String printPrices) {
		if (!HiltonUtility.isEmpty(printPrices) && printPrices.length() > 1) {
			printPrices = printPrices.substring(0, 1);
		}
		this.printPrices = printPrices;
    }

    public java.math.BigDecimal getLeadDays() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.leadDays);
    }

    public void setLeadDays(java.math.BigDecimal leadDays) {
        this.leadDays = leadDays;
    }

    public java.math.BigDecimal getYearsInBusiness() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.yearsInBusiness);
    }

    public void setYearsInBusiness(java.math.BigDecimal yearsInBusiness) {
        this.yearsInBusiness = yearsInBusiness;
    }

    public java.math.BigDecimal getYearsAsVendor() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.yearsAsVendor);
    }

    public void setYearsAsVendor(java.math.BigDecimal yearsAsVendor) {
        this.yearsAsVendor = yearsAsVendor;
    }

    public java.util.Date getLastActive() {
		return this.lastActive;
//		return HiltonUtility.ckNull(this.lastActive);
//		return (java.sql.Date)HiltonUtility.ckNull(this.lastActive);
    }

    public void setLastActive(java.util.Date lastActive) {
        this.lastActive = lastActive;
    }

    public java.util.Date getLastChange() {
		return this.lastChange;
//		return HiltonUtility.ckNull(this.lastChange);
//		return (java.sql.Date)HiltonUtility.ckNull(this.lastChange);
    }

    public void setLastChange(java.util.Date lastChange) {
        this.lastChange = lastChange;
    }

    public java.lang.String getVendUdf1() {
		return (java.lang.String)HiltonUtility.ckNull(this.vendUdf1).trim();
    }

    public void setVendUdf1(java.lang.String vendUdf1) {
		if (!HiltonUtility.isEmpty(vendUdf1) && vendUdf1.length() > 15) {
			vendUdf1 = vendUdf1.substring(0, 15);
		}
		this.vendUdf1 = vendUdf1;
    }

    public java.lang.String getVendUdf2() {
		return (java.lang.String)HiltonUtility.ckNull(this.vendUdf2).trim();
    }

    public void setVendUdf2(java.lang.String vendUdf2) {
		if (!HiltonUtility.isEmpty(vendUdf2) && vendUdf2.length() > 15) {
			vendUdf2 = vendUdf2.substring(0, 15);
		}
		this.vendUdf2 = vendUdf2;
    }

    public java.lang.String getVendUdf3() {
		return (java.lang.String)HiltonUtility.ckNull(this.vendUdf3).trim();
    }

    public void setVendUdf3(java.lang.String vendUdf3) {
		if (!HiltonUtility.isEmpty(vendUdf3) && vendUdf3.length() > 15) {
			vendUdf3 = vendUdf3.substring(0, 15);
		}
		this.vendUdf3 = vendUdf3;
    }

    public java.lang.String getVendUdf4() {
		return (java.lang.String)HiltonUtility.ckNull(this.vendUdf4).trim();
    }

    public void setVendUdf4(java.lang.String vendUdf4) {
		if (!HiltonUtility.isEmpty(vendUdf4) && vendUdf4.length() > 15) {
			vendUdf4 = vendUdf4.substring(0, 15);
		}
		this.vendUdf4 = vendUdf4;
    }

    public java.lang.String getVendUdf5() {
		return (java.lang.String)HiltonUtility.ckNull(this.vendUdf5).trim();
    }

    public void setVendUdf5(java.lang.String vendUdf5) {
		if (!HiltonUtility.isEmpty(vendUdf5) && vendUdf5.length() > 15) {
			vendUdf5 = vendUdf5.substring(0, 15);
		}
		this.vendUdf5 = vendUdf5;
    }

    public java.lang.String getVendUdf6() {
		return (java.lang.String)HiltonUtility.ckNull(this.vendUdf6).trim();
    }

    public void setVendUdf6(java.lang.String vendUdf6) {
		if (!HiltonUtility.isEmpty(vendUdf6) && vendUdf6.length() > 15) {
			vendUdf6 = vendUdf6.substring(0, 15);
		}
		this.vendUdf6 = vendUdf6;
    }

    public java.lang.String getVendUdf7() {
		return (java.lang.String)HiltonUtility.ckNull(this.vendUdf7).trim();
    }

    public void setVendUdf7(java.lang.String vendUdf7) {
		if (!HiltonUtility.isEmpty(vendUdf7) && vendUdf7.length() > 15) {
			vendUdf7 = vendUdf7.substring(0, 15);
		}
		this.vendUdf7 = vendUdf7;
    }

    public java.lang.String getVendUdf8() {
		return (java.lang.String)HiltonUtility.ckNull(this.vendUdf8).trim();
    }

    public void setVendUdf8(java.lang.String vendUdf8) {
		if (!HiltonUtility.isEmpty(vendUdf8) && vendUdf8.length() > 15) {
			vendUdf8 = vendUdf8.substring(0, 15);
		}
		this.vendUdf8 = vendUdf8;
    }

    public java.lang.String getVendUdf9() {
		return (java.lang.String)HiltonUtility.ckNull(this.vendUdf9).trim();
    }

    public void setVendUdf9(java.lang.String vendUdf9) {
		if (!HiltonUtility.isEmpty(vendUdf9) && vendUdf9.length() > 15) {
			vendUdf9 = vendUdf9.substring(0, 15);
		}
		this.vendUdf9 = vendUdf9;
    }

    public java.lang.String getVendUdf10() {
		return (java.lang.String)HiltonUtility.ckNull(this.vendUdf10).trim();
    }

    public void setVendUdf10(java.lang.String vendUdf10) {
		if (!HiltonUtility.isEmpty(vendUdf10) && vendUdf10.length() > 15) {
			vendUdf10 = vendUdf10.substring(0, 15);
		}
		this.vendUdf10 = vendUdf10;
    }

    public java.util.Date getDateEntered() {
		return this.dateEntered;
//		return HiltonUtility.ckNull(this.dateEntered);
//		return (java.sql.Date)HiltonUtility.ckNull(this.dateEntered);
    }

    public void setDateEntered(java.util.Date dateEntered) {
        this.dateEntered = dateEntered;
    }

    public java.util.Date getDateExpires() {
		return this.dateExpires;
//		return HiltonUtility.ckNull(this.dateExpires);
//		return (java.sql.Date)HiltonUtility.ckNull(this.dateExpires);
    }

    public void setDateExpires(java.util.Date dateExpires) {
        this.dateExpires = dateExpires;
    }

    public java.lang.String getStatus() {
		return (java.lang.String)HiltonUtility.ckNull(this.status).trim();
    }

    public void setStatus(java.lang.String status) {
		if (!HiltonUtility.isEmpty(status) && status.length() > 4) {
			status = status.substring(0, 4);
		}
		this.status = status;
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

    public java.lang.String getNotes() {
		return (java.lang.String)HiltonUtility.ckNull(this.notes).trim();
    }

    public void setNotes(java.lang.String notes) {
		if (!HiltonUtility.isEmpty(notes) && notes.length() > 255) {
			notes = notes.substring(0, 255);
		}
		this.notes = notes;
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

    public java.lang.String getApReference() {
		return (java.lang.String)HiltonUtility.ckNull(this.apReference).trim();
    }

    public void setApReference(java.lang.String apReference) {
		if (!HiltonUtility.isEmpty(apReference) && apReference.length() > 20) {
			apReference = apReference.substring(0, 20);
		}
		this.apReference = apReference;
    }

    public java.lang.String getEdiAddress() {
		return (java.lang.String)HiltonUtility.ckNull(this.ediAddress).trim();
    }

    public void setEdiAddress(java.lang.String ediAddress) {
		if (!HiltonUtility.isEmpty(ediAddress) && ediAddress.length() > 20) {
			ediAddress = ediAddress.substring(0, 20);
		}
		this.ediAddress = ediAddress;
    }

    public java.lang.String getEmailAddress() {
		return (java.lang.String)HiltonUtility.ckNull(this.emailAddress).trim();
    }

    public void setEmailAddress(java.lang.String emailAddress) {
		if (!HiltonUtility.isEmpty(emailAddress) && emailAddress.length() > 40) {
			emailAddress = emailAddress.substring(0, 40);
		}
		this.emailAddress = emailAddress;
    }

    public java.lang.String getWebAddress() {
		return (java.lang.String)HiltonUtility.ckNull(this.webAddress).trim();
    }

    public void setWebAddress(java.lang.String webAddress) {
		if (!HiltonUtility.isEmpty(webAddress) && webAddress.length() > 60) {
			webAddress = webAddress.substring(0, 60);
		}
		this.webAddress = webAddress;
    }

    public java.lang.String getParentCode() {
		return (java.lang.String)HiltonUtility.ckNull(this.parentCode).trim();
    }

    public void setParentCode(java.lang.String parentCode) {
		if (!HiltonUtility.isEmpty(parentCode) && parentCode.length() > 15) {
			parentCode = parentCode.substring(0, 15);
		}
		this.parentCode = parentCode;
    }

    public java.lang.String getPcardCode() {
		return (java.lang.String)HiltonUtility.ckNull(this.pcardCode).trim();
    }

    public void setPcardCode(java.lang.String pcardCode) {
		if (!HiltonUtility.isEmpty(pcardCode) && pcardCode.length() > 1) {
			pcardCode = pcardCode.substring(0, 1);
		}
		this.pcardCode = pcardCode;
    }

    public java.lang.String getLastChangedBy() {
		return (java.lang.String)HiltonUtility.ckNull(this.lastChangedBy).trim();
    }

    public void setLastChangedBy(java.lang.String lastChangedBy) {
		if (!HiltonUtility.isEmpty(lastChangedBy) && lastChangedBy.length() > 15) {
			lastChangedBy = lastChangedBy.substring(0, 15);
		}
		this.lastChangedBy = lastChangedBy;
    }

    public java.lang.String getApBatchid() {
		return (java.lang.String)HiltonUtility.ckNull(this.apBatchid).trim();
    }

    public void setApBatchid(java.lang.String apBatchid) {
		if (!HiltonUtility.isEmpty(apBatchid) && apBatchid.length() > 6) {
			apBatchid = apBatchid.substring(0, 6);
		}
		this.apBatchid = apBatchid;
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

    public java.lang.String getTermsAccepted() {
		return (java.lang.String)HiltonUtility.ckNull(this.termsAccepted).trim();
    }

    public void setTermsAccepted(java.lang.String  termsAccepted) {
		if (!HiltonUtility.isEmpty( termsAccepted) &&  termsAccepted.length() > 10) {
		    termsAccepted =  termsAccepted.substring(0, 10);
		}
		this.termsAccepted =  termsAccepted;
    }

    public java.lang.String getValidated() {
		return (java.lang.String)HiltonUtility.ckNull(this.validated).trim();
    }

    public void setValidated(java.lang.String  validated) {
		if (!HiltonUtility.isEmpty(validated) &&  validated.length() > 1) {
		    validated =  validated.substring(0, 1);
		}
		this.validated =  validated;
    }

    public boolean isVendorValidated() {
        if (this.getValidated().equalsIgnoreCase("Y")) {
            return true;
        }
        return false;
    }

    public BigDecimal getVendorRating() {
        if(this.vendorRating == null)
        {
            return new BigDecimal(0);
        }
        else
        {
            return vendorRating;
        }
    }

    public String getRated() {
        if(HiltonUtility.isEmpty(this.rated)){
            this.rated = "N";
        }
        return this.rated;
    }

    public void setRated(String rated) {
        if (!HiltonUtility.isEmpty(rated) && rated.length() > 1) {
            rated = rated.substring(0, 1);
        }
        this.rated = rated;
    }

    public void setVendorRating(BigDecimal vendorRating) {
        this.vendorRating = vendorRating;
    }

    public java.math.BigDecimal getIcHistory() {
		return (java.math.BigDecimal) HiltonUtility.ckNull(this.icHistory);
    }

    public void setIcHistory(java.math.BigDecimal icHistory) {
        this.icHistory = icHistory;
    }

    public java.util.Date getDateExported() {
		return this.dateExported;
    }

    public void setDateExported(java.util.Date dateExported) {
        this.dateExported = dateExported;
    }

    public java.math.BigDecimal getIclLevel() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.iclLevel);
	}

	public void setIclLevel(java.math.BigDecimal iclLevel) {
		this.iclLevel = iclLevel;
	}

    public java.lang.String getVendorType() {
		return (java.lang.String)HiltonUtility.ckNull(this.vendorType).trim();
    }

    public void setVendorType(java.lang.String vendorType) {
		if (!HiltonUtility.isEmpty(vendorType) && vendorType.length() > 1) {
			vendorType = vendorType.substring(0, 1);
		}
		this.vendorType = vendorType;
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
	
	public void setBuyer(String buyer) {
		this.buyer = buyer;
	}

	public String getBuyer() {
		return (java.lang.String)HiltonUtility.ckNull(this.buyer).trim();
	}
	
    public String toString() {
        return new ToStringBuilder(this)
            .append("vendorId", getVendorId())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof Vendor) ) return false;
        Vendor castOther = (Vendor) other;
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
