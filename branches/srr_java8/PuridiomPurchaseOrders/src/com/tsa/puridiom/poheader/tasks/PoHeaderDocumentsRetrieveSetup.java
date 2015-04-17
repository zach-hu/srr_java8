package com.tsa.puridiom.poheader.tasks;

import java.util.Map;

import com.tsa.puridiom.entity.PoHeader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

@SuppressWarnings(value = { "unchecked" })
public class PoHeaderDocumentsRetrieveSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{

		Map incomingRequest = (Map) object;

		PoHeader poHeader = (PoHeader) incomingRequest.get("poHeader") ;
		if (poHeader == null)
		{
			this.setStatus(Status.FAILED) ;
			throw new TsaException("PoHeader Entity was not found for PoHeaderRetrieveSetup!");
		}
		else
		{
			incomingRequest.put("PoLine_icPoHeader", poHeader.getIcPoHeader().toString());
			incomingRequest.put("RequisitionHeader_icReqHeader", poHeader.getIcReqHeader().toString());
		}
		
		this.setStatus(Status.SUCCEEDED) ;
		return null;
	}
}