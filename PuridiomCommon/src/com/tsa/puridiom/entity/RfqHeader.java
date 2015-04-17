package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsagate.foundation.database.Entity;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.Utility;

import java.io.Serializable;
import java.math.BigDecimal;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.Date;
import java.util.List;

/** @author Hibernate CodeGenerator */
public class RfqHeader extends Entity implements Serializable {

    /** identifier field */
    private java.math.BigDecimal icRfqHeader;

    /** nullable persistent field */
    private String rfqNumber;

    /** nullable persistent field */
    private java.util.Date rfqDate;

    /** nullable persistent field */
    private java.util.Date dueDate;

    /** nullable persistent field */
    private java.util.Date awardDate;

    /** nullable persistent field */
    private String status;

    /** nullable persistent field */
    private String rfqDescription;

    /** nullable persistent field */
    private String buyer;

    /** nullable persistent field */
    private String vendorAwarded;

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
    private String owner;

    /** nullable persistent field */
    private java.util.Date dateEntered;

    /** nullable persistent field */
    private String fiscalYear;

    /** nullable persistent field */
    private String language;

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
    private String requisitionNumber;

    /** nullable persistent field */
    private java.math.BigDecimal icReqHeader;

    /** nullable persistent field */
    private String webpost;

    /** nullable persistent field */
    private String rfqType;

    /** nullable persistent field */
    private String rfqAmendment;

    /** nullable persistent field */
    private String bidAccess;

    /** nullable persistent field */
    private String auctionType;

    /** nullable persistent field */
    private String bidDueTime;

    /** nullable persistent field */
    private String timeZone;

    /** nullable persistent field */
    private String bidItemOptions;

    /** nullable persistent field */
    private String actionAlertFlag;

    /** nullable persistent field */
    private String lowestBidSource;

    /** nullable persistent field */
    private String lowestBidReq;

    /** nullable persistent field */
    private String lowestDisplay;

    /** nullable persistent field */
    private String postFilename;

    /** nullable persistent field */
    private java.math.BigDecimal icHeaderHistory;

    /** nullable persistent field */
    private String requisitionerCode;

    /** nullable persistent field */
    private String departmentCode;

    /** nullable persistent field */
    private String authorizationCode;

    /** nullable persistent field */
    private String shippingCode;

    /** nullable persistent field */
    private String shipToCode;

    /** nullable persistent field */
    private String shipToContact;

    /** nullable persistent field */
    private String billToCode;

    /** nullable persistent field */
    private String billToContact;

    /** nullable persistent field */
    private String routing;

    /** nullable persistent field */
    private String priorityCode;

    /** nullable persistent field */
    private String taxCode;

    /** nullable persistent field */
    private java.util.Date requiredDate;

    /** nullable persistent field */
    private String itemLocation;

    /** nullable persistent field */
    private String receiptRequired;

    /** nullable persistent field */
    private java.math.BigDecimal extendMinutes;

    /** nullable persistent field */
    private java.math.BigDecimal bidVariance;

    /** nullable persistent field */
    private String caIndicateLowest;

    /** nullable persistent field */
    private String caLowestAmount;

    /** nullable persistent field */
    private String caWinningVendor;

    /** nullable persistent field */
    private String qaEvent;

    /** nullable persistent field */
    private String bidEvent;

    /** nullable persistent field */
    private String auctionEvent;

    /** nullable persistent field */
    private java.util.Date	qaStartDate;

    /** nullable persistent field */
    private String qaStartTime;

    /** nullable persistent field */
    private java.util.Date	qaEndDate;

    /** nullable persistent field */
    private String qaEndTime;

    /** nullable persistent field */
    private java.util.Date	bidStartDate;

    /** nullable persistent field */
    private String bidStartTime;

    /** nullable persistent field */
    private java.util.Date	bidEndDate;

    /** nullable persistent field */
    private String bidEndTime;

    /** nullable persistent field */
    private java.util.Date	auctionStartDate;

    /** nullable persistent field */
    private String auctionStartTime;

    /** nullable persistent field */
    private java.util.Date	auctionEndDate;

    /** nullable persistent field */
    private String auctionEndTime;

    /** nullable persistent field */
    private String allowProxyBids;

    /** nullable persistent field */
    private String eventPaused;

    /** nullable persistent field */
    private String currencyCode;

    /** nullable persistent field */
    private java.math.BigDecimal currencyFactor;

