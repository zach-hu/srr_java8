package com.tsa.puridiom.labels.tasks;

import java.util.Map;

import com.tsa.puridiom.common.documents.GeneralStatus;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.UniqueKeyGenerator;

public class LabelsCreateSetup extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		String	organizationId = (String) incomingRequest.get("organizationId") ;
		String	userId = (String) incomingRequest.get("userId") ;
        String userTimeZone = (String) incomingRequest.get("userTimeZone");
        PropertiesManager propertiesManager = PropertiesManager.getInstance(organizationId) ;

		// Create new ic codes
		UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();
		incomingRequest.put("Labels_icLabel",ukg.getUniqueKey().toString());
		String today = Dates.today(propertiesManager.getProperty("MISC","DATEFORMAT","MM-dd-yyyy"), userTimeZone) ;
		incomingRequest.put("Labels_lastChangeDate",today) ;
		incomingRequest.put("Labels_lastChangeBy",userId) ;

		if (!incomingRequest.containsKey("Labels_owner")) {
			incomingRequest.put("Labels_owner", userId);
		}
		if (!incomingRequest.containsKey("Labels_language")) {
			incomingRequest.put("Labels_language", "EN");
		}
		if (!incomingRequest.containsKey("Labels_status")) {
			incomingRequest.put("Labels_status", GeneralStatus.STATUS_PERMANENT);
		}

		return null ;
	}

}