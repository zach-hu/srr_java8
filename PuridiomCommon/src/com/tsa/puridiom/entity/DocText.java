package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class DocText implements Serializable {

    /** identifier field */
    private java.math.BigDecimal icText;

    /** nullable persistent field */
    private String stdText;

    /** nullable persistent field */
    private String referenceType;

    /** nullable persistent field */
    private String idReference;

    /** full constructor */
    public DocText(java.math.BigDecimal icText, java.lang.String stdText, java.lang.String referenceType, java.lang.String idReference) {
        this.icText = icText;
        this.stdText = stdText;
        this.referenceType = referenceType;
        this.idReference = idReference;
    }

    /** default constructor */
    public DocText() {
    }

    /** minimal constructor */
    public DocText(java.math.BigDecimal icText) {
        this.icText = icText;
    }

    public java.math.BigDecimal getIcText() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.icText);
    }

    public void setIcText(java.math.BigDecimal icText) {
        this.icText = icText;
    }

    public java.lang.String getStdText() {
		return (java.lang.String)HiltonUtility.ckNull(this.stdText).trim();
    }

    public void setStdText(java.lang.String stdText) {
		if (!HiltonUtility.isEmpty(stdText) && stdText.length() > 2000) {
			stdText = stdText.substring(0, 2000);
		}
		this.stdText = stdText;
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
            .append("icText", getIcText())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof DocText) ) return false;
        DocText castOther = (DocText) other;
        return new EqualsBuilder()
            .append(this.getIcText(), castOther.getIcText())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getIcText())
            .toHashCode();
    }

}
