package com.tsa.puridiom.supplierportal.registration.tasks;

import com.tsa.puridiom.supplierportal.exception.*;
import com.tsagate.foundation.utility.*;
import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class ValidateNewVendorIdSetup extends Task {
	
	public Object executeTask(Object object) throws Exception {
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try {
			String	vendorId = (String) incomingRequest.get("vendorId");
			if (Utility.isEmpty(vendorId)) {
				this.status = Status.FAILED;
				throw new RegistrationException ("Vendor Id cannot be empty.");			
			}
			else {
				incomingRequest.put("Vendor_vendorId", vendorId);
				incomingRequest.put("VendorRegister_vendorId", vendorId);
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