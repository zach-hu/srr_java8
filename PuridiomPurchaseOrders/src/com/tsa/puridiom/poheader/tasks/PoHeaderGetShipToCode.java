package com.tsa.puridiom.poheader.tasks;

import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class PoHeaderGetShipToCode extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			String shipToCode = (String) incomingRequest.get("PoHeader_shipToCode");

			result = shipToCode;
			
			this.setStatus(Status.SUCCEEDED) ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}

}