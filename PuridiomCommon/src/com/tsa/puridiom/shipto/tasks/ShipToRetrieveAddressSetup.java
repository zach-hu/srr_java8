/*
 * Created on Jan 22, 2004
 */
package com.tsa.puridiom.shipto.tasks;

import com.tsa.puridiom.entity.ShipTo;
import com.tsagate.foundation.processengine.*;
import java.util.*;

/**
 * @author Kelli
 */
public class ShipToRetrieveAddressSetup extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {
		Map incomingRequest = (Map) object;
		Object result = null;

		try
		{
			ShipTo shipTo = (ShipTo) incomingRequest.get("shipTo") ;
			if (shipTo == null) {
				throw new Exception("The ShipTo entity was not found.");
			}
			String shipToCode = shipTo.getComp_id().getShipToCode();

			incomingRequest.put("Address_addressCode", shipToCode) ;
			incomingRequest.put("Address_addressType", "ADDR") ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}

		return null ;
	}
}
