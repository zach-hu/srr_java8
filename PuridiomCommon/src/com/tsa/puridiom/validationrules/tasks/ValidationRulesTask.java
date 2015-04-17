/*
 * Created on Nov 5, 2003
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

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.DisbHeader;
import com.tsa.puridiom.entity.InvoiceHeader;
import com.tsa.puridiom.entity.Labels;
import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.entity.ReceiptHeader;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsagate.foundation.processengine.RuleHelper;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.rule.ValidationRule;
import com.tsagate.foundation.rule.ValidationRules;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.Utility;
import com.tsagate.properties.DictionaryManager;

public class ValidationRulesTask extends Task
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
                String filename = this.getProcessFilename();
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
        rules.validateMe();
        return rules;
    }

    public ValidationRules loadRule(String fileName, Map incomingRequest)
    {
        ValidationRules rules = null ;
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
    	ValidationRules rules = (ValidationRules)incomingRequest.get("rules");
        if (rules == null) {
        	rules = new ValidationRules();
        }
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


            if(enabled)
            {
                for(int rulesIndex = 0; rulesIndex < rulesList.size(); rulesIndex++ )
                {
                    Element ruleEl = (Element)rulesList.get(rulesIndex);

                    String ruleName = ruleEl.getChildText("filename");
                    String alias = ruleEl.getChildText("alias");
                    String ruleMsg = ruleEl.getChildText("msg");
                    String fieldsToValidate = ruleEl.getChildText("fieldsToValidate");
                    String ruleMsgLink = ruleEl.getChildText("link");
                    String severity = ruleEl.getChildText("severity");
                    String sOrder = ruleEl.getChildText("order");
                    String elEnabled = ruleEl.getChildText("enabled");
                    String newRules = ruleEl.getChildText("new");
                    String process = ruleEl.getChildText("process");
                    String fieldName = ruleEl.getChildText("fieldname");

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
                    boolean ckValidField = checkValidField(incomingRequest, fieldsToValidate);
                    if(ruleEnabled && ckValidField)
                    {
                        int order = 0;
                        if(sOrder != null)
                        {
                            order = Integer.parseInt(sOrder);
                        }
                        if(fieldName == null)
                        {
                        	fieldName = "";
                        }
                        RuleHelper helper = new RuleHelper(ruleEl.getChild("filename"), this.applicationName);
                        //boolean result = helper.executeRule(incomingRequest);
                        Object returned[] = helper.executeRuleWithMsg(incomingRequest);
                        boolean result = ((Boolean)returned[0]).booleanValue();
                        ValidationRule vRule = new ValidationRule();
                        //vRule.setMessage(ruleMsg + "(" + returned[1].toString() + ")");
                        if (ruleMsg.indexOf("[@") >= 0) {
                        	String rFld = ruleMsg.substring(ruleMsg.indexOf("[@") + 2) ;
                        	if (rFld.indexOf("]") > 0 ) {
                        		rFld = rFld.substring(0, rFld.indexOf("]")) ;
                        		String rValue = (String) incomingRequest.get(rFld) ;
                        		if (rValue == null) rValue = "" ;
                        		ruleMsg = ruleMsg.replace("[@" + rFld + "]", rValue) ;
                        	}
                        }
                        vRule.setMessage(ruleMsg);
                        vRule.setMessageLink(ruleMsgLink);
                        vRule.setSeverity(severity);
                        vRule.setResult(result);
                        vRule.setOrder(order);
                        vRule.setProcess(process);
                        vRule.setFieldName(fieldName);

                        rules.addRule(alias, vRule);
                    }
                }
            }
            else
            {
                rules.setEnabled(enabled);
            }
        }
        catch (Exception e)
        {
        	e.printStackTrace();
            Log.error(this, e.toString());
        }
        return rules;
    }
    private boolean checkValidField(Map incomingRequest, String field){
    	boolean check = true; 
    	if(!HiltonUtility.isEmpty(field))
    	{
    		List labelsList = (List) incomingRequest.get("labelsList");
        	String formType = HiltonUtility.ckNull((String)incomingRequest.get("formType"));
    		String documentType = "";
    		if (formType.equalsIgnoreCase("REQ") || formType.equalsIgnoreCase("REQLN"))
    		{
    			RequisitionHeader requisitionHeader = (RequisitionHeader) incomingRequest.get("requisitionHeader");
    			documentType = requisitionHeader.getRequisitionType();
    		}
    		if (formType.equalsIgnoreCase("PO"))
    		{
    			PoHeader poHeader = (PoHeader) incomingRequest.get("poHeader");
    			documentType = poHeader.getPoType();
    		}
    		if (formType.equalsIgnoreCase("IVH") || formType.equalsIgnoreCase("IVC"))
    		{
    			InvoiceHeader invoiceHeader = (InvoiceHeader) incomingRequest.get("invoiceHeader");
    			documentType = invoiceHeader.getInvoiceType();
    		}
    		if (formType.equalsIgnoreCase("REC"))
    		{
    			ReceiptHeader receiptHeader = (ReceiptHeader) incomingRequest.get("receiptHeader");
    			documentType = receiptHeader.getReceiptType();
    		}
    		if (formType.equalsIgnoreCase("DSB"))
    		{
    			DisbHeader disbHeader = (DisbHeader) incomingRequest.get("disbHeader");
    			documentType = disbHeader.getDisbType();
    		}
    		Labels labeltarget = getLabel(labelsList,field,documentType);
    		if(labeltarget != null)
    		{
    			if(!labeltarget.getVisible().equalsIgnoreCase("Y") || !labeltarget.getRequired().equals("Y") || !labeltarget.getValidation().equals("Y")) {
          			check = false;
        		}
    		}
    		
    	}
    	
         return check;
    }
    
    private Labels getLabel(List labelsList, String labelCode, String moduleType){
    	Labels labelTarget = null;
    	if (labelsList != null) {
          	for (int rulesIndex = 0; rulesIndex < labelsList.size(); rulesIndex++ ) {
          		Labels label = (Labels) labelsList.get(rulesIndex);
          		if(label.getLabelCode().equals(labelCode)){
          			if(label.getModuleType().toUpperCase().equals(moduleType)){
          				labelTarget = label;
          				break;
          			} else if(HiltonUtility.isEmpty(label.getModuleType())){
          				labelTarget = label;
          			}
          		}	
          	}
        }
    	return labelTarget;
    }
}