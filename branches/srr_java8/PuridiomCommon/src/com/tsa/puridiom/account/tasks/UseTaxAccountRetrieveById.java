package com.tsa.puridiom.account.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class UseTaxAccountRetrieveById extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object resultList = null;

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;

			String queryString = "from Account as Account where Account.accountType = 'UTX' order by Account.id.sequence ASC";
			resultList = dbs.query(queryString) ;

			this.setStatus(dbs.getStatus()) ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return resultList;
	}

}