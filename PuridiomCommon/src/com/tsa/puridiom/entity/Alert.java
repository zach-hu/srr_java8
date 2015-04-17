package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class Alert implements Serializable {

    /** identifier field */
    private java.math.BigDecimal icAction;

    /** nullable persistent field */
    private java.util.Date actionDate;

    /** nullable persistent field */
    private String actionCode;

    /** nullable persistent field */
    private String actionDesc;

    /** nullable persistent field */
    private java.math.BigDecimal icNotes;

    /** nullable persistent field */
    private String userId;

    /** nullable persistent field */
    private String refType;

    /** nullable persistent field */
    private java.util.Date dateEntered;

    /** nullable persistent field */
    private java.util.Date dateChanged;

    /** nullable persistent field */
    private java.math.BigDecimal repeats;

    /** nullable persistent field */
    private java.util.Date untilDate;

    /** nullable persistent field */
    private java.math.BigDecimal relativeDays;

    /** nullable persistent field */
    private String systemMessage;

    /** nullable persistent field */
    private String emailMessage;

    /** nullable persistent field */
    private String owner;

    /** nullable persistent field */
    private String alertStatus;

    /** nullable persistent field */
    private String alertSent;

    /** nullable persistent field */
    private String buyer;

    /** nullable persistent field */
    private String requisitioner;

    /** nullable persistent field */
    private String approver;

    /** nullable persistent field */
    private String manager;

    /** nullable persistent field */
    private String extraNames;

    /** full constructor */
    public Alert(java.math.BigDecimal icAction, java.util.Date actionDate, java.lang.String actionCode, java.lang.String actionDesc, java.math.BigDecimal icNotes, java.lang.String userId, java.lang.String refType, java.util.Date dateEntered, java.util.Date dateChanged, java.math.BigDecimal repeats, java.util.Date untilDate, java.math.BigDecimal relativeDays, java.lang.String systemMessage, java.lang.String emailMessage, java.lang.String owner, java.lang.String alertStatus, java.lang.String alertSent, java.lang.String buyer, java.lang.String requisitioner, java.lang.String approver, java.lang.String manager, java.lang.String extraNames) {
        this.icAction = icAction;
        this.actionDate = actionDate;
        this.actionCode = actionCode;
        this.actionDesc = actionDesc;
        this.icNotes = icNotes;
        this.userId = userId;
        this.refType = refType;
        this.dateEntered = dateEntered;
        this.dateChanged = dateChanged;
        this.repeats = repeats;
        this.untilDate = untilDate;
        this.relativeDays = relativeDays;
        this.systemMessage = systemMessage;
        this.emailMessage = emailMessage;
        this.owner = owner;
        this.alertStatus = alertStatus;
        this.alertSent = alertSent;
        this.buyer = buyer;
        this.requisitioner = requisitioner;
        this.approver = approver;
        this.manager = manager;
        this.extraNames = extraNames;
    }

    /** default constructor */
    public Alert() {
    }

    /** minimal constructor */
    public Alert(java.math.BigDecimal icAction) {
        this.icAction = icAction;
    }

    public java.math.BigDecimal getIcAction() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.icAction);
    }

    public void setIcAction(java.math.BigDecimal icAction) {
        this.icAction = icAction;
    }

    public java.util.Date getActionDate() {
		return this.actionDate;
//		return HiltonUtility.ckNull(this.actionDate);
//		return (java.sql.Date)HiltonUtility.ckNull(this.actionDate);
    }

    public void setActionDate(java.util.Date actionDate) {
        this.actionDate = actionDate;
    }

    public java.lang.String getActionCode() {
		return (java.lang.String)HiltonUtility.ckNull(this.actionCode).trim();
    }

    public void setActionCode(java.lang.String actionCode) {
		if (!HiltonUtility.isEmpty(actionCode) && actionCode.length() > 15) {
			actionCode = actionCode.substring(0, 15);
		}
		this.actionCode = actionCode;
    }

    public java.lang.String getActionDesc() {
		return (java.lang.String)HiltonUtility.ckNull(this.actionDesc).trim();
    }

    public void setActionDesc(java.lang.String actionDesc) {
		if (!HiltonUtility.isEmpty(actionDesc) && actionDesc.length() > 250) {
			actionDesc = actionDesc.substring(0, 250);
		}
		this.actionDesc = actionDesc;
    }

    public java.math.BigDecimal getIcNotes() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.icNotes);
    }

    public void setIcNotes(java.math.BigDecimal icNotes) {
        this.icNotes = icNotes;
    }

    public java.lang.String getUserId() {
		return (java.lang.String)HiltonUtility.ckNull(this.userId).trim();
    }

    public void setUserId(java.lang.String userId) {
		if (!HiltonUtility.isEmpty(userId) && userId.length() > 15) {
			userId = userId.substring(0, 15);
		}
		this.userId = userId;
    }

    public java.lang.String getRefType() {
		return (java.lang.String)HiltonUtility.ckNull(this.refType).trim();
    }

    public void setRefType(java.lang.String refType) {
		if (!HiltonUtility.isEmpty(refType) && refType.length() > 3) {
			refType = refType.substring(0, 3);
		}
		this.refType = refType;
    }

    public java.util.Date getDateEntered() {
		return this.dateEntered;
//		return HiltonUtility.ckNull(this.dateEntered);
//		return (java.sql.Date)HiltonUtility.ckNull(this.dateEntered);
    }

    public void setDateEntered(java.util.Date dateEntered) {
        this.dateEntered = dateEntered;
    }

    public java.util.Date getDateChanged() {
		return this.dateChanged;
//		return HiltonUtility.ckNull(this.dateChanged);
//		return (java.sql.Date)HiltonUtility.ckNull(this.dateChanged);
    }

    public void setDateChanged(java.util.Date dateChanged) {
        this.dateChanged = dateChanged;
    }

    public java.math.BigDecimal getRepeats() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.repeats);
    }

    public void setRepeats(java.math.BigDecimal repeats) {
        this.repeats = repeats;
    }

    public java.util.Date getUntilDate() {
		return this.untilDate;
//		return HiltonUtility.ckNull(this.untilDate);
//		return (java.sql.Date)HiltonUtility.ckNull(this.untilDate);
    }

    public void setUntilDate(java.util.Date untilDate) {
        this.untilDate = untilDate;
    }

    public java.math.BigDecimal getRelativeDays() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.relativeDays);
    }

    public void setRelativeDays(java.math.BigDecimal relativeDays) {
        this.relativeDays = relativeDays;
    }

    public java.lang.String getSystemMessage() {
		return (java.lang.String)HiltonUtility.ckNull(this.systemMessage).trim();
    }

    public void setSystemMessage(java.lang.String systemMessage) {
		if (!HiltonUtility.isEmpty(systemMessage) && systemMessage.length() > 1) {
			systemMessage = systemMessage.substring(0, 1);
		}
		this.systemMessage = systemMessage;
    }

    public java.lang.String getEmailMessage() {
		return (java.lang.String)HiltonUtility.ckNull(this.emailMessage).trim();
    }

    public void setEmailMessage(java.lang.String emailMessage) {
		if (!HiltonUtility.isEmpty(emailMessage) && emailMessage.length() > 1) {
			emailMessage = emailMessage.substring(0, 1);
		}
		this.emailMessage = emailMessage;
    }

    public java.lang.String getOwner() {
		return (java.lang.String)HiltonUtility.ckNull(this.owner).trim();
    }

    public void setOwner(java.lang.String owner) {
		if (!HiltonUtility.isEmpty(owner) && owner.length() > 15) {
			owner = owner.substring(0, 15);
		}
		this.owner = owner;
    }

    public java.lang.String getAlertStatus() {
		return (java.lang.String)HiltonUtility.ckNull(this.alertStatus).trim();
    }

    public void setAlertStatus(java.lang.String alertStatus) {
		if (!HiltonUtility.isEmpty(alertStatus) && alertStatus.length() > 2) {
			alertStatus = alertStatus.substring(0, 2);
		}
		this.alertStatus = alertStatus;
    }

    public java.lang.String getAlertSent() {
		return (java.lang.String)HiltonUtility.ckNull(this.alertSent).trim();
    }

    public void setAlertSent(java.lang.String alertSent) {
		if (!HiltonUtility.isEmpty(alertSent) && alertSent.length() > 1) {
			alertSent = alertSent.substring(0, 1);
		}
		this.alertSent = alertSent;
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

    public java.lang.String getRequisitioner() {
		return (java.lang.String)HiltonUtility.ckNull(this.requisitioner).trim();
    }

    public void setRequisitioner(java.lang.String requisitioner) {
		if (!HiltonUtility.isEmpty(requisitioner) && requisitioner.length() > 1) {
			requisitioner = requisitioner.substring(0, 1);
		}
		this.requisitioner = requisitioner;
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

    public java.lang.String getManager() {
		return (java.lang.String)HiltonUtility.ckNull(this.manager).trim();
    }

    public void setManager(java.lang.String manager) {
		if (!HiltonUtility.isEmpty(manager) && manager.length() > 1) {
			manager = manager.substring(0, 1);
		}
		this.manager = manager;
    }

    public java.lang.String getExtraNames() {
		return (java.lang.String)HiltonUtility.ckNull(this.extraNames).trim();
    }

    public void setExtraNames(java.lang.String extraNames) {
		if (!HiltonUtility.isEmpty(extraNames) && extraNames.length() > 255) {
			extraNames = extraNames.substring(0, 255);
		}
		this.extraNames = extraNames;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("icAction", getIcAction())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof Alert) ) return false;
        Alert castOther = (Alert) other;
        return new EqualsBuilder()
            .append(this.getIcAction(), castOther.getIcAction())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getIcAction())
            .toHashCode();
    }

}
