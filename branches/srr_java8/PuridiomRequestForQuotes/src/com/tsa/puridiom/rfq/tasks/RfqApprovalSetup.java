/*
 * Created on Aug 19, 2003
 */
package com.tsa.puridiom.rfq.tasks;

import java.util.*;

import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility ;
import com.tsagate.foundation.processengine.Status;

public class RfqApprovalSetup extends Task
{
    public Object executeTask(Object object) throws Exception
    {

        Map incomingRequest = (Map) object;

        try
        {
        	String icRfqHeader = (String) incomingRequest.get("RfqHeader_icRfqHeader");
            if (Utility.isEmpty(icRfqHeader))
            {
                icRfqHeader = (String) incomingRequest.get("ApprovalLog_icHeader");
                incomingRequest.put("RfqHeader_icRfqHeader",icRfqHeader);
                incomingRequest.put("RfqLine_icRfqHeader",icRfqHeader);
                incomingRequest.put("ApprovalLog_icLine", "0");


            }
            if (Utility.isEmpty(icRfqHeader))
            {
                this.setStatus(Status.FAILED);
                throw new Exception("RfqHeader_icRfqHeader cannot be empty.  Rfq could not be retrieved.");
            }
            else
            {
                incomingRequest.put("ApprovalLog_icHeader",icRfqHeader) ;
            }
            this.setStatus(Status.SUCCEEDED);
		}
        catch (Exception e)
        {
			this.setStatus(Status.FAILED);
			throw new TsaException("Rfq Approvals could not be completed!", e);
		}

        return null ;
    }
}
