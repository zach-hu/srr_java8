package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class StdAttachment implements Serializable {

    /** identifier field */
    private java.math.BigDecimal docIc;

    /** nullable persistent field */
    private String docTitle;

    /** nullable persistent field */
    private String docFilename;

    /** nullable persistent field */
    private String docPrint;

    /** full constructor */
    public StdAttachment(java.math.BigDecimal docIc, java.lang.String docTitle, java.lang.String docFilename , java.lang.String docPrint) {
        this.docIc = docIc;
        this.docTitle = docTitle;
        this.docFilename = docFilename;
        this.docPrint = docPrint;
    }

    /** default constructor */
    public StdAttachment() {
    }

    /** minimal constructor */
    public StdAttachment(java.math.BigDecimal docIc) {
        this.docIc = docIc;
    }

    public java.math.BigDecimal getDocIc() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.docIc);
    }

    public void setDocIc(java.math.BigDecimal docIc) {
        this.docIc = docIc;
    }
    
    public java.lang.String getDocTitle() {
		return (java.lang.String)HiltonUtility.ckNull(this.docTitle).trim();
    }

    public void setDocTitle(java.lang.String docTitle) {
		if (!HiltonUtility.isEmpty(docTitle) && docTitle.length() > 128) {
			docTitle = docTitle.substring(0, 128);
		}
		this.docTitle = docTitle;
    }

    public java.lang.String getDocFilename() {
		return (java.lang.String)HiltonUtility.ckNull(this.docFilename).trim();
    }

    public void setDocFilename(java.lang.String docFilename) {
		if (!HiltonUtility.isEmpty(docFilename) && docFilename.length() > 20) {
			docFilename = docFilename.substring(0, 20);
		}
		this.docFilename = docFilename;
    }

    public java.lang.String getDocPrint() {
		return (java.lang.String)HiltonUtility.ckNull(this.docPrint).trim();
    }

    public void setDocPrint(java.lang.String docPrint) {
		if (!HiltonUtility.isEmpty(docPrint) && docPrint.length() > 1) {
			docPrint = docPrint.substring(0, 1);
		}
		this.docPrint = docPrint;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("docIc", String.valueOf(getDocIc()))
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof StdAttachment) ) return false;
        StdAttachment castOther = (StdAttachment) other;
        return new EqualsBuilder()
            .append(this.getDocIc(), castOther.getDocIc())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getDocIc())
            .toHashCode();
    }

}
