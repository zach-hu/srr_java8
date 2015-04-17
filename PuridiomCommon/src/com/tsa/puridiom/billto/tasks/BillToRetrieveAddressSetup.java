/*
 * Created on Jan 22, 2004
 */
package com.tsa.puridiom.billto.tasks;

import com.tsa.puridiom.entity.BillTo;
import com.tsagate.foundation.processengine.*;
import java.util.*;

/**
 * @author Kelli
 */
public class BillToRetrieveAddressSetup extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {
		Map incomingRequest = (Map) object;
		Object result = null;
		
		try
		{
			BillTo billto = (BillTo) incomingRequest.get("billTo") ;
			if (billto == null) {
				throw new Exception("The BillTo entity was not found.");
			}
			String billToCode = billto.getComp_id().getBillToCode();

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
