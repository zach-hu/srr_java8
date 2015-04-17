package com.tsa.puridiom.entity;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class InvAlternate implements Serializable {

    /** identifier field */
    private java.math.BigDecimal icAlternate;

    /** nullable persistent field */
    private String itemNumber;

    /** nullable persistent field */
    private String altItemNumber;

    /** nullable persistent field */
    private java.util.Date dateEntered;

    /** nullable persistent field */
    private String owner;

    /** full constructor */
    public InvAlternate(java.math.BigDecimal icAlternate, java.lang.String itemNumber, java.lang.String altItemNumber, java.util.Date dateEntered, java.lang.String owner) {
        this.icAlternate = icAlternate;
        this.itemNumber = itemNumber;
        this.altItemNumber = altItemNumber;
        this.dateEntered = dateEntered;
        this.owner = owner;
    }

    /** default constructor */
    public InvAlternate() {
    }

    /** minimal constructor */
    public InvAlternate(java.math.BigDecimal icAlternate) {
        this.icAlternate = icAlternate;
    }

    public java.math.BigDecimal getIcAlternate() {
        return this.icAlternate;
    }

    public void setIcAlternate(java.math.BigDecimal icAlternate) {
        this.icAlternate = icAlternate;
    }

    public java.lang.String getItemNumber() {
        return this.itemNumber;
    }

    public void setItemNumber(java.lang.String itemNumber) {
        this.itemNumber = itemNumber;
    }

    public java.lang.String getAltItemNumber() {
        return this.altItemNumber;
    }

    public void setAltItemNumber(java.lang.String altItemNumber) {
        this.altItemNumber = altItemNumber;
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
            .append("icAlternate", getIcAlternate())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof InvAlternate) ) return false;
        InvAlternate castOther = (InvAlternate) other;
        return new EqualsBuilder()
            .append(this.getIcAlternate(), castOther.getIcAlternate())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getIcAlternate())
            .toHashCode();
    }

}
