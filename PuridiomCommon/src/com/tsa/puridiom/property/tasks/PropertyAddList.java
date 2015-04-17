package com.tsa.puridiom.property.tasks;

import com.tsa.puridiom.property.PropertiesManager;
import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import java.util.List;
import java.util.Map;

public class PropertyAddList extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		Object result = new Object();

		DBSession dbs = (DBSession) incomingRequest.get("dbsession") ;
		PropertiesManager properties = PropertiesManager.getInstance((String) incomingRequest.get("organizationId"));
		List propertyList = properties.getPropertiesToAdd();
		
		for (int i=0; i < propertyList.size(); i++)
		{
			dbs.add((Property) propertyList.get(i));
		}
		
		this.setStatus(dbs.getStatus()) ;
		
		if (this.getStatus() == Status.SUCCEEDED)
		{
			properties.clearPropertiesToAdd();
		}
		
		return result;
	}

}