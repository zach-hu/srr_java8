package com.tsa.puridiom.entity;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class ReportPK implements Serializable {

    /** identifier field */
    private String reportTitle;

    /** identifier field */
    private String reportModule;

    /** full constructor */
    public ReportPK(java.lang.String reportTitle, java.lang.String reportModule) {
        this.reportTitle = reportTitle;
        this.reportModule = reportModule;
    }

    /** default constructor */
    public ReportPK() {
    }

    public java.lang.String getReportTitle() {
        return this.reportTitle;
    }

    public void setReportTitle(java.lang.String reportTitle) {
        this.reportTitle = reportTitle;
    }

    public java.lang.String getReportModule() {
        return this.reportModule;
    }

    public void setReportModule(java.lang.String reportModule) {
        this.reportModule = reportModule;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("reportTitle", getReportTitle())
            .append("reportModule", getReportModule())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof ReportPK) ) return false;
        ReportPK castOther = (ReportPK) other;
        return new EqualsBuilder()
            .append(this.getReportTitle(), castOther.getReportTitle())
            .append(this.getReportModule(), castOther.getReportModule())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getReportTitle())
            .append(getReportModule())
            .toHashCode();
    }

}
