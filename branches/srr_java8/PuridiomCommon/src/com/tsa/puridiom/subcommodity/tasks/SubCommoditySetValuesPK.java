package com.tsa.puridiom.subcommodity.tasks;

import com.tsa.puridiom.entity.SubCommodity;
import java.util.Map;

public class SubCommoditySetValuesPK
{
	public void setValues(Map incomingRequest, SubCommodity subCommodity) throws Exception
	{
		try
		{
			String commodityCode = (String) incomingRequest.get("SubCommodity_commodity");
			subCommodity.setCommodity(commodityCode);
		}
		catch (Exception e)
		{
			throw e;
		}
	}
}