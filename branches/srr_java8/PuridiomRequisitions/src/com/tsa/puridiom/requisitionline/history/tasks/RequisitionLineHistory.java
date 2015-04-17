/*
 * Created on Jul 21, 2004
 *
 * @author  * renzo
 * project: HiltonRequisitions
 * package com.tsa.puridiom.requisitionheader.tasks.RequisitionLineGetHistory.java
 *
 */
package com.tsa.puridiom.requisitionline.history.tasks;

import java.util.HashMap;
import java.util.Map;

import com.tsa.puridiom.entity.RequisitionLine;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;

public class RequisitionLineHistory extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        try
        {
            Map incomingRequest = (Map)object;
            RequisitionLine newLine = (RequisitionLine)incomingRequest.get("newHistoryRequisitionLine");

            if(!Utility.isEmpty(newLine.getRequisitionNumber()) && !newLine.getRequisitionNumber().equals("N/A"))
            {
                PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
                PuridiomProcess process = processLoader.loadProcess("requisitionline-history.xml");

                Map newIncomingRequest = new HashMap();
                newIncomingRequest.put("dbsession", incomingRequest.get("dbsession"));
                newIncomingRequest.put("organizationId", incomingRequest.get("organizationId"));
                newIncomingRequest.put("userId", incomingRequest.get("userId"));
                newIncomingRequest.put("userTimeZone", incomingRequest.get("userTimeZone"));
                newIncomingRequest.put("newHistoryRequisitionLine", newLine);
                newIncomingRequest.put("forwardedTo", incomingRequest.get("forwardedTo"));
                newIncomingRequest.put("rejectedBy", incomingRequest.get("rejectedBy"));
                newIncomingRequest.put("historyStatus", incomingRequest.get("historyStatus"));
                newIncomingRequest.put("PoLine", incomingRequest.get("PoLine"));
                newIncomingRequest.put("writehistory", incomingRequest.get("writehistory"));
                newIncomingRequest.put("originalQuantity", incomingRequest.get("originalQuantity"));
                newIncomingRequest.put("originalPrice", incomingRequest.get("originalPrice"));
                if (incomingRequest.containsKey("RequisitionLine_noteCancel")) {
					newIncomingRequest.put("RequisitionLine_noteCancel", incomingRequest.get("RequisitionLine_noteCancel"));
				}

                newIncomingRequest.put("requisitionHeader", incomingRequest.get("requisitionHeader"));
                newIncomingRequest.put("ipAddress", incomingRequest.get("ipAddress"));
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