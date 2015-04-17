package com.tsa.puridiom.invoiceline.tasks;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;

import com.tsa.puridiom.entity.InvoiceHeader;
import com.tsa.puridiom.entity.PoLine;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

public class InvoiceLineRetrieveByInvoiceHeaderList extends Task {
	public Object executeTask(Object object) throws Exception {
		Map incomingRequest = (Map) object;
		Object result = null;

		try {
			DBSession dbs = (DBSession) incomingRequest.get("dbsession");
			List invoiceHeaderList = (List) incomingRequest.get("invoiceHeaderList");
			PoLine poLine = (PoLine)incomingRequest.get("poLine");
			if (invoiceHeaderList != null && invoiceHeaderList.size() > 0) {
				for (int rhl = 0; rhl < invoiceHeaderList.size(); rhl++) {
					InvoiceHeader invoiceHeader = (InvoiceHeader) invoiceHeaderList.get(rhl);
					String queryString = "from InvoiceLine as invoiceLine where invoiceLine.icIvcHeader = " + invoiceHeader.getIcIvcHeader() + " and invoiceLine.icPoLine = "+ poLine.getIcPoLine();
					List listResult = dbs.query(queryString.toString());
					invoiceHeader.setInvoiceLineList(listResult);
				}
				result = invoiceHeaderList;
				this.setStatus(dbs.getStatus());
			}
			this.setStatus(Status.SUCCEEDED);
		} catch (Exception e) {
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}
}
