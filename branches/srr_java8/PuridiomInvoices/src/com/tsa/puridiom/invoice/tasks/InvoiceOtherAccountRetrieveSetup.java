/*
 * Created on October 11, 2005
 */
package com.tsa.puridiom.invoice.tasks;

import java.util.*;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

/**
 * @author kathleen
 */
public class InvoiceOtherAccountRetrieveSetup extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;

		try {
	        incomingRequest.put("Account_icLine","3");
	        this.setStatus(Status.SUCCEEDED);
		}
		catch(Exception e) {
			this.setStatus(Status.FAILED);
			throw e;
		}

		return null;
	}

}
