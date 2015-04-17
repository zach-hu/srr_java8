/*
 * Created on Aug 19, 2003
 */
package com.tsa.puridiom.requisition.tasks;

import java.util.*;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Utility ;
import com.tsagate.foundation.processengine.Status;
/**
 * @author Administrator
 */
public class RequisitionRecallSetup extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map) object;
		DBSession dbs = (DBSession) incomingRequest.get("dbsession") ;

		String	userId = (String) incomingRequest.get("userId") ;
		String icReqHeader = (String) incomingRequest.get("RequisitionHeader_icReqHeader");

		if (Utility.isEmpty(icReqHeader)) {
			this.setStatus(Status.FAILED);
		} else {
			incomingRequest.put("RequisitionLine_icReqHeader",icReqHeader) ;
			this.setStatus(dbs.getStatus());
		}

		incomingRequest.put("newStatus", DocumentStatus.REQ_RECALLED);
		incomingRequest.put("RequisitionHeader_lastChgBy",userId) ;

		return null ;
	}
}
