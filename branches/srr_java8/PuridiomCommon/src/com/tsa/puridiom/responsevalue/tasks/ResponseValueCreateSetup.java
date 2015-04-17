package com.tsa.puridiom.responsevalue.tasks;

import com.tsa.puridiom.entity.ResponseValue;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import java.util.Map;

public class ResponseValueCreateSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			String organizationId = (String)incomingRequest.get("organizationId");
			String userId = (String) incomingRequest.get("userId");
			PropertiesManager propertiesManager = PropertiesManager.getInstance(organizationId);
			incomingRequest.put("ResponseValue_icQuestion", propertiesManager.getProperty("RESPONSEVALUE DEFAULTS","icQuestion",""));
			incomingRequest.put("ResponseValue_responseValue", propertiesManager.getProperty("RESPONSEVALUE DEFAULTS","responseValue",""));
			incomingRequest.put("ResponseValue_responseText", propertiesManager.getProperty("RESPONSEVALUE DEFAULTS","responseText",""));
			incomingRequest.put("ResponseValue_weight", propertiesManager.getProperty("RESPONSEVALUE DEFAULTS","weight",""));
			incomingRequest.put("ResponseValue_rating", propertiesManager.getProperty("RESPONSEVALUE DEFAULTS","rating",""));

			ResponseValueAdd addTask = new ResponseValueAdd();
			ResponseValue responseValue = (ResponseValue) addTask.executeTask(incomingRequest);
			this.setStatus(addTask.getStatus()) ;

			result = responseValue;
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
