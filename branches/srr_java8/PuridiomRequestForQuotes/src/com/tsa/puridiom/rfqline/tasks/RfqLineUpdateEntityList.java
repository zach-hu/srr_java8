package com.tsa.puridiom.rfqline.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import java.util.*;

public class RfqLineUpdateEntityList extends Task {
	public Object  executeTask (Object object) throws Exception {
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try {
			List	rfqLineList = (List) incomingRequest.get("rfqLineList");
			RfqLineUpdateById rfqLineUpdateTask = new RfqLineUpdateById();
				
			for (int i=0; i < rfqLineList.size(); i++) {
				RfqLine rfqLine = (RfqLine) rfqLineList.get(i);
				
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