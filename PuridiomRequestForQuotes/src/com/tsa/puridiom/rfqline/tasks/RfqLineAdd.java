package com.tsa.puridiom.rfqline.tasks;
import java.util.Map;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import com.tsa.puridiom.entity.*;

public class RfqLineAdd extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			RfqLine rfqLine = (RfqLine) incomingRequest.get("rfqLine");
			if (rfqLine == null)
			{
				rfqLine = new RfqLine();
			}
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
	
			dbs.add(rfqLine);
	
			this.setStatus(dbs.getStatus()) ;
			result = rfqLine;
		}
		catch (Exception e)
		{
			this.status = Status.FAILED;
			throw e;
		}
		
		return result;
	}

}