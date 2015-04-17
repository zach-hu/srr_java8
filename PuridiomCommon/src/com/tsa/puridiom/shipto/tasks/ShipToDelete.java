package com.tsa.puridiom.shipto.tasks;
import java.util.Map;

import com.tsa.puridiom.entity.ShipTo;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;

public class ShipToDelete extends Task{
		public Object  executeTask (Object object) throws Exception

	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		ShipTo shipTo = (ShipTo)incomingRequest.get("shipTo");
		if(shipTo == null)
		{
			shipTo = new ShipTo();
		}
		ShipToSetValuesPK setValues = new ShipToSetValuesPK();
		setValues.setValues(incomingRequest, shipTo);
		dbs.delete(shipTo);
		this.setStatus(dbs.getStatus()) ;
		return shipTo ;
	}

}