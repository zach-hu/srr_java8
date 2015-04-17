package com.tsa.puridiom.vendorregistration.tasks;

import com.tsa.puridiom.vendorregistration.RegisterUser;
import com.tsa.puridiom.vendor.VendorManager;
import com.tsa.puridiom.vendorregistration.exception.*;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import java.util.Map;

public class RegisterUserVendorNameStrip extends Task {
	public Object executeTask(Object object) throws Exception {
		Map incomingRequest = (Map) object;
		Object result = null;

		try {
			RegisterUser registerUser = (RegisterUser) incomingRequest.get("registerUser");

			if (registerUser == null) {
				throw new RegistrationException("Vendor registration information was not found.");
			}

			String name = registerUser.getVendorName();
			
			result = VendorManager.getInstance().stripVendorName(name);
			
			this.status = Status.SUCCEEDED;
		} catch (Exception e) {
			this.status = Status.FAILED;
			throw e;
		}
		return result;
	}
}