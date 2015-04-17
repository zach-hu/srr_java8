package com.tsa.puridiom.rfqline.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class RfqLineGetNewRfqLineNumber extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String icRfqHeaderString = (String) incomingRequest.get("RfqHeader_icRfqHeader");
			
			String whereInsert = null;
			if(incomingRequest.get("whereInsert")!= null)				
			{
				whereInsert = (String) incomingRequest.get("whereInsert");
			}
			
			BigDecimal icRfqHeader = new BigDecimal ( icRfqHeaderString );
			BigDecimal rfqLineNumber = new BigDecimal("0");

			String queryString = "select max(RfqLine.rfqLine) from RfqLine as RfqLine where RfqLine.icRfqHeader = ? ";
			List resultList = dbs.query(queryString, new Object[] {icRfqHeader, } , new Type[] { Hibernate.BIG_DECIMAL}) ;


				if (resultList != null && resultList.size() > 0)
				{
					if (resultList.get(0) != null)
					{
						rfqLineNumber = (BigDecimal) resultList.get(0);
					}
				}

				result = String.valueOf(rfqLineNumber.add(new BigDecimal(1)));

				if(whereInsert != null && whereInsert.equalsIgnoreCase("Before"))
				{
					rfqLineNumber = new BigDecimal("0");
					result = String.valueOf(rfqLineNumber);
				}

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