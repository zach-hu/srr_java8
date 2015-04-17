package com.tsa.puridiom.supplierportal.registration.tasks;

import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class ResetTempVendorId extends Task {
	
	public Object executeTask(Object object) throws Exception {
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try {
			incomingRequest.put("vendorId", "");
					
			this.status = Status.SUCCEEDED;
		}
		catch (Exception e) {
			this.status = Status.FAILED;
			throw e;
		}
		return result;
	}
}