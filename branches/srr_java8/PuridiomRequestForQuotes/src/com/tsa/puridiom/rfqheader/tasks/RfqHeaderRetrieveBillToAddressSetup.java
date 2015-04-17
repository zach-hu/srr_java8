/*
 * Created on Nov 19, 2003
 */
package com.tsa.puridiom.rfqheader.tasks;

import com.tsa.puridiom.entity.RfqHeader;
import com.tsagate.foundation.processengine.*;
import java.util.*;

/**
 * @author Kelli
 */
public class RfqHeaderRetrieveBillToAddressSetup extends Task {
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
			String billToCode = rfh.getBillToCode();

			incomingRequest.put("Address_addressCode", billToCode) ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		
		return null ;
	}
}
