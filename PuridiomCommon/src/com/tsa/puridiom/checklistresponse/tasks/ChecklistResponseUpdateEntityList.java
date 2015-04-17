package com.tsa.puridiom.checklistresponse.tasks;

import com.tsagate.foundation.processengine.*;
import java.util.*;

public class ChecklistResponseUpdateEntityList extends Task {
	public Object  executeTask (Object object) throws Exception {
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try {
			if (incomingRequest.containsKey("ChecklistResponse_icHeader")) {
				PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
				PuridiomProcess process = processLoader.loadProcess("checklistresponse-update-by-id.xml");
				Object icHeaderObj = incomingRequest.get("ChecklistResponse_icHeader");
				
				if (icHeaderObj instanceof String[]) {
					//if the ChecklistResponse_icHeader is an array, all ChecklistResponse values should be an array of the same size
					int	arraySize = ((String[]) icHeaderObj).length;
					Set keySet = incomingRequest.keySet();
					
					for (int i=0; i < arraySize; i++) {
						Map updateParameters = new HashMap();
						updateParameters.put("userId", incomingRequest.get("userId"));
						updateParameters.put("userTimeZone", incomingRequest.get("userTimeZone"));
						updateParameters.put("organizationId", incomingRequest.get("organizationId"));
						updateParameters.put("dbsession", incomingRequest.get("dbsession"));
						
						Iterator iterator = keySet.iterator();
						while (iterator.hasNext()) {
							String key = (String) iterator.next();
							if (key.indexOf("ChecklistResponse_") == 0) {
								//must be an ChecklistResponse attribute and should be an array
								Object obj =  incomingRequest.get(key);
								if (obj instanceof String[]) {
									String arrayObj[] = (String[]) obj;
									updateParameters.put(key, arrayObj[i]);
								}
							}
						}
						process.executeProcess(updateParameters);
					}
				}
				else {
						process.executeProcess(incomingRequest);
				}
			}
			else {
				throw new Exception("The value for ChecklistResponse_icHeader was not found.");
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