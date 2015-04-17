package com.tsa.puridiom.vendorregister.tasks;

import com.tsagate.foundation.processengine.*;
import java.util.*;

public class VendorRegisterContactValidationSetup extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			incomingRequest.put("Labels_moduleAccess", "SUPPLIERPORTALCONTACT");
			incomingRequest.put("validationType", "vendorregistration");
			
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