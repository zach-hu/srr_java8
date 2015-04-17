package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class CatalogPrice implements Serializable {

    /** identifier field */
    private CatalogPricePK comp_id;

    /** nullable persistent field */
    private java.math.BigDecimal currentCost;

    /** nullable persistent field */
    private java.math.BigDecimal minimumCost;

    /** nullable persistent field */
    private java.math.BigDecimal maximumCost;

    /** nullable persistent field */
    private java.util.Date minCostDate;

    /** nullable persistent field */
    private java.util.Date maxCostDate;

    /** full constructor */
    public CatalogPrice(CatalogPricePK comp_id, java.math.BigDecimal currentCost, java.math.BigDecimal minimumCost, java.math.BigDecimal maximumCost, java.util.Date minCostDate, java.util.Date maxCostDate) {
        this.comp_id = comp_id;
        this.currentCost = currentCost;
        this.minimumCost = minimumCost;
        this.maximumCost = maximumCost;
        this.minCostDate = minCostDate;
        this.maxCostDate = maxCostDate;
    }

    /** default constructor */
    public CatalogPrice() {
    }

    /** minimal constructor */
    public CatalogPrice(CatalogPricePK comp_id) {
        this.comp_id = comp_id;
    }

    public CatalogPricePK getComp_id() {
        return this.comp_id;
    }

    public void setComp_id(CatalogPricePK comp_id) {
        this.comp_id = comp_id;
    }

    public java.math.BigDecimal getCurrentCost() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.currentCost);
    }

    public void setCurrentCost(java.math.BigDecimal currentCost) {
        this.currentCost = currentCost;
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

    public java.util.Date getMinCostDate() {
		return this.minCostDate;
//		return HiltonUtility.ckNull(this.minCostDate);
//		return (java.sql.Date)HiltonUtility.ckNull(this.minCostDate);
    }

    public void setMinCostDate(java.util.Date minCostDate) {
        this.minCostDate = minCostDate;
    }

    public java.util.Date getMaxCostDate() {
		return this.maxCostDate;
//		return HiltonUtility.ckNull(this.maxCostDate);
//		return (java.sql.Date)HiltonUtility.ckNull(this.maxCostDate);
    }

    public void setMaxCostDate(java.util.Date maxCostDate) {
        this.maxCostDate = maxCostDate;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("comp_id", getComp_id())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof CatalogPrice) ) return false;
        CatalogPrice castOther = (CatalogPrice) other;
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
