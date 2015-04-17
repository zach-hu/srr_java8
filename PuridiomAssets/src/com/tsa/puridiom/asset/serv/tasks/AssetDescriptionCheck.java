package com.tsa.puridiom.asset.serv.tasks;

import com.tsa.puridiom.entity.InvItem;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;
//import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class AssetDescriptionCheck extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		/*
		 * Method executeTask.
		 *  @param object
		 *  @author EDSAC
		 *  returns a inItem without description
		 * */

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String itemNumber = (String) incomingRequest.get("Asset_itemNumber");
			String description = (String) incomingRequest.get("Asset_description");
			if (description.equals("null")) {
				String queryString = "from InvItem InvItem where InvItem.itemNumber = ? ";
				List resultList = dbs.query(queryString, new Object[] {itemNumber}, new Type[] {Hibernate.STRING});
				InvItem invItem = (InvItem) resultList.get(0);
				description = invItem.getDescription();
			}
			incomingRequest.put("Asset_description",description);
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