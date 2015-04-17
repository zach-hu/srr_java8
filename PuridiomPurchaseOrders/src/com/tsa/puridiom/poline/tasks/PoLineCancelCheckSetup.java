/**
 * Created on Apr 12, 2004
 * @author renzo
 * project: HiltonPurchaseOrders
 * com.tsa.puridiom.poline.tasks.PoLineCancelSetup.java
 *
 */
package com.tsa.puridiom.poline.tasks;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class PoLineCancelCheckSetup extends Task
{
    public Object executeTask(Object object) throws Exception
    {
    	Map incomingRequest = (Map)object;
    	Object ret = null;
        try
        {
            List poItemsFromReq = (List)incomingRequest.get("poItemsFromReq");

            if(poItemsFromReq == null)
            {
            	poItemsFromReq = new ArrayList();
            }
            poItemsFromReq.add(incomingRequest.get("poLine"));

            ret = poItemsFromReq;
            this.setStatus(Status.SUCCEEDED);
        }
        catch (Exception e)
        {
            this.setStatus(Status.FAILED);
            throw new TsaException(this.getName(), e);
        }
        return ret;
    }
}
