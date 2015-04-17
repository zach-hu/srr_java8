package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class DocAttachment implements Serializable {

    /** identifier field */
    private com.tsa.puridiom.entity.DocAttachmentPK comp_id;

    /** nullable persistent field */
    private String docSource;

    /** nullable persistent field */
    private String docTitle;

    /** nullable persistent field */
    private String docFilename;

    /** nullable persistent field */
    private String docPrint;

    /** nullable persistent field */
    private String docPost;

    /** nullable persistent field */
    private String uploadedBy;

    /** nullable persistent field */
    private java.util.Date dateUploaded;


    /** full constructor */
    public DocAttachment(com.tsa.puridiom.entity.DocAttachmentPK comp_id, java.lang.String docSource, java.lang.String docTitle, java.lang.String docFilename , java.lang.String docPrint, java.lang.String docPost, java.lang.String uploadedBy, java.util.Date dateUploaded) {
        this.comp_id = comp_id;
        this.docSource = docSource;
        this.docTitle = docTitle;
        this.docFilename = docFilename;
        this.docPrint = docPrint;
        this.docPost = docPost;
        this.uploadedBy = uploadedBy;
        this.dateUploaded = dateUploaded;
    }

    /** default constructor */
    public DocAttachment() {
    }

    /** minimal constructor */
    public DocAttachment(com.tsa.puridiom.entity.DocAttachmentPK comp_id) {
        this.comp_id = comp_id;
    }

    public com.tsa.puridiom.entity.DocAttachmentPK getComp_id() {
        return this.comp_id;
    }

    public void setComp_id(com.tsa.puridiom.entity.DocAttachmentPK comp_id) {
        this.comp_id = comp_id;
    }

    public java.lang.String getDocSource() {
		return (java.lang.String)HiltonUtility.ckNull(this.docSource).trim();
    }

    public void setDocSource(java.lang.String docSource) {
		if (!HiltonUtility.isEmpty(docSource) && docSource.length() > 3) {
			docSource = docSource.substring(0, 3);
		}
		this.docSource = docSource;
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

    public java.lang.String getDocPost() {
		return (java.lang.String)HiltonUtility.ckNull(this.docPost).trim();
    }

    public void setDocPost(java.lang.String docPost) {
		if (!HiltonUtility.isEmpty(docPost) && docPost.length() > 1) {
			docPost = docPost.substring(0, 1);
		}
		this.docPost = docPost;
    }

    public java.lang.String getUploadedBy() {
		return (java.lang.String)HiltonUtility.ckNull(this.uploadedBy);
    }

    public void setUploadedBy(java.lang.String uploadedBy) {
		this.uploadedBy = uploadedBy;
    }

    public java.util.Date getDateUploaded() {
		return this.dateUploaded;
    }

    public void setDateUploaded(java.util.Date dateUploaded) {
        this.dateUploaded = dateUploaded;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("comp_id", getComp_id())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof DocAttachment) ) return false;
        DocAttachment castOther = (DocAttachment) other;
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
