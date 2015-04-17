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
public class ChecklistEntry implements Serializable {

    /** identifier field */
    private java.math.BigDecimal icQuestion;
    
    /** nullable persistent field */
    private String referenceType;
    
    /** persistent field */
    private java.math.BigDecimal sequence;

    /** nullable persistent field */
    private String questionText;

    /** nullable persistent field */
    private String responseType;

    private List responseValueList;
    private Map responseValueMap;
    
    /** full constructor */
    public ChecklistEntry(java.math.BigDecimal icQuestion, java.lang.String referenceType, java.math.BigDecimal sequence, java.lang.String questionText, java.lang.String responseType) {
        this.icQuestion = icQuestion;
        this.referenceType = referenceType;
        this.sequence = sequence;
        this.questionText = questionText;
        this.responseType = responseType;
    }

    /** default constructor */
    public ChecklistEntry() {
    }

    /** minimal constructor */
    public ChecklistEntry(java.math.BigDecimal icQuestion) {
        this.icQuestion = icQuestion;
    }

    public java.math.BigDecimal getIcQuestion() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.icQuestion);
    }

    public void setIcQuestion(java.math.BigDecimal icQuestion) {
        this.icQuestion = icQuestion;
    }

    public java.lang.String getReferenceType() {
		return (java.lang.String)HiltonUtility.ckNull(this.referenceType).trim();
    }

    public void setReferenceType(java.lang.String referenceType) {
		if (!HiltonUtility.isEmpty(referenceType) && referenceType.length() > 3) {
			referenceType = referenceType.substring(0, 3);
		}
		this.referenceType = referenceType;
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
        if ( !(other instanceof ChecklistEntry) ) return false;
        ChecklistEntry castOther = (ChecklistEntry) other;
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
