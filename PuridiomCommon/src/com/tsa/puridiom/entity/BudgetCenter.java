package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class BudgetCenter implements Serializable {

    /** identifier field */
    private java.math.BigDecimal budgetId;

    /** nullable persistent field */
    private String budgetPeriod;

    /** nullable persistent field */
    private String budget1;

    /** nullable persistent field */
    private String budget2;

    /** nullable persistent field */
    private String budget3;

    /** nullable persistent field */
    private String budget4;

    /** nullable persistent field */
    private String budget5;

    /** nullable persistent field */
    private String budget6;

    /** nullable persistent field */
    private String budget7;

    /** nullable persistent field */
    private String budget8;

    /** nullable persistent field */
    private String budget9;

    /** nullable persistent field */
    private String budget10;

    /** nullable persistent field */
    private String budget11;

    /** nullable persistent field */
    private String budget12;

    /** nullable persistent field */
    private String budget13;

    /** nullable persistent field */
    private String budget14;

    /** nullable persistent field */
    private String budget15;

    /** nullable persistent field */
    private java.math.BigDecimal budgeted;

    /** nullable persistent field */
    private java.math.BigDecimal preEncumbered;

    /** nullable persistent field */
    private java.math.BigDecimal encumbered;

    /** nullable persistent field */
    private java.math.BigDecimal expensed;

    /** nullable persistent field */
    private String owner;

    /** nullable persistent field */
    private String ownerPassword;

    /** nullable persistent field */
    private String status;

    /** nullable persistent field */
    private String approved;

    /** nullable persistent field */
    private String projectId;

    /** nullable persistent field */
    private String comments;

    /** nullable persistent field */
    private String lastChangeBy ;

    /** nullable persistent field */
    private java.util.Date lastChangeDate;

    /** nullable persistent field */
    private java.math.BigDecimal	lastTranId ;

    /** nullable persistent field */
    private java.math.BigDecimal	lastAuditId ;

    /** nullable persistent field */
    private String specificPeriod;

    /** nullable persistent field */
    private java.math.BigDecimal amountIncrement;

    /** nullable persistent field */
    private java.util.Date budgetIncrementDate;

    /** full constructor */
    public BudgetCenter(java.math.BigDecimal budgetId, java.lang.String budgetPeriod, java.lang.String budget1, java.lang.String budget2, java.lang.String budget3, java.lang.String budget4, java.lang.String budget5, java.lang.String budget6, java.lang.String budget7, java.lang.String budget8, java.lang.String budget9, java.lang.String budget10, java.lang.String budget11, java.lang.String budget12, java.lang.String budget13, java.lang.String budget14, java.lang.String budget15, java.math.BigDecimal budgeted, java.math.BigDecimal preEncumbered, java.math.BigDecimal encumbered, java.math.BigDecimal expensed, java.lang.String owner, java.lang.String ownerPassword, java.lang.String status, java.lang.String approved, java.lang.String projectId, java.lang.String comments, java.lang.String lastChangeBy, java.util.Date lastChangeDate, java.math.BigDecimal lastTranId, java.math.BigDecimal lastAuditId, java.lang.String specificPeriod, java.math.BigDecimal amountIncrement, java.util.Date budgetIncrementDate) {
        this.budgetId = budgetId;
        this.budgetPeriod = budgetPeriod;
        this.budget1 = budget1;
        this.budget2 = budget2;
        this.budget3 = budget3;
        this.budget4 = budget4;
        this.budget5 = budget5;
        this.budget6 = budget6;
        this.budget7 = budget7;
        this.budget8 = budget8;
        this.budget9 = budget9;
        this.budget10 = budget10;
        this.budget11 = budget11;
        this.budget12 = budget12;
        this.budget13 = budget13;
        this.budget14 = budget14;
        this.budget15 = budget15;
        this.budgeted = budgeted;
        this.preEncumbered = preEncumbered;
        this.encumbered = encumbered;
        this.expensed = expensed;
        this.owner = owner;
        this.ownerPassword = ownerPassword;
        this.status = status;
        this.approved = approved;
        this.projectId = projectId;
        this.comments = comments;
        this.lastAuditId = lastAuditId ;
        this.lastChangeBy = lastChangeBy ;
        this.lastTranId = lastTranId ;
        this.lastChangeDate = lastChangeDate;
        this.amountIncrement = amountIncrement;
        this.specificPeriod = specificPeriod;
        this.budgetIncrementDate = budgetIncrementDate;
    }

    /** default constructor */
    public BudgetCenter() {
    }

    /** minimal constructor */
    public BudgetCenter(java.math.BigDecimal budgetId) {
        this.budgetId = budgetId;
    }

    public java.math.BigDecimal getBudgetId() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.budgetId);
    }

    public void setBudgetId(java.math.BigDecimal budgetId) {
		this.budgetId = budgetId;
    }

    public java.lang.String getBudgetPeriod() {
		return (java.lang.String)HiltonUtility.ckNull(this.budgetPeriod).trim();
    }

    public void setBudgetPeriod(java.lang.String budgetPeriod) {
		if (!HiltonUtility.isEmpty(budgetPeriod) && budgetPeriod.length() > 15) {
			budgetPeriod = budgetPeriod.substring(0, 15);
		}
		this.budgetPeriod = budgetPeriod;
    }

    public java.lang.String getBudget1() {
		return (java.lang.String)HiltonUtility.ckNull(this.budget1).trim();
    }

    public void setBudget1(java.lang.String budget1) {
		if (!HiltonUtility.isEmpty(budget1) && budget1.length() > 17) {
			budget1 = budget1.substring(0, 17);
		}
		this.budget1 = budget1;
    }

    public java.lang.String getBudget2() {
		return (java.lang.String)HiltonUtility.ckNull(this.budget2).trim();
    }

    public void setBudget2(java.lang.String budget2) {
		if (!HiltonUtility.isEmpty(budget2) && budget2.length() > 17) {
			budget2 = budget2.substring(0, 17);
		}
		this.budget2 = budget2;
    }

    public java.lang.String getBudget3() {
		return (java.lang.String)HiltonUtility.ckNull(this.budget3).trim();
    }

    public void setBudget3(java.lang.String budget3) {
		if (!HiltonUtility.isEmpty(budget3) && budget3.length() > 17) {
			budget3 = budget3.substring(0, 17);
		}
		this.budget3 = budget3;
    }

    public java.lang.String getBudget4() {
		return (java.lang.String)HiltonUtility.ckNull(this.budget4).trim();
    }

    public void setBudget4(java.lang.String budget4) {
		if (!HiltonUtility.isEmpty(budget4) && budget4.length() > 17) {
			budget4 = budget4.substring(0, 17);
		}
		this.budget4 = budget4;
    }

    public java.lang.String getBudget5() {
		return (java.lang.String)HiltonUtility.ckNull(this.budget5).trim();
    }

    public void setBudget5(java.lang.String budget5) {
		if (!HiltonUtility.isEmpty(budget5) && budget5.length() > 17) {
			budget5 = budget5.substring(0, 17);
		}
		this.budget5 = budget5;
    }

    public java.lang.String getBudget6() {
		return (java.lang.String)HiltonUtility.ckNull(this.budget6).trim();
    }

    public void setBudget6(java.lang.String budget6) {
		if (!HiltonUtility.isEmpty(budget6) && budget6.length() > 17) {
			budget6 = budget6.substring(0, 17);
		}
		this.budget6 = budget6;
    }

    public java.lang.String getBudget7() {
		return (java.lang.String)HiltonUtility.ckNull(this.budget7).trim();
    }

    public void setBudget7(java.lang.String budget7) {
		if (!HiltonUtility.isEmpty(budget7) && budget7.length() > 17) {
			budget7 = budget7.substring(0, 17);
		}
		this.budget7 = budget7;
    }

    public java.lang.String getBudget8() {
		return (java.lang.String)HiltonUtility.ckNull(this.budget8).trim();
    }

    public void setBudget8(java.lang.String budget8) {
		if (!HiltonUtility.isEmpty(budget8) && budget8.length() > 17) {
			budget8 = budget8.substring(0, 17);
		}
		this.budget8 = budget8;
    }

    public java.lang.String getBudget9() {
		return (java.lang.String)HiltonUtility.ckNull(this.budget9).trim();
    }

    public void setBudget9(java.lang.String budget9) {
		if (!HiltonUtility.isEmpty(budget9) && budget9.length() > 17) {
			budget9 = budget9.substring(0, 17);
		}
		this.budget9 = budget9;
    }

    public java.lang.String getBudget10() {
		return (java.lang.String)HiltonUtility.ckNull(this.budget10).trim();
    }

    public void setBudget10(java.lang.String budget10) {
		if (!HiltonUtility.isEmpty(budget10) && budget10.length() > 17) {
			budget10 = budget10.substring(0, 17);
		}
		this.budget10 = budget10;
    }

    public java.lang.String getBudget11() {
		return (java.lang.String)HiltonUtility.ckNull(this.budget11).trim();
    }

    public void setBudget11(java.lang.String budget11) {
		if (!HiltonUtility.isEmpty(budget11) && budget11.length() > 17) {
			budget11 = budget11.substring(0, 17);
		}
		this.budget11 = budget11;
    }

    public java.lang.String getBudget12() {
		return (java.lang.String)HiltonUtility.ckNull(this.budget12).trim();
    }

    public void setBudget12(java.lang.String budget12) {
		if (!HiltonUtility.isEmpty(budget12) && budget12.length() > 17) {
			budget12 = budget12.substring(0, 17);
		}
		this.budget12 = budget12;
    }

    public java.lang.String getBudget13() {
		return (java.lang.String)HiltonUtility.ckNull(this.budget13).trim();
    }

    public void setBudget13(java.lang.String budget13) {
		if (!HiltonUtility.isEmpty(budget13) && budget13.length() > 17) {
			budget13 = budget13.substring(0, 17);
		}
		this.budget13 = budget13;
    }

    public java.lang.String getBudget14() {
		return (java.lang.String)HiltonUtility.ckNull(this.budget14).trim();
    }

    public void setBudget14(java.lang.String budget14) {
		if (!HiltonUtility.isEmpty(budget14) && budget14.length() > 17) {
			budget14 = budget14.substring(0, 17);
		}
		this.budget14 = budget14;
    }

    public java.lang.String getBudget15() {
		return (java.lang.String)HiltonUtility.ckNull(this.budget15).trim();
    }

    public void setBudget15(java.lang.String budget15) {
		if (!HiltonUtility.isEmpty(budget15) && budget15.length() > 17) {
			budget15 = budget15.substring(0, 17);
		}
		this.budget15 = budget15;
    }

    public java.math.BigDecimal getBudgeted() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.budgeted);
    }

    public void setBudgeted(java.math.BigDecimal budgeted) {
        this.budgeted = budgeted;
    }

    public java.math.BigDecimal getPreEncumbered() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.preEncumbered);
    }

    public void setPreEncumbered(java.math.BigDecimal preEncumbered) {
        this.preEncumbered = preEncumbered;
    }

    public java.math.BigDecimal getEncumbered() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.encumbered);
    }

    public void setEncumbered(java.math.BigDecimal encumbered) {
        this.encumbered = encumbered;
    }

    public java.math.BigDecimal getExpensed() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.expensed);
    }

    public void setExpensed(java.math.BigDecimal expensed) {
        this.expensed = expensed;
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

    public java.lang.String getOwnerPassword() {
		return (java.lang.String)HiltonUtility.ckNull(this.ownerPassword).trim();
    }

    public void setOwnerPassword(java.lang.String ownerPassword) {
		if (!HiltonUtility.isEmpty(ownerPassword) && ownerPassword.length() > 15) {
			ownerPassword = ownerPassword.substring(0, 15);
		}
		this.ownerPassword = ownerPassword;
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

    public java.lang.String getApproved() {
		return (java.lang.String)HiltonUtility.ckNull(this.approved).trim();
    }

    public void setApproved(java.lang.String approved) {
		if (!HiltonUtility.isEmpty(approved) && approved.length() > 1) {
			approved = approved.substring(0, 1);
		}
		this.approved = approved;
    }

    public java.lang.String getProjectId() {
		return (java.lang.String)HiltonUtility.ckNull(this.projectId).trim();
    }

    public void setProjectId(java.lang.String projectId) {
		if (!HiltonUtility.isEmpty(projectId) && projectId.length() > 15) {
			projectId = projectId.substring(0, 15);
		}
		this.projectId = projectId;
    }

    public java.lang.String getComments() {
		return (java.lang.String)HiltonUtility.ckNull(this.comments).trim();
    }

    public void setComments(java.lang.String comments) {
		if (!HiltonUtility.isEmpty(comments) && comments.length() > 200) {
			comments = comments.substring(0, 200);
		}
		this.comments = comments;
    }

    public java.lang.String getLastChangeBy() {
		return (java.lang.String)HiltonUtility.ckNull(this.lastChangeBy).trim();
    }

    public void setLastChangeBy(java.lang.String lastChangeBy) {
		if (!HiltonUtility.isEmpty(lastChangeBy) && lastChangeBy.length() > 15) {
			lastChangeBy = lastChangeBy.substring(0, 15);
		}
		this.lastChangeBy = lastChangeBy;
    }

    public java.util.Date getLastChangeDate() {
        return this.lastChangeDate;
    }

    public void setLastChangeDate(java.util.Date lastChangeDate) {
        this.lastChangeDate = lastChangeDate;
    }

    public java.math.BigDecimal getLastTranId() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.lastTranId);
    }

    public void setLastTranId(java.math.BigDecimal lastTranId) {
        this.lastTranId = lastTranId;
    }

    public java.math.BigDecimal getLastAuditId() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.lastAuditId);
    }

    public void setLastAuditId(java.math.BigDecimal lastAuditId) {
        this.lastAuditId = lastAuditId;
    }

    public java.lang.String getSpecificPeriod() {
		return (java.lang.String)HiltonUtility.ckNull(this.specificPeriod).trim();
    }

    public void setSpecificPeriod(java.lang.String specificPeriod) {
		if (!HiltonUtility.isEmpty(specificPeriod) && specificPeriod.length() > 15) {
			specificPeriod = specificPeriod.substring(0, 15);
		}
		this.specificPeriod = specificPeriod;
    }

    public java.math.BigDecimal getAmountIncrement() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.amountIncrement);
    }

    public void setAmountIncrement(java.math.BigDecimal amountIncrement) {
        this.amountIncrement = amountIncrement;
    }

    public java.util.Date getBudgetIncrementDate() {
        return this.budgetIncrementDate;
    }

    public void setBudgetIncrementDate(java.util.Date budgetIncrementDate) {
        this.budgetIncrementDate = budgetIncrementDate;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("budgetId", getBudgetId())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof BudgetCenter) ) return false;
        BudgetCenter castOther = (BudgetCenter) other;
        return new EqualsBuilder()
            .append(this.getBudgetId(), castOther.getBudgetId())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getBudgetId())
            .toHashCode();
    }

}