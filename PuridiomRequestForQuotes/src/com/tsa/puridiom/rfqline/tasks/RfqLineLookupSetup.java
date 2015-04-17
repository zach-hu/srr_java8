package com.tsa.puridiom.rfqline.tasks;

import com.tsagate.foundation.utility.*;
import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class RfqLineLookupSetup extends Task
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
			incomingRequest.put("RfqLine_icRfqHeader", icHeader);
			incomingRequest.put("RfqHeader_icRfqHeader", icHeader);
		}

		if (!incomingRequest.containsKey("RfqLine_icRfqLine")) {
			incomingRequest.put("RfqLine_icRfqLine", incomingRequest.get("icLine"));
		}
				
		this.status = Status.SUCCEEDED;
		return result;
	}
}