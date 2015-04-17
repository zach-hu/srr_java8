package com.tsa.puridiom.rfqvendor.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import java.util.*;

public class RfqVendorUpdateEntityList extends Task {
	public Object  executeTask (Object object) throws Exception {
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try {
			List	rfqVendorList = (List) incomingRequest.get("rfqVendorList");
			RfqVendorUpdate rfqVendorUpdateTask = new RfqVendorUpdate();
				
			for (int i=0; i < rfqVendorList.size(); i++) {
			    RfqVendor rfqVendor = (RfqVendor) rfqVendorList.get(i);
				
				Map updateParameters = new HashMap();
				updateParameters.put("userId", incomingRequest.get("userId"));
				updateParameters.put("organizationId", incomingRequest.get("organizationId"));
				updateParameters.put("dbsession", incomingRequest.get("dbsession"));
				updateParameters.put("rfqVendor", rfqVendor);
				
				rfqVendor = (RfqVendor) rfqVendorUpdateTask.executeTask(updateParameters);
				
				if (rfqVendorUpdateTask.getStatus() < Status.SUCCEEDED) {
					throw new Exception("RfqVendorUpdate failed.");
				}
				
				rfqVendorList.set(i, rfqVendor);
			}

			result = rfqVendorList;			
			this.setStatus(Status.SUCCEEDED) ;
		}
		catch (Exception e) {
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}

}