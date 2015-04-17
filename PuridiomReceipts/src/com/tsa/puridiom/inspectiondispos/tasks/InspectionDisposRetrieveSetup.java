package com.tsa.puridiom.inspectiondispos.tasks;

import com.tsagate.foundation.processengine.Task;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class InspectionDisposRetrieveSetup extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null ;

		incomingRequest.put("InspectionDispos_icRecHeader",incomingRequest.get("ReceiptLine_icRecHeader")) ;
		incomingRequest.put("InspectionDispos_icRecLine",incomingRequest.get("ReceiptLine_icRecLine")) ;

		return result ;
	}
}