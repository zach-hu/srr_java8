package com.tsa.puridiom.requisitionline.tasks;

import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

public class RequisitionLineAllUpdateStatus extends Task {
	public Object  executeTask (Object object) throws Exception {
		Map incomingRequest = (Map)object;
		Object result = null;

		try {
			List	reqLineList = (List) incomingRequest.get("requisitionLineList");
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String newStatus = HiltonUtility.ckNull((String) incomingRequest.get("newStatus"));
			
			if(reqLineList != null && reqLineList.size() > 0)
			{
				for (int i=0; i < reqLineList.size(); i++) {
					RequisitionLine reqLine = (RequisitionLine) reqLineList.get(i);
	
					reqLine.setStatus(newStatus);
	               	dbs.update(reqLine);
				}
			}

			this.setStatus(Status.SUCCEEDED) ;
		}
		catch (Exception e) {
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}

}