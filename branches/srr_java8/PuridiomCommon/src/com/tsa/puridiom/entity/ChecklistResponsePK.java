package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class ChecklistResponsePK implements Serializable {

    /** identifier field */
    private java.math.BigDecimal icHeader;

    /** identifier field */
    private java.math.BigDecimal icQuestion;

    /** full constructor */
    public ChecklistResponsePK(java.math.BigDecimal icHeader, java.math.BigDecimal icQuestion) {
        this.icHeader = icHeader;
        this.icQuestion = icQuestion;
    }

    /** default constructor */
    public ChecklistResponsePK() {
    }

    public java.math.BigDecimal getIcHeader() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.icHeader);
    }

    public void setIcHeader(java.math.BigDecimal icHeader) {
        this.icHeader = icHeader;
    }

    public java.math.BigDecimal getIcQuestion() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.icQuestion);
    }

    public void setIcQuestion(java.math.BigDecimal icQuestion) {
        this.icQuestion = icQuestion;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("icHeader", getIcHeader())
            .append("icQuestion", getIcQuestion())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof ChecklistResponsePK) ) return false;
        ChecklistResponsePK castOther = (ChecklistResponsePK) other;
        return new EqualsBuilder()
            .append(this.getIcHeader(), castOther.getIcHeader())
            .append(this.getIcQuestion(), castOther.getIcQuestion())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getIcHeader())
            .append(getIcQuestion())
            .toHashCode();
    }

}
