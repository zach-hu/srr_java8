/*
 * Created on Jul 21, 2004
 *
 * @author  * renzo
 * project: HiltonRequisitions
 * package com.tsa.puridiom.requisitionheader.history.tasks.RequisitionLineRetrieveOldSetup.java
 * 
 */
package com.tsa.puridiom.requisitionheader.history.tasks;

import java.util.Map;

import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class RequisitionHeaderRetrieveOldSetup extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        try
        {
            Map incomingRequest = (Map)object;
            RequisitionHeader newHeader	= (RequisitionHeader)incomingRequest.get("newHistoryRequisitionHeader");
            if(newHeader == null)
            {
                this.setStatus(Status.FAILED);
                throw new TsaException("New Requisition Header was not found!");
            }
            //incomingRequest.put("old_RequisitionHeader_icReqHeader", newHeader.getIcReqHeader().toString());
            
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
