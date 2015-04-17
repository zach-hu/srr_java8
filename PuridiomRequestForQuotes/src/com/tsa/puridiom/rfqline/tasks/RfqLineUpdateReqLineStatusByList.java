package com.tsa.puridiom.rfqline.tasks;

import com.tsa.puridiom.entity.RfqLine;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import java.util.List;
import java.util.Map;

public class RfqLineUpdateReqLineStatusByList extends Task
{
    public Object  executeTask (Object object) throws Exception
    {
        Map incomingRequest = (Map)object;
        Object result = null;

        try
        {
            List	rfqLineList = (List)incomingRequest.get("rfqLineList");
            RfqLineUpdateReqLineStatus task = new RfqLineUpdateReqLineStatus();

            for (int i=0; i < rfqLineList.size(); i++)
            {
                RfqLine rfqLine = (RfqLine) rfqLineList.get(i);

                incomingRequest.put("rfqLine", rfqLine);

                task.executeTask(incomingRequest);

                if (task.getStatus() < Status.SUCCEEDED)
                {
                    this.setStatus(task.getStatus());
                    throw new Exception("RfqLineUpdateReqLineStatus failed for " + rfqLine.toString());
                }
            }

            this.setStatus(Status.SUCCEEDED) ;
        }
        catch (Exception e)
        {
            this.setStatus(Status.FAILED);
            throw e;
        }
        return result;
    }
}