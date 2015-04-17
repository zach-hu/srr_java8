package com.tsa.puridiom.bomcomponent.tasks;

import com.tsa.puridiom.entity.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

public class BomComponentSetValuesPK
{
	public void setValues(Map incomingRequest, BomComponent bomcomponent ) throws Exception
	{
		try
		{
			String icComponentString = (String) incomingRequest.get("BomComponent_icComponent");
			BigDecimal icComponent = new BigDecimal ( icComponentString );
			bomcomponent.setIcComponent(icComponent);
		}
		catch (Exception e)
		{
			throw e;
		}
	}
}