package com.tsa.puridiom.invstage.tasks;
import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.Map;

public class InvStageDeleteById extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		InvStage invStage = (InvStage)incomingRequest.get("invStage");
		if(invStage == null)
		{
			invStage = new InvStage();
		}
		InvStageSetValuesPK setValues = new InvStageSetValuesPK();
		setValues.setValues(incomingRequest, invStage);
		dbs.delete(invStage);
		this.setStatus(dbs.getStatus()) ;
		return invStage ;
	}

}