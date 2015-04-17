package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class InvFormCatalogPK implements Serializable {

    /** identifier field */
    private String itemNumber;

    /** identifier field */
    private String invCatid;

    /** full constructor */
    public InvFormCatalogPK(java.lang.String itemNumber, java.lang.String invCatid) {
        this.itemNumber = itemNumber;
        this.invCatid = invCatid;
    }

    /** default constructor */
    public InvFormCatalogPK() {
    }

    public java.lang.String getItemNumber() {
		return (java.lang.String)HiltonUtility.ckNull(this.itemNumber);
    }

    public void setItemNumber(java.lang.String itemNumber) {
        this.itemNumber = itemNumber;
    }

    public java.lang.String getInvCatid() {
		return (java.lang.String)HiltonUtility.ckNull(this.invCatid);
    }

    public void setInvCatid(java.lang.String invCatid) {
        this.invCatid = invCatid;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("itemNumber", getItemNumber())
            .append("invCatid", getInvCatid())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof InvFormCatalogPK) ) return false;
        InvFormCatalogPK castOther = (InvFormCatalogPK) other;
        return new EqualsBuilder()
            .append(this.getItemNumber(), castOther.getItemNumber())
            .append(this.getInvCatid(), castOther.getInvCatid())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getItemNumber())
            .append(getInvCatid())
            .toHashCode();
    }

}
