package com.tsa.puridiom.poheader.tasks;

import java.util.Map;

import com.tsa.puridiom.entity.PoHeader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

public class PoHeaderReleaseSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
		    PoHeader poHeader = (PoHeader)incomingRequest.get("poHeader");
			incomingRequest.put("historyIc", poHeader.getIcHeaderHistory().toString());
			//incomingRequest.put("blanketIc", poHeader.getIcPoHeader().toString());
			String poType = (String)incomingRequest.get("PoHeader_poType");
			incomingRequest.put("releaseType", poType);
			incomingRequest.put("release_poNumber", poHeader.getPoNumber());
			incomingRequest.put("release_releaseNumber", poHeader.getLastRelease());

			this.status = Status.SUCCEEDED;
		}
		catch (Exception e)
		{
			this.status = Status.FAILED;
			throw e;
		}
		return result;
	}
}