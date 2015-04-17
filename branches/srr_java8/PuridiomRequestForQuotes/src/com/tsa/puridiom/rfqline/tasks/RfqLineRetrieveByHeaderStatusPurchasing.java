package com.tsa.puridiom.rfqline.tasks;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RfqLineRetrieveByHeaderStatusPurchasing extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		List result = new ArrayList();

		try
		{
			if (incomingRequest.containsKey("RfqLine_icRfqHeader"))
			{
				DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
				String icRfqHeader = (String) incomingRequest.get("RfqLine_icRfqHeader");
				StringBuffer queryString = new StringBuffer("from RfqLine as rfqline where rfqline.icRfqHeader = '" + icRfqHeader + "' and rfqline.status = '"+DocumentStatus.RFQ_PURCHASING+"'");

				queryString.append(" order by rfqline.rfqLine ASC ");

				result = dbs.query(queryString.toString()) ;
				this.setStatus(dbs.getStatus());
			}
			else
			{
				throw new Exception("The value for RfqLine_icRfqHeader was not found.");
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