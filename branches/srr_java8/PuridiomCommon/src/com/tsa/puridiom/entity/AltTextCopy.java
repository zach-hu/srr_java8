package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class AltTextCopy implements Serializable {

    /** identifier field */
    private AltTextPK comp_id;

    /** nullable persistent field */
    private java.math.BigDecimal icText;

    /** nullable persistent field */
    private java.util.Date dateEntered;

    /** nullable persistent field */
    private java.util.Date lastChgDate;

    /** nullable persistent field */
    private String lastChgBy;

    private DocText docText;

    /** full constructor */
    public AltTextCopy(AltTextPK comp_id, java.math.BigDecimal icText, java.util.Date dateEntered, java.util.Date lastChgDate, java.lang.String lastChgBy) {
        this.comp_id = comp_id;
        this.icText = icText;
        this.dateEntered = dateEntered;
        this.lastChgDate = lastChgDate;
        this.lastChgBy = lastChgBy;
    }

    /** default constructor */
    public AltTextCopy() {
    }

    public AltTextCopy(AltTextPK comp_id)
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

    public java.util.Date getDateEntered() {
        return this.dateEntered;
    }

    public void setDateEntered(java.util.Date dateEntered) {
        this.dateEntered = dateEntered;
    }

    public java.util.Date getLastChgDate() {
        return this.lastChgDate;
    }

    public void setLastChgDate(java.util.Date lastChgDate) {
        this.lastChgDate = lastChgDate;
    }

    public java.lang.String getLastChgBy() {
        return (java.lang.String)HiltonUtility.ckNull(this.lastChgBy).trim();
    }

    public void setLastChgBy(java.lang.String lastChgBy) {
        this.lastChgBy = lastChgBy;
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
        if ( !(other instanceof AltTextCopy) ) return false;
        AltTextCopy castOther = (AltTextCopy) other;
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
