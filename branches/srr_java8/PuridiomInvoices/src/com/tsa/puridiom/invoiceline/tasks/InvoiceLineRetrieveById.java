package com.tsa.puridiom.invoiceline.tasks;

import com.tsa.puridiom.entity.InvoiceLine;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class InvoiceLineRetrieveById extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String icIvcLineString = (String) incomingRequest.get("InvoiceLine_icIvcLine");
			BigDecimal icIvcLine = new BigDecimal ( icIvcLineString );

			String queryString = "from InvoiceLine as InvoiceLine where InvoiceLine.icIvcLine = ? ";
			List resultList = dbs.query(queryString, new Object[] {icIvcLine, } , new Type[] { Hibernate.BIG_DECIMAL}) ;

			if (resultList != null && resultList.size() > 0)
			{
				result = resultList.get(0);
			}
			this.setStatus(dbs.getStatus()) ;

			InvoiceLine line = (InvoiceLine)result;

			incomingRequest.put("PoHeader_icPoHeader", line.getIcPoHeader().toString());
			incomingRequest.put("PoLine_icPoLine", line.getIcPoLine().toString());
			if (line.getIcRelPoLine().compareTo(new BigDecimal(0)) > 0)
				incomingRequest.put("PoLine_icPoLine", line.getIcRelPoLine().toString());
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}

}