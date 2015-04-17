package com.tsa.puridiom.rfqquestion.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import java.util.*;

public class RfqQuestionUpdateEntityList extends Task {
	public Object  executeTask (Object object) throws Exception {
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try {
			List	rfqQuestionList = (List) incomingRequest.get("rfqQuestionList");
			RfqQuestionUpdateById rfqQuestionUpdateTask = new RfqQuestionUpdateById();
				
			for (int i=0; i < rfqQuestionList.size(); i++) {
				RfqQuestion rfqQuestion = (RfqQuestion) rfqQuestionList.get(i);
				
				Map updateParameters = new HashMap();
				updateParameters.put("userId", incomingRequest.get("userId"));
				updateParameters.put("organizationId", incomingRequest.get("organizationId"));
				updateParameters.put("dbsession", incomingRequest.get("dbsession"));
				updateParameters.put("rfqQuestion", rfqQuestion);
				
				rfqQuestionUpdateTask.executeTask(updateParameters);
				
				if (rfqQuestionUpdateTask.getStatus() < Status.SUCCEEDED) {
					throw new Exception("RfqQuestionUpdateById failed.");
				}
			}

			result = rfqQuestionList;			
			this.setStatus(Status.SUCCEEDED) ;
		}
		catch (Exception e) {
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}

}