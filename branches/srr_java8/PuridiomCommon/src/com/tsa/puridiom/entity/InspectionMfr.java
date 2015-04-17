package com.tsa.puridiom.entity;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class InspectionMfr implements Serializable {

    /** identifier field */
    private java.math.BigDecimal icMfrNo;

	/** identifier field */
    private java.math.BigDecimal icInspNo;

    /** nullable persistent field */
    private java.math.BigDecimal icMsrLine;

    /** nullable persistent field */
    private String documentType;

    /** nullable persistent field */
    private String mfrName;

    /** nullable persistent field */
    private String mfrNo;

    /** nullable persistent field */
    private String mfrHeatLot;

    /** nullable persistent field */
    private java.util.Date shelfLifeDate;

    /** full constructor */
    public InspectionMfr(java.math.BigDecimal icMfrNo, java.math.BigDecimal icInspNo, java.math.BigDecimal icMsrLine, java.lang.String documentType, java.lang.String mfrName, java.lang.String mfrNo, java.lang.String mfrHeatLot, java.util.Date shelfLifeDate) {
    	this.icMfrNo = icMfrNo ;
        this.icInspNo = icInspNo;
        this.icMsrLine = icMsrLine;
        this.documentType = documentType;
        this.mfrName = mfrName;
        this.mfrNo = mfrNo;
        this.mfrHeatLot = mfrHeatLot;
        this.shelfLifeDate = shelfLifeDate;
    }

    /** default constructor */
    public InspectionMfr() {
    }

    /** minimal constructor */
    public InspectionMfr(java.math.BigDecimal icMfrNo) {
        this.icMfrNo = icMfrNo;
    }

    public java.math.BigDecimal getIcMfrNo() {
		return icMfrNo;
	}

	public void setIcMfrNo(java.math.BigDecimal icMfrNo) {
		this.icMfrNo = icMfrNo;
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

    public java.lang.String getDocumentType() {
        return this.documentType;
    }

    public void setDocumentType(java.lang.String documentType) {
        this.documentType = documentType;
    }

    public java.lang.String getMfrName() {
        return this.mfrName;
    }

    public void setMfrName(java.lang.String mfrName) {
        this.mfrName = mfrName;
    }

    public java.lang.String getMfrNo() {
        return this.mfrNo;
    }

    public void setMfrNo(java.lang.String mfrNo) {
        this.mfrNo = mfrNo;
    }

    public java.lang.String getMfrHeatLot() {
        return this.mfrHeatLot;
    }

    public void setMfrHeatLot(java.lang.String mfrHeatLot) {
        this.mfrHeatLot = mfrHeatLot;
    }

    public java.util.Date getShelfLifeDate() {
        return this.shelfLifeDate;
    }

    public void setShelfLifeDate(java.util.Date shelfLifeDate) {
        this.shelfLifeDate = shelfLifeDate;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("icMfrNo", getIcMfrNo())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof InspectionMfr) ) return false;
        InspectionMfr castOther = (InspectionMfr) other;
        return new EqualsBuilder()
            .append(this.getIcMfrNo(), castOther.getIcMfrNo())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getIcMfrNo())
            .toHashCode();
    }

}
