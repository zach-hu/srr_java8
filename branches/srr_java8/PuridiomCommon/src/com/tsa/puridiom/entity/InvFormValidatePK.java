package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class InvFormValidatePK implements Serializable {

    /** identifier field */
    private String userId;

    /** identifier field */
    private String state;

    /** identifier field */
    private String productId;

    /** full constructor */
    public InvFormValidatePK(java.lang.String userId, java.lang.String state, java.lang.String productId) {
        this.userId = userId;
        this.state = state;
        this.productId = productId;
    }

    /** default constructor */
    public InvFormValidatePK() {
    }

    public java.lang.String getUserId() {
		return (java.lang.String)HiltonUtility.ckNull(this.userId);
    }

    public void setUserId(java.lang.String userId) {
        this.userId = userId;
    }

    public java.lang.String getState() {
		return (java.lang.String)HiltonUtility.ckNull(this.state);
    }

    public void setState(java.lang.String state) {
        this.state = state;
    }

    public java.lang.String getProductId() {
		return (java.lang.String)HiltonUtility.ckNull(this.productId);
    }

    public void setProductId(java.lang.String productId) {
        this.productId = productId;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("userId", getUserId())
            .append("state", getState())
            .append("productId", getProductId())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof InvFormValidatePK) ) return false;
        InvFormValidatePK castOther = (InvFormValidatePK) other;
        return new EqualsBuilder()
            .append(this.getUserId(), castOther.getUserId())
            .append(this.getState(), castOther.getState())
            .append(this.getProductId(), castOther.getProductId())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getUserId())
            .append(getState())
            .append(getProductId())
            .toHashCode();
    }

}
