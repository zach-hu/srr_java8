package com.tsa.puridiom.entity;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class ElementDataPK implements Serializable {

    /** identifier field */
    private String formId;

    /** identifier field */
    private java.math.BigDecimal icHeader;

    /** identifier field */
    private java.math.BigDecimal icLine;

    /** identifier field */
    private java.math.BigDecimal icSequence ;

    /** identifier field */
    private String elementId;

    /** full constructor */
    public ElementDataPK(java.lang.String formId, java.math.BigDecimal icHeader, java.math.BigDecimal icLine,  java.math.BigDecimal icSequence,  java.lang.String elementId) {
        this.formId = formId;
        this.icHeader = icHeader;
        this.icLine = icLine;
        this.elementId = elementId;
        this.icSequence = icSequence ;
    }

    /** default constructor */
    public ElementDataPK() {
    }

    public java.lang.String getFormId() {
        return this.formId;
    }

    public void setFormId(java.lang.String formId) {
        this.formId = formId;
    }

    public java.math.BigDecimal getIcHeader() {
        return this.icHeader;
    }

    public void setIcHeader(java.math.BigDecimal icHeader) {
        this.icHeader = icHeader;
    }

    public java.math.BigDecimal getIcLine() {
        return this.icLine;
    }

    public void setIcLine(java.math.BigDecimal icLine) {
        this.icLine = icLine;
    }


    public java.math.BigDecimal getIcSequence() {
		return icSequence;
	}

	public void setIcSequence(java.math.BigDecimal icSequence) {
		this.icSequence = icSequence;
	}

	public java.lang.String getElementId() {
        return this.elementId;
    }

    public void setElementId(java.lang.String elementId) {
        this.elementId = elementId;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("formId", getFormId())
            .append("icHeader", getIcHeader())
            .append("icLine", getIcLine())
            .append("icSequence",getIcSequence())
            .append("elementId", getElementId())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof ElementDataPK) ) return false;
        ElementDataPK castOther = (ElementDataPK) other;
        return new EqualsBuilder()
            .append(this.getFormId(), castOther.getFormId())
            .append(this.getIcHeader(), castOther.getIcHeader())
            .append(this.getIcLine(), castOther.getIcLine())
            .append(this.icSequence, castOther.getIcSequence())
            .append(this.getElementId(), castOther.getElementId())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getFormId())
            .append(getIcHeader())
            .append(getIcLine())
            .append(getIcSequence())
            .append(getElementId())
            .toHashCode();
    }

}
