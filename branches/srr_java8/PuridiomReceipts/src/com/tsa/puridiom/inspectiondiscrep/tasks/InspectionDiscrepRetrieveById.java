package com.tsa.puridiom.inspectiondiscrep.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class InspectionDiscrepRetrieveById extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String icRecHeaderString = (String) incomingRequest.get("InspectionDiscrep_icRecHeader");
			BigDecimal icRecHeader = new BigDecimal ( icRecHeaderString );
			String icRecLineString = (String) incomingRequest.get("InspectionDiscrep_icRecLine");
			BigDecimal icRecLine = new BigDecimal ( icRecLineString );
			String keySequenceString = (String) incomingRequest.get("InspectionDiscrep_keySequence");
			BigDecimal keySequence = new BigDecimal ( keySequenceString );

			String queryString = "from InspectionDiscrep as InspectionDiscrep where InspectionDiscrep.id.icRecHeader = ? and InspectionDiscrep.id.icRecLine = ? and InspectionDiscrep.id.keySequence = ? ";
			List resultList = dbs.query(queryString, new Object[] {icRecHeader, icRecLine, keySequence, } , new Type[] { Hibernate.BIG_DECIMAL, Hibernate.BIG_DECIMAL, Hibernate.BIG_DECIMAL}) ;

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