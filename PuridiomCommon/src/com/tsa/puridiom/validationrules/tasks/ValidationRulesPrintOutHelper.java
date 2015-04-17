/*
 * Created on Nov 29, 2006
 */
package com.tsa.puridiom.validationrules.tasks;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.jdom.Attribute;
import org.jdom.DataConversionException;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.DOMBuilder;

import com.tsagate.foundation.processengine.RuleHelper;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.rule.ValidationRule;
import com.tsagate.foundation.rule.ValidationRules;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.Utility;
import com.tsagate.properties.DictionaryManager;

public class ValidationRulesPrintOutHelper extends Task
{

    /* (non-Javadoc)
     * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
     */
    public Object executeTask(Object object) throws Exception
    {
        Object ret = null;
        Map incomingRequest = (Map) object;
        try
        {
            if (object instanceof Map)
            {

                String organizationId = (String) incomingRequest.get("organizationId");
                String filename = (String) incomingRequest.get("filename");
                incomingRequest.put("isValidationRule", "true");
                if (filename.indexOf(".xml") < 0)
                {
                    filename = filename + ".xml";
                }
                ret = this.validateIt(filename, organizationId, incomingRequest);

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

    protected Object validateIt(String filename, String organizationId, Map incomingRequest) throws Exception
    {
        String pathProperty =  "validation-rule-xml-path";
        
		if (!Utility.isEmpty(this.getApplicationName())) 
		{
		    pathProperty = this.getApplicationName() + "-" + pathProperty;
		}
        String validationPath = DictionaryManager.getInstance("host", organizationId).getProperty(pathProperty, "");
        ValidationRules rules = this.loadRule(validationPath + filename, incomingRequest);
        if (rules == null) {
            throw new Exception("The file for the validation rules could not be found. <br>[" + validationPath + filename + "]");
        }

        return rules;
    }

    public ValidationRules loadRule(String fileName, Map incomingRequest)
    {
        ValidationRules rules = null;
        try
        {
            Log.debug(this, fileName);
            File f = Utility.getOidFile(fileName, (String)incomingRequest.get("organizationId"));
            if (f.exists())
            {
                DOMBuilder docBuilder = new DOMBuilder();
                Document document = docBuilder.build(f);
                rules = loadMe(document, incomingRequest);
            }
            else
            {
                throw new Exception("File not found: " + fileName);
            }
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
            //system.out.println(exception.toString());
        }
        return rules;
    }

    /**
     * @param document
     * @param incomingRequest
     * @param validationType
     */
    private ValidationRules loadMe(Document document, Map incomingRequest)
    {
        ValidationRules rules = new ValidationRules();
        try
        {
            String validationType = (String)incomingRequest.get("validationType");
            if(validationType == null)
            {
                validationType = "save";
            }
            boolean enabled = true;
            List rulesList = document.getRootElement().getChild("validations").getChild(validationType).getChildren("rule");
            try
            {
                Attribute attEnabled = document.getRootElement().getChild("validations").getChild(validationType).getAttribute("enabled");
                if(attEnabled != null)
                {
                    enabled= attEnabled.getBooleanValue();
                }
            }
            catch (DataConversionException dataException)
            {
                enabled = true;
            }

            rules.setEnabled(enabled);
            if(enabled)
            {
                for(int rulesIndex = 0; rulesIndex < rulesList.size(); rulesIndex++ )
                {
                    Element ruleEl = (Element)rulesList.get(rulesIndex);

                    String ruleName = ruleEl.getChildText("filename");
                    String alias = ruleEl.getChildText("alias");
                    String ruleMsg = ruleEl.getChildText("msg");
                    String severity = ruleEl.getChildText("severity");
                    String sOrder = ruleEl.getChildText("order");
                    String elEnabled = ruleEl.getChildText("enabled");
                    String newRules = ruleEl.getChildText("new");
                    if(newRules == null)
                    {
                        newRules = "false";
                    }
                    incomingRequest.put("newRules", newRules);
                    boolean ruleEnabled = true;
                    if(elEnabled == null)
                    {
                        ruleEnabled = true;
                    }
                    else
                    {
                        if(elEnabled.equalsIgnoreCase("N"))
                        {
                            ruleEnabled = false;
                        }
                    }
                    int order = 0;
                    if(sOrder != null)
                    {
                        order = Integer.parseInt(sOrder);
                    }

                    RuleHelper helper = new RuleHelper(ruleEl.getChild("filename"), this.applicationName);
                    ValidationRule vRule = new ValidationRule();

                    vRule.setMessage(ruleMsg);
                    vRule.setSeverity(severity);
                    vRule.setResult(ruleEnabled);
                    vRule.setOrder(order);
                    vRule.setAlias(ruleName + "; new = " + newRules + "; alias = " + alias);

                    rules.addRule(vRule.getAlias(), vRule);
                }
            }
        }
        catch (Exception e)
        {
        	e.printStackTrace();
        }
        return rules;
    }
}