    /** nullable persistent field */
    private java.math.BigDecimal maxPoints;

    /** nullable persistent field */
    private String gfpGfm;

    /** nullable persistent field */
    private String requestCat;

    /** nullable persistent field */
    private String kit;

    /** nullable persistent field */
    private String workOrder;

    /** nullable persistent field */
    private String setAside;

    /** nullable persistent field */
    private String naics;

    /** nullable persistent field */
    private java.math.BigDecimal estimatedCost;

    /** nullable persistent field */
    private String corrosionEval;

    private String originalReqType;

	private List docAttachmentList;

    private List docCommentList;

	private List rfqBidList;

	private List rfqLineList;

	private List rfqQuestionList;

	private List rfqVendorList;

	private List scheduleList;

	private List vendorQuestionList;

	private List requisitionInfoList;

	private List poInfoList;

	private Address billToAddress;

	private Address shipToAddress;

	private Address vendorAddress;

	private java.math.BigDecimal lowestBidSubtotal;

	private String lowestVendorId;

    /** full constructor */
    public RfqHeader(java.math.BigDecimal icRfqHeader, java.lang.String rfqNumber, java.util.Date rfqDate, java.util.Date dueDate, java.util.Date awardDate, java.lang.String status, java.lang.String rfqDescription, java.lang.String buyer, java.lang.String vendorAwarded, java.lang.String udf1Code, java.lang.String udf2Code, java.lang.String udf3Code, java.lang.String udf4Code, java.lang.String udf5Code, java.lang.String udf6Code, java.lang.String udf7Code, java.lang.String udf8Code, java.lang.String udf9Code, java.lang.String udf10Code, java.lang.String owner, java.util.Date dateEntered, java.lang.String fiscalYear, java.lang.String language, java.lang.String approved, java.lang.String appBy, java.util.Date appDate, java.lang.String appSigned, java.lang.String lastChgBy, java.util.Date lastChgDate, java.lang.String requisitionNumber, java.math.BigDecimal icReqHeader, java.lang.String webpost, java.lang.String rfqType, java.lang.String rfqAmendment, java.lang.String bidAccess, java.lang.String auctionType, java.lang.String bidDueTime, java.lang.String timeZone, java.lang.String bidItemOptions, java.lang.String actionAlertFlag, java.lang.String lowestBidSource, java.lang.String lowestBidReq, java.lang.String lowestDisplay, java.lang.String postFilename, java.math.BigDecimal icHeaderHistory, java.lang.String requisitionerCode, java.lang.String departmentCode, java.lang.String authorizationCode, java.lang.String shippingCode, java.lang.String shipToCode, java.lang.String shipToContact, java.lang.String billToCode, java.lang.String billToContact, java.lang.String routing, java.lang.String priorityCode, java.lang.String taxCode, java.util.Date requiredDate, java.lang.String itemLocation, java.lang.String receiptRequired, java.math.BigDecimal extendMinutes, java.math.BigDecimal bidVariance, java.lang.String caIndicateLowest, java.lang.String caLowestAmount, String caWinningVendor, java.lang.String allowProxyBids, java.lang.String eventPaused, java.lang.String currencyCode, java.math.BigDecimal currencyFactor, java.math.BigDecimal maxPoints, java.lang.String gfpGfm, java.lang.String requestCat, java.lang.String kit, java.lang.String workOrder, java.lang.String setAside, java.lang.String naics, java.math.BigDecimal estimatedCost, java.lang.String corrosionEval) {
        this.icRfqHeader = icRfqHeader;
        this.rfqNumber = rfqNumber;
        this.rfqDate = rfqDate;
        this.dueDate = dueDate;
        this.awardDate = awardDate;
        this.status = status;
        this.rfqDescription = rfqDescription;
        this.buyer = buyer;
        this.vendorAwarded = vendorAwarded;
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
        this.owner = owner;
        this.dateEntered = dateEntered;
        this.fiscalYear = fiscalYear;
        this.language = language;
        this.approved = approved;
        this.appBy = appBy;
        this.appDate = appDate;
        this.appSigned = appSigned;
        this.lastChgBy = lastChgBy;
        this.lastChgDate = lastChgDate;
        this.requisitionNumber = requisitionNumber;
        this.icReqHeader = icReqHeader;
        this.webpost = webpost;
        this.rfqType = rfqType;
        this.rfqAmendment = rfqAmendment;
        this.bidAccess = bidAccess;
        this.auctionType = auctionType;
        this.bidDueTime = bidDueTime;
        this.timeZone = timeZone;
        this.bidItemOptions = bidItemOptions;
        this.actionAlertFlag = actionAlertFlag;
        this.lowestBidSource = lowestBidSource;
        this.lowestBidReq = lowestBidReq;
        this.lowestDisplay = lowestDisplay;
        this.postFilename = postFilename;
        this.icHeaderHistory = icHeaderHistory;
        this.requisitionerCode = requisitionerCode;
        this.departmentCode = departmentCode;
        this.authorizationCode = authorizationCode;
        this.shippingCode = shippingCode;
        this.shipToCode = shipToCode;
        this.shipToContact = shipToContact;
        this.billToCode = billToCode;
        this.billToContact = billToContact;
        this.routing = routing;
        this.priorityCode = priorityCode;
        this.taxCode = taxCode;
        this.requiredDate = requiredDate;
        this.itemLocation = itemLocation;
        this.receiptRequired = receiptRequired;
        this.extendMinutes = extendMinutes;
        this.bidVariance = bidVariance;
        this.caIndicateLowest = caIndicateLowest;
        this.caLowestAmount = caLowestAmount;
        this.caWinningVendor = caWinningVendor;
        this.allowProxyBids = allowProxyBids;
        this.eventPaused = eventPaused;
        this.currencyCode = currencyCode;
        this.currencyFactor = currencyFactor;
        this.maxPoints = maxPoints;
        this.gfpGfm = gfpGfm;
        this.requestCat = requestCat;
        this.kit = kit;
        this.workOrder = workOrder;
        this.setAside = setAside;
        this.naics = naics;
        this.estimatedCost = estimatedCost;
        this.corrosionEval = corrosionEval;
    }

