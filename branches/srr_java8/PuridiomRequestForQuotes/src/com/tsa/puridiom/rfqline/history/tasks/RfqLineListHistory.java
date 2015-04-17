/*
 * Created on August 27, 2004
 *
 * project: HiltonRequestForQuotes
 * package com.tsa.puridiom.rfqline.tasks.RfqLineListHistory.java
 *
 */
package com.tsa.puridiom.rfqline.history.tasks;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.RfqLine;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class RfqLineListHistory extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        try
        {
            Map incomingRequest = (Map)object;
            List rfqList = (List)incomingRequest.get("rfqLineList");
            for (Iterator iter = rfqList.iterator(); iter.hasNext();)
            {
                RfqLine rfqLine = (RfqLine) iter.next();
                RfqLineHistory historyLine = new RfqLineHistory();

                incomingRequest.put("newHistoryRfqLine", rfqLine);
                incomingRequest.put("rfqHeader", incomingRequest.get("rfqHeader"));
                incomingRequest.put("autoReleased", incomingRequest.get("rfqFromRelease"));

                historyLine.executeTask(incomingRequest);
                this.setStatus(historyLine.getStatus());
                if(this.getStatus() != Status.SUCCEEDED)
                {
                    throw new TsaException(this.getName() + "Error ocurred writing history for line: " + rfqLine.getRfqLine() + ", item: " + rfqLine.getItemNumber());
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