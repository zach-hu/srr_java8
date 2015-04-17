package com.tsa.puridiom.rfqline.tasks;

import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Utility ;
import com.tsagate.foundation.processengine.Status;
import java.util.*;

/**
 * @author Kelli
 */
public class RfqLineUpdateSetup extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {
		Map incomingRequest = (Map) object;
		
		try
		{
			String icRfqHeader = (String) incomingRequest.get("RfqLine_icRfqHeader");
			String icRfqLine = (String) incomingRequest.get("RfqLine_icRfqLine");
		
			if (Utility.isEmpty(icRfqHeader) || Utility.isEmpty(icRfqLine)) {
				throw new Exception("The value for RfqLine_icRfqHeader or RfqLine_icRfqLine was not found.");
			}
			
			incomingRequest.put("RfqBid_icRfqHeader",icRfqHeader) ;
			incomingRequest.put("RfqBid_icRfqLine",icRfqLine) ;

			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		
		return null ;
	}
}
