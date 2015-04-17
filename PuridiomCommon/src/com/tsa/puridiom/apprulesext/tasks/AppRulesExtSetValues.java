package com.tsa.puridiom.apprulesext.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.utility.*;
import com.tsagate.foundation.processengine.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

public class AppRulesExtSetValues extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			AppRulesExtPK comp_id = new AppRulesExtPK();
			AppRulesExt appRulesExt = (AppRulesExt) incomingRequest.get("appRulesExt");
			if (appRulesExt == null)
			{
				appRulesExt = new AppRulesExt();
			}
			else
            {
                comp_id.setRuleNumber(appRulesExt.getComp_id().getRuleNumber());
                comp_id.setRuleType(appRulesExt.getComp_id().getRuleType());
            }

			if (incomingRequest.containsKey("AppRulesExt_ruleNumber"))
			{
				String ruleNumberString = (String) incomingRequest.get("AppRulesExt_ruleNumber");
				if (Utility.isEmpty(ruleNumberString))
				{
					ruleNumberString = "0";
				}
				BigDecimal ruleNumber = new BigDecimal ( ruleNumberString );
				comp_id.setRuleNumber(ruleNumber);
			}
			if (incomingRequest.containsKey("AppRulesExt_ruleType"))
			{
				String ruleType = (String) incomingRequest.get("AppRulesExt_ruleType");
				comp_id.setRuleType(ruleType);
			}
			if (incomingRequest.containsKey("AppRulesExt_ruleOrder"))
			{
				String ruleOrderString = (String) incomingRequest.get("AppRulesExt_ruleOrder");
				if (Utility.isEmpty(ruleOrderString))
				{
					ruleOrderString = "0";
				}
				BigDecimal ruleOrder = new BigDecimal ( ruleOrderString );
				appRulesExt.setRuleOrder(ruleOrder);
			}
			if (incomingRequest.containsKey("AppRulesExt_ruleFilename"))
			{
				String ruleFilename = (String) incomingRequest.get("AppRulesExt_ruleFilename");
				appRulesExt.setRuleFilename(ruleFilename);
			}
			if (incomingRequest.containsKey("AppRulesExt_routto"))
			{
				String routto = (String) incomingRequest.get("AppRulesExt_routto");
				appRulesExt.setRoutto(routto);
			}
			if (incomingRequest.containsKey("AppRulesExt_ruleAction"))
			{
				String ruleAction = (String) incomingRequest.get("AppRulesExt_ruleAction");
				appRulesExt.setRuleAction(ruleAction);
			}
			if (incomingRequest.containsKey("AppRulesExt_ruleText"))
			{
				String ruleText = (String) incomingRequest.get("AppRulesExt_ruleText");
				appRulesExt.setRuleText(ruleText);
			}
			if (incomingRequest.containsKey("AppRulesExt_notes"))
			{
				String notes = (String) incomingRequest.get("AppRulesExt_notes");
				appRulesExt.setNotes(notes);
			}
			if (incomingRequest.containsKey("AppRulesExt_approverAmount"))
			{
				String approverAmountString = (String) incomingRequest.get("AppRulesExt_approverAmount");
				if (Utility.isEmpty(approverAmountString))
				{
					approverAmountString = "0";
				}
				BigDecimal approverAmount = new BigDecimal ( approverAmountString );
				appRulesExt.setApproverAmount(approverAmount);
			}
			if (incomingRequest.containsKey("AppRulesExt_requiredAuthority"))
			{
				String requiredAuthorityString = (String) incomingRequest.get("AppRulesExt_requiredAuthority");
				if (Utility.isEmpty(requiredAuthorityString))
				{
					requiredAuthorityString = "0";
				}
				BigDecimal requiredAuthority = new BigDecimal ( requiredAuthorityString );
				appRulesExt.setRequiredAuthority(requiredAuthority);
			}
			if (incomingRequest.containsKey("AppRulesExt_reqdAuthObject"))
			{
				String reqdAuthObject = (String) incomingRequest.get("AppRulesExt_reqdAuthObject");
				appRulesExt.setReqdAuthObject(reqdAuthObject);
			}
			if (incomingRequest.containsKey("AppRulesExt_reqdAuthName"))
			{
				String reqdAuthName = (String) incomingRequest.get("AppRulesExt_reqdAuthName");
				appRulesExt.setReqdAuthName(reqdAuthName);
			}
			if (incomingRequest.containsKey("AppRulesExt_reqdAuthSource"))
			{
				String reqdAuthSource = (String) incomingRequest.get("AppRulesExt_reqdAuthSource");
				appRulesExt.setReqdAuthSource(reqdAuthSource);
			}
			if (incomingRequest.containsKey("AppRulesExt_reqdAuthValue"))
			{
				String reqdAuthValue = (String) incomingRequest.get("AppRulesExt_reqdAuthValue");
				appRulesExt.setReqdAuthValue(reqdAuthValue);
			}
			if (incomingRequest.containsKey("AppRulesExt_fyiApprover"))
			{
				String fyiApprover = (String) incomingRequest.get("AppRulesExt_fyiApprover");
				appRulesExt.setFyiApprover(fyiApprover);
			}
			if (incomingRequest.containsKey("AppRulesExt_requiredApprover"))
			{
				String requiredApprover = (String) incomingRequest.get("AppRulesExt_requiredApprover");
				appRulesExt.setRequiredApprover(requiredApprover);
			}
			if (incomingRequest.containsKey("AppRulesExt_advisor"))
			{
				String advisor = (String) incomingRequest.get("AppRulesExt_advisor");
				appRulesExt.setAdvisor(advisor);
			}
			if (incomingRequest.containsKey("AppRulesExt_superRule"))
			{
				String superRule = (String) incomingRequest.get("AppRulesExt_superRule");
				appRulesExt.setSuperRule(superRule);
			}
			if (incomingRequest.containsKey("AppRulesExt_enabled"))
			{
				String enabled = (String) incomingRequest.get("AppRulesExt_enabled");
				appRulesExt.setEnabled(enabled);
			}
			appRulesExt.setComp_id(comp_id);

			result = appRulesExt;
			this.status = Status.SUCCEEDED;
		}
		catch (Exception e)
		{
			this.status = Status.FAILED;
			throw e;
		}
		return result;
	}
}
