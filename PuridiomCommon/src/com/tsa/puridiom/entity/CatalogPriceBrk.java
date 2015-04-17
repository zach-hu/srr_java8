package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class CatalogPriceBrk implements Serializable {

    /** identifier field */
    private CatalogPriceBrkPK comp_id;

    /** nullable persistent field */
    private java.math.BigDecimal unitPrice;

    /** nullable persistent field */
    private String status;

    /** nullable persistent field */
    private String breakType;

    /** nullable persistent field */
    private String breakFrom;

    /** nullable persistent field */
    private String breakTo;

    /** nullable persistent field */
    private String breakNote;

    /** nullable persistent field */
    private java.math.BigDecimal qtyFrom;

    /** nullable persistent field */
    private java.math.BigDecimal qtyTo;

    /** nullable persistent field */
    private java.util.Date dateFrom;

    /** nullable persistent field */
    private java.util.Date dateTo;

    /** nullable persistent field */
    private String umConv;

    /** nullable persistent field */
    private java.math.BigDecimal umFactor;

    /** persistent field */
    private Catalog catalog;

    /** persistent field */
    private CatalogItem catalogItem;

    /** full constructor */
    public CatalogPriceBrk(CatalogPriceBrkPK comp_id, java.math.BigDecimal unitPrice, java.lang.String status, java.lang.String breakType, java.lang.String breakFrom, java.lang.String breakTo, java.lang.String breakNote, java.math.BigDecimal qtyFrom, java.math.BigDecimal qtyTo, java.util.Date dateFrom, java.util.Date dateTo, java.lang.String umConv, java.math.BigDecimal umFactor, Catalog catalog, CatalogItem catalogItem) {
        this.comp_id = comp_id;
        this.unitPrice = unitPrice;
        this.status = status;
        this.breakType = breakType;
        this.breakFrom = breakFrom;
        this.breakTo = breakTo;
        this.breakNote = breakNote;
        this.qtyFrom = qtyFrom;
        this.qtyTo = qtyTo;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.umConv = umConv;
        this.umFactor = umFactor;
        this.catalog = catalog;
        this.catalogItem = catalogItem;
    }

    /** default constructor */
    public CatalogPriceBrk() {
    }

    /** minimal constructor */
    public CatalogPriceBrk(CatalogPriceBrkPK comp_id, Catalog catalog, CatalogItem catalogItem) {
        this.comp_id = comp_id;
        this.catalog = catalog;
        this.catalogItem = catalogItem;
    }

    public CatalogPriceBrkPK getComp_id() {
        return this.comp_id;
    }

    public void setComp_id(CatalogPriceBrkPK comp_id) {
        this.comp_id = comp_id;
    }

    public java.math.BigDecimal getUnitPrice() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.unitPrice);
    }

    public void setUnitPrice(java.math.BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
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

    public java.lang.String getBreakType() {
		return (java.lang.String)HiltonUtility.ckNull(this.breakType).trim();
    }

    public void setBreakType(java.lang.String breakType) {
		if (!HiltonUtility.isEmpty(breakType) && breakType.length() > 1) {
			breakType = breakType.substring(0, 1);
		}
		this.breakType = breakType;
    }

    public java.lang.String getBreakFrom() {
		return (java.lang.String)HiltonUtility.ckNull(this.breakFrom).trim();
    }

    public void setBreakFrom(java.lang.String breakFrom) {
		if (!HiltonUtility.isEmpty(breakFrom) && breakFrom.length() > 20) {
			breakFrom = breakFrom.substring(0, 20);
		}
		this.breakFrom = breakFrom;
    }

    public java.lang.String getBreakTo() {
		return (java.lang.String)HiltonUtility.ckNull(this.breakTo).trim();
    }

    public void setBreakTo(java.lang.String breakTo) {
		if (!HiltonUtility.isEmpty(breakTo) && breakTo.length() > 20) {
			breakTo = breakTo.substring(0, 20);
		}
		this.breakTo = breakTo;
    }

    public java.lang.String getBreakNote() {
		return (java.lang.String)HiltonUtility.ckNull(this.breakNote).trim();
    }

    public void setBreakNote(java.lang.String breakNote) {
		if (!HiltonUtility.isEmpty(breakNote) && breakNote.length() > 60) {
			breakNote = breakNote.substring(0, 60);
		}
		this.breakNote = breakNote;
    }

    public java.math.BigDecimal getQtyFrom() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.qtyFrom);
    }

    public void setQtyFrom(java.math.BigDecimal qtyFrom) {
        this.qtyFrom = qtyFrom;
    }

    public java.math.BigDecimal getQtyTo() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.qtyTo);
    }

    public void setQtyTo(java.math.BigDecimal qtyTo) {
        this.qtyTo = qtyTo;
    }

    public java.util.Date getDateFrom() {
		return this.dateFrom;
//		return HiltonUtility.ckNull(this.dateFrom);
//		return (java.sql.Date)HiltonUtility.ckNull(this.dateFrom);
    }

    public void setDateFrom(java.util.Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public java.util.Date getDateTo() {
		return this.dateTo;
//		return HiltonUtility.ckNull(this.dateTo);
//		return (java.sql.Date)HiltonUtility.ckNull(this.dateTo);
    }

    public void setDateTo(java.util.Date dateTo) {
        this.dateTo = dateTo;
    }

    public java.lang.String getUmConv() {
		return (java.lang.String)HiltonUtility.ckNull(this.umConv).trim();
    }

    public void setUmConv(java.lang.String umConv) {
		if (!HiltonUtility.isEmpty(umConv) && umConv.length() > 15) {
			umConv = umConv.substring(0, 15);
		}
		this.umConv = umConv;
    }

    public java.math.BigDecimal getUmFactor() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.umFactor);
    }

    public void setUmFactor(java.math.BigDecimal umFactor) {
        this.umFactor = umFactor;
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
        if ( !(other instanceof CatalogPriceBrk) ) return false;
        CatalogPriceBrk castOther = (CatalogPriceBrk) other;
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
