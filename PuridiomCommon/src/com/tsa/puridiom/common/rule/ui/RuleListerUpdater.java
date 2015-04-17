/*
 * Created on Jun 1, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.tsa.puridiom.common.rule.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jdom.Element;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.XmlFile;

/**
 * @author renzo
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class RuleListerUpdater extends Task
{


    public Object executeTask(Object object) throws Exception
    {
        Map incomingRequest = (Map)object;
        try
        {
            String afilename[] = (String[])incomingRequest.get("filename");
            String aalias[] = (String[])incomingRequest.get("alias");
            String amsg[] = (String[])incomingRequest.get("msg");
            String aseverity[] = (String[])incomingRequest.get("severity");
            String aorder[] = (String[])incomingRequest.get("order");
            String aenabled[] = (String[])incomingRequest.get("enabled");
            String fileName = (String)incomingRequest.get("ruleFileName");
            List rules = new ArrayList();

            for(int i = 0; i < afilename.length; i++)
            {
                RuleUpdateByRow ruleRow = new RuleUpdateByRow();
                Map parameters = new HashMap();
                parameters.put("filename", afilename[i]);
                parameters.put("alias", aalias[i]);
                parameters.put("msg", amsg[i]);
                parameters.put("severity", aseverity[i]);
                parameters.put("order", aorder[i]);
                parameters.put("enabled", aenabled[i]);
                rules.add(ruleRow.executeTask(parameters));
            }
            this.writeRules(fileName, rules);

            this.setStatus(Status.SUCCEEDED);

        }
        catch (Exception e)
        {
            this.setStatus(Status.FAILED);
            throw new TsaException(e.getMessage(), e);
        }
        return super.executeTask(object);
    }

    public void writeRules(String rulesFileName, List rules)
    {
        XmlFile xFile = new XmlFile(rulesFileName);
        Element requistionEl = xFile.getRootChild("validations");
        Element saveEl = requistionEl.getChild("save");
        saveEl.setChildren(rules);
        xFile.output();
    }
}
