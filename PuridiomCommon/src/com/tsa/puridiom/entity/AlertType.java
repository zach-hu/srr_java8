package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class AlertType implements Serializable {

    /** identifier field */
    private String alertId;

    /** nullable persistent field */
    private String alertText;

    /** nullable persistent field */
    private String alertDw;

    /** nullable persistent field */
    private String alertRefType;

    /** nullable persistent field */
    private String docDw;

    /** nullable persistent field */
    private String buyer;

    /** nullable persistent field */
    private String owner;

    /** nullable persistent field */
    private String requisitioner;

    /** nullable persistent field */
    private String approver;

    /** nullable persistent field */
    private String manager;

    /** nullable persistent field */
    private String enabled;

    /** nullable persistent field */
    private String extraNames;

    /** nullable persistent field */
    private String commentId;

    /** nullable persistent field */
    private String offsetFlag;

    /** nullable persistent field */
    private java.math.BigDecimal offset;

    /** full constructor */
    public AlertType(java.lang.String alertId, java.lang.String alertText, java.lang.String alertDw, java.lang.String alertRefType, java.lang.String docDw, java.lang.String buyer, java.lang.String owner, java.lang.String requisitioner, java.lang.String approver, java.lang.String manager, java.lang.String enabled, java.lang.String extraNames, java.lang.String commentId, java.lang.String offsetFlag, java.math.BigDecimal offset) {
        this.alertId = alertId;
        this.alertText = alertText;
        this.alertDw = alertDw;
        this.alertRefType = alertRefType;
        this.docDw = docDw;
        this.buyer = buyer;
        this.owner = owner;
        this.requisitioner = requisitioner;
        this.approver = approver;
        this.manager = manager;
        this.enabled = enabled;
        this.extraNames = extraNames;
        this.commentId = commentId;
        this.offsetFlag = offsetFlag;
        this.offset = offset;
    }

    /** default constructor */
    public AlertType() {
    }

    /** minimal constructor */
    public AlertType(java.lang.String alertId) {
        this.alertId = alertId;
    }

    public java.lang.String getAlertId() {
		return (java.lang.String)HiltonUtility.ckNull(this.alertId).trim();
    }

    public void setAlertId(java.lang.String alertId) {
		if (!HiltonUtility.isEmpty(alertId) && alertId.length() > 6) {
			alertId = alertId.substring(0, 6);
		}
		this.alertId = alertId;
    }

    public java.lang.String getAlertText() {
		return (java.lang.String)HiltonUtility.ckNull(this.alertText).trim();
    }

    public void setAlertText(java.lang.String alertText) {
		if (!HiltonUtility.isEmpty(alertText) && alertText.length() > 60) {
			alertText = alertText.substring(0, 60);
		}
		this.alertText = alertText;
    }

    public java.lang.String getAlertDw() {
		return (java.lang.String)HiltonUtility.ckNull(this.alertDw).trim();
    }

    public void setAlertDw(java.lang.String alertDw) {
		if (!HiltonUtility.isEmpty(alertDw) && alertDw.length() > 30) {
			alertDw = alertDw.substring(0, 30);
		}
		this.alertDw = alertDw;
    }

    public java.lang.String getAlertRefType() {
		return (java.lang.String)HiltonUtility.ckNull(this.alertRefType).trim();
    }

    public void setAlertRefType(java.lang.String alertRefType) {
		if (!HiltonUtility.isEmpty(alertRefType) && alertRefType.length() > 3) {
			alertRefType = alertRefType.substring(0, 3);
		}
		this.alertRefType = alertRefType;
    }

    public java.lang.String getDocDw() {
		return (java.lang.String)HiltonUtility.ckNull(this.docDw).trim();
    }

    public void setDocDw(java.lang.String docDw) {
		if (!HiltonUtility.isEmpty(docDw) && docDw.length() > 60) {
			docDw = docDw.substring(0, 60);
		}
		this.docDw = docDw;
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

    public java.lang.String getEnabled() {
		return (java.lang.String)HiltonUtility.ckNull(this.enabled).trim();
    }

    public void setEnabled(java.lang.String enabled) {
		if (!HiltonUtility.isEmpty(enabled) && enabled.length() > 1) {
			enabled = enabled.substring(0, 1);
		}
		this.enabled = enabled;
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

    public java.lang.String getCommentId() {
		return (java.lang.String)HiltonUtility.ckNull(this.commentId).trim();
    }

    public void setCommentId(java.lang.String commentId) {
		if (!HiltonUtility.isEmpty(commentId) && commentId.length() > 15) {
			commentId = commentId.substring(0, 15);
		}
		this.commentId = commentId;
    }

    public java.lang.String getOffsetFlag() {
		return (java.lang.String)HiltonUtility.ckNull(this.offsetFlag).trim();
    }

    public void setOffsetFlag(java.lang.String offsetFlag) {
		if (!HiltonUtility.isEmpty(offsetFlag) && offsetFlag.length() > 1) {
			offsetFlag = offsetFlag.substring(0, 1);
		}
		this.offsetFlag = offsetFlag;
    }

    public java.math.BigDecimal getOffset() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.offset);
    }

    public void setOffset(java.math.BigDecimal offset) {
        this.offset = offset;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("alertId", getAlertId())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof AlertType) ) return false;
        AlertType castOther = (AlertType) other;
        return new EqualsBuilder()
            .append(this.getAlertId(), castOther.getAlertId())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getAlertId())
            .toHashCode();
    }

}
