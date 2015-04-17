package com.tsa.puridiom.capitalprojectcar.tasks;

import java.util.Map;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import com.tsa.puridiom.entity.*;

public class CapitalProjectCarDeleteById extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		CapitalProjectCar capitalProjectCar = (CapitalProjectCar)incomingRequest.get("capitalProjectCar");

		if(capitalProjectCar == null)
		{
			capitalProjectCar = new CapitalProjectCar();
		}

		CapitalProjectCarSetValuesPK setValues = new CapitalProjectCarSetValuesPK();
		setValues.setValues(incomingRequest, capitalProjectCar);
		dbs.delete(capitalProjectCar);
		this.setStatus(dbs.getStatus()) ;
		return capitalProjectCar ;

	}

}