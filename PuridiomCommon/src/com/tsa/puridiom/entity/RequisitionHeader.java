package com.tsa.puridiom.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.currcode.CurrencyManager;
import com.tsagate.foundation.database.Entity;
import com.tsagate.foundation.utility.Utility;

/** @author Hibernate CodeGenerator */
public class RequisitionHeader extends Entity implements Serializable {

    /** identifier field */
    private java.math.BigDecimal icReqHeader;

    /** nullable persistent field */
    private String requisitionNumber;

    /** nullable persistent field */
    private String requisitionType;

    /** nullable persistent field */
    private java.util.Date requisitionDate;

    /** nullable persistent field */
    private String status;

    /** nullable persistent field */
    private String discountSource;

    /** nullable persistent field */
    private java.math.BigDecimal discountPercent;

    /** nullable persistent field */
    private java.math.BigDecimal discountAmount;

    /** nullable persistent field */
    private java.math.BigDecimal shippingCharges;

    /** nullable persistent field */
    private String taxShipping;

    /** nullable persistent field */
    private java.math.BigDecimal otherCharges;

    /** nullable persistent field */
    private String taxOther;

    /** nullable persistent field */
    private String otherChargDesc;

    /** nullable persistent field */
    private String internalComments;

    /** nullable persistent field */
    private String fiscalYear;

    /** nullable persistent field */
    private String owner;

    /** nullable persistent field */
    private java.util.Date dateEntered;

    /** nullable persistent field */
    private java.math.BigDecimal taxPercent;

    /** nullable persistent field */
    private java.math.BigDecimal taxAmount;

    /** nullable persistent field */
    private java.math.BigDecimal otherTaxAmount;

    /** nullable persistent field */
    private java.math.BigDecimal shippingTaxAmt;

    /** nullable persistent field */
    private String language;

    /** nullable persistent field */
    private java.math.BigDecimal subtotal;

    /** nullable persistent field */
    private java.math.BigDecimal total;

    /** nullable persistent field */
    private java.util.Date appDate;

    /** nullable persistent field */
    private String appSigned;

    /** nullable persistent field */
    private String lastChgBy;

    /** nullable persistent field */
    private java.util.Date lastChgDate;

    /** nullable persistent field */
    private String approved;

    /** nullable persistent field */
    private String appBy;

    /** nullable persistent field */
    private String assignedBuyer;

    /** nullable persistent field */
    private java.util.Date assignedDate;

    /** nullable persistent field */
    private java.math.BigDecimal estimatedCost;

    /** nullable persistent field */
    private String bidWaiver;

    /** nullable persistent field */
    private String shipAttn;

    /** nullable persistent field */
    private String vendorAttn;

    /** nullable persistent field */
    private String rqHeaderKey;

    /** nullable persistent field */
    private String rqSubType;

    /** nullable persistent field */
    private String pcardReq;

    /** nullable persistent field */
    private String pcardName;

    /** nullable persistent field */
    private String pcardHolder;

    /** nullable persistent field */
    private String pcardNumber;

    /** nullable persistent field */
    private String pcardExpires;

    /** nullable persistent field */
    private java.math.BigDecimal icRevisedOrder;

    /** nullable persistent field */
    private String reqRecalc;

    /** nullable persistent field */
    private String actionAlertFlag;

    /** nullable persistent field */
    private String maxStatus;

    /** nullable persistent field */
    private String buyerRemarks;

    /** nullable persistent field */
    private java.math.BigDecimal ammendment;

    /** nullable persistent field */
    private String requisitionerCode;

    /** nullable persistent field */
    private String departmentCode;

    /** nullable persistent field */
    private String authorizationCode;

    /** nullable persistent field */
    private String vendorId;

    /** nullable persistent field */
    private String shippingCode;

    /** nullable persistent field */
    private String buyer;

    /** nullable persistent field */
    private String shipToCode;

    /** nullable persistent field */
    private String fobCode;

    /** nullable persistent field */
    private String routing;

    /** nullable persistent field */
    private String priorityCode;

    /** nullable persistent field */
    private java.util.Date requiredDate;

    /** nullable persistent field */
    private String taxCode;

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
    private String accountCode;

    /** nullable persistent field */
    private String vendContactCode;

    /** nullable persistent field */
    private String contactAddr;

    /** nullable persistent field */
    private String contactPhone;

