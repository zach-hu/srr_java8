package com.tsa.puridiom.rfqline.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Utility ;
import com.tsagate.foundation.processengine.Status;
import java.util.*;

/**
 * @author Kelli
 */
public class RfqLineDeleteSetup extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map) object;
		DBSession dbs = (DBSession) incomingRequest.get("dbsession") ;

		String icRfqHeader = (String) incomingRequest.get("RfqLine_icRfqHeader");
		String icRfqLine = (String) incomingRequest.get("RfqLine_icRfqLine");
		
		if (Utility.isEmpty(icRfqHeader) || Utility.isEmpty(icRfqLine)) {
			this.setStatus(Status.FAILED);
		} else {
			incomingRequest.put("BillTo_icHeader",icRfqHeader) ;
			incomingRequest.put("BillTo_icLine",icRfqLine) ;
			incomingRequest.put("ShipTo_icHeader",icRfqHeader) ;
			incomingRequest.put("ShipTo_icLine",icRfqLine) ;
			incomingRequest.put("DocComment_icHeader",icRfqHeader) ;
			incomingRequest.put("DocComment_icLine",icRfqLine) ;
			incomingRequest.put("RfqBid_icRfqHeader",icRfqHeader) ;
			incomingRequest.put("RfqBid_icRfqLine",icRfqLine) ;
			incomingRequest.put("RfqVendor_icRfqHeader",icRfqHeader) ;
			incomingRequest.put("RfqVendor_icRfqLine",icRfqLine) ;
			this.setStatus(Status.SUCCEEDED);
		}
		
		return null ;
	}
}
