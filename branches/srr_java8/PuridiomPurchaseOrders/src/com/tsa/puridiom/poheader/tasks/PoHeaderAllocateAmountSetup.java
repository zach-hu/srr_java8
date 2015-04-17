package com.tsa.puridiom.poheader.tasks;

import java.util.*;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

public class PoHeaderAllocateAmountSetup extends Task 
{
	public Object executeTask(Object object) throws Exception 
	{

		Map incomingRequest = (Map)object;

		try 
		{
			String icHeader = (String)incomingRequest.get("Account_icHeader") ;
			String icLine = (String)incomingRequest.get("Account_icLine") ;
			incomingRequest.put("PoHeader_icPoHeader",icHeader) ;
	        incomingRequest.put("PoHeader_icPoLine",icLine) ;
	        this.setStatus(Status.SUCCEEDED) ;
		}
		catch(Exception e) 
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
        
		return null  ;
	}
	
}
