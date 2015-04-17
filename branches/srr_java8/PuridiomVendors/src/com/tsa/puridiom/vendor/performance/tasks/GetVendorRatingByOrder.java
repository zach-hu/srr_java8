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
public class GetVendorRatingByOrder extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession");
			String icHeader = (String ) incomingRequest.get("PerformanceDetail_icPoHeader");

			String sql = "select PoHeader.vendorRating from PoHeader as PoHeader where PoHeader.id = ?";
			List objList = dbs.query(sql, new Object[] {icHeader} , new Type[] { Hibernate.STRING}) ;
			if(objList != null)
			{
				if(objList.size() > 0)
				{
					BigDecimal rating = (BigDecimal)objList.get(0);
					result = rating;
				}
			}

			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException("Vendor Rating By Order could not be Obtained.", e);
		}

		return result;
	}
}
