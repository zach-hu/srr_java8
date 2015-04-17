package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;
import java.math.BigDecimal;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class InvFormPartPK implements Serializable {

    /** identifier field */
    private String itemNumber;

    /** identifier field */
    private BigDecimal formPart;

    /** full constructor */
    public InvFormPartPK(java.lang.String itemNumber, BigDecimal formPart) {
        this.itemNumber = itemNumber;
        this.formPart = formPart;
    }

    /** default constructor */
    public InvFormPartPK() {
    }

    public java.lang.String getItemNumber() {
		return (java.lang.String)HiltonUtility.ckNull(this.itemNumber);
    }

    public void setItemNumber(java.lang.String itemNumber) {
        this.itemNumber = itemNumber;
    }

    public java.math.BigDecimal getFormPart() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.formPart);
    }

    public void setFormPart(java.math.BigDecimal formPart) {
        this.formPart = formPart;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("itemNumber", getItemNumber())
            .append("formPart", getFormPart())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof InvFormPartPK) ) return false;
        InvFormPartPK castOther = (InvFormPartPK) other;
        return new EqualsBuilder()
            .append(this.getItemNumber(), castOther.getItemNumber())
            .append(this.getFormPart(), castOther.getFormPart())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getItemNumber())
            .append(getFormPart())
            .toHashCode();
    }

}
