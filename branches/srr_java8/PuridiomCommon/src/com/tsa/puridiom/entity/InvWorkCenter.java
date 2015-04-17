package com.tsa.puridiom.entity;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.tsa.puridiom.common.utility.HiltonUtility;

/** @author Hibernate CodeGenerator */
public class InvWorkCenter implements Serializable {

    /** identifier field */
    private String workCenterId;

    /** nullable persistent field */
    private String description;

    /** nullable persistent field */
    private String departmentCode;

    /** nullable persistent field */
    private String subcontract;

    /** nullable persistent field */
    private java.math.BigDecimal laborRate;

    /** nullable persistent field */
    private java.math.BigDecimal setupRate;

    /** nullable persistent field */
    private java.math.BigDecimal fixOverHead;

    /** nullable persistent field */
    private java.math.BigDecimal varOverHead;

    /** nullable persistent field */
    private java.math.BigDecimal setuphours;

    /** nullable persistent field */
    private java.math.BigDecimal perDayHours;

    /** nullable persistent field */
    private java.math.BigDecimal perJobHours;

    /** nullable persistent field */
    private java.math.BigDecimal processTime;

    /** nullable persistent field */
    private java.math.BigDecimal processItems;

    /** nullable persistent field */
    private java.math.BigDecimal bufferDays;

    /** nullable persistent field */
    private String vendorName;

    /** nullable persistent field */
    private String vendorId;

    /** nullable persistent field */
    private java.math.BigDecimal cost;

    /** nullable persistent field */
    private String unitOfMeasure;

    /** nullable persistent field */
    private java.math.BigDecimal leadTime;

    /** nullable persistent field */
    private java.util.Date dateEntered;

    /** nullable persistent field */
    private java.util.Date dateExpires;

    /** nullable persistent field */
    private String owner;

    /** nullable persistent field */
    private String status;

    /** full constructor */
    public InvWorkCenter(java.lang.String workCenterId, java.lang.String description, java.lang.String departmentCode, java.lang.String subcontract, java.math.BigDecimal laborRate, java.math.BigDecimal setupRate, java.math.BigDecimal fixOverHead, java.math.BigDecimal varOverHead, java.math.BigDecimal setuphours, java.math.BigDecimal perDayHours, java.math.BigDecimal perJobHours, java.math.BigDecimal processTime, java.math.BigDecimal processItems, java.math.BigDecimal bufferDays, java.lang.String vendorName, java.lang.String vendorId, java.math.BigDecimal cost, java.lang.String unitOfMeasure, java.math.BigDecimal leadTime, java.util.Date dateEntered, java.util.Date dateExpires, java.lang.String owner, java.lang.String status) {
        this.workCenterId = workCenterId;
        this.description = description;
        this.departmentCode = departmentCode;
        this.subcontract = subcontract;
        this.laborRate = laborRate;
        this.setupRate = setupRate;
        this.fixOverHead = fixOverHead;
        this.varOverHead = varOverHead;
        this.setuphours = setuphours;
        this.perDayHours = perDayHours;
        this.perJobHours = perJobHours;
        this.processTime = processTime;
        this.processItems = processItems;
        this.bufferDays = bufferDays;
        this.vendorName = vendorName;
        this.vendorId = vendorId;
        this.cost = cost;
        this.unitOfMeasure = unitOfMeasure;
        this.leadTime = leadTime ;
        this.dateEntered = dateEntered;
        this.dateExpires = dateExpires;
        this.owner = owner;
        this.status = status;
    }

    /** default constructor */
    public InvWorkCenter() {
    }

    /** minimal constructor */
    public InvWorkCenter(java.lang.String workCenterId) {
        this.workCenterId = workCenterId;
    }

    public java.lang.String getWorkCenterId() {
        return (java.lang.String)HiltonUtility.ckNull(this.workCenterId).trim();
    }

    public void setWorkCenterId(java.lang.String workCenterId) {
        this.workCenterId = workCenterId;
    }

    public java.lang.String getDescription() {
        return (java.lang.String)HiltonUtility.ckNull(this.description).trim();
    }

    public void setDescription(java.lang.String description) {
        this.description = description;
    }

