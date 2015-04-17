package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import java.util.List;

/** @author Hibernate CodeGenerator */
public class SaleHeader implements Serializable {

    /** identifier field */
    private java.math.BigDecimal icSaleHeader;

    /** nullable persistent field */
    private String saleNumber;

    /** nullable persistent field */
    private java.util.Date saleDate;

    /** nullable persistent field */
    private java.util.Date awardDate;

    /** nullable persistent field */
    private String status;

    /** nullable persistent field */
    private String description;

    /** nullable persistent field */
    private String contact;

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
    private String webpost;

    /** nullable persistent field */
    private String amendment;

    /** nullable persistent field */
    private String bidAccess;

    /** nullable persistent field */
    private String auctionType;
    
    /** nullable persistent field */
    private String timeZone;

    /** nullable persistent field */
    private String currency;

    /** nullable persistent field */
    private String actionAlertFlag;

    /** nullable persistent field */
    private String highestBidSource;

    /** nullable persistent field */
    private String highestBidReq;

    /** nullable persistent field */
    private String highestDisplay;

    /** nullable persistent field */
    private String postFilename;

    /** nullable persistent field */
    private java.math.BigDecimal icHeaderHistory;

    /** nullable persistent field */
    private String departmentCode;

    /** nullable persistent field */
    private String authorizationCode;

    /** nullable persistent field */
    private String taxCode;

    /** nullable persistent field */
    private java.math.BigDecimal extendMinutes;
    
    /** nullable persistent field */
    private java.math.BigDecimal bidVariance;

    /** nullable persistent field */
    private String caIndicateHighest;
    
    /** nullable persistent field */
    private String caHighestAmount;
    
    /** nullable persistent field */
    private String caWinningBuyer;
    
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
    
	private List docAttachmentList;
    
    private List docCommentList;
	
	private List saleBidList;
	
	private SaleLine saleLine;
	
	private List saleQuestionList;
	
	private List saleBuyerList;

	private java.math.BigDecimal highestBidSubtotal;
	
	private String highestBuyer;
	
    /** full constructor */
    public SaleHeader(java.math.BigDecimal icSaleHeader, java.lang.String saleNumber, java.util.Date saleDate, java.util.Date awardDate, java.lang.String status, java.lang.String description, java.lang.String contact, java.lang.String udf1Code, java.lang.String udf2Code, java.lang.String udf3Code, java.lang.String udf4Code, java.lang.String udf5Code, java.lang.String udf6Code, java.lang.String udf7Code, java.lang.String udf8Code, java.lang.String udf9Code, java.lang.String udf10Code, java.lang.String owner, java.util.Date dateEntered, java.lang.String fiscalYear, java.lang.String language, java.lang.String approved, java.lang.String appBy, java.util.Date appDate, java.lang.String appSigned, java.lang.String lastChgBy, java.util.Date lastChgDate, java.lang.String webpost, java.lang.String amendment, java.lang.String bidAccess, java.lang.String auctionType, java.lang.String timeZone, java.lang.String currency, java.lang.String actionAlertFlag, java.lang.String highestBidSource, java.lang.String highestBidReq, java.lang.String highestDisplay, java.lang.String postFilename, java.math.BigDecimal icHeaderHistory, java.lang.String departmentCode, java.lang.String authorizationCode, java.lang.String taxCode, java.lang.String itemLocation, java.math.BigDecimal extendMinutes, java.math.BigDecimal bidVariance, java.lang.String caIndicateHighest, java.lang.String caHighestAmount, String caWinningBuyer) {
        this.icSaleHeader = icSaleHeader;
        this.saleNumber = saleNumber;
        this.saleDate = saleDate;
        this.awardDate = awardDate;
        this.status = status;
        this.description = description;
        this.contact = contact;
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
        this.webpost = webpost;
        this.amendment = amendment;
        this.bidAccess = bidAccess;
        this.auctionType = auctionType;
        this.timeZone = timeZone;
        this.currency = currency;
        this.actionAlertFlag = actionAlertFlag;
        this.highestBidSource = highestBidSource;
        this.highestBidReq = highestBidReq;
        this.highestDisplay = highestDisplay;
        this.postFilename = postFilename;
        this.icHeaderHistory = icHeaderHistory;
        this.departmentCode = departmentCode;
        this.authorizationCode = authorizationCode;
        this.taxCode = taxCode;
        this.extendMinutes = extendMinutes;
        this.bidVariance = bidVariance;
        this.caIndicateHighest = caIndicateHighest;
        this.caHighestAmount = caHighestAmount;
        this.caWinningBuyer = caWinningBuyer;
    }

