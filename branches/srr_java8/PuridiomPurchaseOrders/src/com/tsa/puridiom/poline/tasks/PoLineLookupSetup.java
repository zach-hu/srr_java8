package com.tsa.puridiom.poline.tasks;

import com.tsagate.foundation.utility.*;
import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class PoLineLookupSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		String	icHeader = (String) incomingRequest.get("icHeader");
		if (Utility.isEmpty(icHeader)) 
		{
			throw new Exception ("icHeader was not specified.");			
		}
		else 
		{
			incomingRequest.put("PoLine_icPoHeader", icHeader);
			incomingRequest.put("PoHeader_icPoHeader", icHeader);
		}

		if (!incomingRequest.containsKey("PoLine_icPoLine")) 
		{
			incomingRequest.put("PoLine_icPoLine", incomingRequest.get("icLine"));
		}
				
		this.status = Status.SUCCEEDED;
		return result;
	}
}