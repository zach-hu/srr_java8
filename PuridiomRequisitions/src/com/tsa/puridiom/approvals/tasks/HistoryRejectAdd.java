package com.tsa.puridiom.approvals.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;

import java.util.List;
import java.util.Map;

public class HistoryRejectAdd extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			List historyRejectList = (List) incomingRequest.get("historyRejectList");
			if (historyRejectList == null)
			{
				throw new Exception ("History Reject List was not found.");
			}
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;

			for( int i = 0; i < historyRejectList.size(); i++)
			{
				HistoryReject historyReject = new HistoryReject();
				historyReject = (HistoryReject) historyRejectList.get(i);
				dbs.add(historyReject);
			}
			result = historyRejectList;
			this.setStatus(dbs.getStatus()) ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}

}