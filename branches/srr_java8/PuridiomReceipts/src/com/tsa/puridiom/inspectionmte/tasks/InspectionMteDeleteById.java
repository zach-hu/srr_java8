package com.tsa.puridiom.inspectionmte.tasks;
import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.Map;

public class InspectionMteDeleteById extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		InspectionMte inspectionMte = (InspectionMte)incomingRequest.get("inspectionMte");
		if(inspectionMte == null)
		{
			inspectionMte = new InspectionMte();
		}
		InspectionMteSetValuesPK setValues = new InspectionMteSetValuesPK();
		setValues.setValues(incomingRequest, inspectionMte);
		dbs.delete(inspectionMte);
		this.setStatus(dbs.getStatus()) ;
		return inspectionMte ;
	}

}