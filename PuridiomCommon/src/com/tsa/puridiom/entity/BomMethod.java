package com.tsa.puridiom.entity;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class BomMethod implements Serializable {

    /** identifier field */
    private java.math.BigDecimal icMethod;

    /** nullable persistent field */
    private String parentItem;

    /** nullable persistent field */
    private String componentItem;

    /** nullable persistent field */
    private String methodId;

    /** nullable persistent field */
    private java.math.BigDecimal batchSize;

    /** nullable persistent field */
    private String unitOfMeasure;

    /** nullable persistent field */
    private String description;

    /** nullable persistent field */
    private String notes;

    /** nullable persistent field */
    private java.util.Date dateEntered;

    /** nullable persistent field */
    private String owner;

    /** full constructor */
    public BomMethod(java.math.BigDecimal icMethod, java.lang.String parentItem, java.lang.String componentItem, java.lang.String methodId, java.math.BigDecimal batchSize, java.lang.String unitOfMeasure, java.lang.String description, java.lang.String notes, java.util.Date dateEntered, java.lang.String owner) {
        this.icMethod = icMethod;
        this.parentItem = parentItem;
        this.componentItem = componentItem;
        this.methodId = methodId;
        this.batchSize = batchSize;
        this.unitOfMeasure = unitOfMeasure;
        this.description = description;
        this.notes = notes;
        this.dateEntered = dateEntered;
        this.owner = owner;
    }

    /** default constructor */
    public BomMethod() {
    }

    /** minimal constructor */
    public BomMethod(java.math.BigDecimal icMethod) {
        this.icMethod = icMethod;
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

    public java.lang.String getMethodId() {
        return this.methodId;
    }

    public void setMethodId(java.lang.String methodId) {
        this.methodId = methodId;
    }

    public java.math.BigDecimal getBatchSize() {
        return this.batchSize;
    }

    public void setBatchSize(java.math.BigDecimal batchSize) {
        this.batchSize = batchSize;
    }

    public java.lang.String getUnitOfMeasure() {
        return this.unitOfMeasure;
    }

    public void setUnitOfMeasure(java.lang.String unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
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
            .append("icMethod", getIcMethod())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof BomMethod) ) return false;
        BomMethod castOther = (BomMethod) other;
        return new EqualsBuilder()
            .append(this.getIcMethod(), castOther.getIcMethod())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getIcMethod())
            .toHashCode();
    }

}
