package com.tsa.puridiom.invmachine.tasks;
import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.Map;

public class InvMachineDeleteById extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		InvMachine invMachine = (InvMachine)incomingRequest.get("invMachine");
		if(invMachine == null)
		{
			invMachine = new InvMachine();
		}
		InvMachineSetValuesPK setValues = new InvMachineSetValuesPK();
		setValues.setValues(incomingRequest, invMachine);
		dbs.delete(invMachine);
		this.setStatus(dbs.getStatus()) ;
		return invMachine ;
	}

}