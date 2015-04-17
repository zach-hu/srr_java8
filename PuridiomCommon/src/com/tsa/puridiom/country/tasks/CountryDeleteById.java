package com.tsa.puridiom.country.tasks;

import java.util.Map;

import com.tsa.puridiom.entity.Country;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;

public class CountryDeleteById extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		Country country = (Country)incomingRequest.get("country");
		if(country == null)
		{
			country = new Country();
		}
		CountrySetValuesPK setValues = new CountrySetValuesPK();
		setValues.setValues(incomingRequest, country);
		dbs.delete(country);
		this.setStatus(dbs.getStatus()) ;
		return country ;
	}

}