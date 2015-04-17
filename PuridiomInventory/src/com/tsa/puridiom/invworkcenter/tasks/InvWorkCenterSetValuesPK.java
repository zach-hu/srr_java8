package com.tsa.puridiom.invworkcenter.tasks;

import com.tsa.puridiom.entity.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

public class InvWorkCenterSetValuesPK
{
	public void setValues(Map incomingRequest, InvWorkCenter invworkcenter ) throws Exception
	{
		try
		{
			String workCenterId = (String ) incomingRequest.get("InvWorkCenter_workCenterId");
			invworkcenter.setWorkCenterId(workCenterId);
		}
		catch (Exception e)
		{
			throw e;
		}
	}
}