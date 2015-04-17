package com.tsa.puridiom.inspectiondispos.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class InspectionDisposRetrieveByReceiptLine extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String icRecHeaderString = (String) incomingRequest.get("InspectionDispos_icRecHeader");
			BigDecimal icRecHeader = new BigDecimal ( icRecHeaderString );
			String icRecLineString = (String) incomingRequest.get("InspectionDispos_icRecLine");
			BigDecimal icRecLine = new BigDecimal ( icRecLineString );

			String queryString = "from InspectionDispos as InspectionDispos where InspectionDispos.id.icRecHeader = ? and InspectionDispos.id.icRecLine = ?  order by  InspectionDispos.id.keySequence";
			List resultList = dbs.query(queryString, new Object[] {icRecHeader, icRecLine } , new Type[] { Hibernate.BIG_DECIMAL, Hibernate.BIG_DECIMAL}) ;

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