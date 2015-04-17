package com.tsa.puridiom.invoiceline.tasks;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;

import com.tsa.puridiom.entity.PoHeader;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;

public class InvoiceLineRetrieveByIcPoHeader extends Task
{
	public Object executeTask(Object object)throws Exception
	{
		Object result = null;
		Map incomingRequest = (Map)object;

		PoHeader poHeader = (PoHeader)incomingRequest.get("poHeader");
		DBSession dbs = (DBSession)incomingRequest.get("dbsession");
		BigDecimal icPoHeader= poHeader.getIcPoHeader();
		String queryString = "select sum(invoiceLine.quantity) from InvoiceLine as invoiceLine where invoiceLine.icPoHeader= ? group by invoiceLine.lineNumber order by invoiceLine.lineNumber";
		result = dbs.query(queryString, icPoHeader, Hibernate.BIG_DECIMAL);


		this.setStatus(dbs.getStatus());
		return result;
	}
}
