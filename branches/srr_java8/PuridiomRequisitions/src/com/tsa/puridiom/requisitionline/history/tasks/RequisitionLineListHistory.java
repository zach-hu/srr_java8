/*
 * Created on Jul 21, 2004
 *
 * @author  * renzo
 * project: HiltonRequisitions
 * package com.tsa.puridiom.requisitionheader.tasks.RequisitionLineGetHistory.java
 *
 */
package com.tsa.puridiom.requisitionline.history.tasks;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.RequisitionLine;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class RequisitionLineListHistory extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        try
        {
            Map incomingRequest = (Map)object;
            List reqList = (List)incomingRequest.get("requisitionLineList");
            if(reqList == null)
            {
                reqList = (List)incomingRequest.get("reqLines");
            }
            for (Iterator iter = reqList.iterator(); iter.hasNext();)
            {
                RequisitionLine reqLine = (RequisitionLine) iter.next();
                RequisitionLineHistory historyLine = new RequisitionLineHistory();

                incomingRequest.put("newHistoryRequisitionLine", reqLine);
                incomingRequest.put("requisitionHeader", incomingRequest.get("requisitionHeader"));
                incomingRequest.put("historyStatus", incomingRequest.get("historyStatus"));
                incomingRequest.put("forwardedTo", incomingRequest.get("forwardedTo"));
                incomingRequest.put("rejectedBy", incomingRequest.get("rejectedBy"));

                historyLine.executeTask(incomingRequest);
                this.setStatus(historyLine.getStatus());
                if(this.getStatus() != Status.SUCCEEDED)
                {
                    throw new TsaException(this.getName() + "Error occurred writing history for line: " + reqLine.getLineNumber() + ", item: " + reqLine.getItemNumber());
                }
            }
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