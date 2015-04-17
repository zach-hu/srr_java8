package com.tsa.puridiom.stdtable.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

import java.util.List;
import java.util.Map;
public class StdTableRetrieveVendorClasses extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;

			result = dbs.query("select stdtable.id.tableKey from StdTable as stdtable where stdtable.id.tableType = 'VCLS' order by stdtable.id.tableKey ASC") ;
			this.setStatus(dbs.getStatus()) ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			Log.error(this, "StdTableRetrieveVendorClasses failed: " + e.getMessage());
		}
		return result ;
	}
}