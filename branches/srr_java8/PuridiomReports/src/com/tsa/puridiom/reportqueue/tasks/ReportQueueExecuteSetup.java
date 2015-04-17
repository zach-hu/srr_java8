package com.tsa.puridiom.reportqueue.tasks;

import java.util.Map;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.ReportQueue;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.TsaException;

public class ReportQueueExecuteSetup extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        Object result = null;
        Map incomingRequest = (Map) object;
        try
        {
        	Log.debug(this, "ExecuteSetup");
            ReportQueue reportQueue = (ReportQueue)incomingRequest.get("reportQueue");
            if(reportQueue == null)
            {
            	incomingRequest.put("failed", Boolean.TRUE);
            	this.setStatus(Status.FAILED);
            }
            else
            {
	            incomingRequest.put("sqlWhere", reportQueue.getWhereClause());
	            incomingRequest.put("browseName", reportQueue.getName());
	            incomingRequest.put("reportName", reportQueue.getName());
	            incomingRequest.put("format", reportQueue.getType());
	            if (HiltonUtility.isEmpty((String)incomingRequest.get("webreport"))) {
	            	incomingRequest.put("webreport", "R");
	            }
	            incomingRequest.put("userId", reportQueue.getOwner());
	            this.setStatus(Status.SUCCEEDED);
            }
        }
        catch (Exception e)
        {
            this.setStatus(Status.FAILED);
            throw new TsaException("ReportExecuteSetup failed, " + e.getMessage(), e);
        }
        return result;
    }
}