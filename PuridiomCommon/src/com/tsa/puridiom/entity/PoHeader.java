package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsagate.foundation.database.Entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.currcode.CurrencyManager;
import com.tsagate.foundation.database.Entity;
import com.tsagate.foundation.utility.Utility;

/** @author Hibernate CodeGenerator */
public class PoHeader extends Entity implements Serializable {

    /** identifier field */
    private java.math.BigDecimal icPoHeader;

    /** nullable persistent field */
    private String poType;

    /** nullable persistent field */
    private String poNumber;

    /** nullable persistent field */
    private java.math.BigDecimal releaseNumber;

    /** nullable persistent field */
    private java.math.BigDecimal revisionNumber;

    /** nullable persistent field */
    private String contractNo;

    /** nullable persistent field */
    private java.util.Date poDate;

    /** nullable persistent field */
    private java.util.Date requiredDate;

    /** nullable persistent field */
    private java.util.Date promisedDate;

    /** nullable persistent field */
    private java.util.Date revisionDate;

    /** nullable persistent field */
    private String vendorId;

    /** nullable persistent field */
    private String vendContactCode;

    /** nullable persistent field */
    private String buyerCode;

    /** nullable persistent field */
    private String termsCode;

    /** nullable persistent field */
    private String fobCode;

    /** nullable persistent field */
    private String shipViaCode;

    /** nullable persistent field */
    private String currencyCode;

    /** nullable persistent field */
    private String shipToCode;

    /** nullable persistent field */
    private String shipToContact;

    /** nullable persistent field */
    private String billToCode;

    /** nullable persistent field */
    private String billToContact;

    /** nullable persistent field */
    private java.util.Date effectiveDate;

    /** nullable persistent field */
    private java.util.Date expirationDate;

    /** nullable persistent field */
    private java.math.BigDecimal blanketLimit;

    /** nullable persistent field */
    private java.math.BigDecimal releaseLimit;

    /** nullable persistent field */
    private String confirming;

    /** nullable persistent field */
    private java.util.Date confirmDate;

    /** nullable persistent field */
    private String confirmNameCode;

    /** nullable persistent field */
    private java.math.BigDecimal poCopies;

    /** nullable persistent field */
    private String ediOrder;

    /** nullable persistent field */
    private java.util.Date datePrinted;

    /** nullable persistent field */
    private java.util.Date dateFaxed;

    /** nullable persistent field */
    private java.util.Date dateEdiXmit;

    /** nullable persistent field */
    private String taxCode;

    /** nullable persistent field */
    private java.math.BigDecimal taxPercent;

    /** nullable persistent field */
    private java.math.BigDecimal taxAmount;

    /** nullable persistent field */
    private String discountCode;

    /** nullable persistent field */
    private java.math.BigDecimal discountPercent;

    /** nullable persistent field */
    private java.math.BigDecimal discountAmount;

    /** nullable persistent field */
    private java.math.BigDecimal shippingCharges;

    /** nullable persistent field */
    private String shippingTaxable;

    /** nullable persistent field */
    private java.math.BigDecimal shippingTax;

    /** nullable persistent field */
    private java.math.BigDecimal otherCharges;

    /** nullable persistent field */
    private String otherDescription;

    /** nullable persistent field */
    private String otherTaxable;

    /** nullable persistent field */
    private java.math.BigDecimal otherTax;

    /** nullable persistent field */
    private String prePaid;

    /** nullable persistent field */
    private String udf1Code;

    /** nullable persistent field */
    private String udf2Code;

    /** nullable persistent field */
    private String udf3Code;

    /** nullable persistent field */
    private String udf4Code;

    /** nullable persistent field */
    private String udf5Code;

    /** nullable persistent field */
    private String udf6Code;

    /** nullable persistent field */
    private String udf7Code;

    /** nullable persistent field */
    private String udf8Code;

    /** nullable persistent field */
    private String udf9Code;

    /** nullable persistent field */
    private String udf10Code;

    /** nullable persistent field */
    private String udf11Code;

    /** nullable persistent field */
    private String udf12Code;

    /** nullable persistent field */
    private String udf13Code;

    /** nullable persistent field */
    private String udf14Code;

    /** nullable persistent field */
    private String udf15Code;

    /** nullable persistent field */
    private String status;

    /** nullable persistent field */
    private String internalComments;

    /** nullable persistent field */
    private String owner;

    /** nullable persistent field */
    private java.util.Date dateEntered;

    /** nullable persistent field */
    private String lastRelease;

    /** nullable persistent field */
    private String lastRevision;

    /** nullable persistent field */
    private String requisitionNumber;

    /** nullable persistent field */
    private String fiscalYear;

    /** nullable persistent field */
    private String language;

    /** nullable persistent field */
    private java.math.BigDecimal currencyFactor;

    /** nullable persistent field */
    private java.math.BigDecimal total;

    /** nullable persistent field */
    private java.math.BigDecimal subtotal;

    /** nullable persistent field */
    private java.math.BigDecimal icReqHeader;

    /** nullable persistent field */
    private java.math.BigDecimal icRfqHeader;

    /** nullable persistent field */
    private String approved;

    /** nullable persistent field */
    private String appBy;

    /** nullable persistent field */
    private java.util.Date appDate;

    /** nullable persistent field */
    private String appSigned;

    /** nullable persistent field */
    private String lastChgBy;

    /** nullable persistent field */
    private java.util.Date lastChgDate;

    /** nullable persistent field */
    private String rfqNumber;

    /** nullable persistent field */
    private String ediStatus;

    /** nullable persistent field */
    private String receiptRequired;

    /** nullable persistent field */
    private String contactAddr;

    /** nullable persistent field */
    private String pyStatus;

    /** nullable persistent field */
    private java.math.BigDecimal savings;

    /** nullable persistent field */
    private String savingsReason;

    /** nullable persistent field */
    private String linkNextOrder;

    /** nullable persistent field */
    private String linkPriorOrder;

    /** nullable persistent field */
    private String apBatchid;

    /** nullable persistent field */
    private java.math.BigDecimal icNextOrderLink;

    /** nullable persistent field */
    private java.math.BigDecimal icPriorOrderLink;

    /** nullable persistent field */
    private String pcardOrder;

    /** nullable persistent field */
    private String pcardName;

    /** nullable persistent field */
    private String pcardHolder;

    /** nullable persistent field */
    private String pcardNumber;

    /** nullable persistent field */
    private String pcardExpires;

    /** nullable persistent field */
    private String lockStatus;

    /** nullable persistent field */
    private String vendorName;

    /** nullable persistent field */
    private String poRecalc;

    /** nullable persistent field */
    private String actionAlertFlag;

    /** nullable persistent field */
    private java.math.BigDecimal obligAmt;

    /** nullable persistent field */
    private java.util.Date obligDate;

    /** nullable persistent field */
    private String obligNum;

    /** nullable persistent field */
    private String vendorEval;

    /** nullable persistent field */
    private String requisitionerCode;

    /** nullable persistent field */
    private String departmentCode;

    /** nullable persistent field */
    private String priorityCode;

    /** nullable persistent field */
    private java.math.BigDecimal icHeaderHistory;

    /** nullable persistent field */
    private java.lang.String contactName;

    /** nullable persistent field */
    private java.lang.String itemLocation;

    /** nullable persistent field */
    private java.lang.String routing;

    /** nullable persistent field */
    private String authorizationCode;

    /** nullable persistent field */
	private String useTaxCode;

	 /** nullable persistent field */
    private java.math.BigDecimal useTaxPercent;

    /** nullable persistent field */
    private java.math.BigDecimal useTaxAmount;

    /** nullable persistent field */
	private String contactPhone;

    /** nullable persistent field */
	private String contactMobilePhone;

    /** nullable persistent field */
    private java.util.Date dateAcknowledged;

    /** nullable persistent field */
    private String acknowledgedBy;

    /** nullable persistent field */
    private String shipToInv;

    /** nullable persistent field */
    private java.math.BigDecimal icHeaderKey;

    /** nullable persistent field */
    private String timeZone;

    /** nullable persistent field */
    private String gfpGfm;

    /** nullable persistent field */
    private String requestCat;

    /** nullable persistent field */
    private String kit;

    /** nullable persistent field */
    private String workOrder;

    /** nullable persistent field */
    private String subType;

    /** nullable persistent field */
    private String insuranceRqd;

    /** nullable persistent field */
    private String naics;

    /** nullable persistent field */
    private java.math.BigDecimal iclLevel;

    private java.math.BigDecimal estimatedCost;

    private List accountList;

    private List docCommentList;

    private List docAttachmentList;

    private List scheduleList;

    private List poLineList;

    private List reviewFinalizeList;

    private Address billToAddress;

    private Address shipToAddress;

    private Address vendorAddress;

    private BigDecimal revisionValue;

    private String autoRelease;

    private BigDecimal autoInterval;

    private String autoReleased;

    private String autoPayment;

    private String autoImport;

    private String vendorClass;

    private BigDecimal vendorRating;

    private String rated;

    private String inspectionReqd;

    private String printMode;

    private String bidWaiver;

