package com.tsa.puridiom.inspectionhistory.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class InspectionHistoryRetrieveByIcRecLine extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String icRecLineString = (String) incomingRequest.get("InspectionHistory_icRecLine");
			BigDecimal icRecLine = new BigDecimal ( icRecLineString );

			String queryString = "from InspectionHistory as InspectionHistory where InspectionHistory.icRecLine = ? ";
			List resultList = dbs.query(queryString, new Object[] {icRecLine, } , new Type[] { Hibernate.BIG_DECIMAL}) ;

			if (resultList == null) resultList = new ArrayList() ;
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