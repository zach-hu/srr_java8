package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class PoAssetPK implements Serializable {

    /** identifier field */
    private java.math.BigDecimal icPoLine;

    /** identifier field */
    private java.math.BigDecimal sequence;

    /** full constructor */
    public PoAssetPK(java.math.BigDecimal icPoLine, java.math.BigDecimal sequence) {
        this.icPoLine = icPoLine;
        this.sequence = sequence;
    }

    /** default constructor */
    public PoAssetPK() {
    }

    public java.math.BigDecimal getIcPoLine() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.icPoLine);
    }

    public void setIcPoLine(java.math.BigDecimal icPoLine) {
        this.icPoLine = icPoLine;
    }

    public java.math.BigDecimal getSequence() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.sequence);
    }

    public void setSequence(java.math.BigDecimal sequence) {
        this.sequence = sequence;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("icPoLine", getIcPoLine())
            .append("sequence", getSequence())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof PoAssetPK) ) return false;
        PoAssetPK castOther = (PoAssetPK) other;
        return new EqualsBuilder()
            .append(this.getIcPoLine(), castOther.getIcPoLine())
            .append(this.getSequence(), castOther.getSequence())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getIcPoLine())
            .append(getSequence())
            .toHashCode();
    }

}
