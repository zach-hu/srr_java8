package com.tsa.puridiom.vendoraffiliate.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Utility;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VendorAffiliateAddList extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			List vendorAffiliateList = new ArrayList();
			String organizationId = (String) incomingRequest.get("organizationId");

			if (incomingRequest.containsKey("VendorAffiliate_affiliateId")) {
				PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
				processLoader.setApplicationName(this.getApplicationName());
				PuridiomProcess process = processLoader.loadProcess("vendoraffiliate-add.xml");

				Object vendorIdObj = incomingRequest.get("VendorAffiliate_vendorId");
				Object affiliateIdObj = incomingRequest.get("VendorAffiliate_affiliateId");
				if (affiliateIdObj instanceof String[]) {
					String	affiliateIdArray[] = (String[]) affiliateIdObj;
					String	vendorId = "";
					if (vendorIdObj instanceof String[]) {
						String tempArray[] = (String[]) vendorIdObj;
						vendorId = tempArray[0];
					} else {
						vendorId = (String) vendorIdObj;
					}
										
					for (int i=0; i < affiliateIdArray.length; i++) {
			            if (Utility.isEmpty(affiliateIdArray[i])) {
			                continue;
			            }
			            if (Utility.isEmpty(vendorId)) {
			                throw new Exception("Vendor Id must be specified to add Vendor Affiliate");
			            }
						Map updateParameters = new HashMap();
						updateParameters.put("userId", incomingRequest.get("userId"));
						updateParameters.put("organizationId", organizationId);
						updateParameters.put("dbsession", incomingRequest.get("dbsession"));
						updateParameters.put("VendorAffiliate_vendorId", vendorId) ;
						updateParameters.put("VendorAffiliate_affiliateId", affiliateIdArray[i]) ;
												
						process.executeProcess(updateParameters);
						
						VendorAffiliate vendorAffiliate = (VendorAffiliate) updateParameters.get("vendorAffiliate");
						
						vendorAffiliateList.add(vendorAffiliate);
					}
				}
				else {
				    String affiliateId = (String) incomingRequest.get("VendorAffiliate_affiliateId");
				    if (!Utility.isEmpty(affiliateId)) {
					    process.executeProcess(incomingRequest);
						
						VendorAffiliate vendorAffiliate = (VendorAffiliate) incomingRequest.get("vendorAffiliate");
						vendorAffiliateList.add(vendorAffiliate);
				    }
				}
			}
			else {
				//No records to update
			}

			result = vendorAffiliateList;
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