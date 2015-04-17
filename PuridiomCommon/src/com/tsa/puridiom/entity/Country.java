package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class Country implements Serializable {

    /** identifier field */
    private String countryCode;

    /** nullable persistent field */
    private String countryName;

    /** nullable persistent field */
    private String currencyCode;

    /** nullable persistent field */
    private String timeZone;

    /** nullable persistent field */
    private String dateFormat;

    /** nullable persistent field */
    private String language;

    /** nullable persistent field */
    private java.util.Date dateEntered;

    /** nullable persistent field */
    private java.util.Date dateExpires;

    /** nullable persistent field */
    private String status;

    /** nullable persistent field */
    private String owner;

    /** full constructor */
    public Country(java.lang.String countryCode, java.lang.String countryName, java.lang.String currencyCode, java.lang.String timeZone, java.util.Date dateEntered, java.util.Date dateExpires, java.lang.String status, java.lang.String owner, java.lang.String dateFormat, java.lang.String language) {
        this.countryCode = countryCode;
        this.countryName = countryName;
        this.currencyCode = currencyCode;
        this.timeZone = timeZone;
        this.dateEntered = dateEntered;
        this.dateExpires = dateExpires;
        this.status = status;
        this.owner = owner;
        this.dateFormat = dateFormat;
        this.language = language;
    }

    /** default constructor */
    public Country() {
    }

    /** minimal constructor */
    public Country(java.lang.String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public java.lang.String getCountryCode() {
		return (java.lang.String)HiltonUtility.ckNull(this.countryCode).trim();
    }

    public void setCountryCode(java.lang.String countryCode) {
		if (!HiltonUtility.isEmpty(countryCode) && countryCode.length() > 15) {
			countryCode = countryCode.substring(0, 15);
		}
		this.countryCode = countryCode;
    }

    public java.lang.String getCountryName() {
		return (java.lang.String)HiltonUtility.ckNull(this.countryName).trim();
    }

    public void setCountryName(java.lang.String countryName) {
		if (!HiltonUtility.isEmpty(countryName) && countryName.length() > 255) {
		    countryName = countryName.substring(0, 255);
		}
		this.countryName = countryName;
    }

    public java.lang.String getCurrencyCode() {
		return (java.lang.String)HiltonUtility.ckNull(this.currencyCode).trim();
    }

    public void setCurrencyCode(java.lang.String currencyCode) {
		if (!HiltonUtility.isEmpty(currencyCode) && currencyCode.length() > 3) {
		    currencyCode = currencyCode.substring(0, 3);
		}
		this.currencyCode = currencyCode;
    }

    public String getTimeZone() {
        return (java.lang.String)HiltonUtility.ckNull(this.timeZone).trim();
    }

    public void setTimeZone(String timeZone) {
		if (!HiltonUtility.isEmpty(timeZone) && timeZone.length() > 3) {
		    timeZone = timeZone.substring(0, 3);
		}
        this.timeZone = timeZone;
    }

    public java.util.Date getDateEntered() {
		return this.dateEntered;
    }

    public void setDateEntered(java.util.Date dateEntered) {
        this.dateEntered = dateEntered;
    }

    public java.util.Date getDateExpires() {
		return this.dateExpires;
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

    public String getDateFormat() {
        return (java.lang.String)HiltonUtility.ckNull(this.dateFormat).trim();
    }

    public void setDateFormat(String dateFormat) {
        if (!HiltonUtility.isEmpty(dateFormat) && dateFormat.length() > 10) {
            dateFormat = dateFormat.substring(0, 10);
        }
        this.dateFormat = dateFormat;
    }

    public String getLanguage() {
        return (java.lang.String)HiltonUtility.ckNull(this.language).trim();
    }

    public void setLanguage(String language) {
        if (!HiltonUtility.isEmpty(language) && language.length() > 15) {
            language = language.substring(0, 15);
        }
        this.language = language;
    }
    public String toString() {
        return new ToStringBuilder(this)
            .append("countryCode", getCountryCode())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof Country) ) return false;
        Country castOther = (Country) other;
        return new EqualsBuilder()
            .append(this.getCountryCode(), castOther.getCountryCode())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getCurrencyCode())
            .toHashCode();
    }

}
