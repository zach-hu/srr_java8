package com.tsa.puridiom.requisitionline.tasks;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Utility;
import org.hibernate.Hibernate;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class RequisitionLineRetrieveIcPoLine extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String icReqLineString = (String) incomingRequest.get("RequisitionLine_icReqLine");

			if (Utility.isEmpty(icReqLineString))
			{
				throw new Exception("RequisitionLine_icReqHeader cannot be empty.  IcPoHeader for the Requisition could not be retrieved.");
			}
			BigDecimal icReqLine = new BigDecimal ( icReqLineString );

			String queryString = "select PoLine.icPoLine from PoLine as PoLine where PoLine.icReqLine = ? and PoLine.status <> '" + DocumentStatus.PO_AMENDED + "' and PoLine.status <> '" + DocumentStatus.CANCELLED + "' and PoLine.poNumber <> 'N/A'";
			List resultList = dbs.query(queryString, icReqLine, Hibernate.BIG_DECIMAL) ;
					
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