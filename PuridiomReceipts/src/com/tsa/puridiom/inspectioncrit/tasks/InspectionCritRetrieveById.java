package com.tsa.puridiom.inspectioncrit.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class InspectionCritRetrieveById extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String inspectCode = (String ) incomingRequest.get("InspectionCrit_inspectCode");
			String critNo = (String ) incomingRequest.get("InspectionCrit_critNo");

			String queryString = "from InspectionCrit as InspectionCrit where InspectionCrit.id.inspectCode = ? and InspectionCrit.id.critNo = ? ";
			List resultList = dbs.query(queryString, new Object[] {inspectCode, critNo, } , new Type[] { Hibernate.STRING, Hibernate.STRING}) ;
					
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