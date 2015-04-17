package com.tsa.puridiom.invmethod.tasks;

import com.tsa.puridiom.entity.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

public class InvMethodSetValuesPK
{
	public void setValues(Map incomingRequest, InvMethod invmethod ) throws Exception
	{
		try
		{
			String methodId = (String ) incomingRequest.get("InvMethod_methodId");
			invmethod.setMethodId(methodId);
		}
		catch (Exception e)
		{
			throw e;
		}
	}
}