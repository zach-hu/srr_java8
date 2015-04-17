package com.tsa.puridiom.rfqline.tasks;

import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Utility ;
import com.tsagate.foundation.processengine.Status;
import java.util.*;

/**
 * @author Kelli
 */
public class RfqLineRetrieveByHeaderSetup extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {
		Map incomingRequest = (Map) object;
		
		try
		{
			String icRfqHeader = (String) incomingRequest.get("RfqLine_icRfqHeader");
		
			if (Utility.isEmpty(icRfqHeader)) {
				if (incomingRequest.containsKey("RfqHeader_icRfqHeader")) {
					icRfqHeader = (String) incomingRequest.get("RfqHeader_icRfqHeader");
				}
				else {
					throw new Exception("The value for RfqLine_icRfqHeader or RfqHeader_icRfqHeader was not found.");
				}
			}
			
			incomingRequest.put("RfqLine_icRfqHeader",icRfqHeader) ;

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
