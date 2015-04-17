package com.tsa.puridiom.stdtable.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.List;
import java.util.Map;
public class StdTableRetrieveBy extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		StringBuffer queryString = new StringBuffer("from StdTable as stdtable where 1=1 ");
		if(incomingRequest.containsKey("StdTable_tableType"))
		{
			String tableType = (String) incomingRequest.get("StdTable_tableType");
			queryString.append(" AND stdtable.id.tableType = '" + tableType + "'");
		}
		if(incomingRequest.containsKey("StdTable_tableKey"))
		{
			String tableKey = (String) incomingRequest.get("StdTable_tableKey");
			queryString.append(" AND stdtable.id.tableKey = '" + tableKey + "'");
		}
		if(incomingRequest.containsKey("StdTable_description"))
		{
			String description = (String) incomingRequest.get("StdTable_description");
			queryString.append(" AND stdtable.description = '" + description + "'");
		}
		if(incomingRequest.containsKey("StdTable_dateEntered"))
		{
			String dateEntered = (String) incomingRequest.get("StdTable_dateEntered");
			queryString.append(" AND stdtable.dateEntered = '" + dateEntered + "'");
		}
		if(incomingRequest.containsKey("StdTable_dateExpires"))
		{
			String dateExpires = (String) incomingRequest.get("StdTable_dateExpires");
			queryString.append(" AND stdtable.dateExpires = '" + dateExpires + "'");
		}
		if(incomingRequest.containsKey("StdTable_status"))
		{
			String status = (String) incomingRequest.get("StdTable_status");
			queryString.append(" AND stdtable.status = '" + status + "'");
		}
		if(incomingRequest.containsKey("StdTable_owner"))
		{
			String owner = (String) incomingRequest.get("StdTable_owner");
			queryString.append(" AND stdtable.owner = '" + owner + "'");
		}
		if(incomingRequest.containsKey("StdTable_dateChanged"))
		{
			String dateChanged = (String) incomingRequest.get("StdTable_dateChanged");
			queryString.append(" AND stdtable.dateChanged = '" + dateChanged + "'");
		}
		if(incomingRequest.containsKey("StdTable_udf1Code"))
		{
			String udf1Code = (String) incomingRequest.get("StdTable_udf1Code");
			queryString.append(" AND stdtable.udf1Code = '" + udf1Code + "'");
		}
		if(incomingRequest.containsKey("StdTable_udf2Code"))
		{
			String udf2Code = (String) incomingRequest.get("StdTable_udf2Code");
			queryString.append(" AND stdtable.udf2Code = '" + udf2Code + "'");
		}
		if(incomingRequest.containsKey("StdTable_udf3Code"))
		{
			String udf3Code = (String) incomingRequest.get("StdTable_udf3Code");
			queryString.append(" AND stdtable.udf3Code = '" + udf3Code + "'");
		}

		queryString.append(" ORDER BY stdtable.id.tableKey ASC");

		List result = dbs.query(queryString.toString()) ;
		this.setStatus(dbs.getStatus()) ;
		return result ;
	}
}