package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class CatalogPriceBrkPK implements Serializable {

    /** identifier field */
    private String catalogId;

    /** identifier field */
    private String itemNumber;

    /** identifier field */
    private java.math.BigDecimal sequence;

    /** full constructor */
    public CatalogPriceBrkPK(java.lang.String catalogId, java.lang.String itemNumber, java.math.BigDecimal sequence) {
        this.catalogId = catalogId;
        this.itemNumber = itemNumber;
        this.sequence = sequence;
    }

    /** default constructor */
    public CatalogPriceBrkPK() {
    }

    public java.lang.String getCatalogId() {
		return this.catalogId;
    }

    public void setCatalogId(java.lang.String catalogId) {
        this.catalogId = catalogId;
    }

    public java.lang.String getItemNumber() {
		return this.itemNumber;
    }

    public void setItemNumber(java.lang.String itemNumber) {
        this.itemNumber = itemNumber;
    }

    public java.math.BigDecimal getSequence() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.sequence);
    }

    public void setSequence(java.math.BigDecimal sequence) {
        this.sequence = sequence;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("catalogId", getCatalogId())
            .append("itemNumber", getItemNumber())
            .append("sequence", getSequence())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof CatalogPriceBrkPK) ) return false;
        CatalogPriceBrkPK castOther = (CatalogPriceBrkPK) other;
        return new EqualsBuilder()
            .append(this.getCatalogId(), castOther.getCatalogId())
            .append(this.getItemNumber(), castOther.getItemNumber())
            .append(this.getSequence(), castOther.getSequence())
            .isEquals();
    }

    public int hashCode() 
    {
        return new HashCodeBuilder()
            .append(getCatalogId())
            .append(getItemNumber())
            .append(getSequence())
            .toHashCode();
    }
}
