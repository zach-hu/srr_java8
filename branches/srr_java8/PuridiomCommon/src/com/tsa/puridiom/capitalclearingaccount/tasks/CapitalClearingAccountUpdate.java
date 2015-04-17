package com.tsa.puridiom.capitalclearingaccount.tasks;
import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class CapitalClearingAccountUpdate extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			CapitalClearingAccount capitalClearingAccount = (CapitalClearingAccount)incomingRequest.get("capitalClearingAccount");
			if (capitalClearingAccount == null)
			{
				throw new Exception ("CapitalClearingAccount was not found.");
			}

			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			dbs.update(capitalClearingAccount);

			result = capitalClearingAccount;
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