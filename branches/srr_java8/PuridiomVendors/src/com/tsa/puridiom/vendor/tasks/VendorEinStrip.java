package com.tsa.puridiom.vendor.tasks;

import com.tsa.puridiom.vendor.VendorManager;
import com.tsa.puridiom.vendorregistration.exception.*;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import java.util.Map;

public class VendorEinStrip extends Task {
	public Object executeTask(Object object) throws Exception {
		Map incomingRequest = (Map) object;
		Object result = null;

		try {

			String ein = (String) incomingRequest.get("Vendor_vendorEin");
			if (ein == null) {
				throw new RegistrationException("Vendor ein [vendorEin] was not found.");
			}

			//incomingRequest.put("Vendor_vendorEin", ein);
			result = ein;

			this.status = Status.SUCCEEDED;
		} catch (Exception e) {
			this.status = Status.FAILED;
			throw e;
		}
		return result;
	}
}