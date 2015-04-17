package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class SchedulePK implements Serializable {

    /** identifier field */
    private String scheduleType;

    /** identifier field */
    private String documentType;

    /** identifier field */
    private java.math.BigDecimal icHeader;

    /** identifier field */
    private java.math.BigDecimal lineNumber;

    /** full constructor */
    public SchedulePK(java.lang.String scheduleType, java.lang.String documentType, java.math.BigDecimal icHeader, java.math.BigDecimal lineNumber) {
        this.scheduleType = scheduleType;
        this.documentType = documentType;
        this.icHeader = icHeader;
        this.lineNumber = lineNumber;
    }

    /** default constructor */
    public SchedulePK() {
    }

    public java.lang.String getScheduleType() {
		return (java.lang.String)HiltonUtility.ckNull(this.scheduleType);
    }

    public void setScheduleType(java.lang.String scheduleType) {
        this.scheduleType = scheduleType;
    }

    public java.lang.String getDocumentType() {
		return (java.lang.String)HiltonUtility.ckNull(this.documentType);
    }

    public void setDocumentType(java.lang.String documentType) {
        this.documentType = documentType;
    }

    public java.math.BigDecimal getIcHeader() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.icHeader);
    }

    public void setIcHeader(java.math.BigDecimal icHeader) {
        this.icHeader = icHeader;
    }

    public java.math.BigDecimal getLineNumber() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.lineNumber);
    }

    public void setLineNumber(java.math.BigDecimal lineNumber) {
        this.lineNumber = lineNumber;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("scheduleType", getScheduleType())
            .append("documentType", getDocumentType())
            .append("icHeader", getIcHeader())
            .append("lineNumber", getLineNumber())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof SchedulePK) ) return false;
        SchedulePK castOther = (SchedulePK) other;
        return new EqualsBuilder()
            .append(this.getScheduleType(), castOther.getScheduleType())
            .append(this.getDocumentType(), castOther.getDocumentType())
            .append(this.getIcHeader(), castOther.getIcHeader())
            .append(this.getLineNumber(), castOther.getLineNumber())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getScheduleType())
            .append(getDocumentType())
            .append(getIcHeader())
            .append(getLineNumber())
            .toHashCode();
    }

}
