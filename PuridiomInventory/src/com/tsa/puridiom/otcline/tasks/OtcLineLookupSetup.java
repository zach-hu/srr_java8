package com.tsa.puridiom.otcline.tasks;

import com.tsagate.foundation.utility.*;
import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class OtcLineLookupSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		String	icHeader = (String) incomingRequest.get("icHeader");
		if (Utility.isEmpty(icHeader)) 
		{
			this.setStatus(Status.FAILED);
			throw new Exception ("icHeader was not specified.");			
		}
		else 
		{
			incomingRequest.put("DisbLine_icDsbHeader", icHeader);
			incomingRequest.put("DisbHeader_icDsbHeader", icHeader);
		}

		if (!incomingRequest.containsKey("DisbLine_icDsbLine")) 
		{
			incomingRequest.put("DisbLine_icDsbLine", incomingRequest.get("icLine"));
		}
				
		this.status = Status.SUCCEEDED;
		return result;
	}
}