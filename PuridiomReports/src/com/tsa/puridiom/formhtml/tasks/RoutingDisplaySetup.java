package com.tsa.puridiom.formhtml.tasks;

import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsagate.foundation.processengine.Task;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

public class RoutingDisplaySetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		String icHeader = (String) incomingRequest.get("parameterValues[0]");

		incomingRequest.put("ApprovalLog_icHeader", icHeader);

		if (!incomingRequest.containsKey("status")) {
			 String entityNameFull   = (String) incomingRequest.get("entityNameFull");
			Class entityHeader = Class.forName(entityNameFull);
     		Object entityHeaderInstance = entityHeader.newInstance();

     		entityHeaderInstance = incomingRequest.get("parameterValues[0]");
		    if (entityHeaderInstance != null) {
		    	String status = (String) incomingRequest.get("status");
		    	Method getStatus = entityHeader.getMethod(status, null);
	     		incomingRequest.put("status", getStatus);
		    }
		}

		return null ;
	}
}
