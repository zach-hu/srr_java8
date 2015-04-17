/*
 * Created on October 19, 2005
 */
package com.tsa.puridiom.invoice.tasks;

import java.util.*;

import com.tsa.puridiom.entity.InvoiceHeader;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Utility ;
import com.tsagate.foundation.processengine.Status;
/**
 * @author kathleen
 */
public class InvoiceDeleteSetup extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map) object;
		DBSession dbs = (DBSession) incomingRequest.get("dbsession") ;

		String icIvcHeader = (String) incomingRequest.get("InvoiceHeader_icIvcHeader");

		if (Utility.isEmpty(icIvcHeader)) {
			this.setStatus(Status.FAILED);
		} else {
			//incomingRequest.put("Schedule_icHeader",icIvcHeader) ;
			incomingRequest.put("Account_icHeader",icIvcHeader) ;
			incomingRequest.put("InvoiceLine_icIvcHeader",icIvcHeader) ;
			InvoiceHeader ivh = (InvoiceHeader) incomingRequest.get("invoiceHeader") ;
			if (ivh != null) {
				incomingRequest.put("InvoiceHeader_invoiceType", ivh.getInvoiceType()) ;
			}
			this.setStatus(dbs.getStatus());
		}

		return null ;
	}
}
