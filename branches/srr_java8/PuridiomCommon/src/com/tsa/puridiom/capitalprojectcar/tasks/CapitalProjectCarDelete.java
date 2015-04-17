package com.tsa.puridiom.capitalprojectcar.tasks;

import java.util.Map;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;
import com.tsa.puridiom.entity.*;

public class CapitalProjectCarDelete extends Task{
		public Object  executeTask (Object object) throws Exception

	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		CapitalProjectCar capitalProjectCar = (CapitalProjectCar)incomingRequest.get("capitalProjectCar");

		try
		{
			if(capitalProjectCar == null)
			{
				this.setStatus(Status.FAILED);
				throw new TsaException("CapitalProjectCar entity was not found!");
			}

			dbs.delete(capitalProjectCar);
			this.setStatus(dbs.getStatus()) ;
			return capitalProjectCar ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException(this.getName(), e);
		}
	}
}