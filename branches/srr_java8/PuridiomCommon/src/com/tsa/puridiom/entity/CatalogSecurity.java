package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;

/** @author Hibernate CodeGenerator */
public class CatalogSecurity implements Serializable {

	/** identifier field */
    private java.math.BigDecimal icHeader;

	/** identifier field */
    private String catalogId;

    /** nullable persistent field */
    private String itemNumber;

    /** nullable persistent field */
    private String accessType;

    /** nullable persistent field */
    private String accessId;

    /** nullable persistent field */
    private String accessDescription;

    /** nullable persistent field */
    private String owner;

    /** nullable persistent field */
    private java.util.Date dateEntered;

    /** nullable persistent field */
    private java.util.Date dateChanged;

    /** nullable persistent field */
    private String lastChangedBy;

    /** full constructor */
    public CatalogSecurity(java.math.BigDecimal icHeader,java.lang.String catalogId, java.lang.String itemNumber, java.lang.String accessType, java.lang.String accessId, java.lang.String accessDescription, java.lang.String owner, java.util.Date dateEntered, java.util.Date dateChanged, java.lang.String lastChangedBy) {
    	this.icHeader = icHeader;
    	this.catalogId = catalogId;
        this.itemNumber = itemNumber;
        this.accessType = accessType;
        this.accessId = accessId;
        this.accessDescription = accessDescription;
        this.owner = owner;
        this.dateEntered = dateEntered;
        this.dateChanged = dateChanged;
        this.lastChangedBy = lastChangedBy;
    }

    /** default constructor */
    public CatalogSecurity() {
    }

    /** minimal constructor */
    public CatalogSecurity(java.math.BigDecimal icHeader, java.lang.String catalogId,java.lang.String itemNumber) {
    	this.icHeader = icHeader;
    	this.catalogId = catalogId;
        this.itemNumber = itemNumber;
    }

    public java.math.BigDecimal getIcHeader() {
    	return (java.math.BigDecimal)HiltonUtility.ckNull(this.icHeader);
	}

	public void setIcHeader(java.math.BigDecimal icHeader) {
		this.icHeader = icHeader;
	}

    public java.lang.String getCatalogId() {
		return (java.lang.String)HiltonUtility.ckNull(this.catalogId).trim();
    }

    public void setCatalogId(java.lang.String catalogId) {
		this.catalogId = catalogId;
    }

    public java.lang.String getItemNumber() {
		return (java.lang.String)HiltonUtility.ckNull(this.itemNumber).trim();
    }

    public void setItemNumber(java.lang.String ItemNumber) {
		this.itemNumber = ItemNumber;
    }

    public java.lang.String getAccessType() {
		return (java.lang.String)HiltonUtility.ckNull(this.accessType).trim();
    }

    public void setAccessType(java.lang.String accessType) {
    	this.accessType = accessType;
    }

    public java.lang.String getAccessId() {
		return (java.lang.String)HiltonUtility.ckNull(this.accessId).trim();
    }

    public void setAccessId(java.lang.String accessId) {
    	if (!HiltonUtility.isEmpty(accessId) && accessId.length() > 15) {
    		accessId = accessId.substring(0, 15);
    	}
    	this.accessId = accessId;
    }

    public java.lang.String getAccessDescription() {
		return (java.lang.String)HiltonUtility.ckNull(this.accessDescription).trim();
    }

    public void setAccessDescription(java.lang.String accessDescription) {
    	if (!HiltonUtility.isEmpty(accessDescription) && accessDescription.length() > 40) {
    		accessDescription = accessDescription.substring(0, 40);
    	}
    	this.accessDescription = accessDescription;
    }

    public java.lang.String getOwner() {
		return (java.lang.String)HiltonUtility.ckNull(this.owner).trim();
    }

    public void setOwner(java.lang.String owner) {
		this.owner = owner;
    }

    public java.util.Date getDateEntered() {
		return this.dateEntered;
    }

    public void setDateEntered(java.util.Date dateEntered) {
        this.dateEntered = dateEntered;
    }

    public java.util.Date getDateChanged() {
		return this.dateChanged;
    }

    public void setDateChanged(java.util.Date dateChanged) {
        this.dateChanged = dateChanged;
    }

    public java.lang.String getLastChangedBy() {
		return (java.lang.String)HiltonUtility.ckNull(this.lastChangedBy).trim();
    }

    public void setLastChangedBy(java.lang.String lastChangedBy) {
		this.lastChangedBy = lastChangedBy;
    }
}
