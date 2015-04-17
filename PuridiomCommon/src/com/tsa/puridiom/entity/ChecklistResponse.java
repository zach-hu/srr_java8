package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class ChecklistResponse implements Serializable {

    /** identifier field */
    private com.tsa.puridiom.entity.ChecklistResponsePK comp_id;

    /** nullable persistent field */
    private String response;

    /** nullable persistent field */
    private String textResponse;

    /** full constructor */
    public ChecklistResponse(com.tsa.puridiom.entity.ChecklistResponsePK comp_id, java.lang.String response, java.lang.String textResponse) {
        this.comp_id = comp_id;
        this.response = response;
        this.textResponse = textResponse;
    }

    /** default constructor */
    public ChecklistResponse() {
    }

    /** minimal constructor */
    public ChecklistResponse(com.tsa.puridiom.entity.ChecklistResponsePK comp_id) {
        this.comp_id = comp_id;
    }

    public com.tsa.puridiom.entity.ChecklistResponsePK getComp_id() {
        return this.comp_id;
    }

    public void setComp_id(com.tsa.puridiom.entity.ChecklistResponsePK comp_id) {
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

    public String toString() {
        return new ToStringBuilder(this)
            .append("comp_id", getComp_id())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof ChecklistResponse) ) return false;
        ChecklistResponse castOther = (ChecklistResponse) other;
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
