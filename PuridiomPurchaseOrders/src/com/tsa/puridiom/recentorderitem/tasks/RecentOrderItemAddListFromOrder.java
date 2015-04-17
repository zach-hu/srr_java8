package com.tsa.puridiom.recentorderitem.tasks;

import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.entity.PoLine;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.Utility;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecentOrderItemAddListFromOrder extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
		    PoHeader poHeader = (PoHeader) incomingRequest.get("poHeader");
		    List	lineList = (List) incomingRequest.get("poLineList");
		    
		    if (lineList == null) {
		        throw new Exception ("Recent Order Items cannot be added.  PoLineList was not found.");
		    }
		    
		    Map requestParameters = new HashMap();
		    
		    requestParameters.put("dbsession", incomingRequest.get("dbsession"));
		    requestParameters.put("userId", incomingRequest.get("userId"));
		    requestParameters.put("userTimeZone", incomingRequest.get("userTimeZone"));
		    requestParameters.put("organizationId", incomingRequest.get("organizationId"));
		    requestParameters.put("poHeader", incomingRequest.get("poHeader"));
		    
		    for (int i = 0; i < lineList.size(); i++) {
		        PoLine poLine = (PoLine) lineList.get(i);
		        if (!Utility.isEmpty(poLine.getItemNumber())) {
			        requestParameters.put("poLine", poLine);
			        
			        PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
					PuridiomProcess process = processLoader.loadProcess("recentorderitem-add.xml");
	
					process.executeProcess(requestParameters);
					
					if (process.getStatus() < Status.SUCCEEDED) {
					    // Continue anyway... do not stop process and display error to user
					    Log.error(this, "RecentOrderItem Add process failed.");
					}
		        }
		    }
		}
		catch (Exception e)
		{
			//this.status = Status.FAILED;
			//throw e;
		    // Continue anyway... do not stop process and display error to user
		    Log.error(this, e.getStackTrace().toString());
		}
		finally
		{
			this.status = Status.SUCCEEDED;
		}
		return result;
	}
}