/*
 * Created on Jun 1, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.tsa.puridiom.common.rule.ui;

import java.util.Map;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

/**
 * @author renzo
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class RuleUpdateByRow extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        Map incomingRequest = (Map)object;
        Object ret = null;
        try
        {
            String filename = (String)incomingRequest.get("filename");
            String alias = (String)incomingRequest.get("alias");
            String msg = (String)incomingRequest.get("msg");
            String severity = (String)incomingRequest.get("severity");
            String order = (String)incomingRequest.get("order");
            String enabled = (String)incomingRequest.get("enabled");
            RuleElement rule = new RuleElement();
            rule.setAlias(alias);
            rule.setEnabled(enabled);
            rule.setFileName(filename);
            rule.setMsg(msg);
            rule.setOrder(order);
            rule.setSeverity(severity);
            ret = rule.getXmlRule();

            this.setStatus(Status.SUCCEEDED);
        }
        catch (Exception e)
        {
            this.setStatus(Status.FAILED);
            throw new TsaException(e.getMessage(), e);
        }
        return ret;
    }
}
