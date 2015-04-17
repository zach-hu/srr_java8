/**
 * Created on Apr 12, 2004
 * @author renzo
 * project: HiltonPurchaseOrders
 * com.tsa.puridiom.poline.tasks.PoLineCancelSetup.java
 *
 */
package com.tsa.puridiom.po.tasks;

import java.util.Map;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class PoCancelSetup extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        try
        {
            Map incomingRequest = (Map)object;
            String icPoHeader = (String)incomingRequest.get("PoHeader_icPoHeader");
            incomingRequest.put("formtype", "PO");
            incomingRequest.put("PoLine_icPoHeader", icPoHeader);
            incomingRequest.put("Account_icHeader", icPoHeader);
            incomingRequest.put("Account_icLine", "0");
            String priorrev = (String)incomingRequest.get("priorrev");
            if(priorrev == null)
            {
            	incomingRequest.put("priorrev", "N");
            }
            String skipcancelcheck = (String)incomingRequest.get("skipcancelcheck");
            if(skipcancelcheck == null)
            {
            	incomingRequest.put("skipcancelcheck", "N");
            }



            if(icPoHeader == null)
            {
                this.setStatus(Status.FAILED);
                throw new TsaException("We couldn't locate your Order!");
            }

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
