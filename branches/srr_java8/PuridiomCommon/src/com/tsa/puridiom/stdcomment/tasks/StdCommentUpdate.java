package com.tsa.puridiom.stdcomment.tasks;
import java.util.Map;

import com.tsa.puridiom.entity.StdComment;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

public class StdCommentUpdate extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			StdComment stdComment = (StdComment)incomingRequest.get("stdComment");
			if (stdComment == null)
			{
				throw new Exception ("StdComment was not found.");
			}
		
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			dbs.update(stdComment);
		
			result = stdComment;
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