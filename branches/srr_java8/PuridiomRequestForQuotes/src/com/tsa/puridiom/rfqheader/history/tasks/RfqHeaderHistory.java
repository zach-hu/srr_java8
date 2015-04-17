/*
 * Created on August 27, 2004
 *
 * project: HiltonRequestForQuotes
 * package com.tsa.puridiom.rfqheader.tasks.RfqHeaderHistory.java
 *
 */
package com.tsa.puridiom.rfqheader.history.tasks;

import java.util.HashMap;
import java.util.Map;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.RfqHeader;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;

public class RfqHeaderHistory extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        try
        {
            Map incomingRequest = (Map)object;
            RfqHeader newHeader = (RfqHeader)incomingRequest.get("rfqHeader");
            if(!Utility.isEmpty(newHeader.getRfqNumber()))
            {
	            PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
				PuridiomProcess process = processLoader.loadProcess("rfqheader-history.xml");

				Map newIncomingRequest = new HashMap();
				newIncomingRequest.put("dbsession", incomingRequest.get("dbsession"));
				newIncomingRequest.put("organizationId", incomingRequest.get("organizationId"));
				newIncomingRequest.put("userId", incomingRequest.get("userId"));
				newIncomingRequest.put("RfqHeader_oldStatus", incomingRequest.get("RfqHeader_status"));
				newIncomingRequest.put("forwardedTo", incomingRequest.get("forwardedTo"));
				newIncomingRequest.put("newHistoryRfqHeader", newHeader);
				newIncomingRequest.put("ipAddress", incomingRequest.get("ipAddress"));
				
				String deferTo = (String)incomingRequest.get("deferTo") ;
                if(!HiltonUtility.isEmpty(deferTo)){
                	newIncomingRequest.put("deferTo", deferTo);
                }
				
				if (incomingRequest.containsKey("ApprovalLog_approverNotes")) {
	                    newIncomingRequest.put("ApprovalLog_approverNotes", incomingRequest.get("ApprovalLog_approverNotes"));
	            }

				process.executeProcess(newIncomingRequest);
				this.setStatus(process.getStatus());
            }
            else
            {
                this.setStatus(Status.SUCCEEDED);
            }
        }
        catch (Exception e)
        {
            this.setStatus(Status.FAILED);
           throw new TsaException(this.getName(), e);
        }
        return super.executeTask(object);
    }
}