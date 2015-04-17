package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class BudgetAuthPK implements Serializable {

    /** identifier field */
    private String primaryUdf;

    /** identifier field */
    private String secondaryUdf;

    /** identifier field */
    private String tertiaryUdf;

    /** identifier field */
    private String authType;

    /** identifier field */
    private String authority;

    /** full constructor */
    public BudgetAuthPK(java.lang.String primaryUdf, java.lang.String secondaryUdf, java.lang.String tertiaryUdf, java.lang.String authType, java.lang.String authority) {
        this.primaryUdf = primaryUdf;
        this.secondaryUdf = secondaryUdf;
        this.tertiaryUdf = tertiaryUdf;
        this.authType = authType;
        this.authority = authority;
    }

    /** default constructor */
    public BudgetAuthPK() {
    }

    public java.lang.String getPrimaryUdf() {
		return (java.lang.String)HiltonUtility.ckNull(this.primaryUdf);
    }

    public void setPrimaryUdf(java.lang.String primaryUdf) {
        this.primaryUdf = primaryUdf;
    }

    public java.lang.String getSecondaryUdf() {
		return (java.lang.String)HiltonUtility.ckNull(this.secondaryUdf);
    }

    public void setSecondaryUdf(java.lang.String secondaryUdf) {
        this.secondaryUdf = secondaryUdf;
    }

    public java.lang.String getTertiaryUdf() {
		return (java.lang.String)HiltonUtility.ckNull(this.tertiaryUdf);
    }

    public void setTertiaryUdf(java.lang.String tertiaryUdf) {
        this.tertiaryUdf = tertiaryUdf;
    }

    public java.lang.String getAuthType() {
		return (java.lang.String)HiltonUtility.ckNull(this.authType);
    }

    public void setAuthType(java.lang.String authType) {
        this.authType = authType;
    }

    public java.lang.String getAuthority() {
		return (java.lang.String)HiltonUtility.ckNull(this.authority);
    }

    public void setAuthority(java.lang.String authority) {
        this.authority = authority;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("primaryUdf", getPrimaryUdf())
            .append("secondaryUdf", getSecondaryUdf())
            .append("tertiaryUdf", getTertiaryUdf())
            .append("authType", getAuthType())
            .append("authority", getAuthority())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof BudgetAuthPK) ) return false;
        BudgetAuthPK castOther = (BudgetAuthPK) other;
        return new EqualsBuilder()
            .append(this.getPrimaryUdf(), castOther.getPrimaryUdf())
            .append(this.getSecondaryUdf(), castOther.getSecondaryUdf())
            .append(this.getTertiaryUdf(), castOther.getTertiaryUdf())
            .append(this.getAuthType(), castOther.getAuthType())
            .append(this.getAuthority(), castOther.getAuthority())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getPrimaryUdf())
            .append(getSecondaryUdf())
            .append(getTertiaryUdf())
            .append(getAuthType())
            .append(getAuthority())
            .toHashCode();
    }

}
