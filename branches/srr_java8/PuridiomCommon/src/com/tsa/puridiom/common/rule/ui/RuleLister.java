/*
 * Created on Jun 1, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.tsa.puridiom.common.rule.ui;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.jdom.Element;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;
import com.tsagate.foundation.utility.XmlFile;
import com.tsagate.properties.DictionaryManager;

/**
 * @author renzo
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class RuleLister extends Task
{
    private String ruleFile = "";

    public Object executeTask(Object object) throws Exception
    {
        Map incomingRequest = (Map)object;
        Object ret = null;
        try
        {
            String organizationId = (String)incomingRequest.get("as_organizationId");
            String formType = (String)incomingRequest.get("formType");
            ret = this.listRules(formType, organizationId);
            incomingRequest.put("ruleFileName", this.ruleFile);
            //System.out.println("ruleFileName="+ this.ruleFile);
            this.setStatus(Status.SUCCEEDED);
        }
        catch (Exception e)
        {
            this.setStatus(Status.FAILED);
            throw new TsaException("An error ocurred obtaing rules List![" + e.getMessage() +"]", e);
        }
        return ret;
    }

    public List listRules(String type, String organizationId)
    {
        String rulesFile = "-validation-rules.xml";
        String path = DictionaryManager.getInstance("host", organizationId).getProperty("validation-rule-xml-path");
        //String path = "C:\\HiltonProjects\\workspace\\HiltonWeb\\xml\\validation-rules\\";

        if(type.equalsIgnoreCase("REQ"))
        {
            rulesFile = path + "req" + rulesFile.toLowerCase();
        }
        else if(type.equalsIgnoreCase("PO"))
        {
            rulesFile = path + "po" + rulesFile.toLowerCase();
        }
        else if(type.equalsIgnoreCase("RFQ"))
        {
            rulesFile = path + "rfq" + rulesFile.toLowerCase();
        }
        else if(type.equalsIgnoreCase("INV"))
        {
            rulesFile = path + "invoice" + rulesFile.toLowerCase();
        }
        else if(type.equalsIgnoreCase("VEN"))
        {
            rulesFile = path + "vendor" + rulesFile.toLowerCase();
        }
        else if(type.equalsIgnoreCase("RECPKG"))
        {
            rulesFile = path + "rec-package" + rulesFile.toLowerCase();
        }
        else if(type.equalsIgnoreCase("RECRET"))
        {
            rulesFile = path + "rec-return" + rulesFile.toLowerCase();
        }
        else if(type.equalsIgnoreCase("REC"))
        {
            rulesFile = path + "rec" + rulesFile.toLowerCase();
        }
        String ruleFile = Utility.getOidFile(rulesFile, organizationId).getAbsolutePath();
        XmlFile xFile = new XmlFile(ruleFile);
        Element requistionEl = xFile.getRootChild("validations");
        Element saveEl = requistionEl.getChild("save");
        List rulesList = this.createList(saveEl);
        this.ruleFile = ruleFile;
        return rulesList;
    }

    private List createList(Element rulesElement)
    {
        List rulesList = rulesElement.getChildren();
        List rules = new ArrayList();
        for (Iterator iter = rulesList.iterator(); iter.hasNext();)
        {
            Element element = (Element) iter.next();
            RuleElement ruleTemp = new RuleElement(element);
            rules.add(ruleTemp);
        }
        return rules;
    }
    public static void main(String[] args)
    {
    }
}
