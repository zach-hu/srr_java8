package com.tsa.puridiom.entity;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class AppLeadTimeView implements Serializable{

	/** identifier field */
    private java.math.BigDecimal icHeader;

    private java.util.Date dateEnd;

    private java.util.Date dateStart;

    private java.math.BigDecimal appLead;

    public AppLeadTimeView() {
	}

    public AppLeadTimeView(java.math.BigDecimal icHeader, java.util.Date dateEnd, java.util.Date dateStart, java.math.BigDecimal appLead)
    {
    	this.icHeader = icHeader;
    	this.dateEnd = dateEnd;
    	this.dateStart = dateStart;
    	this.appLead = appLead;
	}

	public void setIcHeader(java.math.BigDecimal icHeader) {
		this.icHeader = icHeader;
	}

	public java.math.BigDecimal getIcHeader() {
		return icHeader;
	}

	public void setDateEnd(java.util.Date dateEnd) {
		this.dateEnd = dateEnd;
	}

	public java.util.Date getDateEnd() {
		return dateEnd;
	}

	public void setDateStart(java.util.Date dateStart) {
		this.dateStart = dateStart;
	}

	public java.util.Date getDateStart() {
		return dateStart;
	}

	public void setAppLead(java.math.BigDecimal appLead) {
		this.appLead = appLead;
	}

	public java.math.BigDecimal getAppLead() {
		return this.appLead;
	}

	 public String toString() {
	        return new ToStringBuilder(this)
	            .toString();
	 }

	 public boolean equals(Object other) {
	        if ( !(other instanceof AppLeadTimeView) ) return false;
	        AppLeadTimeView castOther = (AppLeadTimeView) other;
	        return new EqualsBuilder()
	            .append(this.getIcHeader(), castOther.getIcHeader())
	            .isEquals();
	    }

	    public int hashCode() {
	        return new HashCodeBuilder()
	            .append(this.getIcHeader())
	            .toHashCode();
	    }

}
