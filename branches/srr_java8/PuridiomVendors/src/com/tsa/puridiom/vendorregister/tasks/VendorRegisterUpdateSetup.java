package com.tsa.puridiom.vendorregister.tasks;

import com.tsa.puridiom.common.utility.BlackBox;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.*;
import java.util.*;

public class VendorRegisterUpdateSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			String	password = (String) incomingRequest.get("VendorRegister_contactPassword");
			
			if (!Utility.isEmpty(password)) {
				password = password.toUpperCase();
				password = BlackBox.getEncrypt(password);
				
				incomingRequest.put("VendorRegister_contactPassword", password);
			}
			else {
				incomingRequest.remove("VendorRegister_contactPassword");
			}
						
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