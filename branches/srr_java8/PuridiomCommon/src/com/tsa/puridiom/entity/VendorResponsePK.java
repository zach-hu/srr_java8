package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class VendorResponsePK implements Serializable {

    /** identifier field */
    private java.math.BigDecimal icRfqHeader;

    /** identifier field */
    private java.math.BigDecimal icQuestion;

    /** identifier field */
    private String vendorId;

    /** full constructor */
    public VendorResponsePK(java.math.BigDecimal icRfqHeader, java.math.BigDecimal icQuestion, java.lang.String vendorId) {
        this.icRfqHeader = icRfqHeader;
        this.icQuestion = icQuestion;
        this.vendorId = vendorId;
    }

    /** default constructor */
    public VendorResponsePK() {
    }

    public java.math.BigDecimal getIcRfqHeader() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.icRfqHeader);
    }

    public void setIcRfqHeader(java.math.BigDecimal icRfqHeader) {
        this.icRfqHeader = icRfqHeader;
    }

    public java.math.BigDecimal getIcQuestion() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.icQuestion);
    }

    public void setIcQuestion(java.math.BigDecimal icQuestion) {
        this.icQuestion = icQuestion;
    }

    public java.lang.String getVendorId() {
		return (java.lang.String)HiltonUtility.ckNull(this.vendorId);
    }

    public void setVendorId(java.lang.String vendorId) {
        this.vendorId = vendorId;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("icRfqHeader", getIcRfqHeader())
            .append("icQuestion", getIcQuestion())
            .append("vendorId", getVendorId())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof VendorResponsePK) ) return false;
        VendorResponsePK castOther = (VendorResponsePK) other;
        return new EqualsBuilder()
            .append(this.getIcRfqHeader(), castOther.getIcRfqHeader())
            .append(this.getIcQuestion(), castOther.getIcQuestion())
            .append(this.getVendorId(), castOther.getVendorId())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getIcRfqHeader())
            .append(getIcQuestion())
            .append(getVendorId())
            .toHashCode();
    }

}
