package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class CurrCode implements Serializable {

    /** identifier field */
    private String currencyCode;

    /** nullable persistent field */
    private String currencyName;

    /** nullable persistent field */
    private String symbol;

    /** nullable persistent field */
    private String country;

    /** nullable persistent field */
    private String symbolPlacement;

    /** nullable persistent field */
    private String negativePlacement;

    /** nullable persistent field */
    private String thousandsSeprtr;

    /** nullable persistent field */
    private String decimalSeparator;

    /** nullable persistent field */
    private java.math.BigDecimal decimalDigits;

    /** nullable persistent field */
    private String leadingZero;

    /** nullable persistent field */
    private String positiveFormat;

    /** nullable persistent field */
    private String negativeFormat;

    /** nullable persistent field */
    private java.util.Date dateEntered;

    /** nullable persistent field */
    private java.util.Date dateExpires;

    /** nullable persistent field */
    private String status;

    /** nullable persistent field */
    private String owner;

    /** nullable persistent field */
    private java.math.BigDecimal conversionToBase;

    /** nullable persistent field */
    private java.math.BigDecimal baseToCurrency;
    
    /** identifier field */
    private String isoCurrency;

    /** full constructor */
    public CurrCode(java.lang.String currencyCode, java.lang.String currencyName, java.lang.String symbol, java.lang.String country, java.lang.String symbolPlacement, java.lang.String negativePlacement, java.lang.String thousandsSeprtr, java.lang.String decimalSeparator, java.math.BigDecimal decimalDigits, java.lang.String leadingZero, java.lang.String positiveFormat, java.lang.String negativeFormat, java.util.Date dateEntered, java.util.Date dateExpires, java.lang.String status, java.lang.String owner, java.math.BigDecimal conversionToBase, java.math.BigDecimal baseToCurrency) {
        this.currencyCode = currencyCode;
        this.currencyName = currencyName;
        this.symbol = symbol;
        this.country = country;
        this.symbolPlacement = symbolPlacement;
        this.negativePlacement = negativePlacement;
        this.thousandsSeprtr = thousandsSeprtr;
        this.decimalSeparator = decimalSeparator;
        this.decimalDigits = decimalDigits;
        this.leadingZero = leadingZero;
        this.positiveFormat = positiveFormat;
        this.negativeFormat = negativeFormat;
        this.dateEntered = dateEntered;
        this.dateExpires = dateExpires;
        this.status = status;
        this.owner = owner;
        this.conversionToBase = conversionToBase;
        this.baseToCurrency = baseToCurrency;
    }

    /** default constructor */
    public CurrCode() {
    }

    /** minimal constructor */
    public CurrCode(java.lang.String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public java.lang.String getCurrencyCode() {
		return (java.lang.String)HiltonUtility.ckNull(this.currencyCode).trim();
    }

    public void setCurrencyCode(java.lang.String currencyCode) {
		if (!HiltonUtility.isEmpty(currencyCode) && currencyCode.length() > 15) {
			currencyCode = currencyCode.substring(0, 15);
		}
		this.currencyCode = currencyCode;
    }

    public java.lang.String getCurrencyName() {
		return (java.lang.String)HiltonUtility.ckNull(this.currencyName).trim();
    }

    public void setCurrencyName(java.lang.String currencyName) {
		if (!HiltonUtility.isEmpty(currencyName) && currencyName.length() > 60) {
			currencyName = currencyName.substring(0, 60);
		}
		this.currencyName = currencyName;
    }

    public java.lang.String getSymbol() {
		return (java.lang.String)HiltonUtility.ckNull(this.symbol).trim();
    }

    public void setSymbol(java.lang.String symbol) {
		if (!HiltonUtility.isEmpty(symbol) && symbol.length() > 5) {
			symbol = symbol.substring(0, 5);
		}
		this.symbol = symbol;
    }

    public java.lang.String getCountry() {
		return (java.lang.String)HiltonUtility.ckNull(this.country).trim();
    }

    public void setCountry(java.lang.String country) {
		if (!HiltonUtility.isEmpty(country) && country.length() > 25) {
			country = country.substring(0, 25);
		}
		this.country = country;
    }

    public java.lang.String getSymbolPlacement() {
		return (java.lang.String)HiltonUtility.ckNull(this.symbolPlacement).trim();
    }

    public void setSymbolPlacement(java.lang.String symbolPlacement) {
		if (!HiltonUtility.isEmpty(symbolPlacement) && symbolPlacement.length() > 10) {
			symbolPlacement = symbolPlacement.substring(0, 10);
		}
		this.symbolPlacement = symbolPlacement;
    }

    public java.lang.String getNegativePlacement() {
		return (java.lang.String)HiltonUtility.ckNull(this.negativePlacement).trim();
    }

    public void setNegativePlacement(java.lang.String negativePlacement) {
		if (!HiltonUtility.isEmpty(negativePlacement) && negativePlacement.length() > 10) {
			negativePlacement = negativePlacement.substring(0, 10);
		}
		this.negativePlacement = negativePlacement;
    }

    public java.lang.String getThousandsSeprtr() {
		return (java.lang.String)HiltonUtility.ckNull(this.thousandsSeprtr).trim();
    }

    public void setThousandsSeprtr(java.lang.String thousandsSeprtr) {
		if (!HiltonUtility.isEmpty(thousandsSeprtr) && thousandsSeprtr.length() > 2) {
			thousandsSeprtr = thousandsSeprtr.substring(0, 2);
		}
		this.thousandsSeprtr = thousandsSeprtr;
    }

    public java.lang.String getDecimalSeparator() {
		return (java.lang.String)HiltonUtility.ckNull(this.decimalSeparator).trim();
    }

    public void setDecimalSeparator(java.lang.String decimalSeparator) {
		if (!HiltonUtility.isEmpty(decimalSeparator) && decimalSeparator.length() > 2) {
			decimalSeparator = decimalSeparator.substring(0, 2);
		}
		this.decimalSeparator = decimalSeparator;
    }

    public java.math.BigDecimal getDecimalDigits() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.decimalDigits);
    }

    public void setDecimalDigits(java.math.BigDecimal decimalDigits) {
        this.decimalDigits = decimalDigits;
    }

    public java.lang.String getLeadingZero() {
		return (java.lang.String)HiltonUtility.ckNull(this.leadingZero).trim();
    }

    public void setLeadingZero(java.lang.String leadingZero) {
		if (!HiltonUtility.isEmpty(leadingZero) && leadingZero.length() > 1) {
			leadingZero = leadingZero.substring(0, 1);
		}
		this.leadingZero = leadingZero;
    }

    public java.lang.String getPositiveFormat() {
		return (java.lang.String)HiltonUtility.ckNull(this.positiveFormat).trim();
    }

    public void setPositiveFormat(java.lang.String positiveFormat) {
		if (!HiltonUtility.isEmpty(positiveFormat) && positiveFormat.length() > 15) {
			positiveFormat = positiveFormat.substring(0, 15);
		}
		this.positiveFormat = positiveFormat;
    }

    public java.lang.String getNegativeFormat() {
		return (java.lang.String)HiltonUtility.ckNull(this.negativeFormat).trim();
    }

    public void setNegativeFormat(java.lang.String negativeFormat) {
		if (!HiltonUtility.isEmpty(negativeFormat) && negativeFormat.length() > 15) {
			negativeFormat = negativeFormat.substring(0, 15);
		}
		this.negativeFormat = negativeFormat;
    }

    public java.util.Date getDateEntered() {
		return this.dateEntered;
//		return HiltonUtility.ckNull(this.dateEntered);
//		return (java.sql.Date)HiltonUtility.ckNull(this.dateEntered);
    }

    public void setDateEntered(java.util.Date dateEntered) {
        this.dateEntered = dateEntered;
    }

    public java.util.Date getDateExpires() {
		return this.dateExpires;
//		return HiltonUtility.ckNull(this.dateExpires);
//		return (java.sql.Date)HiltonUtility.ckNull(this.dateExpires);
    }

    public void setDateExpires(java.util.Date dateExpires) {
        this.dateExpires = dateExpires;
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

    public java.lang.String getOwner() {
		return (java.lang.String)HiltonUtility.ckNull(this.owner).trim();
    }

    public void setOwner(java.lang.String owner) {
		if (!HiltonUtility.isEmpty(owner) && owner.length() > 15) {
			owner = owner.substring(0, 15);
		}
		this.owner = owner;
    }

    public java.math.BigDecimal getConversionToBase() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.conversionToBase);
    }

    public void setConversionToBase(java.math.BigDecimal conversionToBase) {
        this.conversionToBase = conversionToBase;
    }

    public java.math.BigDecimal getBaseToCurrency() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.baseToCurrency);
    }

    public void setBaseToCurrency(java.math.BigDecimal baseToCurrency) {
        this.baseToCurrency = baseToCurrency;
    }

    /**
	 * @return the isoCurrency
	 */
	public String getIsoCurrency()
	{
		return (java.lang.String) HiltonUtility.ckNull(this.isoCurrency).trim();
	}

	/**
	 * @param isoCurrency
	 *            the isoCurrency to set
	 */
	public void setIsoCurrency(String isoCurrency)
	{
		if (!HiltonUtility.isEmpty(isoCurrency) && isoCurrency.length() > 15)
		{
			isoCurrency = isoCurrency.substring(0, 15);
		}
		this.isoCurrency = isoCurrency;
	}

	public String toString() {
        return new ToStringBuilder(this)
            .append("currencyCode", getCurrencyCode())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof CurrCode) ) return false;
        CurrCode castOther = (CurrCode) other;
        return new EqualsBuilder()
            .append(this.getCurrencyCode(), castOther.getCurrencyCode())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getCurrencyCode())
            .toHashCode();
    }

}
