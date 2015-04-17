/*
 * Created on Nov 26, 2003
 */
package com.tsagate.foundation.rule;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.Utility;
import com.tsagate.properties.DictionaryManager;

/**
 * @author renzo / Kelli
 *
 * This is the updated version of the RuleManager
 */
public class RuleManager
{
    private Map organizations = null;
    private static RuleManager ruleManager;

    public static RuleManager getInstance()
    {
        return RuleManager.getInstance(false);
    }

    public static RuleManager getInstance(boolean newRules)
    {
        if(newRules)
        {
            RuleManager.ruleManager = new RuleManager();
        }
        else
        {
            if(RuleManager.ruleManager == null)
            {
                RuleManager.ruleManager = new RuleManager();
            }
        }

        return RuleManager.ruleManager;
    }

    private RuleManager()
    {
        this.organizations = new HashMap();
    }

    public Rule getRule(String name, Map mapValues)
    {
        return this.getRule(name, mapValues, null);
    }
    public Rule getRule(String name, Map mapValues, String applicationName)
    {
        String	organizationId = (String) mapValues.get("organizationId");
        Map rules = null;
        
        if (this.organizations.containsKey(organizationId)) {
            rules = (Map) this.organizations.get(organizationId);
        } else {
            rules = new HashMap();
        }
        
        if(!rules.containsKey(name))
        {
            String isValidationRule = (String)mapValues.get("isValidationRule");
            String newRules = (String)mapValues.get("newRules");
            if(newRules == null)
            {
                newRules = "false";
            }
            if(isValidationRule == null)
            {
                isValidationRule = "false";
            }
            if((isValidationRule.equals("false") && newRules.equals("false")) || (isValidationRule.equals("true") && newRules.equals("false")))
            {
                Rule rule = new Rule(name, mapValues, applicationName);
                rules.put(name, rule);
                RuleManager.ruleManager.organizations.put(organizationId, rules);
            }
            else
            {
                Rule rule = new RuleNew(name, mapValues, applicationName);
                rules.put(name, rule);
                RuleManager.ruleManager.organizations.put(organizationId, rules);
            }
        }
        return (Rule)rules.get(name);
    }
    public boolean evaluate(String name, Map mapValue)
    {
        Rule rule = RuleManager.ruleManager.getRule(name, mapValue);
        return rule.evaluate(mapValue);
    }
    public boolean evaluate(String name, Map mapValue, String applicationName)
    {
        Rule rule = RuleManager.ruleManager.getRule(name, mapValue, applicationName);
        return rule.evaluate(mapValue);
    }
    public boolean isValidationRuleAvailable(String ruleFileName)
    {
    	return isValidationRuleAvailable(ruleFileName, "");
    }
    public boolean isValidationRuleAvailable(String ruleFileName, String organizationId)
    {
    	boolean exists = false;
    	try {
            String filepath = DictionaryManager.getInstance("host", organizationId).getProperty("validation-rule-xml-path", "");
            ruleFileName = filepath + ruleFileName;

            File f = Utility.getOidFile(ruleFileName, organizationId);
            if (f != null) {
            	exists = f.exists();
            }
    	} catch (Exception e) {
    		Log.error(this, e.getMessage());
    	}
        return exists;
    }
}
