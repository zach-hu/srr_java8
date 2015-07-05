/*
 * Created on Aug 19, 2003
 */
package com.tsa.puridiom.billto.tasks;

import java.util.*;

import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

/**
 * @author Administrator
 */
public class BillToUpdateByLine extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;
		Object result = null;

		try {
			if (incomingRequest.containsKey("BillTo_icHeader")) {
				PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
				PuridiomProcess process = processLoader.loadProcess("billto-update-line-row.xml");

				Object billToCodeObj = incomingRequest.get("BillTo_billToCode");
				if (billToCodeObj instanceof String[]) {
					int	arraySize = ((String[]) billToCodeObj).length;
					Set keySet = incomingRequest.keySet();

					for (int i=0; i < arraySize; i++) {
						Map updateParameters = new HashMap();
						updateParameters.put("userId", incomingRequest.get("userId"));
                        updateParameters.put("userTimeZone", incomingRequest.get("userTimeZone"));
                        updateParameters.put("organizationId", incomingRequest.get("organizationId"));
						updateParameters.put("dbsession", incomingRequest.get("dbsession"));
						updateParameters.put("BillTo_icHeader",incomingRequest.get("BillTo_icHeader")) ;
						updateParameters.put("BillTo_icLine",incomingRequest.get("BillTo_icLine")) ;

						Iterator iterator = keySet.iterator();
						while (iterator.hasNext()) {
							String key = (String) iterator.next();
							if (key.indexOf("BillTo_") == 0) {
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
						incomingRequest.put("BillTo_icHeader",incomingRequest.get("BillTo_icHeader")) ;
						incomingRequest.put("BillTo_icLine",incomingRequest.get("BillTo_icLine")) ;
						process.executeProcess(incomingRequest);
				}
			}
			else {
				throw new Exception("The value for BillTo_icHeader was not found.");
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