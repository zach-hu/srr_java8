package com.tsa.puridiom.invoiceline.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class InvoiceLineGetNewLineNumber extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String icHeaderString = (String) incomingRequest.get("InvoiceLine_icIvcHeader");
			BigDecimal icHeader = new BigDecimal ( icHeaderString );
			BigDecimal lineNumber = new BigDecimal("0");

			String queryString = "select max(i.lineNumber) from InvoiceLine as i where i.icIvcHeader = ? ";
			List resultList = dbs.query(queryString, new Object[] {icHeader, } , new Type[] { Hibernate.BIG_DECIMAL});

			if (resultList != null && resultList.size() > 0)
			{
				lineNumber = (BigDecimal) resultList.get(0);
				if (lineNumber == null) {
					lineNumber = new BigDecimal(0) ;
				}
			}
			result = String.valueOf(lineNumber.add(new BigDecimal(1)));
			this.setStatus(dbs.getStatus()) ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}

}