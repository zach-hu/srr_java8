package com.tsa.puridiom.poline.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.TsaException;

import java.util.*;

public class PoLineSetPoNumberFromHeader extends Task 
{
	public Object  executeTask (Object object) throws Exception 
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try 
		{
			List	poLineList = (List) incomingRequest.get("poLineList");
			String poNumber = (String) incomingRequest.get("PoHeader_poNumber");
			
			for (int i=0; i < poLineList.size(); i++) 
			{
				PoLine poLine = (PoLine) poLineList.get(i);
				poLine.setPoNumber(poNumber);
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