    private String originalReqType;

    private java.math.BigDecimal totalUSD;

    /** nullable persistent field */
    private java.math.BigDecimal icMsrHeader;

    /** nullable persistent field */
    private String msrNumber;

    /** nullable persistent field */
    private String corrosionEval;

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

	/** full constructor */
    public PoHeader(java.math.BigDecimal icPoHeader, java.lang.String poType, java.lang.String poNumber, java.math.BigDecimal releaseNumber, java.math.BigDecimal revisionNumber, java.lang.String contractNo, java.util.Date poDate, java.util.Date requiredDate, java.util.Date promisedDate, java.util.Date revisionDate, java.lang.String vendorId, java.lang.String vendContactCode, java.lang.String buyerCode, java.lang.String termsCode, java.lang.String fobCode, java.lang.String shipViaCode, java.lang.String currencyCode, java.lang.String shipToCode, java.lang.String shipToContact, java.lang.String billToCode, java.lang.String billToContact, java.util.Date effectiveDate, java.util.Date expirationDate, java.math.BigDecimal blanketLimit, java.math.BigDecimal releaseLimit, java.lang.String confirming, java.util.Date confirmDate, java.lang.String confirmNameCode, java.math.BigDecimal poCopies, java.lang.String ediOrder, java.util.Date datePrinted, java.util.Date dateFaxed, java.util.Date dateEdiXmit, java.lang.String taxCode, java.math.BigDecimal taxPercent, java.math.BigDecimal taxAmount, java.lang.String discountCode, java.math.BigDecimal discountPercent, java.math.BigDecimal discountAmount, java.math.BigDecimal shippingCharges, java.lang.String shippingTaxable, java.math.BigDecimal shippingTax, java.math.BigDecimal otherCharges, java.lang.String otherDescription, java.lang.String otherTaxable, java.math.BigDecimal otherTax, java.lang.String prePaid, java.lang.String udf1Code, java.lang.String udf2Code, java.lang.String udf3Code, java.lang.String udf4Code, java.lang.String udf5Code, java.lang.String udf6Code, java.lang.String udf7Code, java.lang.String udf8Code, java.lang.String udf9Code, java.lang.String udf10Code, java.lang.String udf11Code, java.lang.String udf12Code, java.lang.String udf13Code, java.lang.String udf14Code, java.lang.String udf15Code, java.lang.String status, java.lang.String internalComments, java.lang.String owner, java.util.Date dateEntered, java.lang.String lastRelease, java.lang.String lastRevision, java.lang.String requisitionNumber, java.lang.String fiscalYear, java.lang.String language, java.math.BigDecimal currencyFactor, java.math.BigDecimal total, java.math.BigDecimal subtotal, java.math.BigDecimal icReqHeader, java.math.BigDecimal icRfqHeader, java.lang.String approved, java.lang.String appBy, java.util.Date appDate, java.lang.String appSigned, java.lang.String lastChgBy, java.util.Date lastChgDate, java.lang.String rfqNumber, java.lang.String ediStatus, java.lang.String receiptRequired, java.lang.String contactAddr, java.lang.String pyStatus, java.math.BigDecimal savings, java.lang.String savingsReason, java.lang.String linkNextOrder, java.lang.String linkPriorOrder, java.lang.String apBatchid, java.math.BigDecimal icNextOrderLink, java.math.BigDecimal icPriorOrderLink, java.lang.String pcardOrder, java.lang.String pcardName, java.lang.String pcardHolder, java.lang.String pcardNumber, java.lang.String pcardExpires, java.lang.String lockStatus, java.lang.String vendorName, java.lang.String poRecalc, java.lang.String actionAlertFlag, java.math.BigDecimal obligAmt, java.util.Date obligDate, java.lang.String vendorEval, java.lang.String requisitionerCode, java.lang.String departmentCode, java.lang.String priorityCode, java.math.BigDecimal icHeaderHistory, java.lang.String authorizationCode, java.lang.String vendorClass, java.math.BigDecimal vendorRating, java.lang.String rated, java.lang.String useTaxCode, java.math.BigDecimal useTaxAmount, java.math.BigDecimal useTaxPercent, java.lang.String inspectionReqd, java.lang.String contactPhone, java.lang.String contactMobilePhone, java.util.Date dateAcknowledged, java.lang.String acknowledgedBy, String shipToInv, java.lang.String obligNum, java.math.BigDecimal icHeaderKey, java.lang.String timeZone, String bidWaiver, java.math.BigDecimal iclLevel, java.lang.String gfpGfm, java.lang.String requestCat, java.lang.String kit, java.lang.String workOrder, java.lang.String subType, java.lang.String insuranceRqd, java.lang.String naics, java.lang.String corrosionEval) {
        this.icPoHeader = icPoHeader;
        this.poType = poType;
        this.poNumber = poNumber;
        this.releaseNumber = releaseNumber;
        this.revisionNumber = revisionNumber;
        this.contractNo = contractNo;
        this.poDate = poDate;
        this.requiredDate = requiredDate;
        this.promisedDate = promisedDate;
        this.revisionDate = revisionDate;
        this.vendorId = vendorId;
        this.vendContactCode = vendContactCode;
        this.buyerCode = buyerCode;
        this.termsCode = termsCode;
        this.fobCode = fobCode;
        this.shipViaCode = shipViaCode;
        this.currencyCode = currencyCode;
        this.shipToCode = shipToCode;
        this.shipToContact = shipToContact;
        this.billToCode = billToCode;
        this.billToContact = billToContact;
        this.effectiveDate = effectiveDate;
        this.expirationDate = expirationDate;
        this.blanketLimit = blanketLimit;
        this.releaseLimit = releaseLimit;
        this.confirming = confirming;
        this.confirmDate = confirmDate;
        this.confirmNameCode = confirmNameCode;
        this.poCopies = poCopies;
        this.ediOrder = ediOrder;
        this.datePrinted = datePrinted;
        this.dateFaxed = dateFaxed;
        this.dateEdiXmit = dateEdiXmit;
        this.taxCode = taxCode;
        this.taxPercent = taxPercent;
        this.taxAmount = taxAmount;
        this.discountCode = discountCode;
        this.discountPercent = discountPercent;
        this.discountAmount = discountAmount;
        this.shippingCharges = shippingCharges;
        this.shippingTaxable = shippingTaxable;
        this.shippingTax = shippingTax;
        this.otherCharges = otherCharges;
        this.otherDescription = otherDescription;
        this.otherTaxable = otherTaxable;
        this.otherTax = otherTax;
        this.prePaid = prePaid;
        this.udf1Code = udf1Code;
        this.udf2Code = udf2Code;
        this.udf3Code = udf3Code;
        this.udf4Code = udf4Code;
        this.udf5Code = udf5Code;
        this.udf6Code = udf6Code;
        this.udf7Code = udf7Code;
        this.udf8Code = udf8Code;
        this.udf9Code = udf9Code;
        this.udf10Code = udf10Code;
        this.udf11Code = udf11Code;
        this.udf12Code = udf12Code;
        this.udf13Code = udf13Code;
        this.udf14Code = udf14Code;
        this.udf15Code = udf15Code;
        this.status = status;
        this.internalComments = internalComments;
        this.owner = owner;
        this.dateEntered = dateEntered;
        this.lastRelease = lastRelease;
        this.lastRevision = lastRevision;
        this.requisitionNumber = requisitionNumber;
        this.fiscalYear = fiscalYear;
        this.language = language;
        this.currencyFactor = currencyFactor;
        this.total = total;
        this.subtotal = subtotal;
        this.icReqHeader = icReqHeader;
        this.icRfqHeader = icRfqHeader;
        this.approved = approved;
        this.appBy = appBy;
        this.appDate = appDate;
        this.appSigned = appSigned;
        this.lastChgBy = lastChgBy;
        this.lastChgDate = lastChgDate;
        this.rfqNumber = rfqNumber;
        this.ediStatus = ediStatus;
        this.receiptRequired = receiptRequired;
        this.contactAddr = contactAddr;
        this.pyStatus = pyStatus;
        this.savings = savings;
        this.savingsReason = savingsReason;
        this.linkNextOrder = linkNextOrder;
        this.linkPriorOrder = linkPriorOrder;
        this.apBatchid = apBatchid;
        this.icNextOrderLink = icNextOrderLink;
        this.icPriorOrderLink = icPriorOrderLink;
        this.pcardOrder = pcardOrder;
        this.pcardName = pcardName;
        this.pcardHolder = pcardHolder;
        this.pcardNumber = pcardNumber;
        this.pcardExpires = pcardExpires;
        this.lockStatus = lockStatus;
        this.vendorName = vendorName;
        this.poRecalc = poRecalc;
        this.actionAlertFlag = actionAlertFlag;
        this.obligAmt = obligAmt;
        this.obligDate = obligDate;
        this.obligNum = obligNum;
        this.vendorEval = vendorEval;
        this.requisitionerCode = requisitionerCode;
        this.departmentCode = departmentCode;
        this.priorityCode = priorityCode;
        this.icHeaderHistory = icHeaderHistory;
        this.authorizationCode = authorizationCode;
        this.vendorClass = vendorClass;
        this.vendorRating = vendorRating;
        this.rated = rated;
        this.useTaxAmount = useTaxAmount;
        this.useTaxCode = useTaxCode;
        this.useTaxPercent = useTaxPercent;
        this.inspectionReqd = inspectionReqd;
        this.contactPhone = contactPhone;
        this.contactMobilePhone = contactMobilePhone;
        this.dateAcknowledged = dateAcknowledged;
        this.acknowledgedBy = acknowledgedBy;
        this.shipToInv = shipToInv;
        this.icHeaderKey = icHeaderKey;
        this.timeZone = timeZone;
        this.bidWaiver = bidWaiver;
        this.iclLevel = iclLevel;
        this.gfpGfm = gfpGfm;
        this.requestCat = requestCat;
        this.kit = kit;
        this.workOrder = workOrder;
        this.subType = subType;
        this.insuranceRqd = insuranceRqd;
        this.naics = naics;
        this.corrosionEval = corrosionEval;
    }

