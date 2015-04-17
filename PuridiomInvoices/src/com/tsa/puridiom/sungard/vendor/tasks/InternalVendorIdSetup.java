package com.tsa.puridiom.sungard.vendor.tasks;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import java.util.Map;

public class InternalVendorIdSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
		    String	nextKeyRangeLow =(String) incomingRequest.get("nextKeyRangeLow");

		    incomingRequest.put("SungardVendor_internalVendorId", nextKeyRangeLow);
		    incomingRequest.put("SungardAddress_internalVendorId", nextKeyRangeLow);
		    incomingRequest.put("SungardVendorContacts_internalVendorId", nextKeyRangeLow);
		    
			this.status = Status.SUCCEEDED;
		}
		catch (Exception e)
		{
			this.status = Status.FAILED;
			throw e;
		}
		return result;
	}
}