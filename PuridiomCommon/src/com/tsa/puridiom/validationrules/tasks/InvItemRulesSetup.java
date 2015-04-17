/*
 * Created on Nov 4, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.tsa.puridiom.validationrules.tasks;

import java.util.Map;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

/**
 * @author renzo
 *
 */
public class InvItemRulesSetup extends Task
{

    public Object executeTask(Object object) throws Exception
    {
        try
        {
            Map incomingRequest = (Map)object;
            String icHeader = (String)incomingRequest.get("InvItem_itemNumber");
            
            if(icHeader == null)
            {
                this.setStatus(Status.FAILED);
                throw new TsaException("Please select an Item to Validate");
            }
            
            //incomingRequest.put("Account_icHeader", icHeader);
            incomingRequest.put("docType", "invitem");
            incomingRequest.put("Labels_moduleAccess", "INVENTORY");
            this.setStatus(Status.SUCCEEDED);
        }
        catch (Exception e)
        {
            this.setStatus(Status.FAILED);
            throw new TsaException(this.getName(), e);
        }
        return null;
    }
}
