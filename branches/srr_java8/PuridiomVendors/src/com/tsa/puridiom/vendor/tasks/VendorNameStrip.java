package com.tsa.puridiom.vendor.tasks;

import com.tsa.puridiom.vendor.VendorManager;
import com.tsa.puridiom.vendorregistration.exception.*;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import java.util.Map;

public class VendorNameStrip extends Task {
	public Object executeTask(Object object) throws Exception {
		Map incomingRequest = (Map) object;
		Object result = null;

		try {
			String name = (String) incomingRequest.get("Vendor_vendorName");

			if (name == null) {
				throw new RegistrationException("Vendor name [vendorName] was not found.");
			}

			incomingRequest.put("originalVendor_vendorName", name);
			
			result = VendorManager.getInstance().stripVendorName(name);
			
			this.status = Status.SUCCEEDED;
		} catch (Exception e) {
			this.status = Status.FAILED;
			throw e;
		}
		return result;
	}
}