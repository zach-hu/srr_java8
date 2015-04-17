package com.tsa.puridiom.bommethod.tasks;
import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.Map;

public class BomMethodDeleteById extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		BomMethod bomMethod = (BomMethod)incomingRequest.get("bomMethod");
		if(bomMethod == null)
		{
			bomMethod = new BomMethod();
		}
		BomMethodSetValuesPK setValues = new BomMethodSetValuesPK();
		setValues.setValues(incomingRequest, bomMethod);
		dbs.delete(bomMethod);
		this.setStatus(dbs.getStatus()) ;
		return bomMethod ;
	}

}