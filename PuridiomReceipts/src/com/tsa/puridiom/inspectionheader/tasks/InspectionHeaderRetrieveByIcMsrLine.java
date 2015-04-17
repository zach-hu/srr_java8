package com.tsa.puridiom.inspectionheader.tasks;

import com.tsa.puridiom.entity.InspectionHeader;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class InspectionHeaderRetrieveByIcMsrLine extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String icMsrLineString = (String) incomingRequest.get("InspectionHeader_icMsrLine");
			if (icMsrLineString == null) icMsrLineString = (String) incomingRequest.get("icMsrLine");
			if (icMsrLineString == null) icMsrLineString = "0" ;

			BigDecimal icMsrLine = new BigDecimal ( icMsrLineString );

			String queryString = "from InspectionHeader as InspectionHeader where  InspectionHeader.id.icMsrLine = ? and (InspectionHeader.icRecLine is null or InspectionHeader.icRecLine = 0)";
			List resultList = dbs.query(queryString, new Object[] { icMsrLine, } , new Type[] { Hibernate.BIG_DECIMAL}) ;

			if (resultList != null && resultList.size() > 0)
			{
				for (int i = 0; i < resultList.size(); i++) {
					InspectionHeader inh = (InspectionHeader) resultList.get(i) ;
					if (inh.getInspectType().startsWith("R")) {
						result = resultList.get(i);
						break ;
					}
				}
				if (result == null) 	result = resultList.get(0);
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