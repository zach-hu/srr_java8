package com.tsa.puridiom.checklistentry.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;

import java.util.*;

public class ChecklistEntryRetrieveResponseValuesByList extends Task {
	public Object  executeTask (Object object) throws Exception {
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try {
			List	checklistEntryList = (List) incomingRequest.get("checklistEntryList");
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
			processLoader.setApplicationName(this.getApplicationName());
			PuridiomProcess process = processLoader.loadProcess("responsevalue-retrieve-by-checklistentry.xml");
			
			for (int i=0; i < checklistEntryList.size(); i++) {
			    ChecklistEntry checklistEntry = (ChecklistEntry) checklistEntryList.get(i);
				
				Map requestParameters = new HashMap();
				requestParameters.put("userId", incomingRequest.get("userId"));
				requestParameters.put("userTimeZone", incomingRequest.get("userTimeZone"));
				requestParameters.put("organizationId", incomingRequest.get("organizationId"));
				requestParameters.put("dbsession", incomingRequest.get("dbsession"));
				requestParameters.put("checklistEntry", checklistEntry);
				
				process.executeProcess(requestParameters);
				
				if (process.getStatus() < Status.SUCCEEDED) {
					throw new Exception("responsevalue-retrieve-by-checklistentry failed.");
				}
				
				checklistEntry = (ChecklistEntry) requestParameters.get("checklistEntry");
				
				checklistEntryList.set(i, checklistEntry);
			}

			result = checklistEntryList;			
			this.setStatus(Status.SUCCEEDED) ;
		}
		catch (Exception e) {
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}

}