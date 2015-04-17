package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class Budget implements Serializable {

    /** identifier field */
    private com.tsa.puridiom.entity.BudgetPK comp_id;

    /** nullable persistent field */
    private java.math.BigDecimal budgeted;

    /** nullable persistent field */
    private java.math.BigDecimal preEncumbered;

    /** nullable persistent field */
    private java.math.BigDecimal encumbered;

    /** nullable persistent field */
    private java.math.BigDecimal expensed;

    /** nullable persistent field */
    private String authority;

    /** nullable persistent field */
    private String description;

    /** full constructor */
    public Budget(com.tsa.puridiom.entity.BudgetPK comp_id, java.math.BigDecimal budgeted, java.math.BigDecimal preEncumbered, java.math.BigDecimal encumbered, java.math.BigDecimal expensed, java.lang.String authority, java.lang.String description) {
        this.comp_id = comp_id;
        this.budgeted = budgeted;
        this.preEncumbered = preEncumbered;
        this.encumbered = encumbered;
        this.expensed = expensed;
        this.authority = authority;
        this.description = description;
    }

    /** default constructor */
    public Budget() {
    }

    /** minimal constructor */
    public Budget(com.tsa.puridiom.entity.BudgetPK comp_id) {
        this.comp_id = comp_id;
    }

    public com.tsa.puridiom.entity.BudgetPK getComp_id() {
        return this.comp_id;
    }

    public void setComp_id(com.tsa.puridiom.entity.BudgetPK comp_id) {
        this.comp_id = comp_id;
    }

    public java.math.BigDecimal getBudgeted() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.budgeted);
    }

    public void setBudgeted(java.math.BigDecimal budgeted) {
        this.budgeted = budgeted;
    }

    public java.math.BigDecimal getPreEncumbered() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.preEncumbered);
    }

    public void setPreEncumbered(java.math.BigDecimal preEncumbered) {
        this.preEncumbered = preEncumbered;
    }

    public java.math.BigDecimal getEncumbered() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.encumbered);
    }

    public void setEncumbered(java.math.BigDecimal encumbered) {
        this.encumbered = encumbered;
    }

    public java.math.BigDecimal getExpensed() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.expensed);
    }

    public void setExpensed(java.math.BigDecimal expensed) {
        this.expensed = expensed;
    }

    public java.lang.String getAuthority() {
		return (java.lang.String)HiltonUtility.ckNull(this.authority).trim();
    }

    public void setAuthority(java.lang.String authority) {
		if (!HiltonUtility.isEmpty(authority) && authority.length() > 15) {
			authority = authority.substring(0, 15);
		}
		this.authority = authority;
    }

    public java.lang.String getDescription() {
		return (java.lang.String)HiltonUtility.ckNull(this.description).trim();
    }

    public void setDescription(java.lang.String description) {
		if (!HiltonUtility.isEmpty(description) && description.length() > 255) {
			description = description.substring(0, 255);
		}
		this.description = description;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("comp_id", getComp_id())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof Budget) ) return false;
        Budget castOther = (Budget) other;
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
