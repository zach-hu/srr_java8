package com.tsa.puridiom.userpayments.tasks;

import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Dates;
import java.util.Map;

public class DefaultAddressSetupFromANPayment extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object	resultObj = null ;

		try
		{
			String	userId = (String) incomingRequest.get("userId");
			String	x_company = (String) incomingRequest.get("x_company");	
			String	x_address = (String) incomingRequest.get("x_address");
			String	x_city = (String) incomingRequest.get("x_city");
			String	x_state = (String) incomingRequest.get("x_state");
			String	x_country = (String) incomingRequest.get("x_country");
			String	x_zip = (String) incomingRequest.get("x_zip");
			
			incomingRequest.put("Address_addressType", "ADDR");
			incomingRequest.put("Address_addressCode", "DEFAULT");
			incomingRequest.put("Address_addressLine1", x_company);
			incomingRequest.put("Address_addressLine2", x_address);
			incomingRequest.put("Address_city", x_city);
			incomingRequest.put("Address_state", x_state);
			incomingRequest.put("Address_postalCode", x_zip);
			incomingRequest.put("Address_country", x_country);
			incomingRequest.put("Address_dateEntered", Dates.today("", ""));
			incomingRequest.put("Address_dateExpires", Dates.today("", ""));
			incomingRequest.put("Address_status", "02");
			incomingRequest.put("Address_owner", userId);
			incomingRequest.put("Address_shipTo", "Y");
			incomingRequest.put("Address_billTo", "Y");

			this.status = Status.SUCCEEDED;
		}
		catch (Exception e)
		{
			this.status = Status.FAILED;
			throw e;
		}
		return resultObj;
	}
}