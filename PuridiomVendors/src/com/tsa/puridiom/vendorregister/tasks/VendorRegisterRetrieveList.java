package com.tsa.puridiom.vendorregister.tasks;

import com.tsa.puridiom.entity.VendorRegister;
import com.tsagate.foundation.processengine.*;
import java.util.*;

public class VendorRegisterRetrieveList extends Task {
	public Object  executeTask (Object object) throws Exception {
		Map incomingRequest = (Map)object;
		Object result = null;

		try {
		    List	vendorRegisterList = new ArrayList();
			if (incomingRequest.containsKey("VendorRegister_vendorId") || incomingRequest.containsKey("VendorRegister_vendorId-Input_checkbox")) {
				PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
				PuridiomProcess process = processLoader.loadProcess("vendorregister-retrieve-by-vendorid.xml");
				Object vendorIdObj = incomingRequest.get("VendorRegister_vendorId");
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
					int	arraySize = vendorIds.length;
					Set keySet = incomingRequest.keySet();

					for (int i=0; i < arraySize; i++) {
						Map updateParameters = new HashMap();
						updateParameters.put("userId", incomingRequest.get("userId"));
						updateParameters.put("userTimeZone", incomingRequest.get("userTimeZone"));
						updateParameters.put("organizationId", incomingRequest.get("organizationId"));
						updateParameters.put("dbsession", incomingRequest.get("dbsession"));
						updateParameters.put("VendorRegister_vendorId", vendorIds[i]);
						updateParameters.put("vendorQualification", "Y");

						process.executeProcess(updateParameters);

						VendorRegister vendorRegister = (VendorRegister) updateParameters.get("vendorRegister");
						vendorRegisterList.add(vendorRegister);
					}
				}
				else {
					incomingRequest.put("VendorRegister_vendorId", (String) vendorIdObj);
					process.executeProcess(incomingRequest);

					VendorRegister vendorRegister = (VendorRegister) incomingRequest.get("vendorRegister");
					vendorRegisterList.add(vendorRegister);
				}
			}
			else {
				throw new Exception("The value for VendorRegister_vendorId was not found.");
			}

			incomingRequest.put("vendorRegisterList", vendorRegisterList);

			this.setStatus(Status.SUCCEEDED) ;
		}
		catch (Exception e) {
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}

}