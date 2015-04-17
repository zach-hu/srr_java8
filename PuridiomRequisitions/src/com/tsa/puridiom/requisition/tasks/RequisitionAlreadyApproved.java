package com.tsa.puridiom.requisition.tasks;

import java.util.Map;

import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class RequisitionAlreadyApproved extends Task
{

    /* (non-Javadoc)
     * @see com.tsagate.foundation.processengine.Task#executeTask(java.lang.Object)
     */
    public Object executeTask(Object object) throws Exception
    {
        Map incomingRequest = (Map)object;
        try
        {
            RequisitionHeader header = (RequisitionHeader)incomingRequest.get("requisitionHeader");
            //TODO when a requisition has already been approved. This task will be executed. Here we can code anything necessary.
            this.setStatus(Status.SUCCEEDED);
        }
        catch (Exception e)
        {
            this.setStatus(Status.FAILED);
            throw new TsaException(e.toString(), e);
        }
        return null;
    }

}
