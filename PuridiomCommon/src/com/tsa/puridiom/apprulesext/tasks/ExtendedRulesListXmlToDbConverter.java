/*
 * Created on Dec 04, 2006
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

import com.tsa.puridiom.entity.AppRulesExt;
import com.tsa.puridiom.entity.AppRulesExtPK;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.rule.ClauseElement;
import com.tsagate.foundation.rule.Rule;
import com.tsagate.foundation.rule.RuleNew;
import com.tsagate.foundation.rule.XMLRuleLoader;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.Utility;
import com.tsagate.properties.DictionaryManager;

/**
 * @author renzo / Kelli
 */
public class ExtendedRulesListXmlToDbConverter
{
    private Map extRules;
    private List extRulesList;
    private String rulesPath;
    private String defaultRulOrder;
    private boolean enabled;

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
        return this.extRulesList;
    }
    public String getApprover(String name)
    {
        ExtendedRule rule = (ExtendedRule)this.extRules.get(name);
        return rule.getRoutTo();
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
    public String getAdvisor(String ruleName)
    {
        return this.getRule(ruleName).getAdvisor();
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
    public void setEnabled(boolean enabled)
    {
        this.enabled = enabled;
    }
    public boolean isEnabled()
    {
        return this.enabled;
    }
    /**
     * @param fileName
     * @param incomingRequest
     */
    public ExtendedRulesListXmlToDbConverter(String fileName, Map incomingRequest)
    {
        String organizationId = (String)incomingRequest.get("organizationId");
        this.rulesPath = DictionaryManager.getInstance("host", organizationId).getProperty("app-rules-ext-xml-path", "");
        this.defaultRulOrder = PropertiesManager.getInstance(organizationId).getProperty("MISC", "RULORDER", "10");
        this.extRules = new HashMap();
        this.extRulesList = new ArrayList();
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

            this.setEnabled(enabled);

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
                    try {
                        rule.setRuleOrder(new BigDecimal(this.defaultRulOrder));
                    } catch (Exception e1) {
                        rule.setRuleOrder(new BigDecimal(10));
                    }
                }

                Element requiredAuthorityEl = ruleEl.getChild("required-authority");
                XMLRuleLoader xmlRuleLoader = new XMLRuleLoader();
                ClauseElement clauseElement = xmlRuleLoader.getClauseElement(requiredAuthorityEl, "");

                rule.setReqdAuthObject(clauseElement.getObject());
                rule.setReqdAuthName(clauseElement.getName());
                rule.setReqdAuthSource(clauseElement.getSource());
                rule.setReqdAuthValue(String.valueOf(clauseElement.getValue()));

                 this.extRules.put(rule.getName() + String.valueOf(rulesIndex), rule);
                 this.extRulesList.add(rule);
            }
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
        private String advisor = "";
        private String newRules = "";
        private String notes = "";
        private BigDecimal requiredAuthority = new BigDecimal(0);
        private String reqdAuthObject = "";
        private String reqdAuthName = "";
        private String reqdAuthSource = "";
        private String reqdAuthValue = "";
        private BigDecimal ruleOrder = null;
        private BigDecimal approverAmount = null;
        private Rule rule;
        private boolean enabled = true;
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
         * @param notes The notes to set.
         */
        public void setNotes(String notes)
        {
            this.notes = notes;
        }
        /**
         * @return Returns notes.
         */
        public String getNotes()
        {
            return notes;
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
         * @return Returns the reqdAuthObject.
         */
        public String getReqdAuthObject()
        {
            return reqdAuthObject;
        }
        /**
         * @param reqdAuthObject The reqdAuthObject to set.
         */
        public void setReqdAuthObject(String reqdAuthObject)
        {
            this.reqdAuthObject = reqdAuthObject;
        }
        /**
         * @return Returns the reqdAuthName.
         */
        public String getReqdAuthName()
        {
            return reqdAuthName;
        }
        /**
         * @param reqdAuthName The reqdAuthName to set.
         */
        public void setReqdAuthName(String reqdAuthName)
        {
            this.reqdAuthName = reqdAuthName;
        }
        /**
         * @return Returns the reqdAuthSource.
         */
        public String getReqdAuthSource()
        {
            return reqdAuthSource;
        }
        /**
         * @param reqdAuthSource The reqdAuthSource to set.
         */
        public void setReqdAuthSource(String reqdAuthSource)
        {
            this.reqdAuthSource = reqdAuthSource;
        }
        /**
         * @return Returns the reqdAuthValue.
         */
        public String getReqdAuthValue()
        {
            return reqdAuthValue;
        }
        /**
         * @param reqdAuthValue The reqdAuthValue to set.
         */
        public void setReqdAuthValue(String reqdAuthValue)
        {
            this.reqdAuthValue = reqdAuthValue;
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
    }
}
