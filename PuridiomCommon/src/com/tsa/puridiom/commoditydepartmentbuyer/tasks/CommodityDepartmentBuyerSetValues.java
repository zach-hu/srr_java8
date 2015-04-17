package com.tsa.puridiom.commoditydepartmentbuyer.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.utility.*;
import com.tsagate.foundation.processengine.*;
import java.math.BigDecimal;
import java.util.Map;

public class CommodityDepartmentBuyerSetValues extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			CommodityDepartmentBuyer commodityDepartmentBuyer = (CommodityDepartmentBuyer) incomingRequest.get("commodityDepartmentBuyer");
			if (commodityDepartmentBuyer == null)
			{
				commodityDepartmentBuyer = new CommodityDepartmentBuyer();
			}

			if (incomingRequest.containsKey("CommodityDepartmentBuyer_icHeader"))
			{
				String icHeaderString = (String) incomingRequest.get("CommodityDepartmentBuyer_icHeader");
				if (Utility.isEmpty( icHeaderString))
				{
					icHeaderString = "0";
				}
				BigDecimal icHeader = new BigDecimal ( icHeaderString );
				commodityDepartmentBuyer.setIcHeader(icHeader);
			}

			if (incomingRequest.containsKey("CommodityDepartmentBuyer_commodity"))
			{
				String commodityCode = (String) incomingRequest.get("CommodityDepartmentBuyer_commodity");
				commodityDepartmentBuyer.setCommodity(commodityCode);
			}

			if (incomingRequest.containsKey("CommodityDepartmentBuyer_departmentCode"))
			{
				String departmentCode = (String ) incomingRequest.get("CommodityDepartmentBuyer_departmentCode");
				commodityDepartmentBuyer.setDepartmentCode(departmentCode);
			}
			if (incomingRequest.containsKey("CommodityDepartmentBuyer_userId"))
			{
				String userId = (String ) incomingRequest.get("CommodityDepartmentBuyer_userId");
				commodityDepartmentBuyer.setUserId(userId);
			}

			result = commodityDepartmentBuyer;
			this.status = Status.SUCCEEDED;
		}
		catch (Exception e)
		{
			this.status = Status.FAILED;
			throw e;
		}
		return result;
	}
}