package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class RfqQuestion implements Serializable {

    /** identifier field */
    private com.tsa.puridiom.entity.RfqQuestionPK comp_id;

    /** persistent field */
    private java.math.BigDecimal sequence;

    /** nullable persistent field */
    private String questionText;

    /** nullable persistent field */
    private String responseType;

    /** nullable persistent field */
    private String ratingMethod;

    /** nullable persistent field */
    private java.math.BigDecimal weight;

    /** nullable persistent field */
    private java.math.BigDecimal maxPoints;
    
    private List responseValueList;
    private Map responseValueMap;
    private List vendorResponseList;
    private Map vendorResponseMap;
    
    /** full constructor */
    public RfqQuestion(com.tsa.puridiom.entity.RfqQuestionPK comp_id, java.math.BigDecimal sequence, java.lang.String questionText, java.lang.String responseType, java.lang.String ratingMethod, java.math.BigDecimal weight, java.math.BigDecimal maxPoints) {
        this.comp_id = comp_id;
        this.sequence = sequence;
        this.questionText = questionText;
        this.responseType = responseType;
        this.ratingMethod = ratingMethod;
        this.weight = weight;
        this.maxPoints = maxPoints;
    }

    /** default constructor */
    public RfqQuestion() {
    }

    /** minimal constructor */
    public RfqQuestion(com.tsa.puridiom.entity.RfqQuestionPK comp_id, java.math.BigDecimal sequence) {
        this.comp_id = comp_id;
        this.sequence = sequence;
    }

    public com.tsa.puridiom.entity.RfqQuestionPK getComp_id() {
        return this.comp_id;
    }

    public void setComp_id(com.tsa.puridiom.entity.RfqQuestionPK comp_id) {
        this.comp_id = comp_id;
    }

    public java.math.BigDecimal getSequence() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.sequence);
    }

    public void setSequence(java.math.BigDecimal sequence) {
        this.sequence = sequence;
    }

    public java.lang.String getQuestionText() {
		return (java.lang.String)HiltonUtility.ckNull(this.questionText).trim();
    }

    public void setQuestionText(java.lang.String questionText) {
		if (!HiltonUtility.isEmpty(questionText) && questionText.length() > 250) {
			questionText = questionText.substring(0, 250);
		}
		this.questionText = questionText;
    }

    public java.lang.String getResponseType() {
		return (java.lang.String)HiltonUtility.ckNull(this.responseType).trim();
    }

    public void setResponseType(java.lang.String responseType) {
		if (!HiltonUtility.isEmpty(responseType) && responseType.length() > 5) {
			responseType = responseType.substring(0, 5);
		}
		this.responseType = responseType;
    }

    public java.lang.String getRatingMethod() {
		return (java.lang.String)HiltonUtility.ckNull(this.ratingMethod).trim();
    }

    public void setRatingMethod(java.lang.String ratingMethod) {
		if (!HiltonUtility.isEmpty(ratingMethod) && ratingMethod.length() > 3) {
		    ratingMethod = ratingMethod.substring(0, 3);
		}
		this.ratingMethod = ratingMethod;
    }
    
    public java.math.BigDecimal getWeight() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.weight);
    }

    public void setWeight(java.math.BigDecimal weight) {
        this.weight = weight;
    }
    
    public java.math.BigDecimal getMaxPoints() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.maxPoints);
    }

    public void setMaxPoints(java.math.BigDecimal maxPoints) {
        this.maxPoints = maxPoints;
    }
    
    public List getResponseValueList() {
        return responseValueList;
    }
    
    public Map getResponseValueMap() {
        return responseValueMap;
    }
    
    public void setResponseValueList(List responseValueList) {
        if (responseValueList != null) {
            responseValueMap = new HashMap();
            for (int i = 0; i < responseValueList.size(); i++) {
                ResponseValue responseValue = (ResponseValue) responseValueList.get(i);
                responseValueMap.put(responseValue.getComp_id().getResponseValue(), responseValue);
            }
        }
        this.responseValueList = responseValueList;
    }
    
    public List getVendorResponseList() {
        return vendorResponseList;
    }
    
    public Map getVendorResponseMap() {
        return vendorResponseMap;
    }
    
    public void setVendorResponseList(List vendorResponseList) {
        if (vendorResponseList != null) {
            vendorResponseMap = new HashMap();
            for (int i = 0; i < vendorResponseList.size(); i++) {
                VendorResponse vendorResponse = (VendorResponse) vendorResponseList.get(i);
                vendorResponseMap.put(vendorResponse.getComp_id().getVendorId(), vendorResponse);
            }
        }
        this.vendorResponseList = vendorResponseList;
    }
    
    public String toString() {
        return new ToStringBuilder(this)
            .append("comp_id", getComp_id())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof RfqQuestion) ) return false;
        RfqQuestion castOther = (RfqQuestion) other;
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
