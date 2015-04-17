/*
 * Created on Nov 28, 2006
 */
package com.tsa.puridiom.paymentaccount.tasks;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.entity.InvoiceHeader;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

import org.hibernate.*;
import org.hibernate.type.Type;

/**
 * @author Kathleen
 */
public class PaymentAccountRetrieveByIcPoHeader extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map) object;

		try
		{
	        DBSession dbs = (DBSession) incomingRequest.get("dbsession");
	        InvoiceHeader invoiceHeader = (InvoiceHeader) incomingRequest.get("invoiceHeader");
			BigDecimal icPoHeader = invoiceHeader.getIcPoHeader();

	        String queryString = "select sum(pa.checkAmount) from PaymentAccount as pa where pa.icPoHeader = ? ";

	        List resultList = dbs.query(queryString, new Object[] {icPoHeader} , new Type[] {Hibernate.BIG_DECIMAL});

			if (resultList != null && resultList.size() > 0)
			{
				BigDecimal paymentTotal = (BigDecimal) resultList.get(0);
				BigDecimal poTotal = invoiceHeader.getPoTotal();
				if (paymentTotal.compareTo(poTotal) >= 0)
				{
					incomingRequest.put("poPaid", "Y");
					incomingRequest.put("newStatus", DocumentStatus.CLOSED);
					incomingRequest.put("PoHeader_icPoHeader", icPoHeader.toString());
					incomingRequest.put("PoLine_icPoHeader", icPoHeader.toString());
				}
			}

			this.setStatus(dbs.getStatus());
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return null;
	}
}
