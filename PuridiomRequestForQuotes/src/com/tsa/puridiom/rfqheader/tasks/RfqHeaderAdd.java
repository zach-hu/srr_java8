package com.tsa.puridiom.rfqheader.tasks;
import java.util.Map;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsa.puridiom.entity.*;

public class RfqHeaderAdd extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			RfqHeader rfqHeader = (RfqHeader)incomingRequest.get("rfqHeader");

			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			dbs.add(rfqHeader);
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