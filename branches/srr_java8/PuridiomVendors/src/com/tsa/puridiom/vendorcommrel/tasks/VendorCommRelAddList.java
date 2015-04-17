package com.tsa.puridiom.vendorcommrel.tasks;

import com.tsa.puridiom.commodity.CommodityManager;
import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Utility;

import java.math.BigDecimal;
import java.util.*;

public class VendorCommRelAddList extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			List commodityList = new ArrayList();
			String organizationId = (String) incomingRequest.get("organizationId");
			BigDecimal newIclLevel = new BigDecimal(0);
			String insCategoryLevelActive = PropertiesManager.getInstance(organizationId).getProperty("VENDOR", "INSCATLVLACTIVE", "N");

			if (incomingRequest.containsKey("VendorCommRel_commodityCode")) {
				PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
				processLoader.setApplicationName(this.getApplicationName());
				PuridiomProcess process = processLoader.loadProcess("vendorcommrel-add.xml");

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
						Map updateParameters = new HashMap();
						updateParameters.put("userId", incomingRequest.get("userId"));
						updateParameters.put("organizationId", organizationId);
						updateParameters.put("dbsession", incomingRequest.get("dbsession"));
						updateParameters.put("VendorCommRel_vendorId", vendorId) ;
						updateParameters.put("VendorCommRel_commodityCode", commodityCodeArray[i]) ;
												
						process.executeProcess(updateParameters);
						
						VendorCommRel vendorCommRel = (VendorCommRel) updateParameters.get("vendorCommRel");
						
						commodityList.add(vendorCommRel);
						
						if (insCategoryLevelActive.equalsIgnoreCase("Y"))
						{
							Commodity commodity = CommodityManager.getInstance().getCommodity(organizationId, commodityCodeArray[i]);
							if (commodity != null && commodity.getIclLevel().compareTo(newIclLevel) > 0) {
								newIclLevel = commodity.getIclLevel();
							}
						}
					}
				}
				else {
				    String commodityCode = (String) incomingRequest.get("VendorCommRel_commodityCode");
				    if (!Utility.isEmpty(commodityCode)) {
					    process.executeProcess(incomingRequest);
						
						VendorCommRel vendorCommRel = (VendorCommRel) incomingRequest.get("vendorCommRel");
						commodityList.add(vendorCommRel);
				    }
				}
			}
			else {
				//No records to update
			}

			incomingRequest.put("Vendor_iclLevel", newIclLevel.toString());

			result = commodityList;
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