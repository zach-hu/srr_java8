/*
 * Created on Jul 21, 2004
 *
 * @author  * renzo
 * project: HiltonPurchaseOrders
 * package com.tsa.puridiom.requisitionline.history.tasks.RequisitionLineHistorySetup.java
 * 
 */
package com.tsa.puridiom.requisitionline.history.tasks;

import java.util.Map;

import com.tsa.puridiom.entity.RequisitionLine;
import com.tsa.puridiom.requisitionline.exceptions.RequisitionLineNotFoundException;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class RequisitionLineHistorySetup extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        try
        {
            Map incomingRequest = (Map)object;
            RequisitionLine newLine	= (RequisitionLine)incomingRequest.get("requisitionLine");
            if(newLine == null)
            {
                this.setStatus(Status.FAILED);
                throw new RequisitionLineNotFoundException("RequisitionLine not found");
            }
            incomingRequest.put("newHistoryRequisitionLine", newLine);
            
            
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
