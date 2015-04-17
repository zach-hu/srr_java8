package com.tsa.puridiom.rfqquestion.tasks;

import com.tsagate.foundation.processengine.*;
import java.util.*;

public class RfqQuestionAddStandard extends Task {
	public Object  executeTask (Object object) throws Exception {
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try {
			if (incomingRequest.containsKey("StdQuestion_icQuestion")) {
				PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
				PuridiomProcess process = processLoader.loadProcess("rfqquestion-add-standard-by-id.xml");
				Object icQuestionObj = incomingRequest.get("StdQuestion_icQuestion");
	
				if (icQuestionObj instanceof String[]) {
					//if the icQuestion is an array, loop through list of icQuestion values and add each as an Rfq Question
					String icQuestions[] = (String[]) icQuestionObj;
					int	arraySize = icQuestions.length;
					Set keySet = incomingRequest.keySet();
		
					for (int i=0; i < arraySize; i++) {
						Map updateParameters = new HashMap();
						updateParameters.put("userId", incomingRequest.get("userId"));
						updateParameters.put("organizationId", incomingRequest.get("organizationId"));
						updateParameters.put("dbsession", incomingRequest.get("dbsession"));
						updateParameters.put("RfqHeader_icRfqHeader", incomingRequest.get("RfqHeader_icRfqHeader"));
						updateParameters.put("RfqQuestion_icRfqHeader", incomingRequest.get("RfqHeader_icRfqHeader"));
						updateParameters.put("StdQuestion_icQuestion", icQuestions[i]);
						
						process.executeProcess(updateParameters);
					}
				}
				else {
						process.executeProcess(incomingRequest);
				}
			}
			else {
				throw new Exception("The value for StandardQuestion_icQuestion was not found.");
			}
	
			this.setStatus(Status.SUCCEEDED) ;
		}
		catch (Exception e) {
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}

}