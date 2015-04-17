/*
 * Created on Aug 19, 2003
 */
package com.tsa.puridiom.account.tasks;

import java.util.*;

import com.tsagate.foundation.processengine.*;

/**
 * @author Administrator
 */
public class StdAccountUpdateByLine extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;
		Object result = null;

		try {
			if (incomingRequest.containsKey("Account_icHeader")) {
				PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
				PuridiomProcess process = processLoader.loadProcess("account-update-line-row.xml");
				Object accountObj = incomingRequest.get("Account_allocPercent");

				if (accountObj instanceof String[]) {
					int	arraySize = ((String[]) accountObj).length;
					Set keySet = incomingRequest.keySet();

					for (int i=0; i < arraySize; i++) {
						Map updateParameters = new HashMap();
						updateParameters.put("userId", incomingRequest.get("userId"));
                        updateParameters.put("userTimeZone", incomingRequest.get("userTimeZone"));
                        updateParameters.put("organizationId", incomingRequest.get("organizationId"));
						updateParameters.put("dbsession", incomingRequest.get("dbsession"));
						updateParameters.put("Account_sequence",Integer.toString(i + 1)) ;
						updateParameters.put("Account_icHeader",incomingRequest.get("Account_icHeader")) ;
						updateParameters.put("Account_icLine",incomingRequest.get("Account_icLine")) ;
						//updateParameters.put("Account_accountType", "STD");
						updateParameters.put("Account_accountTitle", incomingRequest.get("Account_accountTitle"));
						updateParameters.put("Account_dateEntered", incomingRequest.get("Account_dateEntered"));
						updateParameters.put("Account_dateExpires", incomingRequest.get("Account_dateExpires"));
						updateParameters.put("Account_status", incomingRequest.get("Account_status"));
						updateParameters.put("Account_owner", incomingRequest.get("Account_owner"));
						updateParameters.put("Account_allocMethod", "PL");


						String	types[] = (String[]) incomingRequest.get("Account_accountType");
						updateParameters.put("Account_accountType", types[0]);
						/*
						type = "STD";


						if (Utility.ckNull(type).length() == 3) {
							accountType = type.substring(2);
							referenceType = type.substring(0, 2);
						}

						*/
						Iterator iterator = keySet.iterator();
						while (iterator.hasNext()) {
							String key = (String) iterator.next();
							if (key.indexOf("Account_") == 0) {
								Object obj =  incomingRequest.get(key);
								if (obj instanceof String[]) {
									String arrayObj[] = (String[]) obj;
									updateParameters.put(key, arrayObj[i]);
								}
							}
						}
						process.executeProcess(updateParameters);
					}

					incomingRequest.put("Account_accountType", "STD");
				}
				else {
						incomingRequest.put("Account_sequence","1") ;
						//incomingRequest.put("Account_accountType", "STD");
						incomingRequest.put("Account_accountType", incomingRequest.get("Account_accountType"));
						incomingRequest.put("Account_allocMethod", "PL");
						/*
						type = (String) incomingRequest.get("Account_accountType");
						if (Utility.ckNull(type).length() == 3) {
							accountType = type.substring(2);
							referenceType = type.substring(0, 2);
						}
						*/
						process.executeProcess(incomingRequest);
				}

				//incomingRequest.put("accountType", accountType);
				//incomingRequest.put("referenceType", referenceType);
			}
			else {
				throw new Exception("The value for Account_icHeader was not found.");
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
