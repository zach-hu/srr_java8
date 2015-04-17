package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsagate.foundation.database.Entity;
import com.tsagate.foundation.utility.Utility;

import java.io.Serializable;

/** @author Hibernate CodeGenerator */
public class UserLog extends Entity implements Serializable {

	/** identifier field */
	private java.math.BigDecimal icUsrLog;

	/** nullable persistent field */
	private String userId;

	/** nullable persistent field */
	private String mailId;

	/** nullable persistent field */
	private String passCode;

	/** nullable persistent field */
	private java.util.Date dateLog;

	/** nullable persistent field */
	private String timeLog;

	/** nullable persistent field */
	private String ipLog;

	/** nullable persistent field */
	private String status;

	/** full constructor */
	public UserLog (java.math.BigDecimal icUsrLog, String userId, String mailId, String passCode, java.util.Date dateLog, String timeLog, String ipLog, String status) {
		this.icUsrLog = icUsrLog;
		this.userId   = userId;
		this.mailId   = mailId;
		this.passCode = passCode;
		this.dateLog  = dateLog;
		this.timeLog  = timeLog;
		this.ipLog    = ipLog;
		this.status   = status;
	}

	/** default constructor */
	public UserLog() {
	}

	/** minimal constructor */
	public UserLog(java.math.BigDecimal icUsrLog) {
		this.icUsrLog = icUsrLog;
	}

	public java.math.BigDecimal getIcUsrLog() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.icUsrLog);
	}

	public void setIcUsrLog(java.math.BigDecimal icUsrLog) {
		this.icUsrLog = icUsrLog;
	}

	public java.lang.String getUserId() {
		return (java.lang.String)HiltonUtility.ckNull(this.userId).trim();
	}

	public void setUserId(java.lang.String userId) {
		if (!HiltonUtility.isEmpty(userId) && userId.length() > 15) {
			userId = userId.toUpperCase();
		}
		this.userId = userId;
	}

	public java.lang.String getMailId() {
		return (java.lang.String)HiltonUtility.ckNull(this.mailId).trim();
	}

	public void setMailId(java.lang.String mailId) {
		if (!HiltonUtility.isEmpty(mailId) && mailId.length() > 65) {
			mailId = mailId.toLowerCase();
		}
		this.mailId = mailId;
	}

	public java.lang.String getPassCode() {
		return (java.lang.String)Utility.ckNull(this.passCode).trim();
	}

	public void setPassCode(java.lang.String passCode) {
		if (!HiltonUtility.isEmpty(passCode) && passCode.length() > 20) {
			passCode = passCode.substring(0, 20);
		}
		this.passCode = passCode;
	}

	public java.util.Date getDateLog() {
		return this.dateLog;
	}

	public void setDateLog(java.util.Date dateLog) {
		this.dateLog = dateLog;
	}

	public java.lang.String getTimeLog() {
		return (java.lang.String)Utility.ckNull(this.timeLog).trim();
	}

	public void setTimeLog(java.lang.String timeLog) {
		if (!HiltonUtility.isEmpty(timeLog) && timeLog.length() > 15) {
			timeLog = timeLog.substring(0, 15);
		}
		this.timeLog = timeLog;
	}

	public java.lang.String getIpLog() {
		return (java.lang.String)HiltonUtility.ckNull(this.ipLog).trim();
	}

	public void setIpLog(java.lang.String ipLog) {
		if (!HiltonUtility.isEmpty(ipLog) && ipLog.length() > 60) {
			ipLog = ipLog.substring(0, 60);
		}
		this.ipLog = ipLog;
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
}
