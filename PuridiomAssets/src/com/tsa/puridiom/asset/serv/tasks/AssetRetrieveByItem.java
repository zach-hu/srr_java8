package com.tsa.puridiom.asset.serv.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;
//import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class AssetRetrieveByItem extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		/*
		 * @author EDSAC
		 * return an asset list according with the Asset_itemNumber and Asset_itemLocation
		 * */
		Map incomingRequest = (Map)object;
		List result = null;

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String itemNumber = (String ) incomingRequest.get("Asset_itemNumber");
			String itemLocation = (String) incomingRequest.get("Asset_itemLocation");

			//String queryString = "from Asset where Asset.id.tagNumber = ? ";

			String queryString = "from Asset Asset where Asset.itemNumber = ? and Asset.itemLocation =? ";


			List resultList = dbs.query(queryString, new Object[] {itemNumber, itemLocation } , new Type[] { Hibernate.STRING ,Hibernate.STRING}) ;

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