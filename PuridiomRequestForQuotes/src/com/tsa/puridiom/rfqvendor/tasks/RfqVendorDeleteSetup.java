package com.tsa.puridiom.rfqvendor.tasks;

import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Utility ;
import com.tsagate.foundation.processengine.Status;
import java.util.*;

/**
 * @author Kelli
 */
public class RfqVendorDeleteSetup extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map) object;

		String icRfqHeader = (String) incomingRequest.get("RfqVendor_icRfqHeader");
		String vendorId = (String) incomingRequest.get("RfqVendor_vendorId");
		
		if (Utility.isEmpty(icRfqHeader) || Utility.isEmpty(vendorId)) {
			this.setStatus(Status.FAILED);
		} else {
			incomingRequest.put("RfqBid_icRfqHeader",icRfqHeader) ;
			incomingRequest.put("RfqBid_vendorId",vendorId) ;
		}
		
		return null ;
	}
}
