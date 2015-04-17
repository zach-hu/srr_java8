/*
 * Created on Nov 13, 2003
 */
package com.tsa.puridiom.rfq.tasks;

import java.util.*;

import com.tsa.puridiom.entity.RfqHeader;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Utility ;
/**
 * @author Kelli
 */
public class RfqDeleteSetup extends Task {
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
			incomingRequest.put("Schedule_icHeader",icRfqHeader) ;
			incomingRequest.put("BillTo_icHeader",icRfqHeader) ;
			incomingRequest.put("ShipTo_icHeader",icRfqHeader) ;
			incomingRequest.put("Account_icHeader",icRfqHeader) ;
			incomingRequest.put("DocComment_icHeader",icRfqHeader) ;
			incomingRequest.put("DocAttachment_icHeader",icRfqHeader) ;
			incomingRequest.put("RfqLine_icRfqHeader",icRfqHeader) ;
			incomingRequest.put("RfqQuestion_icRfqHeader",icRfqHeader) ;
			incomingRequest.put("RfqVendor_icRfqHeader",icRfqHeader) ;
			incomingRequest.put("RfqBid_icRfqHeader",icRfqHeader) ;
			RfqHeader rfh = (RfqHeader) incomingRequest.get("rfqHeader") ;
			if (rfh != null) {
				incomingRequest.put("RfqHeader_rfqType",rfh.getRfqType()) ;
			}
			this.setStatus(dbs.getStatus());
		}
		
		return null ;
	}
}
