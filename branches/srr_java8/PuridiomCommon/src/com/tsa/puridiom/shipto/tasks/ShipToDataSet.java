/*
 * Created on Jan 22, 2004 
 */
package com.tsa.puridiom.shipto.tasks;

import com.tsa.puridiom.entity.Address;
import com.tsa.puridiom.entity.ShipTo;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import java.util.Map;

/**
 * @author Administrator 
 */
public class ShipToDataSet extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;

		ShipTo shipTo = (ShipTo) incomingRequest.get("shipTo") ;

		shipTo.setShipToAddress((Address) incomingRequest.get("shipToAddress"));
					
		this.setStatus(Status.SUCCEEDED) ;
        
		return null  ;
	}
}
