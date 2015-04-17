package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class VendorQuestion implements Serializable {

    /** identifier field */
    private java.math.BigDecimal icVendorQuestion;

    /** persistent field */
    private java.math.BigDecimal icRfqHeader;

    /** persistent field */
    private String vendorId;

    /** nullable persistent field */
    private String questionTitle;

    /** nullable persistent field */
    private String questionText;

    /** nullable persistent field */
    private java.util.Date datePosted;

    /** nullable persistent field */
    private String timePosted;
    
    /** nullable persistent field */
    private String responseText;

    /** nullable persistent field */
    private java.util.Date responseDate;

    /** nullable persistent field */
    private String responseTime;

    /** full constructor */
    public VendorQuestion(java.math.BigDecimal icVendorQuestion, java.math.BigDecimal icRfqHeader, java.lang.String vendorId, java.lang.String questionTitle, java.lang.String questionText, java.util.Date datePosted, java.lang.String timePosted, java.lang.String responseText, java.util.Date responseDate, java.lang.String responseTime) {
        this.icVendorQuestion = icVendorQuestion;
        this.icRfqHeader = icRfqHeader;
        this.vendorId = vendorId;
        this.questionTitle = questionTitle;
        this.questionText = questionText;
        this.datePosted = datePosted;
        this.timePosted = timePosted;
        this.responseText = responseText;
        this.responseDate = responseDate;
        this.responseTime = responseTime;
    }

    /** default constructor */
    public VendorQuestion() {
    }

    /** minimal constructor */
    public VendorQuestion(java.math.BigDecimal icVendorQuestion) {
        this.icVendorQuestion = icVendorQuestion;
    }

    public  java.math.BigDecimal getIcVendorQuestion() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.icVendorQuestion);
    }

    public void setIcVendorQuestion(java.math.BigDecimal icVendorQuestion) {
        this.icVendorQuestion = icVendorQuestion;
    }

    public java.math.BigDecimal getIcRfqHeader() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.icRfqHeader);
    }

    public void setIcRfqHeader(java.math.BigDecimal icRfqHeader) {
        this.icRfqHeader = icRfqHeader;
    }

    public java.lang.String getVendorId() {
		return (java.lang.String)HiltonUtility.ckNull(this.vendorId).trim();
    }

    public void setVendorId(java.lang.String vendorId) {
		if (!HiltonUtility.isEmpty(vendorId) && vendorId.length() > 15) {
			vendorId = vendorId.substring(0, 15);
		}
		this.vendorId = vendorId;
    }

    public java.lang.String getQuestionTitle() {
		return (java.lang.String)HiltonUtility.ckNull(this.questionTitle).trim();
    }

    public void setQuestionTitle(java.lang.String questionTitle) {
		if (!HiltonUtility.isEmpty(questionTitle) && questionTitle.length() > 60) {
			questionTitle = questionTitle.substring(0, 60);
		}
		this.questionTitle = questionTitle;
    }
    
    public java.lang.String getQuestionText() {
		return (java.lang.String)HiltonUtility.ckNull(this.questionText).trim();
    }

    public void setQuestionText(java.lang.String questionText) {
		if (!HiltonUtility.isEmpty(questionText) && questionText.length() > 255) {
			questionText = questionText.substring(0, 255);
		}
		this.questionText = questionText;
    }

    public java.util.Date getDatePosted() {
        return this.datePosted;
    }

    public void setDatePosted(java.util.Date datePosted) {
        this.datePosted = datePosted;
    }

    public java.lang.String getTimePosted() {
		return (java.lang.String)HiltonUtility.ckNull(this.timePosted).trim();
    }

    public void setTimePosted(java.lang.String timePosted) {
		if (!HiltonUtility.isEmpty(timePosted) && timePosted.length() > 8) {
			timePosted = timePosted.substring(0, 8);
		}
		this.timePosted = timePosted;
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

    public java.util.Date getResponseDate() {
        return this.responseDate;
    }

    public void setResponseDate(java.util.Date responseDate) {
        this.responseDate = responseDate;
    }

    public java.lang.String getResponseTime() {
		return (java.lang.String)HiltonUtility.ckNull(this.responseTime).trim();
    }

    public void setResponseTime(java.lang.String responseTime) {
		if (!HiltonUtility.isEmpty(responseTime) && responseTime.length() > 8) {
			responseTime = responseTime.substring(0, 8);
		}
		this.responseTime = responseTime;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("icVendorQuestion", getIcVendorQuestion())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof VendorQuestion) ) return false;
        VendorQuestion castOther = (VendorQuestion) other;
        return new EqualsBuilder()
            .append(this.getIcVendorQuestion(), castOther.getIcVendorQuestion())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getIcVendorQuestion())
            .toHashCode();
    }

}
