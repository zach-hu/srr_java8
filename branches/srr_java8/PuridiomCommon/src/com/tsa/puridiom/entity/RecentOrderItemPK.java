package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class RecentOrderItemPK implements Serializable {

    /** identifier field */
    private java.lang.String buyerCode;

    /** identifier field */
    private java.lang.String itemNumber;
    
    /** identifier field */
    private java.lang.String itemLocation;
    
    /** full constructor */
    public RecentOrderItemPK(java.lang.String buyerCode, java.lang.String itemNumber, java.lang.String itemLocation) {
        this.buyerCode = buyerCode;
        this.itemNumber = itemNumber;
        this.itemLocation = itemLocation;
    }

    /** default constructor */
    public RecentOrderItemPK() {
    }

    public java.lang.String getBuyerCode() {
		return (java.lang.String)HiltonUtility.ckNull(this.buyerCode);
    }

    public void setBuyerCode(java.lang.String buyerCode) {
        this.buyerCode = buyerCode;
    }
    
    public java.lang.String getItemNumber() {
		return (java.lang.String)HiltonUtility.ckNull(this.itemNumber);
    }

    public void setItemNumber(java.lang.String itemNumber) {
        this.itemNumber = itemNumber;
    }
    
    public java.lang.String getItemLocation() {
		return (java.lang.String)HiltonUtility.ckNull(this.itemLocation);
    }

    public void setItemLocation(java.lang.String itemLocation) {
        this.itemLocation = itemLocation;
    }
    
    public String toString() {
        return new ToStringBuilder(this)
            .append("buyerCode", getBuyerCode())
            .append("itemNumber", getItemNumber())
            .append("itemLocation", getItemLocation())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof RecentOrderItemPK) ) return false;
        RecentOrderItemPK castOther = (RecentOrderItemPK) other;
        return new EqualsBuilder()
            .append(this.getBuyerCode(), castOther.getBuyerCode())
            .append(this.getItemNumber(), castOther.getItemNumber())
            .append(this.getItemLocation(), castOther.getItemLocation())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getBuyerCode())
            .append(getItemNumber())
            .append(getItemLocation())
            .toHashCode();
    }

}
