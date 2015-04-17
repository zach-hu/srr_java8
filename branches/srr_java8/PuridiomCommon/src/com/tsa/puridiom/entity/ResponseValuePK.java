package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class ResponseValuePK implements Serializable {

    /** identifier field */
    private java.math.BigDecimal icQuestion;

    /** identifier field */
    private String responseValue;

    /** full constructor */
    public ResponseValuePK(java.math.BigDecimal icQuestion, java.lang.String responseValue) {
        this.icQuestion = icQuestion;
        this.responseValue = responseValue;
    }

    /** default constructor */
    public ResponseValuePK() {
    }

    public java.math.BigDecimal getIcQuestion() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.icQuestion);
    }

    public void setIcQuestion(java.math.BigDecimal icQuestion) {
        this.icQuestion = icQuestion;
    }

    public java.lang.String getResponseValue() {
		return (java.lang.String)HiltonUtility.ckNull(this.responseValue);
    }

    public void setResponseValue(java.lang.String responseValue) {
        this.responseValue = responseValue;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("icQuestion", getIcQuestion())
            .append("responseValue", getResponseValue())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof ResponseValuePK) ) return false;
        ResponseValuePK castOther = (ResponseValuePK) other;
        return new EqualsBuilder()
            .append(this.getIcQuestion(), castOther.getIcQuestion())
            .append(this.getResponseValue(), castOther.getResponseValue())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getIcQuestion())
            .append(getResponseValue())
            .toHashCode();
    }

}
