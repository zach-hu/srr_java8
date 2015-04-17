package com.tsa.puridiom.inspectionline.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class InspectionLineRetrieveByIcInspNoAndCode extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;

			BigDecimal icInspNo = new BigDecimal(0) ;
			Object obj = incomingRequest.get("InspectionLine_icInspNo") ;
			if (obj instanceof String) {
				String s = (String) obj ;
				icInspNo = new BigDecimal(s) ;
			} else {
				icInspNo = (BigDecimal) obj ;
			}
			String inspCode = (String) incomingRequest.get("inspectCode") ;

			String queryString = "from InspectionLine as InspectionLine where InspectionLine.icInspNo = ? and InspectionLine.inspectCode = ? ";
			List resultList = dbs.query(queryString, new Object[] {icInspNo, inspCode} , new Type[] { Hibernate.BIG_DECIMAL, Hibernate.STRING}) ;

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