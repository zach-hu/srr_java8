package com.tsa.puridiom.stdquestion.tasks;
import java.util.Map;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import com.tsa.puridiom.entity.*;

public class StdQuestionDeleteById extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		StdQuestion stdQuestion = (StdQuestion)incomingRequest.get("stdQuestion");
		if(stdQuestion == null)
		{
			stdQuestion = new StdQuestion();
		}
		StdQuestionSetValuesPK setValues = new StdQuestionSetValuesPK();
		setValues.setValues(incomingRequest, stdQuestion);
		dbs.delete(stdQuestion);
		this.setStatus(dbs.getStatus()) ;
		return stdQuestion ;
	}

}