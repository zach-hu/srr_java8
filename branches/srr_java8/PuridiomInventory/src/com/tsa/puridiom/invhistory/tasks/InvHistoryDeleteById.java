package com.tsa.puridiom.invhistory.tasks;
import java.util.Map;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import com.tsa.puridiom.entity.*;

public class InvHistoryDeleteById extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		InvHistory invHistory = (InvHistory)incomingRequest.get("invHistory");
		if(invHistory == null)
		{
			invHistory = new InvHistory();
		}
		InvHistorySetValuesPK setValues = new InvHistorySetValuesPK();
		setValues.setValues(incomingRequest, invHistory);
		dbs.delete(invHistory);
		this.setStatus(dbs.getStatus()) ;
		return invHistory ;
	}

}