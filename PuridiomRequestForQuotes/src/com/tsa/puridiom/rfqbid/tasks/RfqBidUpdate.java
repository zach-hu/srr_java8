package com.tsa.puridiom.rfqbid.tasks;

import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.TsaException;

import java.util.*;

public class RfqBidUpdate extends Task {
	public Object  executeTask (Object object) throws Exception {
		Map incomingRequest = (Map)object;
		Object result = null;

		try {
			if (incomingRequest.containsKey("RfqBid_icRfqHeader")) {
				PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
				processLoader.setApplicationName(this.getApplicationName());
				PuridiomProcess process = processLoader.loadProcess("rfqbid-update-by-id.xml");
				Object icRfqHeaderObj = incomingRequest.get("RfqBid_icRfqHeader");
				Object icRfqLineObj = incomingRequest.get("RfqBid_icRfqLine");
				Object vendorIdObj = incomingRequest.get("RfqBid_vendorId");
				String icRfqLineArray[] = null;
				String icRfqHeaderArray[] = null;
				String vendorIdArray[] = null;

				if (icRfqHeaderObj instanceof String[]) {
				    icRfqHeaderArray = (String[]) icRfqHeaderObj;
				}
				if (icRfqLineObj instanceof String[]) {
				    icRfqLineArray = (String[]) icRfqLineObj;
				}
				if (vendorIdObj instanceof String[]) {
				    vendorIdArray = (String[]) vendorIdObj;
				}

				if (vendorIdObj instanceof String[]) {
					//if the icRfqHeader is an array, all RfqBid values should be an array of the same size
					int	arraySize = ((String[]) vendorIdObj).length;
					Set keySet = incomingRequest.keySet();

					for (int i=0; i < arraySize; i++)
					{
						Map updateParameters = new HashMap();
						updateParameters.put("userId", incomingRequest.get("userId"));
						updateParameters.put("organizationId", incomingRequest.get("organizationId"));
						updateParameters.put("dbsession", incomingRequest.get("dbsession"));
						updateParameters.put("RfqHeader_icRfqHeader", incomingRequest.get("RfqHeader_icRfqHeader")) ;

						Iterator iterator = keySet.iterator();
						while (iterator.hasNext()) {
							String key = (String) iterator.next();
							if (key.indexOf("RfqBid_") == 0) {
								//must be an RfqBid attribute and should be an array
								Object obj =  incomingRequest.get(key);
								if (obj instanceof String[]) {
									String arrayObj[] = (String[]) obj;
									updateParameters.put(key, arrayObj[i]);
								}
							}
						}

						if (icRfqHeaderObj instanceof String[]) {
							if (icRfqHeaderArray != null && icRfqHeaderArray.length >= 0) {
							    updateParameters.put("RfqBid_icRfqHeader", icRfqHeaderArray[i]) ;
							}
						} else {
							updateParameters.put("RfqBid_icRfqHeader", icRfqHeaderObj) ;
						}

						if (icRfqLineObj instanceof String[]) {
							if (icRfqLineArray != null && icRfqLineArray.length >= 0) {
							    updateParameters.put("RfqBid_icRfqLine", icRfqLineArray[i]) ;
							}
						} else {
							updateParameters.put("RfqBid_icRfqLine", icRfqLineObj) ;
						}

						if (vendorIdArray != null && vendorIdArray.length >= i) {
						    updateParameters.put("RfqBid_vendorId", vendorIdArray[i]) ;
						}

						process.executeProcess(updateParameters);
						if(process.getStatus() != Status.SUCCEEDED)
						{
							this.setStatus(Status.FAILED);
							throw new TsaException("Error ocurred updating rfq bids!");
						}
					}
				}
				else if (vendorIdObj != null) {
						process.executeProcess(incomingRequest);
				}
			}
			else {
				throw new Exception("The value for RfqBid_icRfqHeader was not found.");
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