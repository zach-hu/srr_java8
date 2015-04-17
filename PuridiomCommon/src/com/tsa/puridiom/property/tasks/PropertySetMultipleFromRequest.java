package com.tsa.puridiom.property.tasks;

import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import java.util.*;

public class PropertySetMultipleFromRequest extends Task
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
			Object sectionObj = incomingRequest.get("Property_section");
			String	propertyArray[] = (String[]) incomingRequest.get("Property_property");
			String	valueArray[] = (String[]) incomingRequest.get("Property_value");
			String	sectionArray[] = new String[valueArray.length];
			String	sectionValue = "";
			
			if (sectionObj instanceof String[])
			{
				sectionArray = (String[]) incomingRequest.get("Property_section");
			}
			else
			{
				sectionValue = (String) incomingRequest.get("Property_section");
			
				for (int i=0; i < sectionArray.length; i++)
				{
					sectionArray[i] = sectionValue;
				}
			}
			
			PropertiesManager properties = PropertiesManager.getInstance((String) incomingRequest.get("organizationId")); 

			for (int i=0; i < valueArray.length; i++)
			{
				properties.setProperty(sectionArray[i], propertyArray[i], valueArray[i]);
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