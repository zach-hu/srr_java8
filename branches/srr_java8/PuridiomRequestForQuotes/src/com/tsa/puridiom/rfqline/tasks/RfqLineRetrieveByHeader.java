package com.tsa.puridiom.rfqline.tasks;

import java.math.BigDecimal;
import java.util.Map;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

public class RfqLineRetrieveByHeader extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{

			if (incomingRequest.containsKey("RfqLine_icRfqHeader"))
			{
				String 	stringIcRfqHeader = HiltonUtility.ckNull((String) incomingRequest.get("RfqLine_icRfqHeader"));
				DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;

				if ( !stringIcRfqHeader.equalsIgnoreCase("") && !stringIcRfqHeader.equalsIgnoreCase("0"))
				{
					BigDecimal icRfqHeader = new BigDecimal(stringIcRfqHeader);
					StringBuffer queryString = new StringBuffer("from RfqLine as rfqline where rfqline.icRfqHeader = " + icRfqHeader  );

					queryString.append(" order by rfqline.rfqLine ASC ");

					result = dbs.query(queryString.toString()) ;
				}

				this.setStatus(Status.SUCCEEDED) ;
			}

		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			Log.debug(this, "Error the value for RfqLine_icRfqHeader was not found: \r\n" + e.getMessage());
			e.printStackTrace();
			this.setStatus(Status.FAILED);

		}
		return result ;
	}
}