package com.tsa.puridiom.entity;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class PerformanceHeaderPK implements Serializable {
    /** identifier field */
    private java.lang.String evalNumber;

    /** identifier field */
    private java.math.BigDecimal icPoHeader;

    private String sectionName;

    /** full constructor */
    public PerformanceHeaderPK(java.lang.String evalNumber, java.math.BigDecimal icPoHeader) {
        this.evalNumber = evalNumber;
        this.icPoHeader = icPoHeader;
    }

    /** default constructor */
    public PerformanceHeaderPK() {
    }



    public String toString() {
        return new ToStringBuilder(this)
            .append("evalNumber", getEvalNumber())
            .append("icPoHeader", getIcPoHeader())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof PerformanceHeaderPK) ) return false;
        PerformanceHeaderPK castOther = (PerformanceHeaderPK) other;
        return new EqualsBuilder()
            .append(this.getEvalNumber(), castOther.getEvalNumber())
            .append(this.getIcPoHeader(), castOther.getIcPoHeader())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getEvalNumber())
            .append(getIcPoHeader())
            .toHashCode();
    }

	public java.lang.String getEvalNumber() {
		return evalNumber;
	}

	public void setEvalNumber(java.lang.String evalNumber) {
		this.evalNumber = evalNumber;
	}

	public java.math.BigDecimal getIcPoHeader() {
		return icPoHeader;
	}

	public void setIcPoHeader(java.math.BigDecimal icPoHeader) {
		this.icPoHeader = icPoHeader;
	}

	public String getSectionName() {
		return sectionName;
	}

	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}

}
