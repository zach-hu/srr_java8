package com.tsa.puridiom.catalogsecurity.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Log;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import java.util.List;
import java.util.Map;

public class CatalogSecurityRetrieveByUserId extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;

			String accessId = (String)incomingRequest.get("CatalogSecurity_accessId");
			String queryString = "from CatalogSecurity as cs where cs.accessId = ? ";

			List resultList = dbs.query(queryString, new Object[] {accessId} , new Type[] { Hibernate.STRING}) ;

			if (resultList != null && resultList.size() > 0)
			{
				result = resultList.get(0);
			}
			this.setStatus(dbs.getStatus()) ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			e.printStackTrace();
			Log.error(this, "Error retrieve catalogSecurityByUserId: \r\n" + e.getMessage());
			throw e;
		}
		return result;
	}

}