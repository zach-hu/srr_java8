package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class ShieldAdvancedView implements Serializable {

	/** identifier field */
    private String requisitionNumber;

    /** nullable persistent field */
    private java.util.Date requisitionDate;

    /** nullable persistent field */
    private String poNumber;

    /** nullable persistent field */
    private java.math.BigDecimal releaseNumber;

    /** nullable persistent field */
    private java.math.BigDecimal revisionNumber;

    /** nullable persistent field */
    private java.util.Date poDate;

    /** nullable persistent field */
    private String requisitionerCode;

    /** nullable persistent field */
    private String vendorName;

    /** nullable persistent field */
    private String buyerCode;

    /** nullable persistent field */
    private String status;

    /** nullable persistent field */
    private String fld1;

    /** nullable persistent field */
    private String fld2;

    /** nullable persistent field */
    private String fld3;

    /** nullable persistent field */
    private String fld4;

    /** persistent field */
    private java.math.BigDecimal total;

    /** identifier field */
    private java.math.BigDecimal icHistory;

    /** nullable persistent field */
    private String internalComments;

    /** nullable persistent field */
    private String buyerRemarks;

    /** nullable persistent field */
    private String currencyCode;

    /** nullable persistent field */
    private java.math.BigDecimal currencyFactor;

    /** nullable persistent field */
    private String fiscalYear;

    /** nullable persistent field */
    private String remark;

    /** full constructor */
    public ShieldAdvancedView(java.lang.String requisitionNumber, java.util.Date requisitionDate, java.lang.String poNumber, java.math.BigDecimal releaseNumber, java.math.BigDecimal revisionNumber, java.util.Date poDate, java.lang.String requisitionerCode, java.lang.String vendorName, java.lang.String buyerCode, java.lang.String fld1, java.lang.String fld2, java.lang.String fld3, java.lang.String fld4, java.math.BigDecimal total, java.math.BigDecimal icHistory, java.lang.String internalComments, java.lang.String buyerRemarks, java.lang.String currencyCode, java.math.BigDecimal currencyFactor, java.lang.String fiscalYear, java.lang.String remark) {
        this.requisitionNumber = requisitionNumber;
        this.requisitionDate = requisitionDate;
        this.poNumber = poNumber;
        this.releaseNumber = releaseNumber;
        this.revisionNumber = revisionNumber;
        this.poDate = poDate;
        this.requisitionerCode = requisitionerCode;
        this.vendorName = vendorName;
        this.buyerCode = buyerCode;
        this.fld1 = fld1;
        this.fld2 = fld2;
        this.fld3 = fld3;
        this.fld4 = fld4;
        this.total = total;
        this.icHistory = icHistory;
        this.internalComments = internalComments;
        this.buyerRemarks = buyerRemarks;
        this.currencyCode = currencyCode;
        this.currencyFactor = currencyFactor;
        this.fiscalYear = fiscalYear;
        this.remark = remark;

}

    /** default constructor */
    public ShieldAdvancedView() {
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

    public java.util.Date getRequisitionDate() {
		return this.requisitionDate;
//		return HiltonUtility.ckNull(this.requisitionDate);
//		return (java.sql.Date)HiltonUtility.ckNull(this.requisitionDate);
    }

    public void setRequisitionDate(java.util.Date requisitionDate) {
        this.requisitionDate = requisitionDate;
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

    public java.math.BigDecimal getReleaseNumber() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.releaseNumber);
    }

    public void setReleaseNumber(java.math.BigDecimal releaseNumber) {
        this.releaseNumber = releaseNumber;
    }

    public java.math.BigDecimal getRevisionNumber() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.revisionNumber);
    }

    public void setRevisionNumber(java.math.BigDecimal revisionNumber) {
        this.revisionNumber = revisionNumber;
    }

    public java.util.Date getPoDate() {
        return this.poDate;
    }

    public void setPoDate(java.util.Date poDate) {
        this.poDate = poDate;
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

    public java.lang.String getVendorName() {
		return (java.lang.String)HiltonUtility.ckNull(this.vendorName).trim();
    }

    public void setVendorName(java.lang.String vendorName) {
		if (!HiltonUtility.isEmpty(vendorName) && vendorName.length() > 40) {
			vendorName = vendorName.substring(0, 40);
		}
		this.vendorName = vendorName;
    }

    public java.lang.String getBuyerCode() {
		return (java.lang.String)HiltonUtility.ckNull(this.buyerCode).trim();
    }

    public void setBuyerCode(java.lang.String buyerCode) {
		if (!HiltonUtility.isEmpty(buyerCode) && buyerCode.length() > 15) {
			buyerCode = buyerCode.substring(0, 15);
		}
		this.buyerCode = buyerCode;
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

    public java.lang.String getFld1() {
		return (java.lang.String)HiltonUtility.ckNull(this.fld1).trim();
    }

    public void setFld1(java.lang.String fld1) {
		if (!HiltonUtility.isEmpty(fld1) && fld1.length() > 50) {
			fld1 = fld1.substring(0, 50);
		}
		this.fld1 = fld1;
    }

    public java.lang.String getFld2() {
		return (java.lang.String)HiltonUtility.ckNull(this.fld2).trim();
    }

    public void setFld2(java.lang.String fld2) {
		if (!HiltonUtility.isEmpty(fld2) && fld2.length() > 50) {
			fld2 = fld2.substring(0, 50);
		}
		this.fld2 = fld2;
    }

    public java.lang.String getFld3() {
		return (java.lang.String)HiltonUtility.ckNull(this.fld3).trim();
    }

    public void setFld3(java.lang.String fld3) {
		if (!HiltonUtility.isEmpty(fld3) && fld3.length() > 50) {
			fld3 = fld3.substring(0, 50);
		}
		this.fld3 = fld3;
    }

    public java.lang.String getFld4() {
		return (java.lang.String)HiltonUtility.ckNull(this.fld4).trim();
    }

    public void setFld4(java.lang.String fld4) {
		if (!HiltonUtility.isEmpty(fld4) && fld4.length() > 50) {
			fld4 = fld4.substring(0, 50);
		}
		this.fld4 = fld4;
    }

    public java.math.BigDecimal getTotal() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.total);
    }

    public void setTotal(java.math.BigDecimal total) {
        this.total = total;
    }

    public java.math.BigDecimal getIcHistory() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.icHistory);
    }

    public void setIcHistory(java.math.BigDecimal icHistory) {
        this.icHistory = icHistory;
    }

    public java.lang.String getInternalComments() {
		return (java.lang.String)HiltonUtility.ckNull(this.internalComments).trim();
    }

    public void setInternalComments(java.lang.String internalComments) {
		if (!HiltonUtility.isEmpty(internalComments) && internalComments.length() > 255) {
			internalComments = internalComments.substring(0, 255);
		}
		this.internalComments = internalComments;
    }

    public java.lang.String getBuyerRemarks() {
		return (java.lang.String)HiltonUtility.ckNull(this.buyerRemarks).trim();
    }

    public void setBuyerRemarks(java.lang.String buyerRemarks) {
		if (!HiltonUtility.isEmpty(buyerRemarks) && buyerRemarks.length() > 90) {
			buyerRemarks = buyerRemarks.substring(0, 90);
		}
		this.buyerRemarks = buyerRemarks;
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

    public java.lang.String getFiscalYear() {
		return (java.lang.String)HiltonUtility.ckNull(this.fiscalYear).trim();
    }

    public void setFiscalYear(java.lang.String fiscalYear) {
		if (!HiltonUtility.isEmpty(fiscalYear) && fiscalYear.length() > 4) {
			fiscalYear = fiscalYear.substring(0, 4);
		}
		this.fiscalYear = fiscalYear;
    }

    public java.lang.String getRemark() {
		return (java.lang.String)HiltonUtility.ckNull(this.remark).trim();
    }

    public void setRemark(java.lang.String remark) {
		if (!HiltonUtility.isEmpty(remark) && remark.length() > 255) {
			remark = remark.substring(0, 255);
		}
		this.remark = remark;
    }


/*    public String toString() {
        return new ToStringBuilder(this)
            .append("icIvcLine", getIcIvcLine())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof AccrualDetailView) ) return false;
        AccrualDetailView castOther = (AccrualDetailView) other;
        return new EqualsBuilder()
            .append(this.getIcIvcLine(), castOther.getIcIvcLine())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getIcIvcLine())
            .toHashCode();
    }*/

}