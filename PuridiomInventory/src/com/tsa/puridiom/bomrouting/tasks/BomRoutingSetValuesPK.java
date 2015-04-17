package com.tsa.puridiom.bomrouting.tasks;

import com.tsa.puridiom.entity.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

public class BomRoutingSetValuesPK
{
	public void setValues(Map incomingRequest, BomRouting bomrouting ) throws Exception
	{
		try
		{
			String icRoutingString = (String) incomingRequest.get("BomRouting_icRouting");
			BigDecimal icRouting = new BigDecimal ( icRoutingString );
			bomrouting.setIcRouting(icRouting);
		}
		catch (Exception e)
		{
			throw e;
		}
	}
}