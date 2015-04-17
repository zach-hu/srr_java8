package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class UserPreference implements Serializable {

    /** identifier field */
    private com.tsa.puridiom.entity.UserPreferencePK comp_id;

    /** nullable persistent field */
    private String value;

    /** full constructor */
    public UserPreference(com.tsa.puridiom.entity.UserPreferencePK comp_id, java.lang.String value) {
        this.comp_id = comp_id;
        this.value = value;
    }

    /** default constructor */
    public UserPreference() {
    }

    /** minimal constructor */
    public UserPreference(com.tsa.puridiom.entity.UserPreferencePK comp_id) {
        this.comp_id = comp_id;
    }

    public com.tsa.puridiom.entity.UserPreferencePK getComp_id() {
        return this.comp_id;
    }

    public void setComp_id(com.tsa.puridiom.entity.UserPreferencePK comp_id) {
        this.comp_id = comp_id;
    }

    public java.lang.String getValue() {
		return (java.lang.String)HiltonUtility.ckNull(this.value).trim();
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
        if ( !(other instanceof UserPreference) ) return false;
        UserPreference castOther = (UserPreference) other;
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
