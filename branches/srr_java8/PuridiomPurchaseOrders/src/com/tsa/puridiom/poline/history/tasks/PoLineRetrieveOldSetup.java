/*
 * Created on Jul 21, 2004
 *
 * @author  * renzo
 * project: HiltonPurchaseOrders
 * package com.tsa.puridiom.poline.history.tasks.PoLineRetrieveOldSetup.java
 * 
 */
package com.tsa.puridiom.poline.history.tasks;

import java.util.Map;

import com.tsa.puridiom.entity.PoLine;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class PoLineRetrieveOldSetup extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        try
        {
            Map incomingRequest = (Map)object;
            PoLine newLine	= (PoLine)incomingRequest.get("newHistoryPoLine");
            if(newLine == null)
            {
                this.setStatus(Status.FAILED);
                throw new TsaException("New Po Line was not found!");
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
