package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class InvFormProductPK implements Serializable {

    /** identifier field */
    private String itemNumber;

    /** identifier field */
    private String productId;

    /** full constructor */
    public InvFormProductPK(java.lang.String itemNumber, java.lang.String productId) {
        this.itemNumber = itemNumber;
        this.productId = productId;
    }

    /** default constructor */
    public InvFormProductPK() {
    }

    public java.lang.String getItemNumber() {
		return (java.lang.String)HiltonUtility.ckNull(this.itemNumber);
    }

    public void setItemNumber(java.lang.String itemNumber) {
        this.itemNumber = itemNumber;
    }

    public java.lang.String getProductId() {
		return (java.lang.String)HiltonUtility.ckNull(this.productId);
    }

    public void setProductId(java.lang.String productId) {
        this.productId = productId;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("itemNumber", getItemNumber())
            .append("productId", getProductId())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof InvFormProductPK) ) return false;
        InvFormProductPK castOther = (InvFormProductPK) other;
        return new EqualsBuilder()
            .append(this.getItemNumber(), castOther.getItemNumber())
            .append(this.getProductId(), castOther.getProductId())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getItemNumber())
            .append(getProductId())
            .toHashCode();
    }

}
