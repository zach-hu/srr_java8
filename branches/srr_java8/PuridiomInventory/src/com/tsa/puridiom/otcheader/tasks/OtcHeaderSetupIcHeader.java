package com.tsa.puridiom.otcheader.tasks;

import com.tsagate.foundation.utility.*;
import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class OtcHeaderSetupIcHeader extends Task
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
			incomingRequest.put("DisbHeader_icDsbHeader", icHeader);
		}
				
		this.status = Status.SUCCEEDED;
		return result;
	}
}