    /** default constructor */
    public SaleHeader() {
    }

    /** minimal constructor */
    public SaleHeader(java.math.BigDecimal icSaleHeader) {
        this.icSaleHeader = icSaleHeader;
    }

    public java.math.BigDecimal getIcSaleHeader() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.icSaleHeader);
    }

    public void setIcSaleHeader(java.math.BigDecimal icSaleHeader) {
        this.icSaleHeader = icSaleHeader;
    }

    public java.lang.String getSaleNumber() {
		return (java.lang.String)HiltonUtility.ckNull(this.saleNumber).trim();
    }

    public void setSaleNumber(java.lang.String saleNumber) {
		if (!HiltonUtility.isEmpty(saleNumber) && saleNumber.length() > 20) {
			saleNumber = saleNumber.substring(0, 20);
		}
		this.saleNumber = saleNumber;
    }

    public java.util.Date getSaleDate() {
		return this.saleDate;
    }

    public void setSaleDate(java.util.Date saleDate) {
        this.saleDate = saleDate;
    }

    public java.util.Date getAwardDate() {
		return this.awardDate;
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

    public java.lang.String getDescription() {
		return (java.lang.String)HiltonUtility.ckNull(this.description).trim();
    }

    public void setDescription(java.lang.String description) {
		if (!HiltonUtility.isEmpty(description) && description.length() > 255) {
			description = description.substring(0, 255);
		}
		this.description = description;
    }

    public java.lang.String getContact() {
		return (java.lang.String)HiltonUtility.ckNull(this.contact).trim();
    }

    public void setContact(java.lang.String contact) {
		if (!HiltonUtility.isEmpty(contact) && contact.length() > 15) {
			contact = contact.substring(0, 15);
		}
		this.contact = contact;
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
		return (java.lang.String)HiltonUtility.ckNull(this.language).trim();
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
    }

    public void setLastChgDate(java.util.Date lastChgDate) {
        this.lastChgDate = lastChgDate;
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

    public java.lang.String getAmendment() {
		return (java.lang.String)HiltonUtility.ckNull(this.amendment).trim();
    }

    public void setAmendment(java.lang.String amendment) {
		if (!HiltonUtility.isEmpty(amendment) && amendment.length() > 4) {
			amendment = amendment.substring(0, 4);
		}
		this.amendment = amendment;
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

    public java.lang.String getTimeZone() {
		return (java.lang.String)HiltonUtility.ckNull(this.timeZone).trim();
    }

    public void setTimeZone(java.lang.String timeZone) {
		if (!HiltonUtility.isEmpty(timeZone) && timeZone.length() > 3) {
			timeZone = timeZone.substring(0, 3);
		}
		this.timeZone = timeZone;
    }

    public java.lang.String getCurrency() {
		return (java.lang.String)HiltonUtility.ckNull(this.currency).trim();
    }

    public void setCurrency(java.lang.String currency) {
		if (!HiltonUtility.isEmpty(currency) && currency.length() > 30) {
			currency = currency.substring(0, 30);
		}
		this.currency = currency;
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

    public java.lang.String getHighestBidSource() {
		return (java.lang.String)HiltonUtility.ckNull(this.highestBidSource).trim();
    }

    public void setHighestBidSource(java.lang.String highestBidSource) {
		if (!HiltonUtility.isEmpty(highestBidSource) && highestBidSource.length() > 1) {
			highestBidSource = highestBidSource.substring(0, 1);
		}
		this.highestBidSource = highestBidSource;
    }

    public java.lang.String getHighestBidReq() {
		return (java.lang.String)HiltonUtility.ckNull(this.highestBidReq).trim();
    }

    public void setHighestBidReq(java.lang.String highestBidReq) {
		if (!HiltonUtility.isEmpty(highestBidReq) && highestBidReq.length() > 1) {
			highestBidReq = highestBidReq.substring(0, 1);
		}
		this.highestBidReq = highestBidReq;
    }

    public java.lang.String getHighestDisplay() {
		return (java.lang.String)HiltonUtility.ckNull(this.highestDisplay).trim();
    }

    public void setHighestDisplay(java.lang.String highestDisplay) {
		if (!HiltonUtility.isEmpty(highestDisplay) && highestDisplay.length() > 1) {
			highestDisplay = highestDisplay.substring(0, 1);
		}
		this.highestDisplay = highestDisplay;
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

    public java.lang.String getTaxCode() {
		return (java.lang.String)HiltonUtility.ckNull(this.taxCode).trim();
    }

    public void setTaxCode(java.lang.String taxCode) {
		if (!HiltonUtility.isEmpty(taxCode) && taxCode.length() > 15) {
			taxCode = taxCode.substring(0, 15);
		}
		this.taxCode = taxCode;
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
    
    public java.lang.String getCaIndicateHighest() {
		return (java.lang.String)HiltonUtility.ckNull(this.caIndicateHighest).trim();
    }
    
    public void setCaIndicateHighest(java.lang.String caIndicateHighest) {
		if (!HiltonUtility.isEmpty(caIndicateHighest) && caIndicateHighest.length() > 1) {
			caIndicateHighest = caIndicateHighest.substring(0, 1);
		}
		this.caIndicateHighest = caIndicateHighest;
    }

    public java.lang.String getCaHighestAmount() {
		return (java.lang.String)HiltonUtility.ckNull(this.caHighestAmount).trim();
    }
    
    public void setCaHighestAmount(java.lang.String caHighestAmount) {
		if (!HiltonUtility.isEmpty(caHighestAmount) && caHighestAmount.length() > 1) {
			caHighestAmount = caHighestAmount.substring(0, 1);
		}
		this.caHighestAmount = caHighestAmount;
    }
    
    public java.lang.String getCaWinningBuyer() {
		return (java.lang.String)HiltonUtility.ckNull(this.caWinningBuyer).trim();
    }
    
    public void setCaWinningBuyer(java.lang.String caWinningBuyer) {
		if (!HiltonUtility.isEmpty(caWinningBuyer) && caWinningBuyer.length() > 1) {
			caWinningBuyer = caWinningBuyer.substring(0, 1);
		}
		this.caWinningBuyer = caWinningBuyer;
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
		return (java.lang.String)HiltonUtility.ckNull(this.qaStartTime).trim();
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
		return (java.lang.String)HiltonUtility.ckNull(this.qaEndTime).trim();
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
		return (java.lang.String)HiltonUtility.ckNull(this.bidStartTime).trim();
    }

    public void setBidStartTime(String bidStartTime) {
		if (!HiltonUtility.isEmpty(bidStartTime) && bidStartTime.length() > 8) {
			bidStartTime = bidStartTime.substring(0, 8);
		}
		this.bidStartTime = bidStartTime;
    }

    public String getBidEndTime() {
		return (java.lang.String)HiltonUtility.ckNull(this.bidEndTime).trim();
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
		return (java.lang.String)HiltonUtility.ckNull(this.auctionStartTime).trim();
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
		return (java.lang.String)HiltonUtility.ckNull(this.auctionEndTime).trim();
    }

    public void setAuctionEndTime(String auctionEndTime) {
		if (!HiltonUtility.isEmpty(auctionEndTime) && auctionEndTime.length() > 8) {
			auctionEndTime = auctionEndTime.substring(0, 8);
		}
		this.auctionEndTime = auctionEndTime;
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

	public List getSaleBidList() {
		return this.saleBidList;
	}

	public void setSaleBidList(List saleBidList) {
		this.saleBidList = saleBidList;
	}

	public SaleLine getSaleLine() {
		return this.saleLine;
	}

	public void setSaleLine(SaleLine saleLine) {
		this.saleLine = saleLine;
	}

	public List getSaleBuyerList() {
		return this.saleBuyerList;
	}

	public void setSaleBuyerList(List saleBuyerList) {
		this.saleBuyerList = saleBuyerList;
	}
	
	public String getHighestBuyer() {
		return (java.lang.String)HiltonUtility.ckNull(this.highestBuyer).trim();
	}
	
	public void setHighestBuyer(String highestBuyer) {
		this.highestBuyer = highestBuyer;
	}
	
	public java.math.BigDecimal getHighestBidSubtotal() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.highestBidSubtotal);
	}
	
	public void setHighestBidSubtotal(java.math.BigDecimal highestBidSubtotal) {
		this.highestBidSubtotal = highestBidSubtotal;
	}

    public String toString() {
        return new ToStringBuilder(this)
            .append("icSaleHeader", getIcSaleHeader())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof SaleHeader) ) return false;
        SaleHeader castOther = (SaleHeader) other;
        return new EqualsBuilder()
            .append(this.getIcSaleHeader(), castOther.getIcSaleHeader())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getIcSaleHeader())
            .toHashCode();
    }
}
