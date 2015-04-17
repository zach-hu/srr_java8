/*
 * Created on Oct 29, 2004
 *
 */
package com.tsagate.foundation.rule;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


/**
 * @author renzo
 * severity: 0
 * msg: 1
 * status: 2
 */
public class ValidationRules
{
    private Map rules = new HashMap();
    private int result = -5;
    public int PASSED = 1;
    public int PASSEDWITHERRORS = 0;
    public int FAILED = -1;
    public boolean enabled = true;

    public ValidationRule getRule(String alias)
    {
        return (ValidationRule)this.rules.get(alias);
    }
    public void addRule(String alias)
    {
        this.rules.put(alias, new ValidationRule());
    }

    public void addRule(String alias, ValidationRule rule)
    {
        rule.setAlias(alias);
        this.rules.put(alias, rule);

    }

    public void setMsg(String alias, String msg)
    {
        ValidationRule rule = this.getRule(alias);
        rule.setMessage(msg);
        this.addRule(alias, rule);
    }

    public void setSeverity(String alias, String severity)
    {
        ValidationRule rule = this.getRule(alias);
        rule.setSeverity(severity);
        this.addRule(alias, rule);
    }

    public void setStatus(String alias, boolean status)
    {
        ValidationRule rule = this.getRule(alias);
        rule.setResult(status);
        this.addRule(alias, rule);
    }

    public String getMsg(String alias)
    {
        return this.getRule(alias).getMessage();
    }

    public String getSeverity(String alias)
    {
        return this.getRule(alias).getSeverity();
    }

    public boolean getStatus(String alias)
    {
        return this.getRule(alias).isResult();
    }

    public int validateMe()
    {
        int passed = 1;
        if(this.isEnabled())
        {
	        List rulesList = new ArrayList(this.rules.values());
	        for (Iterator iter = rulesList.iterator(); iter.hasNext();)
	        {
	            ValidationRule rule = (ValidationRule) iter.next();
	            String severity = rule.getSeverity();
	            boolean result = rule.isResult();
	            if(severity.equalsIgnoreCase("E") && !result)
	            {
	                passed = this.FAILED;
	                break;
	            }
	            else if (severity.equalsIgnoreCase("W") && !result)
	            {
	                passed = this.PASSEDWITHERRORS;
	            }
	            else
	            {
	                if(!(passed < 1))
	                {
	                    passed = this.PASSED;
	                }
	            }
	        }
        }
        else
        {
            passed = this.PASSED;
        }
		this.setResult(passed);
        return this.getResult();
    }
    public List getRules()
    {
        List rulesList = new ArrayList(this.rules.values());
        Collections.sort(rulesList, new ValidationRuleOrderComparator());

        return rulesList;
    }

    /**
     * @return Returns the result.
     */
    public int getResult()
    {
        return result;
    }
    /**
     * @param result The result to set.
     */
    private void setResult(int result)
    {
        this.result = result;
    }
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        return buffer.toString();
    }
    /**
     * @param alias
     * @param order
     */
    public void setOrder(String alias, int order)
    {
        ValidationRule rule = this.getRule(alias);
        rule.setOrder(order);
        this.addRule(alias, rule);
    }
    public int getOrder(String alias)
    {
        return this.getRule(alias).getOrder();
    }
    public boolean isEnabled()
    {
        return enabled;
    }
    public void setEnabled(boolean enabled)
    {
        this.enabled = enabled;
    }
}
