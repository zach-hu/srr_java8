package com.tsa.puridiom.vendor.tasks;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.vendorcommrel.tasks.VendorCommRelSetValues;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Utility;

import java.util.*;

public class VendorValidationSetup extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			List	commodityList = new ArrayList();
			
			if (incomingRequest.containsKey("VendorCommRel_commodityCode")) {
				Object vendorIdObj = incomingRequest.get("VendorCommRel_vendorId");
				Object commodityCodeObj = incomingRequest.get("VendorCommRel_commodityCode");
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
			                throw new Exception("Vendor Id must be specified to add VendorCommRel");
			            }
						Map requestParameters = new HashMap();
						requestParameters.put("VendorCommRel_vendorId", vendorId) ;
						requestParameters.put("VendorCommRel_commodityCode", commodityCodeArray[i]) ;
						VendorCommRelSetValues setValuesTask = new VendorCommRelSetValues();
						VendorCommRel vendorCommRel = (VendorCommRel) setValuesTask.executeTask(requestParameters);
						
						commodityList.add(vendorCommRel);
					}
				}
				else {
				    String commodityCode = (String) incomingRequest.get("VendorCommRel_commodityCode");
				    if (!Utility.isEmpty(commodityCode)) {
					    VendorCommRelSetValues setValuesTask = new VendorCommRelSetValues();
						VendorCommRel vendorCommRel = (VendorCommRel) setValuesTask.executeTask(incomingRequest);
						
						commodityList.add(vendorCommRel);
				    }
				}
			}
			else {
				//No commodity records found
			}
			
			incomingRequest.put("vendorCommRelList", commodityList);
			if (HiltonUtility.ckNull(this.applicationName).equals("supplierportal")) {
				incomingRequest.put("Labels_moduleAccess", "SUPPLIERPORTAL");
			} else {
				incomingRequest.put("Labels_moduleAccess", "SUPPLIER");
			}
			
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