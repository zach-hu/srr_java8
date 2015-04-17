package com.tsa.puridiom.poline.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;

import java.math.BigDecimal;
import java.util.Map;

public class PoLineRetrieveByReqLine extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			if (incomingRequest.containsKey("PoLine_icReqLine"))
			{
				DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
				BigDecimal icReqLine = (BigDecimal) incomingRequest.get("PoLine_icReqLine");
				StringBuffer queryString = new StringBuffer("from PoLine as PoLine where PoLine.icReqLine = '" + icReqLine + "'");

				queryString.append(" order by PoLine.icPoLine ASC ");

				result = dbs.query(queryString.toString()) ;
				this.setStatus(dbs.getStatus()) ;
			}
			else
			{
				throw new Exception("The value for RfqLine_icReqLine was not found.");
			}
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result ;
	}
}