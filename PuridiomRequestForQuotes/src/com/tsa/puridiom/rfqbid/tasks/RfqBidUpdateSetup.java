package com.tsa.puridiom.rfqbid.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.*;

/**
 * @author Kelli
 */
public class RfqBidUpdateSetup extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map) object;
		DBSession dbs = (DBSession) incomingRequest.get("dbsession") ;

		if (incomingRequest.containsKey("RfqBid_icRfqHeader")) {
		    Object icHeaderObj = incomingRequest.get("RfqBid_icRfqHeader");
		    
		    if (icHeaderObj instanceof String) {
		        incomingRequest.put("RfqHeader_icRfqHeader",(String) icHeaderObj) ;
		    } else if (icHeaderObj instanceof String[]) {
		        String	icHeaderArray[] = (String[]) icHeaderObj;
		        incomingRequest.put("RfqHeader_icRfqHeader", icHeaderArray[0]) ;
		    }
		}
		
		this.setStatus(dbs.getStatus());
		
		return null ;
	}
}
