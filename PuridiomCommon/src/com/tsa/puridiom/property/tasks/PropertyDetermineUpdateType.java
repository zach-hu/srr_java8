package com.tsa.puridiom.property.tasks;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import java.util.*;

public class PropertyDetermineUpdateType extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {
		Map incomingRequest = (Map) object;
		Object result = "";
		
		try {
			if (incomingRequest.containsKey("Property_section") && incomingRequest.containsKey("Property_property") &&	incomingRequest.containsKey("Property_value")) {
				Object sectionObj = incomingRequest.get("Property_section");
				Object propertyObj = incomingRequest.get("Property_property");
				Object valueObj = incomingRequest.get("Property_value");
				
				if (sectionObj instanceof String && propertyObj instanceof String && valueObj instanceof String) {
					result = "single";
				}
				else {
					result = "multiple";
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