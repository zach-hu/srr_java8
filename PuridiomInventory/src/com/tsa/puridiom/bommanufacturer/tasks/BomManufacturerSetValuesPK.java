package com.tsa.puridiom.bommanufacturer.tasks;

import com.tsa.puridiom.entity.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

public class BomManufacturerSetValuesPK
{
	public void setValues(Map incomingRequest, BomManufacturer bommanufacturer ) throws Exception
	{
		try
		{
			String icManufacturerString = (String) incomingRequest.get("BomManufacturer_icManufacturer");
			BigDecimal icManufacturer = new BigDecimal ( icManufacturerString );
			bommanufacturer.setIcManufacturer(icManufacturer);
		}
		catch (Exception e)
		{
			throw e;
		}
	}
}