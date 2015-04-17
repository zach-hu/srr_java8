package com.tsa.puridiom.invbinlocation.tasks;
import java.util.Map;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import com.tsa.puridiom.entity.*;

public class InvBinLocationDeleteById extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		InvBinLocation invBinLocation = (InvBinLocation)incomingRequest.get("invBinLocation");
		if(invBinLocation == null)
		{
			invBinLocation = new InvBinLocation();
		}
		InvBinLocationSetValuesPK setValues = new InvBinLocationSetValuesPK();
		setValues.setValues(incomingRequest, invBinLocation);
		dbs.delete(invBinLocation);
		this.setStatus(dbs.getStatus()) ;
		return invBinLocation ;
	}

}