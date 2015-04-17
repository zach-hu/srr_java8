package com.tsa.puridiom.stdtable.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import java.util.List;
import java.util.Map;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;

public class StdTableRetrieveById extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String tableType = (String ) incomingRequest.get("StdTable_tableType");
			String tableKey = (String ) incomingRequest.get("StdTable_tableKey");

			String queryString = "from StdTable as StdTable where StdTable.id.tableType = ? and StdTable.id.tableKey = ? ";
			List resultList = dbs.query(queryString, new Object[] {tableType, tableKey } , new Type[] { Hibernate.STRING, Hibernate.STRING}) ;

			if (resultList != null && resultList.size() > 0)
			{
				result = resultList.get(0);
			}
			this.setStatus(dbs.getStatus()) ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}

}