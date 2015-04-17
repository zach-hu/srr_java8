package com.tsa.puridiom.inspectionline.tasks;
import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;

import java.util.List;
import java.util.Map;

public class InspectionLineDeleteLineList extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		List inspectionLineList = (List) incomingRequest.get("inspectionLineList") ;

		for (int i = 0; i < inspectionLineList.size(); i++) {
			InspectionLine inspectionLine = (InspectionLine) inspectionLineList.get(i);
			incomingRequest.put("inspectionLine", inspectionLine) ;
			InspectionLineDeleteById deleteTask = new InspectionLineDeleteById() ;
			deleteTask.executeTask(incomingRequest) ;
		}

		this.setStatus(dbs.getStatus()) ;
		return null ;
	}

}