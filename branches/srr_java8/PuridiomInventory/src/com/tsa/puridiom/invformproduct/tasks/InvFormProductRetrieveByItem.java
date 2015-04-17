package com.tsa.puridiom.invformproduct.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Utility;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import java.util.List;
import java.util.Map;

public class InvFormProductRetrieveByItem extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		List result = null;
		
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String itemNumber = (String) incomingRequest.get("InvFormProduct_itemNumber");
			if (Utility.isEmpty(itemNumber))
			{
				throw new Exception("InvFormProduct_itemNumber cannot be empty.  InvFormProduct could not be retrieved.");
			}
			
			String queryString = "from InvFormProduct as InvFormProduct where InvFormProduct.id.itemNumber = ? ";
			result = dbs.query(queryString, new Object[] {itemNumber, } , new Type[] { Hibernate.STRING}) ;
			
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