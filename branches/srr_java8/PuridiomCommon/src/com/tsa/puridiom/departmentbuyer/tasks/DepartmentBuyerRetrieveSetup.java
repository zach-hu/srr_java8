/*
 * Created on October 21, 2004
 */
package com.tsa.puridiom.departmentbuyer.tasks;

import java.util.*;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.processengine.Status;
/**
 * @author Administrator 
 */
public class DepartmentBuyerRetrieveSetup extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map) object;
		DBSession dbs = (DBSession) incomingRequest.get("dbsession") ;
		
		String code = (String) incomingRequest.get("Department_departmentCode");
		if (code != null)
		{
			incomingRequest.put("DepartmentBuyer_departmentCode", code);
			this.setStatus(Status.SUCCEEDED);
		}
		else
		{
			this.setStatus(Status.FAILED);
		}
		
		return null ;
	}
}
