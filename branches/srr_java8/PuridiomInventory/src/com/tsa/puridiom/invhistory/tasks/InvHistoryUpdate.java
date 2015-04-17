package com.tsa.puridiom.invhistory.tasks;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import com.tsa.puridiom.entity.*;
import java.util.Map;

public class InvHistoryUpdate extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		InvHistory invHistory = (InvHistory)incomingRequest.get("invHistory");
		if(invHistory == null)
		{
		invHistory = new InvHistory();
		}
		DBSession dbs =
                (DBSession)incomingRequest.get("dbsession") ;
		InvHistorySetValues setValues = new InvHistorySetValues();
		setValues.setValues(incomingRequest, invHistory);
		dbs.update(invHistory);
		this.setStatus(dbs.getStatus()) ;
		return invHistory;
	}

}