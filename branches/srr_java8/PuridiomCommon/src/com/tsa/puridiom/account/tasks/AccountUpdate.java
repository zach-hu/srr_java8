package com.tsa.puridiom.account.tasks;
import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class AccountUpdate extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			Account account = (Account)incomingRequest.get("account");
			if (account == null)
			{
				throw new Exception ("Account was not found.");
			}

			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			dbs.update(account);

			result = account;
			this.setStatus(dbs.getStatus()) ;

			/* Had to flush here, so data was available for selection later */
			dbs.getSession().flush() ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}

}