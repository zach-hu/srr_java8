package com.tsa.puridiom.receipt.tasks;

import com.tsagate.foundation.processengine.Task;
import java.util.Map;

public class ReceiptCreateReturnSetup extends Task{

	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;

		incomingRequest.put("ReceiptHeader_receiptType", "R");
		
		return null ;
	}

}