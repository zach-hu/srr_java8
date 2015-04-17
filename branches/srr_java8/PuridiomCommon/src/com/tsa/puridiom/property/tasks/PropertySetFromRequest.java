package com.tsa.puridiom.property.tasks;

import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import java.util.*;

public class PropertySetFromRequest extends Task
{
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		Object result = null;
		
		try
		{
			String	propertyValue = (String) incomingRequest.get("Property_property");
			String	valueValue = (String) incomingRequest.get("Property_value");
			String	sectionValue = (String) incomingRequest.get("Property_section");

			PropertiesManager.getInstance((String) incomingRequest.get("organizationId")).setProperty(sectionValue, propertyValue, valueValue);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
			
		this.setStatus(Status.SUCCEEDED) ;
					
		return result ;
	}	
}