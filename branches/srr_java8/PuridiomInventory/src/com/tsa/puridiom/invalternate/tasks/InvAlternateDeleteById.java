package com.tsa.puridiom.invalternate.tasks;
import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.Map;

public class InvAlternateDeleteById extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		InvAlternate invAlternate = (InvAlternate)incomingRequest.get("invAlternate");
		if(invAlternate == null)
		{
			invAlternate = new InvAlternate();
		}
		InvAlternateSetValuesPK setValues = new InvAlternateSetValuesPK();
		setValues.setValues(incomingRequest, invAlternate);
		dbs.delete(invAlternate);
		this.setStatus(dbs.getStatus()) ;
		return invAlternate ;
	}

}