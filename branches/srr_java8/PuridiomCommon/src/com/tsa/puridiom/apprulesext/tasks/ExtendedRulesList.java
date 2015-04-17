/*
 * Created on Nov 22, 2004
 */
package com.tsa.puridiom.apprulesext.tasks;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.DOMBuilder;

import com.tsagate.foundation.processengine.RuleHelper;
import com.tsagate.foundation.rule.ClauseElement;
import com.tsagate.foundation.rule.Rule;
import com.tsagate.foundation.rule.RuleNew;
import com.tsagate.foundation.rule.XMLRuleLoader;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.Utility;
import com.tsagate.properties.DictionaryManager;

/**
 * @author renzo
 */
public class ExtendedRulesList
{
    private Map extRules;
    private String rulesPath;

    public ExtendedRule getRule(String ruleName)
    {
        return (ExtendedRule)this.extRules.get(ruleName);
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
        ExtendedRule rule = (ExtendedRule)this.extRules.get(name);
        return rule.getRoutTo();
    }
    public boolean getResult(String name)
    {
        ExtendedRule rule = (ExtendedRule)this.extRules.get(name);
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
        ExtendedRule originalExtRule = (ExtendedRule) this.extRules.get(originalRuleName);
        ExtendedRule newExtRule = new ExtendedRule();
        newExtRule.setAdvisor(originalExtRule.getAdvisor());
        newExtRule.setApproverAmount(originalExtRule.getApproverAmount());
        newExtRule.setFyiApprover(originalExtRule.getFyiApprover());
        newExtRule.setName(newRuleName);
        newExtRule.setNewRules(originalExtRule.getNewRules());
        newExtRule.setNotes(originalExtRule.getNotes());
        newExtRule.setRequiredApprover(originalExtRule.getRequiredApprover());
        newExtRule.setRequiredAuthority(originalExtRule.getRequiredAuthority());
        newExtRule.setResult(true);
        newExtRule.setRoutTo(originalExtRule.getRoutTo());
        newExtRule.setRule(originalExtRule.getRule());
        newExtRule.setRuleAction(originalExtRule.getRuleAction());
        newExtRule.setRuleOrder(originalExtRule.getRuleOrder());
        newExtRule.setRuleText(newRuleText);

        this.extRules.put(newRuleName, newExtRule);
    }
    /**
     * @param fileName
     * @param incomingRequest
     */
    public ExtendedRulesList(String fileName, Map incomingRequest)
    {
        String organizationId = (String)incomingRequest.get("organizationId");
        this.rulesPath = DictionaryManager.getInstance("host", organizationId).getProperty("app-rules-ext-xml-path", "");
        this.extRules = new HashMap();
        this.loadRules(fileName, incomingRequest);
        Log.debug(this, "Created ExtendedRulesList");
    }

