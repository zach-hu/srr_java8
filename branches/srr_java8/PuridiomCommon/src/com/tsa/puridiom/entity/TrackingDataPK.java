package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class TrackingDataPK implements Serializable {

	 /** identifier field */
    private java.math.BigDecimal icTracking;
	
    /** identifier field */
    private java.math.BigDecimal icHeader;

    /** identifier field */
    private java.math.BigDecimal icLine;

    /** full constructor */
    public TrackingDataPK(java.math.BigDecimal icHeader, java.math.BigDecimal icLine, java.math.BigDecimal icTracking) {
        this.icHeader = icHeader;
        this.icLine = icLine;
        this.icTracking = icTracking;
    }

    /** default constructor */
    public TrackingDataPK() {
    }

    public java.math.BigDecimal geticTracking() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.icTracking);
    }

    public void setIcTracking(java.math.BigDecimal icTracking) {
        this.icTracking = icTracking;
    }
    
    public java.math.BigDecimal getIcHeader() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.icHeader);
    }

    public void setIcHeader(java.math.BigDecimal icHeader) {
        this.icHeader = icHeader;
    }

    public java.math.BigDecimal getIcLine() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.icLine);
    }

    public void setIcLine(java.math.BigDecimal icLine) {
        this.icLine = icLine;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("icHeader", getIcHeader())
            .append("icLine", getIcLine())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof TrackingDataPK) ) return false;
        TrackingDataPK castOther = (TrackingDataPK) other;
        return new EqualsBuilder()
            .append(this.getIcHeader(), castOther.getIcHeader())
            .append(this.getIcLine(), castOther.getIcLine())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getIcHeader())
            .append(getIcLine())
            .toHashCode();
    }

}
