package com.tsa.puridiom.inspectionstd.tasks;
import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.Map;

public class InspectionStdDeleteById extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		InspectionStd inspectionStd = (InspectionStd)incomingRequest.get("inspectionStd");
		if(inspectionStd == null)
		{
			inspectionStd = new InspectionStd();
		}
		InspectionStdSetValuesPK setValues = new InspectionStdSetValuesPK();
		setValues.setValues(incomingRequest, inspectionStd);
		dbs.delete(inspectionStd);
		this.setStatus(dbs.getStatus()) ;
		return inspectionStd ;
	}

}