package com.tsa.puridiom.commodity.tasks;

import com.tsa.puridiom.entity.Commodity;
import java.util.Map;

public class CommoditySetValuesPK
{
	public void setValues(Map incomingRequest, Commodity commodity) throws Exception
	{
		try
		{
			String commodityCode = (String) incomingRequest.get("Commodity_commodity");
			commodity.setCommodity(commodityCode);
		}
		catch (Exception e)
		{
			throw e;
		}
	}
}