    /** nullable persistent field */
    private String contactMobilePhone;

    /** nullable persistent field */
    private String shipToContact;

    /** nullable persistent field */
    private String billToCode;

    /** nullable persistent field */
    private String billToContact;

    /** nullable persistent field */
    private java.math.BigDecimal icHeaderHistory;

    /** nullable persistent field */
    private String itemLocation;

    /** nullable persistent field */
    private String currencyCode;

    /** nullable persistent field */
    private String receiptRequired;

    /** nullable persistent field */
	private String useTaxCode;

	 /** nullable persistent field */
    private java.math.BigDecimal useTaxPercent;

    /** nullable persistent field */
    private java.math.BigDecimal useTaxAmount;

    /** nullable persistent field */
    private java.math.BigDecimal currencyFactor;

    /** nullable persistent field */
    private java.util.Date servicesStartDate;

    /** nullable persistent field */
    private java.util.Date servicesEndDate;

    /** nullable persistent field */
    private String budgetFlag;

    /** nullable persistent field */
    private String vendorName;

    /** nullable persistent field */
    private String forwardedTo;

    /** nullable persistent field */
    private String forwardedBy;

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

    private String originalReqType;

    /** nullable persistent field */
    private java.math.BigDecimal icMsrHeader;

    /** nullable persistent field */
    private String msrNumber;

    /** nullable persistent field */
    private String corrosionEval;

    /** nullable persistent field */
    private java.util.Date plannedDate;

    private List accountList;

    private List docCommentList;

    private List docAttachmentList;

    private List scheduleList;

	private List requisitionLineList;

    private List rfqInfoList;

    private List poInfoList;

	private Address billToAddress;

	private Address shipToAddress;

	private Address vendorAddress;

	private boolean rejectAccess;

	private java.math.BigDecimal totalUSD;

