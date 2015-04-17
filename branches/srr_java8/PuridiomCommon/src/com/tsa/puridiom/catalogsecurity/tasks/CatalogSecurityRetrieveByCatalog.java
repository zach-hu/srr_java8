package com.tsa.puridiom.catalogsecurity.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Log;

import java.util.List;
import java.util.Map;

public class CatalogSecurityRetrieveByCatalog extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;

			String queryString = "from CatalogSecurity as cs where cs.catalogId = is not null and cs.itemNumber='0' ";

			List resultList = dbs.query(queryString) ;

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
			Log.error(this, "Error retrieve catalogSecurity: \r\n" + e.getMessage());
			throw e;
		}
		return result;
	}

}