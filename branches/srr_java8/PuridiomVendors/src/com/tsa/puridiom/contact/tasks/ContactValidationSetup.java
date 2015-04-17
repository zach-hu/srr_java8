package com.tsa.puridiom.contact.tasks;

import com.tsagate.foundation.processengine.*;
import java.util.*;

public class ContactValidationSetup extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			incomingRequest.put("Labels_moduleAccess", "SUPPLIERCONTACT");
			
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