    /** full constructor */
    public RequisitionHeader(java.math.BigDecimal icReqHeader, java.lang.String requisitionNumber, java.lang.String requisitionType, java.util.Date requisitionDate, java.lang.String status, java.lang.String discountSource, java.math.BigDecimal discountPercent, java.math.BigDecimal discountAmount, java.math.BigDecimal shippingCharges, java.lang.String taxShipping, java.math.BigDecimal otherCharges, java.lang.String taxOther, java.lang.String otherChargDesc, java.lang.String internalComments, java.lang.String fiscalYear, java.lang.String owner, java.util.Date dateEntered, java.math.BigDecimal taxPercent, java.math.BigDecimal taxAmount, java.math.BigDecimal otherTaxAmount, java.math.BigDecimal shippingTaxAmt, java.lang.String language, java.math.BigDecimal subtotal, java.math.BigDecimal total, java.util.Date appDate, java.lang.String appSigned, java.lang.String lastChgBy, java.util.Date lastChgDate, java.lang.String approved, java.lang.String appBy, java.lang.String assignedBuyer, java.util.Date assignedDate, java.math.BigDecimal estimatedCost, java.lang.String bidWaiver, java.lang.String shipAttn, java.lang.String vendorAttn, java.lang.String rqHeaderKey, java.lang.String rqSubType, java.lang.String pcardReq, java.lang.String pcardName, java.lang.String pcardHolder, java.lang.String pcardNumber, java.lang.String pcardExpires, java.math.BigDecimal icRevisedOrder, java.lang.String reqRecalc, java.lang.String actionAlertFlag, java.lang.String maxStatus, java.lang.String buyerRemarks,  java.math.BigDecimal ammendment, java.lang.String requisitionerCode, java.lang.String departmentCode, java.lang.String authorizationCode, java.lang.String vendorId, java.lang.String shippingCode, java.lang.String buyer, java.lang.String shipToCode, java.lang.String fobCode, java.lang.String routing, java.lang.String priorityCode, java.util.Date requiredDate, java.lang.String taxCode, java.lang.String udf1Code, java.lang.String udf2Code, java.lang.String udf3Code, java.lang.String udf4Code, java.lang.String udf5Code, java.lang.String udf6Code, java.lang.String udf7Code, java.lang.String udf8Code, java.lang.String udf9Code, java.lang.String udf10Code, java.lang.String udf11Code, java.lang.String udf12Code, java.lang.String udf13Code, java.lang.String udf14Code, java.lang.String udf15Code, java.lang.String accountCode, java.lang.String vendContactCode, java.lang.String contactAddr, java.lang.String contactPhone, java.lang.String contactMobilePhone, java.lang.String shipToContact, java.lang.String billToCode, java.lang.String billToContact, java.math.BigDecimal icHeaderHistory, java.lang.String currencyCode, java.lang.String receiptRequired, java.lang.String useTaxCode, java.math.BigDecimal useTaxAmount, java.math.BigDecimal useTaxPercent, java.math.BigDecimal currencyFactor, java.util.Date servicesStartDate, java.util.Date servicesEndDate, java.lang.String timeZone, java.lang.String gfpGfm, java.lang.String requestCat, java.lang.String kit, java.lang.String workOrder, java.lang.String corrosionEval) {
        this.icReqHeader = icReqHeader;
        this.requisitionNumber = requisitionNumber;
        this.requisitionType = requisitionType;
        this.requisitionDate = requisitionDate;
        this.status = status;
        this.discountSource = discountSource;
        this.discountPercent = discountPercent;
        this.discountAmount = discountAmount;
        this.shippingCharges = shippingCharges;
        this.taxShipping = taxShipping;
        this.otherCharges = otherCharges;
        this.taxOther = taxOther;
        this.otherChargDesc = otherChargDesc;
        this.internalComments = internalComments;
        this.fiscalYear = fiscalYear;
        this.owner = owner;
        this.dateEntered = dateEntered;
        this.taxPercent = taxPercent;
        this.taxAmount = taxAmount;
        this.otherTaxAmount = otherTaxAmount;
        this.shippingTaxAmt = shippingTaxAmt;
        this.language = language;
        this.subtotal = subtotal;
        this.total = total;
        this.appDate = appDate;
        this.appSigned = appSigned;
        this.lastChgBy = lastChgBy;
        this.lastChgDate = lastChgDate;
        this.approved = approved;
        this.appBy = appBy;
        this.assignedBuyer = assignedBuyer;
        this.assignedDate = assignedDate;
        this.estimatedCost = estimatedCost;
        this.bidWaiver = bidWaiver;
        this.shipAttn = shipAttn;
        this.vendorAttn = vendorAttn;
        this.rqHeaderKey = rqHeaderKey;
        this.rqSubType = rqSubType;
        this.pcardReq = pcardReq;
        this.pcardName = pcardName;
        this.pcardHolder = pcardHolder;
        this.pcardNumber = pcardNumber;
        this.pcardExpires = pcardExpires;
        this.icRevisedOrder = icRevisedOrder;
        this.reqRecalc = reqRecalc;
        this.actionAlertFlag = actionAlertFlag;
        this.maxStatus = maxStatus;
        this.buyerRemarks = buyerRemarks;
        this.ammendment = ammendment;
        this.requisitionerCode = requisitionerCode;
        this.departmentCode = departmentCode;
        this.authorizationCode = authorizationCode;
        this.vendorId = vendorId;
        this.shippingCode = shippingCode;
        this.buyer = buyer;
        this.shipToCode = shipToCode;
        this.fobCode = fobCode;
        this.routing = routing;
        this.priorityCode = priorityCode;
        this.requiredDate = requiredDate;
        this.taxCode = taxCode;
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
        this.accountCode = accountCode;
        this.vendContactCode = vendContactCode;
        this.contactAddr = contactAddr;
        this.contactPhone = contactPhone;
        this.contactMobilePhone = contactMobilePhone;
        this.shipToContact = shipToContact;
        this.billToCode = billToCode;
        this.billToContact = billToContact;
        this.icHeaderHistory = icHeaderHistory;
        this.currencyCode = currencyCode;
        this.receiptRequired = receiptRequired;
        this.useTaxAmount = useTaxAmount;
        this.useTaxCode = useTaxCode;
        this.useTaxPercent = useTaxPercent;
        this.currencyFactor = currencyFactor;
        this.servicesStartDate = servicesStartDate;
        this.servicesEndDate = servicesEndDate;
        this.timeZone = timeZone;
        this.gfpGfm = gfpGfm;
        this.requestCat = requestCat;
        this.kit = kit;
        this.workOrder = workOrder;
        this.corrosionEval = corrosionEval;
    }

    /** default constructor */
    public RequisitionHeader() {
    }

    /** minimal constructor */
    public RequisitionHeader(java.math.BigDecimal icReqHeader) {
        this.icReqHeader = icReqHeader;
    }

