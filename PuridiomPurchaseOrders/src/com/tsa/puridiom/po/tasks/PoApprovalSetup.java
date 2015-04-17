/*
 * Created on Aug 19, 2003
 */
package com.tsa.puridiom.po.tasks;

import java.util.*;

import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility ;
import com.tsagate.foundation.processengine.Status;

public class PoApprovalSetup extends Task
{
    public Object executeTask(Object object) throws Exception
    {

        Map incomingRequest = (Map) object;

        try
        {
        	String icPoHeader = (String) incomingRequest.get("PoHeader_icPoHeader");
            if (Utility.isEmpty(icPoHeader))
            {
                icPoHeader = (String) incomingRequest.get("ApprovalLog_icHeader");
                incomingRequest.put("PoHeader_icPoHeader",icPoHeader);
                incomingRequest.put("PoLine_icPoHeader",icPoHeader);
                incomingRequest.put("ApprovalLog_icLine", "0");


            }
            if (Utility.isEmpty(icPoHeader))
            {
                this.setStatus(Status.FAILED);
                throw new Exception("PoHeader_icPoHeader cannot be empty.  Order could not be retrieved.");
            }
            else
            {
                incomingRequest.put("ApprovalLog_icHeader",icPoHeader) ;
            }
            this.setStatus(Status.SUCCEEDED);
		}
        catch (Exception e)
        {
			this.setStatus(Status.FAILED);
			throw new TsaException("Po Approvals could not be completed!", e);
		}

        return null ;
    }
}
