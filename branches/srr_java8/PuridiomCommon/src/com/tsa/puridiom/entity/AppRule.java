package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsagate.foundation.database.Entity;
import com.tsagate.foundation.utility.Utility;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class AppRule extends Entity implements Serializable {

    /** identifier field */
    private com.tsa.puridiom.entity.AppRulePK comp_id;

    /** persistent field */
    private java.math.BigDecimal amount;

    /** nullable persistent field */
    private String udf8Code;

    /** nullable persistent field */
    private String udf9Code;

    /** nullable persistent field */
    private String udf10Code;

    /** nullable persistent field */
    private java.math.BigDecimal approverLevel;

    /** nullable persistent field */
    private java.math.BigDecimal excludeLess;

    /** nullable persistent field */
    private String fyiApprover;

    /** nullable persistent field */
    private String requiredApprover;

    /** nullable persistent field */
    private String advisor;

    /** nullable persistent field */
    private String notes;

    /** Temporary Holding columns for User Data */

    private java.math.BigDecimal userExcludeLess;
    private java.math.BigDecimal userAmount;
    private String	userCallForward ;
    private String	userApproveByLine ;
    private String	userName;
    private String	ruleData;

    /** full constructor */
    public AppRule(com.tsa.puridiom.entity.AppRulePK comp_id, java.math.BigDecimal amount, java.lang.String udf8Code, java.lang.String udf9Code, java.lang.String udf10Code, java.math.BigDecimal approverLevel, java.math.BigDecimal excludeLess, java.lang.String fyiApprover, java.lang.String requiredApprover, java.lang.String notes, java.lang.String advisor) {
        this.comp_id = comp_id;
        this.amount = amount;
        this.udf8Code = udf8Code;
        this.udf9Code = udf9Code;
        this.udf10Code = udf10Code;
        this.approverLevel = approverLevel;
        this.excludeLess = excludeLess;
        this.fyiApprover = fyiApprover;
        this.requiredApprover = requiredApprover;
        this.notes = notes;
        this.advisor = advisor;
    }

    /** default constructor */
    public AppRule() {
    }

    /** minimal constructor */
    public AppRule(com.tsa.puridiom.entity.AppRulePK comp_id, java.math.BigDecimal amount) {
        this.comp_id = comp_id;
        this.amount = amount;
    }

    public com.tsa.puridiom.entity.AppRulePK getComp_id() {
        return this.comp_id;
    }

    public void setComp_id(com.tsa.puridiom.entity.AppRulePK comp_id) {
        this.comp_id = comp_id;
    }

    public java.math.BigDecimal getAmount() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.amount);
    }

    public void setAmount(java.math.BigDecimal amount) {
        this.amount = amount;
    }

    public java.lang.String getUdf8Code() {
		return (java.lang.String)Utility.ckNull(this.udf8Code).trim();
    }

    public void setUdf8Code(java.lang.String udf8Code) {
		if (!HiltonUtility.isEmpty(udf8Code) && udf8Code.length() > 50) {
			udf8Code = udf8Code.substring(0, 50);
		}
		this.udf8Code = udf8Code;
    }

    public java.lang.String getUdf9Code() {
		return (java.lang.String)Utility.ckNull(this.udf9Code).trim();
    }

    public void setUdf9Code(java.lang.String udf9Code) {
		if (!HiltonUtility.isEmpty(udf9Code) && udf9Code.length() > 50) {
			udf9Code = udf9Code.substring(0, 50);
		}
		this.udf9Code = udf9Code;
    }

    public java.lang.String getUdf10Code() {
		return (java.lang.String)Utility.ckNull(this.udf10Code).trim();
    }

    public void setUdf10Code(java.lang.String udf10Code) {
		if (!HiltonUtility.isEmpty(udf10Code) && udf10Code.length() > 50) {
			udf10Code = udf10Code.substring(0, 50);
		}
		this.udf10Code = udf10Code;
    }

    public java.math.BigDecimal getApproverLevel() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.approverLevel);
    }

    public void setApproverLevel(java.math.BigDecimal approverLevel) {
        this.approverLevel = approverLevel;
    }

    public java.math.BigDecimal getExcludeLess() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.excludeLess);
    }

    public void setExcludeLess(java.math.BigDecimal excludeLess) {
        this.excludeLess = excludeLess;
    }

    public String getFyiApprover() {
        return (java.lang.String)Utility.ckNull(this.fyiApprover).trim();
    }

    public boolean isAnFyiApprover() {
        return this.getFyiApprover().equalsIgnoreCase("Y");
    }

    public void setFyiApprover(String fyiApprover) {
    	if (!HiltonUtility.isEmpty(fyiApprover) && fyiApprover.length() > 1) {
			fyiApprover = fyiApprover.substring(0, 1);
		}
        this.fyiApprover = fyiApprover;
    }

    public String getRequiredApprover() {
        return (java.lang.String)Utility.ckNull(this.requiredApprover).trim();
    }

    public boolean isARequiredApprover() {
        return this.getRequiredApprover().equalsIgnoreCase("Y");
    }

    public void setRequiredApprover(String requiredApprover) {
		if (!HiltonUtility.isEmpty(requiredApprover) && requiredApprover.length() > 1) {
			requiredApprover = requiredApprover.substring(0, 1);
		}
        this.requiredApprover = requiredApprover;
    }

    public String getNotes() {
        return (java.lang.String)Utility.ckNull(this.notes).trim();
    }

    public void setNotes(String notes) {
		if (!HiltonUtility.isEmpty(notes) && notes.length() > 60) {
			notes = notes.substring(0, 60);
		}
        this.notes = notes;
    }

    public String getAdvisor() {
        return (java.lang.String)Utility.ckNull(this.advisor).trim();
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

    public String toString() {
        return new ToStringBuilder(this)
        	.append("Amount: " + getAmount())
            .append("comp_id", getComp_id())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof AppRule) ) return false;
        AppRule castOther = (AppRule) other;
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
	 * @return Returns the userAmount.
	 */
	public java.math.BigDecimal getUserAmount() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.userAmount);
	}
	/**
	 * @param userAmount The userAmount to set.
	 */
	public void setUserAmount(java.math.BigDecimal userAmount) {
		this.userAmount = userAmount;
	}
	/**
	 * @return Returns the userApproveByLine.
	 */
	public String getUserApproveByLine() {
		return (java.lang.String)Utility.ckNull(this.userApproveByLine).trim();
	}
	/**
	 * @param userApproveByLine The userApproveByLine to set.
	 */
	public void setUserApproveByLine(String userApproveByLine) {
		this.userApproveByLine = userApproveByLine;
	}
	/**
	 * @return Returns the userCallForward.
	 */
	public String getUserCallForward() {
		return (java.lang.String)Utility.ckNull(this.userCallForward).trim();
	}
	/**
	 * @param userCallForward The userCallForward to set.
	 */
	public void setUserCallForward(String userCallForward) {
		this.userCallForward = userCallForward;
	}
	/**
	 * @return Returns the userExcludeLess.
	 */
	public java.math.BigDecimal getUserExcludeLess() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.userExcludeLess);
	}
	/**
	 * @param userExcludeLess The userExcludeLess to set.
	 */
	public void setUserExcludeLess(java.math.BigDecimal userExcludeLess) {
		this.userExcludeLess = userExcludeLess;
	}
	/**
	 * @return Returns the userName.
	 */
	public String getUserName() {
		return (java.lang.String)Utility.ckNull(this.userName).trim();
	}
	/**
	 * @param userName The userName to set.
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @return Returns the ruleData.
	 */
	public String getRuleData() {
		return (java.lang.String)Utility.ckNull(this.ruleData).trim();
	}
	/**
	 * @param ruleData The ruleData to set.
	 */
	public void setRuleData(String ruleData) {
		this.ruleData = ruleData;
	}
}
