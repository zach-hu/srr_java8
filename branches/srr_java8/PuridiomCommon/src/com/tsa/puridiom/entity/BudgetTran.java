package com.tsa.puridiom.entity;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class BudgetTran implements Serializable {

    /** identifier field */
    private java.math.BigDecimal tranId;

    /** persistent field */
    private java.math.BigDecimal auditId;

    /** nullable persistent field */
    private java.math.BigDecimal budgetId;

    /** nullable persistent field */
    private String tranType;

    /** nullable persistent field */
    private java.util.Date tranDate;

    /** nullable persistent field */
    private String tranTime;

    /** nullable persistent field */
    private java.math.BigDecimal preEncumbered;

    /** nullable persistent field */
    private java.math.BigDecimal encumbered;

    /** nullable persistent field */
    private java.math.BigDecimal expensed;

    /** nullable persistent field */
    private java.math.BigDecimal balance;

    /** nullable persistent field */
    private String owner;

    private boolean newRecord = false ;

    /** full constructor */
    public BudgetTran(java.math.BigDecimal tranId, java.math.BigDecimal auditId, java.math.BigDecimal budgetId, java.lang.String tranType, java.util.Date tranDate, java.lang.String tranTime, java.math.BigDecimal preEncumbered, java.math.BigDecimal encumbered, java.math.BigDecimal expensed, java.math.BigDecimal balance, java.lang.String owner) {
        this.tranId = tranId;
        this.auditId = auditId;
        this.budgetId = budgetId;
        this.tranType = tranType;
        this.tranDate = tranDate;
        this.tranTime = tranTime;
        this.preEncumbered = preEncumbered;
        this.encumbered = encumbered;
        this.expensed = expensed;
        this.balance = balance;
        this.owner = owner;
    }

    /** default constructor */
    public BudgetTran() {
    }

    /** minimal constructor */
    public BudgetTran(java.math.BigDecimal tranId, java.math.BigDecimal auditId) {
        this.tranId = tranId;
        this.auditId = auditId;
    }

    public java.math.BigDecimal getTranId() {
        return this.tranId;
    }

    public void setTranId(java.math.BigDecimal tranId) {
        this.tranId = tranId;
    }

    public java.math.BigDecimal getAuditId() {
        return this.auditId;
    }

    public void setAuditId(java.math.BigDecimal auditId) {
        this.auditId = auditId;
    }

    public java.math.BigDecimal getBudgetId() {
        return this.budgetId;
    }

    public void setBudgetId(java.math.BigDecimal budgetId) {
        this.budgetId = budgetId;
    }

    public java.lang.String getTranType() {
        return this.tranType;
    }

    public void setTranType(java.lang.String tranType) {
        this.tranType = tranType;
    }

    public java.util.Date getTranDate() {
        return this.tranDate;
    }

    public void setTranDate(java.util.Date tranDate) {
        this.tranDate = tranDate;
    }

    public java.lang.String getTranTime() {
        return this.tranTime;
    }

    public void setTranTime(java.lang.String tranTime) {
        this.tranTime = tranTime;
    }

    public java.math.BigDecimal getPreEncumbered() {
        return this.preEncumbered;
    }

    public void setPreEncumbered(java.math.BigDecimal preEncumbered) {
        this.preEncumbered = preEncumbered;
    }

    public java.math.BigDecimal getEncumbered() {
        return this.encumbered;
    }

    public void setEncumbered(java.math.BigDecimal encumbered) {
        this.encumbered = encumbered;
    }

    public java.math.BigDecimal getExpensed() {
        return this.expensed;
    }

    public void setExpensed(java.math.BigDecimal expensed) {
        this.expensed = expensed;
    }

    public java.math.BigDecimal getBalance() {
        return this.balance;
    }

    public void setBalance(java.math.BigDecimal balance) {
        this.balance = balance;
    }

    public java.lang.String getOwner() {
        return this.owner;
    }

    public void setOwner(java.lang.String owner) {
        this.owner = owner;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("tranId", getTranId())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof BudgetTran) ) return false;
        BudgetTran castOther = (BudgetTran) other;
        return new EqualsBuilder()
            .append(this.getTranId(), castOther.getTranId())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getTranId())
            .toHashCode();
    }

	/**
	 * @return the newRecord
	 */
	public boolean isNewRecord() {
		return newRecord;
	}

	/**
	 * @param newRecord the newRecord to set
	 */
	public void setNewRecord(boolean newRecord) {
		this.newRecord = newRecord;
	}

}
