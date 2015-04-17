package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class BudgetDrawerPK implements Serializable {

    /** identifier field */
    private String budgetId;

    /** identifier field */
    private String authType;

    /** identifier field */
    private String authority;

    /** full constructor */
    public BudgetDrawerPK(java.lang.String budgetId, java.lang.String authType, java.lang.String authority) {
        this.budgetId = budgetId;
        this.authType = authType;
        this.authority = authority;
    }

    /** default constructor */
    public BudgetDrawerPK() {
    }

    public java.lang.String getBudgetId() {
		return (java.lang.String)HiltonUtility.ckNull(this.budgetId).trim();
    }

    public void setBudgetId(java.lang.String budgetId) {
        this.budgetId = budgetId;
    }

    public java.lang.String getAuthType() {
		return (java.lang.String)HiltonUtility.ckNull(this.authType).trim();
    }

    public void setAuthType(java.lang.String authType) {
        this.authType = authType;
    }

    public java.lang.String getAuthority() {
		return (java.lang.String)HiltonUtility.ckNull(this.authority).trim();
    }

    public void setAuthority(java.lang.String authority) {
        this.authority = authority;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("budgetId", getBudgetId())
            .append("authType", getAuthType())
            .append("authority", getAuthority())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof BudgetDrawerPK) ) return false;
        BudgetDrawerPK castOther = (BudgetDrawerPK) other;
        return new EqualsBuilder()
            .append(this.getBudgetId(), castOther.getBudgetId())
            .append(this.getAuthType(), castOther.getAuthType())
            .append(this.getAuthority(), castOther.getAuthority())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getBudgetId())
            .append(getAuthType())
            .append(getAuthority())
            .toHashCode();
    }

}
