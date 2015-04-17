package com.tsa.puridiom.address.tasks;

import java.util.Map;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

public class AddressUpdateRemitToSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;

		try
		{
			if (incomingRequest.containsKey("RemitToAddress_addressType"))
			{
				incomingRequest.put("Address_addressType", incomingRequest.get("RemitToAddress_addressType"));
			}
			if (incomingRequest.containsKey("RemitToAddress_addressCode"))
			{
			    incomingRequest.put("Address_addressCode", incomingRequest.get("RemitToAddress_addressCode"));
			}
			if (incomingRequest.containsKey("RemitToAddress_addressLine1"))
			{
			    incomingRequest.put("Address_addressLine1", incomingRequest.get("RemitToAddress_addressLine1"));
			}
			if (incomingRequest.containsKey("RemitToAddress_addressLine2"))
			{
			    incomingRequest.put("Address_addressLine2", incomingRequest.get("RemitToAddress_addressLine2"));
			}
			if (incomingRequest.containsKey("RemitToAddress_addressLine3"))
			{
			    incomingRequest.put("Address_addressLine3", incomingRequest.get("RemitToAddress_addressLine3"));
			}
			if (incomingRequest.containsKey("RemitToAddress_addressLine4"))
			{
			    incomingRequest.put("Address_addressLine4", incomingRequest.get("RemitToAddress_addressLine4"));
			}
			if (incomingRequest.containsKey("RemitToAddress_city"))
			{
			    incomingRequest.put("Address_city", incomingRequest.get("RemitToAddress_city"));
			}
			if (incomingRequest.containsKey("RemitToAddress_state"))
			{
			    incomingRequest.put("Address_state", incomingRequest.get("RemitToAddress_state"));
			}
			if (incomingRequest.containsKey("RemitToAddress_postalCode"))
			{
			    incomingRequest.put("Address_postalCode", incomingRequest.get("RemitToAddress_postalCode"));
			}
			if (incomingRequest.containsKey("RemitToAddress_country"))
			{
			    incomingRequest.put("Address_country", incomingRequest.get("RemitToAddress_country"));
			}
			if (incomingRequest.containsKey("RemitToAddress_dateEntered"))
			{
			    incomingRequest.put("Address_dateEntered", incomingRequest.get("RemitToAddress_dateEntered"));
			}
			if (incomingRequest.containsKey("RemitToAddress_dateExpires"))
			{
			    incomingRequest.put("Address_dateExpires", incomingRequest.get("RemitToAddress_dateExpires"));
			}
			if (incomingRequest.containsKey("RemitToAddress_status"))
			{
			    incomingRequest.put("Address_status", incomingRequest.get("RemitToAddress_status"));
			}
			if (incomingRequest.containsKey("RemitToAddress_owner"))
			{
			    incomingRequest.put("Address_owner", incomingRequest.get("RemitToAddress_owner"));
			}

			this.status = Status.SUCCEEDED;
		}
		catch (Exception e)
		{
			this.status = Status.FAILED;
			throw e;
		}
		return null;
	}
}