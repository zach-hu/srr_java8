package com.tsa.puridiom.rfqquestion.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;

import java.util.*;

public class RfqQuestionRetrieveResponseValuesByList extends Task {
	public Object  executeTask (Object object) throws Exception {
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try {
			List	rfqQuestionList = (List) incomingRequest.get("rfqQuestionList");
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
			processLoader.setApplicationName(this.getApplicationName());
			PuridiomProcess process = processLoader.loadProcess("responsevalue-retrieve-by-question.xml");
			
			for (int i=0; i < rfqQuestionList.size(); i++) {
				RfqQuestion rfqQuestion = (RfqQuestion) rfqQuestionList.get(i);
				
				Map requestParameters = new HashMap();
				requestParameters.put("userId", incomingRequest.get("userId"));
				requestParameters.put("organizationId", incomingRequest.get("organizationId"));
				requestParameters.put("dbsession", incomingRequest.get("dbsession"));
				requestParameters.put("rfqQuestion", rfqQuestion);
				
				process.executeProcess(requestParameters);
				
				if (process.getStatus() < Status.SUCCEEDED) {
					throw new Exception("responsevalue-retrieve-by-question failed.");
				}
				
				rfqQuestion = (RfqQuestion) requestParameters.get("rfqQuestion");
				
				rfqQuestionList.set(i, rfqQuestion);
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