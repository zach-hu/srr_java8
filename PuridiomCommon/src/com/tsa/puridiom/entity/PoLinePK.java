package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class PoLinePK implements Serializable {

    /** identifier field */
    private java.math.BigDecimal icPoHeader;

    /** identifier field */
    private java.math.BigDecimal icPoLine;

    /** full constructor */
    public PoLinePK(java.math.BigDecimal icPoHeader, java.math.BigDecimal icPoLine) {
        this.icPoHeader = icPoHeader;
        this.icPoLine = icPoLine;
    }

    /** default constructor */
    public PoLinePK() {
    }

    public java.math.BigDecimal getIcPoHeader() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.icPoHeader);
    }

    public void setIcPoHeader(java.math.BigDecimal icPoHeader) {
        this.icPoHeader = icPoHeader;
    }

    public java.math.BigDecimal getIcPoLine() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.icPoLine);
    }

    public void setIcPoLine(java.math.BigDecimal icPoLine) {
        this.icPoLine = icPoLine;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("icPoHeader", getIcPoHeader())
            .append("icPoLine", getIcPoLine())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof PoLinePK) ) return false;
        PoLinePK castOther = (PoLinePK) other;
        return new EqualsBuilder()
            .append(this.getIcPoHeader(), castOther.getIcPoHeader())
            .append(this.getIcPoLine(), castOther.getIcPoLine())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getIcPoHeader())
            .append(getIcPoLine())
            .toHashCode();
    }

}
