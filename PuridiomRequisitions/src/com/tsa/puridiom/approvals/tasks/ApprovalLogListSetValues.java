/*
 * Created on April 28, 2006 
 */
package com.tsa.puridiom.approvals.tasks;

import java.util.*;

import com.tsa.puridiom.entity.ApprovalLog;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

/**
 * @author Kelli
 */
public class ApprovalLogListSetValues extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;
		List	approvalLogList = new ArrayList();
		
		try {
			if (incomingRequest.containsKey("ApprovalLog_userId")) {
				
				Object approvalLogObj = incomingRequest.get("ApprovalLog_userId");
				
				if (approvalLogObj instanceof String[]) {
					int	arraySize = ((String[]) approvalLogObj).length;
					Set keySet = incomingRequest.keySet();
					
					for (int i=0; i < arraySize; i++) {
						ApprovalLogSetValues setValues = new ApprovalLogSetValues();
						
						Map setValueParameters = new HashMap();
						setValueParameters.put("userId", incomingRequest.get("userId"));
						setValueParameters.put("userTimeZone", incomingRequest.get("userTimeZone"));
						setValueParameters.put("organizationId", incomingRequest.get("organizationId"));
						setValueParameters.put("dbsession", incomingRequest.get("dbsession"));
						setValueParameters.put("ApprovalLog_icHeader", incomingRequest.get("ApprovalLog_icHeader"));
						setValueParameters.put("ApprovalLog_icLine", "0");
						setValueParameters.put("ApprovalLog_sequence", Integer.toString(i + 1));
						
						Iterator iterator = keySet.iterator();
						while (iterator.hasNext()) {
							String key = (String) iterator.next();
							if (key.indexOf("ApprovalLog_") == 0) {
								Object obj =  incomingRequest.get(key);
								if (obj instanceof String[]) {
									String arrayObj[] = (String[]) obj;
									setValueParameters.put(key, arrayObj[i]);
								}
							}
						}
						ApprovalLog approvalLog = (ApprovalLog) setValues.executeTask(setValueParameters);
						if(setValues.getStatus() != Status.SUCCEEDED)
						{
							this.setStatus(Status.FAILED);
							throw new Exception("Error ocurred setting values for approval log!");
						}
						
						approvalLogList.add(approvalLog);
					}
				}
				else 
				{
					ApprovalLogSetValues setValues = new ApprovalLogSetValues();
					
					incomingRequest.put("ApprovalLog_icLine", "0");
					incomingRequest.put("ApprovalLog_sequence", "1");
											
					ApprovalLog approvalLog = (ApprovalLog) setValues.executeTask(incomingRequest);
					if(setValues.getStatus() != Status.SUCCEEDED)
					{
						this.setStatus(Status.FAILED);
						throw new Exception("Error ocurred setting values for approval log!");
					}
					
					approvalLogList.add(approvalLog);
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
		return approvalLogList;
	}
}
