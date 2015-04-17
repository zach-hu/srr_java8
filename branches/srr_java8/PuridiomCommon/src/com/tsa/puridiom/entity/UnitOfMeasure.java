package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class UnitOfMeasure implements Serializable {

    /** identifier field */
    private String umCode;

    /** nullable persistent field */
    private String description;

    /** nullable persistent field */
    private String status;

    /** nullable persistent field */
    private java.util.Date dateEntered;

    /** nullable persistent field */
    private java.util.Date dateExpires;

    /** nullable persistent field */
    private String owner;

    /** nullable persistent field */
    private java.math.BigDecimal factor;

    /** nullable persistent field */
    private String conversion;

    /** full constructor */
    public UnitOfMeasure(java.lang.String umCode, java.lang.String description, java.lang.String status, java.util.Date dateEntered, java.util.Date dateExpires, java.lang.String owner, java.math.BigDecimal factor, java.lang.String conversion) {
        this.umCode = umCode;
        this.description = description;
        this.status = status;
        this.dateEntered = dateEntered;
        this.dateExpires = dateExpires;
        this.owner = owner;
        this.factor = factor;
        this.conversion = conversion;
    }

    /** default constructor */
    public UnitOfMeasure() {
    }

    /** minimal constructor */
    public UnitOfMeasure(java.lang.String umCode) {
        this.umCode = umCode;
    }

    public java.lang.String getUmCode() {
		return (java.lang.String)HiltonUtility.ckNull(this.umCode).trim();
    }

    public void setUmCode(java.lang.String umCode) {
		if (!HiltonUtility.isEmpty(umCode) && umCode.length() > 15) {
			umCode = umCode.substring(0, 15);
		}
		this.umCode = umCode;
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

    public java.lang.String getStatus() {
		return (java.lang.String)HiltonUtility.ckNull(this.status).trim();
    }

    public void setStatus(java.lang.String status) {
		if (!HiltonUtility.isEmpty(status) && status.length() > 4) {
			status = status.substring(0, 4);
		}
		this.status = status;
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

    public java.math.BigDecimal getFactor() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.factor);
    }

    public void setFactor(java.math.BigDecimal factor) {
        this.factor = factor;
    }

    public java.lang.String getConversion() {
		return (java.lang.String)HiltonUtility.ckNull(this.conversion).trim();
    }

    public void setConversion(java.lang.String conversion) {
		if (!HiltonUtility.isEmpty(conversion) && conversion.length() > 60) {
			conversion = conversion.substring(0, 60);
		}
		this.conversion = conversion;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("umCode", getUmCode())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof UnitOfMeasure) ) return false;
        UnitOfMeasure castOther = (UnitOfMeasure) other;
        return new EqualsBuilder()
            .append(this.getUmCode(), castOther.getUmCode())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getUmCode())
            .toHashCode();
    }

}
