package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class RfqBidHistoryPK implements Serializable {

    /** identifier field */
    private java.math.BigDecimal icRfqHeader;

    /** identifier field */
    private java.math.BigDecimal icRfqLine;

    /** identifier field */
    private String vendorId;

    /** nullable persistent field */
    private java.math.BigDecimal bidSequence;

    /** full constructor */
    public RfqBidHistoryPK(java.math.BigDecimal icRfqHeader, java.math.BigDecimal icRfqLine, java.lang.String vendorId,java.math.BigDecimal bidSequence) {
        this.icRfqHeader = icRfqHeader;
        this.icRfqLine = icRfqLine;
        this.vendorId = vendorId;
        this.bidSequence = bidSequence;
    }

    /** default constructor */
    public RfqBidHistoryPK() {
    }

    public java.math.BigDecimal getIcRfqHeader() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.icRfqHeader);
    }

    public void setIcRfqHeader(java.math.BigDecimal icRfqHeader) {
        this.icRfqHeader = icRfqHeader;
    }

    public java.math.BigDecimal getIcRfqLine() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.icRfqLine);
    }

    public void setIcRfqLine(java.math.BigDecimal icRfqLine) {
        this.icRfqLine = icRfqLine;
    }

    public java.lang.String getVendorId() {
		return (java.lang.String)HiltonUtility.ckNull(this.vendorId);
    }

    public void setVendorId(java.lang.String vendorId) {
        this.vendorId = vendorId;
    }

    public java.math.BigDecimal getBidSequence() {
        return this.bidSequence;
    }

    public void setBidSequence(java.math.BigDecimal bidSequence) {
        this.bidSequence = bidSequence;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("icRfqHeader", getIcRfqHeader())
            .append("icRfqLine", getIcRfqLine())
            .append("vendorId", getVendorId())
            .append("bidSequence", getBidSequence())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof RfqBidHistoryPK) ) return false;
        RfqBidHistoryPK castOther = (RfqBidHistoryPK) other;
        return new EqualsBuilder()
            .append(this.getIcRfqHeader(), castOther.getIcRfqHeader())
            .append(this.getIcRfqLine(), castOther.getIcRfqLine())
            .append(this.getVendorId(), castOther.getVendorId())
            .append(this.getBidSequence(), castOther.getBidSequence())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getIcRfqHeader())
            .append(getIcRfqLine())
            .append(getVendorId())
            .append(getBidSequence())
            .toHashCode();
    }

}
