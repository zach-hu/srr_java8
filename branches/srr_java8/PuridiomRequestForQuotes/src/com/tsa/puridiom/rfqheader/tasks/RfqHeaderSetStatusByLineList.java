package com.tsa.puridiom.rfqheader.tasks;

import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.RfqHeader;
import com.tsa.puridiom.entity.RfqLine;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

public class RfqHeaderSetStatusByLineList extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        Map incomingRequest = (Map)object;
        Object result = null;

        try
        {
            RfqHeader rfqHeader = (RfqHeader) incomingRequest.get("rfqHeader");
            List rfqLineList = (List) incomingRequest.get("rfqLineList");

            if (rfqHeader == null) {
            	this.setStatus(Status.FAILED);
            	throw new Exception("RfqHeader was not found");
            }

            Integer	rfqStatus = new Integer(0);

            if (rfqLineList != null) {
                for (int i = 0; i < rfqLineList.size(); i++) {
                    RfqLine rfqLine = (RfqLine) rfqLineList.get(i);
                    Integer lineStatus = new Integer(rfqLine.getStatus());
                    if (i == 0) {
                        rfqStatus = lineStatus;
                    } else if (lineStatus.compareTo(rfqStatus) < 0) {
                        rfqStatus = lineStatus;
                    }
                }
                rfqHeader.setStatus(String.valueOf(rfqStatus));
            }

            result = rfqHeader ;
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