package com.tsa.puridiom.entity;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class AppNote implements Serializable {

    /** identifier field */
    private java.math.BigDecimal icNote;

    /** nullable persistent field */
    private java.math.BigDecimal icDoc;

    /** nullable persistent field */
    private String docType;

    /** nullable persistent field */
    private String userType;

    /** nullable persistent field */
    private String userId;

    /** nullable persistent field */
    private String notetype;

    /** nullable persistent field */
    private String notetext;

    /** nullable persistent field */
    private java.util.Date adddate;

    /** nullable persistent field */
    private String addtime;

    /** full constructor */
    public AppNote(java.math.BigDecimal icNote, java.math.BigDecimal icDoc, java.lang.String docType, java.lang.String userType, java.lang.String userId, java.lang.String notetype, java.lang.String notetext, java.util.Date adddate, java.lang.String addtime) {
        this.icNote = icNote;
        this.icDoc = icDoc;
        this.docType = docType;
        this.userType = userType;
        this.userId = userId;
        this.notetype = notetype;
        this.notetext = notetext;
        this.adddate = adddate;
        this.addtime = addtime;
    }

    /** default constructor */
    public AppNote() {
    }

    /** minimal constructor */
    public AppNote(java.math.BigDecimal icNote) {
        this.icNote = icNote;
    }

    public java.math.BigDecimal getIcNote() {
        return this.icNote;
    }

    public void setIcNote(java.math.BigDecimal icNote) {
        this.icNote = icNote;
    }

    public java.math.BigDecimal getIcDoc() {
        return this.icDoc;
    }

    public void setIcDoc(java.math.BigDecimal icDoc) {
        this.icDoc = icDoc;
    }

    public java.lang.String getDocType() {
        return this.docType;
    }

    public void setDocType(java.lang.String docType) {
        this.docType = docType;
    }

    public java.lang.String getUserType() {
        return this.userType;
    }

    public void setUserType(java.lang.String userType) {
        this.userType = userType;
    }

    public java.lang.String getUserId() {
        return this.userId;
    }

    public void setUserId(java.lang.String userId) {
        this.userId = userId;
    }

    public java.lang.String getNotetype() {
        return this.notetype;
    }

    public void setNotetype(java.lang.String notetype) {
        this.notetype = notetype;
    }

    public java.lang.String getNotetext() {
        return this.notetext;
    }

    public void setNotetext(java.lang.String notetext) {
        this.notetext = notetext;
    }

    public java.util.Date getAdddate() {
        return this.adddate;
    }

    public void setAdddate(java.util.Date adddate) {
        this.adddate = adddate;
    }

    public java.lang.String getAddtime() {
        return this.addtime;
    }

    public void setAddtime(java.lang.String addtime) {
        this.addtime = addtime;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("icNote", getIcNote())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof AppNote) ) return false;
        AppNote castOther = (AppNote) other;
        return new EqualsBuilder()
            .append(this.getIcNote(), castOther.getIcNote())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getIcNote())
            .toHashCode();
    }

}
