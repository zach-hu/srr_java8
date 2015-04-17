package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class ShipToPK implements Serializable {

    /** identifier field */
    private java.math.BigDecimal icHeader;

    /** identifier field */
    private java.math.BigDecimal icLine;

    /** identifier field */
    private String shipToCode;

    /** identifier field */
    private String shipToPriority;

    /** full constructor */
    public ShipToPK(java.math.BigDecimal icHeader, java.math.BigDecimal icLine, java.lang.String shipToCode, java.lang.String shipToPriority) {
        this.icHeader = icHeader;
        this.icLine = icLine;
        this.shipToCode = shipToCode;
        this.shipToPriority = shipToPriority;
    }

    /** default constructor */
    public ShipToPK() {
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

    public java.lang.String getShipToCode() {
		return (java.lang.String)HiltonUtility.ckNull(this.shipToCode);
    }

    public void setShipToCode(java.lang.String shipToCode) {
        this.shipToCode = shipToCode;
    }

    public java.lang.String getShipToPriority() {
		return (java.lang.String)HiltonUtility.ckNull(this.shipToPriority);
    }

    public void setShipToPriority(java.lang.String shipToPriority) {
        this.shipToPriority = shipToPriority;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("icHeader", getIcHeader())
            .append("icLine", getIcLine())
            .append("shipToCode", getShipToCode())
            .append("shipToPriority", getShipToPriority())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof ShipToPK) ) return false;
        ShipToPK castOther = (ShipToPK) other;
        return new EqualsBuilder()
            .append(this.getIcHeader(), castOther.getIcHeader())
            .append(this.getIcLine(), castOther.getIcLine())
            .append(this.getShipToCode(), castOther.getShipToCode())
            .append(this.getShipToPriority(), castOther.getShipToPriority())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getIcHeader())
            .append(getIcLine())
            .append(getShipToCode())
            .append(getShipToPriority())
            .toHashCode();
    }

}
