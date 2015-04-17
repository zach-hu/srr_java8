package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class KitItem implements Serializable {

    /** identifier field */
    private com.tsa.puridiom.entity.KitItemPK comp_id;

    /** nullable persistent field */
    private java.math.BigDecimal childQuantity;

    private java.lang.String childDescription;

    /** full constructor */
    public KitItem(com.tsa.puridiom.entity.KitItemPK comp_id, java.math.BigDecimal childQuantity) {
        this.comp_id = comp_id;
        this.childQuantity = childQuantity;
    }

    /** default constructor */
    public KitItem() {
    }

    /** minimal constructor */
    public KitItem(com.tsa.puridiom.entity.KitItemPK comp_id) {
        this.comp_id = comp_id;
    }

    public com.tsa.puridiom.entity.KitItemPK getComp_id() {
        return this.comp_id;
    }

    public void setComp_id(com.tsa.puridiom.entity.KitItemPK comp_id) {
        this.comp_id = comp_id;
    }

    public java.math.BigDecimal getChildQuantity() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.childQuantity);
    }

    public void setChildQuantity(java.math.BigDecimal childQuantity) {
        this.childQuantity = childQuantity;
    }

    public java.lang.String getChildDescription() {
		return (java.lang.String)HiltonUtility.ckNull(this.childDescription);
    }

    public void setChildDescription(java.lang.String childDescription) {
        this.childDescription = childDescription;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("comp_id", getComp_id())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof KitItem) ) return false;
        KitItem castOther = (KitItem) other;
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
