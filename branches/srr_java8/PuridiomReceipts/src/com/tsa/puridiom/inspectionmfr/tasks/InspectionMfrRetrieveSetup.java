package com.tsa.puridiom.inspectionmfr.tasks;

import com.tsa.puridiom.entity.InspectionHeader;
import com.tsagate.foundation.processengine.Task;
import java.util.Map;

@SuppressWarnings(value = { "unchecked" })
public class InspectionMfrRetrieveSetup extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null ;

		InspectionHeader inspectionHeader = (InspectionHeader) incomingRequest.get("inspectionHeader") ;
		incomingRequest.put("InspectionMfr_icInspNo", inspectionHeader.getComp_id().getIcInspNo().toString()) ;

		return result ;
	}
}