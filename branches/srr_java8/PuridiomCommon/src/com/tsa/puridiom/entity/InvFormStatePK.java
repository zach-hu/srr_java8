package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class InvFormStatePK implements Serializable {

    /** identifier field */
    private String itemNumber;

    /** identifier field */
    private String stateId;

    /** full constructor */
    public InvFormStatePK(java.lang.String itemNumber, java.lang.String stateId) {
        this.itemNumber = itemNumber;
        this.stateId = stateId;
    }

    /** default constructor */
    public InvFormStatePK() {
    }

    public java.lang.String getItemNumber() {
		return (java.lang.String)HiltonUtility.ckNull(this.itemNumber);
    }

    public void setItemNumber(java.lang.String itemNumber) {
        this.itemNumber = itemNumber;
    }

    public java.lang.String getStateId() {
		return (java.lang.String)HiltonUtility.ckNull(this.stateId);
    }

    public void setStateId(java.lang.String stateId) {
        this.stateId = stateId;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("itemNumber", getItemNumber())
            .append("stateId", getStateId())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof InvFormStatePK) ) return false;
        InvFormStatePK castOther = (InvFormStatePK) other;
        return new EqualsBuilder()
            .append(this.getItemNumber(), castOther.getItemNumber())
            .append(this.getStateId(), castOther.getStateId())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getItemNumber())
            .append(getStateId())
            .toHashCode();
    }

}