    public java.lang.String getDepartmentCode() {
        return (java.lang.String)HiltonUtility.ckNull(this.departmentCode).trim();
    }

    public void setDepartmentCode(java.lang.String departmentCode) {
        this.departmentCode = departmentCode;
    }

    public java.lang.String getSubcontract() {
        return (java.lang.String)HiltonUtility.ckNull(this.subcontract).trim();
    }

    public void setSubcontract(java.lang.String subcontract) {
        this.subcontract = subcontract;
    }

    public java.math.BigDecimal getLaborRate() {
        return this.laborRate;
    }

    public void setLaborRate(java.math.BigDecimal laborRate) {
        this.laborRate = laborRate;
    }

    public java.math.BigDecimal getSetupRate() {
        return this.setupRate;
    }

    public void setSetupRate(java.math.BigDecimal setupRate) {
        this.setupRate = setupRate;
    }

    public java.math.BigDecimal getFixOverHead() {
        return this.fixOverHead;
    }

    public void setFixOverHead(java.math.BigDecimal fixOverHead) {
        this.fixOverHead = fixOverHead;
    }

    public java.math.BigDecimal getVarOverHead() {
        return this.varOverHead;
    }

    public void setVarOverHead(java.math.BigDecimal varOverHead) {
        this.varOverHead = varOverHead;
    }

    public java.math.BigDecimal getSetuphours() {
        return this.setuphours;
    }

    public void setSetuphours(java.math.BigDecimal setuphours) {
        this.setuphours = setuphours;
    }

    public java.math.BigDecimal getPerDayHours() {
        return this.perDayHours;
    }

    public void setPerDayHours(java.math.BigDecimal perDayHours) {
        this.perDayHours = perDayHours;
    }

    public java.math.BigDecimal getPerJobHours() {
        return this.perJobHours;
    }

    public void setPerJobHours(java.math.BigDecimal perJobHours) {
        this.perJobHours = perJobHours;
    }

    public java.math.BigDecimal getProcessTime() {
        return this.processTime;
    }

    public void setProcessTime(java.math.BigDecimal processTime) {
        this.processTime = processTime;
    }

    public java.math.BigDecimal getProcessItems() {
        return this.processItems;
    }

    public void setProcessItems(java.math.BigDecimal processItems) {
        this.processItems = processItems;
    }

    public java.math.BigDecimal getBufferDays() {
        return this.bufferDays;
    }

    public void setBufferDays(java.math.BigDecimal bufferDays) {
        this.bufferDays = bufferDays;
    }

    public java.lang.String getVendorName() {
        return (java.lang.String)HiltonUtility.ckNull(this.vendorName).trim();
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

    public java.math.BigDecimal getCost() {
        return this.cost;
    }

    public void setCost(java.math.BigDecimal cost) {
        this.cost = cost;
    }

    public java.lang.String getUnitOfMeasure() {
        return (java.lang.String)HiltonUtility.ckNull(this.unitOfMeasure).trim();
    }

    public void setUnitOfMeasure(java.lang.String unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
    }

    public java.math.BigDecimal getLeadTime() {
        return this.leadTime;
    }

    public void setLeadTime(java.math.BigDecimal leadTime) {
        this.leadTime = leadTime;
    }

    public java.util.Date getDateEntered() {
        return this.dateEntered;
    }

    public void setDateEntered(java.util.Date dateEntered) {
        this.dateEntered = dateEntered;
    }

    public java.util.Date getDateExpires() {
        return this.dateExpires;
    }

    public void setDateExpires(java.util.Date dateExpires) {
        this.dateExpires = dateExpires;
    }

    public java.lang.String getOwner() {
        return this.owner;
    }

    public void setOwner(java.lang.String owner) {
        this.owner = owner;
    }

    public java.lang.String getStatus() {
        return this.status;
    }

    public void setStatus(java.lang.String status) {
        this.status = status;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("workCenterId", getWorkCenterId())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof InvWorkCenter) ) return false;
        InvWorkCenter castOther = (InvWorkCenter) other;
        return new EqualsBuilder()
            .append(this.getWorkCenterId(), castOther.getWorkCenterId())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getWorkCenterId())
            .toHashCode();
    }

}
