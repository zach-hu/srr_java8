package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsagate.foundation.utility.Utility;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class Property implements Serializable {

    /** identifier field */
    private com.tsa.puridiom.entity.PropertyPK comp_id;

    /** nullable persistent field */
    private String section;

    /** nullable persistent field */
    private String property;

    /** nullable persistent field */
    private String value;

    /** full constructor */
    public Property(com.tsa.puridiom.entity.PropertyPK comp_id, java.lang.String section, java.lang.String property, java.lang.String value) {
        this.comp_id = comp_id;
        this.section = section;
        this.property = property;
        this.value = value;
    }

    /** default constructor */
    public Property() {
    }

    /** minimal constructor */
    public Property(com.tsa.puridiom.entity.PropertyPK comp_id) {
        this.comp_id = comp_id;
    }

    public com.tsa.puridiom.entity.PropertyPK getComp_id() {
        return this.comp_id;
    }

    public void setComp_id(com.tsa.puridiom.entity.PropertyPK comp_id) {
        this.comp_id = comp_id;
    }


    public java.lang.String getValue() {
		return (java.lang.String)Utility.ckNull(this.value).trim();
    }

    public void setValue(java.lang.String value) {
		if (!HiltonUtility.isEmpty(value) && value.length() > 255) {
			value = value.substring(0, 255);
		}
		this.value = value;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("comp_id", getComp_id())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof Property) ) return false;
        Property castOther = (Property) other;
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
