/*
 * Created on August 21, 2008
 */
package com.tsa.puridiom.apprulesext.tasks;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jdom.Document;
import org.jdom.input.DOMBuilder;

import com.tsa.puridiom.entity.AppRulesExt;
import com.tsagate.foundation.processengine.RuleHelper;
import com.tsagate.foundation.rule.ClauseElement;
import com.tsagate.foundation.rule.RuleNew;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.Utility;
import com.tsagate.properties.DictionaryManager;

/**
 * @author Kelli
 */
public class AppRulesExtList
{
    private Map extRules;

    public AppRulesExt getRule(String ruleName)
    {
        return (AppRulesExt) this.extRules.get(ruleName);
    }
    public String getRuleAction(String ruleName)
    {
        return this.getRule(ruleName).getRuleAction();
    }
    public List getRules()
    {
        return new ArrayList(this.extRules.keySet());
    }
    public String getApprover(String name)
    {
        AppRulesExt rule = (AppRulesExt)this.extRules.get(name);
        return rule.getRoutto();
    }
    public boolean getResult(String name)
    {
        AppRulesExt rule = (AppRulesExt)this.extRules.get(name);
        return rule.isResult();
    }
    public String getRuleText(String ruleName)
    {
        return this.getRule(ruleName).getRuleText();
    }
    public String getFyiApprover(String ruleName)
    {
        return this.getRule(ruleName).getFyiApprover();
    }
    public String getRequiredApprover(String ruleName)
    {
        return this.getRule(ruleName).getRequiredApprover();
    }
    public String getNotes(String ruleName)
    {
        return this.getRule(ruleName).getNotes();
    }
    public BigDecimal getRequiredAuthority(String ruleName)
    {
        return this.getRule(ruleName).getRequiredAuthority();
    }
    public BigDecimal getApproverAmount(String ruleName)
    {
        return this.getRule(ruleName).getApproverAmount();
    }
    public BigDecimal getRuleOrder(String ruleName)
    {
        return this.getRule(ruleName).getRuleOrder();
    }
    public String getAdvisor(String ruleName)
    {
        return this.getRule(ruleName).getAdvisor();
    }
    public void copyRule(String originalRuleName, String newRuleName, String newRuleText)
    {
        AppRulesExt originalExtRule = (AppRulesExt) this.extRules.get(originalRuleName);
        AppRulesExt newExtRule = new AppRulesExt();
        newExtRule.setAdvisor(originalExtRule.getAdvisor());
        newExtRule.setApproverAmount(originalExtRule.getApproverAmount());
        newExtRule.setFyiApprover(originalExtRule.getFyiApprover());
        newExtRule.setRuleFilename(newRuleName);
        newExtRule.setSuperRule(originalExtRule.getSuperRule());
        newExtRule.setNotes(originalExtRule.getNotes());
        newExtRule.setRequiredApprover(originalExtRule.getRequiredApprover());
        newExtRule.setRequiredAuthority(originalExtRule.getRequiredAuthority());
        newExtRule.setResult(true);
        newExtRule.setRoutto(originalExtRule.getRoutto());
        //newExtRule.setRule(originalExtRule.getRule());
        newExtRule.setRuleAction(originalExtRule.getRuleAction());
        newExtRule.setRuleOrder(originalExtRule.getRuleOrder());
        newExtRule.setRuleText(newRuleText);

        this.extRules.put(newRuleName, newExtRule);
    }
    /**
     * @param fileName
     * @param incomingRequest
     */
    public AppRulesExtList(Map incomingRequest)
    {
        this.extRules = new HashMap();
        this.loadRules(incomingRequest);
        Log.debug(this, "Created ExtendedRulesList");
    }

    public void loadRules( Map incomingRquest)
    {
        try
        {
                loadMe(incomingRquest);
        }
        catch(Exception exception)
        {
            Log.error(this, exception.toString());
        }
    }

    /**
     * @param document
     * @param docType
     * @param validationType
     */
    private void loadMe(Map incomingRequest)
    {
        try
        {
            String	originalNewRules = (String) incomingRequest.get("newRules");
            List rulesList = (List) incomingRequest.get("appRulesExtList");

            if (rulesList != null) {
                for(int i = 0; i < rulesList.size(); i++ )
                {
                    AppRulesExt rule = (AppRulesExt) rulesList.get(i);

                    ClauseElement clauseElement = new ClauseElement();
                    clauseElement.setObject(rule.getReqdAuthObject());
                    clauseElement.setName(rule.getReqdAuthName());
                    clauseElement.setSource(rule.getReqdAuthSource());
                    clauseElement.setValue(rule.getReqdAuthValue());

                    RuleNew tempRule = new RuleNew();
                    Object requiredAuthorityObj = tempRule.getValue(clauseElement, incomingRequest);
                    
                    if (requiredAuthorityObj != null) {
                        try {
                            rule.setRequiredAuthority(new BigDecimal(String.valueOf(requiredAuthorityObj)));
                        } catch(Exception e) {
                            e.printStackTrace();
                        }
                    }
                    
                    if (!Utility.isEmpty(rule.getSuperRule())) {
                        incomingRequest.put("newRules", rule.getSuperRule());
                    } else {
                        incomingRequest.put("newRules", originalNewRules);
                    }
                    RuleHelper helper = new RuleHelper(rule.getRuleFilename());
                    boolean result = helper.executeRule(incomingRequest);

                    rule.setResult(result);
                    if(result)
                    {
                        this.extRules.put(rule.getRuleFilename() + String.valueOf(i), rule);
                    }
                }
            }
            incomingRequest.put("newRules", originalNewRules);
        }
        catch (Exception e)
        {
            Log.error(this, e.toString());
        }
    }
}
