package com.tsa.puridiom.poline.tasks;

import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.poline.exceptions.PoLineNotFoundException;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.TsaException;

import java.util.*;

public class PoLineUpdateList extends Task 
{
	public Object  executeTask (Object object) throws Exception 
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try 
		{
			List	poLines = (List)incomingRequest.get("poLines");
			if(poLines == null)
			{
			    poLines = (List)incomingRequest.get("poLineList");
			}
			if(poLines == null)
			{
			    this.setStatus(Status.FAILED);
			    throw new PoLineNotFoundException(this.getName() + "- List of Po Lines was not found");
			}
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
			PuridiomProcess process = processLoader.loadProcess("poline-update-norecalc.xml");
				
			for (int i=0; i < poLines.size(); i++) 
			{
				PoLine poLine = (PoLine) poLines.get(i);

				incomingRequest.put("poLine", poLine);
				
				process.executeProcess(incomingRequest);
				
				if (process.getStatus() < Status.SUCCEEDED) 
				{
					throw new Exception("Po Line save as process failed.");
				}
			}

			result = poLines;			
			this.setStatus(Status.SUCCEEDED) ;
		}
		catch (Exception e) 
		{
			this.setStatus(Status.FAILED);
			throw new TsaException(this.getName(), e);
		}
		return result;
	}
}