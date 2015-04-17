package com.tsa.puridiom.inspectionheader.tasks;

import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Dates;
import java.util.Map;

public class InspectionHeaderSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			incomingRequest.put("InspectionHeader_icInspNo", "0");
			incomingRequest.put("InspectionHeader_icMsrLine", "0");
			incomingRequest.put("InspectionHeader_inspectType", "");
			incomingRequest.put("InspectionHeader_inspectorId", "");
			incomingRequest.put("InspectionHeader_engineerId", "");
			incomingRequest.put("InspectionHeader_remoteInspId", "");
			incomingRequest.put("InspectionHeader_purchaseSpecs", "");
			incomingRequest.put("InspectionHeader_area", "");
			incomingRequest.put("InspectionHeader_storage", "");
			incomingRequest.put("InspectionHeader_location", "");
			incomingRequest.put("InspectionHeader_standardCode", "");
			incomingRequest.put("InspectionHeader_udf01", "");
			incomingRequest.put("InspectionHeader_udf02", "");
			incomingRequest.put("InspectionHeader_udf03", "");
			incomingRequest.put("InspectionHeader_udf04", "");
			incomingRequest.put("InspectionHeader_udf05", "");
			incomingRequest.put("InspectionHeader_inspType", "");
			incomingRequest.put("InspectionHeader_status", "");
			incomingRequest.put("InspectionHeader_owner", "");
			incomingRequest.put("InspectionHeader_lastChangeBy", "");
			incomingRequest.put("InspectionHeader_icRecLine","0") ;
			incomingRequest.put("InspectionHeader_poNumber", "");
			incomingRequest.put("InspectionHeader_icPoLine","0") ;
			incomingRequest.put("InspectionHeader_inspectionNumber","") ;
			incomingRequest.put("InspectionHeader_qtySignoff","0") ;
			incomingRequest.put("InspectionHeader_qtyAccepted","0") ;
			incomingRequest.put("InspectionHeader_qtyRejected","0") ;

			this.status = Status.SUCCEEDED;
		}
		catch (Exception e)
		{
			this.status = Status.FAILED;
			throw e;
		}
		return result;
	}
}