package com.tsa.puridiom.rfqnote.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import java.util.Map;

public class RfqNoteRetrieveByLine extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			if (incomingRequest.containsKey("RfqNote_icHeader") && incomingRequest.containsKey("RfqNote_icLine"))
			{
				DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
				String icHeader = (String) incomingRequest.get("RfqNote_icHeader");
				String icLine = (String) incomingRequest.get("RfqNote_icLine");
				StringBuffer queryString = new StringBuffer("from RfqNote as rfqnote where rfqnote.id.icHeader = '" + icHeader + "' and rfqnote.id.icLine = '" + icLine + "'");
				
				result = dbs.query(queryString.toString()) ;
				this.setStatus(dbs.getStatus()) ;
			}
			else
			{
				throw new Exception("The value for RfqNote_icHeader and RfqNote_icLine must be set.");
			}
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result ;
	}
}