package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class InvHistoryPK implements Serializable {

    /** identifier field */
    private java.math.BigDecimal seqNumber;

    /** identifier field */
    private String itemNumber;

    /** full constructor */
    public InvHistoryPK(java.math.BigDecimal seqNumber, java.lang.String itemNumber) {
        this.seqNumber = seqNumber;
        this.itemNumber = itemNumber;
    }

    /** default constructor */
    public InvHistoryPK() {
    }

    public java.math.BigDecimal getSeqNumber() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.seqNumber);
    }

    public void setSeqNumber(java.math.BigDecimal seqNumber) {
        this.seqNumber = seqNumber;
    }

    public java.lang.String getItemNumber() {
		return (java.lang.String)HiltonUtility.ckNull(this.itemNumber);
    }

    public void setItemNumber(java.lang.String itemNumber) {
        this.itemNumber = itemNumber;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("seqNumber", getSeqNumber())
            .append("itemNumber", getItemNumber())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof InvHistoryPK) ) return false;
        InvHistoryPK castOther = (InvHistoryPK) other;
        return new EqualsBuilder()
            .append(this.getSeqNumber(), castOther.getSeqNumber())
            .append(this.getItemNumber(), castOther.getItemNumber())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getSeqNumber())
            .append(getItemNumber())
            .toHashCode();
    }

}
