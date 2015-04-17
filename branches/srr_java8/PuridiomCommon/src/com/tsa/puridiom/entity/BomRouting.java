package com.tsa.puridiom.entity;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class BomRouting implements Serializable {

    /** identifier field */
    private java.math.BigDecimal icRouting;

    /** identifier field */
    private java.math.BigDecimal icMethod;

    /** nullable persistent field */
    private String parentItem;

    /** nullable persistent field */
    private String componentItem;

    /** nullable persistent field */
    private String stageId;

    /** nullable persistent field */
    private java.math.BigDecimal stageSeq;

    /** nullable persistent field */
    private String description;

    /** nullable persistent field */
    private String workCenterId;

    /** nullable persistent field */
    private String machineId;

    /** nullable persistent field */
    private String respons;

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
    private String backflush;

    /** nullable persistent field */
    private java.math.BigDecimal persons;

    /** nullable persistent field */
    private java.math.BigDecimal ccost;

    /** nullable persistent field */
    private String unitOfMeasure;

    /** nullable persistent field */
    private String poNotes;

    /** nullable persistent field */
    private String notes;

    /** nullable persistent field */
    private java.util.Date dateEntered;

    /** nullable persistent field */
    private String owner;

    /** full constructor */
    public BomRouting(java.math.BigDecimal icRouting, java.math.BigDecimal icMethod,java.lang.String parentItem, java.lang.String componentItem, java.lang.String stageId, java.math.BigDecimal stageSeq, java.lang.String description, java.lang.String workCenterId, java.lang.String machineId, java.lang.String respons, java.math.BigDecimal utilization, java.math.BigDecimal qtyDays, java.math.BigDecimal setupHours, java.math.BigDecimal partsHour, java.math.BigDecimal timePart, java.lang.String vendorName, java.math.BigDecimal leadTime, java.lang.String outside, java.lang.String backflush, java.math.BigDecimal persons, java.math.BigDecimal ccost, java.lang.String unitOfMeasure, java.lang.String poNotes, java.lang.String notes, java.util.Date dateEntered, java.lang.String owner) {
        this.icRouting = icRouting;
        this.icMethod = icMethod;
        this.parentItem = parentItem;
        this.componentItem = componentItem;
        this.stageId = stageId;
        this.stageSeq = stageSeq;
        this.description = description;
        this.workCenterId = workCenterId;
        this.machineId = machineId;
        this.respons = respons;
        this.utilization = utilization;
        this.qtyDays = qtyDays;
        this.setupHours = setupHours;
        this.partsHour = partsHour;
        this.timePart = timePart;
        this.vendorName = vendorName;
        this.leadTime = leadTime;
        this.outside = outside;
        this.backflush = backflush;
        this.persons = persons;
        this.ccost = ccost;
        this.unitOfMeasure = unitOfMeasure;
        this.poNotes = poNotes;
        this.notes = notes;
        this.dateEntered = dateEntered;
        this.owner = owner;
    }

    /** default constructor */
    public BomRouting() {
    }

    /** minimal constructor */
    public BomRouting(java.math.BigDecimal icRouting) {
        this.icRouting = icRouting;
    }

    public java.math.BigDecimal getIcRouting() {
        return this.icRouting;
    }

    public void setIcRouting(java.math.BigDecimal icRouting) {
        this.icRouting = icRouting;
    }

    public java.math.BigDecimal getIcMethod() {
        return this.icMethod;
    }

    public void setIcMethod(java.math.BigDecimal icMethod) {
        this.icMethod = icMethod;
    }

    public java.lang.String getParentItem() {
        return this.parentItem;
    }

    public void setParentItem(java.lang.String parentItem) {
        this.parentItem = parentItem;
    }

    public java.lang.String getComponentItem() {
        return this.componentItem;
    }

    public void setComponentItem(java.lang.String componentItem) {
        this.componentItem = componentItem;
    }

    public java.lang.String getStageId() {
        return this.stageId;
    }

    public void setStageId(java.lang.String stageId) {
        this.stageId = stageId;
    }

    public java.math.BigDecimal getStageSeq() {
        return this.stageSeq;
    }

    public void setStageSeq(java.math.BigDecimal stageSeq) {
        this.stageSeq = stageSeq;
    }

    public java.lang.String getDescription() {
        return this.description;
    }

    public void setDescription(java.lang.String description) {
        this.description = description;
    }

    public java.lang.String getWorkCenterId() {
        return this.workCenterId;
    }

    public void setWorkCenterId(java.lang.String workCenterId) {
        this.workCenterId = workCenterId;
    }

    public java.lang.String getMachineId() {
        return this.machineId;
    }

    public void setMachineId(java.lang.String machineId) {
        this.machineId = machineId;
    }

    public java.lang.String getRespons() {
        return this.respons;
    }

    public void setRespons(java.lang.String respons) {
        this.respons = respons;
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
        return this.vendorName;
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
        return this.outside;
    }

    public void setOutside(java.lang.String outside) {
        this.outside = outside;
    }

    public java.lang.String getBackflush() {
        return this.backflush;
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
        return this.unitOfMeasure;
    }

    public void setUnitOfMeasure(java.lang.String unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
    }

    public java.lang.String getPoNotes() {
        return this.poNotes;
    }

    public void setPoNotes(java.lang.String poNotes) {
        this.poNotes = poNotes;
    }

    public java.lang.String getNotes() {
        return this.notes;
    }

    public void setNotes(java.lang.String notes) {
        this.notes = notes;
    }

    public java.util.Date getDateEntered() {
        return this.dateEntered;
    }

    public void setDateEntered(java.util.Date dateEntered) {
        this.dateEntered = dateEntered;
    }

    public java.lang.String getOwner() {
        return this.owner;
    }

    public void setOwner(java.lang.String owner) {
        this.owner = owner;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("icRouting", getIcRouting())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof BomRouting) ) return false;
        BomRouting castOther = (BomRouting) other;
        return new EqualsBuilder()
            .append(this.getIcRouting(), castOther.getIcRouting())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getIcRouting())
            .toHashCode();
    }

}
