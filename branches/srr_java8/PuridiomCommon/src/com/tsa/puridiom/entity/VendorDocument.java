package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class VendorDocument implements Serializable {

    /** identifier field */
    private com.tsa.puridiom.entity.VendorDocumentPK comp_id;

    /** persistent field */
    private String docTitle;

    /** persistent field */
    private String docFilename;

    /** persistent field */
    private String docType;

    /** nullable persistent field */
    private java.util.Date datePosted;

    /** full constructor */
    public VendorDocument(com.tsa.puridiom.entity.VendorDocumentPK comp_id, java.lang.String docFilename, java.lang.String docType, java.lang.String docTitle, java.util.Date datePosted) {
        this.comp_id = comp_id;
        this.docTitle = docTitle;
        this.docFilename = docFilename;
        this.docType = docType;
        this.datePosted = datePosted;
    }

    /** default constructor */
    public VendorDocument() {
    }

    public com.tsa.puridiom.entity.VendorDocumentPK getComp_id() {
        return this.comp_id;
    }

    public void setComp_id(com.tsa.puridiom.entity.VendorDocumentPK comp_id) {
        this.comp_id = comp_id;
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

    public java.lang.String getDocType() {
		return (java.lang.String)HiltonUtility.ckNull(this.docType).trim();
    }

    public void setDocType(java.lang.String docType) {
		if (!HiltonUtility.isEmpty(docType) && docType.length() > 1) {
			docType = docType.substring(0, 1);
		}
		this.docType = docType;
    }

    public java.util.Date getDatePosted() {
		return this.datePosted;
    }

    public void setDatePosted(java.util.Date datePosted) {
        this.datePosted = datePosted;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("comp_id", getComp_id())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof VendorDocument) ) return false;
        VendorDocument castOther = (VendorDocument) other;
        return new EqualsBuilder()
            .append(this.getComp_id(), castOther.getComp_id())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getComp_id())
            .toHashCode();
    }

}
