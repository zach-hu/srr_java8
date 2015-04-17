package com.tsa.puridiom.invformstate.tasks;

import java.util.Map;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;

import org.hibernate.*;
import org.hibernate.type.Type;

public class InvFormStateDeleteByItem extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;

        DBSession dbs = (DBSession)incomingRequest.get("dbsession");
		String itemNumber = (String)incomingRequest.get("InvFormState_itemNumber");
		
        String queryString = "from InvFormState as i where i.id.itemNumber = ? " ;

		this.setStatus(dbs.delete(queryString,	
					new Object[] {itemNumber},
					new Type[] {Hibernate.STRING})) ;

					
		return object  ;
	}

}