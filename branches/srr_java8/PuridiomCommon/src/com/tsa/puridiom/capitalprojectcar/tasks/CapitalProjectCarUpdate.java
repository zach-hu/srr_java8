package com.tsa.puridiom.capitalprojectcar.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class CapitalProjectCarUpdate extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			CapitalProjectCar capitalProjectCar = (CapitalProjectCar)incomingRequest.get("capitalProjectCar");
			if (capitalProjectCar == null)
			{
				throw new Exception ("CapitalProjectCar was not found.");
			}

			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			dbs.update(capitalProjectCar);

			result = capitalProjectCar;
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