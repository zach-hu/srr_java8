package com.tsa.puridiom.inspectionline.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class InspectionLineRetrieveByIcInspNo extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			BigDecimal icInspNo = (BigDecimal) incomingRequest.get("InspectionLine_icInspNo") ;

			String queryString = "from InspectionLine as InspectionLine where InspectionLine.icInspNo = ? ";
			List resultList = dbs.query(queryString, new Object[] {icInspNo, } , new Type[] { Hibernate.BIG_DECIMAL}) ;

			result = resultList ;
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