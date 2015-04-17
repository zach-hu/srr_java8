package com.tsa.puridiom.rfqline.tasks;
import java.util.Map;

import com.tsa.puridiom.entity.RfqLine;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class RfqLineUpdate extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			RfqLine rfqLine = (RfqLine)incomingRequest.get("rfqLine");
			if (rfqLine == null)
			{
				throw new TsaException("RfqLine was not found!");
			}

			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			dbs.update(rfqLine);

			result = rfqLine;
			this.setStatus(dbs.getStatus()) ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException(this.getName(), e);
		}
		return result;
	}

}