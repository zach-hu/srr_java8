package com.tsa.puridiom.vendorregister.tasks;

import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.vendorregcommrel.tasks.VendorRegCommRelSetValues;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Utility;

import java.util.*;

public class VendorRegisterValidationSetup extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			List	commodityList = new ArrayList();
			
			if (incomingRequest.containsKey("VendorRegCommRel_commodityCode")) {
				Object vendorIdObj = incomingRequest.get("VendorRegCommRel_vendorId");
				Object commodityCodeObj = incomingRequest.get("VendorRegCommRel_commodityCode");
				if (commodityCodeObj instanceof String[]) {
					String	commodityCodeArray[] = (String[]) commodityCodeObj;
					String	vendorId = "";
					if (vendorIdObj instanceof String[]) {
						String tempArray[] = (String[]) vendorIdObj;
						vendorId = tempArray[0];
					} else {
						vendorId = (String) vendorIdObj;
					}
										
					for (int i=0; i < commodityCodeArray.length; i++) {
			            if (Utility.isEmpty(commodityCodeArray[i])) {
			                continue;
			            }
			            if (Utility.isEmpty(vendorId)) {
			                throw new Exception("Vendor Id must be specified to add VendorRegCommRel");
			            }
						Map requestParameters = new HashMap();
						requestParameters.put("VendorRegCommRel_vendorId", vendorId) ;
						requestParameters.put("VendorRegCommRel_commodityCode", commodityCodeArray[i]) ;
						VendorRegCommRelSetValues setValuesTask = new VendorRegCommRelSetValues();
						VendorRegCommRel vendorRegCommRel = (VendorRegCommRel) setValuesTask.executeTask(requestParameters);
						
						commodityList.add(vendorRegCommRel);
					}
				}
				else {
				    String commodityCode = (String) incomingRequest.get("VendorRegCommRel_commodityCode");
				    if (!Utility.isEmpty(commodityCode)) {
					    VendorRegCommRelSetValues setValuesTask = new VendorRegCommRelSetValues();
						VendorRegCommRel vendorRegCommRel = (VendorRegCommRel) setValuesTask.executeTask(incomingRequest);
						
						commodityList.add(vendorRegCommRel);
				    }
				}
			}
			else {
				//No commodity records found
			}
			
			incomingRequest.put("vendorRegCommRelList", commodityList);
			incomingRequest.put("Labels_moduleAccess", "SUPPLIERPORTAL");
			incomingRequest.put("validationType", "vendorregistration");
			
			this.setStatus(Status.SUCCEEDED) ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}

}