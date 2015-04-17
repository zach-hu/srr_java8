package com.tsa.puridiom.property.tasks;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.ConditionTask;
import java.util.*;

public class PropertyIsMultipleUpdate extends ConditionTask
{
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public boolean executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		boolean result = false;
		
		try
		{
			if (incomingRequest.containsKey("Property_section") && incomingRequest.containsKey("Property_property") && 
				incomingRequest.containsKey("Property_value"))
			{
				Object propertyObj = incomingRequest.get("Property_property");
				Object valueObj = incomingRequest.get("Property_value");
				
				if (propertyObj instanceof String[] && valueObj instanceof String[])
				{
					result = true;
				}
			}
		}
		catch (Exception e) {
			this.setStatus(Status.FAILED);
			throw e;
		}
			
		this.setStatus(Status.SUCCEEDED) ;
					
		return result ;
	}
}