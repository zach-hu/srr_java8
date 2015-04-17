/*
 * Created on Jul 21, 2004
 *
 * @author  * renzo
 * project: HiltonRequisitions
 * package com.tsa.puridiom.disbheader.history.tasks.DisbursementLineGetHistory.java
 * 
 */
package com.tsa.puridiom.disbline.history.tasks;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.tsa.puridiom.entity.DisbLine;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;

public class DisbursementLineHistory extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        try
        {
            Map incomingRequest = (Map)object;
            DisbLine newLine = (DisbLine)incomingRequest.get("newHistoryDisbLine");
            
            if(!Utility.isEmpty(newLine.getDisbNumber()))
            {
	            PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
				PuridiomProcess process = processLoader.loadProcess("disbursementline-history.xml");
				 
				Map newIncomingRequest = new HashMap();
				newIncomingRequest.put("dbsession", incomingRequest.get("dbsession"));
				newIncomingRequest.put("organizationId", incomingRequest.get("organizationId"));
				newIncomingRequest.put("userId", incomingRequest.get("userId"));
				newIncomingRequest.put("userTimeZone", incomingRequest.get("userTimeZone"));
				newIncomingRequest.put("newHistoryDisbLine", newLine);
				newIncomingRequest.put("disbHeader", incomingRequest.get("disbHeader"));
				
				//newIncomingRequest.put("historyReqBackorder", this.getBackorderFlag(incomingRequest, newLine.getIcReqLine()));
				
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