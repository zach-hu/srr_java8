package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class InventoryDisbursementView implements Serializable {

	/** identifier field */
	private java.math.BigDecimal icDsbHeader;

	/** nullable persistent field */
    private String commodityCode;

    /** nullable persistent field */
	private java.util.Date disbDate;

	/** nullable persistent field */
    private java.math.BigDecimal quantity;

    /** nullable persistent field */
    private java.math.BigDecimal unitPrice;

    /** nullable persistent field */
    private String description;

    /** nullable persistent field */
    private String umCode;

    /** nullable persistent field */
    private String departmentCode;

    /** nullable persistent field */
    private String itemNumber;

    /** nullable persistent field */
	private String disbNumber;

	/** nullable persistent field */
	private String itemLocationHeader;

	/** nullable persistent field */
	private String requisitionerCode;

	/** nullable persistent field */
	private String requisitionNumber;

	/** nullable persistent field */
	private String shipToCode;

	/** nullable persistent field */
	private String itemLocationLine;

    /** full constructor */
	public InventoryDisbursementView(java.math.BigDecimal icDsbHeader, String commodityCode, java.util.Date disbDate, java.math.BigDecimal quantity, java.math.BigDecimal unitPrice, String description, String umCode, String departmentCode, String itemNumber, String disbNumber, String itemLocationHeader, String requisitionerCode, String requisitionNumber, String shipToCode, String itemLocationLine) {
		this.setIcDsbHeader(icDsbHeader);
		this.setCommodityCode(commodityCode);
		this.setDisbDate(disbDate);
		this.setQuantity(quantity);
		this.setUnitPrice(unitPrice);		
		this.setDescription(description);
        this.setUmCode(umCode);
        this.setDepartmentCode(departmentCode);        
        this.setItemNumber(itemNumber);
        this.setDisbNumber(disbNumber);
        this.setItemLocationHeader(itemLocationHeader);
        this.setRequisitionerCode(requisitionerCode);
		this.setRequisitionNumber(requisitionNumber);		
		this.setShipToCode(shipToCode);        
		this.setItemLocationLine(itemLocationLine);
    }

    /** default constructor */
    public InventoryDisbursementView() {
    }

	/**
	 * @param icDsbHeader the icDsbHeader to set
	 */
	public void setIcDsbHeader(java.math.BigDecimal icDsbHeader) {
		this.icDsbHeader = icDsbHeader;
	}

	/**
	 * @return the icDsbHeader
	 */
	public java.math.BigDecimal getIcDsbHeader() {
		return icDsbHeader;
	}

	/**
	 * @param commodityCode the commodityCode to set
	 */
	public void setCommodityCode(String commodityCode) {
		this.commodityCode = commodityCode;
	}

	/**
	 * @return the commodityCode
	 */
	public String getCommodityCode() {
		return commodityCode;
	}

	/**
	 * @param disbDate the disbDate to set
	 */
	public void setDisbDate(java.util.Date disbDate) {
		this.disbDate = disbDate;
	}

	/**
	 * @return the disbDate
	 */
	public java.util.Date getDisbDate() {
		return disbDate;
	}

	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(java.math.BigDecimal quantity) {
		this.quantity = quantity;
	}

	/**
	 * @return the quantity
	 */
	public java.math.BigDecimal getQuantity() {
		return quantity;
	}

	/**
	 * @param unitPrice the unitPrice to set
	 */
	public void setUnitPrice(java.math.BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	/**
	 * @return the unitPrice
	 */
	public java.math.BigDecimal getUnitPrice() {
		return unitPrice;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param umCode the umCode to set
	 */
	public void setUmCode(String umCode) {
		this.umCode = umCode;
	}

	/**
	 * @return the umCode
	 */
	public String getUmCode() {
		return umCode;
	}

	/**
	 * @param departmentCode the departmentCode to set
	 */
	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}

	/**
	 * @return the departmentCode
	 */
	public String getDepartmentCode() {
		return departmentCode;
	}

	/**
	 * @param itemNumber the itemNumber to set
	 */
	public void setItemNumber(String itemNumber) {
		this.itemNumber = itemNumber;
	}

	/**
	 * @return the itemNumber
	 */
	public String getItemNumber() {
		return itemNumber;
	}

	/**
	 * @param disbNumber the disbNumber to set
	 */
	public void setDisbNumber(String disbNumber) {
		this.disbNumber = disbNumber;
	}

	/**
	 * @return the disbNumber
	 */
	public String getDisbNumber() {
		return disbNumber;
	}

	/**
	 * @param itemLocationHeader the itemLocationHeader to set
	 */
	public void setItemLocationHeader(String itemLocationHeader) {
		this.itemLocationHeader = itemLocationHeader;
	}

	/**
	 * @return the itemLocationHeader
	 */
	public String getItemLocationHeader() {
		return itemLocationHeader;
	}

	/**
	 * @param requisitionerCode the requisitionerCode to set
	 */
	public void setRequisitionerCode(String requisitionerCode) {
		this.requisitionerCode = requisitionerCode;
	}

	/**
	 * @return the requisitionerCode
	 */
	public String getRequisitionerCode() {
		return requisitionerCode;
	}

	/**
	 * @param requisitionNumber the requisitionNumber to set
	 */
	public void setRequisitionNumber(String requisitionNumber) {
		this.requisitionNumber = requisitionNumber;
	}

	/**
	 * @return the requisitionNumber
	 */
	public String getRequisitionNumber() {
		return requisitionNumber;
	}

	/**
	 * @param shipToCode the shipToCode to set
	 */
	public void setShipToCode(String shipToCode) {
		this.shipToCode = shipToCode;
	}

	/**
	 * @return the shipToCode
	 */
	public String getShipToCode() {
		return shipToCode;
	}

	/**
	 * @param itemLocationLine the itemLocationLine to set
	 */
	public void setItemLocationLine(String itemLocationLine) {
		this.itemLocationLine = itemLocationLine;
	}

	/**
	 * @return the itemLocationLine
	 */
	public String getItemLocationLine() {
		return itemLocationLine;
	}



}