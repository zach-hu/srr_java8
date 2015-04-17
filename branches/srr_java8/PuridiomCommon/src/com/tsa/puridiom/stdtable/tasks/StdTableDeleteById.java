package com.tsa.puridiom.stdtable.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.Map;
import com.tsa.puridiom.entity.*;

public class StdTableDeleteById extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		StdTable stdTable = (StdTable)incomingRequest.get("stdTable");
		if(stdTable == null)
		{
			stdTable = new StdTable();
		}
		StdTableSetValuesPK setValues = new StdTableSetValuesPK();
		setValues.setValues(incomingRequest, stdTable);
		dbs.delete(stdTable);
		this.setStatus(dbs.getStatus()) ;
		return stdTable ;
	}

}