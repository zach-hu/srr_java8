/*
 * Created on Oct 16, 2008
 */
package com.tsa.puridiom.stdtable.tasks;

import java.util.*;

import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.*;

/**
 * @author Kelli
 */
public class StdTableUpdateByType extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;
		Object result = null;

		try {
			if (incomingRequest.containsKey("StdTable_tableType")) {
				PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
				PuridiomProcess process = processLoader.loadProcess("stdtable-add-by-id.xml");
				Object tableKeyObj = incomingRequest.get("StdTable_tableKey");

				if (tableKeyObj instanceof String[]) {
					int	arraySize = ((String[]) tableKeyObj).length;
					Set keySet = incomingRequest.keySet();

					for (int i=0; i < arraySize; i++) {
						Map updateParameters = new HashMap();
						updateParameters.put("userId", incomingRequest.get("userId"));
         updateParameters.put("userTimeZone", incomingRequest.get("userTimeZone"));
						updateParameters.put("organizationId", incomingRequest.get("organizationId"));
						updateParameters.put("dbsession", incomingRequest.get("dbsession"));
						updateParameters.put("StdTable_tableType",incomingRequest.get("StdTable_tableType")) ;

						Iterator iterator = keySet.iterator();
						while (iterator.hasNext()) {
							String key = (String) iterator.next();
							if (key.indexOf("StdTable_") == 0) {
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
				throw new Exception("The value for StdTable_tableType was not found.");
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
