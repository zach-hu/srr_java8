package com.tsa.puridiom.entity;

import com.tsagate.foundation.utility.Utility;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class PropertyPK implements Serializable {

    /** identifier field */
    private String section;

    /** identifier field */
    private String property;

    /** full constructor */
    public PropertyPK(java.lang.String section, java.lang.String property) {
        this.section = section;
        this.property = property;
    }

    /** default constructor */
    public PropertyPK() {
    }

    public java.lang.String getSection() {
		return (java.lang.String)Utility.ckNull(this.section);
    }

    public void setSection(java.lang.String section) {
        this.section = section;
    }

    public java.lang.String getProperty() {
		return (java.lang.String)Utility.ckNull(this.property);
    }

    public void setProperty(java.lang.String property) {
        this.property = property;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("section", getSection())
            .append("property", getProperty())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof PropertyPK) ) return false;
        PropertyPK castOther = (PropertyPK) other;
        return new EqualsBuilder()
            .append(this.getSection(), castOther.getSection())
            .append(this.getProperty(), castOther.getProperty())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getSection())
            .append(getProperty())
            .toHashCode();
    }

}
