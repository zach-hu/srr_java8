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
public class StdQuestion implements Serializable {

    /** identifier field */
    private java.math.BigDecimal icQuestion;

    /** nullable persistent field */
    private String questionTitle;

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
    
    /** full constructor */
    public StdQuestion(java.math.BigDecimal icQuestion, java.lang.String questionTitle, java.lang.String questionText, java.lang.String responseType, java.lang.String ratingMethod, java.math.BigDecimal weight, java.math.BigDecimal maxPoints) {
        this.icQuestion = icQuestion;
        this.questionTitle = questionTitle;
        this.questionText = questionText;
        this.responseType = responseType;
        this.ratingMethod = ratingMethod;
        this.weight = weight;
        this.maxPoints = maxPoints;
    }

    /** default constructor */
    public StdQuestion() {
    }

    /** minimal constructor */
    public StdQuestion(java.math.BigDecimal icQuestion) {
        this.icQuestion = icQuestion;
    }

    public java.math.BigDecimal getIcQuestion() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.icQuestion);
    }

    public void setIcQuestion(java.math.BigDecimal icQuestion) {
        this.icQuestion = icQuestion;
    }

    public java.lang.String getQuestionTitle() {
		return (java.lang.String)HiltonUtility.ckNull(this.questionTitle).trim();
    }

    public void setQuestionTitle(java.lang.String questionTitle) {
		if (!HiltonUtility.isEmpty(questionTitle) && questionTitle.length() > 40) {
			questionTitle = questionTitle.substring(0, 40);
		}
		this.questionTitle = questionTitle;
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

    public String toString() {
        return new ToStringBuilder(this)
            .append("icQuestion", getIcQuestion())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof StdQuestion) ) return false;
        StdQuestion castOther = (StdQuestion) other;
        return new EqualsBuilder()
            .append(this.getIcQuestion(), castOther.getIcQuestion())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getIcQuestion())
            .toHashCode();
    }

}
