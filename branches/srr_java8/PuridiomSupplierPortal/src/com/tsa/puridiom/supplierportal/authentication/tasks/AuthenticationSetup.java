package com.tsa.puridiom.supplierportal.authentication.tasks;

import com.tsa.puridiom.property.PropertiesManager;
import com.tsa.puridiom.supplierportal.BlackBox;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.*;
import java.util.Map;

public class AuthenticationSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			String	organizationId = (String) incomingRequest.get("organizationId");
			String	userId = (String) incomingRequest.get("userId");
			String	password = (String) incomingRequest.get("password");
			boolean caseSensitive = PropertiesManager.getInstance(organizationId).getProperty("MISC", "PassCaseSensitive", "N" ).equals("Y");

			if (!Utility.isEmpty(userId)) {
			    userId = userId.toUpperCase();
			}

			if (!Utility.isEmpty(password)) {
				if (caseSensitive) {
					password = BlackBox.getEncrypt(password);
				} else {
					password = password.toUpperCase();
					password = BlackBox.getEncrypt(password);
				}
			}

			incomingRequest.put("Contact_emailAddr", userId);
			incomingRequest.put("VendorRegister_contactEmailAddr", userId);
			incomingRequest.put("password", password);

			this.status = Status.SUCCEEDED;
		}
		catch (Exception e)
		{
			this.status = Status.FAILED;
			throw e;
		}
		return result;
	}
}