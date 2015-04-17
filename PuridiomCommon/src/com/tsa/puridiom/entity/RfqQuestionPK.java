package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class RfqQuestionPK implements Serializable {

    /** identifier field */
    private java.math.BigDecimal icRfqHeader;

    /** identifier field */
    private java.math.BigDecimal icQuestion;

    /** full constructor */
    public RfqQuestionPK(java.math.BigDecimal icRfqHeader, java.math.BigDecimal icQuestion) {
        this.icRfqHeader = icRfqHeader;
        this.icQuestion = icQuestion;
    }

    /** default constructor */
    public RfqQuestionPK() {
    }

    public java.math.BigDecimal getIcRfqHeader() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.icRfqHeader);
    }

    public void setIcRfqHeader(java.math.BigDecimal icRfqHeader) {
        this.icRfqHeader = icRfqHeader;
    }

    public java.math.BigDecimal getIcQuestion() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.icQuestion);
    }

    public void setIcQuestion(java.math.BigDecimal icQuestion) {
        this.icQuestion = icQuestion;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("icRfqHeader", getIcRfqHeader())
            .append("icQuestion", getIcQuestion())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof RfqQuestionPK) ) return false;
        RfqQuestionPK castOther = (RfqQuestionPK) other;
        return new EqualsBuilder()
            .append(this.getIcRfqHeader(), castOther.getIcRfqHeader())
            .append(this.getIcQuestion(), castOther.getIcQuestion())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getIcRfqHeader())
            .append(getIcQuestion())
            .toHashCode();
    }

}
