package com.tsa.puridiom.entity;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.tsa.puridiom.common.utility.HiltonUtility;

/** @author Hibernate CodeGenerator */
public class InvTask implements Serializable {

    /** identifier field */
    private String taskId;

    /** nullable persistent field */
    private java.math.BigDecimal refNo;

    /** nullable persistent field */
    private String stageId;

    /** nullable persistent field */
    private String taskType;

    /** nullable persistent field */
    private String description;

    /** nullable persistent field */
    private String notes;

    /** nullable persistent field */
    private java.util.Date dateEntered;

    /** nullable persistent field */
    private java.util.Date dateExpires;

    /** nullable persistent field */
    private String owner;

    /** nullable persistent field */
    private String status;

    /** full constructor */
    public InvTask(java.lang.String taskId, java.math.BigDecimal refNo, java.lang.String stageId, java.lang.String taskType, java.lang.String description, java.lang.String notes, java.util.Date dateEntered, java.util.Date dateExpires, java.lang.String owner, java.lang.String status) {
        this.taskId = taskId;
        this.refNo = refNo;
        this.stageId = stageId;
        this.taskType = taskType;
        this.description = description;
        this.notes = notes;
        this.dateEntered = dateEntered;
        this.dateExpires = dateExpires;
        this.owner = owner;
        this.status = status;
    }

    /** default constructor */
    public InvTask() {
    }

    /** minimal constructor */
    public InvTask(java.lang.String taskId) {
        this.taskId = taskId;
    }

    public java.lang.String getTaskId() {
        return (java.lang.String)HiltonUtility.ckNull(this.taskId).trim();
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
        return (java.lang.String)HiltonUtility.ckNull(this.stageId).trim();
    }

    public void setStageId(java.lang.String stageId) {
        this.stageId = stageId;
    }

    public java.lang.String getTaskType() {
        return (java.lang.String)HiltonUtility.ckNull(this.taskType).trim();
    }

    public void setTaskType(java.lang.String taskType) {
        this.taskType = taskType;
    }

    public java.lang.String getDescription() {
        return (java.lang.String)HiltonUtility.ckNull(this.description).trim();
    }

    public void setDescription(java.lang.String description) {
        this.description = description;
    }

    public java.lang.String getNotes() {
        return (java.lang.String)HiltonUtility.ckNull(this.notes).trim();
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
            .append("taskId", getTaskId())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof InvTask) ) return false;
        InvTask castOther = (InvTask) other;
        return new EqualsBuilder()
            .append(this.getTaskId(), castOther.getTaskId())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getTaskId())
            .toHashCode();
    }

}
