package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class AltText implements Serializable {

    /** identifier field */
    private AltTextPK comp_id;

    /** nullable persistent field */
    private java.math.BigDecimal icText;

    private DocText docText;

    /** full constructor */
    public AltText(AltTextPK comp_id, java.math.BigDecimal icText) {
        this.comp_id = comp_id;
        this.icText = icText;
    }

    /** default constructor */
    public AltText() {
    }

    public AltText(AltTextPK comp_id)
    {
    	this.comp_id = comp_id;
    }

    public AltTextPK getComp_id() {
        return this.comp_id;
    }

    public void setComp_id(AltTextPK comp_id) {
        this.comp_id = comp_id;
    }

    public java.math.BigDecimal getIcText() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.icText);
    }

    public void setIcText(java.math.BigDecimal icText) {
        this.icText = icText;
    }

    public DocText getDocText() {
        if (this.docText == null) {
            this.setDocText(new DocText());
        }
        return this.docText;
    }

    public void setDocText(DocText docText) {
        this.docText = docText;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("comp_id", getComp_id())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof AltText) ) return false;
        AltText castOther = (AltText) other;
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
