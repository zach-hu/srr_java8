package com.tsa.puridiom.inspectiondispos.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class InspectionDisposRetrieveByIcInspNo extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String icInspNoString = (String) incomingRequest.get("InspectionDispos_icInspNo");
			BigDecimal icInspNo = new BigDecimal ( icInspNoString );

			String queryString = "from InspectionDispos as InspectionDispos where InspectionDispos.icInspNo = ? order by  InspectionDispos.id.keySequence";
			List resultList = dbs.query(queryString, new Object[] {icInspNo} , new Type[] { Hibernate.BIG_DECIMAL}) ;

			if (resultList != null && resultList.size() > 0)
			{
				result = resultList;
			} else {
				result = new ArrayList() ;
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