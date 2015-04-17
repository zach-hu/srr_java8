package com.tsa.puridiom.invoiceline.tasks;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Utility;

public class PoLineOrderRetrieveById extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;

			String icPoHeader = (String) incomingRequest.get("PoLine_icPoHeader");
			if (Utility.isEmpty(icPoHeader))
			{
				throw new Exception("PoLine_icPoHeader cannot be empty.  PO line could not be retrieved.");
			}
			BigDecimal icPoLine = new BigDecimal(icPoHeader);

			String queryString = "select PoLine.udf2Code from PoLine as PoLine where PoLine.icPoHeader = ? ";
			result = dbs.query(queryString, icPoLine, Hibernate.BIG_DECIMAL) ;

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