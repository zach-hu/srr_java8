package com.tsa.puridiom.vendorresponse.tasks;

import com.tsa.puridiom.entity.VendorResponse;
import com.tsagate.foundation.processengine.*;
import java.util.*;

public class VendorResponseUpdateEntityList extends Task {
	public Object  executeTask (Object object) throws Exception {
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try {
			if (incomingRequest.containsKey("VendorResponse_vendorId")) {
			    PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
			    processLoader.setApplicationName(this.getApplicationName());
				PuridiomProcess process = processLoader.loadProcess("vendorresponse-update-by-id.xml");
				Object vendorIdObj = incomingRequest.get("VendorResponse_vendorId");
				List vendorResponseList = new ArrayList();
				
				if (vendorIdObj instanceof String[]) {
					//if the VendorResponse_vendorId is an array, all VendorResponse values should be an array of the same size
					int	arraySize = ((String[]) vendorIdObj).length;
					Set keySet = incomingRequest.keySet();
					
					for (int i=0; i < arraySize; i++) {
						Map updateParameters = new HashMap();
						updateParameters.put("userId", incomingRequest.get("userId"));
						updateParameters.put("organizationId", incomingRequest.get("organizationId"));
						updateParameters.put("dbsession", incomingRequest.get("dbsession"));
						
						Iterator iterator = keySet.iterator();
						while (iterator.hasNext()) {
							String key = (String) iterator.next();
							if (key.indexOf("VendorResponse_") == 0) {
								//must be an VendorResponse attribute and should be an array
								Object obj =  incomingRequest.get(key);
								if (obj instanceof String[]) {
									String arrayObj[] = (String[]) obj;
									updateParameters.put(key, arrayObj[i]);
								}
							}
						}
						process.executeProcess(updateParameters);

						VendorResponse vendorResponse = (VendorResponse) updateParameters.get("vendorResponse");
						vendorResponseList.add(vendorResponse);
					}
				}
				else {
						process.executeProcess(incomingRequest);

						VendorResponse vendorResponse = (VendorResponse) incomingRequest.get("vendorResponse");
						vendorResponseList.add(vendorResponse);
				}
				result = vendorResponseList;
			}
			else {
				throw new Exception("The value for VendorResponse_vendorId was not found.");
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