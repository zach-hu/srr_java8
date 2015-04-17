package com.tsa.puridiom.receiptline.tasks;

import com.tsa.puridiom.entity.ReceiptLine;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Log;

import java.util.*;

public class ReceiptLineSetList extends Task {
	public Object  executeTask (Object object) throws Exception {
		Map incomingRequest = (Map)object;
		Object result = null;

		try {
			if (incomingRequest.containsKey("ReceiptLine_icRecHeader")) {
				PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
				processLoader.setApplicationName(this.getApplicationName());
				PuridiomProcess process = processLoader.loadProcess("receiptline-create-for-validations.xml");
				Object icRecHeaderObj = incomingRequest.get("ReceiptLine_icRecHeader");
				List receiptLineList = new ArrayList();

				if (icRecHeaderObj instanceof String[]) {
					//if the icRecHeader is an array, all ReceiptLine values should be an array of the same size
					int	arraySize = ((String[]) icRecHeaderObj).length;
					Set keySet = incomingRequest.keySet();

					for (int i=0; i < arraySize; i++) {
						Map updateParameters = new HashMap();
						updateParameters.put("userId", incomingRequest.get("userId"));
						updateParameters.put("userTimeZone", incomingRequest.get("userTimeZone"));
						updateParameters.put("organizationId", incomingRequest.get("organizationId"));
						updateParameters.put("dbsession", incomingRequest.get("dbsession"));
						updateParameters.put("receiptHeader", incomingRequest.get("receiptHeader"));
						updateParameters.put("receiptUpdate", "true");
						updateParameters.put("InvBinLocation_tempIc", incomingRequest.get("InvBinLocation_tempIc"));

						Iterator iterator = keySet.iterator();
						while (iterator.hasNext()) {
							String key = (String) iterator.next();
							if (key.indexOf("ReceiptLine_") == 0 || key.indexOf("PoLine_") == 0) {
								//must be a ReceiptLine_ or PoLine_ attribute and should be an array
							    //PoLine attributes are needed for inventory updates
								Object obj =  incomingRequest.get(key);
								if (obj instanceof String[]) {
									String arrayObj[] = (String[]) obj;
									if (arrayObj.length > i) {
										updateParameters.put(key, arrayObj[i]);
									}
								}
							}
						}
						process.executeProcess(updateParameters);

						ReceiptLine receiptLine = (ReceiptLine) updateParameters.get("receiptLine");
						receiptLineList.add(receiptLine);
					}
				}
				else {
						process.executeProcess(incomingRequest);

						ReceiptLine receiptLine = (ReceiptLine) incomingRequest.get("receiptLine");
						receiptLineList.add(receiptLine);
				}

				result = receiptLineList;
			}
			else {
				Log.debug(this, "The value for ReceiptLine_icRecHeader was not found.");
				throw new Exception("We're sorry, but we could not find a receipt for this order.");
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