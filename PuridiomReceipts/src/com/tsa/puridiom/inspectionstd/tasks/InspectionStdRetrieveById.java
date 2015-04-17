package com.tsa.puridiom.inspectionstd.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class InspectionStdRetrieveById extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String icInspStd = (String ) incomingRequest.get("InspectionStd_standardCode");
			if (icInspStd == null) icInspStd = "0" ;
			BigDecimal bIcInspStd = new BigDecimal(icInspStd) ;

			String queryString = "from InspectionStd as InspectionStd where InspectionStd.id.icInspStd = ? ";
			List resultList = dbs.query(queryString, new Object[] {bIcInspStd } , new Type[] { Hibernate.BIG_DECIMAL}) ;

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