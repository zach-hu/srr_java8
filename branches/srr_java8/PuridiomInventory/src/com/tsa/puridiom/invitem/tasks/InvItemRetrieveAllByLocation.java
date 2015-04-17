package com.tsa.puridiom.invitem.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import java.util.List;
import java.util.Map;

public class InvItemRetrieveAllByLocation extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String itemLocation = (String ) incomingRequest.get("InvLocation_itemLocation");

			String queryString = "from InvItem as InvItem, InvLocation as InvLocation" +
					" where InvItem.itemNumber = InvLocation.id.itemNumber" +
					" and InvLocation.id.itemLocation = ? ";

			List resultList = dbs.query(queryString, new Object[] {itemLocation} ,
				new Type[] {Hibernate.STRING}) ;

			result = resultList ;
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