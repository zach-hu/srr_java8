package com.tsa.puridiom.invoiceline.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class InvoiceAccountAllocatedForLines extends Task	{
		public Object  executeTask (Object object) throws Exception

	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String icHeaderString = (String) incomingRequest.get("Account_icHeader");
			BigDecimal icHeader = new BigDecimal ( icHeaderString );
			BigDecimal lineAllocated = new BigDecimal("0");

			String queryString = "select sum(a.allocAmount) from Account as a where a.id.icHeader = ? AND a.id.icLine > 100 ";
			List resultList = dbs.query(queryString, new Object[] {icHeader} , new Type[] { Hibernate.BIG_DECIMAL}) ;

			if (resultList != null && resultList.size() > 0)
			{
				lineAllocated = (BigDecimal) resultList.get(0);
				if (lineAllocated == null) {
					lineAllocated = new BigDecimal(0) ;
				}
			}
			result = String.valueOf(lineAllocated);
			this.setStatus(dbs.getStatus()) ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}

		return result ;
	}
}
