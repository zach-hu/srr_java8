/*
 * Created on Aug 19, 2003
 */
package com.tsa.puridiom.requisition.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsa.puridiom.common.tasks.AutoGenDocNumber;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.processengine.Status;

import java.util.*;

/**
 * @author Administrator
*/
public class RequisitionAutoNumber extends Task {

	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map) object;
		DBSession dbs = (DBSession) incomingRequest.get("dbsession") ;

		/* Setup Parameters */
		incomingRequest.put("AutoGen_documentType","REQ") ;
		incomingRequest.put("AutoGen_genYear",(String) incomingRequest.get("RequisitionHeader_fiscalYear"));
		 		
		 /* Call AutoGenDocNumber Task and Return Next Number */
		AutoGenDocNumber autoGenTask = new AutoGenDocNumber();
		String docNum = (String) autoGenTask.executeTask(incomingRequest) ;
		
		if (docNum != null) {
			this.setStatus(dbs.getStatus()) ;
		} else {
			this.setStatus(Status.FAILED) ;
		}		

		return docNum ;

	}

}
