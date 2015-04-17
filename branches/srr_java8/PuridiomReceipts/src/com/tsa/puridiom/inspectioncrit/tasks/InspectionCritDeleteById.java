package com.tsa.puridiom.inspectioncrit.tasks;
import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.Map;

public class InspectionCritDeleteById extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		InspectionCrit inspectionCrit = (InspectionCrit)incomingRequest.get("inspectionCrit");
		if(inspectionCrit == null)
		{
			inspectionCrit = new InspectionCrit();
		}
		InspectionCritSetValuesPK setValues = new InspectionCritSetValuesPK();
		setValues.setValues(incomingRequest, inspectionCrit);
		dbs.delete(inspectionCrit);
		this.setStatus(dbs.getStatus()) ;
		return inspectionCrit ;
	}

}