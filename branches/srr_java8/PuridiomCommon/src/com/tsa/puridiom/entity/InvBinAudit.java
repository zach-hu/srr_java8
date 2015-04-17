package com.tsa.puridiom.entity;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class InvBinAudit implements Serializable {

    /** identifier field */
    private Long audSeq;

    /** nullable persistent field */
    private java.util.Date audDate;

    /** nullable persistent field */
    private String audType;

    /** nullable persistent field */
    private String itemNumber;

    /** nullable persistent field */
    private String itemLocation;

    /** nullable persistent field */
    private String aisle;

    /** nullable persistent field */
    private String locrow;

    /** nullable persistent field */
    private java.math.BigDecimal icRc;

    /** nullable persistent field */
    private java.math.BigDecimal icRecLine;

    /** nullable persistent field */
    private String owner;

    /** nullable persistent field */
    private String status;

    /** nullable persistent field */
    private java.math.BigDecimal oldQoh;

    /** nullable persistent field */
    private java.math.BigDecimal newQoh;

    private String	audFrom ;

    /** full constructor */
    public InvBinAudit(java.lang.Long audSeq, java.util.Date audDate, java.lang.String audType, java.lang.String itemNumber, java.lang.String itemLocation, java.lang.String aisle, java.lang.String locrow, java.math.BigDecimal icRc, java.math.BigDecimal icRecLine, java.lang.String owner, java.lang.String status, java.math.BigDecimal oldQoh, java.math.BigDecimal newQoh) {
        this.audSeq = audSeq;
        this.audDate = audDate;
        this.audType = audType;
        this.itemNumber = itemNumber;
        this.itemLocation = itemLocation;
        this.aisle = aisle;
        this.locrow = locrow;
        this.icRc = icRc;
        this.icRecLine = icRecLine;
        this.owner = owner;
        this.status = status;
        this.oldQoh = oldQoh;
        this.newQoh = newQoh;
        this.audFrom = audFrom ;
    }

    /** default constructor */
    public InvBinAudit() {
    }

    /** minimal constructor */
    public InvBinAudit(java.lang.Long audSeq) {
        this.audSeq = audSeq;
    }

    public java.lang.Long getAudSeq() {
        return this.audSeq;
    }

    public void setAudSeq(java.lang.Long audSeq) {
        this.audSeq = audSeq;
    }

    public java.util.Date getAudDate() {
        return this.audDate;
    }

    public void setAudDate(java.util.Date audDate) {
        this.audDate = audDate;
    }

    public java.lang.String getAudType() {
        return this.audType;
    }

    public void setAudType(java.lang.String audType) {
        this.audType = audType;
    }

    public java.lang.String getItemNumber() {
        return this.itemNumber;
    }

    public void setItemNumber(java.lang.String itemNumber) {
        this.itemNumber = itemNumber;
    }

    public java.lang.String getItemLocation() {
        return this.itemLocation;
    }

    public void setItemLocation(java.lang.String itemLocation) {
        this.itemLocation = itemLocation;
    }

    public java.lang.String getAisle() {
        return this.aisle;
    }

    public void setAisle(java.lang.String aisle) {
        this.aisle = aisle;
    }

    public java.lang.String getLocrow() {
        return this.locrow;
    }

    public void setLocrow(java.lang.String locrow) {
        this.locrow = locrow;
    }

    public java.math.BigDecimal getIcRc() {
        return this.icRc;
    }

    public void setIcRc(java.math.BigDecimal icRc) {
        this.icRc = icRc;
    }

    public java.math.BigDecimal getIcRecLine() {
        return this.icRecLine;
    }

    public void setIcRecLine(java.math.BigDecimal icRecLine) {
        this.icRecLine = icRecLine;
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

    public java.math.BigDecimal getOldQoh() {
        return this.oldQoh;
    }

    public void setOldQoh(java.math.BigDecimal oldQoh) {
        this.oldQoh = oldQoh;
    }

    public java.math.BigDecimal getNewQoh() {
        return this.newQoh;
    }

    public void setNewQoh(java.math.BigDecimal newQoh) {
        this.newQoh = newQoh;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("audSeq", getAudSeq())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof InvBinAudit) ) return false;
        InvBinAudit castOther = (InvBinAudit) other;
        return new EqualsBuilder()
            .append(this.getAudSeq(), castOther.getAudSeq())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getAudSeq())
            .toHashCode();
    }

	public String getAudFrom() {
		return audFrom;
	}

	public void setAudFrom(String audFrom) {
		this.audFrom = audFrom;
	}

}
