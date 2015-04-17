package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class AssetCost implements Serializable {

	 /** identifier field */
    private com.tsa.puridiom.entity.AssetCostPK comp_id;

    /** nullable persistent field */
    private java.math.BigDecimal amount;

    /** nullable persistent field */
    private String extendLifeFlag;

    /** nullable persistent field */
    private java.util.Date dateEntered;

    /** nullable persistent field */
    private java.util.Date dateReceived;

    /** nullable persistent field */
    private String poNumber;

    /** nullable persistent field */
    private String poVendor;

    /** nullable persistent field */
    private String description;

    /** nullable persistent field */
    private String lastChgBy;

    /** nullable persistent field */
    private java.util.Date dateChanged;

    /** full constructor */
    public AssetCost(com.tsa.puridiom.entity.AssetCostPK comp_Id, java.math.BigDecimal amount, java.lang.String extendLifeFlag, java.util.Date dateEntered, java.util.Date dateReceived, java.lang.String poNumber, java.lang.String poVendor, java.lang.String description, java.lang.String lastChgBy, java.util.Date dateChanged) {
        this.comp_id = comp_Id;
        this.amount = amount;
        this.extendLifeFlag = extendLifeFlag;
        this.dateEntered = dateEntered;
        this.dateReceived = dateReceived;
        this.poNumber = poNumber;
        this.poVendor = poVendor;
        this.description = description;
        this.lastChgBy = lastChgBy;
        this.dateChanged = dateChanged;
    }

    /** default constructor */
    public AssetCost() {
    }

    /** minimal constructor */
    public AssetCost(com.tsa.puridiom.entity.AssetCostPK comp_Id) {
    	this.comp_id = comp_Id;
    }

    public java.math.BigDecimal getAmount() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.amount);
    }

    public void setAmount(java.math.BigDecimal amount) {
        this.amount = amount;
    }

    public java.lang.String getExtendLifeFlag() {
		return (java.lang.String)HiltonUtility.ckNull(this.extendLifeFlag).trim();
    }

    public void setExtendLifeFlag(java.lang.String extendLifeFlag) {
		if (!HiltonUtility.isEmpty(extendLifeFlag) && extendLifeFlag.length() > 1) {
			extendLifeFlag = extendLifeFlag.substring(0, 1);
		}
		this.extendLifeFlag = extendLifeFlag;
    }

    public java.util.Date getDateEntered() {
        return this.dateEntered;
    }

    public void setDateEntered(java.util.Date dateEntered) {
        this.dateEntered = dateEntered;
    }

    public java.util.Date getDateReceived() {
        return this.dateReceived;
    }

    public void setDateReceived(java.util.Date dateReceived) {
        this.dateReceived = dateReceived;
    }

    public java.lang.String getPoNumber() {
		return (java.lang.String)HiltonUtility.ckNull(this.poNumber).trim();
    }

    public void setPoNumber(java.lang.String poNumber) {
		if (!HiltonUtility.isEmpty(poNumber) && poNumber.length() > 30) {
			poNumber = poNumber.substring(0, 30);
		}
		this.poNumber = poNumber;
    }

    public java.lang.String getPoVendor() {
		return (java.lang.String)HiltonUtility.ckNull(this.poVendor).trim();
    }

    public void setPoVendor(java.lang.String poVendor) {
		if (!HiltonUtility.isEmpty(poVendor) && poVendor.length() > 40) {
			poVendor = poVendor.substring(0, 40);
		}
		this.poVendor = poVendor;
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

    public java.util.Date getDateChanged() {
        return this.dateChanged;
    }

    public void setDateChanged(java.util.Date dateChanged) {
        this.dateChanged = dateChanged;
    }

    public java.lang.String getLastChgBy() {
		return (java.lang.String)HiltonUtility.ckNull(this.lastChgBy).trim();
    }

    public void setLastChgBy(java.lang.String lastChgBy) {
		if (!HiltonUtility.isEmpty(lastChgBy) && lastChgBy.length() > 25) {
			lastChgBy = lastChgBy.substring(0, 25);
		}
		this.lastChgBy = lastChgBy;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("comp_id", getComp_id())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof AssetCost) ) return false;
        AssetCost castOther = (AssetCost) other;
        return new EqualsBuilder()
            .append(this.getComp_id(), castOther.getComp_id())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getComp_id())
            .toHashCode();
    }
	public com.tsa.puridiom.entity.AssetCostPK getComp_id() {
		return comp_id;
	}

	public void setComp_id(com.tsa.puridiom.entity.AssetCostPK comp_id) {
		this.comp_id = comp_id;
	}

}
