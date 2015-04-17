/*
 * Created on Nov 13, 2003
 */
package com.tsa.puridiom.rfq.tasks;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Utility ;
import java.util.Map;

/**
 * @author Kelli
 */
public class RfqCancelSetup extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map) object;
		DBSession dbs = (DBSession) incomingRequest.get("dbsession") ;

		String icRfqHeader = (String) incomingRequest.get("RfqHeader_icRfqHeader");
		
		if (Utility.isEmpty(icRfqHeader)) {
			this.setStatus(Status.FAILED);
		} else {
			incomingRequest.put("RfqLine_icRfqHeader",icRfqHeader) ;
			this.setStatus(dbs.getStatus());
		}

		incomingRequest.put("newStatus", DocumentStatus.CANCELLED);
		
		return null ;
	}
}
