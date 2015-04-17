package com.tsa.puridiom.itemcrossref.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.Map;

import org.hibernate.*;
import org.hibernate.type.Type;

public class ItemCrossRefDeleteByItemNumber extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
        DBSession dbs = (DBSession) incomingRequest.get("dbsession");
		String itemNumber = (String) incomingRequest.get("ItemCrossRef_itemNumber");

        String queryString = "from ItemCrossRef as itemCrossRef where itemCrossRef.itemNumber = ?";

		this.setStatus(dbs.delete(queryString,	new Object[] {itemNumber}, new Type[] {Hibernate.STRING}));

		return object ;
	}

}