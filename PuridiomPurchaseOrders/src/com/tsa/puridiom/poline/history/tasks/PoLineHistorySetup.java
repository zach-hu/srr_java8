/*
 * Created on Jul 21, 2004
 *
 * @author  * renzo
 * project: HiltonPurchaseOrders
 * package com.tsa.puridiom.poheader.history.tasks.PoLineGetHistory.java
 * 
 */
package com.tsa.puridiom.poline.history.tasks;

import java.util.Map;

import com.tsa.puridiom.entity.PoLine;
import com.tsa.puridiom.poline.exceptions.PoLineNotFoundException;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class PoLineHistorySetup extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        try
        {
            Map incomingRequest = (Map)object;
            PoLine newLine	= (PoLine)incomingRequest.get("poLine");
            if(newLine == null)
            {
                this.setStatus(Status.FAILED);
                throw new PoLineNotFoundException("PoLine not found");
            }
            incomingRequest.put("newHistoryPoLine", newLine);
            
            
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
