package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsagate.foundation.database.Entity;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


public class AlertConfig implements Serializable {

    /** identifier field */
    private java.math.BigDecimal icAlert;

    /** nullable persistent field */
    private String reportName;

    /** nullable persistent field */
    private String description;

    /** nullable persistent field */
    private String subject;

    /** nullable persistent field */
    private String processXMLFilename;

    /** nullable persistent field */
    private String argumentName;

    /** nullable persistent field */
    private String isActive;

    /** nullable persistent field */
    private String alertType;

    /** nullable persistent field */
    private String alertSql;

    /** nullable persistent field */
    private java.util.Date lastRun;

    /** nullable persistent field */
    private String enabled;

    /** nullable persistent field */
    private String buyer;

    /** nullable persistent field */
    private String owner;

    /** nullable persistent field */
    private String req;

    /** nullable persistent field */
    private String approver;

    /** nullable persistent field */
    private java.math.BigDecimal offsetDays;


    /** full constructor */
    public AlertConfig(java.math.BigDecimal icAlert, java.lang.String reportName,
    		java.lang.String description, java.lang.String subject,
    		java.lang.String processXMLFilename, java.lang.String argumentName,
    		java.lang.String isActive, java.lang.String alertType,
    		java.lang.String alertSql, java.util.Date lastRun,
    		java.lang.String enabled, java.lang.String buyer,
    		java.lang.String owner, java.lang.String req,
    		java.lang.String approver, java.math.BigDecimal offsetDays) {
        this.icAlert = icAlert;
        this.reportName = reportName;
        this.description = description;
        this.subject = subject;
        this.processXMLFilename = processXMLFilename;
        this.argumentName = argumentName;
        this.isActive = isActive;
        this.alertType = alertType;
        this.alertSql = alertSql;
        this.lastRun = lastRun;
        this.enabled = enabled;
        this.buyer = buyer;
        this.owner = owner;
        this.req = req;
        this.approver = approver;
        this.offsetDays = offsetDays;
    }

    /** default constructor */
    public AlertConfig() {
    }

    /** minimal constructor */
    public AlertConfig(java.math.BigDecimal icAlert) {
    	this.icAlert = icAlert;
    }

