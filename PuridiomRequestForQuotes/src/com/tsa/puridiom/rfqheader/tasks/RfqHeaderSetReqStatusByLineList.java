package com.tsa.puridiom.rfqheader.tasks;

import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.RfqHeader;
import com.tsa.puridiom.entity.RfqLine;
import com.tsa.puridiom.rfqline.tasks.RfqLineUpdateReqLineStatus;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class RfqHeaderSetReqStatusByLineList extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        Map incomingRequest = (Map)object;
        Object result = null;

        try
        {
            RfqHeader rfqHeader = (RfqHeader) incomingRequest.get("rfqHeader");
            List rfqLineList = (List) incomingRequest.get("rfqLineList");
            if (rfqHeader == null)
            {
                throw new Exception("RfqHeader was not found");
            }
            if (rfqLineList != null) {
                for (int i = 0; i < rfqLineList.size(); i++) {
                    RfqLine rfqLine = (RfqLine) rfqLineList.get(i);
                    RfqLineUpdateReqLineStatus task = new RfqLineUpdateReqLineStatus();
                    incomingRequest.put("rfqLine", rfqLine);
                    task.executeTask(incomingRequest);
                    this.setStatus(task.getStatus());
                    if(task.getStatus() != Status.SUCCEEDED)
                    {
                        throw new TsaException("An error occurred updating Status for Request for quotes: " + rfqLine.getRfqNumber());
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