/*
 * Created on Aug 19, 2003
 */
package com.tsa.puridiom.vendor.performance.tasks;
import java.math.BigDecimal;
import java.util.*;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsa.puridiom.entity.PerformanceDetail;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;
/**
 * @author Administrator
 */
public class GetOrdersRated extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession");
			String vendorId = (String ) incomingRequest.get("Vendor_vendorId");
			//String sql = "select sum(PoHeader.vendorRating)/ count(*) from PoHeader as PoHeader where PoHeader.vendorId = ?";
			String sql = "select count(*) from PoHeader as PoHeader where PoHeader.vendorId = ? AND PoHeader.vendorRating > 0";
			List objList = dbs.query(sql, new Object[] {vendorId} , new Type[] { Hibernate.STRING}) ;
			//BigDecimal ordersRated = new BigDecimal(0);
			if(objList != null)
			{
				if(objList.size() == 1)
				{
					Number count = (Number)objList.get(0);
					result = count;
				}
			}
			//incomingRequest.put("ordersRated", ordersRated);

			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException("Vendor Rating could not be Obtained.", e);
		}

		return result;
	}
}
