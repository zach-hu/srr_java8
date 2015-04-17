package com.tsa.puridiom.contact.tasks;

import com.tsa.puridiom.common.utility.BlackBox;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.*;

import java.util.*;

public class ContactAdminUpdateSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			if (incomingRequest.containsKey("Contact_contactPassword")) {
			    String	password = (String) incomingRequest.get("Contact_contactPassword");
			    
				if (!Utility.isEmpty(password)) {
					password = password.toUpperCase();
					password = BlackBox.getEncrypt(password);
					
					incomingRequest.put("Contact_contactPassword", password);
				}

				// if Contact_contactPassword is set in incomingRequest, the administrator changed it
			    //incomingRequest.put("Contact_passChanged", "ADMIN");
				incomingRequest.put("Contact_passChanged", Dates.today("yyyy-MM-dd", ""));
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