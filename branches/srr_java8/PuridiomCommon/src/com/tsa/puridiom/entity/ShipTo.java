package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class ShipTo implements Serializable {

    /** identifier field */
    private com.tsa.puridiom.entity.ShipToPK comp_id;

    /** nullable persistent field */
    private java.math.BigDecimal quantity;

    /** nullable persistent field */
    private String attention;

    /** nullable persistent field */
    private java.util.Date shipDate;

    /** nullable persistent field */
    private String codeType;
    
    private Address shipToAddress;

    /** full constructor */
    public ShipTo(com.tsa.puridiom.entity.ShipToPK comp_id, java.math.BigDecimal quantity, java.lang.String attention, java.util.Date shipDate, java.lang.String codeType) {
        this.comp_id = comp_id;
        this.quantity = quantity;
        this.attention = attention;
        this.shipDate = shipDate;
        this.codeType = codeType;
    }

    /** default constructor */
    public ShipTo() {
    }

    /** minimal constructor */
    public ShipTo(com.tsa.puridiom.entity.ShipToPK comp_id) {
        this.comp_id = comp_id;
    }

    public com.tsa.puridiom.entity.ShipToPK getComp_id() {
        return this.comp_id;
    }

    public void setComp_id(com.tsa.puridiom.entity.ShipToPK comp_id) {
        this.comp_id = comp_id;
    }

    public java.math.BigDecimal getQuantity() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.quantity);
    }

    public void setQuantity(java.math.BigDecimal quantity) {
        this.quantity = quantity;
    }

    public java.lang.String getAttention() {
		return (java.lang.String)HiltonUtility.ckNull(this.attention).trim();
    }

    public void setAttention(java.lang.String attention) {
		if (!HiltonUtility.isEmpty(attention) && attention.length() > 40) {
			attention = attention.substring(0, 40);
		}
		this.attention = attention;
    }

    public java.util.Date getShipDate() {
		return this.shipDate;
//		return HiltonUtility.ckNull(this.shipDate);
//		return (java.sql.Date)HiltonUtility.ckNull(this.shipDate);
    }

    public void setShipDate(java.util.Date shipDate) {
        this.shipDate = shipDate;
    }

    public java.lang.String getCodeType() {
		return (java.lang.String)HiltonUtility.ckNull(this.codeType).trim();
    }

    public void setCodeType(java.lang.String codeType) {
		if (!HiltonUtility.isEmpty(codeType) && codeType.length() > 1) {
			codeType = codeType.substring(0, 1);
		}
		this.codeType = codeType;
    }

	public Address getShipToAddress() {
		
		
		
		return this.shipToAddress;
	}

	public void setShipToAddress(Address shipToAddress) {
		this.shipToAddress = shipToAddress;
	}
	
    public String toString() {
        return new ToStringBuilder(this)
            .append("comp_id", getComp_id())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof ShipTo) ) return false;
        ShipTo castOther = (ShipTo) other;
        return new EqualsBuilder()
            .append(this.getComp_id(), castOther.getComp_id())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getComp_id())
            .toHashCode();
    }
    
    public String getShipToCode()
    {
        return this.getComp_id().getShipToCode();
    }

}
