/*
 * Created on Aug 19, 2003
 */
package com.tsa.puridiom.vendor.performance.tasks;
import java.math.BigDecimal;
import java.sql.Types;
import java.util.*;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.PerformanceDetail;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;
/**
 * @author Administrator
 */
public class UpdateOrderVendorRating extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession");
			BigDecimal PoHeader_vendorRating = (BigDecimal)incomingRequest.get("PoHeader_vendorRating");
			String PerformanceDetail_icPoHeader = (String)incomingRequest.get("PerformanceDetail_icPoHeader");
			String PoHeader_rated = (String)incomingRequest.get("PoHeader_rated");
			if(HiltonUtility.isEmpty(PoHeader_rated)){
	    		PoHeader_rated = "N";
	    	}
			String sql = "UPDATE Po_header SET vendor_Rating = ?, rated = ? WHERE ic_po_header = ?";
			Object [] args = {PoHeader_vendorRating.toString(), PoHeader_rated, PerformanceDetail_icPoHeader};
			Integer [] types = {Types.VARCHAR, Types.VARCHAR, Types.VARCHAR};

			this.setStatus(dbs.sqlUpdate(sql, args, types));
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException("Order Rating could not be Updated.", e);
		}

		return result;
	}
}
