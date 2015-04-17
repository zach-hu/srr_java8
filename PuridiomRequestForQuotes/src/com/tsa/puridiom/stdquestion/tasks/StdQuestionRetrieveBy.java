package com.tsa.puridiom.stdquestion.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.List;
import java.util.Map;
public class StdQuestionRetrieveBy extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		StringBuffer queryString = new StringBuffer("from StdQuestion as stdquestion where 1=1 ");
		if(incomingRequest.containsKey("StdQuestion_questionId"))
		{			
			String questionId = (String) incomingRequest.get("StdQuestion_questionId");
			queryString.append(" AND stdquestion.id.questionId = '" + questionId + "'");
		}
		if(incomingRequest.containsKey("StdQuestion_questionTitle"))
		{			
			String questionTitle = (String) incomingRequest.get("StdQuestion_questionTitle");
			queryString.append(" AND stdquestion.questionTitle = '" + questionTitle + "'");
		}
		if(incomingRequest.containsKey("StdQuestion_questionText"))
		{			
			String questionText = (String) incomingRequest.get("StdQuestion_questionText");
			queryString.append(" AND stdquestion.questionText = '" + questionText + "'");
		}
		if(incomingRequest.containsKey("StdQuestion_responseType"))
		{			
			String responseType = (String) incomingRequest.get("StdQuestion_responseType");
			queryString.append(" AND stdquestion.responseType = '" + responseType + "'");
		}
		if(incomingRequest.containsKey("StdQuestion_ratingMethod"))
		{			
			String ratingMethod = (String) incomingRequest.get("StdQuestion_ratingMethod");
			queryString.append(" AND stdquestion.ratingMethod = '" + ratingMethod + "'");
		}
		if(incomingRequest.containsKey("StdQuestion_weight"))
		{			
			String weight = (String) incomingRequest.get("StdQuestion_weight");
			queryString.append(" AND stdquestion.weight = '" + weight + "'");
		}
		if(incomingRequest.containsKey("StdQuestion_maxPoints"))
		{			
			String maxPoints = (String) incomingRequest.get("StdQuestion_maxPoints");
			queryString.append(" AND stdquestion.maxPoints = '" + maxPoints + "'");
		}
		List result = dbs.query(queryString.toString()) ;
		this.setStatus(dbs.getStatus()) ;
		return result ;
	}
}