    /** default constructor */
    public PoHeader() {
    }

    /** minimal constructor */
    public PoHeader(java.math.BigDecimal icPoHeader) {
        this.icPoHeader = icPoHeader;
    }

    public java.math.BigDecimal getIcPoHeader() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.icPoHeader);
    }

    public void setIcPoHeader(java.math.BigDecimal icPoHeader) {
        this.icPoHeader = icPoHeader;
    }

    public java.lang.String getPoType() {
		return (java.lang.String)HiltonUtility.ckNull(this.poType).trim();
    }

    public void setPoType(java.lang.String poType) {
		if (!HiltonUtility.isEmpty(poType) && poType.length() > 2) {
			poType = poType.substring(0, 2);
		}
		this.poType = poType;
    }

    public java.lang.String getPoNumber() {
		return (java.lang.String)HiltonUtility.ckNull(this.poNumber).trim();
    }

    public void setPoNumber(java.lang.String poNumber) {
		if (!HiltonUtility.isEmpty(poNumber) && poNumber.length() > 20) {
			poNumber = poNumber.substring(0, 20);
		}
		this.poNumber = poNumber;
    }

    public java.math.BigDecimal getReleaseNumber() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.releaseNumber);
    }

    public void setReleaseNumber(java.math.BigDecimal releaseNumber) {
        this.releaseNumber = releaseNumber;
    }

    public java.math.BigDecimal getRevisionNumber() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.revisionNumber);
    }

    public void setRevisionNumber(java.math.BigDecimal revisionNumber) {
        this.revisionNumber = revisionNumber;
    }

    public java.lang.String getContractNo() {
		return (java.lang.String)HiltonUtility.ckNull(this.contractNo).trim();
    }

    public void setContractNo(java.lang.String contractNo) {
		if (!HiltonUtility.isEmpty(contractNo) && contractNo.length() > 25) {
			contractNo = contractNo.substring(0, 25);
		}
		this.contractNo = contractNo;
    }

    public java.util.Date getPoDate() {
        return this.poDate;
//		return HiltonUtility.ckNull(this.poDate);
//		return (java.sql.Date)HiltonUtility.ckNull(this.poDate);
    }

    public void setPoDate(java.util.Date poDate) {
        this.poDate = poDate;
    }

    public java.util.Date getRequiredDate() {
        return this.requiredDate;
//		return HiltonUtility.ckNull(this.requiredDate);
//		return (java.sql.Date)HiltonUtility.ckNull(this.requiredDate);
    }

    public void setRequiredDate(java.util.Date requiredDate) {
        this.requiredDate = requiredDate;
    }

    public java.util.Date getPromisedDate() {
        return this.promisedDate;
//		return HiltonUtility.ckNull(this.promisedDate);
//		return (java.sql.Date)HiltonUtility.ckNull(this.promisedDate);
    }

    public void setPromisedDate(java.util.Date promisedDate) {
        this.promisedDate = promisedDate;
    }

    public java.util.Date getRevisionDate() {
        return this.revisionDate;
//		return HiltonUtility.ckNull(this.revisionDate);
//		return (java.sql.Date)HiltonUtility.ckNull(this.revisionDate);
    }

    public void setRevisionDate(java.util.Date revisionDate) {
        this.revisionDate = revisionDate;
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

    public java.lang.String getVendContactCode() {
		return (java.lang.String)HiltonUtility.ckNull(this.vendContactCode).trim();
    }

    public void setVendContactCode(java.lang.String vendContactCode) {
		if (!HiltonUtility.isEmpty(vendContactCode) && vendContactCode.length() > 15) {
			vendContactCode = vendContactCode.substring(0, 15);
		}
		this.vendContactCode = vendContactCode;
    }

    public java.lang.String getBuyerCode() {
		return (java.lang.String)HiltonUtility.ckNull(this.buyerCode).trim();
    }

    public void setBuyerCode(java.lang.String buyerCode) {
		if (!HiltonUtility.isEmpty(buyerCode) && buyerCode.length() > 15) {
			buyerCode = buyerCode.substring(0, 15);
		}
		this.buyerCode = buyerCode;
    }

    public java.lang.String getTermsCode() {
		return (java.lang.String)HiltonUtility.ckNull(this.termsCode).trim();
    }

    public void setTermsCode(java.lang.String termsCode) {
		if (!HiltonUtility.isEmpty(termsCode) && termsCode.length() > 15) {
			termsCode = termsCode.substring(0, 15);
		}
		this.termsCode = termsCode;
    }

    public java.lang.String getFobCode() {
		return (java.lang.String)HiltonUtility.ckNull(this.fobCode).trim();
    }

    public void setFobCode(java.lang.String fobCode) {
		if (!HiltonUtility.isEmpty(fobCode) && fobCode.length() > 15) {
			fobCode = fobCode.substring(0, 15);
		}
		this.fobCode = fobCode;
    }

    public java.lang.String getShipViaCode() {
		return (java.lang.String)HiltonUtility.ckNull(this.shipViaCode).trim();
    }

    public void setShipViaCode(java.lang.String shipViaCode) {
		if (!HiltonUtility.isEmpty(shipViaCode) && shipViaCode.length() > 15) {
			shipViaCode = shipViaCode.substring(0, 15);
		}
		this.shipViaCode = shipViaCode;
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

    public java.lang.String getShipToCode() {
		return (java.lang.String)HiltonUtility.ckNull(this.shipToCode).trim();
    }

    public void setShipToCode(java.lang.String shipToCode) {
		if (!HiltonUtility.isEmpty(shipToCode) && shipToCode.length() > 15) {
			shipToCode = shipToCode.substring(0, 15);
		}
		this.shipToCode = shipToCode;
    }

    public java.lang.String getShipToContact() {
		return (java.lang.String)HiltonUtility.ckNull(this.shipToContact).trim();
    }

    public void setShipToContact(java.lang.String shipToContact) {
		if (!HiltonUtility.isEmpty(shipToContact) && shipToContact.length() > 40) {
			shipToContact = shipToContact.substring(0, 40);
		}
		this.shipToContact = shipToContact;
    }

    public java.lang.String getBillToCode() {
		return (java.lang.String)HiltonUtility.ckNull(this.billToCode).trim();
    }

    public void setBillToCode(java.lang.String billToCode) {
		if (!HiltonUtility.isEmpty(billToCode) && billToCode.length() > 15) {
			billToCode = billToCode.substring(0, 15);
		}
		this.billToCode = billToCode;
    }

    public java.lang.String getBillToContact() {
		return (java.lang.String)HiltonUtility.ckNull(this.billToContact).trim();
    }

    public void setBillToContact(java.lang.String billToContact) {
		if (!HiltonUtility.isEmpty(billToContact) && billToContact.length() > 40) {
			billToContact = billToContact.substring(0, 40);
		}
		this.billToContact = billToContact;
    }

    public java.util.Date getEffectiveDate() {
        return this.effectiveDate;
//		return HiltonUtility.ckNull(this.effectiveDate);
//		return (java.sql.Date)HiltonUtility.ckNull(this.effectiveDate);
    }

    public void setEffectiveDate(java.util.Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public java.util.Date getExpirationDate() {
        return this.expirationDate;
//		return HiltonUtility.ckNull(this.expirationDate);
//		return (java.sql.Date)HiltonUtility.ckNull(this.expirationDate);
    }

    public void setExpirationDate(java.util.Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public java.math.BigDecimal getBlanketLimit() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.blanketLimit);
    }

    public void setBlanketLimit(java.math.BigDecimal blanketLimit) {
        this.blanketLimit = blanketLimit;
    }

    public java.math.BigDecimal getReleaseLimit() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.releaseLimit);
    }

    public void setReleaseLimit(java.math.BigDecimal releaseLimit) {
        this.releaseLimit = releaseLimit;
    }

    public java.lang.String getConfirming() {
		return (java.lang.String)HiltonUtility.ckNull(this.confirming).trim();
    }

    public void setConfirming(java.lang.String confirming) {
		if (!HiltonUtility.isEmpty(confirming) && confirming.length() > 1) {
			confirming = confirming.substring(0, 1);
		}
		this.confirming = confirming;
    }

    public java.util.Date getConfirmDate() {
        return this.confirmDate;
//		return HiltonUtility.ckNull(this.confirmDate);
//		return (java.sql.Date)HiltonUtility.ckNull(this.confirmDate);
    }

    public void setConfirmDate(java.util.Date confirmDate) {
        this.confirmDate = confirmDate;
    }

    public java.lang.String getConfirmNameCode() {
		return (java.lang.String)HiltonUtility.ckNull(this.confirmNameCode).trim();
    }

    public void setConfirmNameCode(java.lang.String confirmNameCode) {
		if (!HiltonUtility.isEmpty(confirmNameCode) && confirmNameCode.length() > 30) {
			confirmNameCode = confirmNameCode.substring(0, 30);
		}
		this.confirmNameCode = confirmNameCode;
    }

    public java.math.BigDecimal getPoCopies() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.poCopies);
    }

    public void setPoCopies(java.math.BigDecimal poCopies) {
        this.poCopies = poCopies;
    }

    public java.lang.String getEdiOrder() {
		return (java.lang.String)HiltonUtility.ckNull(this.ediOrder).trim();
    }

    public void setEdiOrder(java.lang.String ediOrder) {
		if (!HiltonUtility.isEmpty(ediOrder) && ediOrder.length() > 1) {
			ediOrder = ediOrder.substring(0, 1);
		}
		this.ediOrder = ediOrder;
    }

    public java.util.Date getDatePrinted() {
        return this.datePrinted;
//		return HiltonUtility.ckNull(this.datePrinted);
//		return (java.sql.Date)HiltonUtility.ckNull(this.datePrinted);
    }

    public void setDatePrinted(java.util.Date datePrinted) {
        this.datePrinted = datePrinted;
    }

    public java.util.Date getDateFaxed() {
        return this.dateFaxed;
//		return HiltonUtility.ckNull(this.dateFaxed);
//		return (java.sql.Date)HiltonUtility.ckNull(this.dateFaxed);
    }

    public void setDateFaxed(java.util.Date dateFaxed) {
        this.dateFaxed = dateFaxed;
    }

    public java.util.Date getDateEdiXmit() {
        return this.dateEdiXmit;
//		return HiltonUtility.ckNull(this.dateEdiXmit);
//		return (java.sql.Date)HiltonUtility.ckNull(this.dateEdiXmit);
    }

    public void setDateEdiXmit(java.util.Date dateEdiXmit) {
        this.dateEdiXmit = dateEdiXmit;
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

    public java.math.BigDecimal getTaxPercent() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.taxPercent);
    }

    public void setTaxPercent(java.math.BigDecimal taxPercent) {
        this.taxPercent = taxPercent;
    }

    public java.math.BigDecimal getTaxAmount() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.taxAmount);
    }

    public void setTaxAmount(java.math.BigDecimal taxAmount) {
        this.taxAmount = taxAmount;
    }

    public java.lang.String getDiscountCode() {
		return (java.lang.String)HiltonUtility.ckNull(this.discountCode).trim();
    }

    public void setDiscountCode(java.lang.String discountCode) {
		if (!HiltonUtility.isEmpty(discountCode) && discountCode.length() > 1) {
			discountCode = discountCode.substring(0, 1);
		}
		this.discountCode = discountCode;
    }

    public java.math.BigDecimal getDiscountPercent() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.discountPercent);
    }

    public void setDiscountPercent(java.math.BigDecimal discountPercent) {
        this.discountPercent = discountPercent;
    }

    public java.math.BigDecimal getDiscountAmount() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.discountAmount);
    }

    public void setDiscountAmount(java.math.BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }

    public java.math.BigDecimal getShippingCharges() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.shippingCharges);
    }

    public void setShippingCharges(java.math.BigDecimal shippingCharges) {
        this.shippingCharges = shippingCharges;
    }

    public java.lang.String getShippingTaxable() {
		return (java.lang.String)HiltonUtility.ckNull(this.shippingTaxable).trim();
    }

    public void setShippingTaxable(java.lang.String shippingTaxable) {
		if (!HiltonUtility.isEmpty(shippingTaxable) && shippingTaxable.length() > 1) {
			shippingTaxable = shippingTaxable.substring(0, 1);
		}
		this.shippingTaxable = shippingTaxable;
    }

    public java.math.BigDecimal getShippingTax() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.shippingTax);
    }

    public void setShippingTax(java.math.BigDecimal shippingTax) {
        this.shippingTax = shippingTax;
    }

    public java.math.BigDecimal getOtherCharges() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.otherCharges);
    }

    public void setOtherCharges(java.math.BigDecimal otherCharges) {
        this.otherCharges = otherCharges;
    }

    public java.lang.String getOtherDescription() {
		return (java.lang.String)HiltonUtility.ckNull(this.otherDescription).trim();
    }

    public void setOtherDescription(java.lang.String otherDescription) {
		if (!HiltonUtility.isEmpty(otherDescription) && otherDescription.length() > 30) {
			otherDescription = otherDescription.substring(0, 30);
		}
		this.otherDescription = otherDescription;
    }

    public java.lang.String getOtherTaxable() {
		return (java.lang.String)HiltonUtility.ckNull(this.otherTaxable).trim();
    }

    public void setOtherTaxable(java.lang.String otherTaxable) {
		if (!HiltonUtility.isEmpty(otherTaxable) && otherTaxable.length() > 1) {
			otherTaxable = otherTaxable.substring(0, 1);
		}
		this.otherTaxable = otherTaxable;
    }

    public java.math.BigDecimal getOtherTax() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.otherTax);
    }

    public void setOtherTax(java.math.BigDecimal otherTax) {
        this.otherTax = otherTax;
    }

    public java.lang.String getPrePaid() {
		return (java.lang.String)HiltonUtility.ckNull(this.prePaid).trim();
    }

    public void setPrePaid(java.lang.String prePaid) {
		if (!HiltonUtility.isEmpty(prePaid) && prePaid.length() > 1) {
			prePaid = prePaid.substring(0, 1);
		}
		this.prePaid = prePaid;
    }

    public java.lang.String getUdf1Code() {
		return (java.lang.String)HiltonUtility.ckNull(this.udf1Code).trim();
    }

    public void setUdf1Code(java.lang.String udf1Code) {
		if (!HiltonUtility.isEmpty(udf1Code) && udf1Code.length() > 15) {
			udf1Code = udf1Code.substring(0, 15);
		}
		this.udf1Code = udf1Code;
    }

    public java.lang.String getUdf2Code() {
		return (java.lang.String)HiltonUtility.ckNull(this.udf2Code).trim();
    }

    public void setUdf2Code(java.lang.String udf2Code) {
		if (!HiltonUtility.isEmpty(udf2Code) && udf2Code.length() > 15) {
			udf2Code = udf2Code.substring(0, 15);
		}
		this.udf2Code = udf2Code;
    }

    public java.lang.String getUdf3Code() {
		return (java.lang.String)HiltonUtility.ckNull(this.udf3Code).trim();
    }

    public void setUdf3Code(java.lang.String udf3Code) {
		if (!HiltonUtility.isEmpty(udf3Code) && udf3Code.length() > 15) {
			udf3Code = udf3Code.substring(0, 15);
		}
		this.udf3Code = udf3Code;
    }

    public java.lang.String getUdf4Code() {
		return (java.lang.String)HiltonUtility.ckNull(this.udf4Code).trim();
    }

    public void setUdf4Code(java.lang.String udf4Code) {
		if (!HiltonUtility.isEmpty(udf4Code) && udf4Code.length() > 15) {
			udf4Code = udf4Code.substring(0, 15);
		}
		this.udf4Code = udf4Code;
    }

    public java.lang.String getUdf5Code() {
		return (java.lang.String)HiltonUtility.ckNull(this.udf5Code).trim();
    }

    public void setUdf5Code(java.lang.String udf5Code) {
		if (!HiltonUtility.isEmpty(udf5Code) && udf5Code.length() > 15) {
			udf5Code = udf5Code.substring(0, 15);
		}
		this.udf5Code = udf5Code;
    }

    public java.lang.String getUdf6Code() {
		return (java.lang.String)HiltonUtility.ckNull(this.udf6Code).trim();
    }

    public void setUdf6Code(java.lang.String udf6Code) {
		if (!HiltonUtility.isEmpty(udf6Code) && udf6Code.length() > 15) {
			udf6Code = udf6Code.substring(0, 15);
		}
		this.udf6Code = udf6Code;
    }

    public java.lang.String getUdf7Code() {
		return (java.lang.String)HiltonUtility.ckNull(this.udf7Code).trim();
    }

    public void setUdf7Code(java.lang.String udf7Code) {
		if (!HiltonUtility.isEmpty(udf7Code) && udf7Code.length() > 15) {
			udf7Code = udf7Code.substring(0, 15);
		}
		this.udf7Code = udf7Code;
    }

    public java.lang.String getUdf8Code() {
		return (java.lang.String)HiltonUtility.ckNull(this.udf8Code).trim();
    }

    public void setUdf8Code(java.lang.String udf8Code) {
		if (!HiltonUtility.isEmpty(udf8Code) && udf8Code.length() > 15) {
			udf8Code = udf8Code.substring(0, 15);
		}
		this.udf8Code = udf8Code;
    }

    public java.lang.String getUdf9Code() {
		return (java.lang.String)HiltonUtility.ckNull(this.udf9Code).trim();
    }

    public void setUdf9Code(java.lang.String udf9Code) {
		if (!HiltonUtility.isEmpty(udf9Code) && udf9Code.length() > 15) {
			udf9Code = udf9Code.substring(0, 15);
		}
		this.udf9Code = udf9Code;
    }

    public java.lang.String getUdf10Code() {
		return (java.lang.String)HiltonUtility.ckNull(this.udf10Code).trim();
    }

    public void setUdf10Code(java.lang.String udf10Code) {
		if (!HiltonUtility.isEmpty(udf10Code) && udf10Code.length() > 15) {
			udf10Code = udf10Code.substring(0, 15);
		}
		this.udf10Code = udf10Code;
    }

    public java.lang.String getUdf11Code() {
		return (java.lang.String)HiltonUtility.ckNull(this.udf11Code).trim();
    }

    public void setUdf11Code(java.lang.String udf11Code) {
		if (!HiltonUtility.isEmpty(udf11Code) && udf11Code.length() > 15) {
			udf11Code = udf11Code.substring(0, 15);
		}
		this.udf11Code = udf11Code;
    }
    public java.lang.String getUdf12Code() {
		return (java.lang.String)HiltonUtility.ckNull(this.udf12Code).trim();
    }

    public void setUdf12Code(java.lang.String udf12Code) {
		if (!HiltonUtility.isEmpty(udf12Code) && udf12Code.length() > 15) {
			udf12Code = udf12Code.substring(0, 15);
		}
		this.udf12Code = udf12Code;
    }

    public java.lang.String getUdf13Code() {
		return (java.lang.String)HiltonUtility.ckNull(this.udf13Code).trim();
    }

    public void setUdf13Code(java.lang.String udf13Code) {
		if (!HiltonUtility.isEmpty(udf13Code) && udf13Code.length() > 15) {
			udf13Code = udf13Code.substring(0, 15);
		}
		this.udf13Code = udf13Code;
    }

    public java.lang.String getUdf14Code() {
		return (java.lang.String)HiltonUtility.ckNull(this.udf14Code).trim();
    }

    public void setUdf14Code(java.lang.String udf14Code) {
		if (!HiltonUtility.isEmpty(udf14Code) && udf14Code.length() > 15) {
			udf14Code = udf14Code.substring(0, 15);
		}
		this.udf14Code = udf14Code;
    }

    public java.lang.String getUdf15Code() {
		return (java.lang.String)HiltonUtility.ckNull(this.udf15Code).trim();
    }

    public void setUdf15Code(java.lang.String udf15Code) {
		if (!HiltonUtility.isEmpty(udf15Code) && udf15Code.length() > 15) {
			udf15Code = udf15Code.substring(0, 15);
		}
		this.udf15Code = udf15Code;
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

    public java.lang.String getInternalComments() {
		return (java.lang.String)HiltonUtility.ckNull(this.internalComments).trim();
    }

    public void setInternalComments(java.lang.String internalComments) {
		if (!HiltonUtility.isEmpty(internalComments) && internalComments.length() > 255) {
			internalComments = internalComments.substring(0, 255);
		}
		this.internalComments = internalComments;
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

    public java.util.Date getDateEntered() {
        return this.dateEntered;
//		return HiltonUtility.ckNull(this.dateEntered);
//		return (java.sql.Date)HiltonUtility.ckNull(this.dateEntered);
    }

    public void setDateEntered(java.util.Date dateEntered) {
        this.dateEntered = dateEntered;
    }

    public java.lang.String getLastRelease() {
		return (java.lang.String)HiltonUtility.ckNull(this.lastRelease).trim();
    }

    public void setLastRelease(java.lang.String lastRelease) {
		if (!HiltonUtility.isEmpty(lastRelease) && lastRelease.length() > 4) {
			lastRelease = lastRelease.substring(0, 4);
		}
		this.lastRelease = lastRelease;
    }

    public java.lang.String getLastRevision() {
		return (java.lang.String)HiltonUtility.ckNull(this.lastRevision).trim();
    }

    public void setLastRevision(java.lang.String lastRevision) {
		if (!HiltonUtility.isEmpty(lastRevision) && lastRevision.length() > 4) {
			lastRevision = lastRevision.substring(0, 4);
		}
		this.lastRevision = lastRevision;
    }

    public java.lang.String getRequisitionNumber() {
		return (java.lang.String)HiltonUtility.ckNull(this.requisitionNumber).trim();
    }

    public void setRequisitionNumber(java.lang.String requisitionNumber) {
		if (!HiltonUtility.isEmpty(requisitionNumber) && requisitionNumber.length() > 20) {
			requisitionNumber = requisitionNumber.substring(0, 20);
		}
		this.requisitionNumber = requisitionNumber;
    }

    public java.lang.String getFiscalYear() {
		return (java.lang.String)HiltonUtility.ckNull(this.fiscalYear).trim();
    }

    public void setFiscalYear(java.lang.String fiscalYear) {
		if (!HiltonUtility.isEmpty(fiscalYear) && fiscalYear.length() > 4) {
			fiscalYear = fiscalYear.substring(0, 4);
		}
		this.fiscalYear = fiscalYear;
    }

    public java.lang.String getLanguage() {
		return (java.lang.String)Utility.ckNull(this.language).trim();
    }

    public void setLanguage(java.lang.String language) {
		if (!HiltonUtility.isEmpty(language) && language.length() > 15) {
			language = language.substring(0, 15);
		}
		this.language = language;
    }

    public java.math.BigDecimal getCurrencyFactor() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.currencyFactor);
    }

    public void setCurrencyFactor(java.math.BigDecimal currencyFactor) {
        this.currencyFactor = currencyFactor;
    }

    public java.math.BigDecimal getTotal() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.total);
    }

    public void setTotal(java.math.BigDecimal total) {
        this.total = total;
    }

    public java.math.BigDecimal getSubtotal() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.subtotal);
    }

    public void setSubtotal(java.math.BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public java.math.BigDecimal getIcReqHeader() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.icReqHeader);
    }

    public void setIcReqHeader(java.math.BigDecimal icReqHeader) {
        this.icReqHeader = icReqHeader;
    }

    public java.math.BigDecimal getIcRfqHeader() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.icRfqHeader);
    }

    public void setIcRfqHeader(java.math.BigDecimal icRfqHeader) {
        this.icRfqHeader = icRfqHeader;
    }

    public java.lang.String getApproved() {
		return (java.lang.String)HiltonUtility.ckNull(this.approved).trim();
    }

    public void setApproved(java.lang.String approved) {
		if (!HiltonUtility.isEmpty(approved) && approved.length() > 1) {
			approved = approved.substring(0, 1);
		}
		this.approved = approved;
    }

    public java.lang.String getAppBy() {
		return (java.lang.String)HiltonUtility.ckNull(this.appBy).trim();
    }

    public void setAppBy(java.lang.String appBy) {
		if (!HiltonUtility.isEmpty(appBy) && appBy.length() > 15) {
			appBy = appBy.substring(0, 15);
		}
		this.appBy = appBy;
    }

    public java.util.Date getAppDate() {
        return this.appDate;
//		return HiltonUtility.ckNull(this.appDate);
//		return (java.sql.Date)HiltonUtility.ckNull(this.appDate);
    }

    public void setAppDate(java.util.Date appDate) {
        this.appDate = appDate;
    }

    public java.lang.String getAppSigned() {
		return (java.lang.String)HiltonUtility.ckNull(this.appSigned).trim();
    }

    public void setAppSigned(java.lang.String appSigned) {
		if (!HiltonUtility.isEmpty(appSigned) && appSigned.length() > 1) {
			appSigned = appSigned.substring(0, 1);
		}
		this.appSigned = appSigned;
    }

    public java.lang.String getLastChgBy() {
		return (java.lang.String)HiltonUtility.ckNull(this.lastChgBy).trim();
    }

    public void setLastChgBy(java.lang.String lastChgBy) {
		if (!HiltonUtility.isEmpty(lastChgBy) && lastChgBy.length() > 15) {
			lastChgBy = lastChgBy.substring(0, 15);
		}
		this.lastChgBy = lastChgBy;
    }

    public java.util.Date getLastChgDate() {
        return this.lastChgDate;
//		return HiltonUtility.ckNull(this.lastChgDate);
//		return (java.sql.Date)HiltonUtility.ckNull(this.lastChgDate);
    }

    public void setLastChgDate(java.util.Date lastChgDate) {
        this.lastChgDate = lastChgDate;
    }

    public java.lang.String getRfqNumber() {
		return (java.lang.String)HiltonUtility.ckNull(this.rfqNumber).trim();
    }

    public void setRfqNumber(java.lang.String rfqNumber) {
		if (!HiltonUtility.isEmpty(rfqNumber) && rfqNumber.length() > 20) {
			rfqNumber = rfqNumber.substring(0, 20);
		}
		this.rfqNumber = rfqNumber;
    }

    public java.lang.String getEdiStatus() {
		return (java.lang.String)HiltonUtility.ckNull(this.ediStatus).trim();
    }

    public void setEdiStatus(java.lang.String ediStatus) {
		if (!HiltonUtility.isEmpty(ediStatus) && ediStatus.length() > 2) {
			ediStatus = ediStatus.substring(0, 2);
		}
		this.ediStatus = ediStatus;
    }

    public java.lang.String getReceiptRequired() {
		return (java.lang.String)HiltonUtility.ckNull(this.receiptRequired).trim();
    }

    public void setReceiptRequired(java.lang.String receiptRequired) {
		if (!HiltonUtility.isEmpty(receiptRequired) && receiptRequired.length() > 1) {
			receiptRequired = receiptRequired.substring(0, 1);
		}
		this.receiptRequired = receiptRequired;
    }

    public java.lang.String getContactAddr() {
		return (java.lang.String)HiltonUtility.ckNull(this.contactAddr).trim();
    }

    public void setContactAddr(java.lang.String contactAddr) {
		if (!HiltonUtility.isEmpty(contactAddr) && contactAddr.length() > 15) {
			contactAddr = contactAddr.substring(0, 15);
		}
		this.contactAddr = contactAddr;
    }

    public java.lang.String getPyStatus() {
		return (java.lang.String)HiltonUtility.ckNull(this.pyStatus).trim();
    }

    public void setPyStatus(java.lang.String pyStatus) {
		if (!HiltonUtility.isEmpty(pyStatus) && pyStatus.length() > 4) {
			pyStatus = pyStatus.substring(0, 4);
		}
		this.pyStatus = pyStatus;
    }

    public java.math.BigDecimal getSavings() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.savings);
    }

    public void setSavings(java.math.BigDecimal savings) {
        this.savings = savings;
    }

    public java.lang.String getSavingsReason() {
		return (java.lang.String)HiltonUtility.ckNull(this.savingsReason).trim();
    }

    public void setSavingsReason(java.lang.String savingsReason) {
		if (!HiltonUtility.isEmpty(savingsReason) && savingsReason.length() > 30) {
			savingsReason = savingsReason.substring(0, 30);
		}
		this.savingsReason = savingsReason;
    }

    public java.lang.String getLinkNextOrder() {
		return (java.lang.String)HiltonUtility.ckNull(this.linkNextOrder).trim();
    }

    public void setLinkNextOrder(java.lang.String linkNextOrder) {
		if (!HiltonUtility.isEmpty(linkNextOrder) && linkNextOrder.length() > 20) {
			linkNextOrder = linkNextOrder.substring(0, 20);
		}
		this.linkNextOrder = linkNextOrder;
    }

    public java.lang.String getLinkPriorOrder() {
		return (java.lang.String)HiltonUtility.ckNull(this.linkPriorOrder).trim();
    }

    public void setLinkPriorOrder(java.lang.String linkPriorOrder) {
		if (!HiltonUtility.isEmpty(linkPriorOrder) && linkPriorOrder.length() > 20) {
			linkPriorOrder = linkPriorOrder.substring(0, 20);
		}
		this.linkPriorOrder = linkPriorOrder;
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

    public java.math.BigDecimal getIcNextOrderLink() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.icNextOrderLink);
    }

    public void setIcNextOrderLink(java.math.BigDecimal icNextOrderLink) {
        this.icNextOrderLink = icNextOrderLink;
    }

    public java.math.BigDecimal getIcPriorOrderLink() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.icPriorOrderLink);
    }

    public void setIcPriorOrderLink(java.math.BigDecimal icPriorOrderLink) {
        this.icPriorOrderLink = icPriorOrderLink;
    }

    public java.lang.String getPcardOrder() {
		return (java.lang.String)HiltonUtility.ckNull(this.pcardOrder).trim();
    }

    public void setPcardOrder(java.lang.String pcardOrder) {
		if (!HiltonUtility.isEmpty(pcardOrder) && pcardOrder.length() > 1) {
			pcardOrder = pcardOrder.substring(0, 1);
		}
		this.pcardOrder = pcardOrder;
    }

    public java.lang.String getPcardName() {
		return (java.lang.String)HiltonUtility.ckNull(this.pcardName).trim();
    }

    public void setPcardName(java.lang.String pcardName) {
		if (!HiltonUtility.isEmpty(pcardName) && pcardName.length() > 20) {
			pcardName = pcardName.substring(0, 20);
		}
		this.pcardName = pcardName;
    }

    public java.lang.String getPcardHolder() {
		return (java.lang.String)HiltonUtility.ckNull(this.pcardHolder).trim();
    }

    public void setPcardHolder(java.lang.String pcardHolder) {
		if (!HiltonUtility.isEmpty(pcardHolder) && pcardHolder.length() > 45) {
			pcardHolder = pcardHolder.substring(0, 45);
		}
		this.pcardHolder = pcardHolder;
    }

    public java.lang.String getPcardNumber() {
		return (java.lang.String)HiltonUtility.ckNull(this.pcardNumber).trim();
    }

    public void setPcardNumber(java.lang.String pcardNumber) {
		if (!HiltonUtility.isEmpty(pcardNumber) && pcardNumber.length() > 25) {
			pcardNumber = pcardNumber.substring(0, 25);
		}
		this.pcardNumber = pcardNumber;
    }

    public java.lang.String getPcardExpires() {
		return (java.lang.String)HiltonUtility.ckNull(this.pcardExpires).trim();
    }

    public void setPcardExpires(java.lang.String pcardExpires) {
		if (!HiltonUtility.isEmpty(pcardExpires) && pcardExpires.length() > 10) {
			pcardExpires = pcardExpires.substring(0, 10);
		}
		this.pcardExpires = pcardExpires;
    }

    public java.lang.String getLockStatus() {
		return (java.lang.String)HiltonUtility.ckNull(this.lockStatus).trim();
    }

    public void setLockStatus(java.lang.String lockStatus) {
		if (!HiltonUtility.isEmpty(lockStatus) && lockStatus.length() > 1) {
			lockStatus = lockStatus.substring(0, 1);
		}
		this.lockStatus = lockStatus;
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

    public java.lang.String getPoRecalc() {
		return (java.lang.String)HiltonUtility.ckNull(this.poRecalc).trim();
    }

    public void setPoRecalc(java.lang.String poRecalc) {
		if (!HiltonUtility.isEmpty(poRecalc) && poRecalc.length() > 1) {
			poRecalc = poRecalc.substring(0, 1);
		}
		this.poRecalc = poRecalc;
    }

    public java.lang.String getActionAlertFlag() {
		return (java.lang.String)HiltonUtility.ckNull(this.actionAlertFlag).trim();
    }

    public void setActionAlertFlag(java.lang.String actionAlertFlag) {
		if (!HiltonUtility.isEmpty(actionAlertFlag) && actionAlertFlag.length() > 60) {
			actionAlertFlag = actionAlertFlag.substring(0, 60);
		}
		this.actionAlertFlag = actionAlertFlag;
    }

    public java.math.BigDecimal getObligAmt() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.obligAmt);
    }

    public void setObligAmt(java.math.BigDecimal obligAmt) {
        this.obligAmt = obligAmt;
    }

    public java.util.Date getObligDate() {
        return this.obligDate;
//		return HiltonUtility.ckNull(this.obligDate);
//		return (java.sql.Date)HiltonUtility.ckNull(this.obligDate);
    }

    public void setObligDate(java.util.Date obligDate) {
        this.obligDate = obligDate;
    }

    public java.lang.String getObligNum() {
		return (java.lang.String)HiltonUtility.ckNull(this.obligNum).trim();
    }

    public void setObligNum(java.lang.String obligNum) {
		if (!HiltonUtility.isEmpty(obligNum) && obligNum.length() > 15) {
			obligNum = obligNum.substring(0, 15);
		}
		this.obligNum = obligNum;
    }

    public java.lang.String getVendorEval() {
		return (java.lang.String)HiltonUtility.ckNull(this.vendorEval).trim();
    }

    public void setVendorEval(java.lang.String vendorEval) {
		if (!HiltonUtility.isEmpty(vendorEval) && vendorEval.length() > 1) {
			vendorEval = vendorEval.substring(0, 1);
		}
		this.vendorEval = vendorEval;
    }

    public java.lang.String getRequisitionerCode() {
		return (java.lang.String)HiltonUtility.ckNull(this.requisitionerCode).trim();
    }

    public void setRequisitionerCode(java.lang.String requisitionerCode) {
		if (!HiltonUtility.isEmpty(requisitionerCode) && requisitionerCode.length() > 15) {
			requisitionerCode = requisitionerCode.substring(0, 15);
		}
		this.requisitionerCode = requisitionerCode;
    }

    public java.lang.String getDepartmentCode() {
		return (java.lang.String)HiltonUtility.ckNull(this.departmentCode).trim();
    }

    public void setDepartmentCode(java.lang.String departmentCode) {
		if (!HiltonUtility.isEmpty(departmentCode) && departmentCode.length() > 15) {
			departmentCode = departmentCode.substring(0, 15);
		}
		this.departmentCode = departmentCode;
    }

    public java.lang.String getPriorityCode() {
		return (java.lang.String)HiltonUtility.ckNull(this.priorityCode).trim();
    }

    public void setPriorityCode(java.lang.String priorityCode) {
		if (!HiltonUtility.isEmpty(priorityCode) && priorityCode.length() > 15) {
			priorityCode = priorityCode.substring(0, 15);
		}
		this.priorityCode = priorityCode;
    }

    public java.math.BigDecimal getIcHeaderHistory() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.icHeaderHistory);
    }

    public void setIcHeaderHistory(java.math.BigDecimal icHeaderHistory) {
        this.icHeaderHistory = icHeaderHistory;
    }

    public String getTimeZone() {
        return (java.lang.String) HiltonUtility.ckNull(this.timeZone).trim();
    }

    public void setTimeZone(String timeZone) {
        if (!HiltonUtility.isEmpty(timeZone) && timeZone.length() > 30) {
            timeZone = timeZone.substring(0, 30);
        }
        this.timeZone = timeZone;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("icPoHeader", getIcPoHeader())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof PoHeader) ) return false;
        PoHeader castOther = (PoHeader) other;
        return new EqualsBuilder()
            .append(this.getIcPoHeader(), castOther.getIcPoHeader())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getIcPoHeader())
            .toHashCode();
    }

    /**
     * @return Returns the contactName.
     */
    public java.lang.String getContactName()
    {
        //return contactName;
        return (java.lang.String)HiltonUtility.ckNull(contactName);
    }

    /**
     * @param contactName The contactName to set.
     */
    public void setContactName(java.lang.String contactName)
    {
        this.contactName = contactName;
    }

    /**
     * @return Returns the accountList.
     */
    public List getAccountList()
    {
        return accountList;
    }

    /**
     * @param accountList The accountList to set.
     */
    public void setAccountList(List accountList)
    {
        this.accountList = accountList;
    }

    /**
     * @return Returns the billToAddress.
     */
    public Address getBillToAddress()
    {
        return billToAddress;
    }

    /**
     * @param billToAddress The billToAddress to set.
     */
    public void setBillToAddress(Address billToAddress)
    {
        this.billToAddress = billToAddress;
    }

    /**
     * @return Returns the docAttachmentList.
     */
    public List getDocAttachmentList()
    {
        return docAttachmentList;
    }

    /**
     * @param docAttachmentList The docAttachmentList to set.
     */
    public void setDocAttachmentList(List docAttachmentList)
    {
        this.docAttachmentList = docAttachmentList;
    }

    /**
     * @return Returns the docCommentList.
     */
    public List getDocCommentList()
    {
        return docCommentList;
    }

    /**
     * @param docCommentList The docCommentList to set.
     */
    public void setDocCommentList(List docCommentList)
    {
        this.docCommentList = docCommentList;
    }

    /**
     * @return Returns the shipToAddress.
     */
    public Address getShipToAddress()
    {
        return shipToAddress;
    }

    /**
     * @param shipToAddress The shipToAddress to set.
     */
    public void setShipToAddress(Address shipToAddress)
    {
        this.shipToAddress = shipToAddress;
    }

    /**
     * @return Returns the vendorAddress.
     */
    public Address getVendorAddress()
    {
        return vendorAddress;
    }

    /**
     * @param vendorAddress The vendorAddress to set.
     */
    public void setVendorAddress(Address vendorAddress)
    {
        this.vendorAddress = vendorAddress;
    }

    /**
     * @return Returns the poLineList.
     */
    public List getPoLineList()
    {
        return poLineList;
    }

    /**
     * @param poLineList The poLineList to set.
     */
    public void setPoLineList(List poLineList)
    {
        this.poLineList = poLineList;
    }

    /**
     * @return Returns the scheduleList.
     */
    public List getScheduleList()
    {
        return scheduleList;
    }

    /**
     * @param scheduleList The scheduleList to set.
     */
    public void setScheduleList(List scheduleList)
    {
        this.scheduleList = scheduleList;
    }

    /**
     * @return Returns the itemLocation.
     */
    public java.lang.String getItemLocation()
    {
        return (java.lang.String)HiltonUtility.ckNull(this.itemLocation);
    }

    /**
     * @param itemLocation The itemLocation to set.
     */
    public void setItemLocation(java.lang.String itemLocation)
    {
        this.itemLocation = itemLocation;
    }

    /**
     * @return Returns the routing.
     */
    public java.lang.String getRouting()
    {
        return (java.lang.String) HiltonUtility.ckNull(this.routing);
    }

    /**
     * @param routing The routing to set.
     */
    public void setRouting(java.lang.String routing)
    {
        this.routing = routing;
    }

    public java.lang.String getAuthorizationCode() {
		return (java.lang.String)HiltonUtility.ckNull(this.authorizationCode).trim();
    }

    public void setAuthorizationCode(java.lang.String authorizationCode) {
		if (!HiltonUtility.isEmpty(authorizationCode) && authorizationCode.length() > 15) {
			authorizationCode = authorizationCode.substring(0, 15);
		}
		this.authorizationCode = authorizationCode;
    }
    public StringBuffer getDisplayPoNumber()
    {
    	return this.getDisplayPoNumber(true);
    }

    public StringBuffer getDisplayPoNumber(boolean toDisplay)
    {//Order 050413-2 Revision xxx
        StringBuffer subject = new StringBuffer("");
        //subject.append(OrderType.toString(this.getPoType()));
        if(toDisplay){		subject.append("Order ");		}
        //subject.append(" ");
        subject.append(this.getPoNumber());

        if (this.getReleaseNumber().compareTo(new BigDecimal(0)) > 0) {
            subject.append("-");
            subject.append(this.getReleaseNumber()) ;
        }
        if (this.getRevisionNumber().compareTo(new BigDecimal(0)) > 0) {
            subject.append(" Revision ");
            subject.append(this.getRevisionNumber()) ;
        }

        return subject;
    }


	public String getAutoImport() {
		return (java.lang.String)HiltonUtility.ckNull(this.autoImport).trim();
	}

	public void setAutoImport(String autoImport) {
		if (!HiltonUtility.isEmpty(autoImport) && autoImport.length() > 1) {
			autoImport = autoImport.substring(0, 1);
		}
		this.autoImport = autoImport;
	}

	public BigDecimal getAutoInterval() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.autoInterval);
	}

	public void setAutoInterval(BigDecimal autoInterval) {
		this.autoInterval = autoInterval;
	}

	public String getAutoPayment() {
		return (java.lang.String)HiltonUtility.ckNull(this.autoPayment).trim();
	}

	public void setAutoPayment(String autoPayment) {
		if (!HiltonUtility.isEmpty(autoPayment) && autoPayment.length() > 1) {
			autoPayment = autoPayment.substring(0, 1);
		}
		this.autoPayment = autoPayment;
	}

	public String getAutoRelease() {
		return (java.lang.String)HiltonUtility.ckNull(this.autoRelease).trim();
	}

	public void setAutoRelease(String autoRelease) {
		if (!HiltonUtility.isEmpty(autoRelease) && autoRelease.length() > 1) {
			autoRelease = autoRelease.substring(0, 1);
		}
		this.autoRelease = autoRelease;
	}

	public String getAutoReleased() {
		return (java.lang.String)HiltonUtility.ckNull(this.autoReleased).trim();
	}

	public void setAutoReleased(String autoReleased) {
		if (!HiltonUtility.isEmpty(autoReleased) && autoReleased.length() > 10) {
			autoReleased = autoReleased.substring(0, 10);
		}
		this.autoReleased = autoReleased;
	}

	public BigDecimal getRevisionValue() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.revisionValue);
	}

	public void setRevisionValue(BigDecimal revisionValue) {
		this.revisionValue = revisionValue;
	}

	public void setVendorClass(String vendorClass) {
		if (!HiltonUtility.isEmpty(vendorClass) && vendorClass.length() > 40) {
			vendorClass = vendorClass.substring(0, 40);
		}
		this.vendorClass = vendorClass;
	}

	public String getVendorClass() {
		return (java.lang.String)HiltonUtility.ckNull(this.vendorClass).trim();
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

	public void setVendorRating(BigDecimal vendorRating) {
		this.vendorRating = vendorRating;
	}

	public java.math.BigDecimal getUseTaxAmount()
	{
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.useTaxAmount);
	}

	public void setUseTaxAmount(java.math.BigDecimal useTaxAmount)
	{
		this.useTaxAmount = useTaxAmount;
	}

	public String getUseTaxCode()
	{
		return (java.lang.String)HiltonUtility.ckNull(this.useTaxCode).trim();
	}

	public void setUseTaxCode(String useTaxCode)
	{
		this.useTaxCode = useTaxCode;
	}

	public java.math.BigDecimal getUseTaxPercent()
	{
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.useTaxPercent);
	}

	public void setUseTaxPercent(java.math.BigDecimal useTaxPercent)
	{
		this.useTaxPercent = useTaxPercent;
	}


	public String getInspectionReqd() {
		return (java.lang.String)HiltonUtility.ckNull(this.inspectionReqd).trim();
	}

	public void setInspectionReqd(String inspectionReqd) {
		if (!HiltonUtility.isEmpty(inspectionReqd) && inspectionReqd.length() > 1) {
		    inspectionReqd = inspectionReqd.substring(0, 1);
		}
		this.inspectionReqd = inspectionReqd;
	}

	public boolean isInspectionRequired() {
	    if (this.getInspectionReqd().equalsIgnoreCase("Y")) {
	        return true;
	    }
	    return false;
	}

    public java.lang.String getContactPhone() {
		return (java.lang.String)HiltonUtility.ckNull(this.contactPhone).trim();
    }

    public void setContactPhone(java.lang.String contactPhone) {
		if (!HiltonUtility.isEmpty(contactPhone) && contactPhone.length() > 30) {
			contactPhone = contactPhone.substring(0, 30);
		}
		this.contactPhone = contactPhone;
    }

    public void setContactMobilePhone(String contactMobilePhone) {
		if (!HiltonUtility.isEmpty(contactMobilePhone) && contactMobilePhone.length() > 30) {
			contactMobilePhone = contactMobilePhone.substring(0, 30);
		}
		this.contactMobilePhone = contactMobilePhone;
	}

	public String getContactMobilePhone() {
		return (java.lang.String)HiltonUtility.ckNull(this.contactMobilePhone).trim();
	}

    public java.util.Date getDateAcknowledged() {
        return this.dateAcknowledged;
    }

    public void setDateAcknowledged(java.util.Date dateAcknowledged) {
        this.dateAcknowledged = dateAcknowledged;
    }

    public void setAcknowledgedBy(String acknowledgedBy) {
        if (!HiltonUtility.isEmpty(acknowledgedBy) && acknowledgedBy.length() > 65) {
            acknowledgedBy = acknowledgedBy.substring(0, 65);
        }
        this.acknowledgedBy = acknowledgedBy;
    }

    public String getAcknowledgedBy() {
        return (java.lang.String)HiltonUtility.ckNull(this.acknowledgedBy).trim();
    }

	public void setPrintMode(String printMode){
		this.printMode = printMode;
	}

	public String getPrintMode (){
		return (java.lang.String)HiltonUtility.ckNull(this.printMode).trim();
	}

    public void setShipToInv(String shipToInv) {
        if (!HiltonUtility.isEmpty(shipToInv) && shipToInv.length() > 1) {
            shipToInv = shipToInv.substring(0, 1);
        }
        this.shipToInv = shipToInv;
    }

    public String getShipToInv() {
        return (java.lang.String)HiltonUtility.ckNull(this.shipToInv).trim();
    }

    public java.math.BigDecimal getIcHeaderKey() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.icHeaderKey);
    }

    public void setIcHeaderKey(java.math.BigDecimal icHeaderKey) {
        this.icHeaderKey = icHeaderKey;
    }


    public List getReviewFinalizeList() {
		if(this.reviewFinalizeList == null){		this.reviewFinalizeList = new ArrayList();		}
		return this.reviewFinalizeList;
	}

	public void setReviewFinalizeList(List reviewFinalizeList) {
		this.reviewFinalizeList = reviewFinalizeList;
	}

	public boolean isCompleted()
	{
		boolean completed = true;
		if(this.reviewFinalizeList == null || this.reviewFinalizeList.size() < 1){		return false;		}
		for(int i = 0; i < this.reviewFinalizeList.size(); i++)
		{
			ReviewFinalize reviewFinalize = (ReviewFinalize)this.reviewFinalizeList.get(i);
			if(!reviewFinalize.isReviewCompleted())
			{
				completed = false;
				i = this.reviewFinalizeList.size();
			}
		}
		return completed;
	}

    public java.lang.String getBidWaiver() {
		return (java.lang.String)HiltonUtility.ckNull(this.bidWaiver).trim();
    }

    public void setBidWaiver(java.lang.String bidWaiver) {
		if (!HiltonUtility.isEmpty(bidWaiver) && bidWaiver.length() > 15) {
			bidWaiver = bidWaiver.substring(0, 15);
		}
		this.bidWaiver = bidWaiver;
    }

    public void setTotalUSD(java.math.BigDecimal totalUSD) {
        this.totalUSD = totalUSD;
    }

    public java.math.BigDecimal getTotalUSD() {
		return (java.math.BigDecimal)HiltonUtility.ckNull((this.totalUSD));
    }

    public java.math.BigDecimal getIclLevel() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.iclLevel);
	}

	public void setIclLevel(java.math.BigDecimal iclLevel) {
		this.iclLevel = iclLevel;
	}

	public java.lang.String getGfpGfm() {
		return (java.lang.String)HiltonUtility.ckNull(this.gfpGfm).trim();
    }

    public void setGfpGfm(java.lang.String gfpGfm) {
		if (!HiltonUtility.isEmpty(gfpGfm) && gfpGfm.length() > 1) {
			gfpGfm = gfpGfm.substring(0, 1);
		}
		this.gfpGfm = gfpGfm;
    }

    public java.lang.String getRequestCat() {
		return (java.lang.String)HiltonUtility.ckNull(this.requestCat).trim();
    }

    public void setRequestCat(java.lang.String requestCat) {
		if (!HiltonUtility.isEmpty(requestCat) && requestCat.length() > 4) {
			requestCat = requestCat.substring(0, 4);
		}
		this.requestCat = requestCat;
    }

    public java.lang.String getKit() {
		return (java.lang.String)HiltonUtility.ckNull(this.kit).trim();
    }

    public void setKit(java.lang.String kit) {
		if (!HiltonUtility.isEmpty(kit) && kit.length() > 1) {
			kit = kit.substring(0, 1);
		}
		this.kit = kit;
    }

    public java.lang.String getWorkOrder() {
		return (java.lang.String)HiltonUtility.ckNull(this.workOrder).trim();
    }

    public void setWorkOrder(java.lang.String workOrder) {
		if (!HiltonUtility.isEmpty(workOrder) && workOrder.length() > 15) {
			workOrder = workOrder.substring(0, 15);
		}
		this.workOrder = workOrder;
    }

    public java.lang.String getSubType() {
		return (java.lang.String)HiltonUtility.ckNull(this.subType).trim();
    }

    public void setSubType(java.lang.String subType) {
		if (!HiltonUtility.isEmpty(subType) && subType.length() > 2) {
			subType = subType.substring(0, 2);
		}
		this.subType = subType;
    }

    public java.lang.String getInsuranceRqd() {
		return (java.lang.String)HiltonUtility.ckNull(this.insuranceRqd).trim();
    }

    public void setInsuranceRqd(java.lang.String insuranceRqd) {
		if (!HiltonUtility.isEmpty(insuranceRqd) && insuranceRqd.length() > 1) {
			insuranceRqd = insuranceRqd.substring(0, 1);
		}
		this.insuranceRqd = insuranceRqd;
    }

    public java.lang.String getNaics() {
		return (java.lang.String)HiltonUtility.ckNull(this.naics).trim();
    }

    public void setNaics(java.lang.String naics) {
		if (!HiltonUtility.isEmpty(naics) && naics.length() > 15) {
			naics = naics.substring(0, 15);
		}
		this.naics = naics;
    }

    public java.math.BigDecimal getEstimatedCost(){
    	return (java.math.BigDecimal) HiltonUtility.ckNull(this.estimatedCost);
    }

    public void setEstimatedCost(java.math.BigDecimal estimatedCost) {
    	this.estimatedCost = estimatedCost;
    }

	public String getOriginalReqType() {
		return (String)HiltonUtility.ckNull(this.originalReqType).trim();
	}

	public void setOriginalReqType(String originalReqType) {
		if (!HiltonUtility.isEmpty(originalReqType) && originalReqType.length() > 1) {
			originalReqType = originalReqType.substring(0, 1);
		}
		this.originalReqType = originalReqType;
	}

	public java.math.BigDecimal getIcMsrHeader() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.icMsrHeader);
	}

	public void setIcMsrHeader(java.math.BigDecimal icMsrHeader) {
		this.icMsrHeader = icMsrHeader;
	}

	public String getMsrNumber() {
		return (java.lang.String)HiltonUtility.ckNull(this.msrNumber).trim();
	}

	public void setMsrNumber(String msrNumber) {
		if (!HiltonUtility.isEmpty(msrNumber) && msrNumber.length() > 20) {
			msrNumber = msrNumber.substring(0, 20);
		}
		this.msrNumber = msrNumber;
	}

	public java.lang.String getCorrosionEval() {
		return (java.lang.String)HiltonUtility.ckNull(this.corrosionEval).trim();
    }

    public void setCorrosionEval(java.lang.String corrosionEval) {
		if (!HiltonUtility.isEmpty(corrosionEval) && corrosionEval.length() > 3) {
			corrosionEval = corrosionEval.substring(0, 3);
		}
		this.corrosionEval = corrosionEval;
    }
}
