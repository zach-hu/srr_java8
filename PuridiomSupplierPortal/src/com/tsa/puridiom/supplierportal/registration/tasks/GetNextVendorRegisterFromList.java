package com.tsa.puridiom.supplierportal.registration.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import java.util.*;

public class GetNextVendorRegisterFromList extends Task {
	
	public Object executeTask(Object object) throws Exception {
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try {
			List	vendorRegisterList = (List) incomingRequest.get("vendorRegisterList");
			
			if (vendorRegisterList != null) {
				if (vendorRegisterList.size() > 0) {
					//Get the first VendorRegister, then remove it from the list
					VendorRegister vendorRegister = (VendorRegister) vendorRegisterList.get(0);
					
					result = vendorRegister;					 
					
					vendorRegisterList.remove(0);
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