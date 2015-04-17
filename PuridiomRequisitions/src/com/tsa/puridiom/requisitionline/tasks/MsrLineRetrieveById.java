package com.tsa.puridiom.requisitionline.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Utility;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class MsrLineRetrieveById extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String icMsrLineString = (String) incomingRequest.get("MsrLine_icReqLine");
			if (Utility.isEmpty(icMsrLineString))
			{
				throw new Exception("RequisitionLine_icReqLine cannot be empty.  Requisition line could not be retrieved.");
			}
			BigDecimal icMsrLine = new BigDecimal ( icMsrLineString );

			String queryString = "from RequisitionLine as RequisitionLine where RequisitionLine.icReqLine = ? ";
			List resultList = dbs.query(queryString, new Object[] {icMsrLine, } , new Type[] { Hibernate.BIG_DECIMAL}) ;
					
			if (resultList != null && resultList.size() > 0)
			{
				result = resultList.get(0);
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