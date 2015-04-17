/*
 * Created on December 23, 2004
 */
package com.tsa.puridiom.apprule.tasks;

import java.util.*;

import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

/**
 * @author Administrator
 */
public class AppRuleApprovalAmountUpdateByUser extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;
		Object result = null;

		try {
			if (incomingRequest.containsKey("AppRule_udf1Code")) {
				PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
				PuridiomProcess process = processLoader.loadProcess("apprule-userprofile-update.xml");

				Object amountObj = incomingRequest.get("AppRule_amount");
				if (amountObj instanceof String[]) {
					int	arraySize = ((String[]) amountObj).length;
					Set keySet = incomingRequest.keySet();

					for (int i=0; i < arraySize; i++) {
						Map updateParameters = new HashMap();
						updateParameters.put("userId", incomingRequest.get("userId"));
                        updateParameters.put("userTimeZone", incomingRequest.get("userTimeZone"));
                        updateParameters.put("organizationId", incomingRequest.get("organizationId"));
						updateParameters.put("UserProfile_organizationId", incomingRequest.get("organizationId"));
						updateParameters.put("dbsession", incomingRequest.get("dbsession"));
						updateParameters.put("AppRule_udf1Code",incomingRequest.get("AppRule_udf1Code"));

						Iterator iterator = keySet.iterator();
						while (iterator.hasNext()) {
							String key = (String) iterator.next();
							if (key.indexOf("AppRule_") == 0 || key.indexOf("UserProfile_") == 0) {
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
				throw new Exception("The value for AppRule_udf1Code was not found.");
			}

			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e) {
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}
}
