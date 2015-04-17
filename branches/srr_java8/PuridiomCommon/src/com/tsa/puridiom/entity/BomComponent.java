package com.tsa.puridiom.entity;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class BomComponent implements Serializable {

    /** identifier field */
    private java.math.BigDecimal icComponent;

    /** nullable persistent field */
    private String parentItem;

    /** nullable persistent field */
    private String componentItem;

    /** nullable persistent field */
    private String componentType;

    /** nullable persistent field */
    private java.math.BigDecimal bomLine;

    /** nullable persistent field */
    private String description;

    /** nullable persistent field */
    private String unitOfMeasure;

    /** nullable persistent field */
    private java.math.BigDecimal usageQty;

    /** nullable persistent field */
    private java.math.BigDecimal estCost;

    /** nullable persistent field */
    private java.math.BigDecimal scrapPerc;

    /** nullable persistent field */
    private java.util.Date fromDate;

    /** nullable persistent field */
    private java.util.Date thruDate;

    /** nullable persistent field */
    private String methodId;

    /** nullable persistent field */
    private String invtype;

    /** nullable persistent field */
    private String notes;

    /** nullable persistent field */
    private String stageId;

    /** nullable persistent field */
    private String descType;

    /** nullable persistent field */
    private String fixOrvar;

    /** nullable persistent field */
    private String taxAc;

    /** nullable persistent field */
    private String inOut;

    /** nullable persistent field */
    private java.math.BigDecimal unitPrice;

    /** nullable persistent field */
    private java.math.BigDecimal unitCost;

    /** nullable persistent field */
    private String primaryRes;

    /** nullable persistent field */
    private String operName;

    /** nullable persistent field */
    private String featureSl;

    /** nullable persistent field */
    private java.math.BigDecimal costRatio;

    /** nullable persistent field */
    private java.util.Date dateEntered;

    /** nullable persistent field */
    private String owner;

    /** full constructor */
    public BomComponent(java.math.BigDecimal icComponent, java.lang.String parentItem, java.lang.String componentItem, java.lang.String componentType, java.math.BigDecimal bomLine, java.lang.String description, java.lang.String unitOfMeasure, java.math.BigDecimal usageQty, java.math.BigDecimal estCost, java.math.BigDecimal scrapPerc, java.util.Date fromDate, java.util.Date thruDate, java.lang.String methodId, java.lang.String invtype, java.lang.String notes, java.lang.String stageId, java.lang.String descType, java.lang.String fixOrvar, java.lang.String taxAc, java.lang.String inOut, java.math.BigDecimal unitPrice, java.math.BigDecimal unitCost, java.lang.String primaryRes, java.lang.String operName, java.lang.String featureSl, java.math.BigDecimal costRatio, java.util.Date dateEntered, java.lang.String owner) {
        this.icComponent = icComponent;
        this.parentItem = parentItem;
        this.componentItem = componentItem;
        this.componentType = componentType;
        this.bomLine = bomLine;
        this.description = description;
        this.unitOfMeasure = unitOfMeasure;
        this.usageQty = usageQty;
        this.estCost = estCost;
        this.scrapPerc = scrapPerc;
        this.fromDate = fromDate;
        this.thruDate = thruDate;
        this.methodId = methodId;
        this.invtype = invtype;
        this.notes = notes;
        this.stageId = stageId;
        this.descType = descType;
        this.fixOrvar = fixOrvar;
        this.taxAc = taxAc;
        this.inOut = inOut;
        this.unitPrice = unitPrice;
        this.unitCost = unitCost;
        this.primaryRes = primaryRes;
        this.operName = operName;
        this.featureSl = featureSl;
        this.costRatio = costRatio;
        this.dateEntered = dateEntered;
        this.owner = owner;
    }

    /** default constructor */
    public BomComponent() {
    }

    /** minimal constructor */
    public BomComponent(java.math.BigDecimal icComponent) {
        this.icComponent = icComponent;
    }

    public java.math.BigDecimal getIcComponent() {
        return this.icComponent;
    }

    public void setIcComponent(java.math.BigDecimal icComponent) {
        this.icComponent = icComponent;
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

    public java.lang.String getComponentType() {
        return this.componentType;
    }

    public void setComponentType(java.lang.String componentType) {
        this.componentType = componentType;
    }

    public java.math.BigDecimal getBomLine() {
        return this.bomLine;
    }

    public void setBomLine(java.math.BigDecimal bomLine) {
        this.bomLine = bomLine;
    }

    public java.lang.String getDescription() {
        return this.description;
    }

    public void setDescription(java.lang.String description) {
        this.description = description;
    }

    public java.lang.String getUnitOfMeasure() {
        return this.unitOfMeasure;
    }

    public void setUnitOfMeasure(java.lang.String unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
    }

    public java.math.BigDecimal getUsageQty() {
        return this.usageQty;
    }

    public void setUsageQty(java.math.BigDecimal usageQty) {
        this.usageQty = usageQty;
    }

    public java.math.BigDecimal getEstCost() {
        return this.estCost;
    }

    public void setEstCost(java.math.BigDecimal estCost) {
        this.estCost = estCost;
    }

    public java.math.BigDecimal getScrapPerc() {
        return this.scrapPerc;
    }

    public void setScrapPerc(java.math.BigDecimal scrapPerc) {
        this.scrapPerc = scrapPerc;
    }

    public java.util.Date getFromDate() {
        return this.fromDate;
    }

    public void setFromDate(java.util.Date fromDate) {
        this.fromDate = fromDate;
    }

    public java.util.Date getThruDate() {
        return this.thruDate;
    }

    public void setThruDate(java.util.Date thruDate) {
        this.thruDate = thruDate;
    }

    public java.lang.String getMethodId() {
        return this.methodId;
    }

    public void setMethodId(java.lang.String methodId) {
        this.methodId = methodId;
    }

    public java.lang.String getInvtype() {
        return this.invtype;
    }

    public void setInvtype(java.lang.String invtype) {
        this.invtype = invtype;
    }

    public java.lang.String getNotes() {
        return this.notes;
    }

    public void setNotes(java.lang.String notes) {
        this.notes = notes;
    }

    public java.lang.String getStageId() {
        return this.stageId;
    }

    public void setStageId(java.lang.String stageId) {
        this.stageId = stageId;
    }

    public java.lang.String getDescType() {
        return this.descType;
    }

    public void setDescType(java.lang.String descType) {
        this.descType = descType;
    }

    public java.lang.String getFixOrvar() {
        return this.fixOrvar;
    }

    public void setFixOrvar(java.lang.String fixOrvar) {
        this.fixOrvar = fixOrvar;
    }

    public java.lang.String getTaxAc() {
        return this.taxAc;
    }

    public void setTaxAc(java.lang.String taxAc) {
        this.taxAc = taxAc;
    }

    public java.lang.String getInOut() {
        return this.inOut;
    }

    public void setInOut(java.lang.String inOut) {
        this.inOut = inOut;
    }

    public java.math.BigDecimal getUnitPrice() {
        return this.unitPrice;
    }

    public void setUnitPrice(java.math.BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public java.math.BigDecimal getUnitCost() {
        return this.unitCost;
    }

    public void setUnitCost(java.math.BigDecimal unitCost) {
        this.unitCost = unitCost;
    }

    public java.lang.String getPrimaryRes() {
        return this.primaryRes;
    }

    public void setPrimaryRes(java.lang.String primaryRes) {
        this.primaryRes = primaryRes;
    }

    public java.lang.String getOperName() {
        return this.operName;
    }

    public void setOperName(java.lang.String operName) {
        this.operName = operName;
    }

    public java.lang.String getFeatureSl() {
        return this.featureSl;
    }

    public void setFeatureSl(java.lang.String featureSl) {
        this.featureSl = featureSl;
    }

    public java.math.BigDecimal getCostRatio() {
        return this.costRatio;
    }

    public void setCostRatio(java.math.BigDecimal costRatio) {
        this.costRatio = costRatio;
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
            .append("icComponent", getIcComponent())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof BomComponent) ) return false;
        BomComponent castOther = (BomComponent) other;
        return new EqualsBuilder()
            .append(this.getIcComponent(), castOther.getIcComponent())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getIcComponent())
            .toHashCode();
    }

}
