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
public class AccountUpdateByLineSetup extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;
		Object result = null;

		String	formType = (String) incomingRequest.get("formType") ;
		String	accountType = "H";
		String	referenceType = "";

		if (Utility.ckNull(formType).length() == 3) {
			accountType = formType.substring(2);
			referenceType = formType.substring(0, 2);
		}

		incomingRequest.put("accountType", accountType);
		incomingRequest.put("referenceType", referenceType);

		return result;
	}
}
