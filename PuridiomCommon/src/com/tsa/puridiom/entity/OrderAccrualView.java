package com.tsa.puridiom.entity;

import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.tsa.puridiom.common.utility.HiltonUtility;

/** @author Hibernate CodeGenerator */
public class OrderAccrualView implements Serializable
{
    /** nullable persistent field */
    private String poNumber;

    /** nullable persistent field */
    private String vendorId;

    private String supplierName;

    /** nullable persistent field */
    private String shipToCode;

    /** nullable persistent field */
    private java.math.BigDecimal totalPaid;

    /** nullable persistent field */
    private String status;

    /** nullable persistent field */
    private java.util.Date poDate;

    private String buyerCode;

    private String currencyCode;

    private String requisitionerCode;

    private String departmentCode;

    private String poType;

    private java.util.Date requiredDate;

    private String purpose;

    private java.math.BigDecimal orderTotal;

    /** full constructor */
    public OrderAccrualView(java.lang.String poNumber, java.lang.String vendorId, java.lang.String shipToCode, java.math.BigDecimal totalPaid, java.lang.String status, java.util.Date poDate, java.lang.String buyerCode, java.lang.String currencyCode,
    		java.lang.String requisitionerCode, java.lang.String poType, java.util.Date requiredDate, java.lang.String purpose, java.math.BigDecimal orderTotal, java.lang.String supplierName)
    {
        this.poNumber = poNumber;
        this.vendorId = vendorId;
        this.shipToCode = shipToCode;
        this.totalPaid = totalPaid;
        this.status = status;
        this.poDate = poDate;
        this.buyerCode = buyerCode;
        this.currencyCode = currencyCode;
        this.requisitionerCode = requisitionerCode;
        this.poType = poType;
        this.requiredDate = requiredDate;
        this.purpose = purpose;
        this.orderTotal = orderTotal;
        this.supplierName = supplierName;
    }

    /** default constructor */
    public OrderAccrualView() {
    }

    public java.lang.String getPoNumber() {
        return this.poNumber;
    }

    public void setPoNumber(java.lang.String poNumber) {
        this.poNumber = poNumber;
    }

    public java.lang.String getVendorId() {
        return this.vendorId;
    }

    public void setVendorId(java.lang.String vendorId) {
        this.vendorId = vendorId;
    }

    public java.lang.String getShipToCode() {
        return this.shipToCode;
    }

    public void setShipToCode(java.lang.String fld2) {
        this.shipToCode = fld2;
    }

    public java.lang.String getBuyerCode() {
        return this.buyerCode;
    }

    public void setBuyerCode(java.lang.String buyerCode) {
        this.buyerCode = buyerCode;
    }

    public java.math.BigDecimal getTotalPaid() {
    	return (java.math.BigDecimal)HiltonUtility.ckNull(this.totalPaid);
    }

    public void setTotalPaid(java.math.BigDecimal awarded) {
        this.totalPaid = awarded;
    }

    public java.lang.String getStatus() {
        return this.status;
    }

    public void setStatus(java.lang.String status) {
        this.status = status;
    }

    public java.util.Date getPoDate() {
        return this.poDate;
    }

    public void setPoDate(java.util.Date poDate) {
        this.poDate = poDate;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .toString();
    }

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public String getRequisitionerCode() {
		return requisitionerCode;
	}

	public void setRequisitionerCode(String requisitionerCode) {
		this.requisitionerCode = requisitionerCode;
	}

	public String getDepartmentCode() {
		return departmentCode;
	}

	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}

	public String getPoType() {
		return poType;
	}

	public void setPoType(String poType) {
		this.poType = poType;
	}

	public java.util.Date getRequiredDate() {
		return requiredDate;
	}

	public void setRequiredDate(java.util.Date requiredDate) {
		this.requiredDate = requiredDate;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public java.math.BigDecimal getOrderTotal() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.orderTotal);
	}

	public void setOrderTotal(java.math.BigDecimal orderTotal) {
		this.orderTotal = orderTotal;
	}

	public String getSupplierName() {
		return this.supplierName;
	}

	public void setSupplierName(String vendorName) {
		this.supplierName = vendorName;
	}
}
