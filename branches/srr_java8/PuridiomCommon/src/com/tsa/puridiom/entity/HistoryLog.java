package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsagate.foundation.utility.Utility;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class HistoryLog implements Serializable {

    /** identifier field */
    private java.math.BigDecimal icHistory;

    /** persistent field */
    private java.math.BigDecimal icHeaderHistory;

    /** persistent field */
    private java.math.BigDecimal icLineHistory;

    /** persistent field */
    private java.math.BigDecimal icHeader;

    /** persistent field */
    private java.math.BigDecimal icLine;

    /** nullable persistent field */
    private String doctype;

    /** nullable persistent field */
    private String description;

    /** nullable persistent field */
    private String status;

    /** nullable persistent field */
    private String logDate;

    /** nullable persistent field */
    private String logTime;

    /** nullable persistent field */
    private String userid;

    /** nullable persistent field */
    private java.math.BigDecimal logMills;

    /** nullable persistent field */
    private String ipAddress;

    /** nullable persistent field */
    private String timeZone;

    /** full constructor */
    public HistoryLog(java.math.BigDecimal icHistory, java.math.BigDecimal icHeaderHistory, java.math.BigDecimal icLineHistory, java.math.BigDecimal icHeader, java.math.BigDecimal icLine, java.lang.String doctype, java.lang.String description, java.lang.String status, java.lang.String logDate, java.lang.String logTime, java.lang.String userid, java. lang.String timeZone) {
        this.icHistory = icHistory;
        this.icHeaderHistory = icHeaderHistory;
        this.icLineHistory = icLineHistory;
        this.icHeader = icHeader;
        this.icLine = icLine;
        this.doctype = doctype;
        this.description = description;
        this.status = status;
        this.logDate = logDate;
        this.logTime = logTime;
        this.userid = userid;
        this.logMills = logMills ;
        this.timeZone = timeZone;
    }

    /** default constructor */
    public HistoryLog() {
    }

    /** minimal constructor */
    public HistoryLog(java.math.BigDecimal icHistory, java.math.BigDecimal icHeaderHistory, java.math.BigDecimal icLineHistory, java.math.BigDecimal icHeader, java.math.BigDecimal icLine) {
        this.icHistory = icHistory;
        this.icHeaderHistory = icHeaderHistory;
        this.icLineHistory = icLineHistory;
        this.icHeader = icHeader;
        this.icLine = icLine;
    }

    public java.math.BigDecimal getIcHistory() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.icHistory);
    }

    public void setIcHistory(java.math.BigDecimal icHistory) {
        this.icHistory = icHistory;
    }

    public java.math.BigDecimal getIcHeaderHistory() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.icHeaderHistory);
    }

    public void setIcHeaderHistory(java.math.BigDecimal icHeaderHistory) {
        this.icHeaderHistory = icHeaderHistory;
    }

    public java.math.BigDecimal getIcLineHistory() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.icLineHistory);
    }

    public void setIcLineHistory(java.math.BigDecimal icLineHistory) {
        this.icLineHistory = icLineHistory;
    }

    public java.math.BigDecimal getIcHeader() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.icHeader);
    }

    public void setIcHeader(java.math.BigDecimal icHeader) {
        this.icHeader = icHeader;
    }

    public java.math.BigDecimal getIcLine() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.icLine);
    }

    public void setIcLine(java.math.BigDecimal icLine) {
        this.icLine = icLine;
    }

    public java.lang.String getDoctype() {
		return (java.lang.String)HiltonUtility.ckNull(this.doctype).trim();
    }

    public void setDoctype(java.lang.String doctype) {
		if (!HiltonUtility.isEmpty(doctype) && doctype.length() > 3) {
			doctype = doctype.substring(0, 3);
		}
		this.doctype = doctype;
    }

    public java.lang.String getDescription() {
		return (java.lang.String)HiltonUtility.ckNull(this.description).trim();
    }

    public void setDescription(java.lang.String description) {
		if (!HiltonUtility.isEmpty(description) && description.length() > 255) {
			description = description.substring(0, 255);
		}
		this.description = description;
    }

    public java.lang.String getStatus() {
		return (java.lang.String)HiltonUtility.ckNull(this.status).trim();
    }

    public void setStatus(java.lang.String status) {
		if (!HiltonUtility.isEmpty(status) && status.length() > 4) {
			status = status.substring(0, 4);
		}
		this.status = status;
    }

    public java.lang.String getLogDate() {
		return (java.lang.String)Utility.ckNull(this.logDate).trim();
    }

    public void setLogDate(java.lang.String logDate) {
		if (!HiltonUtility.isEmpty(logDate) && logDate.length() > 10) {
			logDate = logDate.substring(0, 10);
		}
		this.logDate = logDate;
    }

    public java.lang.String getLogTime() {
		return (java.lang.String)Utility.ckNull(this.logTime).trim();
    }

    public void setLogTime(java.lang.String logTime) {
		if (!HiltonUtility.isEmpty(logTime) && logTime.length() > 15) {
			logTime = logTime.substring(0, 15);
		}
		this.logTime = logTime;
    }

    public java.lang.String getUserid() {
		return (java.lang.String)HiltonUtility.ckNull(this.userid).trim();
    }

    public void setUserid(java.lang.String userid) {
		if (!HiltonUtility.isEmpty(userid) && userid.length() > 15) {
			userid = userid.substring(0, 15);
		}
		this.userid = userid;
    }

    public java.math.BigDecimal getLogMills() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.logMills);
//    	return logMills;
    }

    public void setLogMills(java.math.BigDecimal logMills) {
    	this.logMills = logMills;
    }

    public String getIpAddress()
	{
    	return (java.lang.String)HiltonUtility.ckNull(this.ipAddress).trim();
	}

	public void setIpAddress(String ipAddress)
	{
		if (!HiltonUtility.isEmpty(ipAddress) && ipAddress.length() > 60) {
			ipAddress = ipAddress.substring(0, 60);
		}
		this.ipAddress = ipAddress;
	}

    public String getTimeZone()
    {
        return (java.lang.String) HiltonUtility.ckNull(this.timeZone).trim();
    }

    public void setTimeZone(String timeZone)
    {
        if (!HiltonUtility.isEmpty(timeZone) && timeZone.length() > 30) {
            timeZone = timeZone.substring(0, 30);
        }
        this.timeZone = timeZone;
    }

	public String toString() {
        return new ToStringBuilder(this)
            .append("icHistory", getIcHistory())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof HistoryLog) ) return false;
        HistoryLog castOther = (HistoryLog) other;
        return new EqualsBuilder()
            .append(this.getIcHistory(), castOther.getIcHistory())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getIcHistory())
            .toHashCode();
    }


}
