package com.tsa.puridiom.entity;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.tsa.puridiom.common.utility.HiltonUtility;

/** @author Hibernate CodeGenerator */
public class InvMethod implements Serializable {

    /** identifier field */
    private String methodId;

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
    public InvMethod(java.lang.String methodId, java.lang.String description, java.lang.String notes, java.util.Date dateEntered, java.util.Date dateExpires, java.lang.String owner, java.lang.String status) {
        this.methodId = methodId;
        this.description = description;
        this.notes = notes;
        this.dateEntered = dateEntered;
        this.dateExpires = dateExpires;
        this.owner = owner;
        this.status = status;
    }

    /** default constructor */
    public InvMethod() {
    }

    /** minimal constructor */
    public InvMethod(java.lang.String methodId) {
        this.methodId = methodId;
    }

    public java.lang.String getMethodId() {
        return (java.lang.String)HiltonUtility.ckNull(this.methodId).trim();
    }

    public void setMethodId(java.lang.String methodId) {
        this.methodId = methodId;
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
            .append("methodId", getMethodId())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof InvMethod) ) return false;
        InvMethod castOther = (InvMethod) other;
        return new EqualsBuilder()
            .append(this.getMethodId(), castOther.getMethodId())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getMethodId())
            .toHashCode();
    }

}
