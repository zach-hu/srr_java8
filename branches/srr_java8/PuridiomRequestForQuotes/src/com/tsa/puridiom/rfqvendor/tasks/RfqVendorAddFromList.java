package com.tsa.puridiom.rfqvendor.tasks;

import com.tsagate.foundation.processengine.*;
import java.util.*;

public class RfqVendorAddFromList extends Task {
	public Object  executeTask (Object object) throws Exception {
		Map incomingRequest = (Map)object;
		Object result = null;

		try {
			if (incomingRequest.containsKey("RfqVendor_vendorId")) {
				PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
				PuridiomProcess process = processLoader.loadProcess("rfqvendor-add-by-id.xml");
				Object obj = incomingRequest.get("RfqVendor_vendorId-Input_checkbox");
				String icRfqHeader = (String)incomingRequest.get("RfqHeader_icRfqHeader");

				List originalVendorList = (List) incomingRequest.get("originalRfqVendorList");

				if (obj instanceof String[]) {
					//if the vendorId is an array, loop through list of vendorId values and add each as an RfqVendor
					String vendorIds[] = (String[]) obj;
					int	arraySize = vendorIds.length;
					//Set keySet = incomingRequest.keySet();

					for (int i=0; i < arraySize; i++) {
						Map updateParameters = new HashMap();
						updateParameters.put("userId", incomingRequest.get("userId"));
						updateParameters.put("organizationId", incomingRequest.get("organizationId"));
						updateParameters.put("dbsession", incomingRequest.get("dbsession"));
						updateParameters.put("RfqVendor_icRfqHeader", incomingRequest.get("RfqVendor_icRfqHeader"));
						updateParameters.put("RfqHeader_icRfqHeader", icRfqHeader);
						updateParameters.put("RfqVendor_vendorId", vendorIds[i]);
						//updateParameters.put("Vendor_vendorId", vendorIds[i]);

						if (originalVendorList != null)
						{
							if (!originalVendorList.contains(vendorIds[i]))
							{
								process.executeProcess(updateParameters);
							}
						}
						else
						{
							process.executeProcess(updateParameters);
						}
					}
				}
				else {
					//incomingRequest.put("Vendor_vendorId", incomingRequest.get("RfqVendor_vendorId"));
					if (originalVendorList != null)
					{
						String vendorId = (String) incomingRequest.get("RfqVendor_vendorId-Input_checkbox");
						if (vendorId == null || vendorId.length() == 0) {
							vendorId = (String) incomingRequest.get("RfqVendor_vendorId");
						}
						incomingRequest.put("RfqVendor_vendorId", vendorId);
						if ( !originalVendorList.contains(vendorId) )
						{
							process.executeProcess(incomingRequest);
						}
					}
					else
					{
						process.executeProcess(incomingRequest);
					}
				}
			}
			else {
			//	throw new Exception("The value for RfqVendor_vendorId was not found.");
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