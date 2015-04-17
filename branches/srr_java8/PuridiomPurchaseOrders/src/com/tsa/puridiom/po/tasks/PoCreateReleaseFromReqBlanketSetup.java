/*
 * Created on Mar 31, 2005
 */
package com.tsa.puridiom.po.tasks;

import java.util.Map;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

/**
 * @author renzo
 */
public class PoCreateReleaseFromReqBlanketSetup extends Task
{

    public Object executeTask(Object object) throws Exception
    {
        Object ret = null;
        try
        {
            Map incomingRequest = (Map)object;
            incomingRequest.put("recalculate", "Y");
            String icPoHeader = (String)incomingRequest.get("PoHeader_icPoHeader");
            if(icPoHeader == null)
            {
                this.setStatus(Status.FAILED);
                throw new TsaException("icPoHeader was not found!");
            }
            String icReqHeader = (String)incomingRequest.get("RequisitionHeader_icReqHeader");
            if(icReqHeader == null)
            {
                this.setStatus(Status.FAILED);
                throw new TsaException("icReqHeader was not found!");
            }
            else
            {
                incomingRequest.put("RequisitionLine_icReqHeader", icReqHeader);
            }
            this.setStatus(Status.SUCCEEDED);
        }
        catch (Exception e)
        {
            this.setStatus(Status.FAILED);
            throw new TsaException(e.getMessage());
        }
        return super.executeTask(object);
    }
}
