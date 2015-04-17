package com.tsa.puridiom.entity;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class InspectionHistory implements Serializable {

    /** identifier field */
    private java.math.BigDecimal icHistory;

    /** persistent field */
    private java.math.BigDecimal icRecLine;


    /** persistent field */
    private java.math.BigDecimal icInspNo;

    /** nullable persistent field */
    private java.math.BigDecimal icMsrLine;

    /** nullable persistent field */
    private String recType;

    /** nullable persistent field */
    private java.util.Date statusDate;

    /** nullable persistent field */
    private java.math.BigDecimal quantity;

    /** nullable persistent field */
    private String area;

    /** nullable persistent field */
    private String storage;

    /** nullable persistent field */
    private String location;

    /** nullable persistent field */
    private String status;

    /** nullable persistent field */
    private String owner;

    /** nullable persistent field */
    private String historyText;

    /** full constructor */
    public InspectionHistory(java.math.BigDecimal icHistory, java.math.BigDecimal icRecLine, java.math.BigDecimal icInspNo, java.math.BigDecimal icMsrLine, java.lang.String recType, java.util.Date statusDate, java.math.BigDecimal quantity, java.lang.String area, java.lang.String storage, java.lang.String location, java.lang.String status, java.lang.String owner, java.lang.String historyText) {
        this.icHistory = icHistory;
        this.icRecLine = icRecLine;
        this.icInspNo = icInspNo;
        this.icMsrLine = icMsrLine;
        this.recType = recType;
        this.statusDate = statusDate;
        this.quantity = quantity;
        this.area = area;
        this.storage = storage;
        this.location = location;
        this.status = status;
        this.owner = owner;
        this.historyText = historyText;
    }

    /** default constructor */
    public InspectionHistory() {
    }

    /** minimal constructor */
    public InspectionHistory(java.math.BigDecimal icHistory, java.math.BigDecimal icInspNo) {
        this.icHistory = icHistory;
        this.icInspNo = icInspNo;
    }

    public java.math.BigDecimal getIcHistory() {
        return this.icHistory;
    }

    public void setIcHistory(java.math.BigDecimal icHistory) {
        this.icHistory = icHistory;
    }

    public java.math.BigDecimal getIcRecLine() {
		return icRecLine;
	}

	public void setIcRecLine(java.math.BigDecimal icRecLine) {
		this.icRecLine = icRecLine;
	}

	public java.math.BigDecimal getIcInspNo() {
        return this.icInspNo;
    }

    public void setIcInspNo(java.math.BigDecimal icInspNo) {
        this.icInspNo = icInspNo;
    }

    public java.math.BigDecimal getIcMsrLine() {
        return this.icMsrLine;
    }

    public void setIcMsrLine(java.math.BigDecimal icMsrLine) {
        this.icMsrLine = icMsrLine;
    }

    public java.lang.String getRecType() {
        return this.recType;
    }

    public void setRecType(java.lang.String recType) {
        this.recType = recType;
    }

    public java.util.Date getStatusDate() {
        return this.statusDate;
    }

    public void setStatusDate(java.util.Date statusDate) {
        this.statusDate = statusDate;
    }

    public java.math.BigDecimal getQuantity() {
        return this.quantity;
    }

    public void setQuantity(java.math.BigDecimal quantity) {
        this.quantity = quantity;
    }

    public java.lang.String getArea() {
        return this.area;
    }

    public void setArea(java.lang.String area) {
        this.area = area;
    }

    public java.lang.String getStorage() {
        return this.storage;
    }

    public void setStorage(java.lang.String storage) {
        this.storage = storage;
    }

    public java.lang.String getLocation() {
        return this.location;
    }

    public void setLocation(java.lang.String location) {
        this.location = location;
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

    public java.lang.String getHistoryText() {
        return this.historyText;
    }

    public void setHistoryText(java.lang.String historyText) {
        this.historyText = historyText;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("icHistory", getIcHistory())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof InspectionHistory) ) return false;
        InspectionHistory castOther = (InspectionHistory) other;
        return new EqualsBuilder()
            .append(this.getIcHistory(), castOther.getIcHistory())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getIcHistory())
            .toHashCode();
    }

}
