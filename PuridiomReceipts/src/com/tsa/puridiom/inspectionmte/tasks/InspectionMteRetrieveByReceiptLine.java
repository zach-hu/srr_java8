package com.tsa.puridiom.inspectionmte.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class InspectionMteRetrieveByReceiptLine extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String icRecHeaderString = (String) incomingRequest.get("InspectionMte_icRecHeader");
			BigDecimal icRecHeader = new BigDecimal ( icRecHeaderString );
			String icRecLineString = (String) incomingRequest.get("InspectionMte_icRecLine");
			BigDecimal icRecLine = new BigDecimal ( icRecLineString );
			String icInspNoString = (String) incomingRequest.get("InspectionMte_icInspNo");
			BigDecimal icInspNo = new BigDecimal ( icInspNoString );

			String queryString = "from InspectionMte as InspectionMte where InspectionMte.id.icRecHeader = ? and InspectionMte.id.icRecLine = ? and InspectionMte.id.icInspNo = ? order by  InspectionMte.id.keySequence";
			List resultList = dbs.query(queryString, new Object[] {icRecHeader, icRecLine, icInspNo } , new Type[] { Hibernate.BIG_DECIMAL, Hibernate.BIG_DECIMAL, Hibernate.BIG_DECIMAL}) ;

			if (resultList != null && resultList.size() > 0)
			{
				result = resultList;
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