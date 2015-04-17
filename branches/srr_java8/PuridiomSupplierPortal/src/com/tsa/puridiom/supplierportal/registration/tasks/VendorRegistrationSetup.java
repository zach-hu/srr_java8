package com.tsa.puridiom.supplierportal.registration.tasks;

import com.tsa.puridiom.property.PropertiesManager;
import com.tsa.puridiom.supplierportal.BlackBox;
import com.tsa.puridiom.supplierportal.exception.*;
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
			String	organizationId = (String) incomingRequest.get("organizationId");
			boolean caseSensitive = PropertiesManager.getInstance(organizationId).getProperty("MISC", "PassCaseSensitive", "N" ).equals("Y");

			if (Utility.isEmpty(emailAddress)) {
				this.status = Status.FAILED;
				throw new RegistrationException ("Email Address was not specified.");
			}
			else {
				if (!Utility.isEmpty(password)) {
					if (caseSensitive) {
						password = BlackBox.getEncrypt("ENCRYPT", password);
						incomingRequest.put("RegisterUser_contactPassword", password);
					} else {
						password = password.toUpperCase();
						password = BlackBox.getEncrypt("ENCRYPT", password);
						incomingRequest.put("RegisterUser_contactPassword", password);
					}
				}
				incomingRequest.put("Contact_emailAddr", emailAddress);
				incomingRequest.put("VendorRegister_contactEmailAddr", emailAddress);
				incomingRequest.put("VendorRegister_altEmailAddr", emailAddress);
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