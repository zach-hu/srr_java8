/*
 * Created on Sept. 17, 2006
 */
package com.tsa.puridiom.rfqbid.tasks;

import com.tsa.puridiom.entity.RfqBid;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import java.util.Map;

/**
 * @author Kelli 
 */
public class RfqBidGetIcRfqHeader extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map) object;
		Object result = null;
		
		try {
		    RfqBid rfqBid = (RfqBid) incomingRequest.get("rfqBid");
		    if (rfqBid != null) {
				result = String.valueOf(rfqBid.getComp_id().getIcRfqHeader());
		    }
		    
			this.setStatus(Status.SUCCEEDED) ;
		} catch (Exception e) {
		    throw e;
		}
		
		return result ;
	}
}
