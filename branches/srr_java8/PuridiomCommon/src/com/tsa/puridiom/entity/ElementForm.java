package com.tsa.puridiom.entity;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class ElementForm implements Serializable {

    /** identifier field */
    private com.tsa.puridiom.entity.ElementFormPK comp_id;

    /** nullable persistent field */
    private String elementModule;

    /** nullable persistent field */
    private String elementId;

    /** nullable persistent field */
    private String elementType;

    /** nullable persistent field */
    private java.math.BigDecimal elementLength;

    /** nullable persistent field */
    private String elementCase;

    /** nullable persistent field */
    private String elementFormat;

    /** nullable persistent field */
    private String elementLabel;

    /** nullable persistent field */
    private String elementDefault;

    /** nullable persistent field */
    private java.math.BigDecimal elementTb;

    /** nullable persistent field */
    private java.math.BigDecimal elementTc;

    /** full constructor */
    public ElementForm(com.tsa.puridiom.entity.ElementFormPK comp_id, java.lang.String elementModule, java.lang.String elementId, java.lang.String elementType, java.math.BigDecimal elementLength, java.lang.String elementCase, java.lang.String elementFormat, java.lang.String elementLabel, java.lang.String elementDefault, java.math.BigDecimal elementTb, java.math.BigDecimal elementTc) {
        this.comp_id = comp_id;
        this.elementModule = elementModule;
        this.elementId = elementId;
        this.elementType = elementType;
        this.elementLength = elementLength;
        this.elementCase = elementCase;
        this.elementFormat = elementFormat;
        this.elementLabel = elementLabel;
        this.elementDefault = elementDefault;
        this.elementTb = elementTb;
        this.elementTc = elementTc;
    }

    /** default constructor */
    public ElementForm() {
    }

    /** minimal constructor */
    public ElementForm(com.tsa.puridiom.entity.ElementFormPK comp_id) {
        this.comp_id = comp_id;
    }

    public com.tsa.puridiom.entity.ElementFormPK getComp_id() {
        return this.comp_id;
    }

    public void setComp_id(com.tsa.puridiom.entity.ElementFormPK comp_id) {
        this.comp_id = comp_id;
    }

    public java.lang.String getElementModule() {
        return this.elementModule;
    }

    public void setElementModule(java.lang.String elementModule) {
        this.elementModule = elementModule;
    }

    public java.lang.String getElementId() {
        return this.elementId;
    }

    public void setElementId(java.lang.String elementId) {
        this.elementId = elementId;
    }

    public java.lang.String getElementType() {
        return this.elementType;
    }

    public void setElementType(java.lang.String elementType) {
        this.elementType = elementType;
    }

    public java.math.BigDecimal getElementLength() {
        return this.elementLength;
    }

    public void setElementLength(java.math.BigDecimal elementLength) {
        this.elementLength = elementLength;
    }

    public java.lang.String getElementCase() {
        return this.elementCase;
    }

    public void setElementCase(java.lang.String elementCase) {
        this.elementCase = elementCase;
    }

    public java.lang.String getElementFormat() {
        return this.elementFormat;
    }

    public void setElementFormat(java.lang.String elementFormat) {
        this.elementFormat = elementFormat;
    }

    public java.lang.String getElementLabel() {
        return this.elementLabel;
    }

    public void setElementLabel(java.lang.String elementLabel) {
        this.elementLabel = elementLabel;
    }

    public java.lang.String getElementDefault() {
        return this.elementDefault;
    }

    public void setElementDefault(java.lang.String elementDefault) {
        this.elementDefault = elementDefault;
    }

    public java.math.BigDecimal getElementTb() {
        return this.elementTb;
    }

    public void setElementTb(java.math.BigDecimal elementTb) {
        this.elementTb = elementTb;
    }

    public java.math.BigDecimal getElementTc() {
        return this.elementTc;
    }

    public void setElementTc(java.math.BigDecimal elementTc) {
        this.elementTc = elementTc;
    }


	public String toString() {
        return new ToStringBuilder(this)
            .append("comp_id", getComp_id())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof ElementForm) ) return false;
        ElementForm castOther = (ElementForm) other;
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
