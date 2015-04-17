package com.tsa.puridiom.bomtask.tasks;
import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.Map;

public class BomTaskDeleteById extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		BomTask bomTask = (BomTask)incomingRequest.get("bomTask");
		if(bomTask == null)
		{
			bomTask = new BomTask();
		}
		BomTaskSetValuesPK setValues = new BomTaskSetValuesPK();
		setValues.setValues(incomingRequest, bomTask);
		dbs.delete(bomTask);
		this.setStatus(dbs.getStatus()) ;
		return bomTask ;
	}

}