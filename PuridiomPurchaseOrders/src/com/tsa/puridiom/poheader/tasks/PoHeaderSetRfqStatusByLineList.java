package com.tsa.puridiom.poheader.tasks;

import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.entity.PoLine;
import com.tsa.puridiom.poline.tasks.PoLineUpdateRfqLinesStatus;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class PoHeaderSetRfqStatusByLineList extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        Map incomingRequest = (Map)object;
        Object result = null;

        try
        {
            PoHeader poHeader = (PoHeader) incomingRequest.get("poHeader");
            List poLineList = (List) incomingRequest.get("poLineList");
            if (poHeader == null)
            {
                throw new Exception("PoHeader was not found");
            }
            if (poLineList != null) {
                for (int i = 0; i < poLineList.size(); i++) {
                    PoLine poLine = (PoLine) poLineList.get(i);
                    PoLineUpdateRfqLinesStatus task = new PoLineUpdateRfqLinesStatus();
                    incomingRequest.put("poLine", poLine);
                    task.executeTask(incomingRequest);
                    this.setStatus(task.getStatus());
                    if(task.getStatus() != Status.SUCCEEDED)
                    {
                        throw new TsaException("An error occurred updating Status for Order: " + poLine.getPoNumber());
                    }
                }
            }

            result = null;
            this.status = Status.SUCCEEDED;
        }
        catch (Exception e)
        {
            this.status = Status.FAILED;
            throw e;
        }
        return result;
    }
}