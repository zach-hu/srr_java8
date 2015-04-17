package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class UserGroupRel implements Serializable {

    /** identifier field */
    private com.tsa.puridiom.entity.UserGroupRelPK comp_id;

    /** full constructor */
    public UserGroupRel(com.tsa.puridiom.entity.UserGroupRelPK comp_id) {
        this.comp_id = comp_id;
    }

    /** default constructor */
    public UserGroupRel() {
    }

    public com.tsa.puridiom.entity.UserGroupRelPK getComp_id() {
        return this.comp_id;
    }

    public void setComp_id(com.tsa.puridiom.entity.UserGroupRelPK comp_id) {
        this.comp_id = comp_id;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("comp_id", getComp_id())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof UserGroupRel) ) return false;
        UserGroupRel castOther = (UserGroupRel) other;
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
