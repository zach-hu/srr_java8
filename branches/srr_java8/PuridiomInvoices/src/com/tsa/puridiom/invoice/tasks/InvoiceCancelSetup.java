/*
 * Created on October 24, 2006
 */
package com.tsa.puridiom.invoice.tasks;

import java.util.*;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Utility ;
import com.tsagate.foundation.processengine.Status;
/**
 * @author Kathleen
 */
public class InvoiceCancelSetup extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map) object;
		DBSession dbs = (DBSession) incomingRequest.get("dbsession");

		String icIvcHeader = (String) incomingRequest.get("InvoiceHeader_icIvcHeader");

		if (Utility.isEmpty(icIvcHeader))
		{
			this.setStatus(Status.FAILED);
		}
		else
		{
			incomingRequest.put("InvoiceLine_icIvcHeader", icIvcHeader);
			this.setStatus(dbs.getStatus());
		}

		incomingRequest.put("newStatus", DocumentStatus.CANCELLED);

		return null;
	}
}
