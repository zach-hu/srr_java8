package com.tsa.puridiom.rfqbid.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.*;

/**
 * @author Kelli
 */
public class RfqBidAddSetup extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map) object;
		DBSession dbs = (DBSession) incomingRequest.get("dbsession") ;

		String icRfqHeader = (String) incomingRequest.get("RfqLine_icRfqHeader");
		
		incomingRequest.put("RfqVendor_icRfqHeader",icRfqHeader) ;
		
		this.setStatus(dbs.getStatus());
		
		return null ;
	}
}
