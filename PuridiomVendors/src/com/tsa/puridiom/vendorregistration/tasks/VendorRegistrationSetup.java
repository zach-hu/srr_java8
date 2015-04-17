package com.tsa.puridiom.vendorregistration.tasks;

import com.tsa.puridiom.common.utility.BlackBox;
import com.tsa.puridiom.vendorregistration.exception.*;
import com.tsagate.foundation.utility.*;
import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class VendorRegistrationSetup extends Task {

	public Object executeTask(Object object) throws Exception {
		Map incomingRequest = (Map)object;
		Object result = null;

		try {
			String	emailAddress = (String) incomingRequest.get("RegisterUser_emailAddress");
			String	password = (String) incomingRequest.get("RegisterUser_contactPassword");
			if (Utility.isEmpty(emailAddress)) {
				this.status = Status.FAILED;
				throw new RegistrationException ("Email Address was not specified.");
			}
			else {
				if (!Utility.isEmpty(password)) {
					password = password.toUpperCase();
					password = BlackBox.getEncrypt("ENCRYPT", password);
					incomingRequest.put("RegisterUser_contactPassword", password);
				}
				incomingRequest.put("Contact_emailAddr", emailAddress);
				incomingRequest.put("VendorRegister_contactEmailAddr", emailAddress);
				incomingRequest.put("VendorRegister_altEmailAddr", emailAddress);
				incomingRequest.put("VendorRegister_dateEntered",Dates.today("", ""));
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