package com.tsa.puridiom.vendorregistration.tasks;

import com.tsa.puridiom.vendorregistration.RegisterUser;
import com.tsa.puridiom.common.utility.BlackBox;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import java.util.Map;

public class RegisterUserDefaultPasswordSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			//String	newPassword = "WELCOME1";

			RegisterUser registerUser = (RegisterUser) incomingRequest.get("registerUser");

			incomingRequest.put("Contact_contactCode", registerUser.getContactCode());
			incomingRequest.put("Contact_contactType", registerUser.getContactType());
			incomingRequest.put("Contact_vendorId", registerUser.getVendorId());
			//incomingRequest.put("Contact_contactPassword", newPassword);
			//incomingRequest.put("Contact_passChanged", "ADMIN");

			incomingRequest.put("VendorRegister_contactEmailAddr", registerUser.getEmailAddress());
			incomingRequest.put("VendorRegister_vendorId", registerUser.getVendorId());
			//incomingRequest.put("VendorRegister_userPassword", newPassword);
			//incomingRequest.put("VendorRegister_passChanged", "ADMIN");

			//incomingRequest.put("RegisterUser_userPassword", BlackBox.getEncrypt(newPassword));

            //registerUser.setContactPassword(BlackBox.getEncrypt(newPassword));

			incomingRequest.put("registerUser", registerUser);

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
