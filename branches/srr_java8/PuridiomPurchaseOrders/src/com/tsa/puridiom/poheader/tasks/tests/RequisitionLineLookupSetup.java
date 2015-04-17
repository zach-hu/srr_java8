package com.tsa.puridiom.poheader.tasks.tests;

import com.tsagate.foundation.utility.*;
import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class RequisitionLineLookupSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		String	icHeader = (String) incomingRequest.get("icHeader");
		if (Utility.isEmpty(icHeader)) {
			throw new Exception ("icHeader was not specified.");			
		}
		else {
			incomingRequest.put("RequisitionLine_icReqHeader", icHeader);
			incomingRequest.put("RequisitionHeader_icReqHeader", icHeader);
		}

		if (!incomingRequest.containsKey("RequisitionLine_icReqLine")) {
			incomingRequest.put("RequisitionLine_icReqLine", incomingRequest.get("icLine"));
		}
				
		this.status = Status.SUCCEEDED;
		return result;
	}
}