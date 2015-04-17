package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class RecentReqItemPK implements Serializable {

    /** identifier field */
    private java.lang.String requisitionerCode;

    /** identifier field */
    private java.lang.String itemNumber;
    
    /** identifier field */
    private java.lang.String itemLocation;
    
    /** full constructor */
    public RecentReqItemPK(java.lang.String requisitionerCode, java.lang.String itemNumber, java.lang.String itemLocation) {
        this.requisitionerCode = requisitionerCode;
        this.itemNumber = itemNumber;
        this.itemLocation = itemLocation;
    }

    /** default constructor */
    public RecentReqItemPK() {
    }

    public java.lang.String getRequisitionerCode() {
		return (java.lang.String)HiltonUtility.ckNull(this.requisitionerCode);
    }

    public void setRequisitionerCode(java.lang.String requisitionerCode) {
        this.requisitionerCode = requisitionerCode;
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
            .append("requisitionerCode", getRequisitionerCode())
            .append("itemNumber", getItemNumber())
            .append("itemLocation", getItemLocation())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof RecentReqItemPK) ) return false;
        RecentReqItemPK castOther = (RecentReqItemPK) other;
        return new EqualsBuilder()
            .append(this.getRequisitionerCode(), castOther.getRequisitionerCode())
            .append(this.getItemNumber(), castOther.getItemNumber())
            .append(this.getItemLocation(), castOther.getItemLocation())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getRequisitionerCode())
            .append(getItemNumber())
            .append(getItemLocation())
            .toHashCode();
    }

}
