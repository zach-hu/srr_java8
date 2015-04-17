package com.tsa.puridiom.vendordocument.tasks;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import java.util.Map;

public class VendorDocumentDeleteByIdSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
		    String	filename = (String) incomingRequest.get("VendorDocument_docFilename");
		    
		    incomingRequest.put("documentType", "external");
		    incomingRequest.put("filename", filename);
		    
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