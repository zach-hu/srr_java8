package com.tsa.puridiom.invhistory.tasks;
import java.util.Map;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import com.tsa.puridiom.entity.*;

public class InvHistoryAdd extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		InvHistory invHistory = (InvHistory)incomingRequest.get("invHistory");
		if(invHistory == null)
		{
			invHistory = new InvHistory();
		}
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		InvHistorySetValues setValues = new InvHistorySetValues();
		setValues.setValues(incomingRequest, invHistory);
		dbs.add(invHistory);
		this.setStatus(dbs.getStatus()) ;
		return invHistory;
	}

}