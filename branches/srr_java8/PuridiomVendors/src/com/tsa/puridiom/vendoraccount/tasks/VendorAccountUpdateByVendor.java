/*
 * Created on June 12, 2006
 */
package com.tsa.puridiom.vendoraccount.tasks;

import java.util.*;

import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

/**
 * @author Kathleen
 */
public class VendorAccountUpdateByVendor extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;
		Object result = null;

		try {
			if (incomingRequest.containsKey("VendorAccount_vendorId")) {
				PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
				PuridiomProcess process = processLoader.loadProcess("vendoraccount-update.xml");

				Object acctNumberObj = incomingRequest.get("VendorAccount_accountNumber");
				if (acctNumberObj instanceof String[]) {
					int	arraySize = ((String[]) acctNumberObj).length;
					Set keySet = incomingRequest.keySet();

					for (int i=0; i < arraySize; i++) {
						Map updateParameters = new HashMap();
						updateParameters.put("userId", incomingRequest.get("userId"));
						updateParameters.put("organizationId", incomingRequest.get("organizationId"));
						updateParameters.put("dbsession", incomingRequest.get("dbsession"));
						updateParameters.put("VendorAccount_vendorId",incomingRequest.get("VendorAccount_vendorId"));

						Iterator iterator = keySet.iterator();
						while (iterator.hasNext()) {
							String key = (String) iterator.next();
							if (key.indexOf("VendorAccount_") == 0) {
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
				else
				{
						incomingRequest.put("VendorAccount_vendorId",incomingRequest.get("VendorAccount_vendorId")) ;
						process.executeProcess(incomingRequest);
				}
			}
			else
			{
				throw new Exception("The value for VendorAccount_vendorId was not found.");
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
