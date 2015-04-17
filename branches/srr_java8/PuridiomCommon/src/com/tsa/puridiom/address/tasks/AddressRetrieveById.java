package com.tsa.puridiom.address.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import java.util.List;
import java.util.Map;

public class AddressRetrieveById extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String addressType = (String ) incomingRequest.get("Address_addressType");
			String addressCode = (String ) incomingRequest.get("Address_addressCode");

			String queryString = "from Address as Address where Address.id.addressType = ? and Address.id.addressCode = ? ";
			List resultList = dbs.query(queryString, new Object[] {addressType, addressCode, } , new Type[] { Hibernate.STRING, Hibernate.STRING}) ;
					
			if (resultList != null && resultList.size() > 0)
			{
				result = resultList.get(0);
			}
			this.setStatus(dbs.getStatus()) ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}
}