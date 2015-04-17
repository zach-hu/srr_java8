package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsagate.foundation.utility.Utility;

import java.io.Serializable;
import java.math.BigDecimal;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class BudgetAudit implements Serializable {

    /** identifier field */
    private java.math.BigDecimal auditId;

    /** nullable persistent field */
    private java.math.BigDecimal budgetId;

    /** nullable persistent field */
    private String authority;

    /** nullable persistent field */
    private java.math.BigDecimal icHeader;

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
    private java.math.BigDecimal balance ;

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

    /** Non entity **/
    private BigDecimal bOperator = new BigDecimal(0);

    private boolean newRecord = false ;

    /** full constructor */
    public BudgetAudit(java.math.BigDecimal auditId, java.math.BigDecimal budgetId, java.lang.String authority, java.math.BigDecimal icHeader, java.math.BigDecimal icLine, java.lang.String actionCode, java.lang.String actionType, java.lang.String actionMake, java.lang.String actionDate, java.lang.String actionTime, java.math.BigDecimal priorAmt, java.math.BigDecimal tranAmt, java.math.BigDecimal balance, java.lang.String parentType, java.lang.String lineType, java.lang.String owner, java.lang.String budgetFlag, java.lang.String exportCode, java.lang.String exportDate, java.lang.String exportGroup) {
        this.auditId = auditId;
        this.budgetId = budgetId;
        this.authority = authority;
        this.icHeader = icHeader;
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
    public BudgetAudit() {
    }

    /** minimal constructor */
    public BudgetAudit(java.math.BigDecimal auditId) {
        this.auditId = auditId;
    }

    public java.math.BigDecimal getAuditId() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.auditId);
    }

    public void setAuditId(java.math.BigDecimal auditId) {
		this.auditId = auditId;
    }

    public java.math.BigDecimal getBudgetId() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.budgetId);
    }

    public void setBudgetId(java.math.BigDecimal budgetId) {
		this.budgetId = budgetId;
    }

    public java.lang.String getAuthority() {
		return (java.lang.String)HiltonUtility.ckNull(this.authority).trim();
    }

    public void setAuthority(java.lang.String authority) {
		if (!HiltonUtility.isEmpty(authority) && authority.length() > 15) {
			authority = authority.substring(0, 15);
		}
		this.authority = authority;
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

    public java.lang.String getActionCode() {
		return (java.lang.String)HiltonUtility.ckNull(this.actionCode).trim();
    }

    public void setActionCode(java.lang.String actionCode) {
		if (!HiltonUtility.isEmpty(actionCode) && actionCode.length() > 3) {
			actionCode = actionCode.substring(0, 3);
		}
		this.actionCode = actionCode;
    }

    public java.lang.String getActionType() {
		return (java.lang.String)HiltonUtility.ckNull(this.actionType).trim();
    }

    public void setActionType(java.lang.String actionType) {
		if (!HiltonUtility.isEmpty(actionType) && actionType.length() > 3) {
			actionType = actionType.substring(0, 3);
		}
		this.actionType = actionType;
    }

    public java.lang.String getActionMake() {
		return (java.lang.String)HiltonUtility.ckNull(this.actionMake).trim();
    }

    public void setActionMake(java.lang.String actionMake) {
		if (!HiltonUtility.isEmpty(actionMake) && actionMake.length() > 3) {
			actionMake = actionMake.substring(0, 3);
		}
		this.actionMake = actionMake;
    }

    public java.lang.String getActionDate() {
		return (java.lang.String)HiltonUtility.ckNull(this.actionDate).trim();
    }

    public void setActionDate(java.lang.String actionDate) {
		if (!HiltonUtility.isEmpty(actionDate) && actionDate.length() > 10) {
			actionDate = actionDate.substring(0, 10);
		}
		this.actionDate = actionDate;
    }

    public java.lang.String getActionTime() {
		return (java.lang.String)Utility.ckNull(this.actionTime).trim();
    }

    public void setActionTime(java.lang.String actionTime) {
		if (!HiltonUtility.isEmpty(actionTime) && actionTime.length() > 12) {
			actionTime = actionTime.substring(0, 12);
		}
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

    public java.math.BigDecimal getBalance() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.balance);
    }

    public void setBalance(java.math.BigDecimal balance) {
        this.balance = balance;
    }

    public java.lang.String getParentType() {
		return (java.lang.String)HiltonUtility.ckNull(this.parentType).trim();
    }

    public void setParentType(java.lang.String parentType) {
		if (!HiltonUtility.isEmpty(parentType) && parentType.length() > 3) {
			parentType = parentType.substring(0, 3);
		}
		this.parentType = parentType;
    }

    public java.lang.String getLineType() {
		return (java.lang.String)HiltonUtility.ckNull(this.lineType).trim();
    }

    public void setLineType(java.lang.String lineType) {
		if (!HiltonUtility.isEmpty(lineType) && lineType.length() > 3) {
			lineType = lineType.substring(0, 3);
		}
		this.lineType = lineType;
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

    public java.lang.String getBudgetFlag() {
		return (java.lang.String)HiltonUtility.ckNull(this.budgetFlag).trim();
    }

    public void setBudgetFlag(java.lang.String budgetFlag) {
		if (!HiltonUtility.isEmpty(budgetFlag) && budgetFlag.length() > 2) {
			budgetFlag = budgetFlag.substring(0, 2);
		}
		this.budgetFlag = budgetFlag;
    }

    public java.lang.String getExportCode() {
		return (java.lang.String)HiltonUtility.ckNull(this.exportCode).trim();
    }

    public void setExportCode(java.lang.String exportCode) {
		if (!HiltonUtility.isEmpty(exportCode) && exportCode.length() > 8) {
			exportCode = exportCode.substring(0, 8);
		}
		this.exportCode = exportCode;
    }

    public java.lang.String getExportDate() {
		return (java.lang.String)HiltonUtility.ckNull(this.exportDate).trim();
    }

    public void setExportDate(java.lang.String exportDate) {
		if (!HiltonUtility.isEmpty(exportDate) && exportDate.length() > 10) {
			exportDate = exportDate.substring(0, 10);
		}
		this.exportDate = exportDate;
    }

    public java.lang.String getExportGroup() {
		return (java.lang.String)HiltonUtility.ckNull(this.exportGroup).trim();
    }

    public void setExportGroup(java.lang.String exportGroup) {
		if (!HiltonUtility.isEmpty(exportGroup) && exportGroup.length() > 10) {
			exportGroup = exportGroup.substring(0, 10);
		}
		this.exportGroup = exportGroup;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("auditId", getAuditId())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof BudgetAudit) ) return false;
        BudgetAudit castOther = (BudgetAudit) other;
        return new EqualsBuilder()
            .append(this.getAuditId(), castOther.getAuditId())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getAuditId())
            .toHashCode();
    }

	/**
	 * @return Returns the bOperator.
	 */
	public BigDecimal getBOperator() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.bOperator);
	}

	/**
	 * @param operator The bOperator to set.
	 */
	public void setBOperator(BigDecimal operator) {
		bOperator = operator;
	}

	/**
	 * @return Returns the newRecord.
	 */
	public boolean isNewRecord() {
		return newRecord;
	}

	/**
	 * @param newRecord The newRecord to set.
	 */
	public void setNewRecord(boolean newRecord) {
		this.newRecord = newRecord;
	}

}
