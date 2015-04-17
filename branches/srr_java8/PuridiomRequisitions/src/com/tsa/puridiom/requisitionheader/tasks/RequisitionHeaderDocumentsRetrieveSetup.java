package com.tsa.puridiom.requisitionheader.tasks;

import java.util.Map;

import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

@SuppressWarnings(value = { "unchecked" })
public class RequisitionHeaderDocumentsRetrieveSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{

		Map incomingRequest = (Map) object;

		RequisitionHeader requisitionHeader = (RequisitionHeader) incomingRequest.get("requisitionHeader") ;
		if (requisitionHeader == null)
		{
			this.setStatus(Status.FAILED) ;
			throw new TsaException("PoHeader Entity was not found for PoHeaderRetrieveSetup!");
		}
		else
		{
			incomingRequest.put("RequisitionLine_icReqHeader", requisitionHeader.getIcReqHeader().toString());
			incomingRequest.put("MsrHeader_icReqHeader", requisitionHeader.getIcMsrHeader().toString());
		}
		
		this.setStatus(Status.SUCCEEDED) ;
		return null;
	}
}