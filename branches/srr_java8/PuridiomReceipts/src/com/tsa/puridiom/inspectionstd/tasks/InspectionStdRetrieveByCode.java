package com.tsa.puridiom.inspectionstd.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class InspectionStdRetrieveByCode extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String standardCode = (String ) incomingRequest.get("InspectionStd_standardCode");
			String inspectType = (String ) incomingRequest.get("InspectionStd_inspectType");

			String queryString = "from InspectionStd as InspectionStd where InspectionStd.standardCode = ? and InspectionStd.inspectType = ? order by InspectionStd.inspectCode, InspectionStd.icInspStd ";
			List resultList = dbs.query(queryString, new Object[] {standardCode, inspectType } , new Type[] { Hibernate.STRING, Hibernate.STRING}) ;

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