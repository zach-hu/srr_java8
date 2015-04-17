package com.tsa.puridiom.entity;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.tsa.puridiom.common.utility.HiltonUtility;

/** @author Hibernate CodeGenerator */
public class InvMachine implements Serializable {

    /** identifier field */
    private String machineId;

    /** nullable persistent field */
    private String description;

    /** nullable persistent field */
    private String workCenterId;

    /** nullable persistent field */
    private String assetId;

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
    public InvMachine(java.lang.String machineId, java.lang.String description, java.lang.String workCenterId, java.lang.String assetId, java.lang.String notes, java.util.Date dateEntered, java.util.Date dateExpires, java.lang.String owner, java.lang.String status) {
        this.machineId = machineId;
        this.description = description;
        this.workCenterId = workCenterId;
        this.assetId = assetId;
        this.notes = notes;
        this.dateEntered = dateEntered;
        this.dateExpires = dateExpires;
        this.owner = owner;
        this.status = status;
    }

    /** default constructor */
    public InvMachine() {
    }

    /** minimal constructor */
    public InvMachine(java.lang.String machineId) {
        this.machineId = machineId;
    }

    public java.lang.String getMachineId() {
        return (java.lang.String)HiltonUtility.ckNull(this.machineId).trim();
    }

    public void setMachineId(java.lang.String machineId) {
        this.machineId = machineId;
    }

    public java.lang.String getDescription() {
        return (java.lang.String)HiltonUtility.ckNull(this.description).trim();
    }

    public void setDescription(java.lang.String description) {
        this.description = description;
    }

    public java.lang.String getWorkCenterId() {
        return (java.lang.String)HiltonUtility.ckNull(this.workCenterId).trim();
    }

    public void setWorkCenterId(java.lang.String workCenterId) {
        this.workCenterId = workCenterId;
    }

    public java.lang.String getAssetId() {
        return (java.lang.String)HiltonUtility.ckNull(this.assetId).trim();
    }

    public void setAssetId(java.lang.String assetId) {
        this.assetId = assetId;
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
            .append("machineId", getMachineId())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof InvMachine) ) return false;
        InvMachine castOther = (InvMachine) other;
        return new EqualsBuilder()
            .append(this.getMachineId(), castOther.getMachineId())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getMachineId())
            .toHashCode();
    }

}
