/*
 * Created on June 30, 2004
 */
package com.tsa.puridiom.invformproduct.tasks;

import java.util.*;

import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

/**
 * @author Administrator 
 */
public class InvFormProductUpdateById extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;
		Object result = null;
		
		try {
			if (incomingRequest.containsKey("InvFormProduct_productId")) {
				PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
				PuridiomProcess process = processLoader.loadProcess("invformproduct-update-by-id.xml");

				Object productIdObj = incomingRequest.get("InvFormProduct_productId");
				if (productIdObj instanceof String[]) {
					int	arraySize = ((String[]) productIdObj).length;
					Set keySet = incomingRequest.keySet();
					
					for (int i=0; i < arraySize; i++) {
						Map updateParameters = new HashMap();
						updateParameters.put("userId", incomingRequest.get("userId"));
						updateParameters.put("userTimeZone", incomingRequest.get("userTimeZone"));
						updateParameters.put("organizationId", incomingRequest.get("organizationId"));
						updateParameters.put("dbsession", incomingRequest.get("dbsession"));
						updateParameters.put("InvFormProduct_itemNumber",incomingRequest.get("InvFormProduct_itemNumber")) ;
						
						Iterator iterator = keySet.iterator();
						while (iterator.hasNext()) {
							String key = (String) iterator.next();
							if (key.indexOf("InvFormProduct_") == 0) {
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
						incomingRequest.put("InvFormProduct_itemNumber",incomingRequest.get("InvFormProduct_itemNumber")) ;
						process.executeProcess(incomingRequest);
				}
			}
			else {
				throw new Exception("The value for InvFormProduct_productId was not found.");
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
