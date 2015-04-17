package com.tsa.puridiom.invitem.tasks;
import java.util.Map;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import com.tsa.puridiom.entity.*;

public class InvItemDeleteById extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		InvItem invItem = (InvItem)incomingRequest.get("invItem");
		String invItemNumber = (String)incomingRequest.get("InvItem_itemNumber");
		if(invItem == null)
		{
			invItem = new InvItem();
			invItem.setItemNumber(invItemNumber);
		}
		InvItemSetValuesPK setValues = new InvItemSetValuesPK();
		setValues.setValues(incomingRequest, invItem);
		dbs.delete(invItem);
		this.setStatus(dbs.getStatus()) ;
		return invItem ;
	}

}
