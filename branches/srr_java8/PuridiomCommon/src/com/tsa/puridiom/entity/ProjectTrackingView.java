package com.tsa.puridiom.entity;

import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class ProjectTrackingView implements Serializable {

    /** nullable persistent field */
    private String fld4;

    /** nullable persistent field */
    private String poNumber;

    /** nullable persistent field */
    private String vendorName;

    /** nullable persistent field */
    private String vendorId;

    /** nullable persistent field */
    private String fld2;

    /** nullable persistent field */
    private String fld1;

    /** nullable persistent field */
    private java.math.BigDecimal awarded;

    /** nullable persistent field */
    private java.math.BigDecimal spentTodate;

    /** nullable persistent field */
    private java.math.BigDecimal allocPercent;

    /** nullable persistent field */
    private String poType;

    /** nullable persistent field */
    private String status;

    /** nullable persistent field */
    private java.util.Date poDate;

    /** nullable persistent field */
    private java.util.Date appDate;
    
    private String internalComments;

    /** full constructor */
    public ProjectTrackingView(java.lang.String fld4, java.lang.String poNumber, java.lang.String vendorName, java.lang.String vendorId, java.lang.String fld2, java.lang.String fld1, java.math.BigDecimal awarded, java.math.BigDecimal spentTodate, java.math.BigDecimal allocPercent, java.lang.String poType, java.lang.String status, java.util.Date poDate, java.util.Date appDate, java.lang.String internalComments) {
        this.fld4 = fld4;
        this.poNumber = poNumber;
        this.vendorName = vendorName;
        this.vendorId = vendorId;
        this.fld2 = fld2;
        this.fld1 = fld1;
        this.awarded = awarded;
        this.spentTodate = spentTodate;
        this.allocPercent = allocPercent;
        this.poType = poType;
        this.status = status;
        this.poDate = poDate;
        this.appDate = appDate;
        this.internalComments = internalComments;
    }

    /** default constructor */
    public ProjectTrackingView() {
    }

    public java.lang.String getFld4() {
        return this.fld4;
    }

    public void setFld4(java.lang.String fld4) {
        this.fld4 = fld4;
    }

    public java.lang.String getPoNumber() {
        return this.poNumber;
    }

    public void setPoNumber(java.lang.String poNumber) {
        this.poNumber = poNumber;
    }

    public java.lang.String getVendorName() {
        return this.vendorName;
    }

    public void setVendorName(java.lang.String vendorName) {
        this.vendorName = vendorName;
    }

    public java.lang.String getVendorId() {
        return this.vendorId;
    }

    public void setVendorId(java.lang.String vendorId) {
        this.vendorId = vendorId;
    }

    public java.lang.String getFld2() {
        return this.fld2;
    }

    public void setFld2(java.lang.String fld2) {
        this.fld2 = fld2;
    }

    public java.lang.String getFld1() {
        return this.fld1;
    }

    public void setFld1(java.lang.String fld1) {
        this.fld1 = fld1;
    }

    public java.math.BigDecimal getAwarded() {
        return this.awarded;
    }

    public void setAwarded(java.math.BigDecimal awarded) {
        this.awarded = awarded;
    }

    public java.math.BigDecimal getSpentTodate() {
        return this.spentTodate;
    }

    public void setSpentTodate(java.math.BigDecimal spentTodate) {
        this.spentTodate = spentTodate;
    }

    public java.math.BigDecimal getAllocPercent() {
        return this.allocPercent;
    }

    public void setAllocPercent(java.math.BigDecimal allocPercent) {
        this.allocPercent = allocPercent;
    }

    public java.lang.String getPoType() {
        return this.poType;
    }

    public void setPoType(java.lang.String poType) {
        this.poType = poType;
    }

    public java.lang.String getStatus() {
        return this.status;
    }

    public void setStatus(java.lang.String status) {
        this.status = status;
    }

    public java.util.Date getPoDate() {
        return this.poDate;
    }

    public void setPoDate(java.util.Date poDate) {
        this.poDate = poDate;
    }

    public java.util.Date getAppDate() {
        return this.appDate;
    }

    public void setAppDate(java.util.Date appDate) {
        this.appDate = appDate;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .toString();
    }

    public String getInternalComments()
    {
        return internalComments;
    }
    public void setInternalComments(String internalComments)
    {
        this.internalComments = internalComments;
    }
}
