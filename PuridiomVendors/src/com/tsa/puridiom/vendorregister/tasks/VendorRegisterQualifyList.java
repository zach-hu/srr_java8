package com.tsa.puridiom.vendorregister.tasks;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.Vendor;
import com.tsagate.foundation.processengine.*;
import java.util.*;

public class VendorRegisterQualifyList extends Task {
	public Object  executeTask (Object object) throws Exception {
		Map incomingRequest = (Map)object;
		Object result = null;

		try {
		    List	vendorList = new ArrayList();
			if (incomingRequest.containsKey("VendorRegister_vendorId") || incomingRequest.containsKey("VendorRegister_vendorId-Input_checkbox")) {
				PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
				PuridiomProcess process = processLoader.loadProcess("vendorregister-qualify-by-id.xml");
				Object vendorIdObj = incomingRequest.get("VendorRegister_vendorId");
				Object newVendorIdObj = incomingRequest.get("newVendorRegister_vendorId");
				if(vendorIdObj == null)
				{
					vendorIdObj = incomingRequest.get("VendorRegister_vendorId-Input_checkbox");
				}
				String vendorIds[];
				if (vendorIdObj instanceof String[])	
				{
					vendorIds = (String[]) vendorIdObj;
				}
	            else 
	            {
	            	vendorIds = new String[1];
				}
				if (vendorIdObj instanceof String[]) {
					//if the vendorId is an array, loop through list of vendorId values and add each as VendorRegister_vendorId
					String newVendorIds[] = null;
					if (newVendorIdObj != null)
					{
						newVendorIds = (String[]) newVendorIdObj;
					}
					int	arraySize = vendorIds.length;
					Set keySet = incomingRequest.keySet();

					for (int i=0; i < arraySize; i++) {
						Map updateParameters = new HashMap();
						updateParameters.put("userId", incomingRequest.get("userId"));
						updateParameters.put("userTimeZone", incomingRequest.get("userTimeZone"));
						updateParameters.put("organizationId", incomingRequest.get("organizationId"));
						updateParameters.put("dbsession", incomingRequest.get("dbsession"));
						updateParameters.put("VendorRegister_vendorId", vendorIds[i]);
						if (newVendorIds != null)
						{
							updateParameters.put("newVendorRegister_vendorId", newVendorIds[i]);
						}					
						updateParameters.put("vendorQualification", "Y");
						process.executeProcess(updateParameters);
						
						Vendor vendor = (Vendor) updateParameters.get("vendor");
						vendorList.add(vendor);
					}
				}
				else {
					incomingRequest.put("VendorRegister_vendorId", (String) vendorIdObj);
					process.executeProcess(incomingRequest);

					Vendor vendor = (Vendor) incomingRequest.get("vendor");
					vendorList.add(vendor);
				}
			}
			else {
				throw new Exception("The value for VendorRegister_vendorId was not found.");
			}

			incomingRequest.put("vendorList", vendorList);

			this.setStatus(Status.SUCCEEDED) ;
		}
		catch (Exception e) {
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}

}