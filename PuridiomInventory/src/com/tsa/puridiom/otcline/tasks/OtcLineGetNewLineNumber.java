package com.tsa.puridiom.otcline.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class OtcLineGetNewLineNumber extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String icDsbHeaderString = (String) incomingRequest.get("DisbHeader_icDsbHeader");
			BigDecimal icDsbHeader = new BigDecimal ( icDsbHeaderString );
			BigDecimal lineNumber = new BigDecimal("0");

			String queryString = "select max(r.lineNumber) from DisbLine as r where r.icDsbHeader = ? ";
			List resultList = dbs.query(queryString, new Object[] {icDsbHeader } , new Type[] { Hibernate.BIG_DECIMAL}) ;
					
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