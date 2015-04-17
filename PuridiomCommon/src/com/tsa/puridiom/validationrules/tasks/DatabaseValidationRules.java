package com.tsa.puridiom.validationrules.tasks;

import com.tsa.puridiom.entity.Rules;
import com.tsagate.foundation.processengine.RuleHelper;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.rule.ValidationRule;
import com.tsagate.foundation.rule.ValidationRules;
import com.tsagate.foundation.utility.Log;

import java.util.List;
import java.util.Map;

public class DatabaseValidationRules extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Object ret = null;
		Map incomingRequest = (Map) object;

		try
		{
			if (object instanceof Map)
			{
				incomingRequest.put("isValidationRule", "true");
				ValidationRules rules = this.loadRule(incomingRequest);
				if (rules == null) {
					throw new Exception("The validation rules could not be loaded from the database.");
				}
				rules.validateMe();
				ret = rules;
			}
			else
			{
				this.setStatus(Status.FAILED);
				throw new Exception("Incorrect Argument - requires Map.");
			}

			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			incomingRequest.remove("isValidationRule");
			throw e;
		}
		incomingRequest.remove("isValidationRule");
		return ret;
	}

	public ValidationRules loadRule(Map incomingRequest)
	{
		ValidationRules rules = (ValidationRules)incomingRequest.get("rules");
		if (rules == null) {
			rules = new ValidationRules();
		}

		try
		{
			String validationType = (String)incomingRequest.get("validationType");

			if (validationType == null) {
				validationType = "save";
			}

			List rulesList = (List) incomingRequest.get("rulesList");

			if (rulesList != null)
			{
				for (int i = 0; i < rulesList.size(); i++)
				{
					Rules rule = (Rules) rulesList.get(i);

					String alias = "ALIAS-" + rule.getRuleName();
					String message = rule.getValidationMessage();
					String messageLink = rule.getValidationLink();
					String severity = rule.getValidationSeverity();
					int order = Integer.parseInt(rule.getRuleOrder());
					String fieldName = rule.getFieldName();
					String ruleExpression = rule.getRuleExpression();

					incomingRequest.put("newRules", "false");

					RuleHelper helper = new RuleHelper(alias);
                    Object returned[] = helper.executeRulesWithMsg(alias, ruleExpression, incomingRequest);
                    boolean result = ((Boolean)returned[0]).booleanValue();

					ValidationRule vRule = new ValidationRule();
					vRule.setMessage(message);
					vRule.setMessageLink(messageLink);
					vRule.setSeverity(severity);
					vRule.setResult(result);
					vRule.setOrder(order);
					vRule.setFieldName(fieldName);

					rules.addRule(alias, vRule);
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			Log.error(this, e.toString());
		}
		return rules;
	}
}
