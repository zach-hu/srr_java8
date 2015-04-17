package com.tsa.puridiom.entity;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class InvManufacturer implements Serializable {

    /** identifier field */
    private java.math.BigDecimal icManufacturer;

    /** nullable persistent field */
    private String itemNumber;

    /** nullable persistent field */
    private String vendorId;

    /** nullable persistent field */
    private String partNumber;

    /** nullable persistent field */
    private String notes;

    /** nullable persistent field */
    private java.util.Date dateEntered;

    /** nullable persistent field */
    private String owner;

    /** full constructor */
    public InvManufacturer(java.math.BigDecimal icManufacturer, java.lang.String itemNumber, java.lang.String vendorId, java.lang.String partNumber, java.lang.String notes, java.util.Date dateEntered, java.lang.String owner) {
        this.icManufacturer = icManufacturer;
        this.itemNumber = itemNumber;
        this.vendorId = vendorId;
        this.partNumber = partNumber;
        this.notes = notes;
        this.dateEntered = dateEntered;
        this.owner = owner;
    }

    /** default constructor */
    public InvManufacturer() {
    }

    /** minimal constructor */
    public InvManufacturer(java.math.BigDecimal icManufacturer) {
        this.icManufacturer = icManufacturer;
    }

    public java.math.BigDecimal getIcManufacturer() {
        return this.icManufacturer;
    }

    public void setIcManufacturer(java.math.BigDecimal icManufacturer) {
        this.icManufacturer = icManufacturer;
    }

    public java.lang.String getItemNumber() {
        return this.itemNumber;
    }

    public void setItemNumber(java.lang.String itemNumber) {
        this.itemNumber = itemNumber;
    }

    public java.lang.String getVendorId() {
        return this.vendorId;
    }

    public void setVendorId(java.lang.String vendorId) {
        this.vendorId = vendorId;
    }

    public java.lang.String getPartNumber() {
        return this.partNumber;
    }

    public void setPartNumber(java.lang.String partNumber) {
        this.partNumber = partNumber;
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
            .append("icManufacturer", getIcManufacturer())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof InvManufacturer) ) return false;
        InvManufacturer castOther = (InvManufacturer) other;
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
