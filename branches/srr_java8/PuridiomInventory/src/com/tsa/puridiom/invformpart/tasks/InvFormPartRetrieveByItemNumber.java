package com.tsa.puridiom.invformpart.tasks;

import java.util.List;
import java.util.Map;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
public class InvFormPartRetrieveByItemNumber extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		StringBuffer queryString = new StringBuffer("from InvFormPart as invformpart where ");
		
		String itemNumber = (String) incomingRequest.get("InvFormPart_itemNumber");
		
		queryString.append(" invformpart.id.itemNumber = '" + itemNumber + "'");

		List result = dbs.query(queryString.toString()) ;
		this.setStatus(dbs.getStatus()) ;
		return result ;
	}
}