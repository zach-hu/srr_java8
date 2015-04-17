package com.tsa.puridiom.entity;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class InspectionMte implements Serializable {

    /** identifier field */
    private com.tsa.puridiom.entity.InspectionMtePK comp_id;

    /** nullable persistent field */
    private java.math.BigDecimal icMsrLine;

    /** nullable persistent field */
    private String mteNo;

    /** nullable persistent field */
    private java.util.Date dateUsed;

    /** nullable persistent field */
    private String description;

    /** nullable persistent field */
    private java.util.Date lastChange;

    /** nullable persistent field */
    private String lastChangeBy;

    /** nullable persistent field */
    private java.util.Date calDate;

    /** full constructor */
    public InspectionMte(com.tsa.puridiom.entity.InspectionMtePK comp_id, java.math.BigDecimal icMsrLine, java.lang.String mteNo, java.util.Date dateUsed, java.lang.String description, java.util.Date lastChange, java.lang.String lastChangeBy, java.util.Date calDate) {
        this.comp_id = comp_id;
        this.icMsrLine = icMsrLine;
        this.mteNo= mteNo ;
        this.dateUsed = dateUsed;
        this.description = description;
        this.lastChange = lastChange;
        this.lastChangeBy = lastChangeBy;
        this.calDate = calDate;
    }

    /** default constructor */
    public InspectionMte() {
    }

    /** minimal constructor */
    public InspectionMte(com.tsa.puridiom.entity.InspectionMtePK comp_id) {
        this.comp_id = comp_id;
    }

    public com.tsa.puridiom.entity.InspectionMtePK getComp_id() {
        return this.comp_id;
    }

    public void setComp_id(com.tsa.puridiom.entity.InspectionMtePK comp_id) {
        this.comp_id = comp_id;
    }

    public java.math.BigDecimal getIcMsrLine() {
        return this.icMsrLine;
    }

    public void setIcMsrLine(java.math.BigDecimal icMsrLine) {
        this.icMsrLine = icMsrLine;
    }

    public java.util.Date getDateUsed() {
        return this.dateUsed;
    }

    public void setDateUsed(java.util.Date dateUsed) {
        this.dateUsed = dateUsed;
    }

    public java.lang.String getDescription() {
        return this.description;
    }

    public void setDescription(java.lang.String description) {
        this.description = description;
    }

    public java.util.Date getLastChange() {
        return this.lastChange;
    }

    public void setLastChange(java.util.Date lastChange) {
        this.lastChange = lastChange;
    }

    public java.lang.String getLastChangeBy() {
        return this.lastChangeBy;
    }

    public void setLastChangeBy(java.lang.String lastChangeBy) {
        this.lastChangeBy = lastChangeBy;
    }

	public String getMteNo() {
		return mteNo;
	}

	public void setMteNo(String mteNo) {
		this.mteNo = mteNo;
	}

	public java.util.Date getCalDate() {
        return this.calDate;
    }

    public void setCalDate(java.util.Date calDate) {
        this.calDate = calDate;
    }

	public String toString() {
        return new ToStringBuilder(this)
            .append("comp_id", getComp_id())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof InspectionMte) ) return false;
        InspectionMte castOther = (InspectionMte) other;
        return new EqualsBuilder()
            .append(this.getComp_id(), castOther.getComp_id())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getComp_id())
            .toHashCode();
    }

}
