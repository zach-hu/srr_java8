package com.tsa.puridiom.inspectionhistory.tasks;
import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.Map;

public class InspectionHistoryDeleteById extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		InspectionHistory inspectionHistory = (InspectionHistory)incomingRequest.get("inspectionHistory");
		if(inspectionHistory == null)
		{
			inspectionHistory = new InspectionHistory();
		}
		InspectionHistorySetValuesPK setValues = new InspectionHistorySetValuesPK();
		setValues.setValues(incomingRequest, inspectionHistory);
		dbs.delete(inspectionHistory);
		this.setStatus(dbs.getStatus()) ;
		return inspectionHistory ;
	}

}