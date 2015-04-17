/*
 * Created on Dec 7, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.tsa.puridiom.approvals.tasks;

import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.ApprovalLog;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.TsaException;

/**
 * @author renzo
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ApprovalLogListUpdate extends Task
{

    public Object executeTask(Object object) throws Exception
    {
        try
        {
            Map incomingRequest = (Map)object;
            this.setStatus(Status.SUCCEEDED) ;
            List routingList = (List)incomingRequest.get("routingList");
            String fromPage = (String) incomingRequest.get("fromPage");
            if (routingList != null && routingList.size() > 0)
            {
            	for(int i = 0; i < routingList.size(); i++)
                {
                    ApprovalLog approvalLog = (ApprovalLog)routingList.get(i);
                    ApprovalLogAdd addMe = new ApprovalLogAdd();
                    incomingRequest.put("approvalLog", approvalLog);
                    addMe.executeTask(incomingRequest);
                    this.setStatus(addMe.getStatus());
                    if(this.getStatus() != Status.SUCCEEDED)
                    {
                        throw new TsaException("An error ocurred writting a record for user: " + approvalLog.getComp_id().getUserId());
                    }
                }
            }
            else
            {
            	if ("routinglist".equalsIgnoreCase(fromPage))
            	{
            		throw new TsaException("Can not forward without any Approvers");
            	}
            }
        }
        catch (Exception e)
        {
        	Log.error(this, e);
        	this.setStatus(Status.FAILED);
            throw e;
        }
        return null;
    }
}
