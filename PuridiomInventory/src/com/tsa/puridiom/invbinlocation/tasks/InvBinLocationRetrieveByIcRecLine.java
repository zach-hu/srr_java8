package com.tsa.puridiom.invbinlocation.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Utility;
import java.util.List;
import java.util.Map;

public class InvBinLocationRetrieveByIcRecLine extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		Object result = null;
		StringBuffer queryString = new StringBuffer("from InvBinLocation as invbinlocation where 1=1 ");

	    String tempIc = (String) incomingRequest.get("InvBinLocation_icRecLine");
	    queryString.append(" AND invbinlocation.icRecLine = " + tempIc + "");

		List resultList = dbs.query(queryString.toString()) ;
		if (resultList != null && resultList.size() > 0)
		{
			result = resultList.get(0);
		}

		this.setStatus(dbs.getStatus()) ;
		return result ;
	}
}