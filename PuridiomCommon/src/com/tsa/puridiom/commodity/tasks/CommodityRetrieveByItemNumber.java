package com.tsa.puridiom.commodity.tasks;

import com.tsa.puridiom.entity.Commodity;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import java.util.List;
import java.util.Map;

/**
 * input: commodity id as Commodity_commodity
 * output: Commodity as commodity
 */
public class CommodityRetrieveByItemNumber extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession");
			String itemNumber = (String ) incomingRequest.get("itemNumber");

			String queryString = "from Commodity as c, InvItem as i where i.commodity = c.commodity AND " +
													 "i.itemNumber = ?";

			List resultList = dbs.query(queryString, new Object[] {itemNumber} , new Type[] { Hibernate.STRING}) ;

			if (resultList != null && resultList.size() > 0)
			{
				result = resultList.get(0);

				if (!(result instanceof Commodity)) {
					result = resultList.get(1);
				}
			}
			this.setStatus(dbs.getStatus());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			this.setStatus(Status.FAILED);
			throw new TsaException(this.getName(), e);
		}
		return result;
	}

}