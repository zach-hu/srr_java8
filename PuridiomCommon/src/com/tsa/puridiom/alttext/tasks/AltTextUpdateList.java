package com.tsa.puridiom.alttext.tasks;

import com.tsagate.foundation.processengine.*;
import java.util.*;

public class AltTextUpdateList extends Task {
	public Object  executeTask (Object object) throws Exception {
		Map incomingRequest = (Map)object;
		Object result = null;

		try {
			if (incomingRequest.containsKey("AltText_language")) {
				PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
				processLoader.setApplicationName(this.getApplicationName());
				PuridiomProcess process = processLoader.loadProcess("alttext-add.xml");
				Object languageObj = incomingRequest.get("AltText_language");

				if (languageObj instanceof String[]) {
					//if the AltText_language is an array, all AltText and DocText values should be an array of the same size
					int	arraySize = ((String[]) languageObj).length;
					Set keySet = incomingRequest.keySet();

					for (int i=0; i < arraySize; i++) {
						Map updateParameters = new HashMap();
						updateParameters.put("userId", incomingRequest.get("userId"));
                        updateParameters.put("userTimeZone", incomingRequest.get("userTimeZone"));
                        updateParameters.put("organizationId", incomingRequest.get("organizationId"));
						updateParameters.put("dbsession", incomingRequest.get("dbsession"));

						Iterator iterator = keySet.iterator();
						while (iterator.hasNext()) {
							String key = (String) iterator.next();
							if (key.indexOf("AltText_") == 0 || key.indexOf("DocText_") == 0) {
								//must be an AltText or DocText attribute
								Object obj =  incomingRequest.get(key);
								if (obj instanceof String[]) {
									String arrayObj[] = (String[]) obj;
									updateParameters.put(key, arrayObj[i]);
								} else if (obj instanceof String) {
                                    updateParameters.put(key, obj);
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
                //No records to update
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