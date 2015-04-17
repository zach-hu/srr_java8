/**
 * Created on Apr 12, 2004
 * @author renzo
 * project: HiltonPurchaseOrders
 * com.tsa.puridiom.poline.tasks.PoLineCancelSetup.java
 *
 */
package com.tsa.puridiom.poline.tasks;

import java.util.Map;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class PoLineCancelSetup extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        try
        {
            Map incomingRequest = (Map)object;
            String icPoHeader = (String)incomingRequest.get("PoLine_icPoHeader");
            String icPoLine = (String)incomingRequest.get("PoLine_icPoLine");
            incomingRequest.put("PoHeader_icPoHeader", icPoHeader);
            incomingRequest.put("Account_icHeader", icPoHeader);
            incomingRequest.put("Account_icLine", icPoLine);
            incomingRequest.put("formtype", "PO");
            String skipcancelcheck = (String)incomingRequest.get("skipcancelcheck");
            if(skipcancelcheck == null)
            {
            	incomingRequest.put("skipcancelcheck", "N");
            }
            String recalculate = (String)incomingRequest.get("recalculate");
            if(recalculate == null)
            {
                incomingRequest.put("recalculate", "Y");
            }

            this.setStatus(Status.SUCCEEDED);
        }
        catch (Exception e)
        {
            this.setStatus(Status.FAILED);
            throw new TsaException(this.getName(), e);
        }
        return super.executeTask(object);
    }
}
