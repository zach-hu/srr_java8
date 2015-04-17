package com.tsa.puridiom.commodity.tasks;

import com.tsa.puridiom.entity.Commodity;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.Map;

public class CommodityDeleteById extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession) incomingRequest.get("dbsession") ;
		Commodity commodity = (Commodity) incomingRequest.get("commodity");
		if(commodity == null)
		{
			commodity = new Commodity();
		}
		CommoditySetValuesPK setValues = new CommoditySetValuesPK();
		setValues.setValues(incomingRequest, commodity);
		dbs.delete(commodity);
		this.setStatus(dbs.getStatus()) ;
		return commodity ;
	}

}