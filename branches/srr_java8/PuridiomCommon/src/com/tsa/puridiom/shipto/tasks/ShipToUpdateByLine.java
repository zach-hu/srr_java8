/*
 * Created on Aug 19, 2003 
 */
package com.tsa.puridiom.shipto.tasks;

import java.util.*;

import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

/**
 * @author Administrator 
 */
public class ShipToUpdateByLine extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;
		Object result = null;
		
		try {
			if (incomingRequest.containsKey("ShipTo_icHeader")) {
				PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
				PuridiomProcess process = processLoader.loadProcess("shipto-update-line-row.xml");
				
				Object shipToCodeObj = incomingRequest.get("ShipTo_shipToCode");
				String	shipToTaxCodeSet = "N";
				if (shipToCodeObj instanceof String[]) {
					int	arraySize = ((String[]) shipToCodeObj).length;
					Set keySet = incomingRequest.keySet();
					
					for (int i=0; i < arraySize; i++) {
						Map updateParameters = new HashMap();
						updateParameters.put("userId", incomingRequest.get("userId"));
						updateParameters.put("userTimeZone", incomingRequest.get("userTimeZone"));
						updateParameters.put("organizationId", incomingRequest.get("organizationId"));
						updateParameters.put("dbsession", incomingRequest.get("dbsession"));
						updateParameters.put("ShipTo_icHeader",incomingRequest.get("ShipTo_icHeader")) ;
						updateParameters.put("ShipTo_icLine",incomingRequest.get("ShipTo_icLine")) ;
						
						Iterator iterator = keySet.iterator();
						while (iterator.hasNext()) {
							String key = (String) iterator.next();
							if (key.indexOf("ShipTo_") == 0) {
								Object obj =  incomingRequest.get(key);
								if (obj instanceof String[]) {
									String arrayObj[] = (String[]) obj;
									updateParameters.put(key, arrayObj[i]);
								}
							}
						}
						updateParameters.put("shipToTaxCodeSet", shipToTaxCodeSet);
						process.executeProcess(updateParameters);

					    shipToTaxCodeSet = (String) updateParameters.get("shipToTaxCodeSet"); 
					}
				}
				else {
						incomingRequest.put("ShipTo_icHeader",incomingRequest.get("ShipTo_icHeader")) ;
						incomingRequest.put("ShipTo_icLine",incomingRequest.get("ShipTo_icLine")) ;
						process.executeProcess(incomingRequest);
				}
				incomingRequest.put("shipToTaxCodeSet", shipToTaxCodeSet);
			}
			else {
				throw new Exception("The value for ShipTo_icHeader was not found.");
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
