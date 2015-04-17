package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class InvUsagePK implements Serializable {

    /** identifier field */
    private String itemNumber;

    /** identifier field */
    private java.math.BigDecimal usageYear;

    /** identifier field */
    private java.math.BigDecimal usageMonth;

    /** full constructor */
    public InvUsagePK(java.lang.String itemNumber, java.math.BigDecimal usageYear, java.math.BigDecimal usageMonth) {
        this.itemNumber = itemNumber;
        this.usageYear = usageYear;
        this.usageMonth = usageMonth;
    }

    /** default constructor */
    public InvUsagePK() {
    }

    public java.lang.String getItemNumber() {
		return (java.lang.String)HiltonUtility.ckNull(this.itemNumber);
    }

    public void setItemNumber(java.lang.String itemNumber) {
        this.itemNumber = itemNumber;
    }

    public java.math.BigDecimal getUsageYear() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.usageYear);
    }

    public void setUsageYear(java.math.BigDecimal usageYear) {
        this.usageYear = usageYear;
    }

    public java.math.BigDecimal getUsageMonth() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.usageMonth);
    }

    public void setUsageMonth(java.math.BigDecimal usageMonth) {
        this.usageMonth = usageMonth;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("itemNumber", getItemNumber())
            .append("usageYear", getUsageYear())
            .append("usageMonth", getUsageMonth())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof InvUsagePK) ) return false;
        InvUsagePK castOther = (InvUsagePK) other;
        return new EqualsBuilder()
            .append(this.getItemNumber(), castOther.getItemNumber())
            .append(this.getUsageYear(), castOther.getUsageYear())
            .append(this.getUsageMonth(), castOther.getUsageMonth())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getItemNumber())
            .append(getUsageYear())
            .append(getUsageMonth())
            .toHashCode();
    }

}
