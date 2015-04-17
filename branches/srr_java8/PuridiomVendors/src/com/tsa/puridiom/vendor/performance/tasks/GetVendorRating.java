/*
 * Created on Aug 19, 2003
 */
package com.tsa.puridiom.vendor.performance.tasks;
import java.math.BigDecimal;
import java.util.*;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.TsaException;
/**
 * @author Administrator
 */
public class GetVendorRating extends Task
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
			String sql = "SELECT COUNT(*),  SUM(PoHeader.vendorRating) FROM PoHeader AS PoHeader WHERE PoHeader.vendorId = ? AND PoHeader.vendorRating >= 0 AND PoHeader.rated = 'Y'";
			List objList = dbs.query(sql, new Object[] {vendorId} , new Type[] { Hibernate.STRING}) ;
			BigDecimal ordersRated = new BigDecimal(0);
			if(objList != null)
			{
				if(objList.size() > 0)
				{
					Object temp[] = (Object[])objList.get(0);
					BigDecimal sum = (BigDecimal)temp[1];
					if(sum == null){	sum = new BigDecimal(0);	}
					int count = Integer.parseInt(temp[0].toString());
					BigDecimal bdTmp = new BigDecimal(count);
					if(bdTmp.compareTo(new BigDecimal(0)) > 1)
					{
						bdTmp = new BigDecimal(1);
					}
					else
					{
						ordersRated = bdTmp;
					}
					if(ordersRated.compareTo(new BigDecimal(0)) < 1)
					{
						result = new BigDecimal(0);
                        incomingRequest.put("Vendor_rated", "N");
					}
					else
					{
                        incomingRequest.put("Vendor_rated", "Y");
						try
						{
							result = sum.divide(bdTmp, BigDecimal.ROUND_HALF_UP);
						}
						catch (Exception e) {
							result = new BigDecimal("0");
							Log.error(this, "Vendor rating was not found." + e);
						}
					}
				}
			}
			incomingRequest.put("ordersRated", ordersRated);

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