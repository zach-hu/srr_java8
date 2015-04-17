/*
 * Created on Nov 19, 2003
 */
package com.tsa.puridiom.requisitionheader.tasks;

import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsagate.foundation.processengine.*;
import java.util.*;

/**
 * @author Kelli
 */
public class RequisitionHeaderRetrieveBillToAddressSetup extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {
		Map incomingRequest = (Map) object;
		Object result = null;
		
		try
		{
			RequisitionHeader rqh = (RequisitionHeader) incomingRequest.get("requisitionHeader") ;
			if (rqh == null) {
				throw new Exception("The RequisitionHeader entity was not found.");
			}
			String billToCode = rqh.getBillToCode();

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
