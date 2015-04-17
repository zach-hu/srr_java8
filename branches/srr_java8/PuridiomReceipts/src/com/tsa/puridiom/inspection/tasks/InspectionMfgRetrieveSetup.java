package com.tsa.puridiom.inspection.tasks;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsagate.foundation.processengine.Task;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class InspectionMfgRetrieveSetup extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		String icHeader = (String) incomingRequest.get("InspectionHeader_icInspNo") ;
		String icLine = (String) incomingRequest.get("InspectionHeader_icMsrLine") ;
		BigDecimal icElementHeader = new BigDecimal(icHeader) ;
		BigDecimal icElementLine = new BigDecimal(icLine) ;

		incomingRequest.put("ElementData_formId", "MFG") ;
		incomingRequest.put("ElementData_icHeader", icHeader) ;
		incomingRequest.put("ElementData_icLine", icLine) ;

		return result;
	}
}