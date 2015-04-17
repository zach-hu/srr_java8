package com.tsa.puridiom.bomcomponent.tasks;
import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.Map;

public class BomComponentDeleteById extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		BomComponent bomComponent = (BomComponent)incomingRequest.get("bomComponent");
		if(bomComponent == null)
		{
			bomComponent = new BomComponent();
		}
		BomComponentSetValuesPK setValues = new BomComponentSetValuesPK();
		setValues.setValues(incomingRequest, bomComponent);
		dbs.delete(bomComponent);
		this.setStatus(dbs.getStatus()) ;
		return bomComponent ;
	}

}