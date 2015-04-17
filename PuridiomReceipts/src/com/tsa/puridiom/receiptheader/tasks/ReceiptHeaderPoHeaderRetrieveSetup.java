package com.tsa.puridiom.receiptheader.tasks;

import java.util.Map;

import com.tsa.puridiom.entity.ReceiptHeader;
import com.tsagate.foundation.processengine.Task;

public class ReceiptHeaderPoHeaderRetrieveSetup extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		ReceiptHeader receiptHeader = (ReceiptHeader) incomingRequest.get("receiptHeader") ;
		if (receiptHeader != null) incomingRequest.put("PoHeader_icPoHeader", receiptHeader.getIcPoHeader().toString()) ;

		return result;
	}

}