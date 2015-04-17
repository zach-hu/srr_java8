package com.tsa.puridiom.invmanufacturer.tasks;
import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.Map;

public class InvManufacturerDeleteById extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		InvManufacturer invManufacturer = (InvManufacturer)incomingRequest.get("invManufacturer");
		if(invManufacturer == null)
		{
			invManufacturer = new InvManufacturer();
		}
		InvManufacturerSetValuesPK setValues = new InvManufacturerSetValuesPK();
		setValues.setValues(incomingRequest, invManufacturer);
		dbs.delete(invManufacturer);
		this.setStatus(dbs.getStatus()) ;
		return invManufacturer ;
	}

}