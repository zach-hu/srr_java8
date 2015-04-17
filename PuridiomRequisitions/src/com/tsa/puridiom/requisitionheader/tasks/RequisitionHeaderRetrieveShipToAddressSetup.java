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
public class RequisitionHeaderRetrieveShipToAddressSetup extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {
		Map incomingRequest = (Map) object;

		try
		{
			RequisitionHeader rqh = (RequisitionHeader) incomingRequest.get("requisitionHeader");
			RequisitionHeader rqho = (RequisitionHeader) incomingRequest.get("requisitionHeaderOriginal");
			if (rqh == null) {
				throw new Exception("The RequisitionHeader entity was not found.");
			}
			String shipToCode = rqh.getShipToCode();
			String addressType = "ADDR";

			/**
			 *  added on 02.08.07 for VSE  - users may enter a 1-time shipto address
			 *  if they do, the address type is SHIPTO
			 *  and the address code is the requisition number
			 **/
			if (shipToCode.equals(rqh.getRequisitionNumber()))
			{
				addressType = "SHIPTO";
			}
			if(rqho != null)
			{
				if (shipToCode.equals(rqho.getRequisitionNumber()))
				{
					shipToCode = rqho.getShipToCode();
					addressType = "SHIPTO";
				}
			}

			incomingRequest.put("Address_addressCode", shipToCode);
			incomingRequest.put("Address_addressType", addressType);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}

		return null ;
	}
}
