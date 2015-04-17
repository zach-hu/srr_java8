package com.tsa.puridiom.vendorregistration.authentication.tasks;

import com.tsa.puridiom.common.utility.BlackBoxTempCheck;
import com.tsa.puridiom.common.utility.BlackBox;
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
			String	userId = (String) incomingRequest.get("userId");
			String	password = (String) incomingRequest.get("password");

			if (!Utility.isEmpty(userId)) {
			    userId = userId.toUpperCase();
			}

			if (!Utility.isEmpty(password)) {
                incomingRequest.put("p", password);

                BlackBoxTempCheck x = new BlackBoxTempCheck();
                x.setApplicationName(this.applicationName);
                Boolean temp = (Boolean) x.executeTask(incomingRequest);

                if (temp.booleanValue()) {
                    incomingRequest.put("RegisterUser_tempPassword", "true");
                }

                password = password.toUpperCase();
                password = BlackBox.getEncrypt(password);
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