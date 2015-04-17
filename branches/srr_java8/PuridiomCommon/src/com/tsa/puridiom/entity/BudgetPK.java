package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class BudgetPK implements Serializable {

    /** identifier field */
    private String fiscalYear;

    /** identifier field */
    private String primaryUdf;

    /** identifier field */
    private String secondaryUdf;

    /** identifier field */
    private String tertiaryUdf;

    /** full constructor */
    public BudgetPK(java.lang.String fiscalYear, java.lang.String primaryUdf, java.lang.String secondaryUdf, java.lang.String tertiaryUdf) {
        this.fiscalYear = fiscalYear;
        this.primaryUdf = primaryUdf;
        this.secondaryUdf = secondaryUdf;
        this.tertiaryUdf = tertiaryUdf;
    }

    /** default constructor */
    public BudgetPK() {
    }

    public java.lang.String getFiscalYear() {
		return this.fiscalYear;
    }

    public void setFiscalYear(java.lang.String fiscalYear) {
        this.fiscalYear = fiscalYear;
    }

    public java.lang.String getPrimaryUdf() {
		return this.primaryUdf;
    }

    public void setPrimaryUdf(java.lang.String primaryUdf) {
        this.primaryUdf = primaryUdf;
    }

    public java.lang.String getSecondaryUdf() {
		return this.secondaryUdf;
    }

    public void setSecondaryUdf(java.lang.String secondaryUdf) {
        this.secondaryUdf = secondaryUdf;
    }

    public java.lang.String getTertiaryUdf() {
		return this.tertiaryUdf;
    }

    public void setTertiaryUdf(java.lang.String tertiaryUdf) {
        this.tertiaryUdf = tertiaryUdf;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("fiscalYear", getFiscalYear())
            .append("primaryUdf", getPrimaryUdf())
            .append("secondaryUdf", getSecondaryUdf())
            .append("tertiaryUdf", getTertiaryUdf())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof BudgetPK) ) return false;
        BudgetPK castOther = (BudgetPK) other;
        return new EqualsBuilder()
            .append(this.getFiscalYear(), castOther.getFiscalYear())
            .append(this.getPrimaryUdf(), castOther.getPrimaryUdf())
            .append(this.getSecondaryUdf(), castOther.getSecondaryUdf())
            .append(this.getTertiaryUdf(), castOther.getTertiaryUdf())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getFiscalYear())
            .append(getPrimaryUdf())
            .append(getSecondaryUdf())
            .append(getTertiaryUdf())
            .toHashCode();
    }

}
