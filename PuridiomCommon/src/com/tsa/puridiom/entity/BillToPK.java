package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class BillToPK implements Serializable {

    /** identifier field */
    private java.math.BigDecimal icHeader;

    /** identifier field */
    private java.math.BigDecimal icLine;

    /** identifier field */
    private String billToCode;

    /** full constructor */
    public BillToPK(java.math.BigDecimal icHeader, java.math.BigDecimal icLine, java.lang.String billToCode) {
        this.icHeader = icHeader;
        this.icLine = icLine;
        this.billToCode = billToCode;
    }

    /** default constructor */
    public BillToPK() {
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

    public java.lang.String getBillToCode() {
		return this.billToCode;
    }

    public void setBillToCode(java.lang.String billToCode) {
        this.billToCode = billToCode;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("icHeader", getIcHeader())
            .append("icLine", getIcLine())
            .append("billToCode", getBillToCode())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof BillToPK) ) return false;
        BillToPK castOther = (BillToPK) other;
        return new EqualsBuilder()
            .append(this.getIcHeader(), castOther.getIcHeader())
            .append(this.getIcLine(), castOther.getIcLine())
            .append(this.getBillToCode(), castOther.getBillToCode())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getIcHeader())
            .append(getIcLine())
            .append(getBillToCode())
            .toHashCode();
    }

}
