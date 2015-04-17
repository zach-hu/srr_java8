package com.tsa.puridiom.inspectionmfr.tasks;
import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.Map;

public class InspectionMfrDeleteById extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		InspectionMfr inspectionMfr = (InspectionMfr)incomingRequest.get("inspectionMfr");
		if(inspectionMfr == null)
		{
			inspectionMfr = new InspectionMfr();
		}
		InspectionMfrSetValuesPK setValues = new InspectionMfrSetValuesPK();
		setValues.setValues(incomingRequest, inspectionMfr);
		dbs.delete(inspectionMfr);
		this.setStatus(dbs.getStatus()) ;
		return inspectionMfr ;
	}

}