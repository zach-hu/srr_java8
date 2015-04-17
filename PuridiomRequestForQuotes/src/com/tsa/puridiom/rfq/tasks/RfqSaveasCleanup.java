package com.tsa.puridiom.rfq.tasks;

import com.tsa.puridiom.entity.RfqHeader;
import com.tsagate.foundation.processengine.*;
import java.util.*;

/**
 * @author Kelli
 */
public class RfqSaveasCleanup extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {
		Map incomingRequest = (Map) object;

		try {
		    incomingRequest.remove("RfqHeader_icRfqHeader");
		    incomingRequest.remove("RfqLine_icRfqHeader");
		    incomingRequest.remove("RfqLine_icRfqLine");
			incomingRequest.remove("Schedule_icHeader");
			incomingRequest.remove("DocComment_icHeader");
			incomingRequest.remove("DocComment_icLine");
			incomingRequest.remove("DocAttachment_icHeader");
			incomingRequest.remove("RfqLine_icRfqHeader");
			incomingRequest.remove("RfqVendor_icRfqHeader");
			incomingRequest.remove("RfqQuestion_icRfqHeader");
			incomingRequest.remove("RfqNote_icHeader");
			incomingRequest.remove("RfqNote_icLine");
			incomingRequest.remove("VendorResponse_icRfqHeader");
			
			RfqHeader	rfqHeader = (RfqHeader) incomingRequest.get("rfqHeader");
			incomingRequest.put("RfqHeader_icRfqHeader", String.valueOf(rfqHeader.getIcRfqHeader()));
			
			this.setStatus(Status.SUCCEEDED);
		}
		catch(Exception e) {
			this.setStatus(Status.FAILED);
			throw e;
		}
		
		return null ;
	}
}
