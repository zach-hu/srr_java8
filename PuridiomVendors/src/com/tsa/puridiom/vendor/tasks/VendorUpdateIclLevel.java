package com.tsa.puridiom.vendor.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;

import java.sql.Types;
import java.util.List;
import java.util.Map;

public class VendorUpdateIclLevel extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession");

			List vendorCommRelList = (List)incomingRequest.get("vendorCommRelList");
			if (vendorCommRelList != null && vendorCommRelList.size() > 0)
			{
				for (int i = 0; i < vendorCommRelList.size(); i++)
				{
					VendorCommRel vendorCommRel = (VendorCommRel)vendorCommRelList.get(i);
					if (vendorCommRel != null)
					{
						String vendorId = vendorCommRel.getComp_id().getVendorId();
						String query = "UPDATE VENDOR SET VENDOR.ICL_LEVEL = (" +
								"SELECT MAX(COMMODITY.ICL_LEVEL) FROM COMMODITY WHERE COMMODITY.COMMODITY IN (SELECT VENDOR_COMM_REL.COMMODITY_CODE FROM VENDOR_COMM_REL WHERE VENDOR_COMM_REL.VENDOR_ID = ?)" +
							") WHERE VENDOR.VENDOR_ID = ?";
						Object [] args = {vendorId, vendorId};
						Integer [] types = {Types.VARCHAR, Types.VARCHAR};
						dbs.sqlUpdate(query, args, types);
					}
				}
			}

			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}
}