    public void loadRules(String fileName, Map incomingRquest)
    {
        try
        {
            Log.debug(this, fileName);
            //File f = new File(this.rulesPath + fileName);
            File f = Utility.getOidFile(this.rulesPath + fileName, (String)incomingRquest.get("organizationId"));
            if (f.exists())
            {
                DOMBuilder docBuilder = new DOMBuilder();
                Document document = docBuilder.build(f);
                loadMe(document, incomingRquest);
            }
            else
            {
                throw new Exception("File not found: " + fileName);
            }
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
    private void loadMe(Document document, Map incomingRequest)
    {
        try
        {
            String	originalNewRules = (String) incomingRequest.get("newRules");
            List rulesList = document.getRootElement().getChildren("rule");
            boolean enabled = true;
            try
            {
                enabled = document.getRootElement().getAttribute("enabled").getBooleanValue();
            }
            catch (Exception dce)
            {
                enabled = true;
            }

            if(enabled)
            {
                for(int rulesIndex = 0; rulesIndex < rulesList.size(); rulesIndex++ )
                {
                    Element ruleEl = (Element)rulesList.get(rulesIndex);
                    ExtendedRule rule = new ExtendedRule();
                    String ruleName = ruleEl.getChildText("rule-filename");
                    rule.setName(ruleName);
                    rule.setRoutTo(ruleEl.getChildText("routto"));
                    rule.setRuleAction(ruleEl.getChildText("rule-action"));
                    rule.setRuleText(ruleEl.getChildText("rule-text"));
                    rule.setFyiApprover(ruleEl.getChildText("fyi-approver"));
                    rule.setRequiredApprover(ruleEl.getChildText("required-approver"));
                    rule.setAdvisor(ruleEl.getChildText("advisor"));
                    rule.setNotes(ruleEl.getChildText("rule-notes"));
                    rule.setNewRules(ruleEl.getChildText("new"));
                    try {
                        rule.setApproverAmount(new BigDecimal(ruleEl.getChildText("approver-amount")));
                    } catch (Exception e) {
                        rule.setApproverAmount(null);
                    }
                    try {
                        rule.setRuleOrder(new BigDecimal(ruleEl.getChildText("rule-order")));
                    } catch (Exception e) {
                        rule.setRuleOrder(null);
                    }

                    Element requiredAuthorityEl = ruleEl.getChild("required-authority");
                    XMLRuleLoader xmlRuleLoader = new XMLRuleLoader();
                    ClauseElement clauseElement = xmlRuleLoader.getClauseElement(requiredAuthorityEl, "");
                    RuleNew	tempRule = new RuleNew();
                    Object requiredAuthorityObj = tempRule.getValue(clauseElement, incomingRequest);
                    if (requiredAuthorityObj != null) {
                        try {
                            rule.setRequiredAuthority(new BigDecimal(String.valueOf(requiredAuthorityObj)));
                        } catch(Exception e) {
                            e.printStackTrace();
                        }
                    }

                    if (!Utility.isEmpty(rule.getNewRules())) {
                        incomingRequest.put("newRules", rule.getNewRules());
                    } else {
                        incomingRequest.put("newRules", originalNewRules);
                    }
                    RuleHelper helper = new RuleHelper(ruleName);
                    boolean result = helper.executeRule(incomingRequest);

                    rule.setResult(result);
                    if(result)
                    {
                        this.extRules.put(rule.getName() + String.valueOf(rulesIndex), rule);
                    }
                }
            }
            else
            {
                Log.debug(this, "xml extended rules are disabled!");
            }
            incomingRequest.put("newRules", originalNewRules);
        }
        catch (Exception e)
        {
            Log.error(this, e.toString());
        }
    }

    public class ExtendedRule
    {
        private String name = "";
        private String routTo = "";
        private String ruleAction = "";
        private String ruleText = "";
        private String fyiApprover = "";
        private String requiredApprover = "";
        private String notes = "";
        private String advisor = "";
        private String newRules = "";
        private BigDecimal requiredAuthority = new BigDecimal(0);
        private BigDecimal approverAmount = null;
        private BigDecimal ruleOrder = null;
        private Rule rule;
        private boolean result = false;
        /**
         * @return Returns the name.
         */
        public String getName()
        {
            return name;
        }
        /**
         * @param name The name to set.
         */
        public void setName(String name)
        {
            this.name = name;
        }
        /**
         * @return Returns the routTo.
         */
        public String getRoutTo()
        {
            return routTo;
        }
        /**
         * @param routTo The routTo to set.
         */
        public void setRoutTo(String routTo)
        {
            this.routTo = routTo;
        }
        /**
         * @return Returns the ruleAction.
         */
        public String getRuleAction()
        {
            return ruleAction;
        }
        /**
         * @param ruleAction The ruleAction to set.
         */
        public void setRuleAction(String ruleAction)
        {
            this.ruleAction = ruleAction;
        }
        /**
         * @return Returns the ruleText.
         */
        public String getRuleText()
        {
            return ruleText;
        }
        /**
         * @param ruleText The ruleText to set.
         */
        public void setRuleText(String ruleText)
        {
            this.ruleText = ruleText;
        }
        /**
         * @return Returns fyiApprover.
         */
        public String getFyiApprover()
        {
            return fyiApprover;
        }
        /**
         * @param fyiApprover The fyiApprover value to set.
         */
        public void setFyiApprover(String fyiApprover)
        {
            this.fyiApprover = fyiApprover;
        }
        /**
         * @return Returns requiredApprover.
         */
        public String getRequiredApprover()
        {
            return requiredApprover;
        }
        /**
         * @param requiredApprover The requiredApprover value to set.
         */
        public void setRequiredApprover(String requiredApprover)
        {
            this.requiredApprover = requiredApprover;
        }
        /**
         * @return Returns notes.
         */
        public String getNotes()
        {
            return notes;
        }
        /**
         * @param requiredAuthority The requiredAuthority to set.
         */
        public void setRequiredAuthority(BigDecimal requiredAuthority)
        {
            this.requiredAuthority = requiredAuthority;
        }
        /**
         * @return Returns requiredAuthority.
         */
        public BigDecimal getRequiredAuthority()
        {
            return requiredAuthority;
        }
        /**
         * @param approverAmount The approverAmount to set.
         */
        public void setApproverAmount(BigDecimal approverAmount)
        {
            this.approverAmount = approverAmount;
        }
        /**
         * @return Returns approverAmount.
         */
        public BigDecimal getApproverAmount()
        {
            return approverAmount;
        }
        /**
         * @param notes The notes to set.
         */
        public void setNotes(String notes)
        {
            this.notes = notes;
        }
        /**
         * @param ruleOrder The ruleOrder to set.
         */
        public void setRuleOrder(BigDecimal ruleOrder)
        {
            this.ruleOrder = ruleOrder;
        }
        /**
         * @return Returns ruleOrder.
         */
        public BigDecimal getRuleOrder()
        {
            return ruleOrder;
        }
        /**
         * @return Returns advisor.
         */
        public String getAdvisor()
        {
            return advisor;
        }
        /**
         * @param advisor The advisor value to set.
         */
        public void setAdvisor(String advisor)
        {
            this.advisor = advisor;
        }
        /**
         * @return Returns newRules.
         */
        public String getNewRules()
        {
            return newRules;
        }
        /**
         * @param newRules The newRules flag to set.
         */
        public void setNewRules(String newRules)
        {
            this.newRules = newRules;
        }
        /**
         * @return Returns the rule.
         */
        public Rule getRule()
        {
            return rule;
        }
        /**
         * @param rule The rule to set.
         */
        public void setRule(Rule rule)
        {
            this.rule = rule;
        }
        /**
         * @return Returns the result.
         */
        public boolean isResult()
        {
            return result;
        }
        /**
         * @param result The result to set.
         */
        public void setResult(boolean result)
        {
            this.result = result;
        }
    }
}