    public java.math.BigDecimal getIcReqHeader() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.icReqHeader);
    }

    public void setIcReqHeader(java.math.BigDecimal icReqHeader) {
        this.icReqHeader = icReqHeader;
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

    public java.lang.String getRequisitionType() {
		return (java.lang.String)HiltonUtility.ckNull(this.requisitionType).trim();
    }

    public void setRequisitionType(java.lang.String requisitionType) {
		if (!HiltonUtility.isEmpty(requisitionType) && requisitionType.length() > 1) {
			requisitionType = requisitionType.substring(0, 1);
		}
		this.requisitionType = requisitionType;
    }

    public java.util.Date getRequisitionDate() {
		return this.requisitionDate;
//		return HiltonUtility.ckNull(this.requisitionDate);
//		return (java.sql.Date)HiltonUtility.ckNull(this.requisitionDate);
    }

    public void setRequisitionDate(java.util.Date requisitionDate) {
        this.requisitionDate = requisitionDate;
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

    public java.lang.String getDiscountSource() {
		return (java.lang.String)HiltonUtility.ckNull(this.discountSource).trim();
    }

    public void setDiscountSource(java.lang.String discountSource) {
		if (!HiltonUtility.isEmpty(discountSource) && discountSource.length() > 1) {
			discountSource = discountSource.substring(0, 1);
		}
		this.discountSource = discountSource;
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

    public java.lang.String getTaxShipping() {
		return (java.lang.String)HiltonUtility.ckNull(this.taxShipping).trim();
    }

    public void setTaxShipping(java.lang.String taxShipping) {
		if (!HiltonUtility.isEmpty(taxShipping) && taxShipping.length() > 1) {
			taxShipping = taxShipping.substring(0, 1);
		}
		this.taxShipping = taxShipping;
    }

    public java.math.BigDecimal getOtherCharges() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.otherCharges);
    }

    public void setOtherCharges(java.math.BigDecimal otherCharges) {
        this.otherCharges = otherCharges;
    }

    public java.lang.String getTaxOther() {
		return (java.lang.String)HiltonUtility.ckNull(this.taxOther).trim();
    }

    public void setTaxOther(java.lang.String taxOther) {
		if (!HiltonUtility.isEmpty(taxOther) && taxOther.length() > 1) {
			taxOther = taxOther.substring(0, 1);
		}
		this.taxOther = taxOther;
    }

    public java.lang.String getOtherChargDesc() {
		return (java.lang.String)HiltonUtility.ckNull(this.otherChargDesc).trim();
    }

    public void setOtherChargDesc(java.lang.String otherChargDesc) {
		if (!HiltonUtility.isEmpty(otherChargDesc) && otherChargDesc.length() > 30) {
			otherChargDesc = otherChargDesc.substring(0, 30);
		}
		this.otherChargDesc = otherChargDesc;
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

    public java.lang.String getFiscalYear() {
		return (java.lang.String)HiltonUtility.ckNull(this.fiscalYear).trim();
    }

    public void setFiscalYear(java.lang.String fiscalYear) {
		if (!HiltonUtility.isEmpty(fiscalYear) && fiscalYear.length() > 4) {
			fiscalYear = fiscalYear.substring(0, 4);
		}
		this.fiscalYear = fiscalYear;
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

    public java.math.BigDecimal getOtherTaxAmount() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.otherTaxAmount);
    }

    public void setOtherTaxAmount(java.math.BigDecimal otherTaxAmount) {
        this.otherTaxAmount = otherTaxAmount;
    }

    public java.math.BigDecimal getShippingTaxAmt() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.shippingTaxAmt);
    }

    public void setShippingTaxAmt(java.math.BigDecimal shippingTaxAmt) {
        this.shippingTaxAmt = shippingTaxAmt;
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

    public java.math.BigDecimal getSubtotal() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.subtotal);
    }

    public void setSubtotal(java.math.BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public java.math.BigDecimal getTotal() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.total);
    }

    public void setTotal(java.math.BigDecimal total) {
        this.total = total;
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

    public java.lang.String getAssignedBuyer() {
		return (java.lang.String)HiltonUtility.ckNull(this.assignedBuyer).trim();
    }

    public void setAssignedBuyer(java.lang.String assignedBuyer) {
		if (!HiltonUtility.isEmpty(assignedBuyer) && assignedBuyer.length() > 15) {
			assignedBuyer = assignedBuyer.substring(0, 15);
		}
		this.assignedBuyer = assignedBuyer;
    }

    public java.util.Date getAssignedDate() {
		return this.assignedDate;
//		return HiltonUtility.ckNull(this.assignedDate);
//		return (java.sql.Date)HiltonUtility.ckNull(this.assignedDate);
    }

    public void setAssignedDate(java.util.Date assignedDate) {
        this.assignedDate = assignedDate;
    }

    public java.math.BigDecimal getEstimatedCost() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.estimatedCost);
    }

    public void setEstimatedCost(java.math.BigDecimal estimatedCost) {
        this.estimatedCost = estimatedCost;
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

    public java.lang.String getShipAttn() {
		return (java.lang.String)HiltonUtility.ckNull(this.shipAttn).trim();
    }

    public void setShipAttn(java.lang.String shipAttn) {
		if (!HiltonUtility.isEmpty(shipAttn) && shipAttn.length() > 40) {
			shipAttn = shipAttn.substring(0, 40);
		}
		this.shipAttn = shipAttn;
    }

    public java.lang.String getVendorAttn() {
		return (java.lang.String)HiltonUtility.ckNull(this.vendorAttn).trim();
    }

    public void setVendorAttn(java.lang.String vendorAttn) {
		if (!HiltonUtility.isEmpty(vendorAttn) && vendorAttn.length() > 40) {
			vendorAttn = vendorAttn.substring(0, 40);
		}
		this.vendorAttn = vendorAttn;
    }

    public java.lang.String getRqHeaderKey() {
		return (java.lang.String)HiltonUtility.ckNull(this.rqHeaderKey).trim();
    }

    public void setRqHeaderKey(java.lang.String rqHeaderKey) {
		if (!HiltonUtility.isEmpty(rqHeaderKey) && rqHeaderKey.length() > 6) {
			rqHeaderKey = rqHeaderKey.substring(0, 6);
		}
		this.rqHeaderKey = rqHeaderKey;
    }

    public java.lang.String getRqSubType() {
		return (java.lang.String)HiltonUtility.ckNull(this.rqSubType).trim();
    }

    public void setRqSubType(java.lang.String rqSubType) {
		if (!HiltonUtility.isEmpty(rqSubType) && rqSubType.length() > 4) {
			rqSubType = rqSubType.substring(0, 4);
		}
		this.rqSubType = rqSubType;
    }

    public java.lang.String getPcardReq() {
		return (java.lang.String)HiltonUtility.ckNull(this.pcardReq).trim();
    }

    public void setPcardReq(java.lang.String pcardReq) {
		if (!HiltonUtility.isEmpty(pcardReq) && pcardReq.length() > 1) {
			pcardReq = pcardReq.substring(0, 1);
		}
		this.pcardReq = pcardReq;
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

    public java.math.BigDecimal getIcRevisedOrder() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.icRevisedOrder);
    }

    public void setIcRevisedOrder(java.math.BigDecimal icRevisedOrder) {
        this.icRevisedOrder = icRevisedOrder;
    }

    public java.lang.String getReqRecalc() {
		return (java.lang.String)HiltonUtility.ckNull(this.reqRecalc).trim();
    }

    public void setReqRecalc(java.lang.String reqRecalc) {
		if (!HiltonUtility.isEmpty(reqRecalc) && reqRecalc.length() > 1) {
			reqRecalc = reqRecalc.substring(0, 1);
		}
		this.reqRecalc = reqRecalc;
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

    public java.lang.String getMaxStatus() {
		return (java.lang.String)HiltonUtility.ckNull(this.maxStatus).trim();
    }

    public void setMaxStatus(java.lang.String maxStatus) {
		if (!HiltonUtility.isEmpty(maxStatus) && maxStatus.length() > 2) {
			maxStatus = maxStatus.substring(0, 2);
		}
		this.maxStatus = maxStatus;
    }

    public java.lang.String getBuyerRemarks() {
		return (java.lang.String)HiltonUtility.ckNull(this.buyerRemarks).trim();
    }

    public void setBuyerRemarks(java.lang.String buyerRemarks) {
		if (!HiltonUtility.isEmpty(buyerRemarks) && buyerRemarks.length() > 90) {
			buyerRemarks = buyerRemarks.substring(0, 90);
		}
		this.buyerRemarks = buyerRemarks;
    }

    public  java.math.BigDecimal getAmmendment() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.ammendment);
    }

    public void setAmmendment( java.math.BigDecimal ammendment) {
        this.ammendment = ammendment;
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

    public java.lang.String getAuthorizationCode() {
		return (java.lang.String)HiltonUtility.ckNull(this.authorizationCode).trim();
    }

    public void setAuthorizationCode(java.lang.String authorizationCode) {
		if (!HiltonUtility.isEmpty(authorizationCode) && authorizationCode.length() > 15) {
			authorizationCode = authorizationCode.substring(0, 15);
		}
		this.authorizationCode = authorizationCode;
    }

    public java.lang.String getVendorId() {
		return (java.lang.String)HiltonUtility.ckNull(this.vendorId).trim();
    }

    public void setVendorId(java.lang.String vendorId) {
		if (!HiltonUtility.isEmpty(vendorId) && vendorId.length() > 25) {
			vendorId = vendorId.substring(0, 25);
		}
		this.vendorId = vendorId;
    }

    public java.lang.String getShippingCode() {
		return (java.lang.String)HiltonUtility.ckNull(this.shippingCode).trim();
    }

    public void setShippingCode(java.lang.String shippingCode) {
		if (!HiltonUtility.isEmpty(shippingCode) && shippingCode.length() > 15) {
			shippingCode = shippingCode.substring(0, 15);
		}
		this.shippingCode = shippingCode;
    }

    public java.lang.String getBuyer() {
		return (java.lang.String)HiltonUtility.ckNull(this.buyer).trim();
    }

    public void setBuyer(java.lang.String buyer) {
		if (!HiltonUtility.isEmpty(buyer) && buyer.length() > 15) {
			buyer = buyer.substring(0, 15);
		}
		this.buyer = buyer;
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

    public java.lang.String getFobCode() {
		return (java.lang.String)HiltonUtility.ckNull(this.fobCode).trim();
    }

    public void setFobCode(java.lang.String fobCode) {
		if (!HiltonUtility.isEmpty(fobCode) && fobCode.length() > 15) {
			fobCode = fobCode.substring(0, 15);
		}
		this.fobCode = fobCode;
    }

    public java.lang.String getRouting() {
		return (java.lang.String)HiltonUtility.ckNull(this.routing).trim();
    }

    public void setRouting(java.lang.String routing) {
		if (!HiltonUtility.isEmpty(routing) && routing.length() > 25) {
			routing = routing.substring(0, 25);
		}
		this.routing = routing;
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

    public java.util.Date getRequiredDate() {
		return this.requiredDate;
//		return HiltonUtility.ckNull(this.requiredDate);
//		return (java.sql.Date)HiltonUtility.ckNull(this.requiredDate);
    }

    public void setRequiredDate(java.util.Date requiredDate) {
        this.requiredDate = requiredDate;
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
		if (!HiltonUtility.isEmpty(udf4Code) && udf4Code.length() > 30) {
			udf4Code = udf4Code.substring(0, 30);
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
		if (!HiltonUtility.isEmpty(udf6Code) && udf6Code.length() > 60) {
			udf6Code = udf6Code.substring(0, 60);
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

    public java.lang.String getAccountCode() {
		return (java.lang.String)HiltonUtility.ckNull(this.accountCode).trim();
    }

    public void setAccountCode(java.lang.String accountCode) {
		if (!HiltonUtility.isEmpty(accountCode) && accountCode.length() > 6) {
			accountCode = accountCode.substring(0, 6);
		}
		this.accountCode = accountCode;
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

    public java.lang.String getContactAddr() {
		return (java.lang.String)HiltonUtility.ckNull(this.contactAddr).trim();
    }

    public void setContactAddr(java.lang.String contactAddr) {
		if (!HiltonUtility.isEmpty(contactAddr) && contactAddr.length() > 25) {
			contactAddr = contactAddr.substring(0, 25);
		}
		this.contactAddr = contactAddr;
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

    public java.math.BigDecimal getIcHeaderHistory() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.icHeaderHistory);
    }

    public void setIcHeaderHistory(java.math.BigDecimal icHeaderHistory) {
        this.icHeaderHistory = icHeaderHistory;
    }

	public String getItemLocation() {
		return (java.lang.String)HiltonUtility.ckNull(this.itemLocation).trim();
//		return itemLocation;
	}

	public void setItemLocation(String itemLocation) {
		if (!HiltonUtility.isEmpty(itemLocation) && itemLocation.length() > 15) {
			itemLocation = itemLocation.substring(0, 15);
		}
		this.itemLocation = itemLocation;
	}

    public String toString() {
        return new ToStringBuilder(this)
            .append("icReqHeader", getIcReqHeader())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof RequisitionHeader) ) return false;
        RequisitionHeader castOther = (RequisitionHeader) other;
        return new EqualsBuilder()
            .append(this.getIcReqHeader(), castOther.getIcReqHeader())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getIcReqHeader())
            .toHashCode();
    }

	/**
	 * Returns the accountList.
	 * @return List
	 */
	public List getAccountList() {



		return accountList;
	}

	/**
	 * Returns the docAttachmentList.
	 * @return List
	 */
	public List getDocAttachmentList() {



		return docAttachmentList;
	}

	/**
	 * Returns the docCommentList.
	 * @return List
	 */
	public List getDocCommentList() {



		return docCommentList;
	}

	/**
	 * Returns the scheduleList.
	 * @return List
	 */
	public List getScheduleList() {



		return scheduleList;
	}

	/**
	 * Sets the accountList.
	 * @param accountList The accountList to set
	 */
	public void setAccountList(List accountList) {
		this.accountList = accountList;
	}

	/**
	 * Sets the docAttachmentList.
	 * @param docAttachmentList The docAttachmentList to set
	 */
	public void setDocAttachmentList(List docAttachmentList) {
		this.docAttachmentList = docAttachmentList;
	}

	/**
	 * Sets the docCommentList.
	 * @param docCommentList The docCommentList to set
	 */
	public void setDocCommentList(List docCommentList) {
		this.docCommentList = docCommentList;
	}

	/**
	 * Sets the scheduleList.
	 * @param scheduleList The scheduleList to set
	 */
	public void setScheduleList(List scheduleList) {
		this.scheduleList = scheduleList;
	}

	/**
	 * Returns the requisitionLineList.
	 * @return List
	 */
	public List getRequisitionLineList() {



		return requisitionLineList;
	}

	/**
	 * Sets the requisitionLineList.
	 * @param requisitionLineList The requisitionLineList to set
	 */
	public void setRequisitionLineList(List requisitionLineList) {
		this.requisitionLineList = requisitionLineList;
	}

	/**
	 * Returns the rfqInfoList.
	 * @return List
	 */
	public List getRfqInfoList() {
		return rfqInfoList;
	}

	/**
	 * Sets the rfqInfoList.
	 * @param rfqInfoList The rfqInfoList to set
	 */
	public void setRfqInfoList(List rfqInfoList) {
		this.rfqInfoList = rfqInfoList;
	}

	/**
	 * Returns the poInfoList.
	 * @return List
	 */
	public List getPoInfoList() {
		return poInfoList;
	}

	/**
	 * Sets the poInfoList.
	 * @param poInfoList The poInfoList to set
	 */
	public void setPoInfoList(List poInfoList) {
		this.poInfoList = poInfoList;
	}

	public Address getBillToAddress() {



		return this.billToAddress;
	}

	public void setBillToAddress(Address billToAddress) {
		this.billToAddress = billToAddress;
	}

	public Address getShipToAddress() {



		return this.shipToAddress;
	}

	public void setShipToAddress(Address shipToAddress) {
		this.shipToAddress = shipToAddress;
	}

	public Address getVendorAddress() {



		return this.vendorAddress;
	}

	public void setVendorAddress(Address vendorAddress) {
		this.vendorAddress = vendorAddress;
	}

	/**
	 * @return Returns the vendorName.
	 */
	public String getVendorName()
	{
		return vendorName;
	}

	/**
	 * @param vendorName The vendorName to set.
	 */
	public void setVendorName(String vendorName)
	{
		this.vendorName = vendorName;
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

    public java.lang.String getReceiptRequired() {
		return (java.lang.String)HiltonUtility.ckNull(this.receiptRequired).trim();
    }

    public void setReceiptRequired(java.lang.String receiptRequired) {
		if (!HiltonUtility.isEmpty(receiptRequired) && receiptRequired.length() > 1) {
			receiptRequired = receiptRequired.substring(0, 1);
		}
		this.receiptRequired = receiptRequired;
    }

	/**
	 * @return Returns user the requisition was forwarded to.
	 */
	public String getForwardedTo()
	{
		return forwardedTo;
	}

	/**
	 * @param forwardedTo The user id the requisition was forwarded to.
	 */
	public void setForwardedTo(String forwardedTo)
	{
		this.forwardedTo = forwardedTo;
	}

	public String getForwardedBy()
	{
		return (java.lang.String)HiltonUtility.ckNull(this.forwardedBy).trim();
	}

	public void setForwardedBy(String forwardedBy)
	{
		this.forwardedBy = forwardedBy;
	}

	public void setRejectAccess(boolean rejectAccess) {
	    this.rejectAccess = rejectAccess;
	}

	/*public void serializeMe()
	{
		ByteArrayOutputStream o = null;
		ObjectOutput output = null;
		try
		{
			System.out.println("start to serialize");
			o = new ByteArrayOutputStream();
			output = new ObjectOutputStream(new FileOutputStream("c:\\RequisitionHeader.ser"));
			output.writeObject(this);
			output.close();
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		finally
		{
			try
			{
				o.close();
				output.close();
			}
			catch (Exception e) {
				// TODO: handle exception
			}
			System.out.println("done serializing");
		}
	}
	*/

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

    public java.math.BigDecimal getCurrencyFactor() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.currencyFactor);
    }

    public void setCurrencyFactor(java.math.BigDecimal currencyFactor) {
        this.currencyFactor = currencyFactor;
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

	public java.util.Date getServicesStartDate() {
		return this.servicesStartDate;
    }

    public void setServicesStartDate(java.util.Date servicesStartDate) {
        this.servicesStartDate = servicesStartDate;
    }

    public java.util.Date getServicesEndDate() {
		return this.servicesEndDate;
    }

    public void setServicesEndDate(java.util.Date servicesEndDate) {
        this.servicesEndDate = servicesEndDate;
    }

	public String getUdf11Code()
	{
		return (java.lang.String)HiltonUtility.ckNull(this.udf11Code).trim();
	}

	public void setUdf11Code(String udf11Code)
	{
		this.udf11Code = udf11Code;
	}

	public String getUdf12Code()
	{
		return (java.lang.String)HiltonUtility.ckNull(this.udf12Code).trim();
	}

	public void setUdf12Code(String udf12Code)
	{
		this.udf12Code = udf12Code;
	}

	public String getUdf13Code()
	{
		return (java.lang.String)HiltonUtility.ckNull(this.udf13Code).trim();
	}

	public void setUdf13Code(String udf13Code)
	{
		this.udf13Code = udf13Code;
	}

	public String getUdf14Code()
	{
		return (java.lang.String)HiltonUtility.ckNull(this.udf14Code).trim();
	}

	public void setUdf14Code(String udf14Code)
	{
		this.udf14Code = udf14Code;
	}

	public String getUdf15Code()
	{
		return (java.lang.String)HiltonUtility.ckNull(this.udf15Code).trim();
	}

	public void setUdf15Code(String udf15Code)
	{
		this.udf15Code = udf15Code;
	}

	/**
	 * @return the budgetFlag
	 */
	public String getBudgetFlag()
	{
		return (java.lang.String) HiltonUtility.ckNull(this.budgetFlag).trim();
	}

	/**
	 * @param budgetFlag the budgetFlag to set
	 */
	public void setBudgetFlag(String budgetFlag)
	{
		this.budgetFlag = budgetFlag;
	}

    /**
     * @return the timeZone
     */
    public String getTimeZone()
    {
        return (java.lang.String) HiltonUtility.ckNull(this.timeZone).trim();
    }

    /**
     * @param timeZone the timeZone to set
     */
    public void setTimeZone(String timeZone)
    {
        if (!HiltonUtility.isEmpty(timeZone) && timeZone.length() > 30) {
            timeZone = timeZone.substring(0, 30);
        }
        this.timeZone = timeZone;
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

    public void setTotalUSD(java.math.BigDecimal totalUSD) {
        this.totalUSD = totalUSD;
    }

    public java.math.BigDecimal getTotalUSD() {
 		return (java.math.BigDecimal)HiltonUtility.ckNull((this.totalUSD));
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

    public java.util.Date getPlannedDate() {
    	return this.plannedDate;
    }

    public void setPlannedDate(java.util.Date plannedDate) {
    	this.plannedDate = plannedDate;
    }
}