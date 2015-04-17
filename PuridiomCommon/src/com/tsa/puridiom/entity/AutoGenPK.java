package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class AutoGenPK implements Serializable {

    /** identifier field */
    private String documentType;

    /** identifier field */
    private String genYear;

    /** full constructor */
    public AutoGenPK(java.lang.String documentType, java.lang.String genYear) {
        this.documentType = documentType;
        this.genYear = genYear;
    }

    /** default constructor */
    public AutoGenPK() {
    }

    public java.lang.String getDocumentType() {
		return this.documentType;
    }

    public void setDocumentType(java.lang.String documentType) {
        this.documentType = documentType;
    }

    public java.lang.String getGenYear() {
		return this.genYear;
    }

    public void setGenYear(java.lang.String genYear) {
        this.genYear = genYear;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("documentType", getDocumentType())
            .append("genYear", getGenYear())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof AutoGenPK) ) return false;
        AutoGenPK castOther = (AutoGenPK) other;
        return new EqualsBuilder()
            .append(this.getDocumentType(), castOther.getDocumentType())
            .append(this.getGenYear(), castOther.getGenYear())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getDocumentType())
            .append(getGenYear())
            .toHashCode();
    }

}
