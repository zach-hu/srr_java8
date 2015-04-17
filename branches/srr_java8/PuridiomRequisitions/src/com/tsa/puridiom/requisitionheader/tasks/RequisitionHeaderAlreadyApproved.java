/*
 *
 */
package com.tsa.puridiom.requisitionheader.tasks;

import java.util.Map;

import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

public class RequisitionHeaderAlreadyApproved extends Task
{

    public Object executeTask(Object object) throws Exception
    {
        Object ret = null;
        try
        {
            Map incomingRequest = (Map)object;
            RequisitionHeader rqh = (RequisitionHeader) incomingRequest.get("requisitionHeader") ;
           	String	message = "Requisition " + rqh.getRequisitionNumber() + " has already been approved and is no longer in your queue!" ;
            Log.info(this, message);
            incomingRequest.put("Message", message);
            incomingRequest.put("approvalAbleVerification", "N");
            ret = "true";
            this.setStatus(Status.SUCCEEDED);
        }
        catch (Exception e)
        {
            this.setStatus(Status.FAILED);
        }
        return ret;
    }
}
