package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class VendorDocumentPK implements Serializable {

    /** identifier field */
    private java.math.BigDecimal icRfqHeader;

    /** identifier field */
    private String vendorId;

    /** identifier field */
    private java.math.BigDecimal docIc;

    /** full constructor */
    public VendorDocumentPK(java.math.BigDecimal icRfqHeader, java.lang.String vendorId, java.math.BigDecimal docIc) {
        this.icRfqHeader = icRfqHeader;
        this.vendorId = vendorId;
        this.docIc = docIc;
    }

    /** default constructor */
    public VendorDocumentPK() {
    }

    public java.math.BigDecimal getIcRfqHeader() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.icRfqHeader);
    }

    public void setIcRfqHeader(java.math.BigDecimal icRfqHeader) {
        this.icRfqHeader = icRfqHeader;
    }

    public java.lang.String getVendorId() {
		return (java.lang.String)HiltonUtility.ckNull(this.vendorId);
    }

    public void setVendorId(java.lang.String vendorId) {
        this.vendorId = vendorId;
    }

    public java.math.BigDecimal getDocIc() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.docIc);
    }

    public void setDocIc(java.math.BigDecimal docIc) {
        this.docIc = docIc;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("icRfqHeader", getIcRfqHeader())
            .append("vendorId", getVendorId())
            .append("docIc", getDocIc())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof VendorDocumentPK) ) return false;
        VendorDocumentPK castOther = (VendorDocumentPK) other;
        return new EqualsBuilder()
            .append(this.getIcRfqHeader(), castOther.getIcRfqHeader())
            .append(this.getVendorId(), castOther.getVendorId())
            .append(this.getDocIc(), castOther.getDocIc())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getIcRfqHeader())
            .append(getVendorId())
            .append(getDocIc())
            .toHashCode();
    }

}
