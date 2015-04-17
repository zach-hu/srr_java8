package com.tsa.puridiom.unitofmeasure.tasks;

import com.tsa.puridiom.entity.UnitOfMeasure;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.Map;

public class UnitOfMeasureDeleteById extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		UnitOfMeasure unitOfMeasure = (UnitOfMeasure)incomingRequest.get("unitOfMeasure");
		if(unitOfMeasure == null)
		{
			unitOfMeasure = new UnitOfMeasure();
		}
		UnitOfMeasureSetValuesPK setValues = new UnitOfMeasureSetValuesPK();
		setValues.setValues(incomingRequest, unitOfMeasure);
		dbs.delete(unitOfMeasure);
		this.setStatus(dbs.getStatus()) ;
		return unitOfMeasure ;
	}

}