package com.tsa.puridiom.rules.tasks;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

import com.tsa.puridiom.entity.Rules;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.Utility;

public class RulesSetValues extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        Map incomingRequest = (Map)object;
        Object result = null;

        String organizationId = (String)incomingRequest.get("organizationId");
        String userDateFormat = (String) incomingRequest.get("userDateFormat");
        if (Utility.isEmpty(userDateFormat)) {
            userDateFormat = PropertiesManager.getInstance(organizationId).getProperty("MISC", "DATEFORMAT", "MM-dd-yyyy");
        }

        try
        {
            Rules rule = (Rules) incomingRequest.get("rules");
            if (rule == null)
            {
            	rule = new Rules();
            }

            if (incomingRequest.containsKey("Rules_icRule"))
            {
                String icRuleString = (String) incomingRequest.get("Rules_icRule");
                if (Utility.isEmpty(icRuleString))
                {
                	icRuleString = "0";
                }
                BigDecimal icRule = new BigDecimal(icRuleString);
                rule.setIcRule(icRule);
            }
            if (incomingRequest.containsKey("Rules_ruleName"))
            {
                String ruleName = (String) incomingRequest.get("Rules_ruleName");
                rule.setRuleName(ruleName);
            }
            if (incomingRequest.containsKey("Rules_ruleOrder"))
            {
                String ruleOrder = (String) incomingRequest.get("Rules_ruleOrder");
                rule.setRuleOrder(ruleOrder);
            }
            if (incomingRequest.containsKey("Rules_ruleExpression"))
            {
                String ruleExpression = (String) incomingRequest.get("Rules_ruleExpression");
                rule.setRuleExpression(ruleExpression);
            }
            if (incomingRequest.containsKey("Rules_validationMessage"))
            {
                String validationMessage = (String) incomingRequest.get("Rules_validationMessage");
                rule.setValidationMessage(validationMessage);
            }
            if (incomingRequest.containsKey("Rules_validationSeverity"))
            {
                String validationSeverity = (String) incomingRequest.get("Rules_validationSeverity");
                rule.setValidationSeverity(validationSeverity);
            }
            if (incomingRequest.containsKey("Rules_validationLink"))
            {
                String validationLink = (String) incomingRequest.get("Rules_validationLink");
                rule.setValidationLink(validationLink);
            }
            if (incomingRequest.containsKey("Rules_fieldName"))
            {
                String fieldName = (String) incomingRequest.get("Rules_fieldName");
                rule.setFieldName(fieldName);
            }
            if (incomingRequest.containsKey("Rules_enabled"))
            {
                String enabled = (String) incomingRequest.get("Rules_enabled");
                rule.setEnabled(enabled);
            }
            if (incomingRequest.containsKey("Rules_moduleAccess"))
            {
                String moduleAccess = (String) incomingRequest.get("Rules_moduleAccess");
                rule.setModuleAccess(moduleAccess);
            }
            if (incomingRequest.containsKey("Rules_status"))
            {
                String status = (String) incomingRequest.get("Rules_status");
                rule.setStatus(status);
            }
            if (incomingRequest.containsKey("Rules_owner"))
            {
                String owner = (String) incomingRequest.get("Rules_owner");
                rule.setOwner(owner);
            }
            if (incomingRequest.containsKey("Rules_lastChangeBy"))
            {
                String lastChangeBy = (String) incomingRequest.get("Rules_lastChangeBy");
                rule.setLastChangeBy(lastChangeBy);
            }
            if (incomingRequest.containsKey("Rules_lastChangeDate"))
            {
            	String lastChangeDateString = (String) incomingRequest.get("Rules_lastChangeDate");
            	Date lastChangeDate = Dates.getSqlDate(lastChangeDateString, userDateFormat);
				rule.setLastChangeDate(lastChangeDate);
            }

            result = rule;
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
