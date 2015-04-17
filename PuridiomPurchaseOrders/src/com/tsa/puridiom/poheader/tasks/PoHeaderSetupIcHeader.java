package com.tsa.puridiom.poheader.tasks;
import java.util.Map;

import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.*;

public class PoHeaderSetupIcHeader extends Task 
{

	public Object executeTask(Object object) throws Exception 
	{
		Map incomingRequest = (Map)object;
		try
		{
			String	icHeader = (String) incomingRequest.get("icHeader");
			if (Utility.isEmpty(icHeader)) 
			{
				throw new Exception ("icHeader was not specified.");			
			}
			else 
			{
				incomingRequest.put("PoHeader_icPoHeader", icHeader);
			}
			
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException(this.getName(), e);
		}
		
		return null ;
	}
}