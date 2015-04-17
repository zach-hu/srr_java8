/*
 * Created on April 05, 2005 
 */
package com.tsa.puridiom.approvals.tasks;

import java.util.*;

import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

/**
 * @author kathleen
 */
public class ApprovalLogUpdateByLine extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;
		Object result = null;
		
		try {
			if (incomingRequest.containsKey("ApprovalLog_userId")) {
				
				Object approvalLogObj = incomingRequest.get("ApprovalLog_userId");
				
				if (approvalLogObj instanceof String[]) {
					int	arraySize = ((String[]) approvalLogObj).length;
					Set keySet = incomingRequest.keySet();
					
					for (int i=0; i < arraySize; i++) {
						PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
						PuridiomProcess process = processLoader.loadProcess("approvallog-update-by-line.xml");
						
						Map updateParameters = new HashMap();
						updateParameters.put("userId", incomingRequest.get("userId"));
						updateParameters.put("userTimeZone", incomingRequest.get("userTimeZone"));
						updateParameters.put("organizationId", incomingRequest.get("organizationId"));
						updateParameters.put("dbsession", incomingRequest.get("dbsession"));
						updateParameters.put("ApprovalLog_icHeader", incomingRequest.get("ApprovalLog_icHeader"));
						updateParameters.put("ApprovalLog_icLine", "0");
						updateParameters.put("ApprovalLog_sequence", Integer.toString(i + 1));
						
						Iterator iterator = keySet.iterator();
						while (iterator.hasNext()) {
							String key = (String) iterator.next();
							if (key.indexOf("ApprovalLog_") == 0) {
								Object obj =  incomingRequest.get(key);
								if (obj instanceof String[]) {
									String arrayObj[] = (String[]) obj;
									updateParameters.put(key, arrayObj[i]);
								}
							}
						}
						process.executeProcess(updateParameters);
						if(process.getStatus() != Status.SUCCEEDED)
						{
							this.setStatus(Status.FAILED);
							throw new TsaException("Error ocurred updating approval log!");
						}
					}
				}
				else 
				{
					PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
					PuridiomProcess process = processLoader.loadProcess("approvallog-update-by-line.xml");
					
					incomingRequest.put("ApprovalLog_icHeader", incomingRequest.get("ApprovalLog_icHeader"));
					incomingRequest.put("ApprovalLog_icLine", "0");
					incomingRequest.put("ApprovalLog_sequence", "1");
											
					process.executeProcess(incomingRequest);
				}
			}
			else 
			{
			    // No approvers to be updated
				//throw new Exception("The value for ApprovalLog_userId was not found.");
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
