package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class InvAlloc implements Serializable {

    /** identifier field */
    private com.tsa.puridiom.entity.InvAllocPK comp_id;

    /** nullable persistent field */
    private java.math.BigDecimal icHeader;

    /** nullable persistent field */
    private String aisle;

    /** nullable persistent field */
    private String locrow;

    /** nullable persistent field */
    private String tier;

    /** nullable persistent field */
    private String bin;

    /** nullable persistent field */
    private java.math.BigDecimal quantity;

    /** nullable persistent field */
    private java.math.BigDecimal icHeaderHistory;

    /** nullable persistent field */
    private java.math.BigDecimal lastQuantity;

    /** nullable persistent field */
    private String lotNumber;

    /** nullable persistent field */
    private java.util.Date itemDate;

    /** nullable persistent field */
    private java.math.BigDecimal icText;
    
    private java.lang.String itemNumber;
    
	private java.lang.String itemLocation;

	/**
	 * getItemLocation
	 * @return
	 */
	public java.lang.String getItemLocation()
	{
		return itemLocation;
	}

	/**
	 * setItemLocation
	 * @param itemLocation
	 */
	public void setItemLocation(java.lang.String itemLocation)
	{
		this.itemLocation = itemLocation;
	}

	/**
	 * getItemNumber
	 * @return
	 */
	public java.lang.String getItemNumber()
	{
		return itemNumber;
	}

	/**
	 * setItemNumber
	 * @param itemNumber
	 */
	public void setItemNumber(java.lang.String itemNumber)
	{
		this.itemNumber = itemNumber;
	}

    /** full constructor */
    public InvAlloc(com.tsa.puridiom.entity.InvAllocPK comp_id, java.math.BigDecimal icHeader, java.lang.String aisle, java.lang.String locrow, java.lang.String tier, java.lang.String bin, java.math.BigDecimal quantity, java.math.BigDecimal icHeaderHistory, java.math.BigDecimal lastQuantity, java.lang.String lotNumber, java.util.Date itemDate, java.math.BigDecimal icText, java.lang.String itemNumber, java.lang.String itemLocation) {
        this.comp_id = comp_id;
        this.icHeader = icHeader;
        this.aisle = aisle;
        this.locrow = locrow;
        this.tier = tier;
        this.bin = bin;
        this.quantity = quantity;
        this.icHeaderHistory = icHeaderHistory;
        this.lastQuantity = lastQuantity;
        this.lotNumber = lotNumber;
        this.itemDate = itemDate;
        this.icText = icText;
        this.itemNumber = itemNumber;
        this.itemLocation = itemLocation;
    }

    /** default constructor */
    public InvAlloc() {
    }

    /** minimal constructor */
    public InvAlloc(com.tsa.puridiom.entity.InvAllocPK comp_id) {
        this.comp_id = comp_id;
    }

    public com.tsa.puridiom.entity.InvAllocPK getComp_id() {
        return this.comp_id;
    }

    public void setComp_id(com.tsa.puridiom.entity.InvAllocPK comp_id) {
        this.comp_id = comp_id;
    }

    public java.math.BigDecimal getIcHeader() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.icHeader);
    }

    public void setIcHeader(java.math.BigDecimal icHeader) {
        this.icHeader = icHeader;
    }

    public java.lang.String getAisle() {
		return (java.lang.String)HiltonUtility.ckNull(this.aisle).trim();
    }

    public void setAisle(java.lang.String aisle) {
		if (!HiltonUtility.isEmpty(aisle) && aisle.length() > 15) {
			aisle = aisle.substring(0, 15);
		}
		this.aisle = aisle;
    }

    public java.lang.String getLocrow() {
		return (java.lang.String)HiltonUtility.ckNull(this.locrow).trim();
    }

    public void setLocrow(java.lang.String locrow) {
		if (!HiltonUtility.isEmpty(locrow) && locrow.length() > 15) {
			locrow = locrow.substring(0, 15);
		}
		this.locrow = locrow;
    }

    public java.lang.String getTier() {
		return (java.lang.String)HiltonUtility.ckNull(this.tier).trim();
    }

    public void setTier(java.lang.String tier) {
		if (!HiltonUtility.isEmpty(tier) && tier.length() > 15) {
			tier = tier.substring(0, 15);
		}
		this.tier = tier;
    }

    public java.lang.String getBin() {
		return (java.lang.String)HiltonUtility.ckNull(this.bin).trim();
    }

    public void setBin(java.lang.String bin) {
		if (!HiltonUtility.isEmpty(bin) && bin.length() > 15) {
			bin = bin.substring(0, 15);
		}
		this.bin = bin;
    }

    public java.math.BigDecimal getQuantity() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.quantity);
    }

    public void setQuantity(java.math.BigDecimal quantity) {
        this.quantity = quantity;
    }

    public java.math.BigDecimal getIcHeaderHistory() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.icHeaderHistory);
    }

    public void setIcHeaderHistory(java.math.BigDecimal icHeaderHistory) {
        this.icHeaderHistory = icHeaderHistory;
    }

    public java.math.BigDecimal getLastQuantity() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.lastQuantity);
    }

    public void setLastQuantity(java.math.BigDecimal lastQuantity) {
        this.lastQuantity = lastQuantity;
    }

    public java.lang.String getLotNumber() {
		return (java.lang.String)HiltonUtility.ckNull(this.lotNumber).trim();
    }

    public void setLotNumber(java.lang.String lotNumber) {
		if (!HiltonUtility.isEmpty(lotNumber) && lotNumber.length() > 15) {
			lotNumber = lotNumber.substring(0, 15);
		}
		this.lotNumber = lotNumber;
    }

    public java.util.Date getItemDate() {
		return this.itemDate;
//		return HiltonUtility.ckNull(this.itemDate);
//		return (java.sql.Date)HiltonUtility.ckNull(this.itemDate);
    }

    public void setItemDate(java.util.Date itemDate) {
        this.itemDate = itemDate;
    }

    public java.math.BigDecimal getIcText() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.icText);
    }

    public void setIcText(java.math.BigDecimal icText) {
        this.icText = icText;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("comp_id", getComp_id())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof InvAlloc) ) return false;
        InvAlloc castOther = (InvAlloc) other;
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
