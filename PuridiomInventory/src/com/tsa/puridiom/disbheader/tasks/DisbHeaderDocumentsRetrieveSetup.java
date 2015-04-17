package com.tsa.puridiom.disbheader.tasks;

import java.util.Map;

import com.tsa.puridiom.entity.DisbHeader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

@SuppressWarnings(value = { "unchecked" })
public class DisbHeaderDocumentsRetrieveSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{

		Map incomingRequest = (Map) object;

		DisbHeader disbHeader = (DisbHeader) incomingRequest.get("disbHeader") ;
		if (disbHeader == null)
		{
			this.setStatus(Status.FAILED) ;
			throw new TsaException("DisbHeader Entity was not found for DisbHeaderDocumentsRetrieveSetup!");
		}
		else
		{
			incomingRequest.put("DisbLine_icDsbHeader", disbHeader.getIcDsbHeader().toString());
		}
		
		this.setStatus(Status.SUCCEEDED) ;
		return null;
	}
}