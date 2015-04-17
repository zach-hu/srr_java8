/*
 * Created on Nov 4, 2004
 */
package com.tsa.puridiom.validationrules.tasks;

import java.math.BigDecimal;
import java.util.Map;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

/**
 * @author renzo
 */
public class InvoiceRulesSetup extends Task
{

    /* (non-Javadoc)
     * @see com.tsagate.foundation.processengine.ITask#executeTask(java.lang.Object)
     */
    public Object executeTask(Object object) throws Exception
    {
        try
        {
            Map incomingRequest = (Map)object;
            String icHeader = (String)incomingRequest.get("InvoiceHeader_icIvcHeader");
            
            if (icHeader == null)
            {
                this.setStatus(Status.FAILED);
                throw new TsaException("Please select an Invoice to validate.  The value for InvoiceHeader_icIvcHeader was not found.");
            }
            BigDecimal ic = new BigDecimal(icHeader);
            
            incomingRequest.put("InvoiceLine_icIvcHeader", icHeader);
            incomingRequest.put("Labels_moduleAccess", "INVOICE");
            incomingRequest.put("Rules_moduleAccess", "INVOICE");
            incomingRequest.put("Rules_enabled", "Y");
            
            this.setStatus(Status.SUCCEEDED);
        }
        catch (Exception e)
        {
            this.setStatus(Status.FAILED);
            throw e;
        }
        return null;
    }
}
