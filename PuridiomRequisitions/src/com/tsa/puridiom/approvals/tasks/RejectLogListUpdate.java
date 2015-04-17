/*
 * Created on Dec 7, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.tsa.puridiom.approvals.tasks;

import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.RejectLog;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

/**
 * @author renzo
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class RejectLogListUpdate extends Task
{

    public Object executeTask(Object object) throws Exception
    {
        Object ret = null;
        try
        {
            Map incomingRequest = (Map)object;

            List rejectList = (List)incomingRequest.get("rejectLogList");
            for(int i = 0; i < rejectList.size(); i++)
            {
                RejectLog rejectLog = (RejectLog)rejectList.get(i);
                RejectLogAdd addMe = new RejectLogAdd();
                incomingRequest.put("rejectLog", rejectLog);
                addMe.executeTask(incomingRequest);
                this.setStatus(addMe.getStatus());
                if(this.getStatus() != Status.SUCCEEDED)
                {
                    throw new TsaException("An error ocurred writting a record for user: " + rejectLog.getUserId());
                }
            }
        }
        catch (Exception e)
        {
            this.setStatus(Status.FAILED);
            throw new TsaException(this.getName(), e);
        }
        return ret;
    }
}
