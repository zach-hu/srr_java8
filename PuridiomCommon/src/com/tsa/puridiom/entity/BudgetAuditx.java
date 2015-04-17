package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class BudgetAuditx implements Serializable {

    /** identifier field */
    private String auditId;

    /** nullable persistent field */
    private String authority;

    /** nullable persistent field */
    private java.math.BigDecimal icLine;

    /** nullable persistent field */
    private String actionCode;

    /** nullable persistent field */
    private String actionType;

    /** nullable persistent field */
    private String actionMake;

    /** nullable persistent field */
    private String actionDate;

    /** nullable persistent field */
    private String actionTime;

    /** nullable persistent field */
    private java.math.BigDecimal priorAmt;

    /** nullable persistent field */
    private java.math.BigDecimal tranAmt;

    /** nullable persistent field */
    private String parentType;

    /** nullable persistent field */
    private String lineType;

    /** nullable persistent field */
    private String owner;

    /** nullable persistent field */
    private String budgetFlag;

    /** nullable persistent field */
    private String exportCode;

    /** nullable persistent field */
    private String exportDate;

    /** nullable persistent field */
    private String exportGroup;

    /** full constructor */
    public BudgetAuditx(java.lang.String auditId, java.lang.String authority, java.math.BigDecimal icLine, java.lang.String actionCode, java.lang.String actionType, java.lang.String actionMake, java.lang.String actionDate, java.lang.String actionTime, java.math.BigDecimal priorAmt, java.math.BigDecimal tranAmt, java.lang.String parentType, java.lang.String lineType, java.lang.String owner, java.lang.String budgetFlag, java.lang.String exportCode, java.lang.String exportDate, java.lang.String exportGroup) {
        this.auditId = auditId;
        this.authority = authority;
        this.icLine = icLine;
        this.actionCode = actionCode;
        this.actionType = actionType;
        this.actionMake = actionMake;
        this.actionDate = actionDate;
        this.actionTime = actionTime;
        this.priorAmt = priorAmt;
        this.tranAmt = tranAmt;
        this.parentType = parentType;
        this.lineType = lineType;
        this.owner = owner;
        this.budgetFlag = budgetFlag;
        this.exportCode = exportCode;
        this.exportDate = exportDate;
        this.exportGroup = exportGroup;
    }

    /** default constructor */
    public BudgetAuditx() {
    }

    /** minimal constructor */
    public BudgetAuditx(java.lang.String auditId) {
        this.auditId = auditId;
    }

    public java.lang.String getAuditId() {
		return (java.lang.String)HiltonUtility.ckNull(this.auditId);
    }

    public void setAuditId(java.lang.String auditId) {
        this.auditId = auditId;
    }

    public java.lang.String getAuthority() {
		return (java.lang.String)HiltonUtility.ckNull(this.authority);
    }

    public void setAuthority(java.lang.String authority) {
        this.authority = authority;
    }

    public java.math.BigDecimal getIcLine() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.icLine);
    }

    public void setIcLine(java.math.BigDecimal icLine) {
        this.icLine = icLine;
    }

    public java.lang.String getActionCode() {
		return (java.lang.String)HiltonUtility.ckNull(this.actionCode);
    }

    public void setActionCode(java.lang.String actionCode) {
        this.actionCode = actionCode;
    }

    public java.lang.String getActionType() {
		return (java.lang.String)HiltonUtility.ckNull(this.actionType);
    }

    public void setActionType(java.lang.String actionType) {
        this.actionType = actionType;
    }

    public java.lang.String getActionMake() {
		return (java.lang.String)HiltonUtility.ckNull(this.actionMake);
    }

    public void setActionMake(java.lang.String actionMake) {
        this.actionMake = actionMake;
    }

    public java.lang.String getActionDate() {
		return (java.lang.String)HiltonUtility.ckNull(this.actionDate);
    }

    public void setActionDate(java.lang.String actionDate) {
        this.actionDate = actionDate;
    }

    public java.lang.String getActionTime() {
		return (java.lang.String)HiltonUtility.ckNull(this.actionTime);
    }

    public void setActionTime(java.lang.String actionTime) {
        this.actionTime = actionTime;
    }

    public java.math.BigDecimal getPriorAmt() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.priorAmt);
    }

    public void setPriorAmt(java.math.BigDecimal priorAmt) {
        this.priorAmt = priorAmt;
    }

    public java.math.BigDecimal getTranAmt() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.tranAmt);
    }

    public void setTranAmt(java.math.BigDecimal tranAmt) {
        this.tranAmt = tranAmt;
    }

    public java.lang.String getParentType() {
		return (java.lang.String)HiltonUtility.ckNull(this.parentType);
    }

    public void setParentType(java.lang.String parentType) {
        this.parentType = parentType;
    }

    public java.lang.String getLineType() {
		return (java.lang.String)HiltonUtility.ckNull(this.lineType);
    }

    public void setLineType(java.lang.String lineType) {
        this.lineType = lineType;
    }

    public java.lang.String getOwner() {
		return (java.lang.String)HiltonUtility.ckNull(this.owner);
    }

    public void setOwner(java.lang.String owner) {
        this.owner = owner;
    }

    public java.lang.String getBudgetFlag() {
		return (java.lang.String)HiltonUtility.ckNull(this.budgetFlag);
    }

    public void setBudgetFlag(java.lang.String budgetFlag) {
        this.budgetFlag = budgetFlag;
    }

    public java.lang.String getExportCode() {
		return (java.lang.String)HiltonUtility.ckNull(this.exportCode);
    }

    public void setExportCode(java.lang.String exportCode) {
        this.exportCode = exportCode;
    }

    public java.lang.String getExportDate() {
		return (java.lang.String)HiltonUtility.ckNull(this.exportDate);
    }

    public void setExportDate(java.lang.String exportDate) {
        this.exportDate = exportDate;
    }

    public java.lang.String getExportGroup() {
		return (java.lang.String)HiltonUtility.ckNull(this.exportGroup);
    }

    public void setExportGroup(java.lang.String exportGroup) {
        this.exportGroup = exportGroup;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("auditId", getAuditId())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof BudgetAuditx) ) return false;
        BudgetAuditx castOther = (BudgetAuditx) other;
        return new EqualsBuilder()
            .append(this.getAuditId(), castOther.getAuditId())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getAuditId())
            .toHashCode();
    }

}
