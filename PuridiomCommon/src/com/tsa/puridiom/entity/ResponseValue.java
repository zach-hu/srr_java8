package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class ResponseValue implements Serializable {

    /** identifier field */
    private com.tsa.puridiom.entity.ResponseValuePK comp_id;

    /** nullable persistent field */
    private String responseText;

    /** nullable persistent field */
    private java.math.BigDecimal weight;
    
    /** nullable persistent field */
    private java.math.BigDecimal rating;

    /** full constructor */
    public ResponseValue(com.tsa.puridiom.entity.ResponseValuePK comp_id, java.lang.String responseText, java.math.BigDecimal weight, java.math.BigDecimal rating) {
        this.comp_id = comp_id;
        this.responseText = responseText;
        this.weight = weight;
        this.rating = rating;
    }

    /** default constructor */
    public ResponseValue() {
    }

    /** minimal constructor */
    public ResponseValue(com.tsa.puridiom.entity.ResponseValuePK comp_id) {
        this.comp_id = comp_id;
    }

    public com.tsa.puridiom.entity.ResponseValuePK getComp_id() {
        return this.comp_id;
    }

    public void setComp_id(com.tsa.puridiom.entity.ResponseValuePK comp_id) {
        this.comp_id = comp_id;
    }

    public java.lang.String getResponseText() {
		return (java.lang.String)HiltonUtility.ckNull(this.responseText).trim();
    }

    public void setResponseText(java.lang.String responseText) {
		if (!HiltonUtility.isEmpty(responseText) && responseText.length() > 255) {
			responseText = responseText.substring(0, 255);
		}
		this.responseText = responseText;
    }

    public java.math.BigDecimal getWeight() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.weight);
    }

    public void setWeight(java.math.BigDecimal weight) {
        this.weight = weight;
    }
    
    public java.math.BigDecimal getRating() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.rating);
    }

    public void setRating(java.math.BigDecimal rating) {
        this.rating = rating;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("comp_id", getComp_id())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof ResponseValue) ) return false;
        ResponseValue castOther = (ResponseValue) other;
        return new EqualsBuilder()
            .append(this.getComp_id(), castOther.getComp_id())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getComp_id())
            .toHashCode();
    }

}
