package com.tsa.puridiom.entity;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class BomTask implements Serializable {

    /** identifier field */
    private java.math.BigDecimal icTask;

    /** persistent field */
    private String taskId;

    /** nullable persistent field */
    private java.math.BigDecimal refNo;

    /** nullable persistent field */
    private String stageId;

    /** nullable persistent field */
    private java.math.BigDecimal icRouting;

    /** nullable persistent field */
    private String taskType;

    /** nullable persistent field */
    private String description;

    /** nullable persistent field */
    private String notes;

    /** nullable persistent field */
    private java.util.Date dateEntered;

    /** nullable persistent field */
    private String owner;

    /** full constructor */
    public BomTask(java.math.BigDecimal icTask, java.lang.String taskId, java.math.BigDecimal refNo, java.lang.String stageId, java.math.BigDecimal icRouting, java.lang.String taskType, java.lang.String description, java.lang.String notes, java.util.Date dateEntered, java.lang.String owner) {
        this.icTask = icTask;
        this.taskId = taskId;
        this.refNo = refNo;
        this.stageId = stageId;
        this.icRouting = icRouting;
        this.taskType = taskType;
        this.description = description;
        this.notes = notes;
        this.dateEntered = dateEntered;
        this.owner = owner;
    }

    /** default constructor */
    public BomTask() {
    }

    /** minimal constructor */
    public BomTask(java.math.BigDecimal icTask, java.lang.String taskId) {
        this.icTask = icTask;
        this.taskId = taskId;
    }

    public java.math.BigDecimal getIcTask() {
        return this.icTask;
    }

    public void setIcTask(java.math.BigDecimal icTask) {
        this.icTask = icTask;
    }

    public java.lang.String getTaskId() {
        return this.taskId;
    }

    public void setTaskId(java.lang.String taskId) {
        this.taskId = taskId;
    }

    public java.math.BigDecimal getRefNo() {
        return this.refNo;
    }

    public void setRefNo(java.math.BigDecimal refNo) {
        this.refNo = refNo;
    }

    public java.lang.String getStageId() {
        return this.stageId;
    }

    public void setStageId(java.lang.String stageId) {
        this.stageId = stageId;
    }

    public java.math.BigDecimal getIcRouting() {
        return this.icRouting;
    }

    public void setIcRouting(java.math.BigDecimal icRouting) {
        this.icRouting = icRouting;
    }

    public java.lang.String getTaskType() {
        return this.taskType;
    }

    public void setTaskType(java.lang.String taskType) {
        this.taskType = taskType;
    }

    public java.lang.String getDescription() {
        return this.description;
    }

    public void setDescription(java.lang.String description) {
        this.description = description;
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
            .append("icTask", getIcTask())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof BomTask) ) return false;
        BomTask castOther = (BomTask) other;
        return new EqualsBuilder()
            .append(this.getIcTask(), castOther.getIcTask())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getIcTask())
            .toHashCode();
    }

}
