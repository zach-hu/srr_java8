package com.tsa.puridiom.rfqline.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class RfqLineRetrieveVendorListByHeader extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			if (incomingRequest.containsKey("RfqLine_icRfqHeader"))
			{
				DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
				String icRfqHeader = (String) incomingRequest.get("RfqLine_icRfqHeader");
				StringBuffer queryString = new StringBuffer("select rfqline.vendorAwarded from RfqLine as rfqline where rfqline.icRfqHeader = '" + icRfqHeader + "' group by rfqline.vendorAwarded");

				//queryString.append(" order by rfqline.rfqLine ASC ");

				result = dbs.query(queryString.toString()) ;
				this.setStatus(dbs.getStatus()) ;
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