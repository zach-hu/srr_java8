package com.tsa.puridiom.commoditydepartmentbuyer.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class CommodityDepartmentBuyerAdd extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			CommodityDepartmentBuyer commodityDepartmentBuyer = (CommodityDepartmentBuyer)incomingRequest.get("commodityDepartmentBuyer");
			if (commodityDepartmentBuyer == null)
			{
				throw new Exception ("CommdityDepartmentBuyer was not found.");
			}

			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			dbs.add(commodityDepartmentBuyer);

			result = commodityDepartmentBuyer;
			this.setStatus(dbs.getStatus()) ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			//throw e;
		}
		return result;
	}

}