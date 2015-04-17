package com.tsa.puridiom.asset.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AssetRetrieveByPoNumber extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		List result = new ArrayList();

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String purchaseOrder = (String ) incomingRequest.get("Asset_purchaseOrder");
			String queryString = "from Asset Asset where Asset.purchaseOrder = ? ";
			List resultList = dbs.query(queryString, new Object[] {purchaseOrder } , new Type[] { Hibernate.STRING}) ;

			if (resultList != null && resultList.size() > 0)
			{
				result = resultList;
			}
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