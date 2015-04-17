package com.tsa.puridiom.poline.tasks;

import java.math.BigDecimal;
import java.util.Map;

import org.hibernate.Hibernate;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.Utility;

public class PoLineRetreiveByHeaderForAutoInvoice extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession");
			String icHeader = (String)incomingRequest.get("PoLine_icPoHeader");
			if (Utility.isEmpty(icHeader))
			{
				icHeader = "0";
			}
			BigDecimal bdHeader = new BigDecimal(icHeader);
			String queryString = "from PoLine as PoLine where PoLine.icPoHeader = ? AND (PoLine.status = '4100' OR PoLine.status = '4150') order by " +
					"PoLine.lineNumber ASC";

			result = dbs.query(queryString, bdHeader, Hibernate.BIG_DECIMAL);

			this.setStatus(dbs.getStatus());
		}
		catch (Exception e)
		{
			Log.error(this, e.toString());
			this.setStatus(Status.FAILED);
		}
		return result;
	}
}
