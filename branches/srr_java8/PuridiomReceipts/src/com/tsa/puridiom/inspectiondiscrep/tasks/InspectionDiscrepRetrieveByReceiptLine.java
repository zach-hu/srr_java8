package com.tsa.puridiom.inspectiondiscrep.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class InspectionDiscrepRetrieveByReceiptLine extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String icRecHeaderString = (String) incomingRequest.get("InspectionDiscrep_icRecHeader");
			if (! (icRecHeaderString == null)) {
				BigDecimal icRecHeader = new BigDecimal ( icRecHeaderString );
				String icRecLineString = (String) incomingRequest.get("InspectionDiscrep_icRecLine");
				BigDecimal icRecLine = new BigDecimal ( icRecLineString );
				String icInspNoString = (String) incomingRequest.get("InspectionDiscrep_icInspNo");
				BigDecimal icInspNo = new BigDecimal ( icInspNoString );

				String queryString = "from InspectionDiscrep as InspectionDiscrep where InspectionDiscrep.id.icRecHeader = ? and InspectionDiscrep.id.icRecLine = ? and InspectionDiscrep.id.icInspNo = ? order by  InspectionDiscrep.id.keySequence";
				List resultList = dbs.query(queryString, new Object[] {icRecHeader, icRecLine, icInspNo } , new Type[] { Hibernate.BIG_DECIMAL, Hibernate.BIG_DECIMAL, Hibernate.BIG_DECIMAL}) ;

				if (resultList != null && resultList.size() > 0)
				{
					result = resultList;
				} else {
					result = new ArrayList() ;
				}
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