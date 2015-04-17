package com.tsa.puridiom.inspectiondiscrep.tasks;
import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class InspectionDiscrepDeleteList extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		List inspectionDiscrepList = (List) incomingRequest.get("inspectionDiscrepList") ;

		if (inspectionDiscrepList == null)  inspectionDiscrepList = new ArrayList();

		for (int i = 0; i < inspectionDiscrepList.size(); i++) {
			InspectionDiscrep inspectionDiscrep = (InspectionDiscrep) inspectionDiscrepList.get(i);
			incomingRequest.put("InspectionDiscrep_icRecHeader", inspectionDiscrep.getComp_id().getIcRecHeader().toString()) ;
			incomingRequest.put("InspectionDiscrep_icRecLine", inspectionDiscrep.getComp_id().getIcRecLine().toString()) ;
			incomingRequest.put("InspectionDiscrep_keySequence", inspectionDiscrep.getComp_id().getKeySequence().toString());
			incomingRequest.put("inspectionDiscrep", inspectionDiscrep) ;
			InspectionDiscrepDeleteById deleteTask = new InspectionDiscrepDeleteById() ;
			deleteTask.executeTask(incomingRequest) ;
		}

		this.setStatus(dbs.getStatus()) ;
		return null ;
	}

}