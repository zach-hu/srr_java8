/*
 * Created on Nov 4, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.tsa.puridiom.validationrules.tasks;

import java.math.BigDecimal;
import java.util.Map;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

/**
 * @author renzo
 *
 */
public class DisbRulesSetup extends Task
{

    public Object executeTask(Object object) throws Exception
    {
        try
        {
            Map incomingRequest = (Map)object;
            String icHeader = (String)incomingRequest.get("DisbHeader_icDsbHeader");
            
            if(icHeader == null)
            {
                this.setStatus(Status.FAILED);
                throw new TsaException("Please select a Requisition to Validate");
            }
            BigDecimal ic = new BigDecimal(icHeader);
            
            incomingRequest.put("DisbLine_icDsbHeader", icHeader);
            incomingRequest.put("Account_icHeader", icHeader);
            incomingRequest.put("formType", "DSB");
            incomingRequest.put("Labels_moduleAccess", "DISBURSEMENT");
            incomingRequest.put("Rules_moduleAccess", "DISBURSEMENT");
            incomingRequest.put("Rules_enabled", "Y");

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
