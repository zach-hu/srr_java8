/*
 * Created on Nov 5, 2003
 */
package com.tsa.puridiom.validationrules.tasks;

import java.util.List;
import java.util.Map;

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

public class DatabaseValidationRulesTask extends Task
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
            List labelsList = (List) incomingRequest.get("labelsList");

            if (labelsList != null) {
            	for (int rulesIndex = 0; rulesIndex < labelsList.size(); rulesIndex++ ) {
            		Labels label = (Labels) labelsList.get(rulesIndex);

            		if (label.getVisible().equalsIgnoreCase("Y"))
            		{
	            		if (label.getRequired().equals("Y") || label.getValidation().equals("Y")) {
	                        String alias = label.getLabelCode();
	                        String ruleMsg = label.getValidationMessage();
	                        String severity = label.getValidationSeverity();
	                        String ruleMsgLink = label.getValidationLink();
	                        String fieldName = label.getFieldname();
	                        String module = label.getModule();
	                        String moduleType = HiltonUtility.ckNull(label.getModuleType());
	                        String ruleFileName = "";
	                        if(module.equals("rql"))
	                        {
	                        	module = "req";
	                        }
	                        if (this.getApplicationName().equals("supplierportal") && validationType.equals("vendorregistration")) {
	                        	module = "vr";
	                        }

	                        if (label.getValidation().equals("Y") && label.getRequired().equals("Y")) {
	                        	ruleFileName = module + "-" + alias + "-mv.xml";
	                        } else if (label.getValidation().equals("Y")) {
	                        	ruleFileName = module + "-" + alias + "-v.xml";
	                        } else if (label.getRequired().equals("Y")) {
	                        	ruleFileName = module + "-" + alias + "-m.xml";
	                        }
	                        ruleFileName = ruleFileName.toLowerCase();

	                        if (ruleFileName.startsWith("-")) {
	                        	ruleFileName = ruleFileName.substring(1);
	                        }
	                        incomingRequest.put("newRules", "false");

	                        RuleHelper helper = new RuleHelper(ruleFileName, this.applicationName);
	                        Object returned[] = helper.executeRuleWithMsg(incomingRequest);
	                        boolean result = ((Boolean)returned[0]).booleanValue();
	                        if(!HiltonUtility.isEmpty(moduleType) && !moduleType.equalsIgnoreCase(documentType))
	                        {
	                        	result = true;
	                        }

	                        ValidationRule vRule = new ValidationRule();
	                        vRule.setMessage(ruleMsg);
	                        vRule.setMessageLink(ruleMsgLink);
	                        vRule.setSeverity(severity);
	                        vRule.setResult(result);
	                        vRule.setOrder(rulesIndex);
	                        vRule.setFieldName(fieldName);

	                        if (!result) {
	                        	rules.addRule(alias, vRule);
	                        }
	            		}
            		}
                }
            }
            else {
                rules.setEnabled(false);
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