/*
 * Created on December 18, 2006
 */
package com.tsa.puridiom.catalogpricebrk.tasks;

import java.util.*;

import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

/**
 * @author kathleen
 */
public class CatalogPriceBrkUpdateByItem extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;
		Object result = null;

		try {
			if (incomingRequest.containsKey("CatalogPriceBrk_itemNumber")) {
				PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
				PuridiomProcess process = processLoader.loadProcess("catalogpricebrk-update.xml");

				Object pricebrkObj = incomingRequest.get("CatalogPriceBrk_sequence");
				if (pricebrkObj instanceof String[]) {
					int	arraySize = ((String[]) pricebrkObj).length;
					Set keySet = incomingRequest.keySet();

					for (int i=0; i < arraySize; i++) {
						Map updateParameters = new HashMap();
						updateParameters.put("userId", incomingRequest.get("userId"));
						updateParameters.put("userTimeZone", incomingRequest.get("userTimeZone"));
						updateParameters.put("organizationId", incomingRequest.get("organizationId"));
						updateParameters.put("dbsession", incomingRequest.get("dbsession"));
						updateParameters.put("CatalogPriceBrk_catalogId",incomingRequest.get("CatalogPriceBrk_catalogId"));
						updateParameters.put("CatalogPriceBrk_itemNumber",incomingRequest.get("CatalogPriceBrk_itemNumber"));

						Iterator iterator = keySet.iterator();
						while (iterator.hasNext()) {
							String key = (String) iterator.next();
							if (key.indexOf("CatalogPriceBrk_") == 0) {
								Object obj =  incomingRequest.get(key);
								if (obj instanceof String[]) {
									String arrayObj[] = (String[]) obj;
									updateParameters.put(key, arrayObj[i]);
								}
							}
						}
						process.executeProcess(updateParameters);
					}
				}
				else {
						incomingRequest.put("CatalogPriceBrk_catalogId",incomingRequest.get("CatalogPriceBrk_catalogId"));
						incomingRequest.put("CatalogPriceBrk_itemNumber",incomingRequest.get("CatalogPriceBrk_itemNumber"));
						process.executeProcess(incomingRequest);
				}
			}
			else {
				throw new Exception("The value for CatalogPriceBrk_itemNumber was not found.");
			}

			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e) {
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}
}
