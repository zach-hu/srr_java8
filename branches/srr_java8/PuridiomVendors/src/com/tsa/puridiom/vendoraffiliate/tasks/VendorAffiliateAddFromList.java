package com.tsa.puridiom.vendoraffiliate.tasks;

import com.tsagate.foundation.processengine.*;
import com.tsa.puridiom.common.utility.HiltonUtility;

import java.util.*;

public class VendorAffiliateAddFromList extends Task {
	public Object  executeTask (Object object) throws Exception {
		Map incomingRequest = (Map)object;
		Object result = null;

		try {
			if (incomingRequest.containsKey("VendorAffiliate_vendorId")) {
				PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
				PuridiomProcess process = processLoader.loadProcess("vendoraffiliate-add.xml");
				Object affiliateIdObj = incomingRequest.get("VendorAffiliate_affiliateId");
				String icPoHeader = (String) incomingRequest.get("VendorAffiliate_icPoHeader");
				String vendorId = (String)incomingRequest.get("Vendor_vendorId");
				
				if (HiltonUtility.isEmpty(vendorId)) {
					vendorId = (String) incomingRequest.get("VendorAffiliate_vendorId");
				}
				
				if (HiltonUtility.isEmpty(icPoHeader)) {
					icPoHeader = (String) incomingRequest.get("PoHeader_icPoHeader");
				}
				if (HiltonUtility.isEmpty(icPoHeader)) {
					icPoHeader = "0";
				}

				List originalVendorList = (List) incomingRequest.get("originalVendorAffiliateList");

				if (affiliateIdObj instanceof String[]) {
					//if the vendorId is an array, loop through list of vendorId values and add each as a VendorAffiliate
					String affiliateIds[] = (String[]) affiliateIdObj;
					int	arraySize = affiliateIds.length;

					for (int i=0; i < arraySize; i++) {
						Map updateParameters = new HashMap();
						updateParameters.put("userId", incomingRequest.get("userId"));
						updateParameters.put("organizationId", incomingRequest.get("organizationId"));
						updateParameters.put("dbsession", incomingRequest.get("dbsession"));
						updateParameters.put("VendorAffiliate_vendorId", vendorId);
						updateParameters.put("VendorAffiliate_affiliateId", affiliateIds[i]);
						updateParameters.put("VendorAffiliate_icPoHeader", icPoHeader);

						if (originalVendorList != null)
						{
							if (!originalVendorList.contains(affiliateIds[i]))
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
					if (originalVendorList != null) {
						String affiliateId = (String) incomingRequest.get("VendorAffiliate_affiliateId");
						
						if (!HiltonUtility.isEmpty(affiliateId) && !originalVendorList.contains(affiliateId)) {
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
			//	throw new Exception("The value for VendorAffiliate_vendorId was not found.");
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