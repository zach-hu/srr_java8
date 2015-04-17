package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class BudgetDrawer implements Serializable {

    /** identifier field */
    private com.tsa.puridiom.entity.BudgetDrawerPK comp_id;

    /** nullable persistent field */
    private String owner;

    /** nullable persistent field */
    private String status;

    /** nullable persistent field */
    private String budgetFlag;

    /** full constructor */
    public BudgetDrawer(com.tsa.puridiom.entity.BudgetDrawerPK comp_id, java.lang.String owner, java.lang.String status, java.lang.String budgetFlag) {
        this.comp_id = comp_id;
        this.owner = owner;
        this.status = status;
        this.budgetFlag = budgetFlag;
    }

    /** default constructor */
    public BudgetDrawer() {
    }

    /** minimal constructor */
    public BudgetDrawer(com.tsa.puridiom.entity.BudgetDrawerPK comp_id) {
        this.comp_id = comp_id;
    }

    public com.tsa.puridiom.entity.BudgetDrawerPK getComp_id() {
        return this.comp_id;
    }

    public void setComp_id(com.tsa.puridiom.entity.BudgetDrawerPK comp_id) {
        this.comp_id = comp_id;
    }

    public java.lang.String getOwner() {
		return (java.lang.String)HiltonUtility.ckNull(this.owner).trim();
    }

    public void setOwner(java.lang.String owner) {
		if (!HiltonUtility.isEmpty(owner) && owner.length() > 15) {
			owner = owner.substring(0, 15);
		}
		this.owner = owner;
    }

    public java.lang.String getStatus() {
		return (java.lang.String)HiltonUtility.ckNull(this.status).trim();
    }

    public void setStatus(java.lang.String status) {
		if (!HiltonUtility.isEmpty(status) && status.length() > 4) {
			status = status.substring(0, 4);
		}
		this.status = status;
    }

    public java.lang.String getBudgetFlag() {
		return (java.lang.String)HiltonUtility.ckNull(this.budgetFlag).trim();
    }

    public void setBudgetFlag(java.lang.String budgetFlag) {
		if (!HiltonUtility.isEmpty(budgetFlag) && budgetFlag.length() > 2) {
			budgetFlag = budgetFlag.substring(0, 2);
		}
		this.budgetFlag = budgetFlag;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("comp_id", getComp_id())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof BudgetDrawer) ) return false;
        BudgetDrawer castOther = (BudgetDrawer) other;
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
