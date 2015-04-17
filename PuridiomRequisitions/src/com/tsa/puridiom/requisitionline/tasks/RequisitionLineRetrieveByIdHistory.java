package com.tsa.puridiom.requisitionline.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class RequisitionLineRetrieveByIdHistory extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String icReqLineString = (String) incomingRequest.get("old_RequisitionLine_icReqLine");
			if (Utility.isEmpty(icReqLineString))
			{
				throw new Exception("RequisitionLine_icReqLine cannot be empty.  Requisition line could not be retrieved.");
			}
			BigDecimal icReqLine = new BigDecimal ( icReqLineString );

			String queryString = "from RequisitionLine as line where line.icReqLine = ? ";
			List resultList = dbs.query(queryString, new Object[] {icReqLine, } , new Type[] { Hibernate.BIG_DECIMAL}) ;
					
			if (resultList != null && resultList.size() > 0)
			{
				result = resultList.get(0);
			}
			this.setStatus(dbs.getStatus()) ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException(this.getName(), e);
		}
		return result;
	}

}