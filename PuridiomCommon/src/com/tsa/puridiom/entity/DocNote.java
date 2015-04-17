package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class DocNote implements Serializable {

    /** identifier field */
    private java.math.BigDecimal icNotes;

    /** nullable persistent field */
    private String notes;

    /** nullable persistent field */
    private String referenceType;

    /** nullable persistent field */
    private String idReference;

    /** full constructor */
    public DocNote(java.math.BigDecimal icNotes, java.lang.String notes, java.lang.String referenceType, java.lang.String idReference) {
        this.icNotes = icNotes;
        this.notes = notes;
        this.referenceType = referenceType;
        this.idReference = idReference;
    }

    /** default constructor */
    public DocNote() {
    }

    /** minimal constructor */
    public DocNote(java.math.BigDecimal icNotes) {
        this.icNotes = icNotes;
    }

    public java.math.BigDecimal getIcNotes() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.icNotes);
    }

    public void setIcNotes(java.math.BigDecimal icNotes) {
        this.icNotes = icNotes;
    }

    public java.lang.String getNotes() {
		return (java.lang.String)HiltonUtility.ckNull(this.notes).trim();
    }

    public void setNotes(java.lang.String notes) {
		if (!HiltonUtility.isEmpty(notes) && notes.length() > 2000) {
			notes = notes.substring(0, 2000);
		}
		this.notes = notes;
    }

    public java.lang.String getReferenceType() {
		return (java.lang.String)HiltonUtility.ckNull(this.referenceType).trim();
    }

    public void setReferenceType(java.lang.String referenceType) {
		if (!HiltonUtility.isEmpty(referenceType) && referenceType.length() > 3) {
			referenceType = referenceType.substring(0, 3);
		}
		this.referenceType = referenceType;
    }

    public java.lang.String getIdReference() {
		return (java.lang.String)HiltonUtility.ckNull(this.idReference).trim();
    }

    public void setIdReference(java.lang.String idReference) {
		if (!HiltonUtility.isEmpty(idReference) && idReference.length() > 70) {
			idReference = idReference.substring(0, 70);
		}
		this.idReference = idReference;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("icNotes", getIcNotes())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof DocNote) ) return false;
        DocNote castOther = (DocNote) other;
        return new EqualsBuilder()
            .append(this.getIcNotes(), castOther.getIcNotes())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getIcNotes())
            .toHashCode();
    }

}
