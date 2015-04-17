package com.tsa.puridiom.bommanufacturer.tasks;
import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.Map;

public class BomManufacturerDeleteById extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		BomManufacturer bomManufacturer = (BomManufacturer)incomingRequest.get("bomManufacturer");
		if(bomManufacturer == null)
		{
			bomManufacturer = new BomManufacturer();
		}
		BomManufacturerSetValuesPK setValues = new BomManufacturerSetValuesPK();
		setValues.setValues(incomingRequest, bomManufacturer);
		dbs.delete(bomManufacturer);
		this.setStatus(dbs.getStatus()) ;
		return bomManufacturer ;
	}

}