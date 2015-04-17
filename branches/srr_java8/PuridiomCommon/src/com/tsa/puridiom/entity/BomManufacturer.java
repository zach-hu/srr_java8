package com.tsa.puridiom.entity;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class BomManufacturer implements Serializable {

    /** identifier field */
    private java.math.BigDecimal icManufacturer;

    /** persistent field */
    private String parentItem;

    /** persistent field */
    private String componentItem;

    /** persistent field */
    private java.math.BigDecimal icComponent;

    /** nullable persistent field */
    private String vendorId;

    /** nullable persistent field */
    private String vendorName ;

    /** nullable persistent field */
    private String methodId;

    /** nullable persistent field */
    private String stageId;

    /** nullable persistent field */
    private String partNumber;

    /** nullable persistent field */
    private java.util.Date dateEntered;

    /** nullable persistent field */
    private String owner;

    /** full constructor */
    public BomManufacturer(java.math.BigDecimal icManufacturer, java.lang.String parentItem, java.lang.String componentItem, java.math.BigDecimal icComponent, java.lang.String vendorId, java.lang.String vendorName, java.lang.String methodId, java.lang.String stageId, java.lang.String partNumber, java.util.Date dateEntered, java.lang.String owner) {
        this.icManufacturer = icManufacturer;
        this.parentItem = parentItem;
        this.componentItem = componentItem;
        this.icComponent = icComponent;
        this.vendorId = vendorId;
        this.vendorName = vendorName ;
        this.methodId = methodId;
        this.stageId = stageId;
        this.partNumber = partNumber;
        this.dateEntered = dateEntered;
        this.owner = owner;
    }

    /** default constructor */
    public BomManufacturer() {
    }

    /** minimal constructor */
    public BomManufacturer(java.math.BigDecimal icManufacturer, java.lang.String parentItem, java.lang.String componentItem, java.math.BigDecimal icComponent) {
        this.icManufacturer = icManufacturer;
        this.parentItem = parentItem;
        this.componentItem = componentItem;
        this.icComponent = icComponent;
    }

    public java.math.BigDecimal getIcManufacturer() {
        return this.icManufacturer;
    }

    public void setIcManufacturer(java.math.BigDecimal icManufacturer) {
        this.icManufacturer = icManufacturer;
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

    public java.lang.String getVendorId() {
        return this.vendorId;
    }

    public void setVendorId(java.lang.String vendorId) {
        this.vendorId = vendorId;
    }

    public java.lang.String getVendorName() {
        return this.vendorName;
    }

    public void setVendorName(java.lang.String vendorName) {
        this.vendorName = vendorName;
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

    public java.lang.String getPartNumber() {
        return this.partNumber;
    }

    public void setPartNumber(java.lang.String partNumber) {
        this.partNumber = partNumber;
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
            .append("icManufacturer", getIcManufacturer())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof BomManufacturer) ) return false;
        BomManufacturer castOther = (BomManufacturer) other;
        return new EqualsBuilder()
            .append(this.getIcManufacturer(), castOther.getIcManufacturer())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getIcManufacturer())
            .toHashCode();
    }

}
