package com.tsa.puridiom.poline.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Utility;
import org.hibernate.Hibernate;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class PoLineRetrieveIcReqHeader extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String icReqLineString = (String) incomingRequest.get("PoLine_icReqLine");

			if (Utility.isEmpty(icReqLineString))
			{
				throw new Exception("PoLine_icReqLine cannot be empty.  IcReqHeader for the Requisition could not be retrieved.");
			}
			BigDecimal icReqLine = new BigDecimal ( icReqLineString );

			String queryString = "select RequisitionLine.icReqHeader from RequisitionLine as RequisitionLine where RequisitionLine.icReqLine = ? ";
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