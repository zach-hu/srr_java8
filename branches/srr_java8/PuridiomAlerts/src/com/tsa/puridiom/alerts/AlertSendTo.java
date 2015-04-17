package com.tsa.puridiom.alerts;

import org.jdom.Element;
import com.tsagate.foundation.utility.Utility;

/**
 * @author renzo
 *
 */
public class AlertSendTo
{
	public static String REQUISITIONER = "requisitioner";
	public static String OWNER = "owner";
	public static String BUYER = "buyer";
	public static String MANUAL = "manual";
	public static String RULE="RULE";

	private String type = "";
	private boolean enabled = false;
	//used only with type = manual;
	private String emailId = "";

	private String ruleName = "";
	private String ruleUserId = "";
	private String ruleUserType = "";

	public String getEmailId()
	{
		return emailId;
	}
	public void setEmailId(String emailId)
	{
		this.emailId = emailId;
	}
	public boolean isEnabled()
	{
		return enabled;
	}
	public void setEnabled(String enabled)
	{
		if(!Utility.isEmpty(enabled))
		{
			this.enabled = enabled.equalsIgnoreCase("Y");
		}
	}

	public String getType()
	{
		return type;
	}
	public void setType(String type)
	{
		this.type = type;
	}

	public void setType(String type, String text, Element userElement)
	{
		if(!Utility.isEmpty(type))
		{
			if(type.equalsIgnoreCase(AlertSendTo.MANUAL))
			{
				this.setType(AlertSendTo.MANUAL);
				this.setEmailId(text);
				this.setEnabled("Y");
			}
			else if(type.equalsIgnoreCase(AlertSendTo.RULE))
			{
				this.setRuleName(userElement.getChildTextTrim("rule"));
				this.setRuleUserType(userElement.getChild("userid").getAttributeValue("type"));
				this.setRuleUserId(userElement.getChildText("userid"));
				this.setEnabled("Y");
			}
			else
			{
				this.setType(type);
				this.setEnabled(text);
			}
		}
		this.type = type;
	}

	public String toString() {
			StringBuffer buffer = new StringBuffer();
			buffer.append("[AlertSendTo:");
			buffer.append(" type: ");
			buffer.append(type);
			buffer.append(" enabled: ");
			buffer.append(enabled);
			buffer.append(" emailId: ");
			buffer.append(emailId);
			buffer.append("]");
			return buffer.toString();
		}
	public String getRuleName()
	{
		return ruleName;
	}
	public void setRuleName(String ruleName)
	{
		this.ruleName = ruleName;
	}
	public String getRuleUserId()
	{
		return ruleUserId;
	}
	public void setRuleUserId(String ruleUserId)
	{
		this.ruleUserId = ruleUserId;
	}
	public String getRuleUserType()
	{
		return ruleUserType;
	}
	public void setRuleUserType(String ruleUserType)
	{
		this.ruleUserType = ruleUserType;
	}

}