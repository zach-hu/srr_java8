package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class InvAllocPK implements Serializable {

    /** identifier field */
    private String referenceType;

    /** identifier field */
    private java.math.BigDecimal icLoc;

    /** identifier field */
    private java.math.BigDecimal icLine;

    /** full constructor */
    public InvAllocPK(java.lang.String referenceType, java.math.BigDecimal icLoc, java.math.BigDecimal icLine) {
        this.referenceType = referenceType;
        this.icLoc = icLoc;
        this.icLine = icLine;
    }

    /** default constructor */
    public InvAllocPK() {
    }

    public java.lang.String getReferenceType() {
		return this.referenceType;
    }

    public void setReferenceType(java.lang.String referenceType) {
        this.referenceType = referenceType;
    }

    public java.math.BigDecimal getIcLoc() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.icLoc);
    }

    public void setIcLoc(java.math.BigDecimal icLoc) {
        this.icLoc = icLoc;
    }

    public java.math.BigDecimal getIcLine() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.icLine);
    }

    public void setIcLine(java.math.BigDecimal icLine) {
        this.icLine = icLine;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("referenceType", getReferenceType())
            .append("icLoc", getIcLoc())
            .append("icLine", getIcLine())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof InvAllocPK) ) return false;
        InvAllocPK castOther = (InvAllocPK) other;
        return new EqualsBuilder()
            .append(this.getReferenceType(), castOther.getReferenceType())
            .append(this.getIcLoc(), castOther.getIcLoc())
            .append(this.getIcLine(), castOther.getIcLine())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getReferenceType())
            .append(getIcLoc())
            .append(getIcLine())
            .toHashCode();
    }

}
