package com.tsa.puridiom.assetcost.serv.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import java.util.List;
import java.util.Map;

public class AssetCostRetrieveByTagNumber extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		List result = null;
		
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String AssetCost_tagNumber = (String ) incomingRequest.get("AssetCost_tagNumber");
			String queryString = "from AssetCost AssetCost where AssetCost.id.tagNumber = ?";
			result = dbs.query(queryString, new Object[] {AssetCost_tagNumber} , new Type[] { Hibernate.STRING}) ;
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