package com.tsa.puridiom.entity;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class InspectionDisposPK implements Serializable {

    /** identifier field */
    private java.math.BigDecimal icRecHeader;

    /** identifier field */
    private java.math.BigDecimal icRecLine;

    private java.math.BigDecimal icInspDiscrep ;

    /** identifier field */
    private java.math.BigDecimal keySequence;

    /** full constructor */
    public InspectionDisposPK(java.math.BigDecimal icRecHeader, java.math.BigDecimal icRecLine,  java.math.BigDecimal icInspDiscrep, java.math.BigDecimal keySequence) {
        this.icRecHeader = icRecHeader;
        this.icRecLine = icRecLine;
        this.icInspDiscrep = icInspDiscrep;
        this.keySequence = keySequence;
    }

    /** default constructor */
    public InspectionDisposPK() {
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

	public java.math.BigDecimal getIcInspDiscrep() {
		return icInspDiscrep;
	}

	public void setIcInspDiscrep(java.math.BigDecimal icInspDiscrep) {
		this.icInspDiscrep = icInspDiscrep;
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
            .append("keySequence", getKeySequence())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof InspectionDisposPK) ) return false;
        InspectionDisposPK castOther = (InspectionDisposPK) other;
        return new EqualsBuilder()
            .append(this.getIcRecHeader(), castOther.getIcRecHeader())
            .append(this.getIcRecLine(), castOther.getIcRecLine())
            .append(this.getKeySequence(), castOther.getKeySequence())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getIcRecHeader())
            .append(getIcRecLine())
            .append(getKeySequence())
            .toHashCode();
    }

}
