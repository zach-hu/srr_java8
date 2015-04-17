package com.tsa.puridiom.invoiceline.tasks;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;

import com.tsa.puridiom.entity.PoLine;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;

public class InvoiceLineRetrieveByIcPoLine extends Task
{
	public Object executeTask(Object object)throws Exception
	{
		Object result = null;
		Map incomingRequest = (Map)object;

		PoLine poLine = (PoLine)incomingRequest.get("poLine");
		DBSession dbs = (DBSession)incomingRequest.get("dbsession");
		BigDecimal icPoLine = poLine.getIcPoLine();
		String queryString = "select sum(invoiceLine.quantity) from InvoiceLine as invoiceLine where invoiceLine.icPoLine= ? group by invoiceLine.lineNumber order by invoiceLine.lineNumber";
		List queryResult = dbs.query(queryString, icPoLine, Hibernate.BIG_DECIMAL);
		if (queryResult != null && queryResult.size() > 0)
		{
			result = queryResult.get(0);
		}

		this.setStatus(dbs.getStatus());
		return result;
	}
}
