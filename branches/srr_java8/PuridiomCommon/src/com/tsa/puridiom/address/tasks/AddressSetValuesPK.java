package com.tsa.puridiom.address.tasks;

import java.util.Map;

import com.tsa.puridiom.entity.Address;
import com.tsa.puridiom.entity.AddressPK;

public class AddressSetValuesPK
{
	public void setValues(Map incomingRequest, Address address ) throws Exception
	{
		try
		{
			String addressType = (String ) incomingRequest.get("Address_addressType");
			String addressCode = (String ) incomingRequest.get("Address_addressCode");
			AddressPK comp_id = new AddressPK();
			comp_id.setAddressCode(addressCode);
			comp_id.setAddressType(addressType);
			address.setComp_id(comp_id);
		}
		catch (Exception e)
		{
			throw e;
		}
	}
}