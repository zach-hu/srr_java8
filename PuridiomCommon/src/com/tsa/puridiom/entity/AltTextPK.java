package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class AltTextPK implements Serializable {

    /** identifier field */
    private String source;

    /** identifier field */
    private String id;

    /** identifier field */
    private String itemNumber;

    /** identifier field */
    private String language;

    /** full constructor */
    public AltTextPK(java.lang.String source, java.lang.String id, java.lang.String itemNumber, java.lang.String language) {
        this.source = source;
        this.id = id;
        this.itemNumber = itemNumber;
        this.language = language;
    }

    /** default constructor */
    public AltTextPK() {
    }

    public java.lang.String getSource() {
		return (java.lang.String)HiltonUtility.ckNull(this.source);
    }

    public void setSource(java.lang.String source) {
        if (!HiltonUtility.isEmpty(source) && source.length() > 3) {
            source = source.substring(0, 3);
        }
        this.source = source;
    }

    public java.lang.String getId() {
        return (java.lang.String)HiltonUtility.ckNull(this.id);
    }

    public void setId(java.lang.String id) {
        if (!HiltonUtility.isEmpty(id) && id.length() > 22) {
            id = id.substring(0, 22);
        }
        this.id = id;
    }

    public java.lang.String getItemNumber() {
		return (java.lang.String)HiltonUtility.ckNull(this.itemNumber);
    }

    public void setItemNumber(java.lang.String itemNumber) {
        if (!HiltonUtility.isEmpty(itemNumber) && itemNumber.length() > 30) {
            itemNumber = itemNumber.substring(0, 30);
        }
        this.itemNumber = itemNumber;
    }

    public java.lang.String getLanguage() {
        return (java.lang.String)HiltonUtility.ckNull(this.language);
    }

    public void setLanguage(java.lang.String language) {
        if (!HiltonUtility.isEmpty(language) && language.length() > 15) {
            language = language.substring(0, 15);
        }
        this.language = language;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("source", getSource())
            .append("id", getId())
            .append("itemNumber", getItemNumber())
            .append("language", getLanguage())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof AltTextPK) ) return false;
        AltTextPK castOther = (AltTextPK) other;
        return new EqualsBuilder()
            .append(this.getSource(), castOther.getSource())
            .append(this.getId(), castOther.getId())
            .append(this.getItemNumber(), castOther.getItemNumber())
            .append(this.getLanguage(), castOther.getLanguage())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getSource())
            .append(getId())
            .append(getItemNumber())
            .append(getLanguage())
            .toHashCode();
    }
}
