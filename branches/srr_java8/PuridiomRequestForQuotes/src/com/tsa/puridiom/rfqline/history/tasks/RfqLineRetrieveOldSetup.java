/*
 * Created on Jul 21, 2004
 *
 * @author  * renzo
 * project: HiltonPurchaseOrders
 * package com.tsa.puridiom.poline.history.tasks.PoLineRetrieveOldSetup.java
 *
 */
package com.tsa.puridiom.rfqline.history.tasks;

import java.util.Map;

import com.tsa.puridiom.entity.RfqLine;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class RfqLineRetrieveOldSetup extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        try
        {
            Map incomingRequest = (Map)object;
            RfqLine newLine	= (RfqLine)incomingRequest.get("newHistoryRfqLine");
            if(newLine == null)
            {
                this.setStatus(Status.FAILED);
                throw new TsaException("New Rfq Line was not found!");
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
