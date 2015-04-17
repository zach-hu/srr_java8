package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class AlertMessageLine implements Serializable {

    /** identifier field */
    private java.math.BigDecimal icLine;

    /** nullable persistent field */
    private java.math.BigDecimal icAlert;

    /** nullable persistent field */
    private java.math.BigDecimal lineSequence;

    /** nullable persistent field */
    private String alertBody;

    /** nullable persistent field */
    private String repeat;

    /** nullable persistent field */
    private String sizeObject;

    /** full constructor */
    public AlertMessageLine(java.math.BigDecimal icLine, java.math.BigDecimal icAlert, java.math.BigDecimal lineSequence,
    							java.lang.String alertBody, java.lang.String repeat, java.lang.String sizeObject) {
    	this.icLine = icLine;
    	this.icAlert = icAlert;
    	this.lineSequence = lineSequence;
    	this.alertBody = alertBody;
    	this.repeat = repeat;
    	this.sizeObject = sizeObject;
    }

    /** default constructor */
    public AlertMessageLine() {
    }

    /** minimal constructor */
    public AlertMessageLine(java.math.BigDecimal icLine) {
        this.icLine = icLine;
    }

    public java.math.BigDecimal getIcLine() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.icLine);
    }

    public void setIcLine(java.math.BigDecimal icLine) {
        this.icLine = icLine;
    }

    public java.math.BigDecimal getIcAlert() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.icAlert);
    }

    public void setIcAlert(java.math.BigDecimal icAlert) {
        this.icAlert = icAlert;
    }

    public java.math.BigDecimal getLineSequence() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.lineSequence);
    }

    public void setLineSequence(java.math.BigDecimal lineSequence) {
        this.lineSequence = lineSequence;
    }

    public java.lang.String getAlertBody() {
		return (java.lang.String)HiltonUtility.ckNull(this.alertBody).trim();
    }

    public void setAlertBody(java.lang.String alertBody) {
		if (!HiltonUtility.isEmpty(alertBody) && alertBody.length() > 500) {
			alertBody = alertBody.substring(0, 500);
		}
		this.alertBody = alertBody;
    }

    public java.lang.String getRepeat() {
		return (java.lang.String)HiltonUtility.ckNull(this.repeat).trim();
    }

    public void setRepeat(java.lang.String repeat) {
		if (!HiltonUtility.isEmpty(repeat) && repeat.length() > 1) {
			repeat = repeat.substring(0, 1);
		}
		this.repeat = repeat;
    }

    public java.lang.String getSizeObject() {
		return (java.lang.String)HiltonUtility.ckNull(this.sizeObject).trim();
    }

    public void setSizeObject(java.lang.String sizeObject) {
		if (!HiltonUtility.isEmpty(sizeObject) && sizeObject.length() > 20) {
			sizeObject = sizeObject.substring(0, 20);
		}
		this.sizeObject = sizeObject;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("icLine", getIcLine())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof AlertMessageLine) ) return false;
        AlertMessageLine castOther = (AlertMessageLine) other;
        return new EqualsBuilder()
            .append(this.getIcLine(), castOther.getIcLine())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getIcLine())
            .toHashCode();
    }

}
