package com.tsa.puridiom.vendorregister.tasks;

import com.tsagate.foundation.processengine.*;

import java.util.Map;

public class VendorRegisterAttachTypeSSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Object ret = null;

		try
		{
			Map incomingRequest = (Map) object;

			incomingRequest.put("VendorDocument_docType", "S");

			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return ret;
	}
}
