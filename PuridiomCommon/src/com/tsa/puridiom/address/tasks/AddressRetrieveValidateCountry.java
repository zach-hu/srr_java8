package com.tsa.puridiom.address.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.Address;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

public class AddressRetrieveValidateCountry extends Task
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

			String countryListValid = "Y";

			if (addressList != null && addressList.size() > 0)
			{
				for (int i=0; i<addressList.size(); i++)
				{
					Address address = (Address)addressList.get(i);
					if (!HiltonUtility.isEmpty(address.getCountry()))
					{
						String queryString = "from Country as Country where Country.countryCode = ? ";
						List resultList = dbs.query(queryString, new Object[] {address.getCountry()} , new Type[] {Hibernate.STRING});

						if (!(resultList != null && resultList.size() > 0))
							countryListValid = "N";
					}
				}
			}
			incomingRequest.put("countryListValid", countryListValid);
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