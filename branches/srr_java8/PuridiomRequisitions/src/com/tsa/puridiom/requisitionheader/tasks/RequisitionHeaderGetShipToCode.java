package com.tsa.puridiom.requisitionheader.tasks;

import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class RequisitionHeaderGetShipToCode extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			String shipToCode = (String) incomingRequest.get("RequisitionHeader_shipToCode");

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