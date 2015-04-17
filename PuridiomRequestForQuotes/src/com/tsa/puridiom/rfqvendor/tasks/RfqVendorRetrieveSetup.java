/*
 * Created on September 17, 2004
 */
package com.tsa.puridiom.rfqvendor.tasks;

import com.tsa.puridiom.entity.RfqHeader;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Utility ;
import java.util.*;

public class RfqVendorRetrieveSetup extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {
		Map incomingRequest = (Map) object;
		Object result = null;
		
		try
		{
			RfqHeader rfh = (RfqHeader) incomingRequest.get("rfqHeader") ;
			if (rfh == null) {
				throw new Exception("The RfqHeader entity was not found.");
			}
			String icHeader = rfh.getIcRfqHeader().toString() ;
			
			if (Utility.isEmpty(icHeader)) {
				throw new Exception("The value for RfqHeader.icRfqHeader cannot be empty.");
			}
			
			incomingRequest.put("RfqVendor_icRfqHeader",icHeader) ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		
		return null ;
	}
}
