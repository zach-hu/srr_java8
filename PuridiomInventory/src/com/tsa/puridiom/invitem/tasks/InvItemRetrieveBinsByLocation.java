package com.tsa.puridiom.invitem.tasks;

import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import java.util.List;
import java.util.Map;

public class InvItemRetrieveBinsByLocation extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		List	resultList = null ;

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String itemNumber = (String ) incomingRequest.get("InvItem_itemNumber");
			String itemLocation = (String ) incomingRequest.get("InvBinLocation_itemLocation");
			String oid = (String) incomingRequest.get("organizationId") ;

			PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
			String 	duomRequired = propertiesManager.getProperty("INVENTORY", "DUALUOMREQUIRED", "N");

			String queryString = "from InvItem as InvItem, InvBinLocation as InvBinLocation" +
			" where InvItem.itemNumber = InvBinLocation.itemNumber" +
			" and InvBinLocation.itemLocation = ? order by InvBinLocation.itemNumber ASC, InvBinLocation.aisle, InvBinLocation.locrow, InvBinLocation.tier, InvBinLocation.bin ASC";
			if (duomRequired.equalsIgnoreCase("Y")) {
				queryString = "from InvItem as InvItem, InvBinLocation as InvBinLocation, Commodity as Commodity" +
				" where InvItem.itemNumber = InvBinLocation.itemNumber and (InvItem.commodity = Commodity.commodity or InvItem.commodity is null)" +
				" and InvBinLocation.itemLocation = ? order by InvBinLocation.itemNumber ASC, InvBinLocation.aisle, InvBinLocation.locrow, InvBinLocation.tier, InvBinLocation.bin ASC";
			}

			resultList = dbs.query(queryString, new Object[] {itemLocation} ,
			new Type[] { Hibernate.STRING}) ;

			if (resultList != null && resultList.size() > 0)
			{
				Object resultObj[] = (Object[]) resultList.get(0);

				//set InvLocation entity in incomingRequest
				incomingRequest.put("invItemBinLocation", resultObj[1]);
				//return InvItem entity
				result = resultObj[0];
			}
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