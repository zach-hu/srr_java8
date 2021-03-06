/*
 * Created on May 5, 2005
 */
package com.tsa.puridiom.requisition.buyerassignment.tasks;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.TsaException;

/**
 * @author renzo
 */
public class RequisitionHeaderApprovedNotification extends Task
{

    public Object executeTask(Object object) throws Exception
    {
        Object ret = null;
        try
        {
            Map incomingRequest = (Map)object;
            RequisitionHeader rqh = (RequisitionHeader)incomingRequest.get("requisitionHeader");
            if(rqh == null)
            {
                this.setStatus(Status.FAILED);
                throw new TsaException("Buyer Could not be Notified, Requisition was not found");
            }
            Log.debug(this, "Notifying Buyer, Requisition: " + rqh.getRequisitionNumber());
            List requisitionLineList = (List)incomingRequest.get("requisitionLineList");
            String assignTo = rqh.getAssignedBuyer();

            Map newIncomingRequest = new HashMap();
            newIncomingRequest.put("userId", incomingRequest.get("userId"));
            newIncomingRequest.put("userTimeZone", incomingRequest.get("userTimeZone"));
            newIncomingRequest.put("organizationId", incomingRequest.get("organizationId"));
            newIncomingRequest.put("dbsession", incomingRequest.get("dbsession"));

            newIncomingRequest.put("assignType", "AUTO");
            newIncomingRequest.put("assignTo", assignTo);
            newIncomingRequest.put("requisitionHeader", rqh);
            newIncomingRequest.put("requisitionLineList", requisitionLineList);
            AssignmentNotificationByHeader notify = new AssignmentNotificationByHeader();
            try
            {
                notify.executeTask(newIncomingRequest);
            }
            catch (Exception e)
            {
                Log.error(this, assignTo + " could not be notified!");
            }
            if(notify.getStatus() != Status.SUCCEEDED)
            {
                Log.error(this, assignTo + " could not be notified!");
            }

            this.setStatus(Status.SUCCEEDED);
        }
        catch (Exception e)
        {
            this.setStatus(Status.FAILED);
            throw new TsaException("Buyer could not be notified.", e);
        }
        return ret;
    }
}
