package com.tsa.puridiom.entity;

import java.io.Serializable;
import java.util.Set;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsagate.foundation.utility.Utility;

/** @author Hibernate CodeGenerator */
public class Catalog implements Serializable {

    /** identifier field */
    private String catalogId;

    /** nullable persistent field */
    private String title;

    /** nullable persistent field */
    private java.util.Date dateEntered;

    /** nullable persistent field */
    private java.util.Date dateExpires;

    /** nullable persistent field */
    private String status;

    /** nullable persistent field */
    private String owner;

    /** nullable persistent field */
    private String source;

    /** nullable persistent field */
    private String vendorId;

    /** nullable persistent field */
    private java.math.BigDecimal icPoHeader;

    /** nullable persistent field */
    private String poNumber;

    /** nullable persistent field */
    private java.math.BigDecimal revisionNumber;

    /** nullable persistent field */
    private String allowReleases;

    /** nullable persistent field */
    private String webCatalog;

    /** nullable persistent field */
    private String externalCatalog;

    /** nullable persistent field */
    private String requestXml;

    /** nullable persistent field */
    private String punchoutUrl;

    /** nullable persistent field */
    private String receiptRequired;

    /** nullable persistent field */
    private String ordersOnly;

    /** persistent field */
    private Set catalogComponents;

    /** persistent field */
    private Set catalogItems;

    /** persistent field */
    private Set catalogPriceBrks;

    /** nullable persistent field */
    private java.math.BigDecimal icAccount;

    /** nullable persistent field */
    private String consolidateReleases;

    /** nullable persistent field */
    private String currencyCode;

    /** nullable persistent field */
    private java.math.BigDecimal icHeaderComment;

    /** nullable persistent field */
    private java.util.Date lastUpdatedDate;

    /** nullable persistent field */
    private String lastUpdatedBy;

    /** nullable persistent field */
    private java.math.BigDecimal icPunchout;

    /** full constructor */
    public Catalog(java.lang.String catalogId, java.lang.String title, java.util.Date dateEntered, java.util.Date dateExpires, java.lang.String status, java.lang.String owner, java.lang.String source, java.lang.String vendorId, java.math.BigDecimal icPoHeader, java.lang.String poNumber, java.math.BigDecimal revisionNumber, java.lang.String allowReleases, java.lang.String webCatalog, java.lang.String externalCatalog, java.lang.String punchoutUrl, java.lang.String requestXml, Set catalogComponents, Set catalogItems, Set catalogPriceBrks, java.math.BigDecimal icAccount, java.lang.String receiptRequired, java.lang.String ordersOnly, java.lang.String consolidateReleases, java.lang.String currencyCode, java.util.Date lastUpdatedDate, java.lang.String lastUpdatedBy, java.math.BigDecimal icPunchout) {
        this.catalogId = catalogId;
        this.title = title;
        this.dateEntered = dateEntered;
        this.dateExpires = dateExpires;
        this.status = status;
        this.owner = owner;
        this.source = source;
        this.vendorId = vendorId;
        this.icPoHeader = icPoHeader;
        this.poNumber = poNumber;
        this.revisionNumber = revisionNumber;
        this.allowReleases = allowReleases;
        this.webCatalog = webCatalog;
        this.externalCatalog = externalCatalog;
        this.requestXml = requestXml;
        this.punchoutUrl = punchoutUrl;
        this.catalogComponents = catalogComponents;
        this.catalogItems = catalogItems;
        this.catalogPriceBrks = catalogPriceBrks;
        this.icAccount = icAccount;
        this.receiptRequired = receiptRequired;
        this.ordersOnly = ordersOnly;
        this.consolidateReleases = consolidateReleases;
        this.currencyCode = currencyCode;
        this.lastUpdatedDate = lastUpdatedDate;
        this.lastUpdatedBy = lastUpdatedBy;
        this.icPunchout	= icPunchout;
    }

    /** default constructor */
    public Catalog() {
    }

    /** minimal constructor */
    public Catalog(java.lang.String catalogId, Set catalogComponents, Set catalogItems, Set catalogPriceBrks) {
        this.catalogId = catalogId;
        this.catalogComponents = catalogComponents;
        this.catalogItems = catalogItems;
        this.catalogPriceBrks = catalogPriceBrks;
    }

    public java.lang.String getCatalogId() {
		return (java.lang.String)HiltonUtility.ckNull(this.catalogId).trim();
    }

    public void setCatalogId(java.lang.String catalogId) {
		if (!HiltonUtility.isEmpty(catalogId) && catalogId.length() > 15) {
			catalogId = catalogId.substring(0, 15);
		}
		this.catalogId = catalogId;
    }

    public java.lang.String getTitle() {
		return (java.lang.String)HiltonUtility.ckNull(this.title).trim();
    }

    public void setTitle(java.lang.String title) {
		if (!HiltonUtility.isEmpty(title) && title.length() > 60) {
			title = title.substring(0, 60);
		}
		this.title = title;
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

    public java.lang.String getSource() {
		return (java.lang.String)HiltonUtility.ckNull(this.source).trim();
    }

    public void setSource(java.lang.String source) {
		if (!HiltonUtility.isEmpty(source) && source.length() > 20) {
			source = source.substring(0, 20);
		}
		this.source = source;
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

    public java.math.BigDecimal getIcPoHeader() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.icPoHeader);
    }

