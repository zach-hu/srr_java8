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
public class RfqHeaderRetrieveShipToAddressSetup extends Task {
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
			String shipToCode = rfh.getShipToCode();
			if (shipToCode.equals(rfh.getRequisitionNumber()))
			{
				String addressType = "SHIPTO";
				incomingRequest.put("Address_addressType", addressType);
			}
			incomingRequest.put("Address_addressCode", shipToCode) ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}

		return null ;
	}
}
