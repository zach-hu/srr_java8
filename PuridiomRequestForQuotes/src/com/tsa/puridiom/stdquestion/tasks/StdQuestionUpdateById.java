package com.tsa.puridiom.stdquestion.tasks;
import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class StdQuestionUpdateById extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			StdQuestion stdQuestion = (StdQuestion)incomingRequest.get("stdQuestion");
			if (stdQuestion == null)
			{
				throw new Exception ("StdQuestion was not found.");
			}
		
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			dbs.update(stdQuestion);
		
			result = stdQuestion;
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