    /** default constructor */
    public RfqHeader() {
    }

    /** minimal constructor */
    public RfqHeader(java.math.BigDecimal icRfqHeader) {
        this.icRfqHeader = icRfqHeader;
    }

    public java.math.BigDecimal getIcRfqHeader() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.icRfqHeader);
    }

    public void setIcRfqHeader(java.math.BigDecimal icRfqHeader) {
        this.icRfqHeader = icRfqHeader;
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

    public java.util.Date getRfqDate() {
		return this.rfqDate;
//		return HiltonUtility.ckNull(this.rfqDate);
//		return (java.sql.Date)HiltonUtility.ckNull(this.rfqDate);
    }

    public void setRfqDate(java.util.Date rfqDate) {
        this.rfqDate = rfqDate;
    }

    public java.util.Date getDueDate() {
		return this.dueDate;
//		return HiltonUtility.ckNull(this.dueDate);
//		return (java.sql.Date)HiltonUtility.ckNull(this.dueDate);
    }

    public void setDueDate(java.util.Date dueDate) {
        this.dueDate = dueDate;
    }

    public java.util.Date getAwardDate() {
		return this.awardDate;
//		return HiltonUtility.ckNull(this.awardDate);
//		return (java.sql.Date)HiltonUtility.ckNull(this.awardDate);
    }

    public void setAwardDate(java.util.Date awardDate) {
        this.awardDate = awardDate;
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

    public java.lang.String getRfqDescription() {
		return (java.lang.String)HiltonUtility.ckNull(this.rfqDescription).trim();
    }

    public void setRfqDescription(java.lang.String rfqDescription) {
		if (!HiltonUtility.isEmpty(rfqDescription) && rfqDescription.length() > 255) {
			rfqDescription = rfqDescription.substring(0, 255);
		}
		this.rfqDescription = rfqDescription;
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

    public java.lang.String getVendorAwarded() {
		return (java.lang.String)HiltonUtility.ckNull(this.vendorAwarded).trim();
    }

    public void setVendorAwarded(java.lang.String vendorAwarded) {
		if (!HiltonUtility.isEmpty(vendorAwarded) && vendorAwarded.length() > 15) {
			vendorAwarded = vendorAwarded.substring(0, 15);
		}
		this.vendorAwarded = vendorAwarded;
    }

    public java.lang.String getUdf1Code() {
		return (java.lang.String)HiltonUtility.ckNull(this.udf1Code).trim();
    }

    public void setUdf1Code(java.lang.String udf1Code) {
		if (!HiltonUtility.isEmpty(udf1Code) && udf1Code.length() > 30) {
			udf1Code = udf1Code.substring(0, 30);
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

    public java.lang.String getRequisitionNumber() {
		return (java.lang.String)HiltonUtility.ckNull(this.requisitionNumber).trim();
    }

    public void setRequisitionNumber(java.lang.String requisitionNumber) {
		if (!HiltonUtility.isEmpty(requisitionNumber) && requisitionNumber.length() > 20) {
			requisitionNumber = requisitionNumber.substring(0, 20);
		}
		this.requisitionNumber = requisitionNumber;
    }

    public java.math.BigDecimal getIcReqHeader() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.icReqHeader);
    }

    public void setIcReqHeader(java.math.BigDecimal icReqHeader) {
        this.icReqHeader = icReqHeader;
    }

    public java.lang.String getWebpost() {
		return (java.lang.String)HiltonUtility.ckNull(this.webpost).trim();
    }

    public void setWebpost(java.lang.String webpost) {
		if (!HiltonUtility.isEmpty(webpost) && webpost.length() > 5) {
			webpost = webpost.substring(0, 5);
		}
		this.webpost = webpost;
    }

    public java.lang.String getRfqType() {
		return (java.lang.String)HiltonUtility.ckNull(this.rfqType).trim();
    }

    public void setRfqType(java.lang.String rfqType) {
		if (!HiltonUtility.isEmpty(rfqType) && rfqType.length() > 2) {
			rfqType = rfqType.substring(0, 2);
		}
		this.rfqType = rfqType;
    }

    public java.lang.String getRfqAmendment() {
		return (java.lang.String)HiltonUtility.ckNull(this.rfqAmendment).trim();
    }

    public void setRfqAmendment(java.lang.String rfqAmendment) {
		if (!HiltonUtility.isEmpty(rfqAmendment) && rfqAmendment.length() > 4) {
			rfqAmendment = rfqAmendment.substring(0, 4);
		}
		this.rfqAmendment = rfqAmendment;
    }

    public java.lang.String getBidAccess() {
		return (java.lang.String)HiltonUtility.ckNull(this.bidAccess).trim();
    }

    public void setBidAccess(java.lang.String bidAccess) {
		if (!HiltonUtility.isEmpty(bidAccess) && bidAccess.length() > 1) {
			bidAccess = bidAccess.substring(0, 1);
		}
		this.bidAccess = bidAccess;
    }

    public java.lang.String getAuctionType() {
		return (java.lang.String)HiltonUtility.ckNull(this.auctionType).trim();
    }

    public void setAuctionType(java.lang.String auctionType) {
		if (!HiltonUtility.isEmpty(auctionType) && auctionType.length() > 1) {
			auctionType = auctionType.substring(0, 1);
		}
		this.auctionType = auctionType;
    }

    public java.lang.String getBidDueTime() {
		return (java.lang.String)Utility.ckNull(this.bidDueTime).trim();
    }

    public void setBidDueTime(java.lang.String bidDueTime) {
		if (!HiltonUtility.isEmpty(bidDueTime) && bidDueTime.length() > 5) {
			bidDueTime = bidDueTime.substring(0, 5);
		}
		this.bidDueTime = bidDueTime;
    }

    public java.lang.String getTimeZone() {
		return (java.lang.String)HiltonUtility.ckNull(this.timeZone).trim();
    }

    public void setTimeZone(java.lang.String timeZone) {
		if (!HiltonUtility.isEmpty(timeZone) && timeZone.length() > 30) {
			timeZone = timeZone.substring(0, 30);
		}
		this.timeZone = timeZone;
    }

    public java.lang.String getBidItemOptions() {
		return (java.lang.String)HiltonUtility.ckNull(this.bidItemOptions).trim();
    }

    public void setBidItemOptions(java.lang.String bidItemOptions) {
		if (!HiltonUtility.isEmpty(bidItemOptions) && bidItemOptions.length() > 1) {
			bidItemOptions = bidItemOptions.substring(0, 1);
		}
		this.bidItemOptions = bidItemOptions;
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

    public java.lang.String getLowestBidSource() {
		return (java.lang.String)HiltonUtility.ckNull(this.lowestBidSource).trim();
    }

    public void setLowestBidSource(java.lang.String lowestBidSource) {
		if (!HiltonUtility.isEmpty(lowestBidSource) && lowestBidSource.length() > 1) {
			lowestBidSource = lowestBidSource.substring(0, 1);
		}
		this.lowestBidSource = lowestBidSource;
    }

    public java.lang.String getLowestBidReq() {
		return (java.lang.String)HiltonUtility.ckNull(this.lowestBidReq).trim();
    }

    public void setLowestBidReq(java.lang.String lowestBidReq) {
		if (!HiltonUtility.isEmpty(lowestBidReq) && lowestBidReq.length() > 1) {
			lowestBidReq = lowestBidReq.substring(0, 1);
		}
		this.lowestBidReq = lowestBidReq;
    }

    public java.lang.String getLowestDisplay() {
		return (java.lang.String)HiltonUtility.ckNull(this.lowestDisplay).trim();
    }

    public void setLowestDisplay(java.lang.String lowestDisplay) {
		if (!HiltonUtility.isEmpty(lowestDisplay) && lowestDisplay.length() > 1) {
			lowestDisplay = lowestDisplay.substring(0, 1);
		}
		this.lowestDisplay = lowestDisplay;
    }

    public java.lang.String getPostFilename() {
		return (java.lang.String)HiltonUtility.ckNull(this.postFilename).trim();
    }

    public void setPostFilename(java.lang.String postFilename) {
		if (!HiltonUtility.isEmpty(postFilename) && postFilename.length() > 20) {
			postFilename = postFilename.substring(0, 20);
		}
		this.postFilename = postFilename;
    }

    public java.math.BigDecimal getIcHeaderHistory() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.icHeaderHistory);
    }

    public void setIcHeaderHistory(java.math.BigDecimal icHeaderHistory) {
        this.icHeaderHistory = icHeaderHistory;
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

    public java.lang.String getShippingCode() {
		return (java.lang.String)HiltonUtility.ckNull(this.shippingCode).trim();
    }

    public void setShippingCode(java.lang.String shippingCode) {
		if (!HiltonUtility.isEmpty(shippingCode) && shippingCode.length() > 15) {
			shippingCode = shippingCode.substring(0, 15);
		}
		this.shippingCode = shippingCode;
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

    public java.lang.String getTaxCode() {
		return (java.lang.String)HiltonUtility.ckNull(this.taxCode).trim();
    }

    public void setTaxCode(java.lang.String taxCode) {
		if (!HiltonUtility.isEmpty(taxCode) && taxCode.length() > 15) {
			taxCode = taxCode.substring(0, 15);
		}
		this.taxCode = taxCode;
    }

    public java.util.Date getRequiredDate() {
		return this.requiredDate;
//		return HiltonUtility.ckNull(this.requiredDate);
//		return (java.sql.Date)HiltonUtility.ckNull(this.requiredDate);
    }

    public void setRequiredDate(java.util.Date requiredDate) {
        this.requiredDate = requiredDate;
    }

    public java.lang.String getItemLocation() {
		return (java.lang.String)HiltonUtility.ckNull(this.itemLocation).trim();
    }

    public void setItemLocation(java.lang.String itemLocation) {
		if (!HiltonUtility.isEmpty(itemLocation) && itemLocation.length() > 15) {
			itemLocation = itemLocation.substring(0, 15);
		}
		this.itemLocation = itemLocation;
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

    public java.math.BigDecimal getExtendMinutes() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.extendMinutes);
    }

    public void setExtendMinutes(java.math.BigDecimal extendMinutes) {
        this.extendMinutes = extendMinutes;
    }

    public java.math.BigDecimal getBidVariance() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.bidVariance);
    }

    public void setBidVariance(java.math.BigDecimal bidVariance) {
        this.bidVariance = bidVariance;
    }

    public java.lang.String getCaIndicateLowest() {
		return (java.lang.String)HiltonUtility.ckNull(this.caIndicateLowest).trim();
    }

    public void setCaIndicateLowest(java.lang.String caIndicateLowest) {
		if (!HiltonUtility.isEmpty(caIndicateLowest) && caIndicateLowest.length() > 1) {
			caIndicateLowest = caIndicateLowest.substring(0, 1);
		}
		this.caIndicateLowest = caIndicateLowest;
    }

    public java.lang.String getCaLowestAmount() {
		return (java.lang.String)HiltonUtility.ckNull(this.caLowestAmount).trim();
    }

    public void setCaLowestAmount(java.lang.String caLowestAmount) {
		if (!HiltonUtility.isEmpty(caLowestAmount) && caLowestAmount.length() > 1) {
			caLowestAmount = caLowestAmount.substring(0, 1);
		}
		this.caLowestAmount = caLowestAmount;
    }

    public java.lang.String getCaWinningVendor() {
		return (java.lang.String)HiltonUtility.ckNull(this.caWinningVendor).trim();
    }

    public void setCaWinningVendor(java.lang.String caWinningVendor) {
		if (!HiltonUtility.isEmpty(caWinningVendor) && caWinningVendor.length() > 1) {
			caWinningVendor = caWinningVendor.substring(0, 1);
		}
		this.caWinningVendor = caWinningVendor;
    }

    public String getQaEvent() {
		return (java.lang.String)HiltonUtility.ckNull(this.qaEvent).trim();
    }

    public void setQaEvent(String qaEvent) {
		if (!HiltonUtility.isEmpty(qaEvent) && qaEvent.length() > 1) {
			qaEvent = qaEvent.substring(0, 1);
		}
		this.qaEvent = qaEvent;
    }

    public String getBidEvent() {
		return (java.lang.String)HiltonUtility.ckNull(this.bidEvent).trim();
    }

    public void setBidEvent(String bidEvent) {
		if (!HiltonUtility.isEmpty(bidEvent) && bidEvent.length() > 1) {
			bidEvent = bidEvent.substring(0, 1);
		}
		this.bidEvent = bidEvent;
    }

    public String getAuctionEvent() {
		return (java.lang.String)HiltonUtility.ckNull(this.auctionEvent).trim();
    }

    public void setAuctionEvent(String auctionEvent) {
		if (!HiltonUtility.isEmpty(auctionEvent) && auctionEvent.length() > 1) {
			auctionEvent = auctionEvent.substring(0, 1);
		}
		this.auctionEvent = auctionEvent;
    }

    public java.util.Date getQaStartDate() {
        return this.qaStartDate;
    }

    public void setQaStartDate(java.util.Date qaStartDate) {
        this.qaStartDate = qaStartDate;
    }

    public String getQaStartTime() {
		return (java.lang.String)Utility.ckNull(this.qaStartTime).trim();
    }

    public void setQaStartTime(String qaStartTime) {
		if (!HiltonUtility.isEmpty(qaStartTime) && qaStartTime.length() > 8) {
			qaStartTime = qaStartTime.substring(0, 8);
		}
		this.qaStartTime = qaStartTime;
    }

    public java.util.Date getQaEndDate() {
        return this.qaEndDate;
    }

    public void setQaEndDate(java.util.Date qaEndDate) {
        this.qaEndDate = qaEndDate;
    }

    public String getQaEndTime() {
		return (java.lang.String)Utility.ckNull(this.qaEndTime).trim();
    }

    public void setQaEndTime(String qaEndTime) {
		if (!HiltonUtility.isEmpty(qaEndTime) && qaEndTime.length() > 8) {
			qaEndTime = qaEndTime.substring(0, 8);
		}
		this.qaEndTime = qaEndTime;
    }

    public java.util.Date getBidEndDate() {
        return bidEndDate;
    }

    public void setBidEndDate(java.util.Date bidEndDate) {
        this.bidEndDate = bidEndDate;
    }

    public java.util.Date getBidStartDate() {
        return bidStartDate;
    }

    public void setBidStartDate(java.util.Date bidStartDate) {
        this.bidStartDate = bidStartDate;
    }

    public String getBidStartTime() {
		return (java.lang.String)Utility.ckNull(this.bidStartTime).trim();
    }

    public void setBidStartTime(String bidStartTime) {
		if (!HiltonUtility.isEmpty(bidStartTime) && bidStartTime.length() > 8) {
			bidStartTime = bidStartTime.substring(0, 8);
		}
		this.bidStartTime = bidStartTime;
    }

    public String getBidEndTime() {
		return (java.lang.String)Utility.ckNull(this.bidEndTime).trim();
    }

    public void setBidEndTime(String bidEndTime) {
		if (!HiltonUtility.isEmpty(bidEndTime) && bidEndTime.length() > 8) {
			bidEndTime = bidEndTime.substring(0, 8);
		}
		this.bidEndTime = bidEndTime;
    }

    public java.util.Date getAuctionStartDate() {
        return auctionStartDate;
    }

    public void setAuctionStartDate(java.util.Date auctionStartDate) {
        this.auctionStartDate = auctionStartDate;
    }

    public String getAuctionStartTime() {
		return (java.lang.String)Utility.ckNull(this.auctionStartTime).trim();
    }

    public void setAuctionStartTime(String auctionStartTime) {
		if (!HiltonUtility.isEmpty(auctionStartTime) && auctionStartTime.length() > 8) {
			auctionStartTime = auctionStartTime.substring(0, 8);
		}
		this.auctionStartTime = auctionStartTime;
    }

    public java.util.Date getAuctionEndDate() {
        return auctionEndDate;
    }

    public void setAuctionEndDate(java.util.Date auctionEndDate) {
        this.auctionEndDate = auctionEndDate;
    }

    public String getAuctionEndTime() {
		return (java.lang.String)Utility.ckNull(this.auctionEndTime).trim();
    }

    public void setAuctionEndTime(String auctionEndTime) {
		if (!HiltonUtility.isEmpty(auctionEndTime) && auctionEndTime.length() > 8) {
			auctionEndTime = auctionEndTime.substring(0, 8);
		}
		this.auctionEndTime = auctionEndTime;
    }

	public String getAllowProxyBids() {
		return (java.lang.String)HiltonUtility.ckNull(this.allowProxyBids).trim();
	}

	public void setAllowProxyBids(String allowProxyBids) {
		if (!HiltonUtility.isEmpty(allowProxyBids) && allowProxyBids.length() > 1) {
			allowProxyBids = allowProxyBids.substring(0, 1);
		}
		this.allowProxyBids = allowProxyBids;
	}

	public String getEventPaused() {
		return (java.lang.String)HiltonUtility.ckNull(this.eventPaused).trim();
	}

	public void setEventPaused(String eventPaused) {
		if (!HiltonUtility.isEmpty(eventPaused) && eventPaused.length() > 1) {
			eventPaused = eventPaused.substring(0, 1);
		}
		this.eventPaused = eventPaused;
	}

	public List getDocAttachmentList() {
		return this.docAttachmentList;
	}

	public void setDocAttachmentList(List docAttachmentList) {
		this.docAttachmentList = docAttachmentList;
	}

	public List getDocCommentList() {
	    return this.docCommentList;
	}

	public void setDocCommentList(List docCommentList) {
		this.docCommentList = docCommentList;
	}

	public List getRfqBidList() {
		return this.rfqBidList;
	}

	public void setRfqBidList(List rfqBidList) {
		this.rfqBidList = rfqBidList;
	}

	public List getRfqLineList() {
		return this.rfqLineList;
	}

	public void setRfqLineList(List rfqLineList) {
		this.rfqLineList = rfqLineList;
	}

	public List getRfqVendorList() {
		return this.rfqVendorList;
	}

	public void setRfqVendorList(List rfqVendorList) {
		this.rfqVendorList = rfqVendorList;
	}

	public List getRfqQuestionList() {
		return this.rfqQuestionList;
	}

	public void setRfqQuestionList(List rfqQuestionList) {
		this.rfqQuestionList = rfqQuestionList;
	}

	public List getScheduleList() {
		return this.scheduleList;
	}

	public void setScheduleList(List scheduleList) {
		this.scheduleList = scheduleList;
	}

	public List getVendorQuestionList() {
		return this.vendorQuestionList;
	}

	public void setVendorQuestionList(List vendorQuestionList) {
		this.vendorQuestionList = vendorQuestionList;
	}

	public List getRequisitionInfoList() {
		return this.requisitionInfoList;
	}

	public void setRequisitionInfoList(List requisitionInfoList) {
		this.requisitionInfoList = requisitionInfoList;
	}

	public List getPoInfoList() {
		return this.poInfoList;
	}

	public void setPoInfoList(List poInfoList) {
		this.poInfoList = poInfoList;
	}

	public Address getShipToAddress() {
		return this.shipToAddress;
	}

	public void setShipToAddress(Address shipToAddress) {
		this.shipToAddress = shipToAddress;
	}

	public Address getBillToAddress() {
		return this.billToAddress;
	}

	public void setBillToAddress(Address billToAddress) {
		this.billToAddress = billToAddress;
	}

	public Address getVendorAddress() {
		return this.vendorAddress;
	}

	public void setVendorAddress(Address vendorAddress) {
		this.vendorAddress = vendorAddress;
	}

	public String getLowestVendorId() {
		return (java.lang.String)HiltonUtility.ckNull(this.lowestVendorId).trim();
	}

	public void setLowestVendorId(String lowestVendorId) {
		this.lowestVendorId = lowestVendorId;
	}

	public java.math.BigDecimal getLowestBidSubtotal() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.lowestBidSubtotal);
	}

	public void setLowestBidSubtotal(java.math.BigDecimal lowestBidSubtotal) {
		this.lowestBidSubtotal = lowestBidSubtotal;
	}

	public Date getQaStartDateTime() {
	    return Dates.getDate(HiltonUtility.getFormattedDate(this.getQaStartDate(), "", "yyyy-MM-dd") + " " + this.getQaStartTime());
	}

	public Date getQaEndDateTime() {
	    return Dates.getDate(HiltonUtility.getFormattedDate(this.getQaEndDate(), "", "yyyy-MM-dd") + " " + this.getQaEndTime());
	}

	public Date getBidStartDateTime() {
	    return Dates.getDate(HiltonUtility.getFormattedDate(this.getBidStartDate(), "", "yyyy-MM-dd") + " " + this.getBidStartTime());
	}

	public Date getBidEndDateTime() {
	    return Dates.getDate(HiltonUtility.getFormattedDate(this.getBidEndDate(), "", "yyyy-MM-dd") + " " + this.getBidEndTime());
	}

	public Date getAuctionStartDateTime() {
	    return Dates.getDate(HiltonUtility.getFormattedDate(this.getAuctionStartDate(), "", "yyyy-MM-dd") + " " + this.getAuctionStartTime());
	}

	public Date getAuctionEndDateTime() {
	    return Dates.getDate(HiltonUtility.getFormattedDate(this.getAuctionEndDate(), "", "yyyy-MM-dd") + " " + this.getAuctionEndTime());
	}

    public Date getDueDateTime() {
        return Dates.getDate(HiltonUtility.getFormattedDate(this.getDueDate(), "", "yyyy-MM-dd") + " " + this.getBidDueTime());
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

    public java.math.BigDecimal getCurrencyFactor() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.currencyFactor);
    }

    public void setCurrencyFactor(java.math.BigDecimal currencyFactor) {
        this.currencyFactor = currencyFactor;
    }

    public java.math.BigDecimal getMaxPoints() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.maxPoints);
    }

    public void setMaxPoints(java.math.BigDecimal maxPoints) {
        this.maxPoints = maxPoints;
    }

	public StringBuffer getDisplayRfqNumber() {
        String rfq =  this.getRfqNumber();
        StringBuffer subject = new StringBuffer("");
        subject.append("Solicitation ");
        subject.append(rfq);
        return subject;
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
		if (!HiltonUtility.isEmpty(requestCat) && requestCat.length() > 1) {
			requestCat = requestCat.substring(0, 1);
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

    public java.lang.String getSetAside() {
		return (java.lang.String)HiltonUtility.ckNull(this.setAside).trim();
    }

    public void setSetAside(java.lang.String setAside) {
		if (!HiltonUtility.isEmpty(setAside) && setAside.length() > 15) {
			setAside = setAside.substring(0, 15);
		}
		this.setAside = setAside;
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

	public java.math.BigDecimal getEstimatedCost() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.estimatedCost);
	}

	public void setEstimatedCost(java.math.BigDecimal estimatedCost) {
		this.estimatedCost = estimatedCost;
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

	public String getOriginalReqType() {
		return (String)HiltonUtility.ckNull(this.originalReqType).trim();
	}

	public void setOriginalReqType(String originalReqType) {
		if (!HiltonUtility.isEmpty(originalReqType) && originalReqType.length() > 1) {
			originalReqType = originalReqType.substring(0, 1);
		}
		this.originalReqType = originalReqType;
	}

    public String toString() {
        return new ToStringBuilder(this)
            .append("icRfqHeader", getIcRfqHeader())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof RfqHeader) ) return false;
        RfqHeader castOther = (RfqHeader) other;
        return new EqualsBuilder()
            .append(this.getIcRfqHeader(), castOther.getIcRfqHeader())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getIcRfqHeader())
            .toHashCode();
    }
}
