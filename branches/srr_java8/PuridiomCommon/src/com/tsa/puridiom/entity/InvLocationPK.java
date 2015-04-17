package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class InvLocationPK implements Serializable {

    /** identifier field */
    private String itemNumber;

    /** identifier field */
    private String itemLocation;

    /** full constructor */
    public InvLocationPK(java.lang.String itemNumber, java.lang.String itemLocation) {
        this.itemNumber = itemNumber;
        this.itemLocation = itemLocation;
    }

    /** default constructor */
    public InvLocationPK() {
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
            .append("itemNumber", getItemNumber())
            .append("itemLocation", getItemLocation())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof InvLocationPK) ) return false;
        InvLocationPK castOther = (InvLocationPK) other;
        return new EqualsBuilder()
            .append(this.getItemNumber(), castOther.getItemNumber())
            .append(this.getItemLocation(), castOther.getItemLocation())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getItemNumber())
            .append(getItemLocation())
            .toHashCode();
    }

}
