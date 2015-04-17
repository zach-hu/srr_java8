/*
 * Created on December 23, 2004
 */
package com.tsa.puridiom.trackingnumber.tasks;

import java.util.*;

import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.UniqueKeyGenerator;

/**
 * @author Administrator
 */
public class TrackingDataUpdateById extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;
		Object result = null;

		try {
			if (incomingRequest.containsKey("TrackingData_trackingNumber")) {
				PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
				PuridiomProcess process = processLoader.loadProcess("trackingdata-update.xml");
				UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();

				Object trackingNumber = incomingRequest.get("TrackingData_trackingNumber");
				if (trackingNumber instanceof String[]) {
					int	arraySize = ((String[]) trackingNumber).length;
					Set keySet = incomingRequest.keySet();

					for (int i=0; i < arraySize; i++) {
						Map updateParameters = new HashMap();
						updateParameters.put("TrackingData_icTracking",ukg.getUniqueKey().toString());
						updateParameters.put("userId", incomingRequest.get("userId"));
                        updateParameters.put("userTimeZone", incomingRequest.get("userTimeZone"));
                        updateParameters.put("organizationId", incomingRequest.get("organizationId"));
						updateParameters.put("dbsession", incomingRequest.get("dbsession"));
						updateParameters.put("TrackingData_icHeader", incomingRequest.get("TrackingData_icHeader"));
						updateParameters.put("TrackingData_icLine", i + "1");

						Iterator iterator = keySet.iterator();
						while (iterator.hasNext()) {
							String key = (String) iterator.next();
							if (key.indexOf("TrackingData_") == 0) {
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
					Map updateParameters = new HashMap();
					updateParameters.put("TrackingData_icTracking",ukg.getUniqueKey().toString());
					updateParameters.put("TrackingData_icHeader", incomingRequest.get("TrackingData_icHeader"));
					updateParameters.put("TrackingData_icLine", "1");
					updateParameters.put("TrackingData_trackingNumber", incomingRequest.get("TrackingData_trackingNumber"));
					updateParameters.put("TrackingData_trackingDesc", incomingRequest.get("TrackingData_trackingDesc"));
					updateParameters.put("userId", incomingRequest.get("userId"));
                    updateParameters.put("userTimeZone", incomingRequest.get("userTimeZone"));
                    updateParameters.put("organizationId", incomingRequest.get("organizationId"));
					updateParameters.put("dbsession", incomingRequest.get("dbsession"));
					process.executeProcess(updateParameters);
				}
			}
			else {
				throw new Exception("The value for AppRule_userId was not found.");
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
