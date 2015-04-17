package com.tsa.puridiom.invitem.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class InvItemRetrieveWithLocationAndBin extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String itemNumber = (String)incomingRequest.get("InvItem_itemNumber");
			String itemLocation = (String)incomingRequest.get("InvLocation_itemLocation");
			String icRcString = (String)incomingRequest.get("InvBinLocation_icRc");
			BigDecimal icRc = new BigDecimal(icRcString);

			String queryString = "from InvItem as InvItem, InvLocation as InvLocation, InvBinLocation as invBinLocation" +
					" where InvItem.itemNumber = InvLocation.id.itemNumber" + 
					" and invBinLocation.itemNumber = InvItem.itemNumber" +
					" and InvItem.itemNumber = ? " +
					" and InvLocation.id.itemLocation = ? " +
					" and invBinLocation.icRc = ?";
					
			List resultList = dbs.query(queryString, new Object[] {itemNumber, itemLocation, icRc} , 
				new Type[] { Hibernate.STRING, Hibernate.STRING, Hibernate.BIG_DECIMAL}) ;
					
			if (resultList != null && resultList.size() > 0)
			{
				Object resultObj[] = (Object[]) resultList.get(0);
				//set InvLocation entity in incomingRequest
				incomingRequest.put("invLocation", resultObj[1]);
				//set InvBinLocation entity in incomingRequest
				incomingRequest.put("invBinLocation", resultObj[2]);
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
		return result;
	}

}