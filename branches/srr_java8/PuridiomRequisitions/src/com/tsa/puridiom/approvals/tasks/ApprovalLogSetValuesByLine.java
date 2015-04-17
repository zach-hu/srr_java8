/*
 * Created on June 21, 2006 
 */
package com.tsa.puridiom.approvals.tasks;

import com.tsa.puridiom.entity.ApprovalLog;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;
import java.util.*;
/**
 * @author Kelli
 */
public class ApprovalLogSetValuesByLine extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;
		Object result = null;
		
		try {
		    List list = new ArrayList();
		    
			if (incomingRequest.containsKey("ApprovalLog_userId")) {
				Object approvalLogObj = incomingRequest.get("ApprovalLog_userId");
				
				if (approvalLogObj instanceof String[]) {
					int	arraySize = ((String[]) approvalLogObj).length;
					Set keySet = incomingRequest.keySet();
					
					for (int i=0; i < arraySize; i++) {
						ApprovalLogSetValues setValues = new ApprovalLogSetValues();
						
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
						ApprovalLog approvalLog = (ApprovalLog) setValues.executeTask(updateParameters);
						list.add(approvalLog);
						
						if(setValues.getStatus() != Status.SUCCEEDED)
						{
							this.setStatus(Status.FAILED);
							throw new TsaException("Error occurred setting values for approval log!");
						}
					}
				}
				else 
				{
				    incomingRequest.put("ApprovalLog_icHeader", incomingRequest.get("ApprovalLog_icHeader"));
					incomingRequest.put("ApprovalLog_icLine", "0");
					incomingRequest.put("ApprovalLog_sequence", "1");
					
					ApprovalLogSetValues setValues = new ApprovalLogSetValues();
					
					ApprovalLog approvalLog = (ApprovalLog) setValues.executeTask(incomingRequest);
					list.add(approvalLog);
				}
			}
			else 
			{
			    // No approvers to be updated
				//throw new Exception("The value for ApprovalLog_userId was not found.");
			}
			
			result = list;
			this.setStatus(Status.SUCCEEDED) ;
		}
		catch (Exception e) {
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}
}
