package com.tsa.puridiom.supplierportal.registration.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import java.util.*;

public class GetNextVendorFromVendorList extends Task {
	
	public Object executeTask(Object object) throws Exception {
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try {
			List	vendorList = (List) incomingRequest.get("vendorList");
			
			if (vendorList != null) {
				if (vendorList.size() > 0) {
					//Get the first Vendor, then remove it from the list
					Vendor vendor = (Vendor) vendorList.get(0);
					
					result = vendor;					 
					
					vendorList.remove(0);
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