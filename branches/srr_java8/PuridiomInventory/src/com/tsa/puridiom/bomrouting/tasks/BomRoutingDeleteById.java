package com.tsa.puridiom.bomrouting.tasks;
import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.Map;

public class BomRoutingDeleteById extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		BomRouting bomRouting = (BomRouting)incomingRequest.get("bomRouting");
		if(bomRouting == null)
		{
			bomRouting = new BomRouting();
		}
		BomRoutingSetValuesPK setValues = new BomRoutingSetValuesPK();
		setValues.setValues(incomingRequest, bomRouting);
		dbs.delete(bomRouting);
		this.setStatus(dbs.getStatus()) ;
		return bomRouting ;
	}

}