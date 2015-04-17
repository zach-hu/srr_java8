package com.tsa.puridiom.entity;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class InspectionDiscrepPK implements Serializable {

    /** identifier field */
    private java.math.BigDecimal icRecHeader;

    /** identifier field */
    private java.math.BigDecimal icRecLine;

    private java.math.BigDecimal icInspNo ;

    /** identifier field */
    private java.math.BigDecimal keySequence;

    /** full constructor */
    public InspectionDiscrepPK(java.math.BigDecimal icRecHeader, java.math.BigDecimal icRecLine, java.math.BigDecimal icInspNo, java.math.BigDecimal keySequence) {
        this.icRecHeader = icRecHeader;
        this.icRecLine = icRecLine;
        this.icInspNo = icInspNo ;
        this.keySequence = keySequence;
    }

    /** default constructor */
    public InspectionDiscrepPK() {
    }

    public java.math.BigDecimal getIcRecHeader() {
        return this.icRecHeader;
    }

    public void setIcRecHeader(java.math.BigDecimal icRecHeader) {
        this.icRecHeader = icRecHeader;
    }

    public java.math.BigDecimal getIcRecLine() {
        return this.icRecLine;
    }

    public void setIcRecLine(java.math.BigDecimal icRecLine) {
        this.icRecLine = icRecLine;
    }

    public java.math.BigDecimal getIcInspNo() {
		return icInspNo;
	}

	public void setIcInspNo(java.math.BigDecimal icInspNo) {
		this.icInspNo = icInspNo;
	}

    public java.math.BigDecimal getKeySequence() {
        return this.keySequence;
    }

    public void setKeySequence(java.math.BigDecimal keySequence) {
        this.keySequence = keySequence;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("icRecHeader", getIcRecHeader())
            .append("icRecLine", getIcRecLine())
            .append("icInspNo", getIcInspNo())
            .append("keySequence", getKeySequence())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof InspectionDiscrepPK) ) return false;
        InspectionDiscrepPK castOther = (InspectionDiscrepPK) other;
        return new EqualsBuilder()
            .append(this.getIcRecHeader(), castOther.getIcRecHeader())
            .append(this.getIcRecLine(), castOther.getIcRecLine())
            .append(this.getIcInspNo(), castOther.getIcInspNo())
            .append(this.getKeySequence(), castOther.getKeySequence())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getIcRecHeader())
            .append(getIcRecLine())
            .append(getIcInspNo())
            .append(getKeySequence())
            .toHashCode();
    }

}
