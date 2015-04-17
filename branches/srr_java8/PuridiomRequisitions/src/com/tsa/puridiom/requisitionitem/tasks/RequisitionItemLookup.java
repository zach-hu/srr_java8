package com.tsa.puridiom.requisitionitem.tasks;

import com.tsagate.foundation.utility.*;
import com.tsagate.foundation.processengine.*;
import java.util.*;

public class RequisitionItemLookup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		Object icReqLineObj = incomingRequest.get("RequisitionLine_icReqLine");
		Object quantityObj = incomingRequest.get("quantity");
		PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
		PuridiomProcess process = processLoader.loadProcess("requisitionitem-lookup-by-id.xml");

		String	icHeader = (String) incomingRequest.get("icHeader");
			
		if (Utility.isEmpty(icHeader)) {
			throw new Exception("The ic header was not found.");
		}
				
		if (icReqLineObj instanceof String[]) {
			String	icReqLines[] = (String[]) icReqLineObj;
			String	quantities[] = (String[]) quantityObj;
			
			for (int i=0; i < icReqLines.length; i++) {
				String	icReqLine = icReqLines[i];
				String	quantity = quantities[i];
				
				Map updateParameters = new HashMap();
				updateParameters.put("userId", incomingRequest.get("userId"));
				updateParameters.put("userTimeZone", incomingRequest.get("userTimeZone"));
				updateParameters.put("organizationId", incomingRequest.get("organizationId"));
				updateParameters.put("dbsession", incomingRequest.get("dbsession"));
				updateParameters.put("icHeader", icHeader);
				updateParameters.put("formtype", incomingRequest.get("formtype"));
				updateParameters.put("RequisitionLine_icReqLine", icReqLine);
				updateParameters.put("quantity", quantity);
				updateParameters.put("createAction", "SAVE");

				process.executeProcess(updateParameters);
				
				if (process.getStatus() < Status.COMPLETED) {
					throw new Exception("RequisitionItemLookup failed.  (RequisitionLine_icReqLine = " + icReqLine);
				}
			}
		}
		else {
			incomingRequest.put("createAction", "SAVE");
			process.executeProcess(incomingRequest);
				
			if (process.getStatus() < Status.COMPLETED) {
				throw new Exception("RequisitionItemLookup failed.");
			}
		}
					
		this.status = Status.SUCCEEDED;
		return result;
	}
}