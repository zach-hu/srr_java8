package com.tsa.puridiom.asset.serv.tasks;

import com.tsa.puridiom.entity.Asset;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class AssetStatusRetrieveByItem extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		/*
		 *@author EDSAC
		 *return an itemNumber list with his status and quantity for each status
		 *
		 */

		Map incomingRequest = (Map)object;
		List resultList = null;
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String tagNumber = (String ) incomingRequest.get("Asset_tagNumber");
			String itemNumber = (String) incomingRequest.get("Asset_itemNumber");
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
			String queryString = "select Asset.assetStatus, count(*) from Asset Asset where Asset.itemNumber=? group by Asset.assetStatus ";
			resultList = dbs.query(queryString, new Object[] {itemNumber } , new Type[] { Hibernate.STRING}) ;
			for (int i=0; i<resultList.size(); i++)
			{
				Object temp[] = (Object[]) resultList.get(i);
				String s_count = String.valueOf((Integer) temp[1]);
				temp[1] = new BigDecimal(s_count);
				resultList.set(i,temp);
			}
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return resultList;
	}
}