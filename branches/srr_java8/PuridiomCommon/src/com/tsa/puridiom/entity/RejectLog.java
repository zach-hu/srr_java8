package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;
import java.math.BigDecimal;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class RejectLog implements Serializable {

    /** identifier field */
    private BigDecimal icReject;

    /** persistent field */
    private BigDecimal icHeader;

    /** persistent field */
    private BigDecimal sequence;

    /** persistent field */
    private String userId;

    /** nullable persistent field */
    private BigDecimal amount;

    /** nullable persistent field */
    private BigDecimal approverAmount;

    /** nullable persistent field */
    private String approved;

    /** nullable persistent field */
    private String udfValues;

    /** nullable persistent field */
    private String authorized;

    /** nullable persistent field */
    private String alternateUserid;

    /** nullable persistent field */
    private java.util.Date dateAssigned;

    /** nullable persistent field */
    private java.util.Date dateApproved;

    /** nullable persistent field */
    private String approverType;

    /** nullable persistent field */
    private String ruleType;

    /** nullable persistent field */
    private String poolid;

    /** nullable persistent field */
    private String ruleAction;

    /** nullable persistent field */
    private String approverSig;

    /** nullable persistent field */
    private String callForward;

    /** nullable persistent field */
    private String ruleSource;

    /** nullable persistent field */
    private String approverNotes;

    /** full constructor */
    public RejectLog(java.math.BigDecimal icReject, java.math.BigDecimal icHeader, BigDecimal sequence, java.lang.String userId, java.math.BigDecimal amount, java.math.BigDecimal approverAmount, java.lang.String approved, java.lang.String udfValues, java.lang.String authorized, java.lang.String alternateUserid, java.util.Date dateAssigned, java.util.Date dateApproved, java.lang.String approverType, java.lang.String ruleType, java.lang.String poolid, java.lang.String ruleAction, java.lang.String approverSig, java.lang.String callForward, java.lang.String ruleSource, java.lang.String approverNotes) {
        this.icReject = icReject;
        this.icHeader = icHeader;
        this.sequence = sequence;
        this.userId = userId;
        this.amount = amount;
        this.approverAmount = approverAmount;
        this.approved = approved;
        this.udfValues = udfValues;
        this.authorized = authorized;
        this.alternateUserid = alternateUserid;
        this.dateAssigned = dateAssigned;
        this.dateApproved = dateApproved;
        this.approverType = approverType;
        this.ruleType = ruleType;
        this.poolid = poolid;
        this.ruleAction = ruleAction;
        this.approverSig = approverSig;
        this.callForward = callForward;
        this.ruleSource = ruleSource;
        this.approverNotes = approverNotes;
    }
    public RejectLog(ApprovalLog approvalLog)
    {
    	this.icHeader = approvalLog.getComp_id().getIcHeader();
    	this.sequence = approvalLog.getComp_id().getSequence();
    	this.userId = approvalLog.getComp_id().getUserId();
        this.amount = approvalLog.getAmount();
        this.approverAmount = approvalLog.getApproverAmount();
        this.approved = approvalLog.getApproved();
        this.udfValues = approvalLog.getUdfValues();
        this.authorized = approvalLog.getAuthorized();
        this.alternateUserid = approvalLog.getAlternateUserid();
        this.dateAssigned = approvalLog.getDateAssigned();
        this.dateApproved = approvalLog.getDateApproved();
        this.approverType = approvalLog.getApproverType();
        this.ruleType = approvalLog.getRuleType();
        this.poolid = approvalLog.getPoolid();
        this.ruleAction = approvalLog.getRuleAction();
        this.approverSig = approvalLog.getApproverSig();
        this.callForward = approvalLog.getCallForward();
        this.ruleSource = approvalLog.getRuleSource();
        this.approverNotes = approvalLog.getApproverNotes();
    }

    /** default constructor */
    public RejectLog() {
    }

    /** minimal constructor */
    public RejectLog(java.math.BigDecimal icReject)
    {
        this.icReject = icReject;
    }

    public java.math.BigDecimal getIcReject() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.icReject);
    }

    public void setIcReject(java.math.BigDecimal icReject) {
        this.icReject = icReject;
    }

    public java.math.BigDecimal getIcHeader() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.icHeader);
    }

    public void setIcHeader(java.math.BigDecimal icHeader) {
        this.icHeader = icHeader;
    }

    public java.math.BigDecimal getSequence() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.sequence);
    }

    public void setSequence(java.math.BigDecimal sequence) {
        this.sequence = sequence;
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

    public java.math.BigDecimal getAmount() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.amount);
    }

    public void setAmount(java.math.BigDecimal amount) {
        this.amount = amount;
    }

    public java.math.BigDecimal getApproverAmount() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.approverAmount);
    }

    public void setApproverAmount(java.math.BigDecimal approverAmount) {
        this.approverAmount = approverAmount;
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

    public java.lang.String getUdfValues() {
		return (java.lang.String)HiltonUtility.ckNull(this.udfValues).trim();
    }

    public void setUdfValues(java.lang.String udfValues) {
		if (!HiltonUtility.isEmpty(udfValues) && udfValues.length() > 254) {
			udfValues = udfValues.substring(0, 254);
		}
		this.udfValues = udfValues;
    }

    public java.lang.String getAuthorized() {
		return (java.lang.String)HiltonUtility.ckNull(this.authorized).trim();
    }

    public void setAuthorized(java.lang.String authorized) {
		if (!HiltonUtility.isEmpty(authorized) && authorized.length() > 1) {
			authorized = authorized.substring(0, 1);
		}
		this.authorized = authorized;
    }

    public java.lang.String getAlternateUserid() {
		return (java.lang.String)HiltonUtility.ckNull(this.alternateUserid).trim();
    }

    public void setAlternateUserid(java.lang.String alternateUserid) {
		if (!HiltonUtility.isEmpty(alternateUserid) && alternateUserid.length() > 15) {
			alternateUserid = alternateUserid.substring(0, 15);
		}
		this.alternateUserid = alternateUserid;
    }

    public java.util.Date getDateAssigned() {
        return this.dateAssigned;
    }

    public void setDateAssigned(java.util.Date dateAssigned) {
        this.dateAssigned = dateAssigned;
    }

    public java.util.Date getDateApproved() {
        return this.dateApproved;
    }

    public void setDateApproved(java.util.Date dateApproved) {
        this.dateApproved = dateApproved;
    }

    public java.lang.String getApproverType() {
		return (java.lang.String)HiltonUtility.ckNull(this.approverType).trim();
    }

    public void setApproverType(java.lang.String approverType) {
		if (!HiltonUtility.isEmpty(approverType) && approverType.length() > 1) {
			approverType = approverType.substring(0, 1);
		}
		this.approverType = approverType;
    }

    public java.lang.String getRuleType() {
		return (java.lang.String)HiltonUtility.ckNull(this.ruleType).trim();
    }

    public void setRuleType(java.lang.String ruleType) {
		if (!HiltonUtility.isEmpty(ruleType) && ruleType.length() > 3) {
			ruleType = ruleType.substring(0, 3);
		}
		this.ruleType = ruleType;
    }

    public java.lang.String getPoolid() {
		return (java.lang.String)HiltonUtility.ckNull(this.poolid).trim();
    }

    public void setPoolid(java.lang.String poolid) {
		if (!HiltonUtility.isEmpty(poolid) && poolid.length() > 15) {
			poolid = poolid.substring(0, 15);
		}
		this.poolid = poolid;
    }

    public java.lang.String getRuleAction() {
		return (java.lang.String)HiltonUtility.ckNull(this.ruleAction).trim();
    }

    public void setRuleAction(java.lang.String ruleAction) {
		if (!HiltonUtility.isEmpty(ruleAction) && ruleAction.length() > 1) {
			ruleAction = ruleAction.substring(0, 1);
		}
		this.ruleAction = ruleAction;
    }

    public java.lang.String getApproverSig() {
		return (java.lang.String)HiltonUtility.ckNull(this.approverSig).trim();
    }

    public void setApproverSig(java.lang.String approverSig) {
		if (!HiltonUtility.isEmpty(approverSig) && approverSig.length() > 1) {
			approverSig = approverSig.substring(0, 1);
		}
		this.approverSig = approverSig;
    }

    public java.lang.String getCallForward() {
		return (java.lang.String)HiltonUtility.ckNull(this.callForward).trim();
    }

    public void setCallForward(java.lang.String callForward) {
		if (!HiltonUtility.isEmpty(callForward) && callForward.length() > 15) {
			callForward = callForward.substring(0, 15);
		}
		this.callForward = callForward;
    }

    public java.lang.String getRuleSource() {
		return (java.lang.String)HiltonUtility.ckNull(this.ruleSource).trim();
    }

    public void setRuleSource(java.lang.String ruleSource) {
		if (!HiltonUtility.isEmpty(ruleSource) && ruleSource.length() > 3) {
			ruleSource = ruleSource.substring(0, 3);
		}
		this.ruleSource = ruleSource;
    }

    public java.lang.String getApproverNotes() {
		return (java.lang.String)HiltonUtility.ckNull(this.approverNotes).trim();
    }

    public void setApproverNotes(java.lang.String approverNotes) {
		if (!HiltonUtility.isEmpty(approverNotes) && approverNotes.length() > 255) {
			approverNotes = approverNotes.substring(0, 255);
		}
		this.approverNotes = approverNotes;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("icReject", getIcReject())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof RejectLog) ) return false;
        RejectLog castOther = (RejectLog) other;
        return new EqualsBuilder()
            .append(this.getIcReject(), castOther.getIcReject())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getIcReject())
            .toHashCode();
    }

}
