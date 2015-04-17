package com.tsa.puridiom.recentreceipt.tasks;
import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.Map;

public class RecentReceiptDeleteById extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		RecentReceipt recentReceipt = (RecentReceipt)incomingRequest.get("recentReceipt");
		if(recentReceipt == null)
		{
			recentReceipt = new RecentReceipt();
		}
		RecentReceiptSetValuesPK setValues = new RecentReceiptSetValuesPK();
		setValues.setValues(incomingRequest, recentReceipt);
		dbs.delete(recentReceipt);
		this.setStatus(dbs.getStatus()) ;
		return recentReceipt ;
	}

}