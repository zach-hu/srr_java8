package com.tsa.puridiom.kititem.tasks;
import java.util.Map;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import com.tsa.puridiom.entity.*;

public class KitItemDeleteById extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		KitItem kitItem = (KitItem)incomingRequest.get("kitItem");
		if(kitItem == null)
		{
			kitItem = new KitItem();
		}
		KitItemSetValuesPK setValues = new KitItemSetValuesPK();
		setValues.setValues(incomingRequest, kitItem);
		dbs.delete(kitItem);
		this.setStatus(dbs.getStatus()) ;
		return kitItem ;
	}

}