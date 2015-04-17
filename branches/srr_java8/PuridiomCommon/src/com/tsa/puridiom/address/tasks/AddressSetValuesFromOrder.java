package com.tsa.puridiom.address.tasks;

import com.tsa.puridiom.entity.Address;
import com.tsagate.foundation.processengine.*;
import java.util.*;

public class AddressSetValuesFromOrder extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{

			Address	originalAddress = (Address) incomingRequest.get("originalAddress");
			Address	address = (Address) incomingRequest.get("address");

			address.setAddressLine1(originalAddress.getAddressLine1());
			address.setAddressLine2(originalAddress.getAddressLine2());
			address.setAddressLine3(originalAddress.getAddressLine3());
			address.setAddressLine4(originalAddress.getAddressLine4());
			address.setCity(originalAddress.getCity());
			address.setState(originalAddress.getState());
			address.setPostalCode(originalAddress.getPostalCode());
			address.setCountry(originalAddress.getCountry());
			//address.setDateEntered(originalAddress.getDateEntered());
			//address.setDateExpires(originalAddress.getDateExpires());
			//address.setStatus(originalAddress.getStatus());
			//address.setOwner(originalAddress.getOwner());

			incomingRequest.put("address", address);

			result = address;
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