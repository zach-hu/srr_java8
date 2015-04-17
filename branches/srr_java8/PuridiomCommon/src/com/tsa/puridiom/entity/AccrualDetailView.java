package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class AccrualDetailView implements Serializable {

	/** identifier field */
    private String poNumber;

    /** persistent field */
    private java.math.BigDecimal rel;

    /** nullable persistent field */
    private java.math.BigDecimal rev;

    /** nullable persistent field */
    private String division;

    /** nullable persistent field */
    private String currency;

	/** nullable persistent field */
    private String buyer;

    /** nullable persistent field */
    private java.util.Date poDate;

    /** nullable persistent field */
    private java.util.Date effDate;

    /** nullable persistent field */
    private java.util.Date expDate;

    /** nullable persistent field */
    private String supplier;

    /** nullable persistent field */
    private String headerStatus;

    /** nullable persistent field */
    private String headerTaxCode;

    /** nullable persistent field */
    private String reqNumber;

    /** nullable persistent field */
    private java.math.BigDecimal line;

    /** nullable persistent field */
    private String description;

    /** nullable persistent field */
    private java.math.BigDecimal qty;

    /** nullable persistent field */
    private String uom;

    /** nullable persistent field */
    private java.math.BigDecimal unitPrice;

    /** nullable persistent field */
    private java.math.BigDecimal lineTotal;

    /** nullable persistent field */
    private java.math.BigDecimal lineShip;

    /** nullable persistent field */
    private String lineTaxCode;

    /** nullable persistent field */
    private java.math.BigDecimal taxAmount;

    /** nullable persistent field */
    private java.math.BigDecimal shippingTax;

    /** nullable persistent field */
    private String taxable;

    /** nullable persistent field */
    private java.math.BigDecimal accrualTaxPerc;

    /** nullable persistent field */
    private java.math.BigDecimal accrualQtyRcvd;

    /** nullable persistent field */
    private java.math.BigDecimal accrualQtyPaid;

    /** nullable persistent field */
    private String lineStatus;

    /** nullable persistent field */
    private String acc01;

    /** nullable persistent field */
    private String acc02;

    /** nullable persistent field */
    private String acc03;

    /** nullable persistent field */
    private String acc04;

    /** nullable persistent field */
    private String acc05;

    /** nullable persistent field */
    private String acc06;

    /** nullable persistent field */
    private java.math.BigDecimal accAmount;

    /** full constructor */
    public AccrualDetailView(java.lang.String division, java.lang.String poNumber, java.math.BigDecimal rel, java.math.BigDecimal rev, java.lang.String currency, java.lang.String buyer, java.util.Date poDate, java.util.Date effDate, java.util.Date expDate, java.lang.String supplier, java.lang.String headerStatus, java.lang.String headerTaxCode, java.lang.String reqNumber, java.math.BigDecimal line, java.lang.String description, java.math.BigDecimal qty, java.lang.String uom, java.math.BigDecimal unitPrice, java.math.BigDecimal lineTotal, java.math.BigDecimal lineShip, java.lang.String lineTaxCode, java.math.BigDecimal taxAmount, java.math.BigDecimal shippingTax, java.lang.String taxable, java.math.BigDecimal accrualTaxPerc, java.math.BigDecimal accrualQtyRcvd, java.math.BigDecimal accrualQtyPaid, java.lang.String lineStatus, java.lang.String acc01, java.lang.String acc02, java.lang.String acc03, java.lang.String acc04, java.lang.String acc05, java.lang.String acc06, java.math.BigDecimal accAmount) {
        this.division = division;
        this.poNumber = poNumber;
        this.rel = rel;
        this.rev = rev;
        this.currency = currency;
        this.buyer = buyer;
        this.poDate = poDate;
        this.effDate = effDate;
        this.expDate = expDate;
        this.supplier = supplier;
        this.headerStatus = headerStatus;
        this.headerTaxCode = headerTaxCode;
        this.reqNumber = reqNumber;
        this.line = line;
        this.description = description;
        this.qty = qty;
        this.uom = uom;
        this.unitPrice = unitPrice;
        this.lineTotal = lineTotal;
        this.lineShip = lineShip;
        this.lineTaxCode = lineTaxCode;
        this.taxAmount = taxAmount;
        this.shippingTax = shippingTax;
        this.taxable = taxable;
        this.accrualTaxPerc = accrualTaxPerc;
        this.accrualQtyRcvd = accrualQtyRcvd;
        this.accrualQtyPaid = accrualQtyPaid;
        this.lineStatus = lineStatus;
        this.acc01 = acc01;
        this.acc02 = acc02;
        this.acc03 = acc03;
        this.acc04 = acc04;
        this.acc05 = acc05;
        this.acc06 = acc06;
        this.accAmount = accAmount;
    }

    /** default constructor */
    public AccrualDetailView() {
    }

    public java.lang.String getDivision() {
		return (java.lang.String)HiltonUtility.ckNull(this.division).trim();
    }

    public void setDivision(java.lang.String division) {
		if (!HiltonUtility.isEmpty(division) && division.length() > 15) {
			division = division.substring(0, 15);
		}
		this.division = division;
    }

    public java.lang.String getPoNumber() {
		return (java.lang.String)HiltonUtility.ckNull(this.poNumber).trim();
    }

    public void setPoNumber(java.lang.String poNumber) {
		if (!HiltonUtility.isEmpty(poNumber) && poNumber.length() > 20) {
			poNumber = poNumber.substring(0, 20);
		}
		this.poNumber = poNumber;
    }

    public java.math.BigDecimal getRel() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.rel);
    }

    public void setRel(java.math.BigDecimal rel) {
        this.rel = rel;
    }

    public java.math.BigDecimal getRev() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.rev);
    }

    public void setRev(java.math.BigDecimal rev) {
        this.rel = rev;
    }

    public java.lang.String getCurrency() {
		return (java.lang.String)HiltonUtility.ckNull(this.currency).trim();
    }

    public void setCurrency(java.lang.String currency) {
		if (!HiltonUtility.isEmpty(currency) && currency.length() > 10) {
			currency = currency.substring(0, 10);
		}
		this.currency = currency;
    }

    public java.lang.String getBuyer() {
		return (java.lang.String)HiltonUtility.ckNull(this.buyer).trim();
    }

    public void setBuyer(java.lang.String buyer) {
		if (!HiltonUtility.isEmpty(buyer) && buyer.length() > 15) {
			buyer = buyer.substring(0, 15);
		}
		this.buyer = buyer;
    }

    public java.util.Date getPoDate() {
        return this.poDate;
    }

    public void setPoDate(java.util.Date poDate) {
        this.poDate = poDate;
    }

    public java.util.Date getEffDate() {
        return this.effDate;
    }

    public void setEffDate(java.util.Date effDate) {
        this.effDate = effDate;
    }

    public java.util.Date getExpDate() {
        return this.expDate;
    }

    public void setExpDate(java.util.Date expDate) {
        this.expDate = expDate;
    }

    public java.lang.String getSupplier() {
		return (java.lang.String)HiltonUtility.ckNull(this.supplier).trim();
    }

    public void setSupplier(java.lang.String supplier) {
		if (!HiltonUtility.isEmpty(supplier) && supplier.length() > 15) {
			supplier = supplier.substring(0, 15);
		}
		this.supplier = supplier;
    }

    public java.lang.String getHeaderStatus() {
		return (java.lang.String)HiltonUtility.ckNull(this.headerStatus).trim();
    }

    public void setHeaderStatus(java.lang.String headerStatus) {
		if (!HiltonUtility.isEmpty(headerStatus) && headerStatus.length() > 4) {
			headerStatus = headerStatus.substring(0, 4);
		}
		this.headerStatus = headerStatus;
    }

    public java.lang.String getHeaderTaxCode() {
		return (java.lang.String)HiltonUtility.ckNull(this.headerTaxCode).trim();
    }

    public void setHeaderTaxCode(java.lang.String headerTaxCode) {
		if (!HiltonUtility.isEmpty(headerTaxCode) && headerTaxCode.length() > 15) {
			headerTaxCode = headerTaxCode.substring(0, 15);
		}
		this.headerTaxCode = headerTaxCode;
    }

    public java.lang.String getReqNumber() {
		return (java.lang.String)HiltonUtility.ckNull(this.reqNumber).trim();
    }

    public void setReqNumber(java.lang.String reqNumber) {
		if (!HiltonUtility.isEmpty(reqNumber) && reqNumber.length() > 20) {
			reqNumber = reqNumber.substring(0, 20);
		}
		this.reqNumber = reqNumber;
    }

    public java.math.BigDecimal getLine() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.line);
    }

    public void setLine(java.math.BigDecimal line) {
        this.line = line;
    }

    public java.lang.String getDescription() {
		return (java.lang.String)HiltonUtility.ckNull(this.description).trim();
    }

    public void setDescription(java.lang.String description) {
		if (!HiltonUtility.isEmpty(description) && description.length() > 2000) {
			description = description.substring(0, 2000);
		}
		this.description = description;
    }

    public java.math.BigDecimal getQty() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.qty);
    }

    public void setQty(java.math.BigDecimal qty) {
        this.qty = qty;
    }

    public java.lang.String getUom() {
		return (java.lang.String)HiltonUtility.ckNull(this.uom).trim();
    }

    public void setUom(java.lang.String uom) {
		if (!HiltonUtility.isEmpty(uom) && uom.length() > 15) {
			uom = uom.substring(0, 15);
		}
		this.uom = uom;
    }

    public java.math.BigDecimal getUnitPrice() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.unitPrice);
    }

    public void setUnitPrice(java.math.BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public java.math.BigDecimal getLineTotal() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.lineTotal);
    }

    public void setLineTotal(java.math.BigDecimal lineTotal) {
        this.lineTotal = lineTotal;
    }

    public java.math.BigDecimal getLineShip() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.lineShip);
    }

    public void setLineShip(java.math.BigDecimal lineShip) {
        this.lineShip = lineShip;
    }

    public java.lang.String getLineTaxCode() {
		return (java.lang.String)HiltonUtility.ckNull(this.lineTaxCode).trim();
    }

    public void setLineTaxCode(java.lang.String lineTaxCode) {
		if (!HiltonUtility.isEmpty(lineTaxCode) && lineTaxCode.length() > 12) {
			lineTaxCode = lineTaxCode.substring(0, 12);
		}
		this.lineTaxCode = lineTaxCode;
    }

    public java.math.BigDecimal getTaxAmount() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.taxAmount);
    }

    public void setTaxAmount(java.math.BigDecimal taxAmount) {
        this.taxAmount = taxAmount;
    }

    public java.math.BigDecimal getShippingTax() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.shippingTax);
    }

    public void setShippingTax(java.math.BigDecimal shippingTax) {
        this.shippingTax = shippingTax;
    }

    public java.lang.String getTaxable() {
		return (java.lang.String)HiltonUtility.ckNull(this.taxable).trim();
    }

    public void setTaxable(java.lang.String taxable) {
		if (!HiltonUtility.isEmpty(taxable) && taxable.length() > 1) {
			taxable = taxable.substring(0, 1);
		}
		this.taxable = taxable;
    }

    public java.math.BigDecimal getAccrualTaxPerc() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.accrualTaxPerc);
    }

    public void setAccrualTaxPerc(java.math.BigDecimal accrualTaxPerc) {
        this.accrualTaxPerc = accrualTaxPerc;
    }

    public java.math.BigDecimal getAccrualQtyRcvd() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.accrualQtyRcvd);
    }

    public void setAccrualQtyRcvd(java.math.BigDecimal accrualQtyRcvd) {
        this.accrualQtyRcvd = accrualQtyRcvd;
    }

    public java.math.BigDecimal getAccrualQtyPaid() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.accrualQtyPaid);
    }

    public void setAccrualQtyPaid(java.math.BigDecimal accrualQtyPaid) {
        this.accrualQtyPaid = accrualQtyPaid;
    }

    public java.lang.String getLineStatus() {
		return (java.lang.String)HiltonUtility.ckNull(this.lineStatus).trim();
    }

    public void setLineStatus(java.lang.String lineStatus) {
		if (!HiltonUtility.isEmpty(lineStatus) && lineStatus.length() > 4) {
			lineStatus = lineStatus.substring(0, 4);
		}
		this.lineStatus = lineStatus;
    }

    public java.lang.String getAcc01() {
		return (java.lang.String)HiltonUtility.ckNull(this.acc01).trim();
    }

    public void setAcc01(java.lang.String acc01) {
		if (!HiltonUtility.isEmpty(acc01) && acc01.length() > 50) {
			acc01 = acc01.substring(0, 50);
		}
		this.acc01 = acc01;
    }

    public java.lang.String getAcc02() {
		return (java.lang.String)HiltonUtility.ckNull(this.acc02).trim();
    }

    public void setAcc02(java.lang.String acc02) {
		if (!HiltonUtility.isEmpty(acc02) && acc02.length() > 50) {
			acc02 = acc02.substring(0, 50);
		}
		this.acc02 = acc02;
    }

    public java.lang.String getAcc03() {
		return (java.lang.String)HiltonUtility.ckNull(this.acc03).trim();
    }

    public void setAcc03(java.lang.String acc03) {
		if (!HiltonUtility.isEmpty(acc03) && acc03.length() > 50) {
			acc03 = acc03.substring(0, 50);
		}
		this.acc03 = acc03;
    }

    public java.lang.String getAcc04() {
		return (java.lang.String)HiltonUtility.ckNull(this.acc04).trim();
    }

    public void setAcc04(java.lang.String acc04) {
		if (!HiltonUtility.isEmpty(acc04) && acc04.length() > 50) {
			acc04 = acc04.substring(0, 50);
		}
		this.acc04 = acc04;
    }

    public java.lang.String getAcc05() {
		return (java.lang.String)HiltonUtility.ckNull(this.acc05).trim();
    }

    public void setAcc05(java.lang.String acc05) {
		if (!HiltonUtility.isEmpty(acc05) && acc05.length() > 50) {
			acc05 = acc05.substring(0, 50);
		}
		this.acc05 = acc05;
    }

    public java.lang.String getAcc06() {
		return (java.lang.String)HiltonUtility.ckNull(this.acc06).trim();
    }

    public void setAcc06(java.lang.String acc06) {
		if (!HiltonUtility.isEmpty(acc06) && acc06.length() > 50) {
			acc06 = acc06.substring(0, 50);
		}
		this.acc06 = acc06;
    }

    public java.math.BigDecimal getAccAmount() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.accAmount);
    }

    public void setAccAmount(java.math.BigDecimal accAmount) {
        this.accAmount = accAmount;
    }

/*    public String toString() {
        return new ToStringBuilder(this)
            .append("icIvcLine", getIcIvcLine())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof AccrualDetailView) ) return false;
        AccrualDetailView castOther = (AccrualDetailView) other;
        return new EqualsBuilder()
            .append(this.getIcIvcLine(), castOther.getIcIvcLine())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getIcIvcLine())
            .toHashCode();
    }*/

}