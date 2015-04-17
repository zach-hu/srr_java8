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
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class PoRulesSetup extends Task
{

    /* (non-Javadoc)
     * @see com.tsagate.foundation.processengine.ITask#executeTask(java.lang.Object)
     */
    public Object executeTask(Object object) throws Exception
    {
        try
        {
            Map incomingRequest = (Map)object;
            String icHeader = (String)incomingRequest.get("PoHeader_icPoHeader");
            
            if(icHeader == null)
            {
                this.setStatus(Status.FAILED);
                throw new TsaException("Please select a Purchase Order to Validate");
            }
            BigDecimal ic = new BigDecimal(icHeader);
            
            incomingRequest.put("PoLine_icPoHeader", icHeader);
            incomingRequest.put("Account_icHeader", icHeader);
            incomingRequest.put("VendorInsurance_icPoHeader", icHeader);
            incomingRequest.put("formType", "PO");
            incomingRequest.put("Labels_moduleAccess", "PURCHASE ORDERS");
            incomingRequest.put("Rules_moduleAccess", "PURCHASE ORDERS");
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
