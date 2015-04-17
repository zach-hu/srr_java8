package com.tsa.puridiom.entity;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class ReportUser implements Serializable {

    /** identifier field */
    private java.math.BigDecimal icReportUser;

    /** nullable persistent field */
    private java.math.BigDecimal icReportQueue;

    /** nullable persistent field */
    private String userId;

    /** nullable persistent field */
    private String docTitle;

    /** nullable persistent field */
    private String docFilename;

    /** nullable persistent field */
    private String dateSent;

    /** nullable persistent field */
    private String timeSent;

    /** nullable persistent field */
    private String publicView;

    /** full constructor */
    public ReportUser(java.math.BigDecimal icReportUser, java.math.BigDecimal icReportQueue, java.lang.String userId, java.lang.String docTitle, java.lang.String docFilename, java.lang.String dateSent, java.lang.String timeSent, java.lang.String publicView) {
    	this.icReportUser = icReportUser;
        this.icReportQueue = icReportQueue;
        this.userId = userId;
        this.docTitle = docTitle;
        this.docFilename = docFilename;
        this.dateSent = dateSent;
        this.timeSent = timeSent;
        this.publicView = publicView;
    }

    /** default constructor */
    public ReportUser() {
    }

    public java.math.BigDecimal getIcReportUser() {
        return this.icReportUser;
    }

    public void setIcReportUser(java.math.BigDecimal icReportUser) {
        this.icReportUser = icReportUser;
    }

    public java.math.BigDecimal getIcReportQueue() {
        return this.icReportQueue;
    }

    public void setIcReportQueue(java.math.BigDecimal icReportQueue) {
        this.icReportQueue = icReportQueue;
    }

    public java.lang.String getUserId() {
        return this.userId;
    }

    public void setUserId(java.lang.String userId) {
        this.userId = userId;
    }

    public java.lang.String getDocTitle() {
        return this.docTitle;
    }

    public void setDocTitle(java.lang.String docTitle) {
        this.docTitle = docTitle;
    }

    public java.lang.String getDocFilename() {
        return this.docFilename;
    }

    public void setDocFilename(java.lang.String docFilename) {
        this.docFilename = docFilename;
    }

    public java.lang.String getDateSent() {
        return this.dateSent;
    }

    public void setDateSent(java.lang.String dateSent) {
        this.dateSent = dateSent;
    }

    public java.lang.String getTimeSent() {
        return this.timeSent;
    }

    public void setTimeSent(java.lang.String timeSent) {
        this.timeSent = timeSent;
    }

    public java.lang.String getPublicView() {
        return this.publicView;
    }

    public void setPublicView(java.lang.String publicView) {
        this.publicView = publicView;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("icReportUser", getIcReportUser())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof ReportUser) ) return false;
        ReportUser castOther = (ReportUser) other;
        return new EqualsBuilder()
            .append(this.getIcReportUser(), castOther.getIcReportUser())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getIcReportUser())
            .toHashCode();
    }

}
