package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class RecentReceiptPK implements Serializable {

    /** identifier field */
    private java.lang.String receivedBy;

    /** identifier field */
    private java.math.BigDecimal icRecHeader;

    /** full constructor */
    public RecentReceiptPK(java.lang.String receiptCode, java.math.BigDecimal icReqHeader) {
        this.receivedBy = receiptCode;
        this.icRecHeader = icReqHeader;
    }

    /** default constructor */
    public RecentReceiptPK() {
    }

    public java.lang.String getReceivedBy() {
		return (java.lang.String)HiltonUtility.ckNull(this.receivedBy);
    }

    public void setReceivedBy(java.lang.String receiptCode) {
        this.receivedBy = receiptCode;
    }
    
    public java.math.BigDecimal getIcRecHeader() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.icRecHeader);
    }

    public void setIcRecHeader(java.math.BigDecimal icRecHeader) {
        this.icRecHeader = icRecHeader;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("requisitionerCode", getReceivedBy())
            .append("icReqHeader", getIcRecHeader())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof RecentReceiptPK) ) return false;
        RecentReceiptPK castOther = (RecentReceiptPK) other;
        return new EqualsBuilder()
            .append(this.getReceivedBy(), castOther.getReceivedBy())
            .append(this.getIcRecHeader(), castOther.getIcRecHeader())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getReceivedBy())
            .append(getIcRecHeader())
            .toHashCode();
    }

}
