package com.tsa.puridiom.invmanufacturer.tasks;

import com.tsa.puridiom.entity.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

public class InvManufacturerSetValuesPK
{
	public void setValues(Map incomingRequest, InvManufacturer invmanufacturer ) throws Exception
	{
		try
		{
			String icManufacturerString = (String) incomingRequest.get("InvManufacturer_icManufacturer");
			BigDecimal icManufacturer = new BigDecimal ( icManufacturerString );
			invmanufacturer.setIcManufacturer(icManufacturer);
		}
		catch (Exception e)
		{
			throw e;
		}
	}
}