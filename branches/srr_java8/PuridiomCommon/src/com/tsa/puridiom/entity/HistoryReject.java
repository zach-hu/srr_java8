package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;
import java.math.BigDecimal;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class HistoryReject implements Serializable {

    /** identifier field */
    private com.tsa.puridiom.entity.HistoryRejectPK comp_id;

    /** nullable persistent field */
    private java.math.BigDecimal amount;

    /** nullable persistent field */
    private java.math.BigDecimal approverAmount;

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
    private String ruleSource ;

    /** nullable persistent field */
    private String approverNotes ;

    /** nullable persistent field */
    private String fyiApprover;

    /** nullable persistent field */
    private String requiredApprover;

    /** nullable persistent field */
    private java.math.BigDecimal excludeLess;

    /** nullable persistent field */
    private String pooldesc;

    /** nullable persistent field */
    private String backupApprover;

    /** nullable persistent field */
    private String advisor;

    /** nullable persistent field */
    private String recommendation;

    private String approverName;

    private String ruleNotes;

    private BigDecimal ruleOrder;

    private boolean sendTo = false;

    /** full constructor */
    public HistoryReject(com.tsa.puridiom.entity.HistoryRejectPK comp_id, java.math.BigDecimal amount, java.math.BigDecimal approverAmount, java.lang.String approved, java.lang.String udfValues, java.lang.String authorized, java.lang.String alternateUserid, java.util.Date dateAssigned, java.util.Date dateApproved, java.lang.String approverType, java.lang.String ruleType, java.lang.String poolid, java.lang.String ruleAction, java.lang.String approverSig, java.lang.String callForward, java.lang.String fyiApprover, java.lang.String requiredApprover, java.math.BigDecimal excludeLess, java.lang.String pooldesc, java.lang.String backupApprover, java.lang.String advisor, java.lang.String recommendation) {
        this.comp_id = comp_id;
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
        this.fyiApprover = fyiApprover;
        this.requiredApprover = requiredApprover;
        this.excludeLess = excludeLess;
        this.pooldesc = pooldesc;
        this.backupApprover = backupApprover;
        this.advisor = advisor;
        this.recommendation = recommendation;
    }

    /** default constructor */
    public HistoryReject() {
    }

    /** minimal constructor */
    public HistoryReject(com.tsa.puridiom.entity.HistoryRejectPK comp_id) {
        this.comp_id = comp_id;
    }

    public com.tsa.puridiom.entity.HistoryRejectPK getComp_id() {
        return this.comp_id;
    }

    public void setComp_id(com.tsa.puridiom.entity.HistoryRejectPK comp_id) {
        this.comp_id = comp_id;
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
//		return HiltonUtility.ckNull(this.dateAssigned);
//		return (java.sql.Date)HiltonUtility.ckNull(this.dateAssigned);
    }

    public void setDateAssigned(java.util.Date dateAssigned) {
        this.dateAssigned = dateAssigned;
    }

    public java.util.Date getDateApproved() {
		return this.dateApproved;
//		return HiltonUtility.ckNull(this.dateApproved);
//		return (java.sql.Date)HiltonUtility.ckNull(this.dateApproved);
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

	/**
	 * @return Returns the approverNotes.
	 */
	public String getApproverNotes() {
		return (java.lang.String)HiltonUtility.ckNull(this.approverNotes).trim();
	}
	/**
	 * @param approverNotes The approverNotes to set.
	 */
	public void setApproverNotes(String approverNotes) {
		if (!HiltonUtility.isEmpty(approverNotes) && approverNotes.length() > 255) {
			approverNotes = approverNotes.substring(0, 255);
		}
		this.approverNotes = approverNotes;
	}
	/**
	 * @return Returns the ruleSource.
	 */
	public String getRuleSource() {
		return (java.lang.String)HiltonUtility.ckNull(this.ruleSource).trim();
	}
	/**
	 * @param ruleSource The ruleSource to set.
	 */
	public void setRuleSource(String ruleSource) {
		if (!HiltonUtility.isEmpty(ruleSource) && ruleSource.length() > 3) {
			ruleSource = ruleSource.substring(0, 3);
		}
		this.ruleSource = ruleSource;
	}

    public java.lang.String getBackupApprover() {
		return (java.lang.String)HiltonUtility.ckNull(this.backupApprover).trim();
    }

    public void setBackupApprover(java.lang.String backupApprover) {
		if (!HiltonUtility.isEmpty(backupApprover) && backupApprover.length() > 15) {
		    backupApprover = backupApprover.substring(0, 15);
		}
		this.backupApprover = backupApprover;
    }

    public java.lang.String getRecommendation() {
		return (java.lang.String)HiltonUtility.ckNull(this.recommendation).trim();
    }

    public void setRecommendation(java.lang.String recommendation) {
		if (!HiltonUtility.isEmpty(recommendation) && recommendation.length() > 1) {
		    recommendation = recommendation.substring(0, 1);
		}
		this.recommendation = recommendation;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("comp_id", getComp_id())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof HistoryReject) ) return false;
        HistoryReject castOther = (HistoryReject) other;
        return new EqualsBuilder()
            .append(this.getComp_id(), castOther.getComp_id())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getComp_id())
            .toHashCode();
    }

    /**
     * @return Returns the approverName.
     */
    public String getApproverName() {
        return approverName;
    }
    /**
     * @param approverName The approverName to set.
     */
    public void setApproverName(String approverName) {
        this.approverName = approverName;
    }
    public String getUserId() {
    	return this.getComp_id().getUserId();
    }
    public String getFyiApprover() {
        return HiltonUtility.ckNull(this.fyiApprover);
    }
    public boolean isAnFyiApprover() {
        return this.getFyiApprover().equalsIgnoreCase("Y");
    }
    public void setFyiApprover(String fyiApprover) {
        this.fyiApprover = fyiApprover;
    }
    public String getRequiredApprover() {
        return HiltonUtility.ckNull(this.requiredApprover);
    }
    public boolean isARequiredApprover() {
        return this.getRequiredApprover().equalsIgnoreCase("Y");
    }
    public void setRequiredApprover(String requiredApprover) {
        this.requiredApprover = requiredApprover;
    }
    public java.math.BigDecimal getExcludeLess() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.excludeLess);
    }
    public void setExcludeLess(java.math.BigDecimal excludeLess) {
        this.excludeLess = excludeLess;
    }
    public String getPooldesc() {
        return HiltonUtility.ckNull(pooldesc);
    }
    public void setPooldesc(String pooldesc) {
        this.pooldesc = pooldesc;
    }
    public String getRuleNotes() {
        return HiltonUtility.ckNull(this.ruleNotes);
    }
    public void setRuleNotes(String ruleNotes) {
        this.ruleNotes = ruleNotes;
    }
    public java.math.BigDecimal getRuleOrder() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.ruleOrder);
    }
    public void setRuleOrder(java.math.BigDecimal ruleOrder) {
        this.ruleOrder = ruleOrder;
    }
    public String getAdvisor() {
        return (java.lang.String)HiltonUtility.ckNull(this.advisor).trim();
    }
    public boolean isAnAdvisor() {
        return this.getAdvisor().equalsIgnoreCase("Y");
    }
    public void setAdvisor(String advisor) {
    	if (!HiltonUtility.isEmpty(advisor) && advisor.length() > 1) {
    	    advisor = advisor.substring(0, 1);
		}
        this.advisor = advisor;
    }
    public boolean isSendTo() {
        return this.sendTo;
    }
    public void setSendTo(boolean sendTo) {
        this.sendTo = sendTo;
    }
}
