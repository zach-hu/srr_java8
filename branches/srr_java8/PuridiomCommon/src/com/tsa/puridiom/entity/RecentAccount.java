package com.tsa.puridiom.entity;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.tsa.puridiom.common.utility.HiltonUtility;

/** @author KC */
public class RecentAccount implements Serializable {

    /** identifier field */
    private com.tsa.puridiom.entity.RecentAccountPK comp_id;

    /** nullable persistent field */
    private java.util.Date dateEntered;

    /** nullable persistent field */
    private java.math.BigDecimal accountCount;

    /** full constructor */
    public RecentAccount(com.tsa.puridiom.entity.RecentAccountPK comp_id, java.util.Date dateEntered, java.math.BigDecimal accountCount) {
        this.comp_id = comp_id;
        this.dateEntered = dateEntered;
        this.accountCount = accountCount;
    }

    /** default constructor */
    public RecentAccount() {
    }

    /** minimal constructor */
    public RecentAccount(com.tsa.puridiom.entity.RecentAccountPK comp_id) {
        this.comp_id = comp_id;
    }

    public com.tsa.puridiom.entity.RecentAccountPK getComp_id() {
        return this.comp_id;
    }

    public void setComp_id(com.tsa.puridiom.entity.RecentAccountPK comp_id) {
        this.comp_id = comp_id;
    }

	public java.util.Date getDateEntered() {
		return this.dateEntered;
    }

    public void setDateEntered(java.util.Date dateEntered) {
        this.dateEntered = dateEntered;
    }

    public java.math.BigDecimal getAccountCount() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.accountCount);
    }

    public void setAccountCount(java.math.BigDecimal accountCount) {
        this.accountCount = accountCount;
    }

    public String toString() {
        return new ToStringBuilder(this)
        	.append("comp_id", getComp_id())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof RecentAccount) ) return false;
        RecentAccount castOther = (RecentAccount) other;
        return new EqualsBuilder()
            .append(this.getComp_id(), castOther.getComp_id())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
        	.append(getComp_id())
            .toHashCode();
    }
}
