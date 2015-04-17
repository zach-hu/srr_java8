/*
 * Created on Nov 19, 2003
 */
package com.tsa.puridiom.rfqline.tasks;

import com.tsa.puridiom.entity.RfqLine;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.Utility ;
import java.util.*;

/**
 * @author Kelli
 */
public class RfqLineRetrieveSetup extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {
	    try {
			Map incomingRequest = (Map) object;
			DBSession dbs = (DBSession) incomingRequest.get("dbsession") ;
			this.setStatus(Status.SUCCEEDED) ;

			RfqLine rfl = (RfqLine) incomingRequest.get("rfqLine") ;
			if (rfl == null) {
				Log.error(this, "RfqLine cannot be found");
				this.setStatus(Status.FAILED) ;
			} else {
				String icHeader = rfl.getIcRfqHeader().toString() ;
				String icLine = rfl.getIcRfqLine().toString() ;

				if (Utility.isEmpty(icHeader)) {
					this.setStatus(Status.FAILED);
				} else {
					incomingRequest.put("RfqLine_icRfqHeader", icHeader);
					incomingRequest.put("RfqLine_icRfqLine", icLine);
					incomingRequest.put("BillTo_icHeader",icHeader) ;
					incomingRequest.put("BillTo_icLine",icLine) ;
					incomingRequest.put("ShipTo_icHeader",icHeader) ;
					incomingRequest.put("ShipTo_icLine",icLine) ;
					incomingRequest.put("DocComment_icHeader",icHeader) ;
					incomingRequest.put("DocComment_icLine",icLine) ;
					incomingRequest.put("DocAttachment_icHeader",icHeader) ;
					incomingRequest.put("DocAttachment_icLine",icLine) ;
					incomingRequest.put("RfqBid_icRfqLine", icLine);
					incomingRequest.put("RfqLine_icReqLine", String.valueOf(rfl.getIcReqLine()));
					incomingRequest.put("RfqLine_icRfqLine", String.valueOf(rfl.getIcRfqLine()));

					if (this.applicationName.equalsIgnoreCase("BIDBOARD") || this.applicationName.equalsIgnoreCase("supplierportal")) {
					    incomingRequest.put("RfqNote_icHeader", icHeader);
					    incomingRequest.put("RfqNote_icLine", icLine);
					    incomingRequest.put("RfqNote_vendorId", incomingRequest.get("vendorId"));
					}
				}
			}
	    } catch (Exception e) {
	        this.setStatus(Status.FAILED);
	        Log.error(this, e.getMessage());
	        throw e;
	    }
		return null ;
	}
}
