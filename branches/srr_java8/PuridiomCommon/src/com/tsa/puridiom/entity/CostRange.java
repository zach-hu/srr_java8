package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class CostRange implements Serializable {

    /** identifier field */
    private java.math.BigDecimal icCostRange;

    /** persistent field */
    private String itemType;

    /** nullable persistent field */
    private String description;

    /** nullable persistent field */
    private java.math.BigDecimal minimumCost;

    /** nullable persistent field */
    private java.math.BigDecimal maximumCost;

    /** full constructor */
    public CostRange(java.math.BigDecimal icCostRange, java.lang.String itemType, java.lang.String description, java.math.BigDecimal minimumCost, java.math.BigDecimal maximumCost) {
        this.icCostRange = icCostRange;
        this.itemType = itemType;
        this.description = description;
        this.minimumCost = minimumCost;
        this.maximumCost = maximumCost;
    }

    /** default constructor */
    public CostRange() {
    }

    /** minimal constructor */
    public CostRange(java.math.BigDecimal icCostRange, java.lang.String itemType) {
        this.icCostRange = icCostRange;
        this.itemType = itemType;
    }

    public java.math.BigDecimal getIcCostRange() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.icCostRange);
    }

    public void setIcCostRange(java.math.BigDecimal icCostRange) {
        this.icCostRange = icCostRange;
    }

    public java.lang.String getItemType() {
		return (java.lang.String)HiltonUtility.ckNull(this.itemType).trim();
    }

    public void setItemType(java.lang.String itemType) {
		if (!HiltonUtility.isEmpty(itemType) && itemType.length() > 3) {
			itemType = itemType.substring(0, 3);
		}
		this.itemType = itemType;
    }

    public java.lang.String getDescription() {
		return (java.lang.String)HiltonUtility.ckNull(this.description).trim();
    }

    public void setDescription(java.lang.String description) {
		if (!HiltonUtility.isEmpty(description) && description.length() > 30) {
			description = description.substring(0, 30);
		}
		this.description = description;
    }

    public java.math.BigDecimal getMinimumCost() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.minimumCost);
    }

    public void setMinimumCost(java.math.BigDecimal minimumCost) {
        this.minimumCost = minimumCost;
    }

    public java.math.BigDecimal getMaximumCost() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.maximumCost);
    }

    public void setMaximumCost(java.math.BigDecimal maximumCost) {
        this.maximumCost = maximumCost;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("icCostRange", getIcCostRange())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof CostRange) ) return false;
        CostRange castOther = (CostRange) other;
        return new EqualsBuilder()
            .append(this.getIcCostRange(), castOther.getIcCostRange())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getIcCostRange())
            .toHashCode();
    }

}
