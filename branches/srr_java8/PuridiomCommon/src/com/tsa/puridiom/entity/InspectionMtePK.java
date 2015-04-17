package com.tsa.puridiom.entity;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class InspectionMtePK implements Serializable {

    /** identifier field */
    private java.math.BigDecimal icRecHeader;

    /** identifier field */
    private java.math.BigDecimal icRecLine;

    /** identifier field */
    private java.math.BigDecimal keySequence;

    /** identifier field */
    private java.math.BigDecimal icInspNo;

    /** full constructor */
    public InspectionMtePK(java.math.BigDecimal icRecHeader, java.math.BigDecimal icRecLine, java.math.BigDecimal keySequence, java.math.BigDecimal icInspNo) {
        this.icRecHeader = icRecHeader;
        this.icRecLine = icRecLine;
        this.keySequence = keySequence;
        this.icInspNo = icInspNo;
    }

    /** default constructor */
    public InspectionMtePK() {
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

    public java.math.BigDecimal getKeySequence() {
        return this.keySequence;
    }

    public void setKeySequence(java.math.BigDecimal keySequence) {
        this.keySequence = keySequence;
    }

    public java.math.BigDecimal getIcInspNo() {
		return icInspNo;
	}

	public void setIcInspNo(java.math.BigDecimal icInspNo) {
		this.icInspNo = icInspNo;
	}

    public String toString() {
        return new ToStringBuilder(this)
            .append("icRecHeader", getIcRecHeader())
            .append("icRecLine", getIcRecLine())
            .append("keySequence", getKeySequence())
            .append("icInspNo", getIcInspNo())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof InspectionMtePK) ) return false;
        InspectionMtePK castOther = (InspectionMtePK) other;
        return new EqualsBuilder()
            .append(this.getIcRecHeader(), castOther.getIcRecHeader())
            .append(this.getIcRecLine(), castOther.getIcRecLine())
            .append(this.getKeySequence(), castOther.getKeySequence())
            .append(this.getIcInspNo(), castOther.getIcInspNo())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getIcRecHeader())
            .append(getIcRecLine())
            .append(getKeySequence())
            .append(getIcInspNo())
            .toHashCode();
    }

}
