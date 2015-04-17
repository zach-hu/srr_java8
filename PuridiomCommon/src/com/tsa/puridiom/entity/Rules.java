package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;

import java.io.Serializable;

public class Rules implements Serializable {
	private java.math.BigDecimal icRule;
	private String ruleName;
	private String ruleOrder;
	private String ruleExpression;
	private String validationMessage;
	private String validationSeverity;
	private String validationLink;
	private String fieldName;
	private String enabled;
	private String moduleAccess;
	private String status;
	private String owner;
	private String lastChangeBy;
	private java.util.Date lastChangeDate;

	public Rules(java.math.BigDecimal icRule, String ruleName, String ruleOrder, String ruleExpression, String validationMessage, String validationSeverity,
			String validationLink, String fieldName, String enabled, String moduleAccess, String status, String owner, String lastChangeBy, java.util.Date lastChangeDate) {
		this.icRule = icRule;
		this.ruleName = ruleName;
		this.ruleOrder = ruleOrder;
		this.ruleExpression = ruleExpression;
		this.validationMessage = validationMessage;
		this.validationSeverity = validationSeverity;
		this.validationLink = validationLink;
		this.fieldName = fieldName;
		this.enabled = enabled;
		this.moduleAccess = moduleAccess;
		this.status = status;
		this.owner =  owner;
		this.lastChangeBy = lastChangeBy;
		this.lastChangeDate = lastChangeDate;
	}

	public Rules() {
	}

	public Rules(java.math.BigDecimal icRule) {
		this.icRule = icRule;
	}

	public java.math.BigDecimal getIcRule() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.icRule);
	}

	public void setIcRule(java.math.BigDecimal icRule) {
		this.icRule = icRule;
	}

	public String getRuleName() {
		return (java.lang.String)HiltonUtility.ckNull(this.ruleName).trim();
	}

	public void setRuleName(String ruleName) {
        if (!HiltonUtility.isEmpty(ruleName) && ruleName.length() > 40) {
        	ruleName = ruleName.substring(0, 40);
        }
		this.ruleName = ruleName;
	}

	public String getRuleOrder() {
		return (java.lang.String)HiltonUtility.ckNull(this.ruleOrder).trim();
	}

	public void setRuleOrder(String ruleOrder) {
        if (!HiltonUtility.isEmpty(ruleOrder) && ruleOrder.length() > 5) {
        	ruleOrder = ruleOrder.substring(0, 5);
        }
		this.ruleOrder = ruleOrder;
	}

	public String getRuleExpression() {
		return (java.lang.String)HiltonUtility.ckNull(this.ruleExpression).trim();
	}

	public void setRuleExpression(String ruleExpression) {
        if (!HiltonUtility.isEmpty(ruleExpression) && ruleExpression.length() > 2000) {
        	ruleExpression = ruleExpression.substring(0, 2000);
        }
		this.ruleExpression = ruleExpression;
	}

	public String getValidationMessage() {
		return (java.lang.String)HiltonUtility.ckNull(this.validationMessage).trim();
	}

	public void setValidationMessage(String validationMessage) {
        if (!HiltonUtility.isEmpty(validationMessage) && validationMessage.length() > 255) {
        	validationMessage = validationMessage.substring(0, 255);
        }
		this.validationMessage = validationMessage;
	}

	public String getValidationSeverity() {
		return (java.lang.String)HiltonUtility.ckNull(this.validationSeverity).trim();
	}

	public void setValidationSeverity(String validationSeverity) {
        if (!HiltonUtility.isEmpty(validationSeverity) && validationSeverity.length() > 1) {
        	validationSeverity = validationSeverity.substring(0, 1);
        }
		this.validationSeverity = validationSeverity;
	}

	public String getValidationLink() {
		return (java.lang.String)HiltonUtility.ckNull(this.validationLink).trim();
	}

	public void setValidationLink(String validationLink) {
		if (!HiltonUtility.isEmpty(validationLink) && validationLink.length() > 255) {
			validationLink = validationLink.substring(0, 255);
		}
		this.validationLink = validationLink;
	}

	public String getFieldName() {
		return (java.lang.String)HiltonUtility.ckNull(this.fieldName).trim();
	}

	public void setFieldName(String fieldName) {
		if (!HiltonUtility.isEmpty(fieldName) && fieldName.length() > 60) {
			fieldName = fieldName.substring(0, 60);
		}
		this.fieldName = fieldName;
	}

	public String getEnabled() {
		return (java.lang.String)HiltonUtility.ckNull(this.enabled).trim();
	}

	public void setEnabled(String enabled) {
        if (!HiltonUtility.isEmpty(enabled) && enabled.length() > 1) {
        	enabled = enabled.substring(0, 1);
        }
		this.enabled = enabled;
	}

	public String getModuleAccess() {
		return (java.lang.String)HiltonUtility.ckNull(this.moduleAccess).trim();
	}

	public void setModuleAccess(String moduleAccess) {
        if (!HiltonUtility.isEmpty(moduleAccess) && moduleAccess.length() > 30) {
        	moduleAccess = moduleAccess.substring(0, 30);
        }
		this.moduleAccess = moduleAccess;
	}

	public String getStatus() {
		return (java.lang.String)HiltonUtility.ckNull(this.status).trim();
	}

	public void setStatus(String status) {
        if (!HiltonUtility.isEmpty(status) && status.length() > 4) {
        	status = status.substring(0, 4);
        }
		if (HiltonUtility.isEmpty(status)) {
			status = "03";
		}
		this.status = status;
	}

	public String getOwner() {
		return (java.lang.String)HiltonUtility.ckNull(this.owner).trim();
	}

	public void setOwner(String owner) {
        if (!HiltonUtility.isEmpty(owner) && owner.length() > 15) {
        	owner = owner.substring(0, 15);
        }
		this.owner = owner;
	}

	public String getLastChangeBy() {
		return (java.lang.String)HiltonUtility.ckNull(this.lastChangeBy).trim();
	}

	public void setLastChangeBy(String lastChangeBy) {
		if (!HiltonUtility.isEmpty(lastChangeBy) && lastChangeBy.length() > 15) {
			lastChangeBy = lastChangeBy.substring(0, 15);
		}
		this.lastChangeBy = lastChangeBy;
	}

	public java.util.Date getLastChangeDate() {
		return lastChangeDate;
	}

	public void setLastChangeDate(java.util.Date lastChangeDate) {
		this.lastChangeDate = lastChangeDate;
	}
}
