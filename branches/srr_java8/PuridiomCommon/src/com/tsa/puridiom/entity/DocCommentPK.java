package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class DocCommentPK implements Serializable {

    /** identifier field */
    private java.math.BigDecimal icHeader;

    /** identifier field */
    private java.math.BigDecimal icLine;

    /** identifier field */
    private java.math.BigDecimal commentOrder;

    /** full constructor */
    public DocCommentPK(java.math.BigDecimal icHeader, java.math.BigDecimal icLine, java.math.BigDecimal commentOrder) {
        this.icHeader = icHeader;
        this.icLine = icLine;
        this.commentOrder = commentOrder;
    }

    /** default constructor */
    public DocCommentPK() {
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

    public java.math.BigDecimal getCommentOrder() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.commentOrder);
    }

    public void setCommentOrder(java.math.BigDecimal commentOrder) {
        this.commentOrder = commentOrder;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("icHeader", getIcHeader())
            .append("icLine", getIcLine())
            .append("commentOrder", getCommentOrder())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof DocCommentPK) ) return false;
        DocCommentPK castOther = (DocCommentPK) other;
        return new EqualsBuilder()
            .append(this.getIcHeader(), castOther.getIcHeader())
            .append(this.getIcLine(), castOther.getIcLine())
            .append(this.getCommentOrder(), castOther.getCommentOrder())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getIcHeader())
            .append(getIcLine())
            .append(getCommentOrder())
            .toHashCode();
    }

}
