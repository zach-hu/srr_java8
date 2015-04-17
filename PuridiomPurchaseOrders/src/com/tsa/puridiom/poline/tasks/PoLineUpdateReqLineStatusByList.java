package com.tsa.puridiom.poline.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import java.util.*;

public class PoLineUpdateReqLineStatusByList extends Task
{
    public Object  executeTask (Object object) throws Exception
    {
        Map incomingRequest = (Map)object;
        Object result = null;

        try
        {
            List	poLineList = (List)incomingRequest.get("poLineList");
            PoLineUpdateReqLineStatus task = new PoLineUpdateReqLineStatus();

            for (int i=0; i < poLineList.size(); i++)
            {
                PoLine poLine = (PoLine) poLineList.get(i);

                incomingRequest.put("poLine", poLine);

                task.executeTask(incomingRequest);

                if (task.getStatus() < Status.SUCCEEDED)
                {
                    this.setStatus(task.getStatus());
                    throw new Exception("PoLineUpdateReqLineStatus failed for " + poLine.toString());
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