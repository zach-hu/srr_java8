package com.tsa.puridiom.entity;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.tsa.puridiom.common.utility.HiltonUtility;

/** @author Hibernate CodeGenerator */
public class InvProperty implements Serializable {

    /** identifier field */
    private java.math.BigDecimal icProperty;

    /** nullable persistent field */
    private String propertyType;

    /** nullable persistent field */
    private String tagNumber;

    /** nullable persistent field */
    private java.math.BigDecimal icRc;

    /** nullable persistent field */
    private String itemNumber;

    /** nullable persistent field */
    private String serialNumber;

    /** nullable persistent field */
    private String poNumber;

    /** nullable persistent field */
    private String contract;

    /** nullable persistent field */
    private String receiptNumber;

    /** nullable persistent field */
    private String shipNumber;

    /** nullable persistent field */
    private String remarks;

    /** nullable persistent field */
    private java.util.Date dateIn;

    /** nullable persistent field */
    private java.util.Date dateOut;

    /** nullable persistent field */
    private String status;

    /** nullable persistent field */
    private String owner;

    private String cblOutNumber ;
    private String armyNumber ;

    /** nullable persistent field */
    private java.math.BigDecimal icRecLine ;
    private java.math.BigDecimal icPoLine ;
    private String assetNumber ;
    /** nullable persistent field */
    private String source;

    /** nullable persistent field */
    private java.math.BigDecimal icDsbLine;

    /** full constructor */
    public InvProperty(java.math.BigDecimal icProperty, java.lang.String propertyType, java.lang.String tagNumber, java.math.BigDecimal icRc, java.lang.String itemNumber, java.lang.String serialNumber, java.lang.String poNumber, java.lang.String contract, java.lang.String receiptNumber, java.lang.String shipNumber, java.lang.String remarks, java.util.Date dateIn, java.util.Date dateOut, java.lang.String status, java.lang.String owner, java.lang.String cblOutNumber, java.lang.String armyNumber, java.math.BigDecimal icRecLine, java.math.BigDecimal icPoLine, java.lang.String assetNumber, java.math.BigDecimal icDsbLine) {
        this.icProperty = icProperty;
        this.propertyType = propertyType;
        this.tagNumber = tagNumber;
        this.icRc = icRc;
        this.itemNumber = itemNumber;
        this.serialNumber = serialNumber;
        this.poNumber = poNumber;
        this.contract = contract;
        this.receiptNumber = receiptNumber;
        this.shipNumber = shipNumber;
        this.remarks = remarks;
        this.dateIn = dateIn;
        this.dateOut = dateOut;
        this.status = status;
        this.owner = owner;
        this.cblOutNumber = cblOutNumber ;
        this.armyNumber = armyNumber ;
        this.icRecLine = icRecLine ;
        this.icPoLine = icPoLine ;
        this.assetNumber = assetNumber ;
        this.icDsbLine = icDsbLine ;
    }

    /** default constructor */
    public InvProperty() {
    }

    /** minimal constructor */
    public InvProperty(java.math.BigDecimal icProperty) {
        this.icProperty = icProperty;
    }

    public java.math.BigDecimal getIcProperty() {
        return this.icProperty;
    }

    public void setIcProperty(java.math.BigDecimal icProperty) {
        this.icProperty = icProperty;
    }

    public java.lang.String getPropertyType() {
        return this.propertyType;
    }

    public void setPropertyType(java.lang.String propertyType) {
        this.propertyType = propertyType;
    }

    public java.lang.String getTagNumber() {
        return this.tagNumber;
    }

    public void setTagNumber(java.lang.String tagNumber) {
        this.tagNumber = tagNumber;
    }

    public java.math.BigDecimal getIcRc() {
        return this.icRc;
    }

    public void setIcRc(java.math.BigDecimal icRc) {
        this.icRc = icRc;
    }

    public java.lang.String getItemNumber() {
        return this.itemNumber;
    }

    public void setItemNumber(java.lang.String itemNumber) {
        this.itemNumber = itemNumber;
    }

    public java.lang.String getSerialNumber() {
        return this.serialNumber;
    }

    public void setSerialNumber(java.lang.String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public java.lang.String getPoNumber() {
        return this.poNumber;
    }

    public void setPoNumber(java.lang.String poNumber) {
        this.poNumber = poNumber;
    }

    public java.lang.String getContract() {
        return this.contract;
    }

    public void setContract(java.lang.String contract) {
        this.contract = contract;
    }

    public java.lang.String getReceiptNumber() {
        return this.receiptNumber;
    }

    public void setReceiptNumber(java.lang.String receiptNumber) {
        this.receiptNumber = receiptNumber;
    }

    public java.lang.String getShipNumber() {
        return this.shipNumber;
    }

    public void setShipNumber(java.lang.String shipNumber) {
        this.shipNumber = shipNumber;
    }

    public java.lang.String getRemarks() {
        return this.remarks;
    }

    public void setRemarks(java.lang.String remarks) {
        this.remarks = remarks;
    }

    public java.util.Date getDateIn() {
        return this.dateIn;
    }

    public void setDateIn(java.util.Date dateIn) {
        this.dateIn = dateIn;
    }

    public java.util.Date getDateOut() {
        return this.dateOut;
    }

    public void setDateOut(java.util.Date dateOut) {
        this.dateOut = dateOut;
    }

    public java.lang.String getStatus() {
        return this.status;
    }

    public void setStatus(java.lang.String status) {
        this.status = status;
    }

    public java.lang.String getOwner() {
        return this.owner;
    }

    public void setOwner(java.lang.String owner) {
        this.owner = owner;
    }

    public java.lang.String getCblOutNumber() {
        return this.cblOutNumber;
    }

    public void setCblOutNumber(java.lang.String cblOutNumber) {
        this.cblOutNumber = cblOutNumber;
    }

    public java.lang.String getArmyNumber() {
        return this.armyNumber;
    }
    public void setArmyNumber(java.lang.String armyNumber) {
        this.armyNumber = armyNumber;
    }

    public java.math.BigDecimal getIcRecLine() {
        return this.icRecLine ;
    }

    public void setIcRecLine(java.math.BigDecimal icRecLine) {
        this.icRecLine = icRecLine ;
    }

	public java.math.BigDecimal getIcPoLine() {
		return icPoLine;
	}

	public void setIcPoLine(java.math.BigDecimal icPoLine) {
		this.icPoLine = icPoLine;
	}

	public String getAssetNumber() {
		return assetNumber;
	}

	public void setAssetNumber(String assetNumber) {
		this.assetNumber = assetNumber;
	}

    public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public java.math.BigDecimal getIcDsbLine() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.icDsbLine);
	}

	public void setIcDsbLine(java.math.BigDecimal icDsbLine) {
		this.icDsbLine = icDsbLine;
	}

	public String toString() {
        return new ToStringBuilder(this)
            .append("icProperty", getIcProperty())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof InvProperty) ) return false;
        InvProperty castOther = (InvProperty) other;
        return new EqualsBuilder()
            .append(this.getIcProperty(), castOther.getIcProperty())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getIcProperty())
            .toHashCode();
    }

}
