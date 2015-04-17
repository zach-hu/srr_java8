package com.tsa.puridiom.rfqline.tasks;

import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Utility;
import java.util.*;

public class RfqLineCreateFromRequisitionLineList extends Task {

	public Object  executeTask (Object object) throws Exception {
		Map incomingRequest = (Map)object;
		
		try {
			Object result = null;
			Object icReqLineObj = incomingRequest.get("RequisitionLine_icReqLine");
			Object quantityObj = incomingRequest.get("quantity");
			String	icHeader = (String) incomingRequest.get("icHeader");
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
			PuridiomProcess process = processLoader.loadProcess("rfqline-create-from-requisition-line.xml");
			
			if (Utility.isEmpty(icHeader)) {
				throw new Exception("The ic header was not found.");
			}
				
			if (icReqLineObj instanceof String[]) {
				String	icReqLineValues[] = (String[]) icReqLineObj;
				String	quantities[] = (String[]) quantityObj;
			
				for (int i=0; i < icReqLineValues.length; i++) {
					String	icReqLine = icReqLineValues[i];
					String	quantity = quantities[i];
				
					Map updateParameters = new HashMap();
					updateParameters.put("userId", incomingRequest.get("userId"));
					updateParameters.put("organizationId", incomingRequest.get("organizationId"));
					updateParameters.put("dbsession", incomingRequest.get("dbsession"));
					updateParameters.put("rfqHeader", incomingRequest.get("rfqHeader"));
					updateParameters.put("RfqHeader_icRfqHeader", icHeader);
					updateParameters.put("RfqLine_icRfqHeader", icHeader);
					updateParameters.put("RfqLine_quantity", quantity);
					updateParameters.put("RequisitionLine_icReqLine", icReqLine);
					updateParameters.put("createAction", "SAVE");

					process.executeProcess(updateParameters);
				
					if (process.getStatus() < Status.COMPLETED) {
						throw new Exception("CreateFromRequisitionLines failed.  (RequisitionLine_icReqLine = " + icReqLine + "; icHeader: " + icHeader);
					}
				}
			}
			else {
				incomingRequest.put("createAction", "SAVE");
				process.executeProcess(incomingRequest);
				
				if (process.getStatus() < Status.COMPLETED) {
					throw new Exception("CreateFromRequisitionLines failed.");
				}
			}
			
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e) {
			this.setStatus(Status.FAILED);
			throw e;
		}
		
		return null ;
	}

}