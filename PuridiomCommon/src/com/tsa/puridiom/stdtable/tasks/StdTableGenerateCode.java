package com.tsa.puridiom.stdtable.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import java.util.List;
import java.util.Map;

public class StdTableGenerateCode extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object StdTable_tableKey = null;
		
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String lastCode = "";
			String	tableType = (String) incomingRequest.get("StdTable_tableType");
			String queryString = "select max(s.id.tableKey) from StdTable as s where s.id.tableType = '" + tableType + "'";
			List resultList = dbs.query(queryString) ;
					
			if (resultList != null && resultList.size() > 0)
			{
				lastCode = (String) resultList.get(0);
				if (lastCode == null) {
					//next code = 0
					StdTable_tableKey = (String) "0";
				}
				else {
					//find next available code
					String availableCharacters = (String) "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
					StdTable_tableKey = (String) availableCharacters.substring(availableCharacters.indexOf(lastCode) + 1, availableCharacters.indexOf(lastCode) + 2);
				}
			}
			this.setStatus(dbs.getStatus()) ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return StdTable_tableKey;
	}

}