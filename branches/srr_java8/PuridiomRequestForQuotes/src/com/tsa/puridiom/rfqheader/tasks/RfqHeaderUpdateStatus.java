package com.tsa.puridiom.rfqheader.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import java.util.Map;

public class RfqHeaderUpdateStatus extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			RfqHeader rfqHeader = (RfqHeader)incomingRequest.get("rfqHeader");
			String	status = (String) incomingRequest.get("RfqHeader_status");
			
			rfqHeader.setStatus(status);

			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			dbs.update(rfqHeader);
			this.setStatus(dbs.getStatus()) ;
			
			result = rfqHeader;
		}
		catch (Exception e)
		{
			this.status = Status.FAILED;
		}
		
		return result;
	}

}