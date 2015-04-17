package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class StdDocument implements Serializable {

    /** identifier field */
    private String fileName;

    /** persistent field */
    private String title;

    /** nullable persistent field */
    private String description;

    /** nullable persistent field */
    private String docType;

    /** nullable persistent field */
    private java.util.Date datePosted;

    /** nullable persistent field */
    private java.math.BigDecimal hits;

    /** full constructor */
    public StdDocument(java.lang.String fileName, java.lang.String title, java.lang.String description, java.lang.String docType, java.util.Date datePosted, java.math.BigDecimal hits) {
        this.fileName = fileName;
        this.title = title;
        this.description = description;
        this.docType = docType;
        this.datePosted = datePosted;
        this.hits = hits;
    }

    /** default constructor */
    public StdDocument() {
    }

    /** minimal constructor */
    public StdDocument(java.lang.String fileName, java.lang.String title) {
        this.fileName = fileName;
        this.title = title;
    }

    public java.lang.String getFileName() {
		return (java.lang.String)HiltonUtility.ckNull(this.fileName).trim();
    }

    public void setFileName(java.lang.String fileName) {
		if (!HiltonUtility.isEmpty(fileName) && fileName.length() > 50) {
			fileName = fileName.substring(0, 50);
		}
		this.fileName = fileName;
    }

    public java.lang.String getTitle() {
		return (java.lang.String)HiltonUtility.ckNull(this.title).trim();
    }

    public void setTitle(java.lang.String title) {
		if (!HiltonUtility.isEmpty(title) && title.length() > 60) {
			title = title.substring(0, 60);
		}
		this.title = title;
    }

    public java.lang.String getDescription() {
		return (java.lang.String)HiltonUtility.ckNull(this.description).trim();
    }

    public void setDescription(java.lang.String description) {
		if (!HiltonUtility.isEmpty(description) && description.length() > 255) {
			description = description.substring(0, 255);
		}
		this.description = description;
    }

    public java.lang.String getDocType() {
		return (java.lang.String)HiltonUtility.ckNull(this.docType).trim();
    }

    public void setDocType(java.lang.String docType) {
		if (!HiltonUtility.isEmpty(docType) && docType.length() > 2) {
			docType = docType.substring(0, 2);
		}
		this.docType = docType;
    }

    public java.util.Date getDatePosted() {
		return this.datePosted;
//		return HiltonUtility.ckNull(this.datePosted);
//		return (java.sql.Date)HiltonUtility.ckNull(this.datePosted);
    }

    public void setDatePosted(java.util.Date datePosted) {
        this.datePosted = datePosted;
    }

    public java.math.BigDecimal getHits() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.hits);
    }

    public void setHits(java.math.BigDecimal hits) {
        this.hits = hits;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("fileName", getFileName())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof StdDocument) ) return false;
        StdDocument castOther = (StdDocument) other;
        return new EqualsBuilder()
            .append(this.getFileName(), castOther.getFileName())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getFileName())
            .toHashCode();
    }

}
