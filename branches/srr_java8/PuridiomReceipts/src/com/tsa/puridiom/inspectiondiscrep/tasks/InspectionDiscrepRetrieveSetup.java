package com.tsa.puridiom.inspectiondiscrep.tasks;

import com.tsa.puridiom.entity.InspectionHeader;
import com.tsagate.foundation.processengine.Task;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class InspectionDiscrepRetrieveSetup extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null ;

		InspectionHeader ih = (InspectionHeader) incomingRequest.get("inspectionHeader") ;
		if(incomingRequest.containsKey("ReceiptLine_icRecHeader")){
			incomingRequest.put("InspectionDiscrep_icRecHeader",incomingRequest.get("ReceiptLine_icRecHeader")) ;
		} else {
			incomingRequest.put("InspectionDiscrep_icRecHeader",incomingRequest.get("ReceiptHeader_icRecHeader")) ;
		}
		incomingRequest.put("InspectionDiscrep_icRecLine",incomingRequest.get("ReceiptLine_icRecLine")) ;
		incomingRequest.put("InspectionDiscrep_icInspNo", ih.getComp_id().getIcInspNo().toString()) ;
//		incomingRequest.put("StdTable_tableType","RECSTAT") ;
		incomingRequest.put("StdTable_tableType","DISC") ;


		return result ;
	}
}