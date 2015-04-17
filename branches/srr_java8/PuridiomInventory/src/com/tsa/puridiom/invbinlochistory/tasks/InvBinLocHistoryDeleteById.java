package com.tsa.puridiom.invbinlochistory.tasks;
import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.Map;

public class InvBinLocHistoryDeleteById extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		InvBinLocHistory invBinLocHistory = (InvBinLocHistory)incomingRequest.get("invBinLocHistory");
		if(invBinLocHistory == null)
		{
			invBinLocHistory = new InvBinLocHistory();
		}
		InvBinLocHistorySetValuesPK setValues = new InvBinLocHistorySetValuesPK();
		setValues.setValues(incomingRequest, invBinLocHistory);
		dbs.delete(invBinLocHistory);
		this.setStatus(dbs.getStatus()) ;
		return invBinLocHistory ;
	}

}