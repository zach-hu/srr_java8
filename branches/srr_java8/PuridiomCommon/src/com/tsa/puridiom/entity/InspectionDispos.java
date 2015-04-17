package com.tsa.puridiom.entity;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.tsa.puridiom.common.utility.HiltonUtility;

/** @author Hibernate CodeGenerator */
public class InspectionDispos implements Serializable {

    /** identifier field */
    private com.tsa.puridiom.entity.InspectionDisposPK comp_id;

    /** nullable persistent field */
    private java.math.BigDecimal icInspNo;

    /** nullable persistent field */
    private java.math.BigDecimal icMsrLine;

    /** nullable persistent field */
    private String disposition;

    /** nullable persistent field */
    private String respGroup;

    /** nullable persistent field */
    private String dispType;

    /** nullable persistent field */
    private java.math.BigDecimal dispQuantity;

    /** nullable persistent field */
    private java.util.Date dispClosed;

    /** nullable persistent field */
    private java.util.Date lastChange;

    /** nullable persistent field */
    private String lastChangeBy;

    /** nullable persistent field */
    private String description;


    /** full constructor */
    public InspectionDispos(com.tsa.puridiom.entity.InspectionDisposPK comp_id, java.math.BigDecimal icInspNo, java.math.BigDecimal icMsrLine, java.lang.String disposition, java.lang.String respGroup, java.lang.String dispType, java.math.BigDecimal dispQuantity, java.util.Date dispClosed, java.util.Date lastChange, java.lang.String lastChangeBy) {
        this.comp_id = comp_id;
        this.icInspNo = icInspNo ;
        this.icMsrLine = icMsrLine;
        this.disposition = disposition;
        this.respGroup = respGroup;
        this.dispType = dispType;
        this.dispQuantity = dispQuantity;
        this.dispClosed = dispClosed;
        this.lastChange = lastChange;
        this.lastChangeBy = lastChangeBy;
    }

    /** default constructor */
    public InspectionDispos() {
    }

    /** minimal constructor */
    public InspectionDispos(com.tsa.puridiom.entity.InspectionDisposPK comp_id) {
        this.comp_id = comp_id;
    }

    public com.tsa.puridiom.entity.InspectionDisposPK getComp_id() {
        return this.comp_id;
    }

    public void setComp_id(com.tsa.puridiom.entity.InspectionDisposPK comp_id) {
        this.comp_id = comp_id;
    }

    public java.math.BigDecimal getIcMsrLine() {
        return this.icMsrLine;
    }

    public void setIcMsrLine(java.math.BigDecimal icMsrLine) {
        this.icMsrLine = icMsrLine;
    }

    public java.lang.String getDisposition() {
        return this.disposition;
    }

    public void setDisposition(java.lang.String disposition) {
        this.disposition = disposition;
    }

    public java.lang.String getRespGroup() {
        return this.respGroup;
    }

    public void setRespGroup(java.lang.String respGroup) {
        this.respGroup = respGroup;
    }

    public java.lang.String getDispType() {
        return this.dispType;
    }

    public void setDispType(java.lang.String dispType) {
        this.dispType = dispType;
    }

    public java.math.BigDecimal getDispQuantity() {
        return this.dispQuantity;
    }

    public void setDispQuantity(java.math.BigDecimal dispQuantity) {
        this.dispQuantity = dispQuantity;
    }

    public java.util.Date getDispClosed() {
        return this.dispClosed;
    }

    public void setDispClosed(java.util.Date dispClosed) {
        this.dispClosed = dispClosed;
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

    public java.math.BigDecimal getIcInspNo() {
		return icInspNo;
	}

	public void setIcInspNo(java.math.BigDecimal icInspNo) {
		this.icInspNo = icInspNo;
	}

	 public java.lang.String getDescription() {
		return (java.lang.String)HiltonUtility.ckNull(this.description).trim();
    }

    public void setDescription(java.lang.String description) {
		if (!HiltonUtility.isEmpty(description) && description.length() > 2000) {
			description = description.substring(0, 2000);
		}
		this.description = description;
    }

	public String toString() {
        return new ToStringBuilder(this)
            .append("comp_id", getComp_id())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof InspectionDispos) ) return false;
        InspectionDispos castOther = (InspectionDispos) other;
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
