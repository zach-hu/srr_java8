package com.tsa.puridiom.entity;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class InspectionHeaderPK implements Serializable {

    /** identifier field */
    private java.math.BigDecimal icInspNo;

    /** identifier field */
    private java.math.BigDecimal icMsrLine;

    /** full constructor */
    public InspectionHeaderPK(java.math.BigDecimal icInspNo, java.math.BigDecimal icMsrLine) {
        this.icInspNo = icInspNo;
        this.icMsrLine = icMsrLine;
    }

    /** default constructor */
    public InspectionHeaderPK() {
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

    public String toString() {
        return new ToStringBuilder(this)
            .append("icInspNo", getIcInspNo())
            .append("icMsrLine", getIcMsrLine())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof InspectionHeaderPK) ) return false;
        InspectionHeaderPK castOther = (InspectionHeaderPK) other;
        return new EqualsBuilder()
            .append(this.getIcInspNo(), castOther.getIcInspNo())
            .append(this.getIcMsrLine(), castOther.getIcMsrLine())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getIcInspNo())
            .append(getIcMsrLine())
            .toHashCode();
    }

}
