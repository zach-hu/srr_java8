package com.tsa.puridiom.disbheader.tasks;
import java.util.Map;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import com.tsa.puridiom.entity.*;

public class DisbHeaderDeleteById extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		DisbHeader disbHeader = (DisbHeader)incomingRequest.get("disbHeader");
		if(disbHeader == null)
		{
			disbHeader = new DisbHeader();
		}
		DisbHeaderSetValuesPK setValues = new DisbHeaderSetValuesPK();
		setValues.setValues(incomingRequest, disbHeader);
		dbs.delete(disbHeader);
		this.setStatus(dbs.getStatus()) ;
		return disbHeader ;
	}

}