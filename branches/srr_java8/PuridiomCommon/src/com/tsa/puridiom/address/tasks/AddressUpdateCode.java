package com.tsa.puridiom.address.tasks;

import com.tsa.puridiom.entity.Address;
import com.tsagate.foundation.processengine.*;

import java.util.List;
import java.util.Map;

public class AddressUpdateCode extends Task
{
	public Object executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		List   addressList = (List)incomingRequest.get("retrieveAddressList");
		String newLocation = (String)incomingRequest.get("newItemLocation");
		String oldLocation = (String)incomingRequest.get("oldItemLocation");

		for (int i = 0; i < addressList.size(); i++)
		{
			Address address = (Address)addressList.get(i);
			if(address.getComp_id().getAddressCode().equals(oldLocation))
			{
				address.getComp_id().setAddressCode(newLocation);
				incomingRequest.put("address", address);
				AddressUpdate update = new AddressUpdate();
				update.executeTask(incomingRequest);
				if(update.getStatus() != Status.SUCCEEDED)
				{
					i = addressList.size();
				}
				this.setStatus(update.getStatus());
				break;
			}
		}
		return addressList;
	}
}