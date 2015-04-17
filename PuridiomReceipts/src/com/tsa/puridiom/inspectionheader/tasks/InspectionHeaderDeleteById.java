package com.tsa.puridiom.inspectionheader.tasks;
import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.Map;

public class InspectionHeaderDeleteById extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		InspectionHeader inspectionHeader = (InspectionHeader)incomingRequest.get("inspectionHeader");
		if(inspectionHeader == null)
		{
			inspectionHeader = new InspectionHeader();
		}
		InspectionHeaderSetValuesPK setValues = new InspectionHeaderSetValuesPK();
		setValues.setValues(incomingRequest, inspectionHeader);
		dbs.delete(inspectionHeader);
		this.setStatus(dbs.getStatus()) ;
		return inspectionHeader ;
	}

}