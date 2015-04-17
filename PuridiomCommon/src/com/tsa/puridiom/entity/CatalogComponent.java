package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class CatalogComponent implements Serializable {

    /** identifier field */
    private CatalogComponentPK comp_id;

    /** nullable persistent field */
    private String optionId;

    /** nullable persistent field */
    private String legendCode;

    /** nullable persistent field */
    private String actionCode;

    /** nullable persistent field */
    private java.math.BigDecimal optionPrice;

    /** nullable persistent field */
    private String optionDecript;

    /** persistent field */
    private Catalog catalog;

    /** persistent field */
    private CatalogItem catalogItem;

    /** full constructor */
    public CatalogComponent(CatalogComponentPK comp_id, java.lang.String optionId, java.lang.String legendCode, java.lang.String actionCode, java.math.BigDecimal optionPrice, java.lang.String optionDecript, Catalog catalog, CatalogItem catalogItem) {
        this.comp_id = comp_id;
        this.optionId = optionId;
        this.legendCode = legendCode;
        this.actionCode = actionCode;
        this.optionPrice = optionPrice;
        this.optionDecript = optionDecript;
        this.catalog = catalog;
        this.catalogItem = catalogItem;
    }

    /** default constructor */
    public CatalogComponent() {
    }

    /** minimal constructor */
    public CatalogComponent(CatalogComponentPK comp_id, Catalog catalog, CatalogItem catalogItem) {
        this.comp_id = comp_id;
        this.catalog = catalog;
        this.catalogItem = catalogItem;
    }

    public CatalogComponentPK getComp_id() {
        return this.comp_id;
    }

    public void setComp_id(CatalogComponentPK comp_id) {
        this.comp_id = comp_id;
    }

    public java.lang.String getOptionId() {
		return (java.lang.String)HiltonUtility.ckNull(this.optionId).trim();
    }

    public void setOptionId(java.lang.String optionId) {
		if (!HiltonUtility.isEmpty(optionId) && optionId.length() > 15) {
			optionId = optionId.substring(0, 15);
		}
		this.optionId = optionId;
    }

    public java.lang.String getLegendCode() {
		return (java.lang.String)HiltonUtility.ckNull(this.legendCode).trim();
    }

    public void setLegendCode(java.lang.String legendCode) {
		if (!HiltonUtility.isEmpty(legendCode) && legendCode.length() > 15) {
			legendCode = legendCode.substring(0, 15);
		}
		this.legendCode = legendCode;
    }

    public java.lang.String getActionCode() {
		return (java.lang.String)HiltonUtility.ckNull(this.actionCode).trim();
    }

    public void setActionCode(java.lang.String actionCode) {
		if (!HiltonUtility.isEmpty(actionCode) && actionCode.length() > 1) {
			actionCode = actionCode.substring(0, 1);
		}
		this.actionCode = actionCode;
    }

    public java.math.BigDecimal getOptionPrice() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.optionPrice);
    }

    public void setOptionPrice(java.math.BigDecimal optionPrice) {
        this.optionPrice = optionPrice;
    }

    public java.lang.String getOptionDecript() {
		return (java.lang.String)HiltonUtility.ckNull(this.optionDecript).trim();
    }

    public void setOptionDecript(java.lang.String optionDecript) {
		if (!HiltonUtility.isEmpty(optionDecript) && optionDecript.length() > 255) {
			optionDecript = optionDecript.substring(0, 255);
		}
		this.optionDecript = optionDecript;
    }

    public Catalog getCatalog() {
		
		
		
        return this.catalog;
    }

    public void setCatalog(Catalog catalog) {
        this.catalog = catalog;
    }

    public CatalogItem getCatalogItem() {
		
		
		
        return this.catalogItem;
    }

    public void setCatalogItem(CatalogItem catalogItem) {
        this.catalogItem = catalogItem;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("comp_id", getComp_id())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof CatalogComponent) ) return false;
        CatalogComponent castOther = (CatalogComponent) other;
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
