package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class AppRulesExt implements Serializable {

    /** identifier field */
    private com.tsa.puridiom.entity.AppRulesExtPK comp_id;

    /** nullable persistent field */
    private java.math.BigDecimal ruleOrder;

    /** nullable persistent field */
    private String ruleFilename;

    /** nullable persistent field */
    private String routto;

    /** nullable persistent field */
    private String ruleAction;

    /** nullable persistent field */
    private String ruleText;

    /** nullable persistent field */
    private String notes;

    /** nullable persistent field */
    private java.math.BigDecimal approverAmount;

    /** nullable persistent field */
    private java.math.BigDecimal requiredAuthority;

    /** nullable persistent field */
    private String reqdAuthObject;

    /** nullable persistent field */
    private String reqdAuthSource;

    /** nullable persistent field */
    private String reqdAuthName;

    /** nullable persistent field */
    private String reqdAuthValue;

    /** nullable persistent field */
    private String fyiApprover;

    /** nullable persistent field */
    private String requiredApprover;

    /** nullable persistent field */
    private String advisor;

    /** nullable persistent field */
    private String superRule;

    /** nullable persistent field */
    private String enabled;
    
    private String requestType;

    private boolean result = false;

    /** full constructor */
    public AppRulesExt(com.tsa.puridiom.entity.AppRulesExtPK comp_id, java.math.BigDecimal ruleOrder, String ruleFilename, String routto, String ruleAction, String ruleText, String notes, java.math.BigDecimal approverAmount, java.math.BigDecimal requiredAuthority, String reqdAuthObject, String reqdAuthName, String reqdAuthSource, String reqdAuthValue, String fyiApprover, String requiredApprover, String advisor, String superRule, String enabled, String requestType) {
        this.comp_id = comp_id;
        this.ruleOrder = ruleOrder;
        this.ruleFilename = ruleFilename;
        this.routto = routto;
        this.ruleAction = ruleAction;
        this.ruleText = ruleText;
        this.notes = notes;
        this.approverAmount = approverAmount;
        this.requiredAuthority = requiredAuthority;
        this.reqdAuthObject = reqdAuthObject;
        this.reqdAuthName = reqdAuthName;
        this.reqdAuthSource = reqdAuthSource;
        this.reqdAuthValue = reqdAuthValue;
        this.fyiApprover = fyiApprover;
        this.requiredApprover = requiredApprover;
        this.advisor = advisor;
        this.superRule = superRule;
        this.enabled = enabled;
        this.requestType = requestType;
    }

    /** default constructor */
    public AppRulesExt() {
    }

    /** minimal constructor */
    public AppRulesExt(com.tsa.puridiom.entity.AppRulesExtPK comp_id) {
        this.comp_id = comp_id;
    }

    public com.tsa.puridiom.entity.AppRulesExtPK getComp_id() {
        return this.comp_id;
    }

    public void setComp_id(com.tsa.puridiom.entity.AppRulesExtPK comp_id) {
        this.comp_id = comp_id;
    }

    public java.math.BigDecimal getRuleOrder() {
        return (java.math.BigDecimal)HiltonUtility.ckNull(this.ruleOrder);
    }

    public void setRuleOrder(java.math.BigDecimal ruleOrder) {
        this.ruleOrder = ruleOrder;
    }

    public java.lang.String getRuleFilename() {
		return (java.lang.String)HiltonUtility.ckNull(this.ruleFilename).trim();
    }

    public void setRuleFilename(java.lang.String ruleFilename) {
		if (!HiltonUtility.isEmpty(ruleFilename) && ruleFilename.length() > 60) {
			ruleFilename = ruleFilename.substring(0, 60);
		}
		this.ruleFilename = ruleFilename;
    }

    public java.lang.String getRoutto() {
		return (java.lang.String)HiltonUtility.ckNull(this.routto).trim();
    }

    public void setRoutto(java.lang.String routto) {
		if (!HiltonUtility.isEmpty(routto) && routto.length() > 16) {
			routto = routto.substring(0, 16);
		}
		this.routto = routto;
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

    public java.lang.String getRuleText() {
        return (java.lang.String)HiltonUtility.ckNull(this.ruleText).trim();
    }

    public void setRuleText(java.lang.String ruleText) {
        if (!HiltonUtility.isEmpty(ruleText) && ruleText.length() > 255) {
            ruleText = ruleText.substring(0, 255);
        }
        this.ruleText = ruleText;
    }

    public java.lang.String getNotes() {
        return (java.lang.String)HiltonUtility.ckNull(this.notes).trim();
    }

    public void setNotes(java.lang.String notes) {
        if (!HiltonUtility.isEmpty(notes) && notes.length() > 60) {
            notes = notes.substring(0, 60);
        }
        this.notes = notes;
    }

    public java.math.BigDecimal getApproverAmount() {
        return (java.math.BigDecimal)HiltonUtility.ckNull(this.approverAmount);
    }

    public void setApproverAmount(java.math.BigDecimal approverAmount) {
        this.approverAmount = approverAmount;
    }

    public java.math.BigDecimal getRequiredAuthority() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.requiredAuthority);
    }

    public void setRequiredAuthority(java.math.BigDecimal requiredAuthority) {
        this.requiredAuthority = requiredAuthority;
    }

    public java.lang.String getReqdAuthObject() {
        return (java.lang.String)HiltonUtility.ckNull(this.reqdAuthObject).trim();
    }

    public void setReqdAuthObject(java.lang.String reqdAuthObject) {
        if (!HiltonUtility.isEmpty(reqdAuthObject) && reqdAuthObject.length() > 30) {
            reqdAuthObject = reqdAuthObject.substring(0, 30);
        }
        this.reqdAuthObject = reqdAuthObject;
    }

    public java.lang.String getReqdAuthName() {
        return (java.lang.String)HiltonUtility.ckNull(this.reqdAuthName).trim();
    }

    public void setReqdAuthName(java.lang.String reqdAuthName) {
        if (!HiltonUtility.isEmpty(reqdAuthName) && reqdAuthName.length() > 30) {
            reqdAuthName = reqdAuthName.substring(0, 30);
        }
        this.reqdAuthName = reqdAuthName;
    }

    public java.lang.String getReqdAuthSource() {
        return (java.lang.String)HiltonUtility.ckNull(this.reqdAuthSource).trim();
    }

    public void setReqdAuthSource(java.lang.String reqdAuthSource) {
        if (!HiltonUtility.isEmpty(reqdAuthSource) && reqdAuthSource.length() > 30) {
            reqdAuthSource = reqdAuthSource.substring(0, 30);
        }
        this.reqdAuthSource = reqdAuthSource;
    }

    public java.lang.String getReqdAuthValue() {
        return (java.lang.String)HiltonUtility.ckNull(this.reqdAuthValue).trim();
    }

    public void setReqdAuthValue(java.lang.String reqdAuthValue) {
        if (!HiltonUtility.isEmpty(reqdAuthValue) && reqdAuthValue.length() > 30) {
            reqdAuthValue = reqdAuthValue.substring(0, 30);
        }
        this.reqdAuthValue = reqdAuthValue;
    }

    public java.lang.String getFyiApprover() {
		return (java.lang.String)HiltonUtility.ckNull(this.fyiApprover).trim();
    }

    public void setFyiApprover(java.lang.String fyiApprover) {
		if (!HiltonUtility.isEmpty(fyiApprover) && fyiApprover.length() > 1) {
			fyiApprover = fyiApprover.substring(0, 1);
		}
		this.fyiApprover = fyiApprover;
    }

    public boolean isFyiApproverValue() {
        return this.getFyiApprover().equalsIgnoreCase("Y");
    }

    public java.lang.String getRequiredApprover() {
        return (java.lang.String)HiltonUtility.ckNull(this.requiredApprover).trim();
    }

    public void setRequiredApprover(java.lang.String requiredApprover) {
        if (!HiltonUtility.isEmpty(requiredApprover) && requiredApprover.length() > 1) {
            requiredApprover = requiredApprover.substring(0, 1);
        }
        this.requiredApprover = requiredApprover;
    }

    public boolean isRequiredApproverValue() {
        return this.getRequiredApprover().equalsIgnoreCase("Y");
    }

    public java.lang.String getAdvisor() {
        return (java.lang.String)HiltonUtility.ckNull(this.advisor).trim();
    }

    public void setAdvisor(java.lang.String advisor) {
        if (!HiltonUtility.isEmpty(advisor) && advisor.length() > 1) {
            advisor = advisor.substring(0, 1);
        }
        this.advisor = advisor;
    }

    public boolean isAnAdvisorValue() {
        return this.getAdvisor().equalsIgnoreCase("Y");
    }

    public java.lang.String getSuperRule() {
        return (java.lang.String)HiltonUtility.ckNull(this.superRule).trim();
    }

    public void setSuperRule(java.lang.String superRule) {
        if (!HiltonUtility.isEmpty(superRule) && superRule.length() > 1) {
            superRule = superRule.substring(0, 1);
        }
        this.superRule = superRule;
    }

    public java.lang.String getRequestType() {
        return (java.lang.String)HiltonUtility.ckNull(this.requestType).trim();
    }

    public void setRequestType(java.lang.String requestType) {
        if (!HiltonUtility.isEmpty(requestType) && requestType.length() > 20) {
        	requestType = requestType.substring(0, 20);
        }
        this.requestType = requestType;
    }

    public boolean isEnabledValue() {
        return this.getEnabled().equalsIgnoreCase("Y");
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

    public String toString() {
        return new ToStringBuilder(this)
            .append("comp_id", getComp_id())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof AppRulesExt) ) return false;
        AppRulesExt castOther = (AppRulesExt) other;
        return new EqualsBuilder()
            .append(this.getComp_id(), castOther.getComp_id())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getComp_id())
            .toHashCode();
    }

    public boolean isResult() {
        return this.result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }
    
}
