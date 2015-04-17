package com.tsa.puridiom.receiptline.tasks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.entity.ReceiptLine;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

public class ReceiptLineUpdateList extends Task {
	public Object  executeTask (Object object) throws Exception {
		Map incomingRequest = (Map)object;
		Object result = null;

		try {
		    List receiptLineList = new ArrayList();
			if (incomingRequest.containsKey("ReceiptLine_icRecHeader")) {
				PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
				processLoader.setApplicationName(this.getApplicationName());
				PuridiomProcess process = processLoader.loadProcess("receiptline-update-by-id.xml");
				PoHeader poHeader = (PoHeader) HiltonUtility.ckNull((PoHeader)incomingRequest.get("poHeader"));
				Object icRecLineObj = incomingRequest.get("ReceiptLine_icRecLine");

				if (icRecLineObj instanceof String[]) {
					//if the icRecHeader is an array, all ReceiptLine values should be an array of the same size
					int	arraySize = ((String[]) icRecLineObj).length;
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
						updateParameters.put("poHeader", incomingRequest.get("poHeader")) ;
						updateParameters.put("requisitionHeader", incomingRequest.get("requisitionHeader")) ;
						updateParameters.put("receiptMethod", incomingRequest.get("receiptMethod")) ;
						updateParameters.put("receiptMethodTemp", HiltonUtility.ckNull((String)incomingRequest.get("receiptMethodTemp")));

						if ("Adjustment".equalsIgnoreCase((String)incomingRequest.get("receiptMethod")))
						{
							updateParameters.put("ReceiptLine_status", "4150");
							if (poHeader.getKit().equals("Y")) {
								updateParameters.put("ReceiptLine_inventoryFlag", "K");
							}
							updateParameters.put("ReceiptLine_receiptLine", String.valueOf(i + 1));
							Object obj = incomingRequest.get("ReceiptLine_icPoLineHistory");
							if (obj instanceof String[]) {
								String array[] = (String[]) obj;
								updateParameters.put("ReceiptLine_icLineHistory", array[i]);
							}

						}

						Iterator iterator = keySet.iterator();
						while (iterator.hasNext()) {
							String key = (String) iterator.next();
							if (key.indexOf("ReceiptLine_") == 0 || key.indexOf("PoLine_") == 0) {
								//must be a ReceiptLine_ or PoLine_ attribute and should be an array
							    //PoLine attributes are needed for inventory updates
								Object obj =  incomingRequest.get(key);
								if (obj instanceof String[]) {
									String arrayObj[] = (String[]) obj;
									updateParameters.put(key, arrayObj[i]);
								}
							}
						}
						process.executeProcess(updateParameters);

						ReceiptLine receiptLine = (ReceiptLine) updateParameters.get("receiptLine");

						receiptLineList.add(receiptLine);
					}
				} else {
						if ("Adjustment".equalsIgnoreCase((String)incomingRequest.get("receiptMethod")))
						{
							incomingRequest.put("ReceiptLine_status", "4150");
							incomingRequest.put("ReceiptLine_receiptLine", String.valueOf(1));
							incomingRequest.put("ReceiptLine_icLineHistory", incomingRequest.get("ReceiptLine_icLineHistory"));
							if (poHeader.getKit().equals("Y")) {
								incomingRequest.put("ReceiptLine_inventoryFlag", "K");
							}
						}
						process.executeProcess(incomingRequest);

						ReceiptLine receiptLine = (ReceiptLine) incomingRequest.get("receiptLine");

						receiptLineList.add(receiptLine);
				}
			}
			else {
				Log.debug(this, "The value for ReceiptLine_icRecHeader was not found.");
				throw new Exception("We're sorry, but we could not find a receipt for this order.");
			}

			result = receiptLineList;

			this.setStatus(Status.SUCCEEDED) ;
		}
		catch (Exception e) {
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}

}