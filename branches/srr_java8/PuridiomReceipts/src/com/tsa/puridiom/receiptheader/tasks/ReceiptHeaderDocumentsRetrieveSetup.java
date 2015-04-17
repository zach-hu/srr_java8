package com.tsa.puridiom.receiptheader.tasks;

import java.util.Map;

import com.tsa.puridiom.entity.ReceiptHeader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

@SuppressWarnings(value = { "unchecked" })
public class ReceiptHeaderDocumentsRetrieveSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{

		Map incomingRequest = (Map) object;

		ReceiptHeader receiptHeader = (ReceiptHeader) incomingRequest.get("receiptHeader") ;
		if (receiptHeader == null)
		{
			this.setStatus(Status.FAILED) ;
			throw new TsaException("ReceiptHeader Entity was not found for ReceiptHeaderDocumentsRetrieveSetup!");
		}
		else
		{
			incomingRequest.put("ReceiptLine_icRecHeader", receiptHeader.getIcRecHeader().toString());
			incomingRequest.put("PoHeader_icPoHeader", receiptHeader.getIcPoHeader().toString());
			incomingRequest.put("RequisitionHeader_icReqHeader", receiptHeader.getIcReqHeader().toString());
		}
		
		this.setStatus(Status.SUCCEEDED) ;
		return null;
	}
}