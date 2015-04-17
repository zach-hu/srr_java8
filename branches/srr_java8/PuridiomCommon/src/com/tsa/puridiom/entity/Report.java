package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class Report implements Serializable {

    /** identifier field */
    private com.tsa.puridiom.entity.ReportPK comp_id;

    /** nullable persistent field */
    private String reportDatawindow;

    /** nullable persistent field */
    private String reportDescription;

    /** nullable persistent field */
    private String reportPrompt;

    /** nullable persistent field */
    private String reportXml;

    /** nullable persistent field */
    private String userAccessControl;

    /** full constructor */
    public Report(com.tsa.puridiom.entity.ReportPK comp_id, java.lang.String reportDatawindow, java.lang.String reportDescription, java.lang.String reportPrompt, java.lang.String reportXml, java.lang.String userAccessControl) {
        this.comp_id = comp_id;
        this.reportDatawindow = reportDatawindow;
        this.reportDescription = reportDescription;
        this.reportPrompt = reportPrompt;
        this.reportXml = reportXml;
        this.userAccessControl = userAccessControl;
    }

    /** default constructor */
    public Report() {
    }

    /** minimal constructor */
    public Report(com.tsa.puridiom.entity.ReportPK comp_id) {
        this.comp_id = comp_id;
    }

    public com.tsa.puridiom.entity.ReportPK getComp_id() {
        return this.comp_id;
    }

    public void setComp_id(com.tsa.puridiom.entity.ReportPK comp_id) {
        this.comp_id = comp_id;
    }

    public java.lang.String getReportDatawindow() {
		return (java.lang.String)HiltonUtility.ckNull(this.reportDatawindow).trim();
    }

    public void setReportDatawindow(java.lang.String reportDatawindow) {
		if (!HiltonUtility.isEmpty(reportDatawindow) && reportDatawindow.length() > 60) {
			reportDatawindow = reportDatawindow.substring(0, 60);
		}
		this.reportDatawindow = reportDatawindow;
    }

    public java.lang.String getReportDescription() {
		return (java.lang.String)HiltonUtility.ckNull(this.reportDescription).trim();
    }

    public void setReportDescription(java.lang.String reportDescription) {
		if (!HiltonUtility.isEmpty(reportDescription) && reportDescription.length() > 255) {
			reportDescription = reportDescription.substring(0, 255);
		}
		this.reportDescription = reportDescription;
    }

    public java.lang.String getReportPrompt() {
		return (java.lang.String)HiltonUtility.ckNull(this.reportPrompt).trim();
    }

    public void setReportPrompt(java.lang.String reportPrompt) {
		if (!HiltonUtility.isEmpty(reportPrompt) && reportPrompt.length() > 1) {
			reportPrompt = reportPrompt.substring(0, 1);
		}
		this.reportPrompt = reportPrompt;
    }

    public java.lang.String getUserAccessControl() {
		return (java.lang.String)HiltonUtility.ckNull(this.userAccessControl).trim();
    }

    public void setUserAccessControl(java.lang.String userAccessControl) {
		if (!HiltonUtility.isEmpty(userAccessControl) && userAccessControl.length() > 10) {
			userAccessControl = userAccessControl.substring(0, 10);
		}
		this.userAccessControl = userAccessControl;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("comp_id", getComp_id())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof Report) ) return false;
        Report castOther = (Report) other;
        return new EqualsBuilder()
            .append(this.getComp_id(), castOther.getComp_id())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getComp_id())
            .toHashCode();
    }

	public String getReportXml() {
		return (java.lang.String)HiltonUtility.ckNull(this.reportXml).trim();
	}

	public void setReportXml(String reportXml) {
		if (!HiltonUtility.isEmpty(reportXml) && reportXml.length() > 60) {
			reportXml = reportXml.substring(0, 60);
		}
		this.reportXml = reportXml;
	}

}
