package com.tsa.puridiom.vendordocument.tasks;

import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.UniqueKeyGenerator;

import java.util.Map;

public class VendorDocumentAddSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
		    UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();
		    incomingRequest.put("VendorDocument_docIc",ukg.getUniqueKey().toString());
		    
		    if (!incomingRequest.containsKey("VendorDocument_icRfqHeader")) {
		        incomingRequest.put("VendorDocument_icRfqHeader", "0");
		    }
		    if (!incomingRequest.containsKey("VendorDocument_vendorId")) {
		        incomingRequest.put("VendorDocument_vendorId", "");
		    }
			if (!incomingRequest.containsKey("VendorDocument_docTitle")) {
			    incomingRequest.put("VendorDocument_docTitle", "");
			}
			if (!incomingRequest.containsKey("VendorDocument_docFilename")) {
			    incomingRequest.put("VendorDocument_docFilename", "");
			}
			if (!incomingRequest.containsKey("VendorDocument_datePosted")) {
			    incomingRequest.put("VendorDocument_datePosted", Dates.today("yyyy-MM-dd"));
			}
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