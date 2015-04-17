package com.tsa.puridiom.contact.tasks;

import com.tsa.puridiom.common.utility.BlackBox;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.*;
import java.util.*;

public class ContactUpdateSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			String	password = (String) incomingRequest.get("Contact_contactPassword");

			if (!Utility.isEmpty(password)) {
				incomingRequest.put("Contact_contactPassword", password);
			}
			else {
				incomingRequest.remove("Contact_contactPassword");
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