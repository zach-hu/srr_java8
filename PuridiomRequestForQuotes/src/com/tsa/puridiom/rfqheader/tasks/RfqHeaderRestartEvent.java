package com.tsa.puridiom.rfqheader.tasks;

import com.tsa.puridiom.entity.RfqHeader;
import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class RfqHeaderRestartEvent extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			RfqHeader rfqHeader = (RfqHeader) incomingRequest.get("rfqHeader");
			if (rfqHeader == null)
			{
				rfqHeader = new RfqHeader();			
			}
			
			rfqHeader.setEventPaused("N");
			
			this.status = Status.SUCCEEDED;	
			result = rfqHeader;
		}
		catch (Exception e)
		{
			this.status = Status.FAILED;
			throw e;
		}
				
		return result;
	}
}