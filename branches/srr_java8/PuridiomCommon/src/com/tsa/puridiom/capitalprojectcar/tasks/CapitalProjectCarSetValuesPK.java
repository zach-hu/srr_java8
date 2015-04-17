package com.tsa.puridiom.capitalprojectcar.tasks;

import com.tsa.puridiom.entity.*;
import java.math.BigDecimal;
import java.util.Map;

public class CapitalProjectCarSetValuesPK
{
	public void setValues(Map incomingRequest, CapitalProjectCar capitalProjectCar ) throws Exception
	{
		try
		{
			String icProjectCarString = (String) incomingRequest.get("CapitalProjectCar_icProjectCar");
			BigDecimal icProjectCar = new BigDecimal ( icProjectCarString );
			capitalProjectCar.setIcProjectCar(icProjectCar);
		}
		catch (Exception e)
		{
			throw e;
		}
	}
}