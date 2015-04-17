package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class VendorResponse implements Serializable {

    /** identifier field */
    private com.tsa.puridiom.entity.VendorResponsePK comp_id;

    /** nullable persistent field */
    private String response;

    /** nullable persistent field */
    private String textResponse;
    
    /** nullable persistent field */
    private java.math.BigDecimal score;

    /** full constructor */
    public VendorResponse(com.tsa.puridiom.entity.VendorResponsePK comp_id, java.lang.String response, java.lang.String textResponse, java.math.BigDecimal score) {
        this.comp_id = comp_id;
        this.response = response;
        this.textResponse = textResponse;
        this.score = score;
    }

    /** default constructor */
    public VendorResponse() {
    }

    /** minimal constructor */
    public VendorResponse(com.tsa.puridiom.entity.VendorResponsePK comp_id) {
        this.comp_id = comp_id;
    }

    public com.tsa.puridiom.entity.VendorResponsePK getComp_id() {
        return this.comp_id;
    }

    public void setComp_id(com.tsa.puridiom.entity.VendorResponsePK comp_id) {
        this.comp_id = comp_id;
    }

    public java.lang.String getResponse() {
		return (java.lang.String)HiltonUtility.ckNull(this.response).trim();
    }

    public void setResponse(java.lang.String response) {
		if (!HiltonUtility.isEmpty(response) && response.length() > 2) {
			response = response.substring(0, 2);
		}
		this.response = response;
    }

    public java.lang.String getTextResponse() {
		return (java.lang.String)HiltonUtility.ckNull(this.textResponse).trim();
    }

    public void setTextResponse(java.lang.String textResponse) {
		if (!HiltonUtility.isEmpty(textResponse) && textResponse.length() > 60) {
			textResponse = textResponse.substring(0, 60);
		}
		this.textResponse = textResponse;
    }

    public java.math.BigDecimal getScore() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.score);
    }

    public void setScore(java.math.BigDecimal score) {
        this.score = score;
    }
    
    public String toString() {
        return new ToStringBuilder(this)
            .append("comp_id", getComp_id())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof VendorResponse) ) return false;
        VendorResponse castOther = (VendorResponse) other;
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
