package com.tsa.puridiom.entity;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class InspectionDiscrep implements Serializable {

    /** identifier field */
    private com.tsa.puridiom.entity.InspectionDiscrepPK comp_id;

    /** nullable persistent field */
    private java.math.BigDecimal icMsrLine;

    /** nullable persistent field */
    private String inspectCode;

    /** nullable persistent field */
    private String inspRequirements;

    /** nullable persistent field */
    private String inspDescription;

    /** nullable persistent field */
    private java.math.BigDecimal inspQuantity;

    /** nullable persistent field */
    private String status;

    /** nullable persistent field */
    private java.util.Date inspStartDate;

    /** nullable persistent field */
    private java.util.Date lastChange;

    /** nullable persistent field */
    private String lastChangeBy;

    private java.math.BigDecimal icInspDiscrep ;

    /** full constructor */
    public InspectionDiscrep(com.tsa.puridiom.entity.InspectionDiscrepPK comp_id, java.math.BigDecimal icMsrLine, java.lang.String inspectCode, java.lang.String inspRequirements, java.lang.String inspDescription, java.math.BigDecimal inspQuantity, java.lang.String status, java.util.Date inspStartDate, java.util.Date lastChange, java.lang.String lastChangeBy, java.math.BigDecimal icInspDiscrep) {
        this.comp_id = comp_id;
        this.icMsrLine = icMsrLine;
        this.inspectCode = inspectCode;
        this.inspRequirements = inspRequirements;
        this.inspDescription = inspDescription;
        this.inspQuantity = inspQuantity;
        this.status = status;
        this.inspStartDate = inspStartDate;
        this.lastChange = lastChange;
        this.lastChangeBy = lastChangeBy;
        this.icInspDiscrep = icInspDiscrep ;
    }

    /** default constructor */
    public InspectionDiscrep() {
    }

    /** minimal constructor */
    public InspectionDiscrep(com.tsa.puridiom.entity.InspectionDiscrepPK comp_id) {
        this.comp_id = comp_id;
    }

    public com.tsa.puridiom.entity.InspectionDiscrepPK getComp_id() {
        return this.comp_id;
    }

    public void setComp_id(com.tsa.puridiom.entity.InspectionDiscrepPK comp_id) {
        this.comp_id = comp_id;
    }

    public java.math.BigDecimal getIcMsrLine() {
        return this.icMsrLine;
    }

    public void setIcMsrLine(java.math.BigDecimal icMsrLine) {
        this.icMsrLine = icMsrLine;
    }

    public java.lang.String getInspectCode() {
        return this.inspectCode;
    }

    public void setInspectCode(java.lang.String inspectCode) {
        this.inspectCode = inspectCode;
    }

    public java.lang.String getInspRequirements() {
        return this.inspRequirements;
    }

    public void setInspRequirements(java.lang.String inspRequirements) {
        this.inspRequirements = inspRequirements;
    }

    public java.lang.String getInspDescription() {
        return this.inspDescription;
    }

    public void setInspDescription(java.lang.String inspDescription) {
        this.inspDescription = inspDescription;
    }

    public java.math.BigDecimal getInspQuantity() {
        return this.inspQuantity;
    }

    public void setInspQuantity(java.math.BigDecimal inspQuantity) {
        this.inspQuantity = inspQuantity;
    }

    public java.lang.String getStatus() {
        return this.status;
    }

    public void setStatus(java.lang.String status) {
        this.status = status;
    }

    public java.util.Date getInspStartDate() {
        return this.inspStartDate;
    }

    public void setInspStartDate(java.util.Date inspStartDate) {
        this.inspStartDate = inspStartDate;
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

	public java.math.BigDecimal getIcInspDiscrep() {
		return icInspDiscrep;
	}

	public void setIcInspDiscrep(java.math.BigDecimal icInspDiscrep) {
		this.icInspDiscrep = icInspDiscrep;
	}

	public String toString() {
        return new ToStringBuilder(this)
            .append("comp_id", getComp_id())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof InspectionDiscrep) ) return false;
        InspectionDiscrep castOther = (InspectionDiscrep) other;
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
