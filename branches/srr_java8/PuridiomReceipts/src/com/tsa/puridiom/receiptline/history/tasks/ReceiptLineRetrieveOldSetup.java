/*
 * Created on Jul 21, 2004
 *
 * @author  * renzo
 * project: HiltonRequisitions
 * package com.tsa.puridiom.requisitionline.history.tasks.RequisitionLineRetrieveOldSetup.java
 *
 */
package com.tsa.puridiom.receiptline.history.tasks;

import java.util.Map;

import com.tsa.puridiom.entity.ReceiptLine;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class ReceiptLineRetrieveOldSetup extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        try
        {
            Map incomingRequest = (Map)object;
            ReceiptLine newLine	= (ReceiptLine)incomingRequest.get("newHistoryReceiptLine");
            if(newLine == null)
            {
                this.setStatus(Status.FAILED);
                throw new TsaException("New Receipt Line was not found!");
            }
            //incomingRequest.put("RequisitionHeader_icHeaderHistory", newLine.getIcReqHeader().toString());
            incomingRequest.put("ReceiptHeader_icRecHeader", newLine.getIcRecHeader().toString());

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
