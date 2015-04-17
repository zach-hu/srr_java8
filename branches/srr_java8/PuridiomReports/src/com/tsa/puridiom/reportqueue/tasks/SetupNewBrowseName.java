package com.tsa.puridiom.reportqueue.tasks;

import java.util.Map;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.ReportQueue;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.TsaException;

public class SetupNewBrowseName extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        Object result = null;
        Map incomingRequest = (Map) object;
        try
        {
        	String originalBrowseName = (String) incomingRequest.get("browseName");
        	String newBrowseName= "MonthlyActivityBuyer";        	
            
	        incomingRequest.put("originalBrowseName", originalBrowseName );
	        incomingRequest.put("browseName", newBrowseName );
	        
            this.setStatus(Status.SUCCEEDED);

        }
        catch (Exception e)
        {
            this.setStatus(Status.FAILED);
            e.printStackTrace();
            Log.debug(this, "Error SetupNewBrowseName");
            
        }
        return result;
    }
}