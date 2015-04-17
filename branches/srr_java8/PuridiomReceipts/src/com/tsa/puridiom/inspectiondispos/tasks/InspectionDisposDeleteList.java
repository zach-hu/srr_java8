package com.tsa.puridiom.inspectiondispos.tasks;
import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class InspectionDisposDeleteList extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		List inspectionDisposList = (List) incomingRequest.get("inspectionDisposList") ;

		if (inspectionDisposList == null)  inspectionDisposList = new ArrayList();

		for (int i = 0; i < inspectionDisposList.size(); i++) {
			InspectionDispos inspectionDispos = (InspectionDispos) inspectionDisposList.get(i);
			incomingRequest.put("InspectionDispos_icRecHeader", inspectionDispos.getComp_id().getIcRecHeader().toString()) ;
			incomingRequest.put("InspectionDispos_icRecLine", inspectionDispos.getComp_id().getIcRecLine().toString()) ;
			incomingRequest.put("InspectionDispos_keySequence", inspectionDispos.getComp_id().getKeySequence().toString());
			incomingRequest.put("inspectionDispos", inspectionDispos) ;
			InspectionDisposDeleteById deleteTask = new InspectionDisposDeleteById() ;
			deleteTask.executeTask(incomingRequest) ;
		}

		this.setStatus(dbs.getStatus()) ;
		return null ;
	}

}