package com.tsa.puridiom.inspectionmte.tasks;
import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class InspectionMteDeleteList extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		List inspectionMteList = (List) incomingRequest.get("inspectionMteList") ;

		if (inspectionMteList == null)  inspectionMteList = new ArrayList();

		for (int i = 0; i < inspectionMteList.size(); i++) {
			InspectionMte inspectionMte = (InspectionMte) inspectionMteList.get(i);
			incomingRequest.put("inspectionMte", inspectionMte) ;
			incomingRequest.put("InspectionMte_icRecHeader", inspectionMte.getComp_id().getIcRecHeader().toString()) ;
			incomingRequest.put("InspectionMte_icRecLine", inspectionMte.getComp_id().getIcRecLine().toString()) ;
			incomingRequest.put("InspectionMte_keySequence", inspectionMte.getComp_id().getKeySequence().toString()) ;
			incomingRequest.put("InspectionMte_icInspNo", inspectionMte.getComp_id().getIcInspNo().toString()) ;
			InspectionMteDeleteById deleteTask = new InspectionMteDeleteById() ;
			deleteTask.executeTask(incomingRequest) ;
		}

		this.setStatus(dbs.getStatus()) ;
		return null ;
	}

}