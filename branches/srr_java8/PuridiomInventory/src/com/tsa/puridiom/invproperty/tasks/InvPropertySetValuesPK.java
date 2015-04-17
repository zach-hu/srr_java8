package com.tsa.puridiom.invproperty.tasks;

import com.tsa.puridiom.entity.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

public class InvPropertySetValuesPK
{
	public void setValues(Map incomingRequest, InvProperty invproperty ) throws Exception
	{
		try
		{
			String icPropertyString = (String) incomingRequest.get("InvProperty_icProperty");
			BigDecimal icProperty = new BigDecimal ( icPropertyString );
			invproperty.setIcProperty(icProperty);
		}
		catch (Exception e)
		{
			throw e;
		}
	}
}