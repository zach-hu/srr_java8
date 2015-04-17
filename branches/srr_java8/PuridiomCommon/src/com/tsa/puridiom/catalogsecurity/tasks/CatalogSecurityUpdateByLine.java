/*
 * Created on Aug 19, 2003
 */
package com.tsa.puridiom.catalogsecurity.tasks;

import java.util.*;

import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.*;

/**
 * @author Administrator
 */
public class CatalogSecurityUpdateByLine extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;
		Object result = null;

		try {
			if (incomingRequest.containsKey("CatalogSecurity_catalogId")) {
				PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
				PuridiomProcess process = processLoader.loadProcess("catalogsecurity-add.xml");
				Object catalogIdObj = incomingRequest.get("CatalogSecurity_catalogId");

				if (catalogIdObj instanceof String[]) {
					int	arraySize = ((String[]) catalogIdObj).length;
					Set keySet = incomingRequest.keySet();

					for (int i=0; i < arraySize; i++) {
						Map updateParameters = new HashMap();

						updateParameters.put("organizationId", incomingRequest.get("organizationId"));
						updateParameters.put("dbsession", incomingRequest.get("dbsession"));

						Iterator iterator = keySet.iterator();
						while (iterator.hasNext()) {
							String key = (String) iterator.next();
							if (key.indexOf("CatalogSecurity_") == 0) {
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

						process.executeProcess(incomingRequest);
				}

			}
			else {
				throw new Exception("The value for CatalogSecurity was not found.");
			}

			this.setStatus(Status.SUCCEEDED) ;
		}
		catch (Exception e) {
			this.setStatus(Status.FAILED);
			Log.error(this, "An Error occurred at CatalogSecurity" + e);
			e.printStackTrace();
			throw e;

		}
		return result;
	}
}
