package com.tsa.puridiom.rfqquestion.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class RfqQuestionAdd extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			RfqQuestion rfqQuestion = (RfqQuestion)incomingRequest.get("rfqQuestion");
			if (rfqQuestion == null)
			{
				throw new Exception ("RfqQuestion was not found.");
			}
		
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			dbs.add(rfqQuestion);
		
			result = rfqQuestion;
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