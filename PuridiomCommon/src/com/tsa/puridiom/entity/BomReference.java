package com.tsa.puridiom.entity;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class BomReference implements Serializable {

    /** identifier field */
    private java.math.BigDecimal icReference;

    /** nullable persistent field */
    private String parentItem;

    /** nullable persistent field */
    private String componentItem;

    /** nullable persistent field */
    private java.math.BigDecimal icComponent;

    /** nullable persistent field */
    private String referenceId;

    /** nullable persistent field */
    private java.math.BigDecimal refNo;

    /** nullable persistent field */
    private java.math.BigDecimal qty;

    /** nullable persistent field */
    private String methodId;

    /** nullable persistent field */
    private String stageId;

    /** nullable persistent field */
    private java.math.BigDecimal icRouting;

    /** nullable persistent field */
    private java.util.Date dateEntered;

    /** nullable persistent field */
    private String owner;

    /** full constructor */
    public BomReference(java.math.BigDecimal icReference, java.lang.String parentItem, java.lang.String componentItem, java.math.BigDecimal icComponent, java.lang.String referenceId, java.math.BigDecimal refNo, java.math.BigDecimal qty, java.lang.String methodId, java.lang.String stageId, java.math.BigDecimal icRouting, java.util.Date dateEntered, java.lang.String owner) {
        this.icReference = icReference;
        this.parentItem = parentItem;
        this.componentItem = componentItem;
        this.icComponent = icComponent;
        this.referenceId = referenceId;
        this.refNo = refNo;
        this.qty = qty;
        this.methodId = methodId;
        this.stageId = stageId;
        this.icRouting = icRouting;
        this.dateEntered = dateEntered;
        this.owner = owner;
    }

    /** default constructor */
    public BomReference() {
    }

    /** minimal constructor */
    public BomReference(java.math.BigDecimal icReference) {
        this.icReference = icReference;
    }

    public java.math.BigDecimal getIcReference() {
        return this.icReference;
    }

    public void setIcReference(java.math.BigDecimal icReference) {
        this.icReference = icReference;
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

    public java.math.BigDecimal getIcComponent() {
        return this.icComponent;
    }

    public void setIcComponent(java.math.BigDecimal icComponent) {
        this.icComponent = icComponent;
    }

    public java.lang.String getReferenceId() {
        return this.referenceId;
    }

    public void setReferenceId(java.lang.String referenceId) {
        this.referenceId = referenceId;
    }

    public java.math.BigDecimal getRefNo() {
        return this.refNo;
    }

    public void setRefNo(java.math.BigDecimal refNo) {
        this.refNo = refNo;
    }

    public java.math.BigDecimal getQty() {
        return this.qty;
    }

    public void setQty(java.math.BigDecimal qty) {
        this.qty = qty;
    }

    public java.lang.String getMethodId() {
        return this.methodId;
    }

    public void setMethodId(java.lang.String methodId) {
        this.methodId = methodId;
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
            .append("icReference", getIcReference())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof BomReference) ) return false;
        BomReference castOther = (BomReference) other;
        return new EqualsBuilder()
            .append(this.getIcReference(), castOther.getIcReference())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getIcReference())
            .toHashCode();
    }

}
