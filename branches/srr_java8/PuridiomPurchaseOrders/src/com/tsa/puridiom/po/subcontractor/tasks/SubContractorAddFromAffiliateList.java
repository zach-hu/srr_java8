package com.tsa.puridiom.po.subcontractor.tasks;

import com.tsagate.foundation.processengine.*;
import com.tsa.puridiom.common.utility.HiltonUtility;

import java.util.*;

public class SubContractorAddFromAffiliateList extends Task {
	public Object  executeTask (Object object) throws Exception {
		Map incomingRequest = (Map)object;
		Object result = null;

		try {
			if (incomingRequest.containsKey("SubContractor_affiliateId")) {
				PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
				PuridiomProcess process = processLoader.loadProcess("subcontractor-add.xml");
				Object affiliateIdObj = incomingRequest.get("SubContractor_affiliateId");
				Object subNameObj = incomingRequest.get("SubContractor_subName");
				
				Object checkboxObj = incomingRequest.get("c_checkbox");
				String icPoHeader = (String) incomingRequest.get("PoHeader_icPoHeader");
				List originalSubContractorList = (List) incomingRequest.get("originalSubContractorList");

				if (checkboxObj instanceof String[]) {
					//if the vendorId is an array, loop through list of vendorId values and add each as a SubContractor
					String checkboxValues[] = (String[]) checkboxObj;
					String affiliateIds[] = (String[]) affiliateIdObj;
					int	arraySize = checkboxValues.length;

					for (int i=0; i < arraySize; i++) {
						String selectedValue = checkboxValues[i];
						if (!HiltonUtility.isEmpty(selectedValue) && selectedValue.equals("Y")) {
							Map updateParameters = new HashMap();
							updateParameters.put("userId", incomingRequest.get("userId"));
							updateParameters.put("organizationId", incomingRequest.get("organizationId"));
							updateParameters.put("dbsession", incomingRequest.get("dbsession"));
							
							if (!incomingRequest.containsKey("SubContractor_poNumber")) {
								updateParameters.put("SubContractor_poNumber", incomingRequest.get("PoHeader_poNumber"));
							}
							if (!incomingRequest.containsKey("SubContractor_releaseNumber")) {
								updateParameters.put("SubContractor_releaseNumber", incomingRequest.get("PoHeader_releaseNumber"));
							}
							if (!incomingRequest.containsKey("SubContractor_contractno")) {
								updateParameters.put("SubContractor_contractno", incomingRequest.get("PoHeader_contractNo"));
							}
	
							Set keySet = incomingRequest.keySet();
							Iterator iterator = keySet.iterator();
							while (iterator.hasNext()) {
								String key = (String) iterator.next();
								if (key.indexOf("SubContractor_") == 0) {
									Object obj =  incomingRequest.get(key);
									if (obj instanceof String[]) {
										String array[] = (String[]) obj;
										if (array.length >= arraySize) {
											updateParameters.put(key, array[i]);
										}
									} else {
										updateParameters.put(key, obj);
									}
								}
							}
							
							if (originalSubContractorList == null || !originalSubContractorList.contains(affiliateIds[i]))
							{
								process.executeProcess(updateParameters);
							}
						}
					}
				}
				else {
					Map updateParameters = new HashMap();
					updateParameters.put("userId", incomingRequest.get("userId"));
					updateParameters.put("organizationId", incomingRequest.get("organizationId"));
					updateParameters.put("dbsession", incomingRequest.get("dbsession"));
					
					if (!incomingRequest.containsKey("SubContractor_poNumber")) {
						updateParameters.put("SubContractor_poNumber", incomingRequest.get("PoHeader_poNumber"));
					}
					if (!incomingRequest.containsKey("SubContractor_releaseNumber")) {
						updateParameters.put("SubContractor_releaseNumber", incomingRequest.get("PoHeader_releaseNumber"));
					}
					if (!incomingRequest.containsKey("SubContractor_contractno")) {
						updateParameters.put("SubContractor_contractno", incomingRequest.get("PoHeader_contractNo"));
					}

					Set keySet = incomingRequest.keySet();
					Iterator iterator = keySet.iterator();
					while (iterator.hasNext()) {
						String key = (String) iterator.next();
						if (key.indexOf("SubContractor_") == 0) {
							Object obj =  incomingRequest.get(key);
							if (obj instanceof String[]) {
								String array[] = (String[]) obj;
								updateParameters.put(key, array[0]);
							} else {
								updateParameters.put(key, obj);
							}
						}
					}
					
					process.executeProcess(updateParameters);
				}
			}
			else {
			//	throw new Exception("The value for SubContractor_affiliateId was not found.");
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