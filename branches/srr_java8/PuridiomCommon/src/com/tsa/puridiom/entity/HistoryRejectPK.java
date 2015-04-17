package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class HistoryRejectPK implements Serializable {

    /** identifier field */
    private java.math.BigDecimal icHeader;

    /** identifier field */
    private java.math.BigDecimal icLine;

    /** identifier field */
    private java.math.BigDecimal sequence;

    /** identifier field */
    private String userId;

    /** full constructor */
    public HistoryRejectPK(java.math.BigDecimal icHeader, java.math.BigDecimal icLine, java.math.BigDecimal sequence, java.lang.String userId) {
        this.icHeader = icHeader;
        this.icLine = icLine;
        this.sequence = sequence;
        this.userId = userId;
    }

    /** default constructor */
    public HistoryRejectPK() {
    }

    public java.math.BigDecimal getIcHeader() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.icHeader);
    }

    public void setIcHeader(java.math.BigDecimal icHeader) {
        this.icHeader = icHeader;
    }

    public java.math.BigDecimal getIcLine() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.icLine);
    }

    public void setIcLine(java.math.BigDecimal icLine) {
        this.icLine = icLine;
    }

    public java.math.BigDecimal getSequence() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.sequence);
    }

    public void setSequence(java.math.BigDecimal sequence) {
        this.sequence = sequence;
    }

    public java.lang.String getUserId() {
		return (java.lang.String)HiltonUtility.ckNull(this.userId);
    }

    public void setUserId(java.lang.String userId) {
        this.userId = userId;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("icHeader", getIcHeader())
            .append("icLine", getIcLine())
            .append("sequence", getSequence())
            .append("userId", getUserId())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof HistoryRejectPK) ) return false;
        HistoryRejectPK castOther = (HistoryRejectPK) other;
        return new EqualsBuilder()
            .append(this.getIcHeader(), castOther.getIcHeader())
            .append(this.getIcLine(), castOther.getIcLine())
            .append(this.getSequence(), castOther.getSequence())
            .append(this.getUserId(), castOther.getUserId())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getIcHeader())
            .append(getIcLine())
            .append(getSequence())
            .append(getUserId())
            .toHashCode();
    }

}
