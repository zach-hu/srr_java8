package com.tsa.puridiom.rfqline.tasks;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import java.util.*;

public class RfqLineUpdateEntityListStatus extends Task {
	public Object  executeTask (Object object) throws Exception {
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try {
			List	rfqLineList = (List) incomingRequest.get("rfqLineList");
			String	status = (String) incomingRequest.get("RfqLine_status");
			RfqLineUpdateById rfqLineUpdateTask = new RfqLineUpdateById();
			
			if (!HiltonUtility.isEmpty(status)) {
				for (int i=0; i < rfqLineList.size(); i++) {
					RfqLine rfqLine = (RfqLine) rfqLineList.get(i);
					
					rfqLine.setStatus(status);
					
					Map updateParameters = new HashMap();
					updateParameters.put("userId", incomingRequest.get("userId"));
					updateParameters.put("organizationId", incomingRequest.get("organizationId"));
					updateParameters.put("dbsession", incomingRequest.get("dbsession"));
					updateParameters.put("rfqLine", rfqLine);
					
					rfqLineUpdateTask.executeTask(updateParameters);
					
					if (rfqLineUpdateTask.getStatus() < Status.SUCCEEDED) {
						throw new Exception("RfqLineUpdateById failed.");
					}
				}
			}

			result = rfqLineList;			
			this.setStatus(Status.SUCCEEDED) ;
		}
		catch (Exception e) {
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}

}