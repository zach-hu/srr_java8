/*
 * Created on Jul 21, 2004
 *
 * @author  * renzo
 * project: HiltonRequisitions
 * package com.tsa.puridiom.disbheader.tasks.DisbHeaderHistory.java
 * 
 */
package com.tsa.puridiom.disbheader.history.tasks;

import java.util.HashMap;
import java.util.Map;

import com.tsa.puridiom.entity.DisbHeader;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class DisbHeaderHistory extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        try
        { 
            Map incomingRequest = (Map)object;
            PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
			PuridiomProcess process = processLoader.loadProcess("disbursement-history.xml");
			 
			Map newIncomingRequest = new HashMap();
			newIncomingRequest.put("dbsession", incomingRequest.get("dbsession"));
			newIncomingRequest.put("organizationId", incomingRequest.get("organizationId"));
			newIncomingRequest.put("userId", incomingRequest.get("userId"));
			newIncomingRequest.put("userTimeZone", incomingRequest.get("userTimeZone"));
			
			DisbHeader newHeader = (DisbHeader)incomingRequest.get("disbHeader");
			newIncomingRequest.put("DisbHeader_oldStatus", incomingRequest.get("DisbHeader_status"));
			newIncomingRequest.put("newHistoryDisbHeader", newHeader);
			
			process.executeProcess(newIncomingRequest);
			
			this.setStatus(Status.SUCCEEDED);
        }
        catch (Exception e) 
        {
            this.setStatus(Status.FAILED);
           throw new TsaException(this.getName(), e);
        }
        return super.executeTask(object);
    }
}