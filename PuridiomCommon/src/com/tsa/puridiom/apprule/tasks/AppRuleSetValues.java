package com.tsa.puridiom.apprule.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.utility.*;
import com.tsagate.foundation.processengine.*;
import java.math.BigDecimal;
import java.util.Map;

public class AppRuleSetValues extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			AppRulePK comp_id = new AppRulePK();
			String organizationId = (String)incomingRequest.get("organizationId");
			AppRule appRule = (AppRule) incomingRequest.get("appRule");
			if (appRule == null)
			{
				appRule = new AppRule();
			}

			if (incomingRequest.containsKey("AppRule_userId"))
			{
				String userId = (String) incomingRequest.get("AppRule_userId");
				comp_id.setUserId(userId);
			}
			if (incomingRequest.containsKey("AppRule_amount"))
			{
				String amountString = (String) incomingRequest.get("AppRule_amount");
				if (Utility.isEmpty(amountString))
				{
					amountString = "0";
				}
				BigDecimal amount = new BigDecimal ( amountString );
				appRule.setAmount(amount);
			}
			if (incomingRequest.containsKey("AppRule_udf1Code"))
			{
				String udf1Code = (String) incomingRequest.get("AppRule_udf1Code");
				if (Utility.isEmpty(udf1Code))
				{
					udf1Code = " ";
				}
				comp_id.setUdf1Code(udf1Code);
			}
			if (incomingRequest.containsKey("AppRule_udf2Code"))
			{
				String udf2Code = (String) incomingRequest.get("AppRule_udf2Code");
				if (Utility.isEmpty(udf2Code))
				{
					udf2Code = " ";
				}
				comp_id.setUdf2Code(udf2Code);
			}
			if (incomingRequest.containsKey("AppRule_udf3Code"))
			{
				String udf3Code = (String) incomingRequest.get("AppRule_udf3Code");
				if (Utility.isEmpty(udf3Code))
				{
					udf3Code = " ";
				}
				comp_id.setUdf3Code(udf3Code);
			}
			if (incomingRequest.containsKey("AppRule_udf4Code"))
			{
				String udf4Code = (String) incomingRequest.get("AppRule_udf4Code");
				if (Utility.isEmpty(udf4Code))
				{
					udf4Code = " ";
				}
				comp_id.setUdf4Code(udf4Code);
			}
			if (incomingRequest.containsKey("AppRule_udf5Code"))
			{
				String udf5Code = (String) incomingRequest.get("AppRule_udf5Code");
				if (Utility.isEmpty(udf5Code))
				{
					udf5Code = " ";
				}
				comp_id.setUdf5Code(udf5Code);
			}
			if (incomingRequest.containsKey("AppRule_udf6Code"))
			{
				String udf6Code = (String) incomingRequest.get("AppRule_udf6Code");
				if (Utility.isEmpty(udf6Code))
				{
					udf6Code = " ";
				}
				comp_id.setUdf6Code(udf6Code);
			}
			if (incomingRequest.containsKey("AppRule_udf7Code"))
			{
				String udf7Code = (String) incomingRequest.get("AppRule_udf7Code");
				if (Utility.isEmpty(udf7Code))
				{
					udf7Code = (String) incomingRequest.get("AppRule_fyiApprover");
				}
				comp_id.setUdf7Code(udf7Code);
			}

			if (incomingRequest.containsKey("AppRule_udf8Code"))
			{
				String udf8Code = (String) incomingRequest.get("AppRule_udf8Code");
				appRule.setUdf8Code(udf8Code);
			}
			if (incomingRequest.containsKey("AppRule_udf9Code"))
			{
				String udf9Code = (String) incomingRequest.get("AppRule_udf9Code");
				appRule.setUdf9Code(udf9Code);
			}
			if (incomingRequest.containsKey("AppRule_udf10Code"))
			{
				String udf10Code = (String) incomingRequest.get("AppRule_udf10Code");
				appRule.setUdf10Code(udf10Code);
			}
			if (incomingRequest.containsKey("AppRule_approverLevel"))
			{
				String approverLevelString = (String) incomingRequest.get("AppRule_approverLevel");
				if (Utility.isEmpty(approverLevelString))
				{
					approverLevelString = "0";
				}
				BigDecimal approverLevel = new BigDecimal ( approverLevelString );
				appRule.setApproverLevel(approverLevel);
			}
			if (incomingRequest.containsKey("AppRule_excludeLess"))
			{
				String excludeLessString = (String) incomingRequest.get("AppRule_excludeLess");
				if (Utility.isEmpty(excludeLessString))
				{
					excludeLessString = "0";
				}
				BigDecimal excludeLess = new BigDecimal ( excludeLessString );
				appRule.setExcludeLess(excludeLess);
			}
			if (incomingRequest.containsKey("AppRule_fyiApprover"))
			{
				String fyiApprover = (String) incomingRequest.get("AppRule_fyiApprover");
				appRule.setFyiApprover(fyiApprover);
			}
			if (incomingRequest.containsKey("AppRule_requiredApprover"))
			{
				String requiredApprover = (String) incomingRequest.get("AppRule_requiredApprover");
				appRule.setRequiredApprover(requiredApprover);
			}
			if (incomingRequest.containsKey("AppRule_notes"))
			{
				String notes = (String) incomingRequest.get("AppRule_notes");
				appRule.setNotes(notes);
			}
			if (incomingRequest.containsKey("AppRule_advisor"))
			{
				String advisor = (String) incomingRequest.get("AppRule_advisor");
				appRule.setAdvisor(advisor);
			}
			appRule.setComp_id(comp_id);

			result = appRule;
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
