package com.tsa.puridiom.asset.serv.tasks;

import com.tsa.puridiom.entity.Asset;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;
//import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class AssetRetrieveByItemNumber extends Task{
	public Object  executeTask (Object object) throws Exception
	{

		/*
		 * @author EDSAC
		 * return an Asset_itemNumber list
		 */

		Map incomingRequest = (Map)object;
		List resultList = null;

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession");
			String tagNumber = (String ) incomingRequest.get("Asset_tagNumber");
			String itemNumber = (String ) incomingRequest.get("Asset_itemNumber");

			if (itemNumber==null)
			{
				itemNumber = (String ) incomingRequest.get("InvItem_itemNumber");
				if (itemNumber==null)
				{
					String queryString = "from Asset Asset where Asset.tagNumber = ? ";
					resultList = dbs.query(queryString, new Object[] {tagNumber } , new Type[] { Hibernate.STRING}) ;
					Asset asset = (Asset) resultList.get(0);
					itemNumber = asset.getItemNumber();
				}
			}

			String queryString = "from Asset Asset where Asset.itemNumber = ? order by Asset.assetStatus ";
			resultList = dbs.query(queryString, new Object[] {itemNumber} , new Type[] { Hibernate.STRING}) ;
			this.setStatus(dbs.getStatus()) ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return resultList;
	}

}