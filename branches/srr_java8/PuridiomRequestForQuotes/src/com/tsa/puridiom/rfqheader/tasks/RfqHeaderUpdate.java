package com.tsa.puridiom.rfqheader.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import java.util.Map;

public class RfqHeaderUpdate extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			RfqHeader rfqHeader = (RfqHeader)incomingRequest.get("rfqHeader");

			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			dbs.update(rfqHeader);
			result = rfqHeader;
			this.setStatus(dbs.getStatus()) ;

		}
		catch (Exception e)
		{
			this.status = Status.FAILED;
		}

		return result;
	}

}