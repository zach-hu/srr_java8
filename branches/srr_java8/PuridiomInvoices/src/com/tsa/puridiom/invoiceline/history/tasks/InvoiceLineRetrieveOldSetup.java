/*
 * Created on Jul 21, 2004
 *
 * @author  * renzo
 * project: HiltonPurchaseOrders
 * package com.tsa.puridiom.poline.history.tasks.PoLineRetrieveOldSetup.java
 *
 */
package com.tsa.puridiom.invoiceline.history.tasks;

import java.util.Map;

import com.tsa.puridiom.entity.InvoiceLine;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class InvoiceLineRetrieveOldSetup extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        try
        {
            Map incomingRequest = (Map)object;
            InvoiceLine newLine	= (InvoiceLine)incomingRequest.get("newHistoryInvoiceLine");
            if(newLine == null)
            {
                this.setStatus(Status.FAILED);
                throw new TsaException("New Invoice Line was not found!");
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