    public void setIcPoHeader(java.math.BigDecimal icPoHeader) {
        this.icPoHeader = icPoHeader;
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

    public java.math.BigDecimal getRevisionNumber() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.revisionNumber);
    }

    public void setRevisionNumber(java.math.BigDecimal revisionNumber) {
        this.revisionNumber = revisionNumber;
    }

    public java.lang.String getAllowReleases() {
		return (java.lang.String)HiltonUtility.ckNull(this.allowReleases).trim();
    }

    public void setAllowReleases(java.lang.String allowReleases) {
		if (!HiltonUtility.isEmpty(allowReleases) && allowReleases.length() > 1) {
			allowReleases = allowReleases.substring(0, 1);
		}
		this.allowReleases = allowReleases;
    }

    public java.lang.String getWebCatalog() {
		return (java.lang.String)HiltonUtility.ckNull(this.webCatalog).trim();
    }

    public void setWebCatalog(java.lang.String webCatalog) {
		if (!HiltonUtility.isEmpty(webCatalog) && webCatalog.length() > 15) {
			webCatalog = webCatalog.substring(0, 15);
		}
		this.webCatalog = webCatalog;
    }

    public java.lang.String getExternalCatalog() {
		return (java.lang.String)HiltonUtility.ckNull(this.externalCatalog).trim();
    }

    public void setExternalCatalog(java.lang.String externalCatalog) {
		if (!HiltonUtility.isEmpty(externalCatalog) && externalCatalog.length() > 1) {
			externalCatalog = externalCatalog.substring(0, 1);
		}
		this.externalCatalog = externalCatalog;
    }

    public java.lang.String getRequestXml() {
		return (java.lang.String)HiltonUtility.ckNull(this.requestXml).trim();
    }

    public void setRequestXml(java.lang.String requestXml) {
		if (!HiltonUtility.isEmpty(requestXml) && requestXml.length() > 30) {
			requestXml = requestXml.substring(0, 30);
		}
		this.requestXml = requestXml;
    }

    public java.lang.String getPunchoutUrl() {
		return (java.lang.String)Utility.ckNull(this.punchoutUrl).trim();
    }

    public void setPunchoutUrl(java.lang.String punchoutUrl) {
		if (!HiltonUtility.isEmpty(punchoutUrl) && punchoutUrl.length() > 255) {
			punchoutUrl = punchoutUrl.substring(0, 255);
		}
		this.punchoutUrl = punchoutUrl;
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

    public java.lang.String getOrdersOnly() {
		return (java.lang.String)HiltonUtility.ckNull(this.ordersOnly).trim();
    }

    public void setOrdersOnly(java.lang.String ordersOnly) {
		if (!HiltonUtility.isEmpty(ordersOnly) && ordersOnly.length() > 1) {
			ordersOnly = ordersOnly.substring(0, 1);
		}
		this.ordersOnly = ordersOnly;
    }

    public java.util.Set getCatalogComponents() {
        return this.catalogComponents;
    }

    public void setCatalogComponents(java.util.Set catalogComponents) {
        this.catalogComponents = catalogComponents;
    }

    public java.util.Set getCatalogItems() {
        return this.catalogItems;
    }

    public void setCatalogItems(java.util.Set catalogItems) {
        this.catalogItems = catalogItems;
    }

    public java.util.Set getCatalogPriceBrks() {
        return this.catalogPriceBrks;
    }

    public void setCatalogPriceBrks(java.util.Set catalogPriceBrks) {
        this.catalogPriceBrks = catalogPriceBrks;
    }

    public java.math.BigDecimal getIcAccount() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.icAccount);
    }

    public void setIcAccount(java.math.BigDecimal icAccount) {
        this.icAccount = icAccount;
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

    /**
     * @return the icHeaderComment
     */
    public java.math.BigDecimal getIcHeaderComment()
    {
    	return (java.math.BigDecimal) HiltonUtility.ckNull(this.icHeaderComment);
    }

    /**
     * @param icHeaderComment the icHeaderComment to set
     */
    public void setIcHeaderComment(java.math.BigDecimal icHeaderComment)
    {
    	this.icHeaderComment = icHeaderComment;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("catalogId", getCatalogId())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof Catalog) ) return false;
        Catalog castOther = (Catalog) other;
        return new EqualsBuilder()
            .append(this.getCatalogId(), castOther.getCatalogId())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getCatalogId())
            .toHashCode();
    }

	public String getConsolidateReleases() {
		if(HiltonUtility.isEmpty(this.consolidateReleases))
		{
			return "N";
		}
		return (java.lang.String)HiltonUtility.ckNull(this.consolidateReleases).trim();
	}

	public void setConsolidateReleases(String consolidateReleases) {
		if (!HiltonUtility.isEmpty(consolidateReleases) && consolidateReleases.length() > 1) {
			consolidateReleases = consolidateReleases.substring(0, 1);
		}
		this.consolidateReleases = consolidateReleases;
	}

	public java.util.Date getLastUpdatedDate() {
		return this.lastUpdatedDate;
	}

	public void setLastUpdatedDate(java.util.Date lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}

	public java.lang.String getLastUpdatedBy() {
		return (java.lang.String) HiltonUtility.ckNull(this.lastUpdatedBy)
				.trim();
	}

	public void setLastUpdatedBy(java.lang.String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

    public java.math.BigDecimal getIcPunchout()
    {
    	return (java.math.BigDecimal) HiltonUtility.ckNull(this.icPunchout);
    }

    public void setIcPunchout(java.math.BigDecimal icPunchout)
    {
    	this.icPunchout = icPunchout;
    }

}
