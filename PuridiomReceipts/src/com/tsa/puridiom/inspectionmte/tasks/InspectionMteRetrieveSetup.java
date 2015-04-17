package com.tsa.puridiom.inspectionmte.tasks;

import com.tsagate.foundation.processengine.Task;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class InspectionMteRetrieveSetup extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null ;

		incomingRequest.put("InspectionMte_icRecHeader",incomingRequest.get("ReceiptLine_icRecHeader")) ;
		incomingRequest.put("InspectionMte_icRecLine",incomingRequest.get("ReceiptLine_icRecLine")) ;

		return result ;
	}
}