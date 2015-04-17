/*
 * Created on December 12, 2007
 */
package com.tsa.puridiom.account.tasks;

import java.util.*;

import com.tsagate.foundation.processengine.*;

/**
 * @author Administrator
 */
public class UseTaxAccountUpdateByLine extends Task {
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

				incomingRequest.put("Account_sequence","1");
				incomingRequest.put("Account_accountType", "UTX");
				incomingRequest.put("Account_allocMethod", "PH");

				process.executeProcess(incomingRequest);
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
