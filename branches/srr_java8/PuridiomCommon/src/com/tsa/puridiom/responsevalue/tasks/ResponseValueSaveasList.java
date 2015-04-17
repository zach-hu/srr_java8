package com.tsa.puridiom.responsevalue.tasks;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.ResponseValue;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

import java.util.*;

public class ResponseValueSaveasList extends Task {
	public Object  executeTask (Object object) throws Exception {
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try {
			List responseValueList = (List) incomingRequest.get("responseValueList");
			String	newIcQuestion = (String) incomingRequest.get("newResponseValue_icQuestion");
			
			if (responseValueList != null) {
			    if (!HiltonUtility.isEmpty(newIcQuestion)) {
					PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
					PuridiomProcess process = processLoader.loadProcess("responsevalue-saveas-by-id.xml");
						
					for (int i=0; i < responseValueList.size(); i++) {
					    ResponseValue responseValue = (ResponseValue) responseValueList.get(i);
						Map updateParameters = new HashMap();
						updateParameters.put("userId", incomingRequest.get("userId"));
						updateParameters.put("userTimeZone", incomingRequest.get("userTimeZone"));
						updateParameters.put("organizationId", incomingRequest.get("organizationId"));
						updateParameters.put("dbsession", incomingRequest.get("dbsession"));
						updateParameters.put("responseValue", responseValue);
						updateParameters.put("newResponseValue_icQuestion", newIcQuestion);
						
						process.executeProcess(updateParameters);
						
						if (process.getStatus() != Status.SUCCEEDED) {
						    throw new Exception ("An error occurred during the process responsevalue-saveas-by-id.");
						}
					}
				}
				else {
					throw new Exception("The value for newResponseValue_icQuestion was not found.");
				}
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