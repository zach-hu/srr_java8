package com.tsa.puridiom.requisitionline.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class RequisitionLineGetNewLineNumber extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String icReqHeaderString = (String) incomingRequest.get("RequisitionHeader_icReqHeader");

			String whereInsert = null;
			if(incomingRequest.get("whereInsert")!= null)
			{
				whereInsert = (String) incomingRequest.get("whereInsert");
			}

			BigDecimal icReqHeader = new BigDecimal ( icReqHeaderString );
			BigDecimal reqLineNumber = new BigDecimal("0");

			String queryString = "select max(r.lineNumber) from RequisitionLine as r where r.icReqHeader = ? ";
			List resultList = dbs.query(queryString, new Object[] {icReqHeader, } , new Type[] { Hibernate.BIG_DECIMAL}) ;

			if (resultList != null && resultList.size() > 0)
			{
				reqLineNumber = (BigDecimal) resultList.get(0);
				if (reqLineNumber == null) {
					reqLineNumber = new BigDecimal(0) ;
				}
			}
			result = String.valueOf(reqLineNumber.add(new BigDecimal(1)));

			if(whereInsert != null && whereInsert.equalsIgnoreCase("Before"))
			{
				reqLineNumber = new BigDecimal("0");
				result = String.valueOf(reqLineNumber);
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