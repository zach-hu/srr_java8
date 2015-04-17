package com.tsa.puridiom.poline.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class PoLineRetrieveByIcLineHistory extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			if (incomingRequest.containsKey("PoLine_icLineHistory"))
			{
				DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
				BigDecimal icLineHistory = (BigDecimal) incomingRequest.get("PoLine_icLineHistory");
				StringBuffer queryString = new StringBuffer("from PoLine as PoLine where PoLine.icLineHistory = '" + icLineHistory + "'");

				queryString.append(" order by PoLine.icPoLine ASC ");

				List resultList = dbs.query(queryString.toString()) ;
				if (resultList != null && resultList.size() > 0)
				{
					result = resultList.get(0);
				}
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