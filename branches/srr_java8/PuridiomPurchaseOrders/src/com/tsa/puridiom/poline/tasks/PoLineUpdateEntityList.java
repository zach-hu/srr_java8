package com.tsa.puridiom.poline.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.TsaException;

import java.util.*;

public class PoLineUpdateEntityList extends Task 
{
	public Object  executeTask (Object object) throws Exception 
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try 
		{
			List	poLineList = (List) incomingRequest.get("poLineList");
			
				
			for (int i=0; i < poLineList.size(); i++) 
			{
				PoLine poLine = (PoLine) poLineList.get(i);
				
				Map updateParameters = new HashMap();
				updateParameters.put("userId", incomingRequest.get("userId"));
				updateParameters.put("userTimeZone", incomingRequest.get("userTimeZone"));
				updateParameters.put("organizationId", incomingRequest.get("organizationId"));
				updateParameters.put("dbsession", incomingRequest.get("dbsession"));
				updateParameters.put("poLine", poLine);
				
				PoLineUpdateById task = new PoLineUpdateById();
				task.executeTask(updateParameters);
				
				if (task.getStatus() < Status.SUCCEEDED) 
				{
					throw new TsaException(this.getName() + " failed!");
				}
				poLineList.set(i, poLine);
			}

			result = poLineList;			
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