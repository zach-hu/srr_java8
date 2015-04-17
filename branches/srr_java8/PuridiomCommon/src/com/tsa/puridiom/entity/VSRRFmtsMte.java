package com.tsa.puridiom.entity;

import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;

public class VSRRFmtsMte implements Serializable {

    /** nullable persistent field */
    private String mteNo;

    /** nullable persistent field */
    private String description;

    /** nullable persistent field */
    private java.util.Date calDueDate;

    /** nullable persistent field */
    private java.util.Date calDate;

    /** nullable persistent field */
    private String activeFl;

    /** nullable persistent field */
    private String createdBy;

    /** nullable persistent field */
    private java.util.Date createdDt;

    /** nullable persistent field */
    private String modifiedBy;

    /** nullable persistent field */
    private java.util.Date modifiedDt;

    /** Full Constructor **/
    public VSRRFmtsMte(java.lang.String mteNo, java.lang.String description, java.util.Date calDueDate, java.util.Date calDate, java.lang.String activeFl, java.lang.String createdBy, java.util.Date createdDt, java.lang.String modifiedBy, java.util.Date modifiedDt) {
        this.mteNo = mteNo;
        this.description = description;
        this.calDueDate = calDueDate;
        this.calDate = calDate;
        this.activeFl = activeFl;
        this.createdBy = createdBy;
        this.createdDt = createdDt;
        this.modifiedBy = modifiedBy;
        this.modifiedDt = modifiedDt;
    }

    /** default constructor */
    public VSRRFmtsMte() {
    }

    public java.lang.String getMteNo() {
        return this.mteNo;
    }

    public void setMteNo(java.lang.String mteNo) {
        this.mteNo = mteNo;
    }

    public java.lang.String getDescription() {
        return this.description;
    }

    public void setDescription(java.lang.String description) {
        this.description = description;
    }

    public java.util.Date getCalDueDate() {
        return this.calDueDate;
    }

    public void setCalDueDate(java.util.Date calDueDate) {
        this.calDueDate = calDueDate;
    }

    public java.util.Date getCalDate() {
        return this.calDate;
    }

    public void setCalDate(java.util.Date calDate) {
        this.calDate = calDate;
    }

    public java.lang.String getActiveFl() {
        return this.activeFl;
    }

    public void setActiveFl(java.lang.String activeFl) {
        this.activeFl = activeFl;
    }

    public java.lang.String getCreatedBy() {
        return this.createdBy;
    }

    public void setCreatedBy(java.lang.String createdBy) {
        this.createdBy = createdBy;
    }

    public java.util.Date getCreatedDt() {
        return this.createdDt;
    }

    public void setCreatedDt(java.util.Date createdDt) {
        this.createdDt = createdDt;
    }

    public java.lang.String getModifiedBy() {
        return this.modifiedBy;
    }

    public void setModifiedBy(java.lang.String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public java.util.Date getModifiedDt() {
        return this.modifiedDt;
    }

    public void setModifiedDt(java.util.Date modifiedDt) {
        this.modifiedDt = modifiedDt;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .toString();
    }
}
