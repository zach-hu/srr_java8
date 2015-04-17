package com.tsa.puridiom.entity;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class ElementData implements Serializable {

    /** identifier field */
    private com.tsa.puridiom.entity.ElementDataPK comp_id;

    /** nullable persistent field */
    private String elementValue;

    /** full constructor */
    public ElementData(com.tsa.puridiom.entity.ElementDataPK comp_id, java.lang.String elementValue) {
        this.comp_id = comp_id;
        this.elementValue = elementValue;
    }

    /** default constructor */
    public ElementData() {
    }

    /** minimal constructor */
    public ElementData(com.tsa.puridiom.entity.ElementDataPK comp_id) {
        this.comp_id = comp_id;
    }

    public com.tsa.puridiom.entity.ElementDataPK getComp_id() {
        return this.comp_id;
    }

    public void setComp_id(com.tsa.puridiom.entity.ElementDataPK comp_id) {
        this.comp_id = comp_id;
    }

    public java.lang.String getElementValue() {
        return this.elementValue;
    }

    public void setElementValue(java.lang.String elementValue) {
        this.elementValue = elementValue;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("comp_id", getComp_id())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof ElementData) ) return false;
        ElementData castOther = (ElementData) other;
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
