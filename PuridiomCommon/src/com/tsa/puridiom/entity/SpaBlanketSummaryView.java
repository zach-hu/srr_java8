package com.tsa.puridiom.entity;


import java.io.Serializable;
import java.math.BigDecimal;


/** @author Hibernate CodeGenerator */
public class SpaBlanketSummaryView implements Serializable {

	/** identifier field */
    private BigDecimal icPoHeader;

    /** nullable persistent field */
    private String vendorName;

    /** nullable persistent field */
    private String vendorId;

    /** nullable persistent field */
    private String poNumber;

    /** nullable persistent field */
    private String poType;

    /** nullable persistent field */
    private java.util.Date effectiveDate;

    /** nullable persistent field */
    private java.util.Date expirationDate;

    /** nullable persistent field */
    private String status;

    /** nullable persistent field */
    private String buyerCode;

    /** nullable persistent field */
    private String udf1Code;

    /** nullable persistent field */
    private java.math.BigDecimal total;

    /** nullable persistent field */
    private String awardedDate;

    /** nullable persistent field */
    private java.math.BigDecimal releaseTotal;

    /** nullable persistent field */
    private java.math.BigDecimal totalNumberReleases;

    /** nullable persistent field */
    private java.math.BigDecimal blanketLimit;

    /** nullable persistent field */
    private java.math.BigDecimal releaseLimit;

    /** nullable persistent field */
    private String internalComments;


    /** full constructor */
    public SpaBlanketSummaryView(java.math.BigDecimal icPoHeader, java.lang.String vendorName, java.lang.String vendorId, java.lang.String poNumber, java.lang.String poType, java.util.Date effectiveDate, java.util.Date expirationDate, java.lang.String status, java.lang.String buyerCode, java.lang.String udf1Code, java.math.BigDecimal total, java.lang.String awardedDate, java.math.BigDecimal releaseTotal, java.math.BigDecimal totalNumberReleases, java.math.BigDecimal blanketLimit, java.math.BigDecimal releaseLimit, java.lang.String internalComments){
        this.icPoHeader = icPoHeader;
        this.vendorName = vendorName;
        this.vendorId = vendorId;
        this.poNumber = poNumber;
        this.poType = poType;
        this.effectiveDate = effectiveDate;
        this.expirationDate = expirationDate;
        this.status = status;
        this.buyerCode = buyerCode;
        this.udf1Code = udf1Code;
        this.total = total;
        this.awardedDate = awardedDate;
        this.releaseTotal = releaseTotal;
        this.totalNumberReleases = totalNumberReleases;
        this.blanketLimit = blanketLimit;
        this.releaseLimit = releaseLimit;
        this.internalComments = internalComments;
    }

    /** default constructor */
    public SpaBlanketSummaryView(){
    }

    public BigDecimal getIcPoHeader() {
		return icPoHeader;
	}

	public void setIcPoHeader(BigDecimal icPoHeader) {
		this.icPoHeader = icPoHeader;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public String getVendorId() {
		return vendorId;
	}

	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}

	public String getPoNumber() {
		return poNumber;
	}

	public void setPoNumber(String poNumber) {
		this.poNumber = poNumber;
	}

	public String getPoType() {
		return poType;
	}

	public void setPoType(String poType) {
		this.poType = poType;
	}

	public java.util.Date getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(java.util.Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public java.util.Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(java.util.Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getBuyerCode() {
		return buyerCode;
	}

	public void setBuyerCode(String buyerCode) {
		this.buyerCode = buyerCode;
	}

	public String getUdf1Code() {
		return udf1Code;
	}

	public void setUdf1Code(String udf1Code) {
		this.udf1Code = udf1Code;
	}

	public java.math.BigDecimal getTotal() {
		return total;
	}

	public void setTotal(java.math.BigDecimal total) {
		this.total = total;
	}

	public String getAwardedDate() {
		return awardedDate;
	}

	public void setAwardedDate(String awardedDate) {
		this.awardedDate = awardedDate;
	}

	public java.math.BigDecimal getReleaseTotal() {
		return releaseTotal;
	}

	public void setReleaseTotal(java.math.BigDecimal releaseTotal) {
		this.releaseTotal = releaseTotal;
	}

	public java.math.BigDecimal getTotalNumberReleases() {
		return totalNumberReleases;
	}

	public void setTotalNumberReleases(java.math.BigDecimal totalNumberReleases) {
		this.totalNumberReleases = totalNumberReleases;
	}

	public java.math.BigDecimal getBlanketLimit() {
		return blanketLimit;
	}

	public void setBlanketLimit(java.math.BigDecimal blanketLimit) {
		this.blanketLimit = blanketLimit;
	}

	public java.math.BigDecimal getReleaseLimit() {
		return releaseLimit;
	}

	public void setReleaseLimit(java.math.BigDecimal releaseLimit) {
		this.releaseLimit = releaseLimit;
	}

	public String getInternalComments() {
		return internalComments;
	}

	public void setInternalComments(String internalComments) {
		this.internalComments = internalComments;
	}
}