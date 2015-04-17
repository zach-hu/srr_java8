package com.tsa.puridiom.supplierportal.registration.tasks;

import com.tsa.puridiom.supplierportal.exception.*;
import com.tsagate.foundation.processengine.*;
import java.util.*;

public class GenerateNextVendorId extends Task {
	public Object executeTask(Object object) throws Exception {
		Map incomingRequest = (Map) object;
		Object result = null;
		
		try {
			String	vendorId = (String) incomingRequest.get("vendorId");
			int vendorIdCheckCount = 1;
			
			if (vendorId == null) {
				throw new RegistrationFatalException("Vendor Id was not set.  Cannot generate next code.");
			}
			
			try {
				vendorIdCheckCount = ((Integer) incomingRequest.get("vendorIdCheckCount")).intValue();
			}
			catch (Exception e1) {
				// vendorIdCheckCount must not have been set... use default of 1
			}
			
			vendorId = vendorId.substring(0, 5) + String.valueOf(vendorIdCheckCount - 1);
			
			vendorIdCheckCount++;
			incomingRequest.put("vendorIdCheckCount", new Integer(vendorIdCheckCount));
			
			result = vendorId;
			this.status = Status.SUCCEEDED;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
}