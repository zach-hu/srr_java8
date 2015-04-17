package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class InvCatreqPK implements Serializable {

    /** identifier field */
    private String invCatid;

    /** identifier field */
    private String reqClass;

    /** full constructor */
    public InvCatreqPK(java.lang.String invCatid, java.lang.String reqClass) {
        this.invCatid = invCatid;
        this.reqClass = reqClass;
    }

    /** default constructor */
    public InvCatreqPK() {
    }

    public java.lang.String getInvCatid() {
		return (java.lang.String)HiltonUtility.ckNull(this.invCatid);
    }

    public void setInvCatid(java.lang.String invCatid) {
        this.invCatid = invCatid;
    }

    public java.lang.String getReqClass() {
		return (java.lang.String)HiltonUtility.ckNull(this.reqClass);
    }

    public void setReqClass(java.lang.String reqClass) {
        this.reqClass = reqClass;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("invCatid", getInvCatid())
            .append("reqClass", getReqClass())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof InvCatreqPK) ) return false;
        InvCatreqPK castOther = (InvCatreqPK) other;
        return new EqualsBuilder()
            .append(this.getInvCatid(), castOther.getInvCatid())
            .append(this.getReqClass(), castOther.getReqClass())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getInvCatid())
            .append(getReqClass())
            .toHashCode();
    }

}
