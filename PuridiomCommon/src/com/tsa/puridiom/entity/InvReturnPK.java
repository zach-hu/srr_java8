package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class InvReturnPK implements Serializable {

    /** identifier field */
    private String requisitionNumber;

    /** identifier field */
    private java.math.BigDecimal lineNo;

    /** identifier field */
    private String itemNumber;

    /** full constructor */
    public InvReturnPK(java.lang.String requisitionNumber, java.math.BigDecimal lineNo, java.lang.String itemNumber) {
        this.requisitionNumber = requisitionNumber;
        this.lineNo = lineNo;
        this.itemNumber = itemNumber;
    }

    /** default constructor */
    public InvReturnPK() {
    }

    public java.lang.String getRequisitionNumber() {
		return (java.lang.String)HiltonUtility.ckNull(this.requisitionNumber);
    }

    public void setRequisitionNumber(java.lang.String requisitionNumber) {
        this.requisitionNumber = requisitionNumber;
    }

    public java.math.BigDecimal getLineNo() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.lineNo);
    }

    public void setLineNo(java.math.BigDecimal lineNo) {
        this.lineNo = lineNo;
    }

    public java.lang.String getItemNumber() {
		return (java.lang.String)HiltonUtility.ckNull(this.itemNumber);
    }

    public void setItemNumber(java.lang.String itemNumber) {
        this.itemNumber = itemNumber;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("requisitionNumber", getRequisitionNumber())
            .append("lineNo", getLineNo())
            .append("itemNumber", getItemNumber())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof InvReturnPK) ) return false;
        InvReturnPK castOther = (InvReturnPK) other;
        return new EqualsBuilder()
            .append(this.getRequisitionNumber(), castOther.getRequisitionNumber())
            .append(this.getLineNo(), castOther.getLineNo())
            .append(this.getItemNumber(), castOther.getItemNumber())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getRequisitionNumber())
            .append(getLineNo())
            .append(getItemNumber())
            .toHashCode();
    }

}
