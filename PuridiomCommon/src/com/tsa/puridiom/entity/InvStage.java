package com.tsa.puridiom.entity;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.tsa.puridiom.common.utility.HiltonUtility;

/** @author Hibernate CodeGenerator */
public class InvStage implements Serializable {

    /** identifier field */
    private String stageId;

    /** nullable persistent field */
    private String description;

    /** nullable persistent field */
    private String respons;

    /** nullable persistent field */
    private String notes;

    /** nullable persistent field */
    private String workCenterId;

    /** nullable persistent field */
    private java.math.BigDecimal utilization;

    /** nullable persistent field */
    private java.math.BigDecimal qtyDays;

    /** nullable persistent field */
    private java.math.BigDecimal setupHours;

    /** nullable persistent field */
    private java.math.BigDecimal partsHour;

    /** nullable persistent field */
    private java.math.BigDecimal timePart;

    /** nullable persistent field */
    private String vendorName;

    /** nullable persistent field */
    private java.math.BigDecimal leadTime;

    /** nullable persistent field */
    private String outside;

    /** nullable persistent field */
    private String descriptor;

    /** nullable persistent field */
    private String machineId;

    /** nullable persistent field */
    private String backflush;

    /** nullable persistent field */
    private java.math.BigDecimal persons;

    /** nullable persistent field */
    private java.math.BigDecimal ccost;

    /** nullable persistent field */
    private String unitOfMeasure ;

    /** nullable persistent field */
    private java.util.Date dateEntered;

    /** nullable persistent field */
    private java.util.Date dateExpires;

    /** nullable persistent field */
    private String owner;

    /** nullable persistent field */
    private String status;

    /** full constructor */
    public InvStage(java.lang.String stageId, java.lang.String description, java.lang.String respons, java.lang.String notes, java.lang.String workCenterId, java.math.BigDecimal utilization, java.math.BigDecimal qtyDays, java.math.BigDecimal setupHours, java.math.BigDecimal partsHour, java.math.BigDecimal timePart, java.lang.String vendorName, java.math.BigDecimal leadTime, java.lang.String outside, java.lang.String descriptor, java.lang.String machineId, java.lang.String backflush, java.math.BigDecimal persons, java.math.BigDecimal ccost, java.lang.String unitOfMeasure, java.util.Date dateEntered, java.util.Date dateExpires, java.lang.String owner, java.lang.String status) {
        this.stageId = stageId;
        this.description = description;
        this.respons = respons;
        this.notes = notes;
        this.workCenterId = workCenterId;
        this.utilization = utilization;
        this.qtyDays = qtyDays;
        this.setupHours = setupHours;
        this.partsHour = partsHour;
        this.timePart = timePart;
        this.vendorName = vendorName;
        this.leadTime = leadTime;
        this.outside = outside;
        this.descriptor = descriptor;
        this.machineId = machineId;
        this.backflush = backflush;
        this.persons = persons ;
        this.ccost = ccost ;
        this.unitOfMeasure = unitOfMeasure ;
        this.dateEntered = dateEntered;
        this.dateExpires = dateExpires;
        this.owner = owner;
        this.status = status;
    }

    /** default constructor */
    public InvStage() {
    }

    /** minimal constructor */
    public InvStage(java.lang.String stageId) {
        this.stageId = stageId;
    }

    public java.lang.String getStageId() {
        return (java.lang.String)HiltonUtility.ckNull(this.stageId).trim();
    }

    public void setStageId(java.lang.String stageId) {
        this.stageId = stageId;
    }

    public java.lang.String getDescription() {
        return (java.lang.String)HiltonUtility.ckNull(this.description).trim();
    }

    public void setDescription(java.lang.String description) {
        this.description = description;
    }

    public java.lang.String getRespons() {
        return (java.lang.String)HiltonUtility.ckNull(this.respons).trim();
    }

    public void setRespons(java.lang.String respons) {
        this.respons = respons;
    }

    public java.lang.String getNotes() {
        return (java.lang.String)HiltonUtility.ckNull(this.notes).trim();
    }

    public void setNotes(java.lang.String notes) {
        this.notes = notes;
    }

    public java.lang.String getWorkCenterId() {
        return (java.lang.String)HiltonUtility.ckNull(this.workCenterId).trim();
    }

    public void setWorkCenterId(java.lang.String workCenterId) {
        this.workCenterId = workCenterId;
    }

    public java.math.BigDecimal getUtilization() {
        return this.utilization;
    }

    public void setUtilization(java.math.BigDecimal utilization) {
        this.utilization = utilization;
    }

    public java.math.BigDecimal getQtyDays() {
        return this.qtyDays;
    }

    public void setQtyDays(java.math.BigDecimal qtyDays) {
        this.qtyDays = qtyDays;
    }

    public java.math.BigDecimal getSetupHours() {
        return this.setupHours;
    }

    public void setSetupHours(java.math.BigDecimal setupHours) {
        this.setupHours = setupHours;
    }

    public java.math.BigDecimal getPartsHour() {
        return this.partsHour;
    }

    public void setPartsHour(java.math.BigDecimal partsHour) {
        this.partsHour = partsHour;
    }

    public java.math.BigDecimal getTimePart() {
        return this.timePart;
    }

    public void setTimePart(java.math.BigDecimal timePart) {
        this.timePart = timePart;
    }

    public java.lang.String getVendorName() {
        return (java.lang.String)HiltonUtility.ckNull(this.vendorName).trim();
    }

    public void setVendorName(java.lang.String vendorName) {
        this.vendorName = vendorName;
    }

    public java.math.BigDecimal getLeadTime() {
        return this.leadTime;
    }

    public void setLeadTime(java.math.BigDecimal leadTime) {
        this.leadTime = leadTime;
    }

    public java.lang.String getOutside() {
        return (java.lang.String)HiltonUtility.ckNull(this.outside).trim();
    }

    public void setOutside(java.lang.String outside) {
        this.outside = outside;
    }

    public java.lang.String getDescriptor() {
        return (java.lang.String)HiltonUtility.ckNull(this.descriptor).trim();
    }

    public void setDescriptor(java.lang.String descriptor) {
        this.descriptor = descriptor;
    }

    public java.lang.String getMachineId() {
        return (java.lang.String)HiltonUtility.ckNull(this.machineId).trim();
    }

    public void setMachineId(java.lang.String machineId) {
        this.machineId = machineId;
    }

    public java.lang.String getBackflush() {
        return (java.lang.String)HiltonUtility.ckNull(this.backflush).trim();
    }

    public void setBackflush(java.lang.String backflush) {
        this.backflush = backflush;
    }

    public java.math.BigDecimal getPersons() {
        return this.persons;
    }

    public void setPersons(java.math.BigDecimal persons) {
        this.persons = persons;
    }

    public java.math.BigDecimal getCcost() {
        return this.ccost;
    }

    public void setCcost(java.math.BigDecimal ccost) {
        this.ccost = ccost;
    }

    public java.lang.String getUnitOfMeasure() {
        return (java.lang.String)HiltonUtility.ckNull(this.unitOfMeasure).trim();
    }

    public void setUnitOfMeasure(java.lang.String unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
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
            .append("stageId", getStageId())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof InvStage) ) return false;
        InvStage castOther = (InvStage) other;
        return new EqualsBuilder()
            .append(this.getStageId(), castOther.getStageId())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getStageId())
            .toHashCode();
    }

}
