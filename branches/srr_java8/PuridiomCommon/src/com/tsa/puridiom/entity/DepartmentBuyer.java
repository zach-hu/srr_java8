package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class DepartmentBuyer implements Serializable {

    /** identifier field */
    private com.tsa.puridiom.entity.DepartmentBuyerPK comp_id;

    /** nullable persistent field */
    private java.math.BigDecimal assignAmount;

    /** full constructor */
    public DepartmentBuyer(com.tsa.puridiom.entity.DepartmentBuyerPK comp_id, java.math.BigDecimal assignAmount) {
        this.comp_id = comp_id;
        this.assignAmount = assignAmount;
    }

    /** default constructor */
    public DepartmentBuyer() {
    }

    /** minimal constructor */
    public DepartmentBuyer(com.tsa.puridiom.entity.DepartmentBuyerPK comp_id) {
        this.comp_id = comp_id;
    }

    public com.tsa.puridiom.entity.DepartmentBuyerPK getComp_id() {
        return this.comp_id;
    }

    public void setComp_id(com.tsa.puridiom.entity.DepartmentBuyerPK comp_id) {
        this.comp_id = comp_id;
    }

    public java.math.BigDecimal getAssignAmount() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.assignAmount);
    }

    public void setAssignAmount(java.math.BigDecimal assignAmount) {
        this.assignAmount = assignAmount;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("comp_id", getComp_id())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof DepartmentBuyer) ) return false;
        DepartmentBuyer castOther = (DepartmentBuyer) other;
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
