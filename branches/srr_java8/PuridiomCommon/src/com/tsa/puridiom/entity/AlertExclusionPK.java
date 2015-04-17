package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class AlertExclusionPK implements Serializable {

    /** identifier field */
    private String alertId;

    /** identifier field */
    private java.math.BigDecimal icRef;

    /** full constructor */
    public AlertExclusionPK(java.lang.String alertId, java.math.BigDecimal icRef) {
        this.alertId = alertId;
        this.icRef = icRef;
    }

    /** default constructor */
    public AlertExclusionPK() {
    }

    public java.lang.String getAlertId() {
		return (java.lang.String)HiltonUtility.ckNull(this.alertId);
    }

    public void setAlertId(java.lang.String alertId) {
        this.alertId = alertId;
    }

    public java.math.BigDecimal getIcRef() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.icRef);
    }

    public void setIcRef(java.math.BigDecimal icRef) {
        this.icRef = icRef;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("alertId", getAlertId())
            .append("icRef", getIcRef())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof AlertExclusionPK) ) return false;
        AlertExclusionPK castOther = (AlertExclusionPK) other;
        return new EqualsBuilder()
            .append(this.getAlertId(), castOther.getAlertId())
            .append(this.getIcRef(), castOther.getIcRef())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getAlertId())
            .append(getIcRef())
            .toHashCode();
    }

}
