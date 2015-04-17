package com.tsa.puridiom.supplierportal.registration.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import java.util.*;

public class ValidateVendorListVendorEin extends Task {
	
	public Object executeTask(Object object) throws Exception {
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try {
			List	vendorList = (List) incomingRequest.get("vendorList");
			
			if (vendorList != null) {
				for (int i=0; i < vendorList.size(); i++) {
					Vendor vendor = (Vendor) vendorList.get(i); 
					incomingRequest.put("vendor", vendor);

					
				}
			}
					
			this.status = Status.SUCCEEDED;
		}
		catch (Exception e) {
			this.status = Status.FAILED;
			throw e;
		}
		return result;
	}
}