package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class TaxCode implements Serializable {

    /** identifier field */
    private String taxCode;

    /** nullable persistent field */
    private String description;

    /** nullable persistent field */
    private java.math.BigDecimal taxRate;

    /** nullable persistent field */
    private java.util.Date dateEntered;

    /** nullable persistent field */
    private java.util.Date dateExpires;

    /** nullable persistent field */
    private String owner;

    /** nullable persistent field */
    private String status;

    /** full constructor */
    public TaxCode(java.lang.String taxCode, java.lang.String description, java.math.BigDecimal taxRate, java.util.Date dateEntered, java.util.Date dateExpires, java.lang.String owner, java.lang.String status) {
        this.taxCode = taxCode;
        this.description = description;
        this.taxRate = taxRate;
        this.dateEntered = dateEntered;
        this.dateExpires = dateExpires;
        this.owner = owner;
        this.status = status;
    }

    /** default constructor */
    public TaxCode() {
    }

    /** minimal constructor */
    public TaxCode(java.lang.String taxCode) {
        this.taxCode = taxCode;
    }

    public java.lang.String getTaxCode() {
		return (java.lang.String)HiltonUtility.ckNull(this.taxCode).trim();
    }

    public void setTaxCode(java.lang.String taxCode) {
		if (!HiltonUtility.isEmpty(taxCode) && taxCode.length() > 15) {
			taxCode = taxCode.substring(0, 15);
		}
		this.taxCode = taxCode;
    }

    public java.lang.String getDescription() {
		return (java.lang.String)HiltonUtility.ckNull(this.description).trim();
    }

    public void setDescription(java.lang.String description) {
		if (!HiltonUtility.isEmpty(description) && description.length() > 255) {
			description = description.substring(0, 255);
		}
		this.description = description;
    }

    public java.math.BigDecimal getTaxRate() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.taxRate);
    }

    public void setTaxRate(java.math.BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    public java.util.Date getDateEntered() {
		return this.dateEntered;
//		return HiltonUtility.ckNull(this.dateEntered);
//		return (java.sql.Date)HiltonUtility.ckNull(this.dateEntered);
    }

    public void setDateEntered(java.util.Date dateEntered) {
        this.dateEntered = dateEntered;
    }

    public java.util.Date getDateExpires() {
		return this.dateExpires;
//		return HiltonUtility.ckNull(this.dateExpires);
//		return (java.sql.Date)HiltonUtility.ckNull(this.dateExpires);
    }

    public void setDateExpires(java.util.Date dateExpires) {
        this.dateExpires = dateExpires;
    }

    public java.lang.String getOwner() {
		return (java.lang.String)HiltonUtility.ckNull(this.owner).trim();
    }

    public void setOwner(java.lang.String owner) {
		if (!HiltonUtility.isEmpty(owner) && owner.length() > 15) {
			owner = owner.substring(0, 15);
		}
		this.owner = owner;
    }

    public java.lang.String getStatus() {
		return (java.lang.String)HiltonUtility.ckNull(this.status).trim();
    }

    public void setStatus(java.lang.String status) {
		if (!HiltonUtility.isEmpty(status) && status.length() > 4) {
			status = status.substring(0, 4);
		}
		this.status = status;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("taxCode", getTaxCode())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof TaxCode) ) return false;
        TaxCode castOther = (TaxCode) other;
        return new EqualsBuilder()
            .append(this.getTaxCode(), castOther.getTaxCode())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getTaxCode())
            .toHashCode();
    }

}
