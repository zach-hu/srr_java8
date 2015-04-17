package com.tsa.puridiom.inspectionmfr.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class InspectionMfrDeleteByIcInspNo extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String icInspNoString = (String) incomingRequest.get("InspectionMfr_icInspNo");
			if (icInspNoString == null) icInspNoString = (String) incomingRequest.get("InspectionHeader_icInspNo");
			BigDecimal icInspNo = new BigDecimal ( icInspNoString );

			String queryString = "from InspectionMfr as InspectionMfr where InspectionMfr.icInspNo = ?";

			int status  = dbs.delete(queryString, new Object[] {icInspNo } , new Type[] { Hibernate.BIG_DECIMAL}) ;

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