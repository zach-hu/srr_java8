package com.tsa.puridiom.entity;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class ElementFormPK implements Serializable {

    /** identifier field */
    private String formId;

    /** identifier field */
    private java.math.BigDecimal elementIndex;

    /** full constructor */
    public ElementFormPK(java.lang.String formId, java.math.BigDecimal elementIndex) {
        this.formId = formId;
        this.elementIndex = elementIndex;
    }

    /** default constructor */
    public ElementFormPK() {
    }

    public java.lang.String getFormId() {
        return this.formId;
    }

    public void setFormId(java.lang.String formId) {
        this.formId = formId;
    }

    public java.math.BigDecimal getElementIndex() {
        return this.elementIndex;
    }

    public void setElementIndex(java.math.BigDecimal elementIndex) {
        this.elementIndex = elementIndex;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("formId", getFormId())
            .append("elementIndex", getElementIndex())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof ElementFormPK) ) return false;
        ElementFormPK castOther = (ElementFormPK) other;
        return new EqualsBuilder()
            .append(this.getFormId(), castOther.getFormId())
            .append(this.getElementIndex(), castOther.getElementIndex())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getFormId())
            .append(getElementIndex())
            .toHashCode();
    }

}
