/*
 * Created on Aug 19, 2003
 */
package com.tsa.puridiom.account.tasks;

import java.util.*;

import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.*;

/**
 * @author Administrator
 */
public class AccountUpdateByLine extends Task {
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
				Object accountObj = incomingRequest.get("Account_allocAmount");
				String	accountType = "H";
				String	referenceType = "";
				String	type = "";

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
						if (incomingRequest.containsKey("Account_accountType")) {
							updateParameters.put("Account_accountType",incomingRequest.get("Account_accountType")) ;
						} else if (incomingRequest.containsKey("formType")) {
							updateParameters.put("Account_accountType",incomingRequest.get("formType")) ;
						} else {
							updateParameters.put("Account_accountType",((String) incomingRequest.get("Default_referenceType"))) ;
						}
						
						if(updateParameters.get("Account_accountType") instanceof String[])
						{
							String	types[] = (String[]) updateParameters.get("Account_accountType");
							type = types[0];
						}else{
							type = (String) updateParameters.get("Account_accountType");
						}

						if (Utility.ckNull(type).length() == 3) {
							accountType = type.substring(2);
							referenceType = type.substring(0, 2);
						}

						Iterator iterator = keySet.iterator();
						while (iterator.hasNext()) {
							String key = (String) iterator.next();
							if (key.indexOf("Account_") == 0) {
								Object obj =  incomingRequest.get(key);
								if (obj instanceof String[]) {
									String arrayObj[] = (String[]) obj;
									if (arrayObj.length > i) {
										updateParameters.put(key, arrayObj[i]);
									} else {
										updateParameters.put(key, "");
									}
								}
							}
						}
						process.executeProcess(updateParameters);
					}

					incomingRequest.put("Account_accountType", type);
				}
				else {
						incomingRequest.put("Account_sequence","1") ;
						if (incomingRequest.containsKey("Account_accountType")) {
							incomingRequest.put("Account_accountType",incomingRequest.get("Account_accountType")) ;
						} else if (incomingRequest.containsKey("formType")) {
							incomingRequest.put("Account_accountType",((String) incomingRequest.get("formType"))) ;
						} else {
							incomingRequest.put("Account_accountType",incomingRequest.get("Default_referenceType")) ;
						}
						type = (String) incomingRequest.get("Account_accountType");
						if (Utility.ckNull(type).length() == 3) {
							accountType = type.substring(2);
							referenceType = type.substring(0, 2);
						}
						process.executeProcess(incomingRequest);
				}

				incomingRequest.put("accountType", accountType);
				incomingRequest.put("referenceType", referenceType);
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
