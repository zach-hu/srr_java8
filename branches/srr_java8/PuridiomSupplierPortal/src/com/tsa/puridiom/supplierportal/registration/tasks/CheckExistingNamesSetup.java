package com.tsa.puridiom.supplierportal.registration.tasks;

import com.tsa.puridiom.supplierportal.exception.*;
import com.tsagate.foundation.utility.*;
import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class CheckExistingNamesSetup extends Task {
	
	public Object executeTask(Object object) throws Exception {
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try {
			String	vendorName = (String) incomingRequest.get("vendorName");

			if (Utility.isEmpty(vendorName)) {
				this.status = Status.FAILED;
				throw new RegistrationException ("Vendor Name cannot be empty.");			
			}

			incomingRequest.put("Vendor_vendorName", vendorName);
			incomingRequest.put("VendorRegister_vendorName", vendorName);
					
			this.status = Status.SUCCEEDED;
		}
		catch (Exception e) {
			this.status = Status.FAILED;
			throw e;
		}
		return result;
	}
}