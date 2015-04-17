package com.tsa.puridiom.requisitionheader.tasks;

import com.tsagate.foundation.utility.*;
import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class RequisitionHeaderSetupIcHeader extends Task
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
			incomingRequest.put("RequisitionHeader_icReqHeader", icHeader);
		}
				
		this.status = Status.SUCCEEDED;
		return result;
	}
}