    public java.math.BigDecimal getIcAlert() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.icAlert);
    }

    public void setIcAlert(java.math.BigDecimal icAlert) {
        this.icAlert = icAlert;
    }

    public java.lang.String getReportName() {
		return (java.lang.String)HiltonUtility.ckNull(this.reportName).trim();
    }

    public void setReportName(java.lang.String reportName) {
		if (!HiltonUtility.isEmpty(reportName) && reportName.length() > 80) {
			reportName = reportName.substring(0, 80);
		}
		this.reportName = reportName;
    }

    public java.lang.String getDescription() {
		return (java.lang.String)HiltonUtility.ckNull(this.description).trim();
    }

    public void setDescription(java.lang.String description) {
		if (!HiltonUtility.isEmpty(description) && description.length() > 100) {
			description = description.substring(0, 100);
		}
		this.description = description;
    }

    public java.lang.String getSubject() {
		return (java.lang.String)HiltonUtility.ckNull(this.subject).trim();
    }

    public void setSubject(java.lang.String subject) {
		if (!HiltonUtility.isEmpty(subject) && subject.length() > 150) {
			subject = subject.substring(0, 150);
		}
		this.subject = subject;
    }

    public java.lang.String getProcessXMLFilename() {
		return (java.lang.String)HiltonUtility.ckNull(this.processXMLFilename).trim();
    }

    public void setProcessXMLFilename(java.lang.String processXMLFilename) {
		if (!HiltonUtility.isEmpty(processXMLFilename) && processXMLFilename.length() > 100) {
			processXMLFilename = processXMLFilename.substring(0, 100);
		}
		this.processXMLFilename = processXMLFilename;
    }

    public java.lang.String getArgumentName() {
		return (java.lang.String)HiltonUtility.ckNull(this.argumentName).trim();
    }

    public void setArgumentName(java.lang.String argumentName) {
		if (!HiltonUtility.isEmpty(argumentName) && argumentName.length() > 50) {
			argumentName = argumentName.substring(0, 50);
		}
		this.argumentName = argumentName;
    }

    public java.lang.String getIsActive() {
		return (java.lang.String)HiltonUtility.ckNull(this.isActive).trim();
    }

    public void setIsActive(java.lang.String isActive) {
		if (!HiltonUtility.isEmpty(isActive) && isActive.length() > 1) {
			isActive = isActive.substring(0, 1);
		}
		this.isActive = isActive;
    }

    public java.lang.String getAlertType() {
		return (java.lang.String)HiltonUtility.ckNull(this.alertType).trim();
    }

    public void setAlertType(java.lang.String alertType) {
		if (!HiltonUtility.isEmpty(alertType) && alertType.length() > 10) {
			alertType = alertType.substring(0, 10);
		}
		this.alertType = alertType;
    }

    public java.lang.String getAlertSql() {
		return (java.lang.String)HiltonUtility.ckNull(this.alertSql).trim();
    }

    public void setAlertSql(java.lang.String sql) {
		if (!HiltonUtility.isEmpty(alertSql) && alertSql.length() > 400) {
			alertSql = alertSql.substring(0, 400);
		}
		this.alertSql = alertSql;
    }

        public java.util.Date getLastRun() {
		return this.lastRun;
//		return HiltonUtility.ckNull(this.lastRun);
//		return (java.sql.Date)HiltonUtility.ckNull(this.lastRun);
    }

    public void setLastRun(java.util.Date lastRun) {
    	this.lastRun = lastRun;
    }

    public java.lang.String getEnabled() {
		return (java.lang.String)HiltonUtility.ckNull(this.enabled).trim();
    }

    public void setEnabled(java.lang.String enabled) {
		if (!HiltonUtility.isEmpty(enabled) && enabled.length() > 1) {
			enabled = enabled.substring(0, 1);
		}
		this.enabled = enabled;
    }

    public java.lang.String getBuyer() {
		return (java.lang.String)HiltonUtility.ckNull(this.buyer).trim();
    }

    public void setBuyer(java.lang.String buyer) {
		if (!HiltonUtility.isEmpty(buyer) && buyer.length() > 1) {
			buyer = buyer.substring(0, 1);
		}
		this.buyer = buyer;
    }

    public java.lang.String getOwner() {
		return (java.lang.String)HiltonUtility.ckNull(this.owner).trim();
    }

    public void setOwner(java.lang.String owner) {
		if (!HiltonUtility.isEmpty(owner) && owner.length() > 1) {
			owner = owner.substring(0, 1);
		}
		this.owner = owner;
    }

    public java.lang.String getReq() {
		return (java.lang.String)HiltonUtility.ckNull(this.req).trim();
    }

    public void setReq(java.lang.String req) {
		if (!HiltonUtility.isEmpty(req) && req.length() > 1) {
			req = req.substring(0, 1);
		}
		this.req = req;
    }

    public java.lang.String getApprover() {
		return (java.lang.String)HiltonUtility.ckNull(this.approver).trim();
    }

    public void setApprover(java.lang.String approver) {
		if (!HiltonUtility.isEmpty(approver) && approver.length() > 1) {
			approver = approver.substring(0, 1);
		}
		this.approver = approver;
    }

    public java.math.BigDecimal getOffsetDays() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.offsetDays);
    }

    public void setOffsetDays(java.math.BigDecimal offsetDays) {
        this.offsetDays = offsetDays;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("icAlert", getIcAlert())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof AlertConfig) ) return false;
        AlertConfig castOther = (AlertConfig) other;
        return new EqualsBuilder()
            .append(this.getIcAlert(), castOther.getIcAlert())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getIcAlert())
            .toHashCode();
    }

}
