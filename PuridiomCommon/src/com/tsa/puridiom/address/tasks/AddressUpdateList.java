package com.tsa.puridiom.address.tasks;

import com.tsagate.foundation.processengine.*;
import java.util.*;

public class AddressUpdateList extends Task {
	public Object  executeTask (Object object) throws Exception {
		Map incomingRequest = (Map)object;
		Object result = null;

		try {
			if (incomingRequest.containsKey("Address_addressType")) {
				PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
				processLoader.setApplicationName(this.getApplicationName());
				PuridiomProcess process = processLoader.loadProcess("address-update-by-id.xml");
				Object addressTypeObj = incomingRequest.get("Address_addressType");

				if (addressTypeObj instanceof String[]) {
					//if the icRfqHeader is an array, all RfqBid values should be an array of the same size
					int	arraySize = ((String[]) addressTypeObj).length;
					Set keySet = incomingRequest.keySet();

					for (int i=0; i < arraySize; i++) {
						Map updateParameters = new HashMap();
						updateParameters.put("userId", incomingRequest.get("userId"));
                        updateParameters.put("userTimeZone", incomingRequest.get("userTimeZone"));
                        updateParameters.put("organizationId", incomingRequest.get("organizationId"));
						updateParameters.put("dbsession", incomingRequest.get("dbsession"));

						Iterator iterator = keySet.iterator();
						while (iterator.hasNext()) {
							String key = (String) iterator.next();
							if (key.indexOf("Address_") == 0) {
								//must be an Address attribute and should be an array
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
				throw new Exception("The value for Contact_contactCode was not found.");
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