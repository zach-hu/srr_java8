/**
 *
 */
package com.tsa.puridiom.requisition.tasks;

import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

/**
 * @author renzo
 *
 */
public class RequisitionSetUserRejectAccess extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        Object ret = Boolean.TRUE;
        Map incomingRequest = (Map)object;
        boolean access = true;
        try
        {
            String userId = (String)incomingRequest.get("userId");
            RequisitionHeader header = (RequisitionHeader)incomingRequest.get("requisitionHeader");
            List lines = (List)incomingRequest.get("requisitionLineList");
            for(int i = 0; i < lines.size(); i++)
            {
                RequisitionLine line = (RequisitionLine)lines.get(i);
                String assignedBuyer = line.getAssignedBuyer();
                if(!assignedBuyer.equalsIgnoreCase(userId))
                {
                    access = false;
                    i = lines.size();
                }
            }
            incomingRequest.put("requisitionHeader", header);
            this.setStatus(Status.SUCCEEDED);
        }
        catch(Exception e)
        {
            this.setStatus(Status.FAILED);
            throw new TsaException(this.getName() + ", " + e.getMessage() , e);
        }
        return String.valueOf(access);
    }
}
