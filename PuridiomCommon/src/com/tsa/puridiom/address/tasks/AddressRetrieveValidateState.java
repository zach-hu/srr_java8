package com.tsa.puridiom.address.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

import com.tsa.puridiom.entity.Address;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AddressRetrieveValidateState extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Object ret = null;
		try
		{
			Map incomingRequest = (Map) object;
			DBSession dbs = (DBSession)incomingRequest.get("dbsession");

			List addressList = new ArrayList();
			Object obj = incomingRequest.get("address");
			if (obj instanceof List) {
				addressList = (List)incomingRequest.get("address");
			} else if (obj instanceof Address){
				addressList.add((Address)incomingRequest.get("address"));
			}

			String stateListValid = "N";

			if (addressList != null && addressList.size() > 0)
			{
				for (int i=0; i<addressList.size(); i++)
				{
					Address address = (Address)addressList.get(i);
					if(address.getCountry().equalsIgnoreCase("US") || address.getCountry().equalsIgnoreCase("CA") || address.getCountry().equalsIgnoreCase(""))
					{
						String state = address.getState();
						if(state.equalsIgnoreCase(""))
						{
							stateListValid = "N";
						}
						else
						{
							stateListValid = "Y";
						}
					}
					else
					{
						if(stateListValid.equalsIgnoreCase("N"))
						{
							continue;
						}
						else
						{
							stateListValid = "Y";
						}
					}
				}
			}
			incomingRequest.put("stateListValid", stateListValid);
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException("An Error occurred at AddressRetrieveValidateCountry", e);
		}
		return ret;
	}
}