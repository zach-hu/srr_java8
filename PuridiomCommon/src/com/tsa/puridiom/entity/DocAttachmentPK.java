package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class DocAttachmentPK implements Serializable {

    /** identifier field */
    private java.math.BigDecimal icHeader;

    /** identifier field */
    private java.math.BigDecimal docIc;

    /** identifier field */
    private java.math.BigDecimal icLine;

    /** full constructor */
    public DocAttachmentPK(java.math.BigDecimal icHeader, java.math.BigDecimal docIc) {
        this.icHeader = icHeader;
        this.docIc = docIc;
    }

    /** default constructor */
    public DocAttachmentPK() {
    }

    public java.math.BigDecimal getIcHeader() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.icHeader);
    }

    public void setIcHeader(java.math.BigDecimal icHeader) {
        this.icHeader = icHeader;
    }

    public java.math.BigDecimal getDocIc() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.docIc);
    }

    public void setDocIc(java.math.BigDecimal docIc) {
        this.docIc = docIc;
    }

    public java.math.BigDecimal getIcLine() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.icLine);
    }

    public void setIcLine(java.math.BigDecimal icLine) {
        this.icLine = icLine;
    }
    
    
    public String toString() {
        return new ToStringBuilder(this)
            .append("icHeader", getIcHeader())
            .append("docOrder", getDocIc())
            .append("icLine", getIcLine())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof DocAttachmentPK) ) return false;
        DocAttachmentPK castOther = (DocAttachmentPK) other;
        return new EqualsBuilder()
            .append(this.getIcHeader(), castOther.getIcHeader())
            .append(this.getDocIc(), castOther.getDocIc())
            .append(this.getIcLine(), castOther.getIcLine())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getIcHeader())
            .append(getDocIc())
            .append(getIcLine())
            .toHashCode();
    }

}
