/*
 * Created on Nov 22, 2006
 */
package com.tsa.puridiom.paymentaccount.tasks;

import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsa.puridiom.entity.InvoiceHeader;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

/**
 * @author Kathleen
 */
public class PaymentAccountRetrieveByInvoice extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map) object;
		Object result = null;

		try
		{
	        DBSession dbs = (DBSession) incomingRequest.get("dbsession");
	        InvoiceHeader invoiceHeader = (InvoiceHeader) incomingRequest.get("invoiceHeader");
			String invoiceNumber = invoiceHeader.getInvoiceNumber();
			String poNumber = invoiceHeader.getPoNumber();

	        String queryString = "from PaymentAccount as pa where pa.invoiceNumber = ? AND pa.poNumber = ?";

	        result = dbs.query(queryString, new Object[] {invoiceNumber, poNumber} , new Type[] {Hibernate.STRING, Hibernate.STRING});

			this.setStatus(dbs.getStatus());
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		
		return result;
	}
}
