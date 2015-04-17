package com.tsa.puridiom.requisitionheader.tasks;

import java.util.Map;

import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

@SuppressWarnings(value = { "unchecked" })
public class MsrHeaderDocumentsRetrieveSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{

		Map incomingRequest = (Map) object;

		RequisitionHeader msrHeader = (RequisitionHeader) incomingRequest.get("msrHeader") ;
		if (msrHeader == null)
		{
			this.setStatus(Status.FAILED) ;
			throw new TsaException("MsrHeader Entity was not found for MsrHeaderDocumentsRetrieveSetup!");
		}
		else
		{
			incomingRequest.put("MsrLine_icReqHeader", msrHeader.getIcReqHeader().toString());
		}
		
		this.setStatus(Status.SUCCEEDED) ;
		return null;
	}
}