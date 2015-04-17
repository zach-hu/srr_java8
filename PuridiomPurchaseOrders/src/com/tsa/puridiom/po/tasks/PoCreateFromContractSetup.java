/**
 * Created on Apr 12, 2004
 * @author renzo
 * project: HiltonPurchaseOrders
 * com.tsa.puridiom.poline.tasks.PoLineCancelSetup.java
 *
 */
package com.tsa.puridiom.po.tasks;

import java.util.Map;
import java.math.BigDecimal;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class PoCreateFromContractSetup extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        try
        {
            Map incomingRequest = (Map)object;

            String newType = (String) incomingRequest.get("newType"); 
            incomingRequest.put("newType", newType);           
            
            incomingRequest.put("createFromContract", "Y");

            String icPoHeader = (String) incomingRequest.get("PoHeader_icPoHeader");
            BigDecimal bdIcPoHeader = new BigDecimal(icPoHeader);

            incomingRequest.put("originalIc", bdIcPoHeader);

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
