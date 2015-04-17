package com.tsa.puridiom.rfqline.tasks;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Utility;
import org.hibernate.Hibernate;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class RfqLineRetrieveIcPoLine extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String icRfqLineString = (String) incomingRequest.get("RfqLine_icRfqLine");

			if (Utility.isEmpty(icRfqLineString))
			{
				throw new Exception("RfqLine_icRfqLine cannot be empty.  IcPoHeader for the Solicitation could not be retrieved.");
			}
			BigDecimal icRfqLine = new BigDecimal ( icRfqLineString );

			String queryString = "select PoLine.icPoLine from PoLine as PoLine where PoLine.icRfqLine = ? and PoLine.status <> '" + DocumentStatus.PO_AMENDED + "' and PoLine.status <> '" + DocumentStatus.CANCELLED + "' and PoLine.poNumber <> 'N/A'";
			List resultList = dbs.query(queryString, icRfqLine, Hibernate.BIG_DECIMAL) ;
					
			if (resultList != null && resultList.size() > 0)
			{
				result = String.valueOf(resultList.get(0));
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