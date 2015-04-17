package com.tsa.puridiom.stdcomment.tasks;
import java.util.Map;

import com.tsa.puridiom.entity.StdComment;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;

public class StdCommentDeleteById extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		StdComment stdComment = (StdComment)incomingRequest.get("stdComment");
		if(stdComment == null)
		{
			stdComment = new StdComment();
		}
		StdCommentSetValuesPK setValues = new StdCommentSetValuesPK();
		setValues.setValues(incomingRequest, stdComment);
		dbs.delete(stdComment);
		this.setStatus(dbs.getStatus()) ;
		return stdComment ;
	}

}